package bankingManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Staff {
    private Connection connection;

    public Staff(Connection connection) {
        this.connection = connection;
    }

    // Staff Login Validation
    public boolean staffLogin(String email, String password) {
        String query = "SELECT * FROM staffdataset WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if staff exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // View All Customers
    public void viewAllCustomers() {
        String query = "SELECT u.full_name, u.email, u.dob, a.account_number, a.created_at " +
                       "FROM usersdataset u " +
                       "LEFT JOIN accountsdatasets a ON u.email = a.email";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- List of All Customers ---");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("full_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("DOB: " + rs.getString("dob"));
                long accNum = rs.getLong("account_number");
                if (accNum != 0) {
                    System.out.println("Account Number: " + accNum);
                    System.out.println("Account Created At: " + rs.getTimestamp("created_at"));
                } else {
                    System.out.println("No Bank Account Created Yet");
                }
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
