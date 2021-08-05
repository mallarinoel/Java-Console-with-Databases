//////////////////////////////////////////////
// Sql Command
//id int 11 auto_increment.
//fname = varchar(50);
//mname varchar(50;
//lname varchar(50;
//age int (2);
//gender	varchar(6)
//address	varchar(100)
//contact	varchar(11)
//govid	varchar(255)
//symtoms	varchar(50)
//////////////////////////////////////////////


package simple.contact.tracing;

import java.util.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class SimpleContactTracing {

    public static String url = "jdbc:mysql://localhost:3306/mydb?userSSL=true";
    public static String user = "root";
    public static String pass = "";
    public static Connection con;
    public static Statement stmt;
    public static int ch = 0;
    public static Scanner in = new Scanner(System.in);
    public static String useradmin, passadmin;
    public static String number, fname, mname, lname, address, govid, symtoms, gender;
    public static int age;
    public static char yn;
    public static ResultSet rs;

    private static void adminLogin() {
        try {
            int counter = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

            System.out.println("//===== Admin Login =======//");
            System.out.print("Username : ");
            useradmin = in.next();
            System.out.print("Password : ");
            passadmin = in.next();
            try ( Connection con = DriverManager.getConnection(url, user, pass)) {
                stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery("select * from admin_account where username='" + useradmin + "' and password ='" + passadmin + "'");
                while (rs.next()) {
                    counter++;
                }
                if (counter == 0) {
                    System.out.println("Please check your account.");
                    adminLogin();
                }
                AdminPanel();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Account is not existed. Try Again!\n");
            adminLogin();
        }
    }
    public static boolean b;

    public static void strchecker(String val) {
        String c = val.replaceAll(" ", "");

        if (!"".equals(c)) {
            b = false;
        } else {
            System.out.println("Enter a valid input!!!");
            b = true;
        }

    }

    private static void addRecord() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String insert = ("insert into info_person " + " (fname, mname, lname,  age, gender, address, contact, govid, symtoms)"
                    + " values( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement psmtp = con.prepareStatement(insert);

            print("\n\nWelcome to Contact Tracing Form - Fill up the form below.");

            b = true;
            while (b == true) {
                printf("Enter First Name : ");
                fname = in.next();
                strchecker(fname);
            }
            b = true;
            while (b == true) {
                printf("Enter Middle Name : ");
                mname = in.next();
                strchecker(mname);
            }

            b = true;
            while (b == true) {
                printf("Enter Last Name : ");
                lname = in.next();
                strchecker(lname);
            }
            b = true;

            printf("Enter Age : ");
            age = in.nextInt();

            b = true;
            while (b == true) {
                printf("Enter Gender: ");
                gender = in.next();
                strchecker(gender);
            }

            b = true;

            while (b == true) {
                printf("Enter Address: ");
                address = in.next();
                strchecker(address);
            }

            b = true;
            while (b == true) {
                printf("Enter Contact Number : ");
                number = in.next();
                strchecker(number);
            }
            b = true;
            while (b == true) {
                print("\nExample of Valid ID : SSS, TIN, Voter's ID, Driver's license, Etc\n");
                printf("Entet Type of Valid ID : ");
                govid = in.next();
                strchecker(govid);
            }
            b = true;
            while (b == true) {
                printf("Enter Symtoms :");
                symtoms = in.next();
                strchecker(mname);
            }
            psmtp.setString(1, fname);
            psmtp.setString(2, mname);
            psmtp.setString(3, lname);
            psmtp.setInt(4, age);
            psmtp.setString(5, gender);
            psmtp.setString(6, address);
            psmtp.setString(7, number);
            psmtp.setString(8, govid);
            psmtp.setString(9, symtoms);
            psmtp.executeUpdate();
            print("\n//=====|| Recoreded Succesfully ||====\\");
            print("\n\n\n");
            menu();
            con.close();
        } catch (Exception e) {
        }
    }

    private static void print(String str) {
        System.out.println(str);
    }

    private static void printf(String str) {
        System.out.print(str);
    }

    private static void deletRecord() {
        try {

            int id = 0;
            String mysql = "select * from info_person";
            con = DriverManager.getConnection(url, user, pass);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(mysql);
            System.out.println("Id    First Name       Middle Name         Last Name");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "\t" + rs.getString("fname") + "\t" + "\t"
                        + rs.getString("mname") + "\t"
                        + rs.getString("lname") + "\n");
            }
            
            print("To delete the Record enter the ID.");
            print("Press 1 to delete");
            print("Press 2 to Back");
            int choice = 0;
            choice = in.nextInt();
            
            if(choice == 1){ 
            printf("Enter ID : ");
            id = in.nextInt();
            String delete_info = "delete from info_person where id=?";
            PreparedStatement psmtp = con.prepareStatement(delete_info);
            psmtp.setInt(1, id);
            psmtp.executeUpdate();
            print("Deleted Successfully\n");
            deletRecord();
            }else if (choice == 2){
             print("\n");
             menu();
            }else{
                print(choice + "Not found ");
            }
            
            
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    private static void AdminPanel() {
        int ch = 0;
        do {
            print("[1] = Delete Info");
            print("[2] = Exit");
            printf("Enter Choice : ");
            ch = in.nextInt();
            switch (ch) {
                case 1: {
                    deletRecord();
                    break;
                }
                case 2: {
                    System.exit(0);
                    break;
                }
                default: {
                    print("Would you like to continue?[Y/N]");
                    printf("Enter Choice : ");
                    yn = in.next().charAt(0);
                    break;
                }
            }
        } while (yn == 'y' || yn == 'Y');
    }

    public static void menu() {
        do {

            System.out.println("//=========== Welcome to my Simple Contact Tracing =======//");
            System.out.println("[1] = To continue");
            System.out.print("Enter Choice : ");
            ch = in.nextInt();

            switch (ch) {
                case 1: {
                    addRecord();
                    break;
                }
                case 2: {
                    // loading();
                    adminLogin();
                    break;
                }
                default: {
                    System.out.println("[" + ch + "] invalid choicem, Try again.\n");
                    System.out.println("Would you like to continue? [Y/N]");
                    System.out.print("Enter Choice : ");
                    yn = in.next().charAt(0);
                    break;
                }

            }

        } while (yn == 'y' || yn == 'Y');

    }

    public static void main(String[] args) {
        menu();

    }

}
