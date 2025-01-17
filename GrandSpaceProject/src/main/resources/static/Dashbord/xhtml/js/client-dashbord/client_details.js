// URL to fetch client data
const apiUrl = "${API_BASE_URL}/api/client/findAll";
// const apiUrl = "http://localhost:9090/api/client/findAll";

// Function to fetch client data
async function fetchClientData() {
    try {
        const response = await fetch(apiUrl);

        // Check if the response is ok
        if (!response.ok) {
            throw new Error(`Error fetching data: ${response.statusText}`);
        }

        // Parse JSON response
        const clients = await response.json();

        // Populate the table
        populateClientTable(clients);
    } catch (error) {
        console.error("Error:", error.message);
    }
}

// Function to populate the table with client data
function populateClientTable(clients) {
    const tableBody = document.querySelector(".table-responsive-md tbody");
    
    // Clear the table body (in case of previous data)
    tableBody.innerHTML = "";

    // Loop through the clients and add rows to the table
    clients.forEach((client) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td><strong>${client.id}</strong></td>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.email}</td>
            <td>${client.phone}</td>
            <td>${client.state}</td>
            <td>${client.city}</td>
            <td>${client.currentAddress}</td>
            <td>${client.premium ? "Yes" : "No"}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Fetch client data on page load
document.addEventListener("DOMContentLoaded", fetchClientData);
