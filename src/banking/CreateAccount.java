package banking;

import java.util.Scanner;
import java.sql.*;
import java.util.Random;

public class CreateAccount {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose one option: ");
		System.out.println("1. Create Account 2. Login");
		int opt = sc.nextInt();
		switch(opt) {
			case 1:
				System.out.println("Enter Details for Account Creation!");
				break;
			case 2:
				Login.main(args);
				return;
			default:
				System.out.println("Choose correct options");
				break;
		}
		Random random = new Random();
		
		System.out.print("Enter sno: ");
		int sno = sc.nextInt();
		System.out.print("Enter your name: ");
		String name = sc.next();
		System.out.print("Enter your address: ");
		String address = sc.next();
		System.out.print("Enter your password: ");
		String password = sc.next();
		System.out.print("Enter your mobile number: ");
		long mobile = sc.nextLong();
		int balance = 0;
		int accountNo = random.nextInt(90000000) + 10000000;
		int pin = random.nextInt(9000) + 1000;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Nireesha@0521");
			PreparedStatement ps = con.prepareStatement("insert into user(sno, name, address, password, mobile, accountNo, pin, balance)values(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, sno);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, password);
			ps.setLong(5, mobile);
			ps.setInt(6, accountNo);
			ps.setInt(7, pin);
			ps.setInt(8, balance);
			int d = ps.executeUpdate();
			if(d>0) {
				System.out.println("Congratulations!!! Your account created successfully");
				System.out.println("Your account number is: " + accountNo);
				System.out.println("Your pin number: " + pin);
			}
			else {
				System.out.println("fail");
			}
			System.out.println("Choose an option: ");
			System.out.println("1. Login  2. Delete Account");
			int option = sc.nextInt();
			switch(option) {
				case 1:
					Login.main(args);
					break;
				case 2:
					PreparedStatement s = con.prepareStatement("delete from user where name = ?");
					s.setString(1, name);
					s.executeUpdate();
					System.out.println("Your Account Deleted Successfully");
					break;
				default:
					System.out.println("Choose correct Option!!");
			}
			sc.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}