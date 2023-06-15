import com.google.gson.Gson;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;


public class Enclosure extends Entity {
    private String EncName;
    private int Capacite;
    private int Taille;

    public Enclosure(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        Gson gson = new Gson();
        Enclosure enclosure = gson.fromJson(json, Enclosure.class);
        this.EncName = enclosure.EncName;
        this.Capacite = enclosure.Capacite;
        this.Taille = enclosure.Taille;
    }

    public Enclosure(StoreClient<String, String> client, String _id) {
        super(client, _id);
    }

    public String getEncName() {
        return EncName;
    }

    public void setEncName(String encName) {
        EncName = encName;
    }

    public int getCapacite() {
        return Capacite;
    }

    public void setCapacite(int capacite) {
        Capacite = capacite;
    }

    public int getTaille() {
        return Taille;
    }

    public void setTaille(int taille) {
        Taille = taille;
    }

    @Override
    public void read() {
        Versioned<String> versioned = client.get(_id);
        Gson gson = new Gson();
        Enclosure enclosure = gson.fromJson(versioned.getValue(), Enclosure.class);
        this.EncName = enclosure.EncName;
        this.Capacite = enclosure.Capacite;
        this.Taille = enclosure.Taille;
    }
}
