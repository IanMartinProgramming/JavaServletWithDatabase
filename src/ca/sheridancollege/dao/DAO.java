package ca.sheridancollege.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	Connection conn = null;
	
	public void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a3", "root", "1234");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Check your JDBC Driver location!!!");
		}
	}
	
	public String getNameByID(int id) {
		String name = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT firstName, lastName FROM RockStars WHERE id = ?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				rs.next();
				name = rs.getString("firstName") + " " + rs.getString("lastName");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return name;
	}
	
	public double getSalaryByID(int id) {
		double salary = 0.0;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT salary FROM RockStars WHERE id = ?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				rs.next();
				salary = rs.getDouble("salary");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return salary;
	}
	
	public double getSalaryByName(String firstName, String lastName) {
		double salary = 0.0;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT salary FROM RockStars WHERE firstName = ? and lastName = ?");
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				rs.next();
				salary = rs.getDouble("salary");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return salary;
	}
	
	public int getIDByName(String firstName, String lastName) {
		int id = -1;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM RockStars WHERE firstName = ? and lastName = ?");
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				rs.next();
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
}
