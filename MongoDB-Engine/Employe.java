package ProjetBD;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employe extends Entity {
    private final String collectionName = "Employe";
    private String indexName = "EmpNo_Index";

    public Employe(){
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName(){
        return this.indexName;
    }

    public static void main(String args[]){
        Employe employes = new Employe();
        String colEmploye = employes.getCollectionName();

        // Drop Collection
        employes.dropCollection(colEmploye);

        // Drop an index of a collection
        employes.dropAIndexOfACollection(colEmploye, employes.getIndexName());

        employes.createCollection(colEmploye);
        employes.loadFromJsonArrayFile(employes.getCollectionName(),Util.filePathToImport,"employes.json");

        // Add 1 employe
        Document employe1 = new Document("_id", 4003)
                .append("empPrenom", "John")
                .append("empNom", "Doe")
                .append("empCV", "CV1.pdf")
                .append("empJob", "Jardinier")
                .append("empSal", 2000)
                .append("empDateNaiss", "1990-01-01")
                .append("empDateEmb", "2020-01-01")
                .append("dept", 1);

        employes.insertOne(colEmploye, employe1);

        // Add many employes
        List<Document> employesToAdd = new ArrayList<>();

        Document employe2 = new Document("_id", 4004)
                .append("empPrenom", "Jane")
                .append("empNom", "Smith")
                .append("empCV", "CV2.pdf")
                .append("empJob", "Soigneur")
                .append("empSal", 4000)
                .append("empDateNaiss", "1995-05-10")
                .append("empDateEmb", "2021-03-15")
                .append("dept", 2);
        employesToAdd.add(employe2);

        Document employe3 = new Document("_id", 4005)
                .append("empPrenom", "Michael")
                .append("empNom", "Johnson")
                .append("empCV", "CV3.pdf")
                .append("empJob", "Manager")
                .append("empSal", 3500)
                .append("empDateNaiss", "1988-07-20")
                .append("empDateEmb", "2019-08-01")
                .append("dept", 3);
        employesToAdd.add(employe3);

        employes.insertMany(colEmploye, employesToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        employes.getElementById(colEmploye, 1);

        // Get entry by query
        System.out.println("Get by query Test");
        employes.getElementByQuery(colEmploye,
                new Document("empJob", "Manager"),
                new Document("_id", 1).append("empNom", 1),
                null);

        // Update employe
        System.out.println("Update Test");
        Document query = new Document("_id", 2);
        Document update = new Document("$set", new Document("empSal", 4500));
        employes.updateMany(colEmploye, query, update, new UpdateOptions());

        // Delete _id == 7
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$eq", 7));
        employes.deleteMany(colEmploye, deleteQuery);

        // Join local and foreign collections
        System.out.println("Jointure Test");
        String foreignCollectionName = "Departement";
        String localColJoinFieldName = "dept";
        String foreignColJoinFieldName = "_id";
        Document filterFieldsOnLocalCollection = new Document();
        String namedJoinedElements = "Associé à";
        employes.joinLocalAndForeignCollections(colEmploye, foreignCollectionName,
                localColJoinFieldName, foreignColJoinFieldName,
                filterFieldsOnLocalCollection, namedJoinedElements);

        // Group by fields
        System.out.println("Group Test");
        Document groupFields = new Document("_id", "$empJob")
                .append("count", new Document("$sum", 1));
        employes.groupBy(colEmploye, "$group", groupFields);

        // Create indexes
        List<String> fieldNames = Arrays.asList("empPrenom", "empNom");
        boolean isAscendingIndex = true;
        boolean indexUnique = false;
        employes.createIndexes(colEmploye, employes.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        employes.getAllIndexesOfACollection(colEmploye);

        employes.exportToJsonArrayFile(colEmploye, Util.filePathToExport, "employe");

        System.out.println("\nEnd of test on Employe class.\n");
    }
}
