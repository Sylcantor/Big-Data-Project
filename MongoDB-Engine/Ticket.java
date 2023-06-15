package ProjetBD;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Ticket extends Entity {
    private final String collectionName = "Ticket";
    private String indexName = "DateAchat_Index";

    public Ticket() {
        super();
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public static void main(String args[]) {
        Ticket tickets = new Ticket();
        String colTicket = tickets.getCollectionName();

        // Drop Collection
        tickets.dropCollection(colTicket);

        // Drop an index of a collection
        tickets.dropAIndexOfACollection(colTicket, tickets.getIndexName());

        tickets.createCollection(colTicket);
        tickets.loadFromJsonArrayFile(tickets.getCollectionName(), Util.filePathToImport, "tickets.json");

        // Add 1 ticket
        Document ticket1 = new Document("_id", 100001)
                .append("DateAchat", new Date())
                .append("Categorie", "Enfant")
                .append("Tarif", 10.0)
                .append("appartenance", 5);

        tickets.insertOne(colTicket, ticket1);

        // Add many tickets
        List<Document> ticketsToAdd = new ArrayList<>();

        Document ticket2 = new Document("_id", 100002)
                .append("DateAchat", new Date())
                .append("Categorie", "Adulte")
                .append("Tarif", 20.0)
                .append("appartenance", 7);
        ticketsToAdd.add(ticket2);

        Document ticket3 = new Document("_id", 100003)
                .append("DateAchat", new Date())
                .append("Categorie", "Adulte")
                .append("Tarif", 150.0)
                .append("appartenance", 7);
        ticketsToAdd.add(ticket3);

        tickets.insertMany(colTicket, ticketsToAdd);

        // Get entry by id
        System.out.println("Get by elem id Test");
        tickets.getElementById(colTicket, 1);

        // Get entry by query
        System.out.println("Get by query Test");
        tickets.getElementByQuery(colTicket,
                new Document("Categorie", "Adulte"),
                new Document("_id", 1).append("Categorie", 1),
                new Document("DateAchat", -1));

        // Update tickets
        System.out.println("Update Test");
        Document query = new Document("_id", 7);
        Document update = new Document("$set", new Document("Tarif", 20.0));
        tickets.updateMany(colTicket, query, update, new UpdateOptions());

        // Delete _id < 3
        System.out.println("Delete Test");
        Document deleteQuery = new Document("_id", new Document("$lt", 4));
        tickets.deleteMany(colTicket, deleteQuery);

        // Group by fields
        System.out.println("Group Test");
        Document groupFields = new Document("_id", "$Categorie")
                .append("count", new Document("$sum", 1));
        tickets.groupBy(colTicket, "$group", groupFields);

        // Create indexes
        List<String> fieldNames = Arrays.asList("DateAchat");
        boolean isAscendingIndex = true;
        boolean indexUnique = false;
        tickets.createIndexes(colTicket, tickets.getIndexName(), fieldNames, isAscendingIndex, indexUnique);

        // Get all indexes of a collection
        tickets.getAllIndexesOfACollection(colTicket);

        tickets.exportToJsonArrayFile(colTicket, Util.filePathToExport, "tickets");

        System.out.println("\nEnd of test on Ticket class.\n");
    }
}
