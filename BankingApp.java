package bankingManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class BankingApp {
    private static final String url = "jdbc:mysql://localhost:3306/bankdb_systems";
    private static final String username = "root";
    private static final String password = "1234";

    public static void main(String[] args) {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e.getMessage());
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {

            // Create objects
            User user = new User(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);
            Staff staff = new Staff(connection);

            String email;
            long account_number;

            while (true) {
                System.out.println("\n*** WELCOME TO BANKING SYSTEM ***");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Staff Login");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice1 = scanner.nextInt();

                switch (choice1) {
                    case 1:
                        // Customer Registration
                        user.register();
                        break;

                    case 2:
                        // Customer Login
                        email = user.login();
                        if (email != null) {
                            System.out.println("\nUser Logged In!");

                            // If no bank account, ask to create one
                            if (!accounts.account_exist(email)) {
                                System.out.println("\n1. Open a new Bank Account");
                                System.out.println("2. Exit");
                                if (scanner.nextInt() == 1) {
                                    account_number = accounts.open_account(email);
                                    System.out.println("Account Created Successfully!");
                                    System.out.println("Your Account Number is: " + account_number);
                                } else {
                                    break;
                                }
                            }

                            // Existing account menu
                            account_number = accounts.getAccount_number(email);
                            int choice2 = 0;
                            while (choice2 != 5) {
                                System.out.println("\n1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log Out");
                                System.out.print("Enter your choice: ");
                                choice2 = scanner.nextInt();

                                switch (choice2) {
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        System.out.println("Logging Out...");
                                        break;
                                    default:
                                        System.out.println("Enter Valid Choice!");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Incorrect Email or Password!");
                        }
                        break;

                    case 3:
                        // Staff Login
                        scanner.nextLine(); // clear buffer
                        System.out.print("Staff Email: ");
                        String staffEmail = scanner.nextLine();
                        System.out.print("Staff Password: ");
                        String staffPass = scanner.nextLine();

                        if (staff.staffLogin(staffEmail, staffPass)) {
                            System.out.println("Staff Login Successful!");
                            staff.viewAllCustomers();
                        } else {
                            System.out.println("Invalid Staff Credentials!");
                        }
                        break;

                    case 4:
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Exiting System!");
                        return;

                    default:
                        System.out.println("Enter Valid Choice!");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
