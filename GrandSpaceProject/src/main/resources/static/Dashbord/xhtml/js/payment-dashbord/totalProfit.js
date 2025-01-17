document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const totalProfit = document.getElementById("totalProfit");

    // Fetch the total project count from the API
    fetch("${API_BASE_URL}/api/client-budget/totalNetProfit")
    // fetch("http://localhost:9090/api/client-budget/totalNetProfit")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            totalProfit.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            totalProfit.textContent = "Error"; // Display error message if fetch fails
        });
});

/////////////////////////////

document.addEventListener("DOMContentLoaded", function () {
    // Define the URL to fetch the data
    const url = "${API_BASE_URL}/api/client-budget/getAll";
    // const url = "http://localhost:9090/api/client-budget/getAll";

    // Function to fetch data and populate the table
    async function fetchAndPopulateTable() {
        try {
            // Fetch data from the API
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            // Get the table body element
            const tableBody = document.querySelector("#projects tbody");

            // Clear any existing rows
            tableBody.innerHTML = "";

            // Loop through the data and create table rows
            data.forEach((item) => {
                const row = document.createElement("tr");

                // Create and append cells for each column
                row.innerHTML = `
                    <td>${item.project?.id || "N/A"}</td>
                    <td>${item.finalBudget || "N/A"}</td>
                    <td>${item.managerEstimate || "N/A"}</td>
                    <td>${item.materialBudget || "N/A"}</td>
                    <td>${item.profit || "N/A"}</td>
                    
                `;

                // Append the row to the table body
                tableBody.appendChild(row);
            });
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    }

    // Call the function to populate the table
    fetchAndPopulateTable();
});
