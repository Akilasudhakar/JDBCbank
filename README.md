🏦 Banking Management System
A simple Banking Management System built in Java using JDBC and MySQL.
It supports customer registration, account management, and staff login to view all customers.

✨ Features
👤 Customer
Register with Full Name, Email, Password, and Date of Birth

Login with Email & Password

Open a new bank account (with initial balance and security PIN)

Perform Transactions:

Debit Money

Credit Money

Transfer Money

Check Balance

Account creation date & time automatically recorded

🧑‍💼 Staff
Login with Staff Email & Password (stored in DB)

View all registered customers with:

Full Name

Email

Date of Birth

Account Number (if created)

Account Creation Date & Time

🛠️ Technologies Used
Java 8+

JDBC (Java Database Connectivity)

MySQL Database

LocalDateTime (for storing account creation timestamp)

📂 Project Structure
bash
Copy
Edit
bankingManagementSystem/
├── BankingApp.java        # Main Application Entry
├── User.java              # Handles Customer Registration & Login
├── Customer.java          # Customer model class
├── Accounts.java          # Handles Account creation and details
├── AccountManager.java    # Debit, Credit, Transfer, Check Balance
├── Staff.java             # Staff login & view all customers
└── README.md              # Project Documentation
🗄️ Database Schema
Run these SQL commands to create the required tables:

sql
Copy
Edit
CREATE DATABASE bankdb_systems;

USE bankdb_systems;

-- Table for Customers
CREATE TABLE usersdataset (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    dob DATE
);

-- Table for Accounts
CREATE TABLE accountsdatasets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT UNIQUE,
    full_name VARCHAR(100),
    email VARCHAR(100),
    balance DOUBLE,
    security_pin VARCHAR(10),
    created_at DATETIME,
    FOREIGN KEY (email) REFERENCES usersdataset(email)
);

-- Table for Staff Members
CREATE TABLE staffdataset (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);
👉 Insert a staff member manually for login testing:

sql
Copy
Edit
INSERT INTO staffdataset (full_name, email, password)
VALUES ('Admin User', 'admin@bank.com', 'admin123');
▶️ How to Run
Clone this repository:

bash
Copy
Edit
git clone https://github.com/yourusername/Banking-Management-System.git
cd Banking-Management-System
Import the project in your IDE (Eclipse / IntelliJ / NetBeans).

Update database credentials in BankingApp.java:

java
Copy
Edit
private static final String url = "jdbc:mysql://localhost:3306/bankdb_systems";
private static final String username = "root";
private static final String password = "your_password";
Run the BankingApp.java file.

📸 Sample Outputs
Customer Registration

yaml
Copy
Edit
Full Name: John Doe
Email: john@example.com
Password: *****
Date of Birth (YYYY-MM-DD): 1995-08-15
Registration Successful!
Staff View

yaml
Copy
Edit
--- List of All Customers ---
Name: John Doe
Email: john@example.com
DOB: 1995-08-15
Account Number: 10000100
Account Created At: 2025-07-30 10:45:32
----------------------------
