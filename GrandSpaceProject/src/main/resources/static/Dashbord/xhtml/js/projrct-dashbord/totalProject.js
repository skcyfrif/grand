document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const totalProjectsSpan = document.getElementById("totalProjects");

    // Fetch the total project count from the API
    fetch("http://88.222.241.45:6070/api/project//projects/count")
        // fetch("http://localhost:9090/api/project/projects/count")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            totalProjectsSpan.textContent = data; // Assuming the response is a plain number
            console.log(data);
            
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            totalProjectsSpan.textContent = "Error"; // Display error message if fetch fails
        });
});

async function fetchProjects() {
    try {
        // Send a GET request to the API
        const response = await fetch('http://88.222.241.45:6070/api/project/projects/last-year');
        // const response = await fetch('http://localhost:9090/api/project/projects/last-year');

        // Check if the response is successful (status 200-299)
        if (response.ok) {
            const projects = await response.json();

            // Get the table body element
            const tableBody = document.querySelector('#projects tbody');
            
            // Clear existing table rows before adding new ones
            tableBody.innerHTML = '';

            // Loop through each project and create a row
            projects.forEach(project => {
                const row = document.createElement('tr');
                
                // Project ID
                const idCell = document.createElement('td');
                idCell.textContent = project.id || 'N/A';
                row.appendChild(idCell);

                // Project Name
                const nameCell = document.createElement('td');
                nameCell.textContent = project.name || 'No Client';
                row.appendChild(nameCell);

                // Project Status
                const statusCell = document.createElement('td');
                statusCell.textContent = project.status || 'No status';
                row.appendChild(statusCell);

                // Client Name
                const clientCell = document.createElement('td');
                clientCell.textContent = project.client.firstName || 'Unknown Client';
                row.appendChild(clientCell);

                // Manager Name
                const managerCell = document.createElement('td');
                managerCell.textContent = project.manager || 'Unknown Manager';
                row.appendChild(managerCell);

                // Registration Date
                const dateCell = document.createElement('td');
                dateCell.textContent = project.registrationDate || 'No start date';
                row.appendChild(dateCell);

                // Action Cell with View Button
                const actionCell = document.createElement('td');
                const viewButton = document.createElement('button');
                viewButton.classList.add('btn', 'btn-info');
                viewButton.textContent = 'View';
                viewButton.onclick = () => viewProject(project.description || 'No description available');
                actionCell.appendChild(viewButton);
                row.appendChild(actionCell);

                // Append the row to the table body
                // tableBody.appendChild(row);
                // tableBody.appendChild(row);
                tableBody.appendChild(row);
            });
        } else {
            console.error('Failed to fetch projects. Status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching projects:', error);
    }
}

// Function to view project description in a modal
function viewProject(description) {
    const modal = document.getElementById('viewModal');
    const descriptionElement = document.getElementById('projectDescription');
    
    descriptionElement.textContent = description;
    modal.style.display = 'flex';
}

// Function to close the modal
function closeModal() {
    const modal = document.getElementById('viewModal');
    modal.style.display = 'none';
}

// Call fetchProjects function when the page loads
document.addEventListener('DOMContentLoaded', fetchProjects);


/////////////////////////////

