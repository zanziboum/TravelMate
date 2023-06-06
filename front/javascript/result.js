// Sélectionnez le formulaire
const form = document.getElementById('myForm');

// Écoutez l'événement de soumission du formulaire
form.addEventListener('submit', function(event) {
    event.preventDefault(); // Empêche le formulaire de se soumettre normalement

    // Récupérez les valeurs des champs du formulaire
    const selectedOptions = document.getElementById('selected-options').innerHTML;
    const departVille = document.getElementById('depart-ville').value;
    const departLieu = document.getElementById('depart-lieu').value;
    const arriveeVille = document.getElementById('arrivee-ville').value;
    const arriveeLieu = document.getElementById('arrivee-lieu').value;
    const sliderValue = document.getElementById('sliderValue').innerHTML;

    // Affichez les valeurs dans la div des résultats
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = `
            <p>Options sélectionnées: ${selectedOptions}</p>
            <p>Départ: ${departVille}, ${departLieu}</p>
            <p>Arrivée: ${arriveeVille}, ${arriveeLieu}</p>
            <p>Valeur du slider: ${sliderValue}</p>
        `;

    // Réinitialisez le formulaire (facultatif)
    form.reset();
});