document.addEventListener("DOMContentLoaded", function () {
    // Target the span where the total project count will be displayed
    const activeVendor = document.getElementById("activeVendor");

    // Fetch the total project count from the API
    fetch("${API_BASE_URL}/api/manager/active/count")
    // fetch("http://localhost:9090/api/manager/active/count")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Parse the JSON response
        })
        .then(data => {
            // Update the span content with the total project count
            activeVendor.textContent = data; // Assuming the response is a plain number
        })
        .catch(error => {
            console.error("Error fetching project count:", error);
            activeVendor.textContent = "Error"; // Display error message if fetch fails
        });
});