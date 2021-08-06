import java.sql.*;
import java.util.*;
import java.time.format.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Timestamp;

class schoolattendancesystem {

//////////////////Data Base Info ////////////////////////////////////////////////

	public static String uri = "jdbc:mysql://localhost:3306/mydb?userSSL=true";
	public static String dbuser = "root";
	public static String dbpass = "";
	public static PreparedStatement psmtp;
	public static Statement stmt;
	public static ResultSet res;
	public static Connection con;

/////////////// Global Variables  /////////////////////////////////////////////

	public static char yn;
	public static int tempid, ch = 0;
	public static Scanner in = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh-mm-ss");
	public static LocalTime time = LocalTime.now();
	public static LocalDate now = LocalDate.now();

//////////////// Teacher Variables ////////////////////////////////////////////

	public static int tid, tage, TstrTime;
	public static String tusername, tpassword, tfname, tlname, tgender, temail, tsubject = "";
	public static String tsqlin = "INSERT INTO tblteacher "
			+ " (username, password, fname, lname, age, gender, email, subject, date) "
			+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String tsqldel = "DELETE FROM tblteacher where id= ?";
	public static String tsqlview = "SELECT * FROM tblteacher";

//////////////// Student Variables ////////////////////////////////////////////

	public static int sid, sage, SstrTime;
	public static String susername, spassword, sfname, slname, scourse, semail, sgender, saddress, scontact;
	public static String ssqlin = "INSERT INTO tblstudent"
			+ " (username, password, fname, lname, age, course, gender, address, contact, email, date) "
			+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String ssqldel = "DELETE FROM tblstudent WHERE id= ?";
	public static String ssqlview = "SELECT * FROM tblstudent";

//////////////// Admin Variables //////////////////////////////////////////////

	public static String ausername, apassword, aemail = "";
	public static String tbladmin = "INSERT INTO tbladmin " + "(username, password, email) " + " values(?, ?, ?)";

///////////////////////////////////////////////////////////////////////////////

	private static void removeStudent() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			res = stmt.executeQuery(ssqlview);

			while (res.next()) {
				sid = res.getInt(1);
				susername = res.getString("username");
				spassword = res.getString("password");
				sfname = res.getString("fname");
				slname = res.getString("lname");
				sage = res.getInt("age");
				scourse = res.getString("course");
				sgender = res.getString("gender");
				saddress = res.getString("address");
				scontact = res.getString("contact");
				semail = res.getString("email");
				System.out.println("/////////////////////////////////////////////////////\n");
				System.out.println("Current Date " + dtf.format(now));
				System.out.println("ID = " + sid);
				System.out.println("Username : " + susername);
				System.out.println("Password : " + spassword);
				System.out.println("First Name : " + sfname);
				System.out.println("Last Name : " + slname);
				System.out.println("Age : " + sage);
				System.out.println("Course : " + scourse);
				System.out.println("Gender : " + sgender);
				System.out.println("Address : " + saddress);
				System.out.println("Contact # : " + scontact);
				System.out.println("Email : " + semail);
				System.out.println("/////////////////////////////////////////////////////\n");
			}
			System.out.print("Enter User ID : ");
			sid = in.nextInt();
			psmtp = con.prepareStatement(ssqldel);
			psmtp.setInt(1, sid);
			psmtp.executeUpdate();
			psmtp.close();
			con.close();
			System.out.println("\tDeleted Successfully\n");
			adminPanel();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void addStudent() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			psmtp = con.prepareStatement(ssqlin);

			System.out.print("Enter Username : ");
			susername = in.next();

			System.out.print("Enter Password : ");
			spassword = in.next();

			System.out.print("Enter First Name : ");
			sfname = in.next();

			System.out.print("Enter Last Name : ");
			slname = in.next();

			System.out.print("Enter Age : ");
			sage = in.nextInt();

			System.out.print("Enter Course : ");
			scourse = in.next();

			System.out.print("Enter Gender : ");
			sgender = in.next();

			System.out.print("Enter Address : ");
			saddress = in.next();

			System.out.print("Enter Contact # : ");
			scontact = in.next();

			System.out.print("Enter Email : ");
			semail = in.next();

			Date date = java.sql.Date.valueOf(now);

			psmtp.setString(1, susername);
			psmtp.setString(2, spassword);
			psmtp.setString(3, sfname);
			psmtp.setString(4, slname);
			psmtp.setInt(5, sage);
			psmtp.setString(6, scourse);
			psmtp.setString(7, sgender);
			psmtp.setString(8, saddress);
			psmtp.setString(9, scontact);
			psmtp.setString(10, semail);
			psmtp.setDate(11, date);
			psmtp.executeUpdate();
			psmtp.close();
			con.close();
			System.out.println("\tSuccessfully Added.\n");
			adminPanel();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void editStudent() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			res = stmt.executeQuery(tsqlview);

			while (res.next()) {
				tid = res.getInt(1);
				tusername = res.getString("username");
				tpassword = res.getString("password");
				tfname = res.getString("fname");
				tlname = res.getString("lname");
				tage = res.getInt("age");
				tgender = res.getString("gender");
				temail = res.getString("email");
				tsubject = res.getString("subject");
				System.out.println("Current Date " + dtf.format(now));
				System.out.println("ID = " + tid);
				System.out.println("Username : " + tusername);
				System.out.println("Password : " + tpassword);
				System.out.println("First Name : " + tfname);
				System.out.println("Last Name : " + tlname);
				System.out.println("Age : " + tage);
				System.out.println("Gender" + tgender);
				System.out.println("Email : " + temail);
				System.out.println("Subject : " + tsubject);
			}

			System.out.println("///////////////////////////////////////////////\n");

			do {
				System.out.print("Select ID : ");
				tempid = in.nextInt();
				String idView = "SELECT * FROM tblteacher WHERE id='" + tempid + "'";
				psmtp = con.prepareStatement(idView);
				res = stmt.executeQuery(idView);
				while (res.next()) {
					tid = res.getInt(1);
					tfname = res.getString("fname");
				}

				String tsqluser = "UPDATE tblteacher set username = ?";
				String tsqlpass = "UPDATE tblteacher set password = ?";
				String tsqlfname = "UPDATE tblteacher set fname = ?";
				String tsqllname = "UPDATE tblteacher set lname = ?";
				String tsqlage = "UPDATE tblteacher set age = ?";
				String tsqlgender = "UPDATE tblteacher set gender = ?";
				String tsqlemail = "UPDATE tblteacher set email = ?";
				String tsqlsubject = "UPDATE tblteacher set subject = ?";

				System.out.println("[1] = edit Username");
				System.out.println("[2] = edit Password");
				System.out.println("[3] = edit First Name");
				System.out.println("[4] = edit Last Name");
				System.out.println("[5] = edit Age");
				System.out.println("[6] = edit Gender");
				System.out.println("[7] = edit Email");
				System.out.println("[8] = edit Subject");
				System.out.print("Enter Choice : ");
				ch = in.nextInt();

				switch (ch) {
				case 1: {
					psmtp = con.prepareStatement(tsqluser);
					System.out.print("Enter Username : ");
					tusername = in.next();
					psmtp.setString(1, tusername);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 2: {
					psmtp = con.prepareStatement(tsqlpass);
					System.out.print("Enter Password : ");
					tpassword = in.next();
					psmtp.setString(1, tpassword);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 3: {
					psmtp = con.prepareStatement(tsqlfname);
					System.out.print("Enter First Name : ");
					tfname = in.next();
					psmtp.setString(1, tfname);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 4: {
					psmtp = con.prepareStatement(tsqllname);
					System.out.print("Enter Last Name : ");
					tlname = in.next();
					psmtp.setString(1, tlname);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 5: {
					psmtp = con.prepareStatement(tsqlage);
					System.out.print("Enter Age : ");
					tage = in.nextInt();
					psmtp.setInt(1, tage);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 6: {
					psmtp = con.prepareStatement(tsqlgender);
					System.out.print("Enter Gender : ");
					tgender = in.next();
					psmtp.setString(1, tgender);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 7: {
					psmtp = con.prepareStatement(tsqlemail);
					System.out.print("Enter Email : ");
					temail = in.next();
					psmtp.setString(1, temail);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				case 8: {
					psmtp = con.prepareStatement(tsqlsubject);
					System.out.print("Enter Subject : ");
					tsubject = in.next();
					psmtp.setString(1, tsubject);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				default: {
					System.out.println(ch + " not found.");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);

					break;
				}
				}

			} while (yn == 'Y' || yn == 'y');
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void removeTeacher() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			res = stmt.executeQuery(tsqlview);

			while (res.next()) {
				tid = res.getInt(1);
				tusername = res.getString("username");
				tpassword = res.getString("password");
				tfname = res.getString("fname");
				tlname = res.getString("lname");
				tage = res.getInt("age");
				tgender = res.getString("gender");
				temail = res.getString("email");
				tsubject = res.getString("subject");
				System.out.println("/////////////////////////////////////////////////////\n");

				System.out.println("\tCurrent Date " + dtf.format(now));
				System.out.println("ID = " + tid);
				System.out.println("Username : " + tusername);
				System.out.println("Password : " + tpassword);
				System.out.println("First Name : " + tfname);
				System.out.println("Last Name : " + tlname);
				System.out.println("Age : " + tage);
				System.out.println("Gender" + tgender);
				System.out.println("Email : " + temail);
				System.out.println("Subject : " + tsubject);
				System.out.println("/////////////////////////////////////////////////////\n");
			}
			System.out.print("Enter User ID : ");
			tid = in.nextInt();
			psmtp = con.prepareStatement(tsqldel);
			psmtp.setInt(1, tid);
			psmtp.executeUpdate();
			psmtp.close();
			con.close();
			System.out.println("\tDeleted Successfully\n");
			adminPanel();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void addTeacher() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			psmtp = con.prepareStatement(tsqlin);

			System.out.print("Enter Username : ");
			tusername = in.next();

			System.out.print("Enter Password : ");
			tpassword = in.next();

			System.out.print("Enter First Name : ");
			tfname = in.next();

			System.out.print("Enter Last Name : ");
			tlname = in.next();

			System.out.print("Enter Age : ");
			tage = in.nextInt();

			System.out.print("Enter Gender : ");
			tgender = in.next();

			System.out.print("Enter Email : ");
			temail = in.next();

			System.out.print("Enter Subject : ");
			tsubject = in.next();

			Date date = java.sql.Date.valueOf(now);

			psmtp.setString(1, tusername);
			psmtp.setString(2, tpassword);
			psmtp.setString(3, tfname);
			psmtp.setString(4, tlname);
			psmtp.setInt(5, tage);
			psmtp.setString(6, tgender);
			psmtp.setString(7, temail);
			psmtp.setString(8, tsubject);
			psmtp.setDate(9, date);
			psmtp.executeUpdate();
			psmtp.close();
			con.close();
			System.out.println("\tSuccessfully Added.\n");
			adminPanel();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

///////////////////////////////////////////////////////////////////////////////////
	private static void addDate() {
		try {
			String addDate = "insert into date " + "(date)" + " values(?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			psmtp = con.prepareStatement(addDate);
			System.out.println("Current Date " + dtf.format(now));
			java.sql.Date date = java.sql.Date.valueOf(now);

			psmtp.setDate(1, date);
			psmtp.executeUpdate();
			System.out.println("\tSuccessfully Added.\n");
		} catch (Exception e) {
		}
	}
//////////////////////////////////////////////////////////////////////////////////

	private static void editTeacher() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			res = stmt.executeQuery(tsqlview);

			while (res.next()) {
				tid = res.getInt(1);
				tusername = res.getString("username");
				tpassword = res.getString("password");
				tfname = res.getString("fname");
				tlname = res.getString("lname");
				tage = res.getInt("age");
				tgender = res.getString("gender");
				temail = res.getString("email");
				tsubject = res.getString("subject");
				System.out.println("Current Date " + dtf.format(now));
				System.out.println("ID = " + tid);
				System.out.println("Username : " + tusername);
				System.out.println("Password : " + tpassword);
				System.out.println("First Name : " + tfname);
				System.out.println("Last Name : " + tlname);
				System.out.println("Age : " + tage);
				System.out.println("Gender" + tgender);
				System.out.println("Email : " + temail);
				System.out.println("Subject : " + tsubject);
			}

			System.out.println("///////////////////////////////////////////////\n");

			do {
				System.out.print("Select ID : ");
				tempid = in.nextInt();
				String idView = "SELECT * FROM tblstudent WHERE id='" + tempid + "'";
				psmtp = con.prepareStatement(idView);
				res = stmt.executeQuery(idView);
				while (res.next()) {
					tid = res.getInt(1);
					tfname = res.getString("fname");
				}

				String ssqluser = "UPDATE tblstudent set username = ?";
				String ssqlpass = "UPDATE tblstudent set password = ?";
				String ssqlfname = "UPDATE tblstudent set fname = ?";
				String ssqllname = "UPDATE tblstudent set lname = ?";
				String ssqlage = "UPDATE tblstudent set age = ?";
				String ssqlcourse = "UPDATE tblstudent set course = ?";
				String ssqlgender = "UPDATE tblstudent set gender = ?";
				String ssqladdress = "UPDATE tblstudent set address = ?";
				String ssqlcontact = "UPDATE tblstudent set contact=?";
				String tsqlemail = "UPDATE tblstudent set email = ?";

				System.out.println("[1] = edit Username");
				System.out.println("[2] = edit Password");
				System.out.println("[3] = edit First Name");
				System.out.println("[4] = edit Last Name");
				System.out.println("[5] = edit Age");
				System.out.println("[6] = edit Course");
				System.out.println("[7] = edit Address");
				System.out.println("[8] = edit Gender");
				System.out.println("[9] = edit Contact");
				System.out.println("[10] = edit Email");
				System.out.print("Enter Choice : ");
				ch = in.nextInt();

				switch (ch) {
				case 1: {
					psmtp = con.prepareStatement(ssqluser);
					System.out.print("Enter Username : ");
					susername = in.next();
					psmtp.setString(1, susername);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 2: {
					psmtp = con.prepareStatement(ssqlpass);
					System.out.print("Enter Password : ");
					spassword = in.next();
					psmtp.setString(1, spassword);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 3: {
					psmtp = con.prepareStatement(ssqlfname);
					System.out.print("Enter First Name : ");
					sfname = in.next();
					psmtp.setString(1, sfname);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 4: {
					psmtp = con.prepareStatement(ssqllname);
					System.out.print("Enter Last Name : ");
					slname = in.next();
					psmtp.setString(1, slname);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 5: {
					psmtp = con.prepareStatement(ssqlage);
					System.out.print("Enter Age : ");
					sage = in.nextInt();
					psmtp.setInt(1, sage);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 6: {
					psmtp = con.prepareStatement(ssqlcourse);
					System.out.print("Enter Course : ");
					scourse = in.next();
					psmtp.setString(1, scourse);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}

				case 7: {
					psmtp = con.prepareStatement(ssqlgender);
					System.out.print("Enter Gender : ");
					sgender = in.next();
					psmtp.setString(1, sgender);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 8: {
					psmtp = con.prepareStatement(ssqladdress);
					System.out.print("Enter Address : ");
					saddress = in.next();
					psmtp.setString(1, saddress);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}

				case 9: {
					psmtp = con.prepareStatement(ssqlcontact);
					System.out.print("Enter Contact # : ");
					scontact = in.next();
					psmtp.setString(1, scontact);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N] : ");
					yn = in.next().charAt(0);
					break;
				}
				case 10: {
					psmtp = con.prepareStatement(tsqlemail);
					System.out.print("Enter Email : ");
					semail = in.next();
					psmtp.setString(1, semail);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Successfully Edited");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				default: {
					System.out.println(ch + " not found.");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);

					break;
				}
				}

			} while (yn == 'Y' || yn == 'y');
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void registerAdmin() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();
			psmtp = con.prepareStatement(tbladmin);

			System.out.print("Enter Username : ");
			ausername = in.next();
			System.out.print("Enter Password : ");
			apassword = in.next();
			System.out.print("Enter Email : ");
			aemail = in.next();

			psmtp.setString(1, ausername);
			psmtp.setString(2, apassword);
			psmtp.setString(3, aemail);
			psmtp.executeUpdate();
			System.out.println("Successfully Registered.");
			userLogin();
			psmtp.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void studentAttendance() {
		do {
			try {
				String strDate = "UPDATE tblstudent set date = ?";
				psmtp = con.prepareStatement(strDate);
				Date strdate = java.sql.Date.valueOf(now);
				psmtp.setDate(1, strdate);
				psmtp.executeUpdate();
				psmtp.close();
				con.close();
				System.out.println("\tCurrent Date " + dtf.format(now));
				System.out.println("[1] = Time in");
				System.out.println("[2] = Time out");
				System.out.println("[3] = Log Out");
				System.out.print("Enter Choice : ");
				ch = in.nextInt();
				switch (ch) {

				case 1: {
					String strIn = "UPDATE tblstudent set time_in = ?";
					psmtp = con.prepareStatement(strIn);
					System.out.println("Current Time : " + tf.format(time));
					java.util.Date date=new java.util.Date();
					Timestamp sqlTime =new java.sql.Timestamp(date.getTime());
					psmtp.setTimestamp(1, sqlTime);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Student Present");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				case 2: {
					String strIn = "UPDATE tblstudent set time_out = ?";
					System.out.println("Current Time : " + tf.format(time));
					psmtp = con.prepareStatement(strIn);
					System.out.println("Current Time : " + tf.format(time));
					java.util.Date date=new java.util.Date();
					Timestamp sqlTime =new java.sql.Timestamp(date.getTime());
					psmtp.setTimestamp(1, sqlTime);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Student Out");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				case 3:{
					
				break;
				}
				default: {
					System.out.println(ch + " not found.");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
				}
				}
			} catch (Exception e) {
			}
		} while (yn == 'Y' || yn == 'y');
	}

	private static void teacherAttendance() {
		do {
			try {
				String strDate = "UPDATE tblteacher set date = ?";
				psmtp = con.prepareStatement(strDate);
				Date strdate = java.sql.Date.valueOf(now);
				psmtp.setDate(1, strdate);
				psmtp.executeUpdate();
				psmtp.close();
				con.close();
				System.out.println("\tCurrent Date " + dtf.format(now));
				System.out.println("[1] = Time in");
				System.out.println("[2] = Time out");
				System.out.println("[3] = Log Out");
				System.out.print("Enter Choice : ");
				ch = in.nextInt();
				switch (ch) {

				case 1: {
					String strIn = "UPDATE tblteacher set time_in = ?";
					psmtp = con.prepareStatement(strIn);
					System.out.println("Current Time : " + tf.format(time));
					java.util.Date date=new java.util.Date();
					Timestamp sqlTime =new java.sql.Timestamp(date.getTime());
					psmtp.setTimestamp(1, sqlTime);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Teacher In");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				case 2: {
					String strIn = "UPDATE tblteacher set time_out = ?";
					System.out.println("Current Time : " + tf.format(time));
					psmtp = con.prepareStatement(strIn);
					System.out.println("Current Time : " + tf.format(time));
					java.util.Date date=new java.util.Date();
					Timestamp sqlTime =new java.sql.Timestamp(date.getTime());
					psmtp.setTimestamp(1, sqlTime);
					psmtp.executeUpdate();
					psmtp.close();
					con.close();
					System.out.println("Teacher Out");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				default: {
					System.out.println(ch + " not found.");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
				}
				}
			} catch (Exception e) {}
		}while(yn == 'Y' || yn == 'y');
		
	}

	private static void studentLogin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();

			System.out.print("Enter Username : ");
			susername = in.next();

			System.out.print("Enter Password : ");
			spassword = in.next();

			res = stmt.executeQuery(
					"SELECT * FROM tblstudent WHERE username='" + susername + "' and password ='" + spassword + "'");
			if (res.next()) {
				System.out.println("\n\tSuccessfully Login\n");
				studentAttendance();

			} else {
				System.out.println("\n\tAccount not found!, Please try again\n");
				studentLogin();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void teacherLogin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();

			System.out.print("Enter Username : ");
			tusername = in.next();

			System.out.print("Enter Password : ");
			tpassword = in.next();

			res = stmt.executeQuery(
					"SELECT * FROM tblteacher WHERE username='" + tusername + "' and password ='" + tpassword + "'");
			if (res.next()) {
				System.out.println("\n\tSuccessfully Login\n");
				teacherAttendance();
			} else {
				System.out.println("\n\tAccount not found!, Please try again\n");
				teacherLogin();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void adminLogin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();

			System.out.print("Enter Username : ");
			ausername = in.next();

			System.out.print("Enter Password : ");
			apassword = in.next();

			res = stmt.executeQuery(
					"SELECT * FROM tbladmin WHERE username='" + ausername + "' and password ='" + apassword + "'");
			if (res.next()) {
				System.out.println("\n\tSuccessfully Login\n");
				adminPanel();
			} else {
				System.out.println("\n\tAccount not found!, Please try again\n");
				adminLogin();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static void adminPanel() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();

			do {
				System.out.println("[1] = Add Student");
				System.out.println("[2] = Edit Student");
				System.out.println("[3] = Remove Student");
				System.out.println("[4] = Add Teacher");
				System.out.println("[5] = Edit Teacher");
				System.out.println("[6] = Remove Teacher");
				System.out.println("//////////////////////////////////////");
				System.out.print("Enter Choice : ");
				ch = in.nextInt();
				switch (ch) {
				case 1: {
					addStudent();
					break;
				}
				case 2: {
					editStudent();
					break;
				}
				case 3: {
					removeStudent();
					break;
				}

				case 4: {
					addTeacher();
					break;
				}
				case 5: {
					editTeacher();
					break;
				}
				case 6: {
					removeTeacher();
					break;
				}
				default: {
					System.out.println(ch + " not found.");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
				}
				}
			} while (yn == 'Y' || yn == 'y');
		} catch (Exception e) {
			System.out.println(e);
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static void userLogin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(uri, dbuser, dbpass);
			stmt = con.createStatement();

			do {
				System.out.println("\tCurrent Date " + dtf.format(now));
				System.out.println("[1] = Student Login");
				System.out.println("[2] = Teacher Login");
				System.out.println("[3] = Admin Login");
				System.out.print("Enter Choice : ");
				ch = in.nextInt();
				switch (ch) {
				case 0: {
					//Hidden Admin Registration//
					registerAdmin();
				}
				case 1: {
					studentLogin();
					break;
				}
				case 2: {
					teacherLogin();
					break;
				}
				case 3: {
					adminLogin();
					break;
				}
				default: {
					System.out.println(ch + " not found.");
					System.out.print("Would you like to continue? [Y/N]");
					yn = in.next().charAt(0);
					break;
				}
				}
			} while (yn == 'y' || yn == 'Y');
		} catch (Exception e) {
		}

	}

	public static void main(String[] Args) {
		userLogin();
	}
}
