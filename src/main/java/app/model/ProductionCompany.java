package app.model;

public class ProductionCompany {
    private String name;
    private int procoId;


    public ProductionCompany(int procoId, String name) {
        this.name = name;
        this.procoId = procoId;
    }

    public String getName() {
        return name;
    }
    
    public int getProcoId() {
    	return procoId;
    }
}
