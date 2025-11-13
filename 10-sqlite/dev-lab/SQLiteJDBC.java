import java.sql.*;

public class SQLiteJDBC {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:10-sqlite/sqlite-database/test.db");
        } catch  (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }
}
