package ProjetBD;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Departement extends Entity {
    private final String collectionName = "Departement";
    private String indexName = "Dname_Index";

    public Departement(){
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName(){
        return this.indexName;
    }

    public static void main(String args[]){
        Departement departement = new Departement();
        String colDepartement = departement.getCollectionName();

        // Drop Collection
        departement.dropCollection(colDepartement);

        // Drop an index of a collection
        departement.dropAIndexOfACollection(colDepartement, departement.getIndexName());

        departement.createCollection(colDepartement);
        departement.loadFromJsonArrayFile(departement.getCollectionName(),Util.filePathToImport,"departements.json");

        // Add 1 departement
        Document departement1 = new Document("_id", 2)
                .append("Dname", "Département des oiseau");

        departement.insertOne(colDepartement, departement1);

        // Add many departements
        List<Document> departementsToAdd = new ArrayList<>();

        Document departement2 = new Document("_id", 3)
                .append("Dname", "Département des reptiles");
        departementsToAdd.add(departement2);

        Document departement3 = new Document("_id", 4)
                .append("Dname", "Département des poissons");
        departementsToAdd.add(departement3);

        departement.insertMany(colDepartement, departementsToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        departement.getElementById(colDepartement, 1);

        // Get entry by query
        System.out.println("Get by query Test");
        departement.getElementByQuery(colDepartement,
                new Document("Dname", "Département des reptiles"),
                new Document("_id", 1).append("Dname", 1),
                null);

        // Update departement
        System.out.println("Update Test");
        Document query = new Document("_id", 2);
        Document update = new Document("$set", new Document("Dname", "Département des oiseaux"));
        departement.updateMany(colDepartement, query, update, new UpdateOptions());

        // Delete _id == 5
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$eq", 5));
        departement.deleteMany(colDepartement, deleteQuery);


        // Create indexes
        List<String> fieldNames = Arrays.asList("Dname");
        boolean isAscendingIndex = true;
        boolean indexUnique = true;
        departement.createIndexes(colDepartement, departement.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        departement.getAllIndexesOfACollection(colDepartement);

        departement.exportToJsonArrayFile(colDepartement, Util.filePathToExport, "departements");

        System.out.println("\nEnd of test on Departement class.\n");
    }
}
