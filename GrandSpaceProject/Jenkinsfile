pipeline {
    agent any

    environment {
        REGISTRY = "docker.io"
        IMAGE_NAME_BACKEND = "grandspace-fullstack"
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
        BUILD_TAG = "${env.BUILD_NUMBER}"
        SPRING_DATASOURCE_URL = "jdbc:mysql://172.22.0.2:3306/grandspace?createDatabaseIfNotExist=true"
        SPRING_DATASOURCE_USERNAME = "root"
        SPRING_DATASOURCE_PASSWORD = "root"
        DOCKER_NETWORK = "grandspace_network"
        DB_CONTAINER = "grandspace-db"
        PHPMYADMIN_CONTAINER = "grandspace-phpmyadmin"
        BACKEND_CONTAINER = "grandspace-container"
        APACHE_CONTAINER = "grand-apache"
        DOMAIN_NAME = "www.grandspace.co.in"
    }

    tools {
        maven 'maven'
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    echo "Cloning repository..."
                    checkout scm
                }
            }
        }

        stage('Create Docker Network') {
            steps {
                script {
                    echo "Ensuring Docker network exists..."
                    sh """
                    docker network inspect ${DOCKER_NETWORK} >/dev/null 2>&1 || docker network create ${DOCKER_NETWORK}
                    """
                }
            }
        }

        stage('Start MySQL') {
            steps {
                script {
                    echo "Ensuring MySQL container is running..."
                    sh """
                    if docker ps --filter "name=${DB_CONTAINER}" | grep -q ${DB_CONTAINER}; then
                        echo "Container ${DB_CONTAINER} is already running."
                    elif docker ps -a --filter "name=${DB_CONTAINER}" | grep -q ${DB_CONTAINER}; then
                        docker start ${DB_CONTAINER}
                    else
                        docker run -d --name ${DB_CONTAINER} \
                            --network ${DOCKER_NETWORK} \
                            -e MYSQL_ROOT_PASSWORD=${SPRING_DATASOURCE_PASSWORD} \
                            -e MYSQL_DATABASE=grandspace \
                            -p 3308:3306 mysql:5.7
                    fi
                    """
                }
            }
        }

        stage('Start phpMyAdmin') {
            steps {
                script {
                    echo "Starting phpMyAdmin container..."
                    sh """
                    docker ps -q --filter "name=${PHPMYADMIN_CONTAINER}" | grep -q . || \
                    docker run -d --name ${PHPMYADMIN_CONTAINER} \
                        --network ${DOCKER_NETWORK} \
                        -e PMA_HOST=${DB_CONTAINER} \
                        -e MYSQL_ROOT_PASSWORD=${SPRING_DATASOURCE_PASSWORD} \
                        -p 8182:80 phpmyadmin/phpmyadmin
                    """
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    echo "Logging in to Docker Hub..."
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_CREDENTIALS_ID}") {
                        echo 'Docker login successful'
                    }
                }
            }
        }

        stage('Build GrandSpaceProject') {
            steps {
                dir('GrandSpaceProject') {
                    script {
                        echo "Building GrandSpaceProject..."
                        sh 'mvn clean install'
                        sh """
                        docker build -t ${REGISTRY}/${IMAGE_NAME_BACKEND}:${BUILD_TAG} .
                        """
                    }
                }
            }
        }

        stage('Push Images to Docker Hub') {
            steps {
                script {
                    echo "Pushing images to Docker Hub..."
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_CREDENTIALS_ID}") {
                        sh """
                        docker tag ${REGISTRY}/${IMAGE_NAME_BACKEND}:${BUILD_TAG} ${REGISTRY}/cyfrifprotech/${IMAGE_NAME_BACKEND}:${BUILD_TAG}
                        docker push ${REGISTRY}/cyfrifprotech/${IMAGE_NAME_BACKEND}:${BUILD_TAG}
                        """
                    }
                }
            }
        }

        stage('Deploy Grandspace') {
            steps {
                script {
                    echo "Deploying GrandSpaceProject container..."
                    sh """
                    docker ps -a -q --filter "name=${BACKEND_CONTAINER}" | grep -q . && \
                    docker rm -f ${BACKEND_CONTAINER} || echo "No existing backend container to remove."

                    docker run -d --name ${BACKEND_CONTAINER} \
                        --network ${DOCKER_NETWORK} \
                        -e SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL} \
                        -e SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME} \
                        -e SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD} \
                        -p 9080:9090 ${REGISTRY}/cyfrifprotech/${IMAGE_NAME_BACKEND}:${BUILD_TAG}
                    """
                }
            }
        }

        stage('Start Apache HTTP Server') {
            steps {
                script {
                    echo "Starting Apache HTTP Server container..."

                    // Ensure apache.conf exists with proper permissions
                    sh """
                    mkdir -p ${WORKSPACE}/config

                    # Create apache.conf if it does not exist
                    docker run --rm -v ${WORKSPACE}/config:/config busybox sh -c '
                    if [ ! -f /config/apache.conf ]; then
                        echo "<VirtualHost *:80>
                                ServerName ${DOMAIN_NAME}
                                ProxyPass / http://${BACKEND_CONTAINER}:9080/
                                ProxyPassReverse / http://${BACKEND_CONTAINER}:9080/
                            </VirtualHost>" > /config/apache.conf
                        chmod 644 /config/apache.conf
                    fi
                    '
                    """

                    // Check if apache.conf is created and print it
                    sh "echo 'apache.conf content:' && cat ${WORKSPACE}/config/apache.conf"

                    // Ensure Docker network exists
                    sh """
                    docker network inspect ${DOCKER_NETWORK} >/dev/null 2>&1 || docker network create ${DOCKER_NETWORK}
                    """

                    // Remove old Apache container if exists
                    sh """
                    docker ps -q --filter "name=${APACHE_CONTAINER}" | grep -q . && \
                    docker rm -f ${APACHE_CONTAINER} || echo "No existing Apache container to remove."
                    """

                    // Check if port 80 is available (optional, for debugging)
                    sh """
                    sudo lsof -i :80 || echo "Port 80 is available"
                    """

                    // Start Apache container
                    sh """
                    docker run -d --name ${APACHE_CONTAINER} \
                        --network ${DOCKER_NETWORK} \
                        -v ${WORKSPACE}/config/apache.conf:/usr/local/apache2/conf/httpd.conf:ro \
                        -p 80:80 httpd:alpine || { echo "Apache container failed to start"; exit 1; }
                    """

                    // Check Apache container logs
                    sh "docker logs ${APACHE_CONTAINER}"
                }
            }
        }


        stage('Cleanup Unused Resources') {
            steps {
                script {
                    echo "Cleaning up unused Docker resources..."
                    sh """
                    docker image prune -f
                    docker container prune -f
                    """
                }
            }
        }
    }
}
