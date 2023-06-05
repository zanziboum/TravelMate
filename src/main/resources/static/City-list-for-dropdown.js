// Enum definition
const City = {
    PARIS: { latitude: 48.8566, longitude: 2.3522, zipCode: "75000" },
    BOULOGNE_BILLANCOURT: { latitude: 48.8397, longitude: 2.2399, zipCode: "92100" },
    MONTREUIL: { latitude: 48.8635, longitude: 2.4484, zipCode: "93100" },
    SAINT_DENIS: { latitude: 48.9362, longitude: 2.3574, zipCode: "93200" },
    ARGENTEUIL: { latitude: 48.9476, longitude: 2.2469, zipCode: "95100" },
    NANTERRE: { latitude: 48.8927, longitude: 2.2179, zipCode: "92000" },
    VERSAILLES: { latitude: 48.8014, longitude: 2.1301, zipCode: "78000" },
    CRETEIL: { latitude: 48.7804, longitude: 2.4614, zipCode: "94000" },
    COLOMBES: { latitude: 48.9226, longitude: 2.2546, zipCode: "92700" },
    ASNIERES_SUR_SEINE: { latitude: 48.9088, longitude: 2.285, zipCode: "92600" },
    AULNAY_SOUS_BOIS: { latitude: 48.9333, longitude: 2.486, zipCode: "93600" },
    RUEIL_MALMAISON: { latitude: 48.8826, longitude: 2.1769, zipCode: "92500" },
    CHAMPIGNY_SUR_MARNE: { latitude: 48.8156, longitude: 2.5107, zipCode: "94500" },
    SAINT_MAUR_DES_FOSSES: { latitude: 48.7988, longitude: 2.4919, zipCode: "94100" },
    COURBEVOIE: { latitude: 48.8984, longitude: 2.2558, zipCode: "92400" },
    AUBERVILLIERS: { latitude: 48.9161, longitude: 2.3844, zipCode: "93300" },
    DRANCY: { latitude: 48.922, longitude: 2.4505, zipCode: "93700" },
    FONTENAY_SOUS_BOIS: { latitude: 48.8494, longitude: 2.4747, zipCode: "94120" },
    ISSY_LES_MOLINEAUX: { latitude: 48.8249, longitude: 2.2732, zipCode: "92130" },
    LEVALLOIS_PERRET: { latitude: 48.8931, longitude: 2.2884, zipCode: "92300" }
};

// Function to populate a dropdown menu with enum values
function populateDropdownMenu(enumObject, dropdownId) {
    const dropdown = document.getElementById(dropdownId);

    for (const city in enumObject) {
        if (enumObject.hasOwnProperty(city)) {
            const option = document.createElement("option");
            option.value = city;
            option.text = city.replace(/_/g, " ");
            dropdown.appendChild(option);
        }
    }
}

// Populate dropdown menus
populateDropdownMenu(City, "depart-ville");
populateDropdownMenu(City, "arrivee-ville");
