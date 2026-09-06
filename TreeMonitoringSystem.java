import java.util.*;
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
