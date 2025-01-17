let selectedProjectId = null; // Variable to store the selected projectId

async function fetchBudgets() {
    try {
        const response = await fetch('${API_BASE_URL}/api/projectS/budgets');
        // const response = await fetch('http://localhost:9090/api/projectS/budgets');

        if (response.ok) {
            const budgets = await response.json();
            const tableBody = document.getElementById('budgetTableBody');
            tableBody.innerHTML = ''; // Clear existing rows before populating

            budgets.forEach(budget => {
                const row = document.createElement('tr');

                const idCell = document.createElement('td');
                idCell.textContent = budget.id || 'N/A';
                row.appendChild(idCell);

                const projectIdCell = document.createElement('td');
                projectIdCell.textContent = budget.project.id || 'No Project Id';
                row.appendChild(projectIdCell);

                const projectBudgetCell = document.createElement('td');
                projectBudgetCell.textContent = budget.project.budget || 'No Budget';
                row.appendChild(projectBudgetCell);

                const budgetIdCell = document.createElement('td');
                budgetIdCell.textContent = budget.manager.id || 'No ID';
                row.appendChild(budgetIdCell);

                const vendorEstimateCell = document.createElement('td');
                vendorEstimateCell.textContent = budget.estimatedBudget || 'No Estimate';
                row.appendChild(vendorEstimateCell);

                const estimateDateCell = document.createElement('td');
                estimateDateCell.textContent = budget.uploadDate || 'No Estimate Date';
                row.appendChild(estimateDateCell);

                const actionCell = document.createElement('td');
                const selectButton = document.createElement('button');
                selectButton.textContent = 'Select';
                selectButton.classList.add('btn', 'btn-primary');
                selectButton.onclick = () => openPopup(budget.project.id, budget.id);
                
                // Disable "Select" buttons for other rows with the same projectId if a project has already been selected
                if (selectedProjectId && budget.project.id === selectedProjectId) {
                    selectButton.disabled = true; // Disable button if projectId matches the selected one
                }

                actionCell.appendChild(selectButton);
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

function openPopup(projectId, managerBudgetId) {
    // Set hidden fields for projectId and managerBudgetId
    document.getElementById('projectId').value = projectId;
    document.getElementById('managerBudgetId').value = managerBudgetId;

    // Show the popup
    const popup = document.getElementById('budgetPopup');
    popup.style.display = 'flex';

    // Close popup actions
    document.getElementById('cancelPopup').onclick = closePopup;
    document.getElementById('closePopup').onclick = closePopup;

    // Handle form submit
    document.getElementById('budgetForm').onsubmit = (e) => {
        e.preventDefault();
        handleSubmit();
    };

    // Disable "Select" button for the selected projectId
    selectedProjectId = projectId; // Set the selected projectId to disable buttons for other rows
    fetchBudgets(); // Re-fetch budgets to update button states
}

function closePopup() {
    // Hide the popup and reset the selected projectId
    document.getElementById('budgetPopup').style.display = 'none';
    selectedProjectId = null;
    fetchBudgets(); // Re-fetch budgets to enable all buttons again
}

async function handleSubmit() {
    const projectId = document.getElementById('projectId').value;
    const managerBudgetId = document.getElementById('managerBudgetId').value;
    const materialCost = document.getElementById('materialCost').value;
    const profitMargin = document.getElementById('profitMargin').value;

    console.log('Submitting:', { projectId, managerBudgetId, materialCost, profitMargin });

    try {
        // const response = await fetch(`http://localhost:9090/api/admin/project/${projectId}/select-budget`, {
        const response = await fetch(`${API_BASE_URL}/api/admin/project/${projectId}/select-budget`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                managerBudgetId,
                materialCost,
                profitMargin,
            }),
        });

        if (response.ok) {
            console.log('Budget selected successfully');
            alert('Budget selected successfully!');
            closePopup();
        } else {
            console.error('Failed to select budget:', response.status);
            alert('Failed to select budget. Please try again.');
        }
    } catch (error) {
        console.error('Error selecting budget:', error);
        alert('An error occurred while selecting the budget.');
    }
}

// Initialize
document.addEventListener('DOMContentLoaded', fetchBudgets);
