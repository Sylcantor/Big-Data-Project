import voldemort.client.protocol.admin.AdminClient;
import voldemort.client.protocol.admin.AdminClientConfig;
import voldemort.store.StoreDefinition;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.utils.ByteArray;
import voldemort.versioning.Versioned;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.parse.ANTLRParser.prequelConstruct_return;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DatabaseBuilder {
    private StoreClient<String, String> client;
    private Gson gson;
    private AdminClient adminClient;
    private StoreClientFactory factory;

    public DatabaseBuilder(String bootStrapUrl, StoreClientFactory factory) {
        this.factory = factory;
        this.gson = new Gson();
        this.adminClient = new AdminClient(bootStrapUrl);
    }

    public void buildVisitors() throws IOException {
        client = factory.getStoreClient("visitors");
        Type listType = new TypeToken<List<Visitor>>(){}.getType();
        List<Visitor> visitors = gson.fromJson(new FileReader("data/visitors.json"), listType);
        for (Visitor visitor : visitors) {
            // Convert the Visitor object to JSON
            String json = gson.toJson(visitor);
            // Insert the visitor data into the database
            client.put(visitor.getId(), json);
        }
    }

    public void buildTickets() throws IOException {
        client = factory.getStoreClient("tickets");
        Type listType = new TypeToken<List<Ticket>>(){}.getType();
        List<Ticket> tickets = gson.fromJson(new FileReader("data/tickets.json"), listType);
        for (Ticket ticket : tickets) {
            // Convert the Ticket object to JSON
            String json = gson.toJson(ticket);
            // Insérer les données du ticket dans la base de données
            client.put(ticket.getId(), json);
        }
    }

    public void buildEmployees() throws IOException {
        client = factory.getStoreClient("employees");
        Type listType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> employees = gson.fromJson(new FileReader("data/employees.json"), listType);
        for (Employee employee : employees) {
            // Convert the Employee object to JSON
            String json = gson.toJson(employee);
            // Insert the employee data into the database
            client.put(employee.getId(), json);
        }
    }

    public void buildDepartments() throws IOException {
        client = factory.getStoreClient("departments");
        Type listType = new TypeToken<List<Department>>(){}.getType();
        List<Department> departments = gson.fromJson(new FileReader("data/departments.json"), listType);
        for (Department department : departments) {
            // Convert the Department object to JSON
            String json = gson.toJson(department);
            // Insert the department data into the database
            client.put(department.getId(), json);
        }
    }


    public void deleteAllData() {
        // List of all stores
        List<String> stores = Arrays.asList("visitors", "tickets", "employees", "departments", "treatments", "animals", "enclosures");

        for (String storeName : stores) {
            StoreClient<String, String> client = this.factory.getStoreClient(storeName);
            // Get all keys
            List<String> keys = getAllKeys(storeName);

            // Delete all keys
            for (String key : keys) {
                client.delete(key);
            }
        }
    }

    public void buildTreatments() throws IOException {
        client = factory.getStoreClient("treatments");
        Type listType = new TypeToken<List<Treatment>>(){}.getType();
        List<Treatment> treatments = gson.fromJson(new FileReader("data/treatments.json"), listType);
        for (Treatment treatment : treatments) {
            // Convert the Treatment object to JSON
            String json = gson.toJson(treatment);
            // Insert the treatment data into the database
            client.put(treatment.getId(), json);
        }
    }

    public void buildAnimals() throws IOException {
        client = factory.getStoreClient("animals");
        Type listType = new TypeToken<List<Animal>>(){}.getType();
        List<Animal> animals = gson.fromJson(new FileReader("data/animals.json"), listType);
        for (Animal animal : animals) {
            // Convertir l'objet Animal en JSON
            String json = gson.toJson(animal);
            // Insérer les données de l'animal dans la base de données
            client.put(animal.getId(), json);
        }
    }


    public void buildEnclosures() throws IOException {
        client = factory.getStoreClient("enclosures");
        Type listType = new TypeToken<List<Enclosure>>(){}.getType();
        List<Enclosure> enclosures = gson.fromJson(new FileReader("data/enclosures.json"), listType);
        for (Enclosure enclosure : enclosures) {
            // Convertir l'objet Enclosure en JSON
            String json = gson.toJson(enclosure);
            // Insérer les données de l'enclosure dans la base de données
            client.put(enclosure.getId(), json);
        }
    }

    public List<String> getAllKeys(String storeName) {
        int nodeId = 0;
        List<String> keys = new ArrayList<String>();

        // Get the list of partitions
        List<Integer> partitionList = adminClient.getAdminClientCluster().getNodeById(nodeId).getPartitionIds();

        Iterator<ByteArray> iterator = adminClient.bulkFetchOps.fetchKeys(nodeId, storeName, partitionList, null, false);
        while (iterator.hasNext()) {
            keys.add(new String(iterator.next().get()));
        }
        return keys;
    }
}
