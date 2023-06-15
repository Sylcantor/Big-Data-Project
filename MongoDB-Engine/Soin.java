package ProjetBD;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Soin extends Entity {
    private final String collectionName = "Soin";
    private String indexName = "SoinName_Index";

    public Soin(){
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName(){
        return this.indexName;
    }

    public static void main(String args[]){
        Soin soin = new Soin();
        String colSoin = soin.getCollectionName();

        // Drop Collection
        soin.dropCollection(colSoin);

        // Drop an index of a collection
        soin.dropAIndexOfACollection(colSoin, soin.getIndexName());

        soin.createCollection(colSoin);
        soin.loadFromJsonArrayFile(soin.getCollectionName(),Util.filePathToImport,"soins.json");

        // Add 1 soin
        Document soin1 = new Document("_id", 6001)
                .append("SoinName", "Vaccination")
                .append("DateSoin", "2022-01-01")
                .append("AnimId", 6)
                .append("EmpId", 3);

        soin.insertOne(colSoin, soin1);

        // Add many soins
        List<Document> soinsToAdd = new ArrayList<>();

        Document soin2 = new Document("_id", 6002)
                .append("SoinName", "Vermifuge")
                .append("DateSoin", "2022-02-15")
                .append("AnimId", 7)
                .append("EmpId", 2);
        soinsToAdd.add(soin2);

        Document soin3 = new Document("_id", 6003)
                .append("SoinName", "Nettoyage des dents")
                .append("DateSoin", "2022-03-10")
                .append("AnimId", 3)
                .append("EmpId", 1);
        soinsToAdd.add(soin3);

        soin.insertMany(colSoin, soinsToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        soin.getElementById(colSoin, 1);

        // Get entry by query
        System.out.println("Get by query Test");
        soin.getElementByQuery(colSoin,
                new Document("EmpId", new Document("$gt", 1)),
                new Document("_id", 1).append("SoinName", 1),
                null);

        // Update soins
        System.out.println("Update Test");
        Document query = new Document("_id", 2);
        Document update = new Document("$set", new Document("SoinName", "Opération"));
        soin.updateMany(colSoin, query, update, new UpdateOptions());

        // Delete _id == 3
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$eq", 3));
        soin.deleteMany(colSoin, deleteQuery);

        // Join local and foreign collections
        System.out.println("Jointure Test");
        String foreignCollectionName = "Animal";
        String localColJoinFieldName = "AnimId";
        String foreignColJoinFieldName = "_id";
        Document filterFieldsOnLocalCollection = new Document();
        String namedJoinedElements = "Necessite";
        soin.joinLocalAndForeignCollections(colSoin, foreignCollectionName,
                localColJoinFieldName, foreignColJoinFieldName,
                filterFieldsOnLocalCollection, namedJoinedElements);

        // Group by fields
        System.out.println("Group Test");
        Document groupFields = new Document("_id", "$SoinName")
                .append("count", new Document("$sum", 1));
        soin.groupBy(colSoin, "$group", groupFields);

        // Create indexes
        List<String> fieldNames = Arrays.asList("SoinName");
        boolean isAscendingIndex = true;
        boolean indexUnique = false;
        soin.createIndexes(colSoin, soin.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        soin.getAllIndexesOfACollection(colSoin);

        soin.exportToJsonArrayFile(colSoin, Util.filePathToExport, "soins");

        System.out.println("\nEnd of test on Soin class.\n");
    }
}
