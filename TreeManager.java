import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class TreeManager {

    
public void addTree(Tree tree) {

    String treeType = (tree instanceof FruitTree) ? "Fruit" : "Shade";

    String sql = "INSERT INTO trees (tree_id, species, location, plantation_date, height, health_status, alive, tree_type) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, tree.getTreeId());
        pstmt.setString(2, tree.getSpecies());
        pstmt.setString(3, tree.getLocation());
        pstmt.setString(4, tree.getPlantationDate());
        pstmt.setDouble(5, tree.getHeight());
        pstmt.setString(6, tree.getHealthStatus());
        pstmt.setBoolean(7, tree.isAlive());
        pstmt.setString(8, treeType);

        pstmt.executeUpdate();
        System.out.println("Tree added successfully to database!");

    } catch (SQLException e) {
        System.out.println("Error adding tree: " + e.getMessage());
    }
}
   // Display all trees (now reads from database)
public void displayAllTrees() {

    String sql = "SELECT * FROM trees";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        boolean found = false;

        System.out.println("\n===== ALL TREES =====");

        while (rs.next()) {
            found = true;

            System.out.println("----------------------------------------");
            System.out.println("Tree ID          : " + rs.getInt("tree_id"));
            System.out.println("Species          : " + rs.getString("species"));
            System.out.println("Location         : " + rs.getString("location"));
            System.out.println("Plantation Date  : " + rs.getString("plantation_date"));
            System.out.println("Height           : " + rs.getDouble("height") + " cm");
            System.out.println("Health Status    : " + rs.getString("health_status"));
            System.out.println("Survival Status  : " + (rs.getBoolean("alive") ? "Alive" : "Dead"));
            System.out.println("Tree Type        : " + rs.getString("tree_type"));
            System.out.println("----------------------------------------");
        }

        if (!found) {
            System.out.println("No trees registered.");
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving trees: " + e.getMessage());
    }
}

   // Search tree (now reads from database)
public void searchTree(int id) {

    String sql = "SELECT * FROM trees WHERE tree_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);

        try (ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("\nTree found!");
                System.out.println("----------------------------------------");
                System.out.println("Tree ID          : " + rs.getInt("tree_id"));
                System.out.println("Species          : " + rs.getString("species"));
                System.out.println("Location         : " + rs.getString("location"));
                System.out.println("Plantation Date  : " + rs.getString("plantation_date"));
                System.out.println("Height           : " + rs.getDouble("height") + " cm");
                System.out.println("Health Status    : " + rs.getString("health_status"));
                System.out.println("Survival Status  : " + (rs.getBoolean("alive") ? "Alive" : "Dead"));
                System.out.println("Tree Type        : " + rs.getString("tree_type"));
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Tree not found.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Error searching tree: " + e.getMessage());
    }
}

    // Update tree (now updates database)
public void updateTree(int id, double height,
                       String healthStatus,
                       boolean alive) {

    String sql = "UPDATE trees SET height = ?, health_status = ?, alive = ? WHERE tree_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setDouble(1, height);
        pstmt.setString(2, healthStatus);
        pstmt.setBoolean(3, alive);
        pstmt.setInt(4, id);

        int rowsUpdated = pstmt.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Tree information updated!");
        } else {
            System.out.println("Tree not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error updating tree: " + e.getMessage());
    }
}

       

// Delete tree (now deletes from database)
public void deleteTree(int id) {

    String sql = "DELETE FROM trees WHERE tree_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);

        int rowsDeleted = pstmt.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Tree deleted successfully!");
        } else {
            System.out.println("Tree not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error deleting tree: " + e.getMessage());
    }
}

   // Calculate survival rate (now reads from database)
public void calculateSurvivalRate() {

    String sql = "SELECT alive FROM trees";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        int totalTrees = 0;
        int aliveTrees = 0;

        while (rs.next()) {
            totalTrees++;
            if (rs.getBoolean("alive")) {
                aliveTrees++;
            }
        }

        if (totalTrees == 0) {
            System.out.println("No trees available.");
            return;
        }

        double survivalRate = ((double) aliveTrees / totalTrees) * 100;

        System.out.println("\n===== SURVIVAL REPORT =====");
        System.out.println("Total Trees : " + totalTrees);
        System.out.println("Alive Trees : " + aliveTrees);
        System.out.println("Dead Trees  : " + (totalTrees - aliveTrees));
        System.out.printf("Survival Rate: %.2f%%\n", survivalRate);

    } catch (SQLException e) {
        System.out.println("Error calculating survival rate: " + e.getMessage());
    }
}

// Show trees needing attention (now reads from database)
public void treesNeedingAttention() {

    String sql = "SELECT * FROM trees WHERE alive = 0 OR health_status = 'Poor'";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        boolean found = false;

        System.out.println("\n===== TREES NEEDING ATTENTION =====");

        while (rs.next()) {
            found = true;

            System.out.println("----------------------------------------");
            System.out.println("Tree ID          : " + rs.getInt("tree_id"));
            System.out.println("Species          : " + rs.getString("species"));
            System.out.println("Location         : " + rs.getString("location"));
            System.out.println("Plantation Date  : " + rs.getString("plantation_date"));
            System.out.println("Height           : " + rs.getDouble("height") + " cm");
            System.out.println("Health Status    : " + rs.getString("health_status"));
            System.out.println("Survival Status  : " + (rs.getBoolean("alive") ? "Alive" : "Dead"));
            System.out.println("Tree Type        : " + rs.getString("tree_type"));
            System.out.println("----------------------------------------");
        }

        if (!found) {
            System.out.println("No trees currently need attention.");
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving trees: " + e.getMessage());
    }
}
}
