package ProjetBD;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;

import java.io.*;
import java.util.*;

public abstract class Entity {
    private final MongoDatabase database;
    public Entity(){
        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient(Util.hostName, Util.port);

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential(Util.userName, Util.dbName, Util.passWord.toCharArray());
        System.out.println("Connected to the database successfully");
        System.out.println("Credentials ::"+ credential);
        // Accessing the database
        database = mongoClient.getDatabase(Util.dbName);
    }

    public void createCollection(String nomCollection){
        //Creating a currentCollection
        database.createCollection(nomCollection);
        System.out.println("Collection" + nomCollection + " created successfully");
    }

    public void dropCollection(String nomCollection){
        //Drop a currentCollection
        MongoCollection<Document> currentCollection = null;
        currentCollection = database.getCollection(nomCollection);
        System.out.println("!!!! Collection " + nomCollection + ": " + currentCollection);

        if (currentCollection == null)
            System.out.println("Collection inexistante");
        else {
            currentCollection.drop();
            System.out.println("Collection " + currentCollection + " dropped successfully !!!");
        }
    }

    public void insertOne(String nomCollection, Document doc){
        MongoCollection<Document> currentCol = database.getCollection(nomCollection);
        currentCol.insertOne(doc);
        System.out.println("Document inserted successfully");
    }

    public void insertMany(String nomCollection, List<Document> docs){
        MongoCollection<Document> currentCol = database.getCollection(nomCollection);
        currentCol.insertMany(docs);
        System.out.println("Many Documents inserted successfully");
    }

    public void getElementById(String nomCollection, Integer colId){
        MongoCollection<Document> currentCol = database.getCollection(nomCollection);
        Document whereQuery = new Document();
        whereQuery.put("_id", colId);
        Document res = currentCol.find(whereQuery).first();
        System.out.println("Response of " + nomCollection + " matching with id " + colId + ": " + res);
    }

    public void getElementByQuery(String nomCollection, Document whereQuery,
                                  Document projectionFields, Document sortFields){
        MongoCollection<Document> currentCol = database.getCollection(nomCollection);
        FindIterable<Document> colElements = currentCol.find(whereQuery).sort(sortFields).projection(projectionFields);

        // Getting the iterator
        Iterator<Document> it = colElements.iterator();
        Util.displayIterator(it, "Get element from " + nomCollection + " by query.");
    }

    public void updateMany(String nomCollection, Document whereQuery,
                           Document updateExpressions, UpdateOptions updateOptions){
        MongoCollection<Document> currentCol = database.getCollection(nomCollection);
        UpdateResult updateResult = currentCol.updateMany(whereQuery, updateExpressions, updateOptions);

        System.out.println("\nResult update : "
                +"getUpdate id: " + updateResult
                +"getMatchedCount : " + updateResult.getMatchedCount()
                +"getModifiedCount : " + updateResult.getModifiedCount()
        );
    }

    public void deleteMany(String nomCollection, Document filters){
        MongoCollection<Document> currentCol = database.getCollection(nomCollection);
        DeleteResult result = currentCol.deleteMany(filters);
        System.out.println(result);
    }

    public void joinLocalAndForeignCollections(String localCollectionName, String foreignCollectionName,
                                               String localColJoinFieldName, String foreignColJoinFieldName,
                                               Document filterFieldsOnLocalCollection, String namedJoinedElements){
        MongoCollection<Document> localCol = database.getCollection(localCollectionName);

        AggregateIterable<Document> joinLocalAndForeign = localCol.aggregate (
                Arrays.asList(
                        Aggregates.match(filterFieldsOnLocalCollection),
                        Aggregates.lookup(foreignCollectionName, localColJoinFieldName,
                                foreignColJoinFieldName, namedJoinedElements)
                )
        );

        System.out.println("Result of joining local collection " + localCollectionName
                + " and foreign one " + foreignCollectionName + ": ");
        for (Document d1 : joinLocalAndForeign)
            System.out.println(d1);
    }

    public void groupBy(String collectionName, String groupOperator, Document groupFields) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document groupStage = new Document(groupOperator, groupFields);

        List<Document> pipeline = Arrays.asList(
                groupStage
        );

        AggregateIterable<Document> result = collection.aggregate(pipeline);

        for (Document document : result) {
            System.out.println(document);
        }
    }

    public void createIndexes(String localCollectionName, String indexName,
                                     List<String> fieldNames, boolean isAscendingIndex, boolean indexUnique){
        String returnedIndexName;
        MongoCollection<Document> currentCollection = database.getCollection(localCollectionName);
        IndexOptions indexOptions = new IndexOptions() ;

        if (indexName!= null)
            indexOptions.unique(indexUnique).name(indexName);
        else
            indexOptions.unique(indexUnique);

        if (isAscendingIndex)
            returnedIndexName= currentCollection.createIndex(Indexes.ascending(fieldNames), indexOptions);
        else
            returnedIndexName= currentCollection.createIndex(Indexes.descending(fieldNames), indexOptions);
        System.out.println("\n\nNom de l'index cree : " + returnedIndexName);
    }

    public void getAllIndexesOfACollection(String localCollectionName){
        MongoCollection<Document> currentCol = database.getCollection(localCollectionName);
        ListIndexesIterable<Document> liIndexIter = currentCol.listIndexes();
        Iterator<Document> it = liIndexIter.iterator();// Getting the iterator
        Util.displayIterator(it, "Dans getAllIndexesOfACollection:  Liste des indexes de la collection "
                + localCollectionName);
    }

    public void dropAIndexOfACollection(String localCollectionName, String indexName){
        MongoCollection<Document> currentCol = database.getCollection(localCollectionName);
        currentCol.dropIndex(indexName);
    }

    public void loadFromJsonArrayFile(String collectionName, String filePath, String fileName) {
        String jsonFilePath = filePath + "/" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
            String line;
            StringBuilder jsonContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }

            List<Document> deptDocuments = new ArrayList<>();
            String json = jsonContent.toString();
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                String doc = jsonArray.getJSONObject(i).toString();
                Document document = Document.parse(doc);
                deptDocuments.add(document);
            }

            if (!deptDocuments.isEmpty()) {
                MongoCollection<Document> collection = database.getCollection(collectionName);

                collection.insertMany(deptDocuments);

                System.out.println("Documents inserted successfully");
            } else {
                System.out.println("No documents found in the JSON file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportToJsonArrayFile(String collectionName, String filePath, String fileName){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().cursor();
        List<String> elementList = new ArrayList<>();
        File f = new File(filePath + "/" + fileName + ".json");
        while(cursor.hasNext()){
            Document doc = cursor.next();
            elementList.add(doc.toJson());
        }
        String jsonString = elementList.toString();
        try {
            // Créer un objet FileWriter avec le fichier en tant que paramètre
            FileWriter fileWriter = new FileWriter(f);

            // Créer un objet BufferedWriter pour écrire dans le fichier
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Écrire du texte dans le fichier
            bufferedWriter.write(jsonString);

            // Fermer le BufferedWriter et fileWriter pour libérer les ressources
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Le texte a été écrit dans le fichier avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


