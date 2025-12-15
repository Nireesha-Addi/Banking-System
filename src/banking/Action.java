package banking;

import java.util.Scanner;
import java.sql.*;

class Deposit{
	int balance=0;
	public void depositAmount(int Balance, int depositAmount) {
		balance = Balance;
		balance += depositAmount;
		System.out.println(depositAmount + " deposited successful");
		System.out.println("Your current balance is: " + balance);
	}
	public int getBalance() {
		return balance;
	}
}

class Withdraw{
	int balance=0;
	public void withdrawAmount(int Balance, int withdrawAmount) {
		balance = Balance;
		if(withdrawAmount <= balance) {
			balance -= withdrawAmount;
			System.out.println(withdrawAmount + " withdraw successful");
			System.out.println("Your current balance is: " + balance);
		}
		else {
			System.out.println("Insufficient funds");
		}
	}
	public int getBalance() {
		return balance;
	}
}

class CheckBalance{
	int balance=0;
	public void checkBalance(int Balance) {
		balance = Balance;
		System.out.print("Your current balance is: " + balance);
	}
}

class ChangePassword{
	String pass, username;
	public boolean isPassword(String password, String pass) {
		this.pass = pass;
		if(password.equals(pass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isName(String name, String username) {
		this.username = username;
		if(name.equals(username)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

class UserDetails {
	
	Scanner sc = new Scanner(System.in);
	int pinNo, balance=0,pin, option;
	String password, name;
	Deposit deposit = new Deposit();
	Withdraw withdraw = new Withdraw();
	CheckBalance checkbalance = new CheckBalance();
	ChangePassword changepassword = new ChangePassword();
	public boolean isPin(int pin) {
		System.out.println("Enter your pin Number: ");
		pinNo = sc.nextInt();
		if(pinNo == pin) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int chooseOption() {
		System.out.println("Choose one option from below: ");
		System.out.println("1. Deposit  2. Withdraw  3. Check Balance 4. Change Password 5. forgot password");
		int option = sc.nextInt();
		if(!(isPin(pin))) {
			System.out.println("Invalid pin!!");
			return balance;
		}
		switch(option) {
		case 1:
			System.out.print("Enter Deposite Amount: ");
			int depositAmount = sc.nextInt();
			deposit.depositAmount(balance, depositAmount);
			return deposit.getBalance();
		case 2:
			System.out.print("Enter Withdraw Amount: ");
			int withdrawAmount = sc.nextInt();
			withdraw.withdrawAmount(balance, withdrawAmount);
			return withdraw.getBalance();
		case 3:
			checkbalance.checkBalance(balance);
			return balance;
		case 4: 
			System.out.print("Enter your password: ");
			String pass = sc.next();
			if(changepassword.isPassword(password, pass)) {
				System.out.print("Enter your new Password: ");
				String newPassword = sc.next();
				setPassword(newPassword);
				System.out.println("Password changed successfully");
			}
			else {
				System.out.println("Wrong password entered!!");
			}
			return balance;
		case 5:
			System.out.print("Enter your username: ");
			String username = sc.next();
			
			if(changepassword.isName(name, username)) {
				System.out.print("Enter your new Password: ");
				String newPassword = sc.next();
				setPassword(newPassword);
				System.out.println("Password updated successfully");
			}
			else {
				System.out.println("Wrong username!!");
			}
			return balance;
		default:
			System.out.println("Choose correct option!!!");
			return balance;
		}
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}
}


public class Action {
	static String name;
	public static void setName(String Name) {
		name = Name;
	}
	
	public static void main(String[] args) {
		UserDetails details = new UserDetails();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Nireesha@0521");
			PreparedStatement ps1 = con.prepareStatement("select balance, password, pin  from user where name = ?");
			ps1.setString(1, name);
			ResultSet rs1= ps1.executeQuery();
			String password;
			int balance=0, pin;
			if(rs1.next()) {
				balance = rs1.getInt("balance");
				password = rs1.getString("password");
				pin = rs1.getInt("pin");
				details.setBalance(balance);
				details.setPassword(password);
				details.setPin(pin);
				details.setName(name);
				balance = details.chooseOption();
			}
			PreparedStatement ps2 = con.prepareStatement("Update user set balance=?,  password = ? where name=?");
			ps2.setInt(1, balance);
			ps2.setString(2, details.getPassword());
			ps2.setString(3, name);
			ps2.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
}