import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ODBCConnectionExample {

    public static void main(String[] args) {
        // Define the ODBC data source name (DSN)
        String dsn = "your_dsn_name";
        // Define the database user and password (if applicable)
        String user = "your_username";
        String password = "your_password";

        // Connection URL format for ODBC
        String url = "jdbc:odbc:" + dsn;

        // Initialize connection and statement objects
        Connection conn = null;
        Statement stmt = null;

        try {
            // Load JDBC-ODBC Bridge driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

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
                // Replace with your column names
                System.out.println("Column1: " + rs.getString("Column1"));
                System.out.println("Column2: " + rs.getString("Column2"));
                // Add more columns as needed
            }

            // Close the ResultSet
            rs.close();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-ODBC Bridge driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the resources
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
