document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const totalNoOfVendors = document.getElementById("totalNoOfVendors");

    // Fetch the total project count from the API
    fetch("${API_BASE_URL}/api/manager/managers/count")
    // fetch("http://localhost:9090/api/manager/managers/count")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            totalNoOfVendors.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            totalNoOfVendors.textContent = "Error"; // Display error message if fetch fails
        });
});