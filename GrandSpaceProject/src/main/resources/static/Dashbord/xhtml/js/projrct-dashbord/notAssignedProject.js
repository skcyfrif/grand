document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const notAssignedProject = document.getElementById("notAssignedProject");

    // Fetch the total project count from the API
    // fetch("http://localhost:9090/api/project/projects/un-publish/count")
    fetch("http://88.222.241.45:6070/api/project/projects/un-publish/count")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            notAssignedProject.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            notAssignedProject.textContent = "Error"; // Display error message if fetch fails
        });
});

// Function to fetch all projects and populate the table
document.addEventListener('DOMContentLoaded', function () {
    // Function to fetch projects and update table
    function fetchProjects() {
        // fetch('http://localhost:9090/api/project/un-publish-projects')
        fetch('http://88.222.241.45:6070/api/project/un-publish-projects')
            .then(response => response.json()) // Assuming the response is in JSON format
            .then(data => {
                const tbody = document.querySelector('#projects tbody');
                tbody.innerHTML = ''; // Clear existing table rows

                // Loop through the data and add rows dynamically
                data.forEach(project => {
                    const row = document.createElement('tr');

                    row.innerHTML = `
                            <td>${project.id}</td>
                            <td>${project.name}</td>
                            <td>${project.status}</td>
                            <td>${project.clientName}</td>
                            <td>${project.assignedManager || 'Not Assigned'}</td>
                            <td>${project.description}</td>
                            <td>${project.registrationDate}</td>
                            <td>
                                <button class="btn btn-danger delete-btn" data-id="${project.id}">Delete</button>
                                <button class="btn btn-primary publish-btn" data-id="${project.id}">Publish</button>
                            </td>
                        `;

                    tbody.appendChild(row);
                });

                // Add event listeners for delete buttons
                document.querySelectorAll('.delete-btn').forEach(button => {
                    button.addEventListener('click', function () {
                        const projectId = this.getAttribute('data-id');
                        deleteProject(projectId);
                    });
                });

                // Add event listeners for publish buttons
                document.querySelectorAll('.publish-btn').forEach(button => {
                    button.addEventListener('click', function () {
                        const projectId = this.getAttribute('data-id');
                        publishProject(projectId);
                    });
                });
            })
            .catch(error => {
                console.error('Error fetching projects:', error);
            });
    }

    // Function to handle project deletion
    function deleteProject(projectId) {
        // fetch(`http://localhost:9090/api/project/delete/${projectId}`, {
        fetch(`http://88.222.241.45:6070/api/project/delete/${projectId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Project deleted successfully');
                    fetchProjects(); // Refresh the table after deletion
                } else {
                    alert('Error deleting project');
                }
            })
            .catch(error => {
                console.error('Error deleting project:', error);
            });
    }

    // Function to handle project publishing
    function publishProject(projectId) {
        // fetch(`http://localhost:9090/api/project/${projectId}/assign-to-managers`, {
        fetch(`http://88.222.241.45:6070/api/project/${projectId}/assign-to-managers`, {
            method: 'POST',
        })
            .then(response => {
                if (response.ok) {
                    alert('Project published successfully');
                    fetchProjects(); // Refresh the table after publishing
                } else {
                    alert('Error publishing project');
                }
            })
            .catch(error => {
                console.error('Error publishing project:', error);
            });
    }

    // Fetch projects on page load
    fetchProjects();
});
