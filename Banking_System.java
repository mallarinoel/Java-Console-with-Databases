package bank_system;

import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Bank_system {

    public static String random_number;
    public static boolean repeat = true;
    public static boolean repeat2 = true;
    private static String CardNumber, checkBal;
    private static String fname, mname, lname;
    private static int pin, age, ch;
    private static double balance, nbalance, withdraw, deposit, newbalance;
    final private static Scanner sc = new Scanner(System.in);
    public static Statement stmt;

    public static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(20));
    }

    public static void randomNum() {
        Random rand = new Random();
        String r = String.format((Locale) null,
                "52%02d-%04d-%04d-%04d",
                rand.nextInt(100),
                rand.nextInt(1000),
                rand.nextInt(1000),
                rand.nextInt(1000));
        random_number = r;
    }

    public static void checkpin(int p) {
        if (p >= 1000 && p < 10000) {
            repeat = false;
        } else {
            System.out.println("Invalid Pin!!!");
            repeat = true;
        }

    }

    public static void check(String val) {
        String c = val.replaceAll(" ", "");

        if (!"".equals(c)) {
            repeat2 = false;
        } else {
            System.out.println("Enter a valid input!!!");
            repeat2 = true;
        }

    }

    private static void createacc() {
        try {
            String url = "jdbc:mysql://localhost:3306/bankdb?userSSL=true";//Name of database is bankdb
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            String insert = ("insert into user_account " + " (cardnumber, fname, mname, lname,  age, pin)"
                    + " values( ?, ?, ?, ?, ?, ?)");
            PreparedStatement psmtp = con.prepareStatement(insert);
            clearScreen();
            randomNum();
            System.out.println("\n----------------------------------------------------------------------\n\n");

            System.out.println("Create Bank Account\n");
            System.out.print("Card Number: ");
            CardNumber = random_number;
            System.out.println(CardNumber);
            while (repeat2 == true) {
                System.out.print("Enter First Name : ");
                fname = sc.next();
                check(fname);
            }
            repeat2 = true;

            while (repeat2 == true) {
                System.out.print("Enter Middle Name : ");
                mname = sc.next();
                check(mname);
            }
            repeat2 = true;

            while (repeat2 == true) {
                System.out.print("Enter Last Name : ");
                lname = sc.next();
                check(lname);
            }

            System.out.print("Enter Age : ");
            age = sc.nextInt();

            //pag hindi 4 digit ang pin uulit
            while (repeat == true) {

                System.out.print("Enter 4 Digit PIN: ");
                pin = sc.nextInt();
                checkpin(pin);
            }
            try {
                if (age >= 18) {
                    psmtp.setString(1, CardNumber);
                    psmtp.setString(2, fname);
                    psmtp.setString(3, mname);
                    psmtp.setString(4, lname);
                    psmtp.setInt(5, age);
                    psmtp.setInt(6, pin);
                    psmtp.executeUpdate();
                    System.out.println("Account Created Successfuly!");
                    clearScreen();
                    function_Start();
                } else if (age < 0 && age > 120) {
                    System.out.println("Invalid Age");
                } else {
                    System.out.println("\nSorry!!! Your Age is not valid to create an account!!!");
                    function_Start();
                }
            } catch (SQLException e) {
            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            System.out.println(e);
        }

    }

    private static void account_withdraw() {
        try {
            String url = "jdbc:mysql://localhost:3306/bankdb?userSSL=true";//Name of database is myDB
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            System.out.println("Name : " + fname);
            System.out.println("Account Number:" + CardNumber + " \nBalance: " + balance + "");
            System.out.print("Amount : ");
            withdraw = sc.nextDouble();
            if (withdraw > balance) {
                System.out.println("Sorry you are out of balance\n\n\n");
                Welcomeuser();
            } else {
                String update = ("update user_account set balance =?");
                try {
                    PreparedStatement psmtp = con.prepareStatement(update);
                    newbalance = balance - withdraw;
                    psmtp.setDouble(1, newbalance);
                    psmtp.executeUpdate();
                    System.out.println("Successly Withdraw\n\n\n\n");
                    Welcomeuser();
                } catch (SQLException e) {
                }

            }
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    private static void account_deposit() {
        try {
            String url = "jdbc:mysql://localhost:3306/bankdb?userSSL=true";//Name of database is myDB
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            String sql = ("SELECT * FROM user_account");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                CardNumber = rs.getString("cardnumber");
                balance = rs.getDouble("balance");
                fname = rs.getString("fname");
                System.out.println("Account Name : " + fname);
                System.out.println("Account Number:" + CardNumber + " \nBalance: " + balance + "");
                System.out.println("How much you are going to deposit?");

                System.out.print("Enter amount: ");
                nbalance = sc.nextDouble();

                String insert = ("update user_account set balance=?");
                try {
                    PreparedStatement psmtp = con.prepareStatement(insert);
                    deposit = balance + nbalance;
                    psmtp.setDouble(1, deposit);

                    psmtp.executeUpdate();

                    System.out.println("Successly Deposit\n\n\n\n");
                    Welcomeuser();
                } catch (SQLException e) {
                }

            }

        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    private static void Welcomeuser() {
        String url = "jdbc:mysql://localhost:3306/bankdb?userSSL=true";//Name of database is myDB
        String user = "root";
        String pass = "";
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM user_account");
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                fname = rs.getString("fname");
                balance = rs.getDouble("balance");
                System.out.println("Welcome " + fname + "\n");
                System.out.println("Balance " + balance + "");
                System.out.println("Press 1 - Withdraw Cash");
                System.out.println("Press 2 - Deposit Cash");
                System.out.println("Press 3 - Exit");
                System.out.print("Enter Choice: ");

                ch = sc.nextInt();
                switch (ch) {
                    case 1: {
                        clearScreen();
                        account_withdraw();

                        break;
                    }
                    case 2: {
                        clearScreen();
                        account_deposit();
                        break;
                    }
                    case 3: {
                        clearScreen();
                        function_Start();
                        break;
                    }
                    default: {
                        Welcomeuser();
                        break;
                    }

                }
            }
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    private static void LogInAccount() {
        try {
            System.out.println("LOG IN");
            System.out.print("Card Number: ");
            String cardNum = sc.next();
            System.out.print("PIN: ");
            pin = sc.nextInt();
            String url = "jdbc:mysql://localhost:3306/bankdb?userSSL=true";//Name of database is myDB
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            try ( Connection con = DriverManager.getConnection(url, user, pass)) {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from user_account where cardnumber='" + cardNum + "' and pin ='" + pin + "'");

                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count == 0) {
                    System.out.print("Card Number or PIN is wrong or Card Number is not existed. Try Again!\n\n\n");
                    LogInAccount();
                }
                clearScreen();
                Welcomeuser();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Card Number or PIN is wrong or Card Number is not existed. Try Again!");
            LogInAccount();
        }
    }

    private static void CheckBalance() {
        try {
            System.out.print("Card Number: ");
            checkBal = sc.next();

            String url = "jdbc:mysql://localhost:3306/bankdb?userSSL=true";//Name of database is myDB
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            String sql = ("SELECT * FROM user_account");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CardNumber = rs.getString("cardnumber");
                balance = rs.getDouble("balance");
                fname = rs.getString("fname");
                System.out.println("Account Name : " + fname);
                System.out.println("Account Number:" + CardNumber + " \nBalance: " + balance + "");

                System.out.println("\nPress 1 to exit , Press 2 to Continue");
                System.out.print("Enter Choice : ");
                ch = sc.nextInt();
                switch (ch) {
                    case 1: {
                        System.out.println("\nThank you!");
                        System.exit(0);
                        break;
                    }
                    case 2: {

                        clearScreen();
                        function_Start();
                        break;
                    }
                    default: {
                        System.out.println(ch + "Invalid Input, Choose again.");
                    }
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    private static void function_Start() {
        System.out.println("CLS BANK");
        System.out.println("[1] = Create Bank Account");
        System.out.println("[2] = Log In an Account");
        System.out.println("[3] = Check Balance");
        System.out.print("Enter Choice: ");
        ch = sc.nextInt();

        switch (ch) {
            case 1: {
                System.out.println();
                createacc();
                break;
            }
            case 2: {
                System.out.println();
                LogInAccount();
                break;
            }
            case 3: {
                System.out.println();
                CheckBalance();
                break;
            }
            default: {
                System.out.println();
                function_Start();
                break;

            }
        }

    }

    public static void main(String[] args) {

        try {

            function_Start();
        } catch (NumberFormatException e) {
            System.out.println("Please Enter a Valid Data!!!");
            clearScreen();
            function_Start();
        } catch (NullPointerException e) {
            System.out.println("Invalid Input!!!");
            clearScreen();
            function_Start();
        } catch (final Exception e) {
            System.out.println("Invalid Character!!!");
            clearScreen();
            function_Start();
        }

    }

}
