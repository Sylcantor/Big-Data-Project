import com.google.gson.Gson;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.versioning.Versioned;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties prop = new Properties();

        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
        
        StoreClient<String, String> client;
        
        try {
            // Charger le fichier de configuration

            FileInputStream in = new FileInputStream("config.properties");
            prop.load(in);
            in.close();


            // Vérifier l'argument de ligne de commande
            if (args.length > 0) {
                String command = args[0];
                if (command.equals("build")) {
                    // Construire la base de données
                    // ...
                    client = factory.getStoreClient("visitors");
                    DatabaseBuilder dbBuilder = new DatabaseBuilder(bootstrapUrl, factory);
                    dbBuilder.buildVisitors();
                    dbBuilder.buildTickets();
                    dbBuilder.buildEmployees();
                    dbBuilder.buildDepartments();
                    dbBuilder.buildAnimals();
                    dbBuilder.buildTreatments();   
                    dbBuilder.buildEnclosures();

                    // Mettre à jour le fichier de configuration
                    prop.setProperty("database.built", "true");
                    FileOutputStream out = new FileOutputStream("config.properties");
                    prop.store(out, null);
                    out.close();

                    System.out.println("Database built successfully.");

                } else if (command.equals("deleteAllData")) {
                    // Supprimer toutes les données
                    // ...
                    DatabaseBuilder dbBuilder = new DatabaseBuilder(bootstrapUrl, factory);
                    dbBuilder.deleteAllData();

                    // Mettre à jour le fichier de configuration
                    prop.setProperty("database.built", "false");
                    FileOutputStream out = new FileOutputStream("config.properties");
                    prop.store(out, null);
                    out.close();

                    System.out.println("All data deleted successfully.");

                } else if (command.equals("run")) {
                    // Exécuter le programme seulement si la base de données a été construite
                    if (Boolean.parseBoolean(prop.getProperty("database.built"))) {
                        // Exécuter le programme
                        // ...

                        /*--------------------------------------------- VISITOR -------------------------------------------------*/
                        // Create a new visitor
                        client = factory.getStoreClient("visitors");
                        Visitor visitor = new Visitor(client, "1");
                        
                        // Read the visitor and update properties
                        visitor.read();

                        System.out.println("--------------------------------------------- VISITOR -------------------------------------------------");

                        System.out.println("Initial visitor: " + visitor.getVNom());

                        // Update the visitor
                        visitor.setVNom("Marcel");
                        visitor.setVPrenom("Pagnol");
                        visitor.setVVille("Marseille");
                        Gson gson = new Gson();
                        String json = gson.toJson(visitor);
                        visitor.update(json);

                        // Read the updated visitor
                        Visitor updatedVisitor = new Visitor(client, "1");
                        updatedVisitor.read();
                        System.out.println("Updated visitor \nNom: " + updatedVisitor.getVNom() + "\nPrenom: " + updatedVisitor.getVPrenom() + "\nVille: " + updatedVisitor.getVVille());

                        System.out.println("-------------------------------------------------------------------------------------------------------");

                        /*--------------------------------------------- EMPLOYEES -------------------------------------------------*/

                        client = factory.getStoreClient("employees");

                        System.out.println("--------------------------------------------- EMPLOYEES -------------------------------------------------");

                        // Create a new employee
                        Employee employee = new Employee(client, "1");
                        
                        // Read the employee and update properties
                        employee.read();

                        System.out.println("Initial employee: " + employee.getEmpNom());

                        // Update the employee
                        employee.setEmpNom("Jacques");
                        employee.setEmpPrenom("Brel");
                        employee.setEmpSal(2300);

                        gson = new Gson();
                        json = gson.toJson(employee);
                        employee.update(json);

                        // Read the updated employee
                        Employee updatedEmployee = new Employee(client, "1");
                        updatedEmployee.read();

                        System.out.println("Updated employee \nNom: " + updatedEmployee.getEmpNom() + "\nPrenom: " + updatedEmployee.getEmpPrenom() + "\nSalaire: " + updatedEmployee.getEmpSal());
                        
                        System.out.println("-------------------------------------------------------------------------------------------------------");


                        /*--------------------------------------------- DEPARTMENTS -------------------------------------------------*/

                        client = factory.getStoreClient("departments");

                        System.out.println("--------------------------------------------- DEPARTMENTS -------------------------------------------------");

                        // Create a new department
                        Department department = new Department(client, "1");

                        // Read the department and update properties
                        department.read();

                        System.out.println("Initial department: " + department.getDname());

                        // Update the department

                        department.setDname("Avocats");

                        gson = new Gson();
                        json = gson.toJson(department);
                        department.update(json);

                        // Read the updated department
                        Department updatedDepartment = new Department(client, "1");
                        updatedDepartment.read();

                        System.out.println("Updated department \nNom: " + updatedDepartment.getDname());

                        System.out.println("-------------------------------------------------------------------------------------------------------");

                        /*--------------------------------------------- ANIMALS -------------------------------------------------*/

                        client = factory.getStoreClient("animals");

                        System.out.println("--------------------------------------------- ANIMALS -------------------------------------------------");

                        // Create a new animal
                        Animal animal = new Animal(client, "1");

                        // Read the animal and update properties
                        animal.read();

                        System.out.println("Initial animal: " + animal.getAnimName());

                        // Update the animal
                        animal.setAnimName("Ambre");
                        animal.setEnclos(567);
                        animal.setPoids(346);

                        gson = new Gson();
                        json = gson.toJson(animal);
                        animal.update(json);

                        // Read the updated animal
                        Animal updatedAnimal = new Animal(client, "1");
                        updatedAnimal.read();

                        System.out.println("Updated animal \nNom: " + updatedAnimal.getAnimName() + "\nEnclos: " + updatedAnimal.getEnclos() + "\nPoids: " + updatedAnimal.getPoids());
                        
                        System.out.println("-------------------------------------------------------------------------------------------------------");


                        /*--------------------------------------------- ENCLOSURES -------------------------------------------------*/
                        client = factory.getStoreClient("enclosures");

                        System.out.println("--------------------------------------------- ENCLOSURES -------------------------------------------------");

                        // Create a new enclosure
                        Enclosure enclosure = new Enclosure(client, "1");

                        // Read the enclosure and update properties
                        enclosure.read();

                        System.out.println("Initial enclosure: " + enclosure.getEncName());

                        // Update the enclosure
                        enclosure.setEncName("Enclos des lions");
                        enclosure.setCapacite(96);
                        enclosure.setTaille(130);

                        gson = new Gson();
                        json = gson.toJson(enclosure);
                        enclosure.update(json);

                        // Read the updated enclosure
                        Enclosure updatedEnclosure = new Enclosure(client, "1");
                        updatedEnclosure.read();

                        System.out.println("Updated enclosure \nNom: " + updatedEnclosure.getEncName() + "\nCapacite: " + updatedEnclosure.getCapacite() + "\nTaille: " + updatedEnclosure.getTaille());

                        System.out.println("-------------------------------------------------------------------------------------------------------");


                        /*--------------------------------------------- TICKETS -------------------------------------------------*/
                        
                        client = factory.getStoreClient("tickets");

                        System.out.println("--------------------------------------------- TICKETS -------------------------------------------------");


                        // Create a new ticket
                        Ticket ticket = new Ticket(client, "1");

                        // Read the ticket and update properties
                        ticket.read();

                        System.out.println("Initial ticket: " + ticket.getDateAchat());

                        // Update the ticket
                        ticket.setDateAchat("2020-12-12");
                        ticket.setTarif(50);

                        gson = new Gson();
                        json = gson.toJson(ticket);
                        ticket.update(json);

                        // Read the updated ticket
                        Ticket updatedTicket = new Ticket(client, "1");
                        updatedTicket.read();

                        System.out.println("Updated ticket \nDate: " + updatedTicket.getDateAchat() + "\nTarif: " + updatedTicket.getTarif());

                        System.out.println("-------------------------------------------------------------------------------------------------------");

                        /*--------------------------------------------- TREATMENT -------------------------------------------------*/

                        client = factory.getStoreClient("treatments");
                        System.out.println("--------------------------------------------- TREATMENT -------------------------------------------------");

                        // Create a new treatment
                        Treatment treatment = new Treatment(client, "1");

                        // Read the treatment and update properties
                        treatment.read();

                        System.out.println("Initial treatment: " + treatment.getSoinName());

                        // Update the treatment
                        treatment.setSoinName("Toilettage");
                        treatment.setDateSoin("2020-12-12");

                        gson = new Gson();
                        json = gson.toJson(treatment);
                        treatment.update(json);

                        // Read the updated treatment
                        Treatment updatedTreatment = new Treatment(client, "1");
                        updatedTreatment.read();

                        System.out.println("Updated treatment \nNom: " + updatedTreatment.getSoinName() + "\nDate: " + updatedTreatment.getDateSoin());
                        System.out.println("-------------------------------------------------------------------------------------------------------");
                    
                    } else {
                        System.out.println("The database has not been built. Run 'build' before 'run'.");
                    }
                }
                else{
                    System.out.println("Usage: java Main <command>");
                    System.out.println("where <command> is one of:");
                    System.out.println("  build           Build the database");
                    System.out.println("  deleteAllData   Delete all data");
                    System.out.println("  run             Execute the program");
                }
            }
            else {
                System.out.println("Usage: java Main <command>");
                System.out.println("where <command> is one of:");
                System.out.println("  build           Build the database");
                System.out.println("  deleteAllData   Delete all data");
                System.out.println("  run             Execute the program");
            }
        } catch (IOException e) {
            // Traitement des erreurs
            e.printStackTrace();
        }
    }
}




/*
public class Main {
    public static void main(String[] args) {
        String bootstrapUrl = "tcp://localhost:6666";
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));

        // create a client that executes operations on a single store
        StoreClient<String, String> client = factory.getStoreClient("visitors");

        // Create a new visitor
        Visitor visitor = new Visitor(client, "V1");
        System.out.println("Initial visitor: " + visitor.getVNom());

        // Update the visitor
        visitor.setVNom("Dupont");
        Gson gson = new Gson();
        String json = gson.toJson(visitor);
        visitor.update(json);

        // Read the updated visitor
        Visitor updatedVisitor = new Visitor(client, "V1");
        System.out.println("Updated visitor: " + updatedVisitor.getVNom());
    }
}

 */