package ProjetBD;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Visiteur extends Entity {
    private final String collectionName = "Visiteur";
    private String indexName = "VNom_Index";

    public Visiteur() {
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public static void main(String args[]) {
        Visiteur visiteur = new Visiteur();
        String colVisiteur = visiteur.getCollectionName();

        // Drop Collection
        visiteur.dropCollection(colVisiteur);

        // Drop an index of a collection
        visiteur.dropAIndexOfACollection(colVisiteur, visiteur.getIndexName());

        visiteur.createCollection(colVisiteur);
        visiteur.loadFromJsonArrayFile(visiteur.getCollectionName(), Util.filePathToImport, "visiteurs.json");

        // Add 1 visiteur
        Document visiteur1 = new Document("_id", 100001)
                .append("VNom", "Dupont")
                .append("VPrenom", "Jean")
                .append("VVille", "Paris")
                .append("VAge", 10)
                .append("VDateVisite", "2023-06-01");

        visiteur.insertOne(colVisiteur, visiteur1);

        // Add many visiteurs
        List<Document> visiteursToAdd = new ArrayList<>();

        Document visiteur2 = new Document("_id", 100002)
                .append("VNom", "Martin")
                .append("VPrenom", "Sophie")
                .append("VVille", "Lyon")
                .append("VAge", 25)
                .append("VDateVisite", "2023-05-15");
        visiteursToAdd.add(visiteur2);

        Document visiteur3 = new Document("_id", 100003)
                .append("VNom", "Leclerc")
                .append("VPrenom", "Julie")
                .append("VVille", "Marseille")
                .append("VAge", 19)
                .append("VDateVisite", "2023-06-05");
        visiteursToAdd.add(visiteur3);

        visiteur.insertMany(colVisiteur, visiteursToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        visiteur.getElementById(colVisiteur, 1);

        // Get entry by query
        System.out.println("Get by query Test");
        visiteur.getElementByQuery(colVisiteur,
                new Document("VVille", "Paris"),
                new Document("_id", 1).append("VNom", 1),
                null);

        // Update visiteurs
        System.out.println("Update Test");
        Document query = new Document("_id", 2);
        Document update = new Document("$set", new Document("VAge", 26));
        visiteur.updateMany(colVisiteur, query, update, new UpdateOptions());

        // Delete _id == 3
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$eq", 3));
        visiteur.deleteMany(colVisiteur, deleteQuery);

        // Join local and foreign collections
        String foreignCollectionName = "Ticket";
        String localColJoinFieldName = "_id";
        String foreignColJoinFieldName = "appartenance";
        Document filterFieldsOnLocalCollection = new Document();
        String namedJoinedElements = "possède";
        visiteur.joinLocalAndForeignCollections(colVisiteur, foreignCollectionName,
                localColJoinFieldName, foreignColJoinFieldName,
                filterFieldsOnLocalCollection, namedJoinedElements);

        // Group by fields
        System.out.println("Group Test");
        Document groupFields = new Document("_id", "$VAge")
                .append("count", new Document("$sum", 1));
        visiteur.groupBy(colVisiteur, "$group", groupFields);

        // Create indexes
        List<String> fieldNames = Arrays.asList("VNom");
        boolean isAscendingIndex = true;
        boolean indexUnique = false;
        visiteur.createIndexes(colVisiteur, visiteur.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        visiteur.getAllIndexesOfACollection(colVisiteur);

        visiteur.exportToJsonArrayFile(colVisiteur, Util.filePathToExport, "visiteurs");

        System.out.println("\nEnd of test on Visiteur class.\n");
    }
}
