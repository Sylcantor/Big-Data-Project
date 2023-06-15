import com.google.gson.Gson;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public class Department extends Entity {
    private String Dname;

    public Department(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        Gson gson = new Gson();
        Department department = gson.fromJson(json, Department.class);
        this.Dname = department.Dname;
    }

    public Department(StoreClient<String, String> client, String _id) {
        super(client, _id);
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String Dname) {
        this.Dname = Dname;
    }

    @Override
    public void read() {
        Versioned<String> versioned = client.get(_id);
        Gson gson = new Gson();
        Department department = gson.fromJson(versioned.getValue(), Department.class);
        this.Dname = department.Dname;
    }
}
