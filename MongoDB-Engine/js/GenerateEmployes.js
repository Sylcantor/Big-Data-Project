// generate Emloyees object

// employee template
// {
//     "_id": 10,
//     "empPrenom": "Mia",
//     "empNom": "Clark",
//     "empCV": "lien_vers_le_cv_10",
//     "empJob": "Technicien",
//     "empSal": 2200,
//     "empDateNaiss": "1983-12-14",
//     "empDateEmb": "2015-04-28",
//     "dept": 2
//   }


const fs = require('fs');

const zooJobs = [
    "Soigneur animalier",
    "Vétérinaire",
    "Dresseur d'animaux",
    "Animateur pédagogique",
    "Biologiste animalier",
    "Conservateur de zoo",
    "Directeur de zoo",
    "Assistant soigneur animalier",
    "Nutritionniste animalier",
    "Concepteur d'enclos de zoo",
    "Conservateur de la faune sauvage",
    "Technicien vétérinaire de zoo",
    "Interprète d'enclos de zoo",
    "Chercheur en zoo",
    "Administrateur de zoo",
];


const names = [
    "Gayla",
    "Christen",
    "Mabelle",
    "Alanie",
    "Salvaine",
    "Blandy",
    "Lillaudine",
    "Érica",
    "Raphanie",
    "Domin",
    "Corene",
    "Violandrick",
    "Harita",
    "Salis",
    "Walterik",
    "Teric",
    "Eleann",
    "Jasmin",
    "Myril",
    "Rosalla",
    "Madette",
    "Frédérice",
    "Deane",
    "Juanis",
    "Carma",
    "Maryl",
    "Xavieven",
    "Ronnica",
    "Bessica",
    "Gerto",
    "Ghislain",
    "Zacharlie",
    "Elson",
    "Rubenoît",
    "Sherberta",
    "Dores",
    "Elvian",
    "Tylerome",
    "Thond",
    "Cecille",
    "Jorgette",
    "Howardo",
    "Nando",
    "Pamelore",
    "Danise",
    "Duard",
    "Jorget",
    "Estie",
    "Amelindsey",
    "Edwight",
    "Alandy",
    "Kathley",
    "Benja",
    "Karie",
    "Belle",
    "Quelle",
    "Charlest",
    "Carlie",
    "Nathryn",
    "Gilbertrude",
    "Didie",
    "Michelly",
    "Morge",
    "Corian",
    "Simonderi",
    "Zachel",
    "Jeffernice",
    "Faitha",
    "Thelix",
    "Annon",
    "Simonelsea",
    "Lette",
    "Darlena",
    "Sévelma",
    "Tashleen",
    "Lucile",
    "Rebecky",
    "Kathanie",
    "Gabrigitte",
    "Mariam",
    "Travid",
    "Floraldine",
    "Micholly",
    "Thell",
    "Lissa",
    "Amando",
    "Winique",
    "Ramonathy",
    "Frandy",
    "Estherita",
    "Danne",
    "Katha",
    "Lydian",
    "Monde",
    "Claud",
    "Matthie",
    "Marcederien",
    "Violas",
    "Kayle",
    "Antal",
    "Sandrea",
    "Verik",
    "Brance",
    "Juanicole",
    "Mathanny",
    "Michelly",
    "Tyron",
    "Nette",
    "Yannie",
    "Alexandy",
    "Alennie",
    "Thilde",
    "Alexanna",
    "Franclara",
    "Patrichèle",
    "Cristephia",
    "Prilyn",
    "Katheryl",
    "Mathley",
    "Alfrey",
    "Roseph",
    "Verika",
    "Vickaël",
    "Rolyn",
    "Colana",
    "Pearlos",
    "Roche",
    "Jille",
    "Crain",
    "Franda",
    "Olivierri",
    "Amandon",
    "Yanna",
    "Claine",
    "Magalin",
    "Hanny",
    "Mathella",
    "Vivia",
    "Kelle",
    "Cassa",
    "Carma",
    "Miguelyne",
    "Salie",
    "Stalie",
    "Eddier",
    "Mather",
    "Bryadrina",
    "Thond",
    "Davier",
    "Milie",
    "Katrick",
    "Laudian",
    "Adrick",
    "Juliettie",
    "Olivie",
    "Nathia",
    "Randine",
    "Rentin",
    "Lette",
    "Heritte",
    "Gabrica",
    "Jilla",
    "Dalupe",
    "Darlestin",
    "Katheri",
    "Julion",
    "Morgett",
    "Angelicia",
    "Marthane",
    "Frandine",
    "Danita",
    "Chard",
    "Hillie",
    "Waynet",
    "Gracey",
    "Denny",
    "Matherbert",
    "Jeane",
    "Edwayne",
    "Sanda",
    "Mitchel",
    "Karena",
    "Jaret",
    "Violetty",
    "Walterry",
    "Phill",
    "Julinton",
    "Darlos",
    "Ivane",
    "Glene",
    "Allardo",
    "Stepher",
    "Michenrien",
    "Cathley",
    "Meline",
    "Mamin",
    "Ginge",
    "Morgar",
    "Jeanon",
    "Katheodora",
    "Ginia",
    "Oline",
    "Velmered",
    "Fernard",
    "Jorine",
    "Duardon",
    "Ameline",
    "Marthur",
    "Sadian",
    "Arnon",
    "Andon",
    "Fabridget",
    "Berto",
    "Karlene",
    "Simonder",
    "Jamiltony",
    "Miguette",
    "Maryadric",
    "Bertrude",
    "Timond",
    "Gilla",
    "Barbarry",
    "Darry",
    "Phine",
    "Hilian",
    "Nandrea",
    "Ollia",
    "Pearla",
    "Carlotte",
    "Grette",
    "Natheri",
    "Jonard",
    "Kristelly",
    "Kariet",
    "Mohamelice",
    "Edgary",
    "Bethel",
    "Helmercelle",
];
let id = 0;
let emps = [];
// 

for (let i = 0; i < 4000; i++) {
    emps.push(generateEmployee());
}


fs.appendFile('employes.json', "[", function(err) {
    for (let k = 0; k < emps.length; k++) {
        fs.appendFile('employes.json', JSON.stringify(emps[k]) + ",\n", function(err) {
            if (err) throw err;
            console.log('Saved!');
        });
        console.log("Progress : " + k + " / " + emps.length);
    }
    fs.appendFile('employes.json', "]", function(err) {})
})





function generateEmployee() {
    function randomDate(start, end) {
        return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    }

    function randomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    const employee = {
        _id: id++,
        empPrenom: names[Math.floor(Math.random() * names.length)],
        empNom: names[Math.floor(Math.random() * names.length)],
        empCV: "./cvs/CV" + id + ".pdf",
        empJob: zooJobs[Math.floor(Math.random() * zooJobs.length)],
        empSal: randomInt(1000, 3500),
        empDateNaiss: randomDate(new Date(1960, 0, 1), new Date(2000, 0, 1)),
        empDateEmb: randomDate(new Date(2010, 0, 1), new Date(2020, 0, 1)),
        dept: randomInt(1, 5)
    };
    return employee;
}