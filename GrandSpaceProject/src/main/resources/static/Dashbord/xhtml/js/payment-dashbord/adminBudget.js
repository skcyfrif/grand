async function fetchProjectBudgets() {
    try {
        const response = await fetch('${API_BASE_URL}/api/admin/project-budgets');
        // const response = await fetch('http://localhost:9090/api/admin/project-budgets');
        if (response.ok) {
            const budgets = await response.json();
            const tableBody = document.getElementById('budgetTableBody');
            tableBody.innerHTML = '';

            budgets.forEach(budget => {
                const row = document.createElement('tr');

                // ID
                const idCell = document.createElement('td');
                idCell.textContent = budget.id || 'N/A';
                row.appendChild(idCell);

                // Material Cost
                const materialCostCell = document.createElement('td');
                materialCostCell.textContent = budget.materialCost || 'N/A';
                row.appendChild(materialCostCell);

                // With Material
                const withMaterialCell = document.createElement('td');
                withMaterialCell.textContent = budget.budgetWithMaterials || 'N/A';
                row.appendChild(withMaterialCell);

                // Without Material
                const withoutMaterialCell = document.createElement('td');
                withoutMaterialCell.textContent = budget.budgetWithoutMaterials || 'N/A';
                row.appendChild(withoutMaterialCell);

                // Margin
                const marginCell = document.createElement('td');
                marginCell.textContent = budget.profitMargin || 'N/A';
                row.appendChild(marginCell);

                // Vendor Estimate ID
                const vendorEstimateIdCell = document.createElement('td');
                vendorEstimateIdCell.textContent = budget.managerBudget.id || 'N/A';
                row.appendChild(vendorEstimateIdCell);

                // Vendor ID
                const vendorIdCell = document.createElement('td');
                vendorIdCell.textContent = budget.manager.id || 'N/A';
                row.appendChild(vendorIdCell);

                // Project ID
                const projectIdCell = document.createElement('td');
                projectIdCell.textContent = budget.project.id || 'N/A';
                row.appendChild(projectIdCell);

                // Action
                const actionCell = document.createElement('td');
                const publishButton = document.createElement('button');
                publishButton.textContent = 'Publish';
                publishButton.classList.add('btn', 'btn-success');
                publishButton.onclick = () => handlePublish(budget.id); // Pass budget id here
                actionCell.appendChild(publishButton);
                row.appendChild(actionCell);

                tableBody.appendChild(row);
            });
        } else {
            console.error('Failed to fetch data:', response.status);
        }
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

async function handlePublish(budgetId) {
    try {
        // const response = await fetch(`${API_BASE_URL}/api/admin/${budgetId}/publish`, {
        const response = await fetch(`${API_BASE_URL}/api/admin/${budgetId}/publish`, {
            method: 'PUT', // Change this to PUT
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            alert("Project Budget published successfully!");
            fetchProjectBudgets(); // Re-fetch the budgets to update the UI
        } else {
            console.error('Failed to publish:', response.status);
            alert("Failed to publish the project budget.");
        }
    } catch (error) {
        console.error('Error publishing:', error);
        alert("Error publishing the project budget.");
    }
}

// Initialize
document.addEventListener('DOMContentLoaded', fetchProjectBudgets);
