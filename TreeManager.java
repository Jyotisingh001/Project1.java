import java.util.ArrayList;
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
