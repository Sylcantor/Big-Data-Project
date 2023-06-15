import com.google.gson.Gson;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public class Ticket extends Entity {
    private String dateAchat;
    private String categorie;
    private int tarif;
    private int appartenance;

    // Constructeur existant
    public Ticket(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        // Parsez le JSON pour initialiser les attributs
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);
        this.dateAchat = ticket.dateAchat;
        this.categorie = ticket.categorie;
        this.tarif = ticket.tarif;
        this.appartenance = ticket.appartenance;
    }
    // Nouveau constructeur sans JSON
    public Ticket(StoreClient<String, String> client, String _id) {
        super(client, _id);
        // Ici, vous pouvez initialiser les attributs à des valeurs par défaut ou les laisser non initialisés
    }

    // Getters et setters pour les attributs
    public String getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public int getAppartenance() {
        return appartenance;
    }

    public void setAppartenance(int appartenance) {
        this.appartenance = appartenance;
    }

    @Override
    public void read() {
        Versioned<String> versioned = this.client.get(this._id);
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(versioned.getValue(), Ticket.class);
        this.dateAchat = ticket.dateAchat;
        this.categorie = ticket.categorie;
        this.tarif = ticket.tarif;
        this.appartenance = ticket.appartenance;
    }
}
