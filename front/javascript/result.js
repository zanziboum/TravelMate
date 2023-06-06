// Sélectionner le formulaire
const form = document.querySelector('form');

// Écouter l'événement de soumission du formulaire
form.addEventListener('submit', function(event) {
    event.preventDefault(); // Empêcher la soumission du formulaire

    // Récupérer les valeurs sélectionnées dans les dropdowns
    const selectedDepartureCity = document.getElementById('depart-ville').value;
    const selectedDepartureAttraction = document.getElementById('depart-lieu').value;
    const selectedArrivalCity = document.getElementById('arrivee-ville').value;
    const selectedArrivalAttraction = document.getElementById('arrivee-lieu').value;

    // Vérifier si toutes les valeurs sont sélectionnées
    if (
        selectedDepartureCity &&
        selectedDepartureAttraction &&
        selectedArrivalCity &&
        selectedArrivalAttraction
    ) {
        // Construire le message de résultat
        const resultMessage = `Vous avez sélectionné le départ de ${selectedDepartureAttraction} à ${selectedDepartureCity}, et l'arrivée à ${selectedArrivalAttraction} à ${selectedArrivalCity}.`;

        // Afficher le résultat dans la div "result"
        const resultDiv = document.getElementById('result');
        resultDiv.textContent = resultMessage;
    }
});
