import java.util.ArrayList;
import java.util.Scanner;

// ================= ABSTRACT CLASS =================
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


// ================= FRUIT TREE =================
class FruitTree extends Tree {

    public FruitTree(int treeId, String species, String location,
                     String plantationDate, double height,
                     String healthStatus, boolean alive) {

        super(treeId, species, location, plantationDate,
                height, healthStatus, alive);
    }


    public void displayTreeType() {
        System.out.println("Tree Type        : Fruit Tree");
    }
}


// ================= SHADE TREE =================
class ShadeTree extends Tree {

    public ShadeTree(int treeId, String species, String location,
                     String plantationDate, double height,
                     String healthStatus, boolean alive) {

        super(treeId, species, location, plantationDate,
                height, healthStatus, alive);
    }

    
    public void displayTreeType() {
        System.out.println("Tree Type        : Shade Tree");
    }
}


// ================= TREE MANAGER =================
class TreeManager {

    private ArrayList<Tree> trees = new ArrayList<>();

    // Add tree
    public void addTree(Tree tree) {

        for (Tree t : trees) {
            if (t.getTreeId() == tree.getTreeId()) {
                System.out.println("Tree ID already exists!");
                return;
            }
        }

        trees.add(tree);
        System.out.println("Tree added successfully!");
    }

    // Display all trees
    public void displayAllTrees() {

        if (trees.isEmpty()) {
            System.out.println("No trees registered.");
            return;
        }

        System.out.println("\n===== ALL TREES =====");

        for (Tree tree : trees) {
            tree.displayInfo();
        }
    }

    // Search tree
    public void searchTree(int id) {

        for (Tree tree : trees) {

            if (tree.getTreeId() == id) {

                System.out.println("\nTree found!");
                tree.displayInfo();
                return;
            }
        }

        System.out.println("Tree not found.");
    }

    // Update tree
    public void updateTree(int id, double height,
                           String healthStatus,
                           boolean alive) {

        for (Tree tree : trees) {

            if (tree.getTreeId() == id) {

                tree.setHeight(height);
                tree.setHealthStatus(healthStatus);
                tree.setAlive(alive);

                System.out.println("Tree information updated!");
                return;
            }
        }

        System.out.println("Tree not found.");
    }

    // Delete tree
    public void deleteTree(int id) {

        for (Tree tree : trees) {

            if (tree.getTreeId() == id) {

                trees.remove(tree);

                System.out.println("Tree deleted successfully!");
                return;
            }
        }

        System.out.println("Tree not found.");
    }

    // Calculate survival rate
    public void calculateSurvivalRate() {

        if (trees.isEmpty()) {
            System.out.println("No trees available.");
            return;
        }

        int aliveTrees = 0;

        for (Tree tree : trees) {

            if (tree.isAlive()) {
                aliveTrees++;
            }
        }

        int totalTrees = trees.size();

        double survivalRate =
                ((double) aliveTrees / totalTrees) * 100;

        System.out.println("\n===== SURVIVAL REPORT =====");
        System.out.println("Total Trees : " + totalTrees);
        System.out.println("Alive Trees : " + aliveTrees);
        System.out.println("Dead Trees  : " +
                (totalTrees - aliveTrees));

        System.out.printf("Survival Rate: %.2f%%\n",
                survivalRate);
    }

    // Show trees needing attention
    public void treesNeedingAttention() {

        boolean found = false;

        System.out.println("\n===== TREES NEEDING ATTENTION =====");

        for (Tree tree : trees) {

            if (!tree.isAlive() || tree.getHealthStatus() .equalsIgnoreCase("Poor")) {

                tree.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println( "No trees currently need attention.");
        }
    }
}


// ================= MAIN CLASS =================
public class TreeMonitoringSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TreeManager manager = new TreeManager();

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println(" TREE SURVIVAL & PLANTATION MONITORING");
            System.out.println("======================================");

            System.out.println("1. Add New Tree");
            System.out.println("2. View All Trees");
            System.out.println("3. Search Tree");
            System.out.println("4. Update Tree");
            System.out.println("5. Delete Tree");
            System.out.println("6. Calculate Survival Rate");
            System.out.println("7. Trees Needing Attention");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            try {

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    // ================= ADD =================
                    case 1:

                        System.out.print("Enter Tree ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Species: ");
                        String species = sc.nextLine();

                        System.out.print("Enter Location: ");
                        String location = sc.nextLine();

                        System.out.print(
                                "Enter Plantation Date: ");
                        String date = sc.nextLine();

                        System.out.print(
                                "Enter Initial Height (cm): ");
                        double height = sc.nextDouble();
                        sc.nextLine();

                        System.out.print(
                                "Enter Health Status " +
                                "(Healthy/Average/Poor): ");

                        String health = sc.nextLine();

                        System.out.print(
                                "Is the tree alive? (yes/no): ");

                        String aliveInput = sc.nextLine();

                        boolean alive =
                                aliveInput.equalsIgnoreCase("yes");

                        System.out.println("\nSelect Tree Type:");
                        System.out.println("1. Fruit Tree");
                        System.out.println("2. Shade Tree");

                        System.out.print("Enter type: ");

                        int type = sc.nextInt();
                        sc.nextLine();

                        Tree tree;

                        if (type == 1) {

                            tree = new FruitTree(
                                    id,
                                    species,
                                    location,
                                    date,
                                    height,
                                    health,
                                    alive
                            );

                        } else {

                            tree = new ShadeTree(
                                    id,
                                    species,
                                    location,
                                    date,
                                    height,
                                    health,
                                    alive
                            );
                        }

                        manager.addTree(tree);

                        break;


                    // ================= VIEW =================
                    case 2:

                        manager.displayAllTrees();

                        break;


                    // ================= SEARCH =================
                    case 3:

                        System.out.print(
                                "Enter Tree ID to search: ");

                        int searchId = sc.nextInt();
                        sc.nextLine();

                        manager.searchTree(searchId);

                        break;


                    // ================= UPDATE =================
                    case 4:

                        System.out.print(
                                "Enter Tree ID to update: ");

                        int updateId = sc.nextInt();

                        System.out.print(
                                "Enter New Height (cm): ");

                        double newHeight = sc.nextDouble();
                        sc.nextLine();

                        System.out.print(
                                "Enter New Health Status: ");

                        String newHealth = sc.nextLine();

                        System.out.print(
                                "Is the tree alive? (yes/no): ");

                        String newAliveInput =
                                sc.nextLine();

                        boolean newAlive =
                                newAliveInput
                                .equalsIgnoreCase("yes");

                        manager.updateTree(
                                updateId,
                                newHeight,
                                newHealth,
                                newAlive
                        );

                        break;


                    // ================= DELETE =================
                    case 5:

                        System.out.print(
                                "Enter Tree ID to delete: ");

                        int deleteId = sc.nextInt();
                        sc.nextLine();

                        manager.deleteTree(deleteId);

                        break;


                    // ================= SURVIVAL RATE =================
                    case 6:

                        manager.calculateSurvivalRate();

                        break;


                    // ================= ATTENTION =================
                    case 7:

                        manager.treesNeedingAttention();

                        break;


                    // ================= EXIT =================
                    case 8:

                        System.out.println(
                                "Thank you for using the system!");

                        break;


                    default:

                        System.out.println(
                                "Invalid choice!");

                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid input! Please enter the correct value.");

                sc.nextLine();

                choice = 0;
            }

        } while (choice != 8);

        sc.close();
    }
}
