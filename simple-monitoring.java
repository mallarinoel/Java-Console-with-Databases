//////////////////////////////////////////////
//////| Developed By : Noel Mallari  |////////
//////| Date Created : August/3/2021 |////////
//////////////////////////////////////////////
package student_monitoring_system;

import java.util.*;
import java.sql.*;
import java.sql.DriverManager;

public class Student_monitoring_system {

    public static boolean incheck = true;
    public static String url = "jdbc:mysql://localhost:3306/mydb?userSSL=true";
    public static String user = "root";
    public static String pass = "";
    public static Connection con;
    public static Statement stmt;
    public static PreparedStatement psmtp;
    public static Scanner sc = new Scanner(System.in);

    //my SQL Variables
    public static int id, absent, present = 0;
    public static String ch, fname, mname, lname, gender, address, birth, number;

    public static void dtcheck(int i) {
        int j = 0;

        if (!"".equals(j)) {
            incheck = false;
        } else {
            System.out.println("Enter a valid input!!!");
            incheck = true;
        }

    }

    public static void checker(String val) {
        String c = val.replaceAll(" ", "");

        if (!"".equals(c)) {
            incheck = false;
        } else {
            System.out.println("Enter a valid input!!!");
            incheck = true;
        }

    }
    private static void StudentInfo(){
       try {
                System.out.print("Enter ID : ");
                id = sc.nextInt();

                while (incheck == true) {
                    System.out.print("Enter First Name : ");
                    fname = sc.next();
                    checker(fname);
                }
                incheck = true;

                while (incheck == true) {
                    System.out.print("Enter Middle Name : ");
                    mname = sc.next();
                    checker(mname);
                }

                incheck = true;

                while (incheck == true) {
                    System.out.print("Enter Last Name : ");
                    lname = sc.next();
                    checker(lname);
                }

                incheck = true;

                while (incheck == true) {
                    System.out.print("Enter Gender : ");
                    gender = sc.next();
                    checker(gender);
                }

                incheck = true;

                while (incheck == true) {
                    System.out.print("Enter number : ");
                    number = sc.next();
                    checker(number);
                }

                incheck = true;

                while (incheck == true) {
                    System.out.print("Enter Address : ");
                    address = sc.next();
                    checker(address);
                }

                incheck = true;
                while (incheck == true) {

                    System.out.println("\tyyyy-MM-dd");
                    System.out.print("Enter Birthday : ");
                    birth = sc.next();
                    checker(birth);
                }

                incheck = true;
                while (incheck == true) {
                    System.out.print("Enter Number of absent : ");
                    absent = sc.nextInt();
                    dtcheck(absent);
                }
                incheck = true;
                while (incheck == true) {
                    System.out.print("Enter Number of present : ");
                    present = sc.nextInt();
                    dtcheck(present);
                }
                java.sql.Date date = java.sql.Date.valueOf(birth);

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                stmt = con.createStatement();
                String addRecord = ("insert into tbl_student " + "(id, fname, mname, lname,  gender, number, address, birth, absent, present)"
                        + " values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                psmtp = con.prepareStatement(addRecord);

                psmtp.setInt(1, id);
                psmtp.setString(2, fname);
                psmtp.setString(3, mname);
                psmtp.setString(4, lname);
                psmtp.setString(5, gender);
                psmtp.setString(6, number);
                psmtp.setString(7, address);
                psmtp.setDate(8, date);
                psmtp.setInt(9, absent);
                psmtp.setInt(10, present);
                psmtp.executeUpdate(); 
                System.out.println("Successfuly Inserted!");
                System.out.print("Would you like to continue? [Y/N]");
                ch = sc.next();
                if(ch.equals("y") || ch.equals("Y")){
                    System.out.println("\n\n");
                StudentInfo();
                }else{
                System.exit(1);
                }
            
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public static void main(String[] args) {
        StudentInfo();
    }
}
