import com.google.gson.Gson;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;


public class Animal extends Entity {
    private String AnimName;
    private String Espece;
    private String Sexe;
    private String Diet;
    private String DateNaiss;
    private String DateArriv;
    private int Poids;
    private int Taille;
    private int Enclos;

    public Animal(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        Gson gson = new Gson();
        Animal animal = gson.fromJson(json, Animal.class);
        this.AnimName = animal.AnimName;
        this.Espece = animal.Espece;
        this.Sexe = animal.Sexe;
        this.Diet = animal.Diet;
        this.DateNaiss = animal.DateNaiss;
        this.DateArriv = animal.DateArriv;
        this.Poids = animal.Poids;
        this.Taille = animal.Taille;
        this.Enclos = animal.Enclos;
    }

    public Animal(StoreClient<String, String> client, String _id) {
        super(client, _id);
    }

    public String getAnimName() {
        return AnimName;
    }

    public void setAnimName(String animName) {
        AnimName = animName;
    }

    public String getEspece() {
        return Espece;
    }

    public void setEspece(String espece) {
        Espece = espece;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public String getDiet() {
        return Diet;
    }

    public void setDiet(String diet) {
        Diet = diet;
    }

    public String getDateNaiss() {
        return DateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        DateNaiss = dateNaiss;
    }

    public String getDateArriv() {
        return DateArriv;
    }

    public void setDateArriv(String dateArriv) {
        DateArriv = dateArriv;
    }

    public int getPoids() {
        return Poids;
    }

    public void setPoids(int poids) {
        Poids = poids;
    }

    public int getTaille() {
        return Taille;
    }

    public void setTaille(int taille) {
        Taille = taille;
    }

    public int getEnclos() {
        return Enclos;
    }

    public void setEnclos(int enclos) {
        Enclos = enclos;
    }

    @Override
    public void read() {
        Versioned<String> versioned = client.get(_id);
        Gson gson = new Gson();
        Animal animal = gson.fromJson(versioned.getValue(), Animal.class);
        this.AnimName = animal.AnimName;
        this.Espece = animal.Espece;
        this.Sexe = animal.Sexe;
        this.Diet = animal.Diet;
        this.DateNaiss = animal.DateNaiss;
        this.DateArriv = animal.DateArriv;
        this.Poids = animal.Poids;
        this.Taille = animal.Taille;
        this.Enclos = animal.Enclos;
    }
}
