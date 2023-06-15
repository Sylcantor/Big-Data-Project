// a script to generate many animals objects
// and then write them to a json file

// template for an animal object
// {
//     "_id": 1,
//     "AnimName": "Lion",
//     "Espece": "Felidae",
//     "Sexe": "M",
//     "Diet": "Carnivore",
//     "DateNaiss": "2020-05-12",
//     "DateArriv": "2021-01-15",
//     "Poids": 180,
//     "Taille": 120,
//     "Enclos": "Enclos B"
// }



// We will use the API to generate animals
// https://api.api-ninjas.com/v1/animals

// template for an animal response
// [
//     {
//       "name": "Cheetah",
//       "taxonomy": {
//         "kingdom": "Animalia",
//         "phylum": "Chordata",
//         "class": "Mammalia",
//         "order": "Carnivora",
//         "family": "Felidae",
//         "genus": "Acinonyx",
//         "scientific_name": "Acinonyx jubatus"
//       },
//       "locations": [
//         "Africa",
//         "Asia",
//         "Eurasia"
//       ],
//       "characteristics": {
//         "prey": "Gazelle, Wildebeest, Hare",
//         "name_of_young": "Cub",
//         "group_behavior": "Solitary/Pairs",
//         "estimated_population_size": "8,500",
//         "biggest_threat": "Habitat loss",
//         "most_distinctive_feature": "Yellowish fur covered in small black spots",
//         "gestation_period": "90 days",
//         "habitat": "Open grassland",
//         "diet": "Carnivore",
//         "average_litter_size": "3",
//         "lifestyle": "Diurnal",
//         "common_name": "Cheetah",
//         "number_of_species": "5",
//         "location": "Asia and Africa",
//         "slogan": "The fastest land mammal in the world!",
//         "group": "Mammal",
//         "color": "BrownYellowBlackTan",
//         "skin_type": "Fur",
//         "top_speed": "70 mph",
//         "lifespan": "10 - 12 years",
//         "weight": "40kg - 65kg (88lbs - 140lbs)",
//         "height": "115cm - 136cm (45in - 53in)",
//         "age_of_sexual_maturity": "20 - 24 months",
//         "age_of_weaning": "3 months"
//       }
//     }
//   ]

// API KEY 0SrHDPPdhR23gmjf0q2wLg==clYbSChoO1iTItlr


const fs = require('fs');

const animalNames = [
    "Aardvark",
    "Albatross",
    "Ant",
    "Armadillo",
    "Baboon",
    "Badger",
    "Barracuda",
    "Beagle",
    "Bear",
    "Beaver",
    "Bison",
    "Blackbird",
    "Boa Constrictor",
    "Bulldog",
    "Butterfly",
    "Camel",
    "Capybara",
    "Cheetah",
    "Chimpanzee",
    "Chipmunk",
    "Cobra",
    "Cockatoo",
    "Cougar",
    "Cow",
    "Crab",
    "Crocodile",
    "Dalmatian",
    "Deer",
    "Dolphin",
    "Donkey",
    "Dragonfly",
    "Duck",
    "Eagle",
    "Elephant",
    "Falcon",
    "Flamingo",
    "Fox",
    "Frog",
    "Gazelle",
    "Giraffe",
    "Goat",
    "Goldfish",
    "Gorilla",
    "Grizzly Bear",
    "Hamster",
    "Hawk",
    "Hedgehog",
    "Hippopotamus",
    "Hummingbird",
    "Hyena",
    "Iguana",
    "Impala",
    "Jackal",
    "Jaguar",
    "Kangaroo",
    "Koala",
    "Komodo Dragon",
    "Lemur",
    "Leopard",
    "Lion",
    "Llama",
    "Lynx",
    "Macaw",
    "Manatee",
    "Meerkat",
    "Monkey",
    "Moose",
    "Nightingale",
    "Ocelot",
    "Octopus",
    "Orangutan",
    "Ostrich",
    "Otter",
    "Owl",
    "Panda",
    "Panther",
    "Parrot",
    "Peacock",
    "Penguin",
    "Pig",
    "Polar Bear",
    "Porcupine",
    "Puma",
    "Quail",
    "Rabbit",
    "Raccoon",
    "Rat",
    "Rattlesnake",
    "Rhinoceros",
    "Robin",
    "Salamander",
    "Seagull",
    "Shark",
    "Sheep",
    "Skunk",
    "Sloth",
    "Snake",
    "Squirrel",
    "Tiger",
    "Toucan",
    "Turtle",
    "Unicorn",
    "Vulture",
    "Walrus",
    "Warthog",
    "Whale",
    "Wolf",
    "Wolverine",
    "Wombat",
    "Woodpecker",
    "Yak",
    "Zebra",
    "Alpaca",
    "Anteater",
    "Arctic Fox",
    "Baboon",
    "Badger",
    "Bison",
    "Capybara",
    "Chameleon",
    "Cheetah",
    "Chinchilla",
    "Cockatoo",
    "Dingo",
    "Dolphin",
    "Duck-billed Platypus",
    "Echidna",
    "Elephant Seal",
    "Fennec Fox",
    "Flying Squirrel",
    "Gazelle",
    "Gibbon",
    "Giraffe",
    "Gorilla",
    "Hedgehog",
    "Hippopotamus",
    "Hummingbird",
    "Ibex",
    "Iguana",
    "Impala",
    "Jackal",
    "Jaguar",
    "Kangaroo",
    "Koala",
    "Lemur",
    "Lionfish",
    "Lynx",
    "Mandrill",
    "Meerkat",
    "Mongoose",
    "Narwhal",
    "Ocelot",
    "Okapi",
    "Orca",
    "Ostrich",
    "Pangolin",
    "Peacock",
    "Platypus",
    "Puffin",
    "Quokka",
    "Raccoon",
    "Red Panda",
    "Sloth",
    "Tapir",
    "Tarsier",
    "Toucan",
    "Uakari",
    "Vervet Monkey",
    "Wallaby",
    "Wombat",
    "X-ray Tetra",
    "Yak",
    "Yellow-bellied Marmot",
];




// get animals from the API
// with fetch


// test with random name

let animals = []
let done = 0;
let id = 1;

for (let j = 0; j < animalNames.length; j++) {
    const randomName = animalNames[j];
    fetch('https://api.api-ninjas.com/v1/animals?name=' + randomName, {
            method: 'GET',
            headers: {
                'X-Api-Key': '0SrHDPPdhR23gmjf0q2wLg==clYbSChoO1iTItlr'
            }
        })
        .then(response => response.json())
        .then(data => {

            for (let i = 0; i < data.length; i++) {

                let weight = 0;
                if (data[i].characteristics.weight === undefined) {
                    weight = Math.floor(Math.random() * 500) + 1;
                } else {
                    weight = parseFloat(data[i].characteristics.weight.split("kg")[0]);
                    if (isNaN(weight)) { weight = Math.floor(Math.random() * 500) + 1 };
                }
                let height = 0;
                if (data[i].characteristics.height === undefined) {
                    height = Math.floor(Math.random() * 200) + 1;
                } else {
                    height = parseFloat(data[i].characteristics.height.split("cm")[0]);
                    if (isNaN(height)) {
                        height = Math.floor(Math.random() * 200) + 1
                    };
                }



                const animal = generateAnimal(id++, data[i].name,
                    data[i].taxonomy.family,
                    data[i].characteristics.diet,
                    weight,
                    height);
                animals.push(animal);
            }
        })
        .catch(error => {
            console.error(error);
        });
    console.log("Progress: " + j + "/" + animalNames.length);
}

let interval = setInterval(() => {
    if (animals.length >= animalNames.length - 3) {
        done = 1;
    }
    clearInterval(interval);
}, 1000)


let interval2 = setInterval(() => {
    if (done === 1) {
        clearInterval(interval2);
        fs.appendFile('animals.json', "[", function(err) {
            for (let k = 0; k < animals.length; k++) {
                fs.appendFile('animals.json', JSON.stringify(animals[k]) + ",\n", function(err) {
                    if (err) throw err;
                    console.log('Saved!');
                });
            }
            fs.appendFile('animals.json', "]", function(err) {})
        })

    }
}, 3000)


// function to generate an animal object
function generateAnimal(id, name, species, diet, weight, height) {
    // generate a random date
    function randomDate(start, end) {
        return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    }
    // generate a random integer between min and max
    function randomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min)
    }

    function randomEnclos() {
        const enclos = ["Enclos A", "Enclos B", "Enclos C", "Enclos D", "Enclos E", "Enclos F", "Enclos G", "Enclos H", "Enclos I", "Enclos J"]
        return enclos[randomInt(0, enclos.length - 1)]
    }

    function randSexe() {
        if (Math.random() < 0.5) {
            return "M"
        } else {
            return "F"
        }
    }

    // generate a random animal
    var animal = {
            "_id": id,
            "AnimName": name,
            "Espece": species,
            "Sexe": randSexe(),
            "Diet": diet,
            "DateNaiss": randomDate(new Date(2010, 0, 1), new Date()).toISOString().slice(0, 10),
            "DateArriv": randomDate(new Date(2010, 0, 1), new Date()).toISOString().slice(0, 10),
            "Poids": weight,
            "Taille": height,
            "Enclos": randomEnclos()
        }
        //console.log(animal);
    return animal
}

console.log("Generating animals...")