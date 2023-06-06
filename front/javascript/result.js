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

    const types = getSelectedTypes()

    console.log(types)


    let param = new URLSearchParams();
    param.append("start", departLieu);
    param.append("end", arriveeLieu);

    param.append("nbNode",sliderValue)

    if (types.length === 0){
        axios.get(`${apiUrl}/PathNoFilter`, { params: param })
            .then(response => {
                const attractions = response.data;
                console.log(response)
                showResult(response,resultsDiv)
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des attractions :', error);
            });
    }else{
        types.forEach(type => param.append("kinds", type));
        axios.get(`${apiUrl}/PathKindFilter`, { params: param })
            .then(response => {
                const attractions = response.data;
                console.log(response)
                showResult(response,resultsDiv)
            })
            .catch(error => {
                console.error('Erreur lors de la récupération des attractions :', error);
            });
    }


    // Réinitialisez le formulaire (facultatif)
    //form.reset();
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

function showResult(response,resultsDiv){
    const attractions = response.data;

    // Effacez le contenu précédent de resultsDiv
    resultsDiv.innerHTML = '';

    // Parcourez les attractions et créez des éléments HTML pour chaque attraction
    attractions.forEach(attraction => {
        // Créez un élément de div pour l'encadré de l'attraction
        const attractionDiv = document.createElement('div');
        attractionDiv.classList.add('attraction');

        // Créez un élément de titre pour le nom de l'attraction
        const attractionName = document.createElement('h2');
        attractionName.textContent = attraction.name;
        attractionDiv.appendChild(attractionName);

        // Créez un élément de paragraphe pour d'autres informations sur l'attraction (score, etc.)
        const attractionInfo = document.createElement('h3');
        attractionInfo.textContent = `Ville: ${attraction.city.name} `;
        const attractionInfo2 = document.createElement('h3');
        attractionInfo2.textContent = `Score: ${attraction.score}`;
        attractionDiv.appendChild(attractionInfo);
        attractionDiv.appendChild(attractionInfo2);

        // Ajoutez l'encadré de l'attraction à resultsDiv
        resultsDiv.appendChild(attractionDiv);
    });
}