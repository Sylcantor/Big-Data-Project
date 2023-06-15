import com.google.gson.Gson;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public class Treatment extends Entity {
    private String SoinName;
    private String DateSoin;
    private int AnimId;
    private int EmpId;

    public Treatment(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        Gson gson = new Gson();
        Treatment treatment = gson.fromJson(json, Treatment.class);
        this.SoinName = treatment.SoinName;
        this.DateSoin = treatment.DateSoin;
        this.AnimId = treatment.AnimId;
        this.EmpId = treatment.EmpId;
    }

    public Treatment(StoreClient<String, String> client, String _id) {
        super(client, _id);
    }

    public String getSoinName() {
        return SoinName;
    }

    public void setSoinName(String SoinName) {
        this.SoinName = SoinName;
    }

    public String getDateSoin() {
        return DateSoin;
    }

    public void setDateSoin(String DateSoin) {
        this.DateSoin = DateSoin;
    }

    public int getAnimId() {
        return AnimId;
    }

    public void setAnimId(int AnimId) {
        this.AnimId = AnimId;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }

    @Override
    public void read() {
        Versioned<String> versioned = client.get(_id);
        Gson gson = new Gson();
        Treatment treatment = gson.fromJson(versioned.getValue(), Treatment.class);
        this.SoinName = treatment.SoinName;
        this.DateSoin = treatment.DateSoin;
        this.AnimId = treatment.AnimId;
        this.EmpId = treatment.EmpId;
    }
}
