import com.google.gson.Gson;
import voldemort.client.StoreClient;    
import voldemort.versioning.Versioned;

public class Visitor extends Entity {
    private String VNom;
    private String VPrenom;
    private String VVille;
    private int VAge;
    private String VDateVisite;

    // Constructeur existant
    public Visitor(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        // Parsez le JSON pour initialiser les attributs
        Gson gson = new Gson();
        Visitor visitor = gson.fromJson(json, Visitor.class);
        this.VNom = visitor.VNom;
        this.VPrenom = visitor.VPrenom;
        this.VVille = visitor.VVille;
        this.VAge = visitor.VAge;
        this.VDateVisite = visitor.VDateVisite;
    }
    // Nouveau constructeur sans JSON
    public Visitor(StoreClient<String, String> client, String _id) {
        super(client, _id);
        // Ici, vous pouvez initialiser les attributs à des valeurs par défaut ou les laisser non initialisés
    }

    // Getters et setters pour les attributs
    public String getVNom() {
        return VNom;
    }

    public void setVNom(String VNom) {
        this.VNom = VNom;
    }

    public String getVPrenom() {
        return VPrenom;
    }

    public void setVPrenom(String VPrenom) {
        this.VPrenom = VPrenom;
    }

    public String getVVille() {
        return VVille;
    }

    public void setVVille(String VVille) {
        this.VVille = VVille;
    }

    public int getVAge() {
        return VAge;
    }

    public void setVAge(int VAge) {
        this.VAge = VAge;
    }

    public String getVDateVisite() {
        return VDateVisite;
    }

    public void setVDateVisite(String VDateVisite) {
        this.VDateVisite = VDateVisite;
    }

    @Override
    public void read() {
        Versioned<String> versioned = this.client.get(this._id);
        String json = versioned.getValue();
        Gson gson = new Gson();
        Visitor visitor = gson.fromJson(json, Visitor.class);
        this.VNom = visitor.VNom;
        this.VPrenom = visitor.VPrenom;
        this.VVille = visitor.VVille;
        this.VAge = visitor.VAge;
        this.VDateVisite = visitor.VDateVisite;
    }
}