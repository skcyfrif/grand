document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const totalPaidAmount = document.getElementById("totalPaidAmount");

    // Fetch the total project count from the API
    fetch("${API_BASE_URL}/api/payment/total-paid-amount")
    // fetch("http://localhost:9090/api/payment/total-paid-amount")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            totalPaidAmount.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            totalPaidAmount.textContent = "Error"; // Display error message if fetch fails
        });
});

document.addEventListener('DOMContentLoaded', function () {
    // Fetch data for client budgets
    fetch('http://88.222.241.45:6070/api/client-budget/getAll')
    // fetch('http://localhost:9090/api/client-budget/getAll')
        .then(response => response.json())
        .then(clientBudgets => {
            // Fetch data for payment plans
            fetch('http://88.222.241.45:6070/api/payment/getAll')
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

                        // Calculate the remaining amount (if needed)
                        // const remainingAmount = finalBudget - totalPaidAmount;

                        // Create a new row and append it to the table
                        const row = document.createElement('tr');
                        row.innerHTML = `
                <td>${clientBudget.id}</td>
                <td>5</td> <!-- Total Payment Phases are always 5 -->
                <td>${paidPhases.length}</td> <!-- Number of Paid Phases -->
                <td>${finalBudget}</td> <!-- Total Amount (final budget) -->
                <td>${totalPaidAmount.toFixed(2)}</td> <!-- Total Paid Amount -->
              `;
                        tbody.appendChild(row);
                    });
                })
                .catch(error => console.error('Error fetching payment plans:', error));
        })
        .catch(error => console.error('Error fetching client budgets:', error));
});

