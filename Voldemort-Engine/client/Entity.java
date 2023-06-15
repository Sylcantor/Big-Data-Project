import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public abstract class Entity {
    protected transient StoreClient<String, String> client;
    protected String _id;

    public Entity(StoreClient<String, String> client, String _id) {
        this.client = client;
        this._id = _id;
    }

    public void create(String json) {
        client.put(_id, json);
    }

    public abstract void read();

    public void update(String json) {
        client.put(_id, json);
    }

    public void delete() {
        client.delete(_id);
    }

    public String getId() {
        return _id;
    }
}