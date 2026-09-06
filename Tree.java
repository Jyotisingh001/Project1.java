abstract class Tree {

    private int treeId;
    private String species;
    private String location;
    private String plantationDate;
    private double height;
    private String healthStatus;
    private boolean alive;

    // Constructor
    public Tree(int treeId, String species, String location,
                String plantationDate, double height,
                String healthStatus, boolean alive) {

        this.treeId = treeId;
        this.species = species;
        this.location = location;
        this.plantationDate = plantationDate;
        this.height = height;
        this.healthStatus = healthStatus;
        this.alive = alive;
    }

    // Getters
    public int getTreeId() {
        return treeId;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }

    public String getPlantationDate() {
        return plantationDate;
    }

    public double getHeight() {
        return height;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public boolean isAlive() {
        return alive;
    }

    // Setters
    public void setHeight(double height) {
        this.height = height;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Abstract method
    public abstract void displayTreeType();

    // Display tree information
    public void displayInfo() {

        System.out.println("----------------------------------------");
        System.out.println("Tree ID          : " + treeId);
        System.out.println("Species          : " + species);
        System.out.println("Location         : " + location);
        System.out.println("Plantation Date  : " + plantationDate);
        System.out.println("Height           : " + height + " cm");
        System.out.println("Health Status    : " + healthStatus);
        System.out.println("Survival Status  : " +
                (alive ? "Alive" : "Dead"));

        displayTreeType();

        System.out.println("----------------------------------------");
    }
}
