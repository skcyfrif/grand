document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const assignedProject = document.getElementById("assignedProject");

    // Fetch the total project count from the API
    fetch("http://88.222.241.45:6070/api/project/count-assigned-projects-last-year")
    // fetch("http://localhost:9090/api/project/count-assigned-projects-last-year")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            assignedProject.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            assignedProject.textContent = "Error"; // Display error message if fetch fails
        });
});

// Function to fetch all projects and populate the table
async function fetchProjects() {
    try {
        // Send a GET request to the API
        const response = await fetch('http://88.222.241.45:6070/api/project/assigned-projects-registered-last-year');
        // const response = await fetch('http://localhost:9090/api/project/assigned-projects-registered-last-year');

        // Check if the response is successful (status 200-299)
        if (response.ok) {
            // Parse the JSON response
            const projects = await response.json();

            // Log the response to verify its structure
            console.log(projects);

            // Get the table body element
            const tableBody = document.querySelector('#projects tbody');

            // Clear the existing table rows before adding new ones
            tableBody.innerHTML = '';

            // Loop through the projects and add each project to the table
            projects.forEach(project => {
                const row = document.createElement('tr');

                // Create table cells for each project field
                const idCell = document.createElement('td');
                idCell.textContent = project.id || 'N/A'; // Fallback if id is missing
                row.appendChild(idCell);

                const nameCell = document.createElement('td');
                nameCell.textContent = project.name || 'No name'; // Fallback if name is missing
                row.appendChild(nameCell);

                const statusCell = document.createElement('td');
                statusCell.textContent = project.status || 'No status'; // Fallback if status is missing
                row.appendChild(statusCell);

                // Extract client and manager names from the respective objects
                const clientName = project.client ? `${project.client.firstName}` : 'Unknown Client';
                const managerName = project.manager ? `${project.manager.firstName}` : 'Unknown Manager';

                // Populate client and manager name cells
                const clientCell = document.createElement('td');
                clientCell.textContent = clientName;
                row.appendChild(clientCell);

                const managerCell = document.createElement('td');
                managerCell.textContent = managerName;
                row.appendChild(managerCell);

                const descriptionCell = document.createElement('td');
                descriptionCell.textContent = project.description || 'No description'; // Fallback if description is missing
                row.appendChild(descriptionCell);

                const startDateCell = document.createElement('td');
                startDateCell.textContent = project.startDate || 'Not available'; // Fallback if startDate is missing
                row.appendChild(startDateCell);

                // Append the row to the table body
                tableBody.appendChild(row);
            });
        } else {
            console.error('Failed to fetch projects. Status:', response.status);
        }
    } catch (error) {
        console.error('Error fetching projects:', error);
    }
}

// Call fetchProjects function when the page loads
document.addEventListener('DOMContentLoaded', fetchProjects);


/////////////////////////////

