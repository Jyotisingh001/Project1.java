class ShadeTree extends Tree {

    public ShadeTree(int treeId, String species, String location,
                     String plantationDate, double height,
                     String healthStatus, boolean alive) {

        super(treeId, species, location, plantationDate,
                height, healthStatus, alive);
    }

    
    public void displayTreeType() {
        System.out.println("Tree Type       : Shade Tree");
    }
}
