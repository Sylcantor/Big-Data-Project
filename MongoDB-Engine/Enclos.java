package ProjetBD;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enclos extends Entity {
    private final String collectionName = "Enclos";
    private String indexName = "EncName_Index";

    public Enclos(){
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName(){
        return this.indexName;
    }

    public static void main(String args[]){
        Enclos enclos = new Enclos();
        String colEnclos = enclos.getCollectionName();

        // Drop Collection
        enclos.dropCollection(colEnclos);

        // Drop an index of a collection
        enclos.dropAIndexOfACollection(colEnclos, enclos.getIndexName());

        enclos.createCollection(colEnclos);
        enclos.loadFromJsonArrayFile(enclos.getCollectionName(),Util.filePathToImport,"enclos.json");

        // Add 1 enclos
        Document enclos1 = new Document("_id", 11)
                .append("EncName", "Enclos K")
                .append("Capacite", 10)
                .append("Taille", 100);

        enclos.insertOne(colEnclos, enclos1);

        // Add many enclos
        List<Document> enclosToAdd = new ArrayList<>();

        Document enclos2 = new Document("_id", 12)
                .append("EncName", "Enclos L")
                .append("Capacite", 5)
                .append("Taille", 50);
        enclosToAdd.add(enclos2);

        Document enclos3 = new Document("_id", 13)
                .append("EncName", "Enclos M")
                .append("Capacite", 8)
                .append("Taille", 80);
        enclosToAdd.add(enclos3);

        enclos.insertMany(colEnclos, enclosToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        enclos.getElementById(colEnclos, 1);

        // Get entry by query
        System.out.println("Get by query Test");
        enclos.getElementByQuery(colEnclos,
                new Document("Capacite", new Document("$gt", 5)),
                new Document("_id", 1).append("EncName", 1),
                null);

        // Update enclos
        System.out.println("Update Test");
        Document query = new Document("_id", 2);
        Document update = new Document("$set", new Document("Capacite", 6));
        enclos.updateMany(colEnclos, query, update, new UpdateOptions());

        // Delete _id == 5
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$eq", 5));
        enclos.deleteMany(colEnclos, deleteQuery);

        // Join local and foreign collections
        System.out.println("Jointure Test");
        String foreignCollectionName = "Animal";
        String localColJoinFieldName = "EncName";
        String foreignColJoinFieldName = "Enclos";
        Document filterFieldsOnLocalCollection = new Document();
        String namedJoinedElements = "Resident";
        enclos.joinLocalAndForeignCollections(colEnclos, foreignCollectionName,
                localColJoinFieldName, foreignColJoinFieldName,
                filterFieldsOnLocalCollection, namedJoinedElements);

        // Group by fields
        System.out.println("Group Test");
        Document groupFields = new Document("_id", "$Taille")
                .append("count", new Document("$sum", 1));
        enclos.groupBy(colEnclos, "$group", groupFields);

        // Create indexes
        List<String> fieldNames = Arrays.asList("EncName");
        boolean isAscendingIndex = true;
        boolean indexUnique = true;
        enclos.createIndexes(colEnclos, enclos.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        enclos.getAllIndexesOfACollection(colEnclos);

        enclos.exportToJsonArrayFile(colEnclos, Util.filePathToExport, "enclos");

        System.out.println("\nEnd of test on Enclos class.\n");
    }
}
