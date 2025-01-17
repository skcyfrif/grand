document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const totalAmountToBePaid = document.getElementById("totalAmountToBePaid");

    // Fetch the total project count from the API
    fetch("${API_BASE_URL}/api/payment/total-amount-to-be-paid")
    // fetch("http://localhost:9090/api/payment/total-amount-to-be-paid")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            totalAmountToBePaid.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            totalAmountToBePaid.textContent = "Error"; // Display error message if fetch fails
        });
});

document.addEventListener('DOMContentLoaded', function () {
    // Fetch data for client budgets
    fetch('${API_BASE_URL}/api/client-budget/getAll')
    // fetch('http://localhost:9090/api/client-budget/getAll')
        .then(response => response.json())
        .then(clientBudgets => {
            // Fetch data for payment plans
            fetch('${API_BASE_URL}/api/payment/getAll')
            // fetch('http://localhost:9090/api/payment/getAll')
                .then(response => response.json())
                .then(paymentPlans => {
                    // Create the table body dynamically
                    let tbody = document.querySelector('#projects tbody');
                    clientBudgets.forEach(clientBudget => {
                        // Calculate the total number of paid phases
                        const paidPhases = paymentPlans.filter(paymentPlan =>
                            paymentPlan.clientBudget.id === clientBudget.id && paymentPlan.paid === true);

                        // Calculate the total paid amount
                        const totalPaidAmount = paidPhases.reduce((sum, paymentPlan) =>
                            sum + parseFloat(paymentPlan.amountDue), 0);

                        // Get the final budget from the client budget
                        const finalBudget = clientBudget.finalBudget;

                        // Calculate the remaining amount (final budget - total paid amount)
                        const remainingAmount = finalBudget - totalPaidAmount;

                        // Create a new row and append it to the table
                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td>${clientBudget.id}</td>
                            <td>5</td> <!-- Total Payment Phases are always 5 -->
                            <td>${paidPhases.length}</td> <!-- Number of Paid Phases -->
                            <td>${finalBudget}</td> <!-- Total Amount (final budget) -->
                            <td>${remainingAmount.toFixed(2)}</td> <!-- Remaining Amount -->
                        `;
                        tbody.appendChild(row);
                    });
                })
                .catch(error => console.error('Error fetching payment plans:', error));
        })
        .catch(error => console.error('Error fetching client budgets:', error));
});

