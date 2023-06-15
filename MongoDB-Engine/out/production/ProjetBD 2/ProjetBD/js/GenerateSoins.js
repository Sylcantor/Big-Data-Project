// generate soins object

//soins template
// {
//     "_id": 1,
//     "SoinName": "Vaccination",
//     "DateSoin": "2022-01-01",
//     "AnimId": 1,
//     "EmpId": 1
//   }




const fs = require('fs');

const soins = [];
let id = 1;

const soinsZoo = [
    "Vaccination",
    "Vermifuge",
    "Administration de médicaments",
    "Nettoyage des enclos",
    "Alimentation des animaux",
    "Toilettage",
    "Exercice et activités physiques",
    "Prise de température",
    "Contrôle des parasites",
    "Bain",
    "Soins de plaies",
    "Suivi de l'état de santé",
    "Radiographie",
    "Analyses de laboratoire",
    "Observation comportementale",
    "Préparation des repas",
    "Enrichissement environnemental",
    "Réhabilitation",
    "Éducation des visiteurs",
    "Captures et transferts d'animaux",
];

for (let i = 0; i < 6000; i++) {
    soins.push(generateSoins());
    console.log("Progress: " + i + "/6000");
}


fs.appendFile('soins.json', "[" + "\n", function(err) {
    if (err) throw err;
    for (let i = 0; i < soins.length; i++) {
        fs.appendFile('soins.json', JSON.stringify(soins[i]) + ",\n", function(err) {
            if (err) throw err;
            console.log('Saved!');
        });
    }
    fs.appendFile('soins.json', "]" + "\n", function(err) {
        if (err) throw err;
        console.log('Done!');
    })
});






function generateSoins(SoinName, DateSoin, AnimId, EmpId) {

    function randomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min)
    }

    function randomDate(start, end) {
        return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    }
    return {
        _id: id++,
        SoinName: soinsZoo[Math.floor(Math.random() * soinsZoo.length)],
        DateSoin: randomDate(new Date(2010, 0, 1), new Date(2023, 0, 1)).toISOString().slice(0, 10),
        AnimId: randomInt(1, 720),
        EmpId: randomInt(1, 3998)
    }
}