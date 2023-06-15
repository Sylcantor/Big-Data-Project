import com.google.gson.Gson;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;


public class Employee extends Entity {
    private String empPrenom;
    private String empNom;
    private String empCV;
    private String empJob;
    private double empSal;
    private String empDateNaiss;
    private String empDateEmb;
    private int dept;

    public Employee(StoreClient<String, String> client, String _id, String json) {
        super(client, _id);
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        this.empPrenom = employee.empPrenom;
        this.empNom = employee.empNom;
        this.empCV = employee.empCV;
        this.empJob = employee.empJob;
        this.empSal = employee.empSal;
        this.empDateNaiss = employee.empDateNaiss;
        this.empDateEmb = employee.empDateEmb;
        this.dept = employee.dept;
    }

    public Employee(StoreClient<String, String> client, String _id) {
        super(client, _id);
    }

    public String getEmpPrenom() {
        return empPrenom;
    }

    public void setEmpPrenom(String empPrenom) {
        this.empPrenom = empPrenom;
    }

    public String getEmpNom() {
        return empNom;
    }

    public void setEmpNom(String empNom) {
        this.empNom = empNom;
    }

    public String getEmpCV() {
        return empCV;
    }

    public void setEmpCV(String empCV) {
        this.empCV = empCV;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public double getEmpSal() {
        return empSal;
    }

    public void setEmpSal(double empSal) {
        this.empSal = empSal;
    }

    public String getEmpDateNaiss() {
        return empDateNaiss;
    }

    public void setEmpDateNaiss(String empDateNaiss) {
        this.empDateNaiss = empDateNaiss;
    }

    public String getEmpDateEmb() {
        return empDateEmb;
    }

    public void setEmpDateEmb(String empDateEmb) {
        this.empDateEmb = empDateEmb;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }
    
    @Override
    public void read() {
        Versioned<String> versioned = client.get(_id);
        Gson gson = new Gson();
        Employee employee = gson.fromJson(versioned.getValue(), Employee.class);
        this.empPrenom = employee.empPrenom;
        this.empNom = employee.empNom;
        this.empCV = employee.empCV;
        this.empJob = employee.empJob;
        this.empSal = employee.empSal;
        this.empDateNaiss = employee.empDateNaiss;
        this.empDateEmb = employee.empDateEmb;
        this.dept = employee.dept;
    }
}
