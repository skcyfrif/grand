document.addEventListener('DOMContentLoaded', function() {
    // Define the table body where data will be inserted
    const tableBody = document.querySelector('#budgets tbody');

    // Fetch the client budget data from the API
    fetch('${API_BASE_URL}/api/client-budget/getAll')
    // fetch('http://localhost:9090/api/client-budget/getAll')
        .then(response => response.json()) // Parse the JSON response
        .then(data => {
            // Clear existing rows in the table body
            tableBody.innerHTML = '';

            // Loop through each client budget and add it as a row in the table
            data.forEach(budget => {
                // Create a new row element
                const row = document.createElement('tr');

                // Create and append cells to the row for each budget field
                row.innerHTML = `
                    <td>${budget.project.id}</td>
                    <td>${budget.project.name}</td>
                    <td>$${budget.clientProjectBudget.toFixed(2)}</td>
                    <td>$${budget.managerEstimate.toFixed(2)}</td>
                    <td>$${budget.finalBudget.toFixed(2)}</td>
                    <td>${budget.withMaterials ? 'Yes' : 'No'}</td>
                    <td>${budget.materialBudget !== null && budget.materialBudget !== undefined ? '$' + budget.materialBudget.toFixed(2) : 'Not Available'}</td>
                `;

                // Append the row to the table body
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching client budget data:', error);
        });
});
