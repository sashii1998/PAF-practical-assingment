package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hcs", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertDoctor(String dCode, String dName, String specilisation, String dTell, String dMaill) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into doctor( docid, docCode, docname, specilisation, doctell, docmaill)"
					+ " values ( ?,  ?,  ?,  ?,  ?,  ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, dCode);
			preparedStmt.setString(3, dName);
			preparedStmt.setString(4, specilisation);
			preparedStmt.setString(5, dTell);
			preparedStmt.setString(6, dMaill);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newDoctor = readDoctor(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readDoctor()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1'><tr><th>Doctor Code</th><th>Doctor Name</th><th>Specilisation</th><th>Doctor Phone</th><th>Doctor Emaill</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from doctor";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String docid = Integer.toString(rs.getInt("docid"));
				String docCode = rs.getString("docCode");
				String docname = rs.getString("docname");
				String specilisation = rs.getString("specilisation");
				String doctell = rs.getString("doctell");
				String docmaill = rs.getString("docmaill");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidItemIDUpdate\' name=\'hidItemIDUpdate\' type=\'hidden\' value=\'" + docid + "'>" 
							+ docCode + "</td>";      
				output += "<td>" + docname + "</td>";     
				output += "<td>" + specilisation + "</td>";
				output += "<td>" + doctell + "</td>";
				output += "<td>" + docmaill + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-docid='" + docid + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the items.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateDoctor(String ID, String code, String name, String specilisation, String tell, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctor SET docCode=?,docname=?,specilisation=?,doctell=?,docmaill=? WHERE docid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, specilisation);
			preparedStmt.setString(4, tell);
			preparedStmt.setString(5, email);
			preparedStmt.setInt(6, Integer.parseInt(ID));	 
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newDoctor = readDoctor();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newDoctor + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteDoctor(String docid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctor where docid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(docid));
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newDoctor = readDoctor();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					newDoctor + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the item.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}

