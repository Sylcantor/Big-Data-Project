package ProjetBD;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal extends Entity {
    private final String collectionName = "Animal";
    private String indexName = "AnimName_Index";
    public Animal(){
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName(){
        return this.indexName;
    }

    public static void main(String args[]){
        Animal animals = new Animal();
        String colAnimal = animals.getCollectionName();

        // Drop Collection
        animals.dropCollection(colAnimal);

        // Drop an index of a collection
        animals.dropAIndexOfACollection(colAnimal, animals.getIndexName());

        animals.createCollection(colAnimal);
        animals.loadFromJsonArrayFile(animals.getCollectionName(),Util.filePathToImport,"animals.json");

        // Add 1 animal
        Document perroquet = new Document("_id", 724)
                .append("AnimName", "Perroquet")
                .append("Espece", "Psittacidae")
                .append("Sexe", "F")
                .append("Diet", "Omnivore")
                .append("DateNaiss", "2020-12-01")
                .append("DateArriv", "2021-06-20")
                .append("Poids", 0.5)
                .append("Taille", 30)
                .append("Enclos", "Enclos C");

        animals.insertOne(colAnimal, perroquet);

        // Add many animals
        List<Document> animalsToAdd = new ArrayList<>();

        Document animal1 = new Document("_id", 725)
                .append("AnimName", "Panthère des neiges")
                .append("Espece", "Panthera")
                .append("Sexe", "F")
                .append("Diet", "Carnivore")
                .append("DateNaiss", "2018-06-08")
                .append("DateArriv", "2019-01-10")
                .append("Poids", 50)
                .append("Taille", 70)
                .append("Enclos", "Enclos B");
        animalsToAdd.add(animal1);

        Document animal2 = new Document("_id", 726)
                .append("AnimName", "Kangourou")
                .append("Espece", "Macropus")
                .append("Sexe", "M")
                .append("Diet", "Herbivore")
                .append("DateNaiss", "2017-04-10")
                .append("DateArriv", "2017-11-15")
                .append("Poids", 80)
                .append("Taille", 150)
                .append("Enclos", "Enclos C");
        animalsToAdd.add(animal2);

        animals.insertMany(colAnimal, animalsToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        animals.getElementById(colAnimal, 7);

        // Get entry by query
        System.out.println("Get by query Test");
        animals.getElementByQuery(colAnimal,
                new Document("Sexe", "F"),
                new Document("_id", 1).append("AnimName", 1),
                new Document("DateNaiss", -1));

        // Update animals
        System.out.println("Update Test");
        Document query = new Document("_id", 4);
        Document update = new Document("$set", new Document("Sexe", "F"));
        animals.updateMany(colAnimal, query, update, new UpdateOptions());

        // Delete _id >= 9
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$gte", 9));
        animals.deleteMany(colAnimal, deleteQuery);

        // Join local and foreign collections
        /*String foreignCollectionName = "Zoo";
        String localColJoinFieldName = "ZooId";
        String foreignColJoinFieldName = "_id";
        Document filterFieldsOnLocalCollection = new Document();
        String namedJoinedElements = "joinedZoo";
        animals.joinLocalAndForeignCollections(colAnimal, foreignCollectionName,
                localColJoinFieldName, foreignColJoinFieldName,
                filterFieldsOnLocalCollection, namedJoinedElements);
        */

        // Group by fields
        System.out.println("Group Test");
        Document groupFields = new Document("_id", "$Sexe")
                .append("count", new Document("$sum", 1));
        animals.groupBy(colAnimal, "$group", groupFields);

        // Create indexes
        List<String> fieldNames = Arrays.asList("AnimName");
        boolean isAscendingIndex = true;
        boolean indexUnique = false;
        animals.createIndexes(colAnimal, animals.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        animals.getAllIndexesOfACollection(colAnimal);

        animals.exportToJsonArrayFile(colAnimal, Util.filePathToExport, "animals");

        System.out.println("\nEnd of test on Animal class.\n");
    }
}
