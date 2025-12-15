package banking;

import java.util.Scanner;
import java.sql.*;

public class Login {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your details to login!");
		System.out.print("Enter your name: ");
		String name = sc.next();
		System.out.print("Enter your password: ");
		String password = sc.next();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Nireesha@0521");
			PreparedStatement ps = con.prepareStatement("select * from user where name = ? and password = ?");
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Login successful");
				Action.setName(name);
				Action.main(args);
			}
			else {
				System.out.println("Enter valid Credentials!");
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		sc.close();
	}
}
