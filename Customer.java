package bankingManagementSystem;

import java.util.Scanner;

public class Customer {
    private String fullName;
    private String email;
    private String password;
    private String dob;  // YYYY-MM-DD format

    public void inputCustomerDetails(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Full Name: ");
        this.fullName = scanner.nextLine();
        System.out.print("Email: ");
        this.email = scanner.nextLine();
        System.out.print("Password: ");
        this.password = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        this.dob = scanner.nextLine();
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getDob() { return dob; }
}
