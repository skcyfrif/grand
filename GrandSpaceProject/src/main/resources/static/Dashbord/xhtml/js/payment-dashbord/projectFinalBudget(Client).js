async function fetchBudgetData() {
    try {
        // Fetching data from the API
        const response = await fetch('${API_BASE_URL}/api/client-budget/getAll');
        // const response = await fetch('http://localhost:9090/api/client-budget/getAll');
        
        if (response.ok) {
            const budgets = await response.json(); // Assuming the API returns a JSON array
            
            const tableBody = document.getElementById('budgetTableBody');
            tableBody.innerHTML = ''; // Clear existing rows before populating
            
            // Loop through each budget and create a table row
            budgets.forEach(budget => {
                const row = document.createElement('tr');
                
                // Pr.Id Cell
                const projectIdCell = document.createElement('td');
                projectIdCell.textContent = budget.project.id || 'N/A'; // Adjust according to API response
                row.appendChild(projectIdCell);
                
                // Material Budget Cell
                const materialBudgetCell = document.createElement('td');
                materialBudgetCell.textContent = budget.materialBudget || 'Not Selected'; // Adjust according to API response
                row.appendChild(materialBudgetCell);
                
                // With Material Cell
                const withMaterialCell = document.createElement('td');
                withMaterialCell.textContent = budget.withMaterials || 'Not Selected'; // Adjust according to API response
                row.appendChild(withMaterialCell);
                
                // Approved Budget Cell
                const approvedBudgetCell = document.createElement('td');
                approvedBudgetCell.textContent = budget.finalBudget || 'N/A'; // Adjust according to API response
                row.appendChild(approvedBudgetCell);

                // Approved Budget Cell (status)
                const statusCell = document.createElement('td');
                statusCell.textContent = budget.project.status || 'N/A'; // Adjust according to API response
                row.appendChild(statusCell);
                
                // Append the row to the table body
                tableBody.appendChild(row);

                // Store the projectBudgetId in sessionStorage for the first budget (or adjust as needed)
                if (budget.project && budget.project.id) {
                    sessionStorage.setItem("projectBudgetId", budget.id);
                }
            });
        } else {
            console.error('Failed to fetch data:', response.status);
        }
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

// Initialize the fetch function when the page loads
document.addEventListener('DOMContentLoaded', fetchBudgetData);
