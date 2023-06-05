function populateDropdownOptions(url, selectElementId) {
var selectElement = document.getElementById(selectElementId);

// Make an Axios GET request to fetch the data from the Spring backend
axios.get(url)
.then(function (response) {
var options = response.data;

// Populate the dropdown options
options.forEach(function (option) {
var optionElement = document.createElement('option');
optionElement.value = option.value;
optionElement.textContent = option.label;
selectElement.appendChild(optionElement);
});
})
.catch(function (error) {
console.error('Request failed:', error);
});
}

// Call the function to populate the dropdown options
populateDropdownOptions('/api/cities', 'depart-ville');
populateDropdownOptions('/api/monuments', 'depart-lieu');
populateDropdownOptions('/api/cities', 'arrivee-ville');
populateDropdownOptions('/api/monuments', 'arrivee-lieu');
