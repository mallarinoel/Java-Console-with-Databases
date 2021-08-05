/////////////////////////////////////////////
//First_name = varchar(50)
//Middle_name = varchar(50)
//Last_name = varchar(50)
//Age = int(2)
//Gender = varchar(6)
//Address = varchar(50)
//Date_of_birth = date
//Nationality = varchar(50)
//Religion = varchar(50)
//Student_contact = int(9)
//Section = varchar
//Fathers_name = varchar
//Father_Occupation = varchar
//Mothers_name = varchar
//Occupation = varchar
//Parents_address = varchar
//Parents_contact = varchar
/////////////////////////////////////////////

//=========================================================================================================================//

package enrollmentsystem;

import java.util.*;
import java.sql.*;

public class Enrollmentsystem {

    public static int ch = 0;
    public static char yn;
    public static String username, password;
    public static boolean string = false;
    public static String url = "jdbc:mysql://localhost:3306/dbmysql?userSSL=true";
    public static String user = "root";
    public static String pass = "";
    public static Statement stmt;
    public static Connection con;
    public static Scanner in = new Scanner(System.in);
    public static PreparedStatement psmtp;
    public static ResultSet rs;
    public static String First_name, Student_contact, Date_of_birth, Middle_name, Last_name, Gender, Address, Nationality, Religion, Section, Fathers_name, Father_Occupation, Mothers_name, Occupation, Parents_address, Parents_contact;
    public static int Age;

    public static void print(String str) {
        System.out.println(str);
    }

    public static void printf(String str) {
        System.out.print(str);
    }

    private static void studentPanel() {
        do {
            print("/////////////////////////////////////");
            print("//=Welcome back [" + username + "]=//");
            print("////| [1] = Add Student     |////////");
            print("////| [2] = Delete Student  |////////");
            print("/////////////////////////////////////");
            printf("Enter Choice : ");
            ch = in.nextInt();
            switch (ch) {

                case 1: {
                    print("\n");
                    addStudent();
                    break;
                }

                case 2: {
                    print("\n");
                    deletRecord();
                    break;
                }

                case 3: {
                    print("\n");
                    break;
                }
                default: {
                    print("Would you like to continue? [Y/N]");
                    printf("Enter : ");
                    yn = in.next().charAt(0);
                    break;
                }
            }
        } while (yn == 'y' || yn == 'Y');
    }

    private static void deletRecord() {
        try {

            int id = 0;
            String mysql = "select * from tbl_student";
            con = DriverManager.getConnection(url, user, pass);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(mysql);
            System.out.println("Id    First Name           Last Name");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "\t" + rs.getString("First_name") + "\t\t     " +  rs.getString("Last_Name")+ "\n");
            }
            print("\n[1] = Delete Student");
            print("[2] =  Back");
            printf("Enter Choice : ");
            int choice = 0;
            choice = in.nextInt();
            print("\n");
            if (choice == 1) {
                printf("Enter Student ID : ");
                id = in.nextInt();
                String delete_info = "delete from tbl_student where id=?";
                PreparedStatement psmtp = con.prepareStatement(delete_info);
                psmtp.setInt(1, id);
                psmtp.executeUpdate();
                print("\tDeleted Successfully\n");
                studentPanel();
            } else if (choice == 2) {
                print("\n");
                studentPanel();
            } else {
                print(choice + "Not found ");
            }

        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    private static void addStudent() {

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            String mysql = ("insert into tbl_student " + " (First_name, Middle_name, Last_name, Age, Gender, Address, Date_of_birth, Nationality, Religion, Student_contact, Section, Fathers_name, Fathers_Occupation, Mothers_name, Occupation, Parents_address, Parents_contact)"
                    + " values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            psmtp = con.prepareStatement(mysql);

            print("\n\t===|Fill up the form below.|===");
            print("//////////////////////////////");

            printf("Enter First Name : ");
            First_name = in.next();

            if (First_name.isEmpty()) {
                print("Cannot be empty");
                addStudent();
            } else {
                printf("Enter Middle Name : ");
                Middle_name = in.next();
                if (Middle_name.isEmpty()) {
                } else {
                    printf("Enter Last Name : ");
                    Last_name = in.next();
                    if (Last_name.isEmpty()) {

                        print("Cannot be empty");
                        addStudent();

                    } else {

                        printf("Enter Age : ");
                        Age = in.nextInt();

                        printf("Enter Gender : ");
                        Gender = in.next();
                        if (Gender.isEmpty()) {
                            print("Cannot be empty");
                            addStudent();
                        } else {
                            printf("Enter Religion : ");
                            Religion = in.next();
                            if (Religion.isEmpty()) {
                                print("Cannot be empty");
                                addStudent();
                            } else {
                            printf("Enter Address : ");
                            Address = in.next();
                            if (Address.isEmpty()) {
                                print("Cannot be empty");
                                addStudent();
                            } else {

                                print("\n\t\tyyyy|mm|dd");

                                printf("Enter Date of birth : ");
                                Date_of_birth = in.next();
                                if (Date_of_birth.isEmpty()) {
                                    print("Cannot be empty");
                                    addStudent();
                                } else {
                                    printf("Enter Nationality : ");
                                    Nationality = in.next();

                                    if (Nationality.isEmpty()) {
                                        print("Cannot be empty");
                                        addStudent();
                                    } else {

                                        printf("Enter Contact # : ");
                                        Student_contact = in.next();
                                        if (Student_contact.isEmpty()) {
                                            print("Cannot be empty");
                                            addStudent();
                                        } else {
                                            printf("Enter Section : ");
                                            Section = in.next();

                                            if (Section.isEmpty()) {
                                                print("Cannot be empty");
                                                addStudent();

                                            } else {
                                                printf("Enter Fathers Name : ");
                                                Fathers_name = in.next();

                                                if (Fathers_name.isEmpty()) {
                                                    print("Cannot be empty");
                                                    addStudent();
                                                } else {
                                                    printf("Enter Occupation : ");
                                                    Father_Occupation = in.next();

                                                    if (Father_Occupation.isEmpty()) {
                                                        print("Cannot be empty");
                                                        addStudent();
                                                    } else {
                                                        printf("Enter Mothers Name : ");
                                                        Mothers_name = in.next();

                                                        if (Mothers_name.isEmpty()) {
                                                            print("Cannot be empty");
                                                            addStudent();
                                                        } else {
                                                            printf("Enter Occupation : ");
                                                            Occupation = in.next();

                                                            if (Occupation.isEmpty()) {
                                                                print("Cannot be empty");
                                                                addStudent();
                                                            } else {
                                                                printf("Enter Parents Address : ");
                                                                Parents_address = in.next();

                                                                if (Parents_address.isEmpty()) {
                                                                    print("Cannot be empty");
                                                                    addStudent();
                                                                } else {
                                                                    printf("Enter Parents Contact # : ");
                                                                    Parents_contact = in.next();
                                                                    if (Parents_contact.isEmpty()) {
                                                                        print("Cannot be empty");
                                                                        addStudent();
                                                                    } else {
                                                                        java.sql.Date date = java.sql.Date.valueOf(Date_of_birth);
                                                                        psmtp.setString(1, First_name);
                                                                        psmtp.setString(2, Middle_name);
                                                                        psmtp.setString(3, Last_name);
                                                                        psmtp.setInt(4, Age);
                                                                        psmtp.setString(5, Gender);
                                                                        psmtp.setString(6, Address);
                                                                        psmtp.setDate(7, date);
                                                                        psmtp.setString(8, Nationality);
                                                                        psmtp.setString(9, Religion);
                                                                        psmtp.setString(10, Student_contact);
                                                                        psmtp.setString(11, Section);
                                                                        psmtp.setString(12, Fathers_name);
                                                                        psmtp.setString(13, Father_Occupation);
                                                                        psmtp.setString(14, Mothers_name);
                                                                        psmtp.setString(15, Occupation);
                                                                        psmtp.setString(16, Parents_address);
                                                                        psmtp.setString(17, Parents_contact);
                                                                        psmtp.executeUpdate();
                                                                        print("\tSuccessfully Added.\n");
                                                                        studentPanel();
                                                                        con.close();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void login() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("\n||===== Welcome back - Login =======||");
            System.out.print("Username : ");
            username = in.next();
            System.out.print("Password : ");
            password = in.next();
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from tbl_account where username='" + username + "' and password ='" + password + "'");
                if (rs.next()) {
                    print("\tSuccessfully Login\n");
                    studentPanel();
                } else {
                    print("\n\tAccount not found!, Please try again\n");
                    programMenu();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void register() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            String mysql = ("insert into tbl_account " + " (username, password)"
                    + " values( ?, ?)");

            psmtp = con.prepareStatement(mysql);
            print("////////////////////////////////////");
            printf("\nEnter Username : ");
            username = in.next();
            if (username.equals("")) {
                register();
            } else {
                printf("Enter Password : ");
                password = in.next();
                if (password.equals("")) {
                    register();
                } else {
                    psmtp.setString(1, username);
                    psmtp.setString(2, password);
                    psmtp.executeUpdate();
                    print("\tRegistered Successfully :).\n");
                    programMenu();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void programMenu() {
        do {
            print("////////////////////////////////////");
            print("////////[1] = Login    |////////////");
            print("////////[2] = Register |////////////");
            print("////////////////////////////////////");
            printf("Enter Choice : ");
            ch = in.nextInt();
            switch (ch) {
                case 1: {
                    login();
                    break;
                }
                case 2: {
                    register();
                    break;
                }
                default: {
                    print("Would you like to continue? [Y/N]");
                    printf("Enter : ");
                    yn = in.next().charAt(0);
                    break;
                }
            }
        } while (yn == 'Y' || yn == 'y');
    }

    public static void main(String[] args) {
        programMenu();
    }
}
