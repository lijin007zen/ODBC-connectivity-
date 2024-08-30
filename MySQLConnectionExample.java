import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";

        Connection conn = null;
        Statement stmt = null;

        try {
            // Establish a connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // Create a statement object
            stmt = conn.createStatement();

            // Example SQL query
            String sql = "SELECT * FROM your_table_name";

            // Execute the query
            ResultSet rs = stmt.executeQuery(sql);

            // Process the results
            while (rs.next()) {
                System.out.println("Column1: " + rs.getString("Column1"));
                System.out.println("Column2: " + rs.getString("Column2"));
            }

            // Close the ResultSet
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
