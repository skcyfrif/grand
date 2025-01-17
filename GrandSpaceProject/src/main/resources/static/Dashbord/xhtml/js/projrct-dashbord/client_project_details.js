// Fetch client ID from session
const clientId = sessionStorage.getItem("userId") || "123"; // Replace "123" with a fallback value if needed
console.log("Client ID:", clientId);

// Function to fetch and display projects
async function fetchProjects() {
    try {
        // const response = await fetch(`http://localhost:9090/api/project/client/${clientId}`);
        const response = await fetch(`http://88.222.241.45:6070/api/project/client/${clientId}`);
        if (!response.ok) {
            throw new Error("Failed to fetch projects");
        }
        const projects = await response.json();
        populateTable(projects);
    } catch (error) {
        console.error("Error:", error.message);
    }
}
// Function to populate the table
function populateTable(projects) {
    console.log("Projects data:", projects);

    const tableBody = document.querySelector("#ClientProjects tbody");
    console.log("Table Body:", tableBody);

    tableBody.innerHTML = ""; // Clear any existing rows
    projects.forEach((project) => {
        // Use default values for each field if they are missing or undefined
        const projectId = project.id || "N/A";
        const projectName = project.name || "Unnamed Project";
        const area = project.areaInSquareFeet || "Unknown Area";
        const managerFirstName = project.manager?.firstName || "N/A";
        const managerLastName = project.manager?.lastName || "";
        const managerEmail = project.manager?.email || "No Email";
        const status = project.status || "Not Specified";

        // Create a new table row
        const row = document.createElement("tr");

        // Populate the row with data
        row.innerHTML = `
            <td>${projectId}</td>
            <td>${projectName}</td>
            <td>${area}</td>
            <td>${managerFirstName} ${managerLastName}</td>
            <td>${managerEmail}</td>
            <td>${status}</td>
            <td>
                <button class="btn btn-primary" onclick="showBudgetDetails(${projectId})">
                    View Details
                </button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Function to handle the "View Details" button click
async function showBudgetDetails(projectId) {
    try {
        // Fetch budget details for the selected project
        // const response = await fetch(`http://localhost:9090/api/project/projects/${projectId}/budget`);
        const response = await fetch(`http://88.222.241.45:6070/api/project/projects/${projectId}/budget`);
        if (!response.ok) {
            throw new Error("Failed to fetch project budget details");
        }

        const budgetDetails = await response.json();
        console.log("Budget Details Response:", budgetDetails);


        // Use default values if budget or finalBudget is missing
        const budget = budgetDetails || "Not Available";
        const finalBudget = budgetDetails.finalBudget || "Not Available";

        // Create a modal container if it doesn't already exist
        let modalContainer = document.getElementById("budgetModal");
        if (!modalContainer) {
            modalContainer = document.createElement("div");
            modalContainer.id = "budgetModal";
            modalContainer.style.position = "fixed";
            modalContainer.style.top = "0";
            modalContainer.style.left = "0";
            modalContainer.style.width = "100%";
            modalContainer.style.height = "100%";
            modalContainer.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
            modalContainer.style.display = "flex";
            modalContainer.style.justifyContent = "center";
            modalContainer.style.alignItems = "center";
            modalContainer.style.zIndex = "1000";

            // Add the modal content
            modalContainer.innerHTML = `
                <div style="background: white; padding: 20px; border-radius: 8px; text-align: center; position: relative; width: 400px;">
                    <h4>Budget Details for Project ID: ${projectId}</h4>
                    <p><strong>Budget:</strong> ${budget}</p>
                    <p><strong>Final Budget:</strong> ${finalBudget}</p>
                    <button id="viewMoreBtn" class="btn btn-primary">View More</button>
                    <button id="closeModalBtn" class="btn btn-secondary">Close</button>
                </div>
            `;
            document.body.appendChild(modalContainer);

            // Add event listeners for buttons
            modalContainer.querySelector("#viewMoreBtn").addEventListener("click", () => {
                // Redirect to the project-budget.html page
                window.location.href = `project-budget.html`;
            });
            modalContainer.querySelector("#closeModalBtn").addEventListener("click", () => {
                document.body.removeChild(modalContainer);
            });
        }
    } catch (error) {
        console.error("Error fetching budget details:", error.message);
        alert("Unable to load budget details. Please try again.");
    }
}



// Fetch projects on page load
document.addEventListener("DOMContentLoaded", fetchProjects);