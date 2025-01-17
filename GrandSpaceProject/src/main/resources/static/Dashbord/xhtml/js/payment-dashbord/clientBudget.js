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
                
                // Client Estimate Cell
                const clientEstimateCell = document.createElement('td');
                clientEstimateCell.textContent = budget.clientProjectBudget || 'N/A'; // Adjust according to API response
                row.appendChild(clientEstimateCell);
                
                // Vendor Estimate Cell
                const vendorEstimateCell = document.createElement('td');
                vendorEstimateCell.textContent = budget.managerEstimate || 'N/A'; // Adjust according to API response
                row.appendChild(vendorEstimateCell);
                
                // Approved Budget Cell
                const approvedBudgetCell = document.createElement('td');
                approvedBudgetCell.textContent = budget.finalBudget || 'N/A'; // Adjust according to API response
                row.appendChild(approvedBudgetCell);
                
                // Action Cell with a "Publish" button
                const actionCell = document.createElement('td');
                const publishButton = document.createElement('button');
                publishButton.textContent = 'Publish';
                publishButton.classList.add('btn', 'btn-primary');
                publishButton.onclick = () => handlePublish(budget.project.id, budget.projectBudget.manager.id); // Pass managerId
                actionCell.appendChild(publishButton);
                row.appendChild(actionCell);
                
                // Append the row to the table body
                tableBody.appendChild(row);
            });
        } else {
            console.error('Failed to fetch data:', response.status);
        }
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

// Function to handle the "Publish" button click
function handlePublish(projectId, managerId) {
    console.log('Publishing budget for project ID:', projectId, 'with Manager ID:', managerId);
    alert(`Asking to vendor with Manager ID: ${managerId}`);
    // Add your logic here to handle the publish action, like sending an API request or updating the UI.
}

// Initialize the fetch function when the page loads
document.addEventListener('DOMContentLoaded', fetchBudgetData);
