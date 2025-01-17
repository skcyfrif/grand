# Use a lightweight web server
FROM nginx:alpine

# Copy frontend files to the Nginx default public directory
COPY . /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]