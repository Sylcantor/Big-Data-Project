// Generate ticket object

// ticket template
// {
//     "_id": 1,
//     "dateAchat": "2023-06-10T09:00:00Z",
//     "categorie": "Enfant",
//     "tarif": 10.0,
//     "appartenance": 1
//   }

const fs = require('fs');

const tickets = [];
let id = 1;

for (let i = 0; i < 100000; i++) {
    tickets.push(generateTicket());
    console.log("Progress: " + i + "/100000");
}
fs.appendFile('tickets.json', JSON.stringify(tickets, undefined, 2) + "\n", function(err) {
    if (err) throw err;
    console.log('Saved!');
});



function generateTicket() {
    function getRandomDateTime() {
        const year = Math.floor(Math.random() * 10) + 2019; // Random year between 2019 and 2028
        const month = Math.floor(Math.random() * 12); // Random month between 0 and 11
        const day = Math.floor(Math.random() * 28) + 1; // Random day between 1 and 28
        const hours = Math.floor(Math.random() * 24); // Random hours between 0 and 23
        const minutes = Math.floor(Math.random() * 60); // Random minutes between 0 and 59
        const seconds = Math.floor(Math.random() * 60); // Random seconds between 0 and 59

        const date = new Date(Date.UTC(year, month, day, hours, minutes, seconds));
        return date.toISOString();
    }
    return {
        "_id": id,
        "dateAchat": getRandomDateTime(),
        "categorie": ["Enfant", "Adulte", "Senior"][Math.floor(Math.random() * 3)],
        "tarif": [10, 15, 20][Math.floor(Math.random() * 3)],
        "appartenance": id++
    }
}