const apiUrl = 'http://localhost:8080/tourism';

// Récupérer les données des villes depuis l'API
axios.get(`${apiUrl}/NameCity`)
    .then(response => {
        const cities = response.data;
        const departureCityDropdown = document.getElementById('depart-ville');
        const arrivalCityDropdown = document.getElementById('arrivee-ville');

        fillDropdownOptions(departureCityDropdown, cities);
        fillDropdownOptions(arrivalCityDropdown, cities);

        // Gérer les événements
        departureCityDropdown.addEventListener('change', handleDepartureAttractionChange);
        arrivalCityDropdown.addEventListener('change',handleArrivalAttractionChange);
    })
    .catch(error => {
        console.error('Erreur lors de la récupération des noms de villes:', error);
    });

// Récupérer les données des types d'événements depuis l'API
axios.get(`${apiUrl}/kindName`)
    .then(response => {
        const typesEvenements = response.data;
        typesEvenements.sort()
        const filterDropdown = document.getElementById('filter');

        fillDropdownOptions(filterDropdown, typesEvenements);
    })
    .catch(error => {
        console.error('Erreur lors de la récupération des types d\'événements:', error);
    });

function getSelectedTypes(){
    var selectedOptionsDiv = document.getElementById('selected-options');
    var selectedOptions = selectedOptionsDiv.getElementsByClassName('selected-option');
    var typesBatiments = [];

    for (var i = 0; i < selectedOptions.length; i++) {
        var selectedOption = selectedOptions[i];
        var typeBatiment = selectedOption.childNodes[0].nodeValue.trim();
        typesBatiments.push(typeBatiment);
    }
    return typesBatiments
}

// Fonction de gestion du changement de la ville de départ
function handleDepartureAttractionChange(event) {
    const selectedCity = event.target.value;
    const departureAttractionDropdown = document.getElementById('depart-lieu');

    // Réinitialiser les options du dropdown des attractions
    resetDropdownOptions(departureAttractionDropdown);

    // Vérifier si une ville a été sélectionnée et si un filtre est choisi
    if (selectedCity) {
        const selectedTypes = getSelectedTypes()
        if(selectedTypes.length === 0){
            // Effectuer une requête AJAX pour récupérer les attractions de la ville sélectionnée avec le filtre choisi
            axios.get(`${apiUrl}/fromCity`, {
                params: {
                    city: selectedCity}
            })
                .then(response => {
                    const attractions = response.data;
                    fillDropdownOptions(departureAttractionDropdown, attractions);
                })
                .catch(error => {
                    console.error('Erreur lors de la récupération des attractions :', error);
                });
        }else{

            let param = new URLSearchParams();
            param.append("city", selectedCity);
            selectedTypes.forEach(type => param.append("kinds", type));

            axios.get(`${apiUrl}/fromCityAndKinds`, { params: param })
                .then(response => {
                    const attractions = response.data;
                    fillDropdownOptions(departureAttractionDropdown, attractions);
                })
                .catch(error => {
                    console.error('Erreur lors de la récupération des attractions :', error);
                });
            }
        }
}
// Fonction de gestion du changement de la ville d'arrivee
function handleArrivalAttractionChange(event) {
    const selectedCity = event.target.value;
    const arrivalAttractionDropdown = document.getElementById('arrivee-lieu');

    // Réinitialiser les options du dropdown des attractions
    resetDropdownOptions(arrivalAttractionDropdown);

    // Vérifier si une ville a été sélectionnée
    if (selectedCity) {
        const selectedTypes = getSelectedTypes()
        if(selectedTypes.length === 0){
            // Effectuer une requête AJAX pour récupérer les attractions de la ville sélectionnée
            axios.get(`${apiUrl}/fromCity`, {params:{city:selectedCity}})
                .then(response => {
                    const attractions = response.data;
                    fillDropdownOptions(arrivalAttractionDropdown, attractions);
                })
                .catch(error => {
                    console.error('Erreur lors de la récupération des attractions:', error);
                });
        }else{
            let param = new URLSearchParams();
            param.append("city", selectedCity);
            selectedTypes.forEach(type => param.append("kinds", type));

            axios.get(`${apiUrl}/fromCityAndKinds`, { params: param })
                .then(response => {
                    const attractions = response.data;
                    fillDropdownOptions(arrivalAttractionDropdown, attractions);
                })
                .catch(error => {
                    console.error('Erreur lors de la récupération des attractions:', error);
                });
        }
    }
}


// Fonction utilitaire pour remplir les options d'un dropdown
function fillDropdownOptions(dropdown, options) {
    options.forEach(option => {
        const optionElement = document.createElement('option');
        optionElement.value = option;
        optionElement.text = option;
        dropdown.appendChild(optionElement);
    });
}

// Fonction utilitaire pour réinitialiser les options d'un dropdown
function resetDropdownOptions(dropdown) {
    while (dropdown.firstChild) {
        dropdown.removeChild(dropdown.firstChild);
    }
    const defaultOption = document.createElement('option');
    defaultOption.disabled = true;
    defaultOption.selected = true;
    defaultOption.textContent = 'Sélectionnez une option';
    dropdown.appendChild(defaultOption);
}

$(function() {
    $("#filter").change(function() {
        var selectedOption = $(this).children("option:selected");
        var selectedOptionText = selectedOption.text();
        if (selectedOptionText) {
            // Créer un nouvel élément div pour l'option sélectionnée
            var optionDiv = $('<div class="selected-option">' + selectedOptionText + '<button class="remove-option">x</button></div>');

            // Ajouter un gestionnaire de clics au bouton de suppression de l'option
            optionDiv.find('.remove-option').click(function() {
                optionDiv.remove();
                // Réactiver l'option dans le menu déroulant lorsqu'elle est supprimée
                selectedOption.prop("disabled", false);
            });

            // Ajouter l'option à la boîte de sélection
            $("#selected-options").append(optionDiv);

            // Désactiver l'option dans le menu déroulant
            selectedOption.prop("disabled", true);

            // Réinitialiser la sélection du menu déroulant à la première option
            $(this).prop('selectedIndex', 0);
        }
    });
});