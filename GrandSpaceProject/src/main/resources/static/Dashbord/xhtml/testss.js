// Default values
const defaultArea = 1000; // 1000 square feet
const defaultCostPerSqFt = 1000; // Default cost per square foot for "Normal"

// Initialize the input fields with default values when the page loads
window.onload = function() {
    // Set default Builtup Area to 1000 and disable editing
    document.getElementById("area").value = defaultArea;
    
    // Set default Approx Cost (Per Square Feet) dropdown to "Normal" and disable it
    document.getElementById("costPerSqFt").value = defaultCostPerSqFt;

    // Calculate cost immediately on page load
    calculateCost();
}

function calculateCost() {
    // Get the values from the input fields
    const area = parseFloat(document.getElementById("area").value);
    const costPerSqFt = parseFloat(document.getElementById("costPerSqFt").value);

    // Validation: Ensure both inputs are numbers and positive
    if (isNaN(area) || isNaN(costPerSqFt)) {
        alert("Please enter valid numeric values.");
        return;
    }

    if (area <= 0 || costPerSqFt <= 0) {
        alert("Please ensure all values are positive.");
        return;
    }

    // Calculate the total cost based on the area and cost per square foot
    const totalCost = area * costPerSqFt;

    // Material cost percentages
    const cementPercentage = 16.4;
    const sandPercentage = 12.3;
    const aggregatePercentage = 7.4;
    const steelPercentage = 24.6;
    const finishersPercentage = 16.5;
    const fittingsPercentage = 28.8;

    // Calculate the cost for each material
    const cementCost = (cementPercentage / 100) * totalCost;
    const sandCost = (sandPercentage / 100) * totalCost;
    const aggregateCost = (aggregatePercentage / 100) * totalCost;
    const steelCost = (steelPercentage / 100) * totalCost;
    const finishersCost = (finishersPercentage / 100) * totalCost;
    const fittingsCost = (fittingsPercentage / 100) * totalCost;

    // Prepare the results list in the table format
    const costDetails = `
        <tr>
            <td>Cement (16.4%)</td>
            <td>${cementCost.toLocaleString()} Rs.</td>
        </tr>
        <tr>
            <td>Sand (12.3%)</td>
            <td>${sandCost.toLocaleString()} Rs.</td>
        </tr>
        <tr>
            <td>Aggregate (7.4%)</td>
            <td>${aggregateCost.toLocaleString()} Rs.</td>
        </tr>
        <tr>
            <td>Steel (24.6%)</td>
            <td>${steelCost.toLocaleString()} Rs.</td>
        </tr>
        <tr>
            <td>Finishers (16.5%)</td>
            <td>${finishersCost.toLocaleString()} Rs.</td>
        </tr>
        <tr>
            <td>Fittings (28.8%)</td>
            <td>${fittingsCost.toLocaleString()} Rs.</td>
        </tr>
        <tr class="table-success">
            <th>Total Cost</th>
            <th>${totalCost.toLocaleString()} Rs.</th>
        </tr>
    `;

    // Display the breakdown and the total cost
    document.getElementById("costDetails").innerHTML = costDetails;
}
