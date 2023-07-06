package database;

import java.sql.*;

public class MySQLExe {
	
	public MySQLExe() {
		try{
	        String url = "jdbc:mysql://localhost:3306/sun?serverTimezone=Europe/Moscow&useSSL=false";
	        String username = "";
	        String password = "";
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	         System.out.println("+++");
	        try (Connection conn = DriverManager.getConnection(url, username, password)){
	             
	           Statement statement = conn.createStatement();
	            
	           ResultSet resultSet = statement.executeQuery("SELECT * FROM javavac WHERE date='123'");
	           while(resultSet.next()){
	                
	        	   String name = resultSet.getString("data");
	        	   //System.out.println(name);
	//               int id = resultSet.getInt(1);
	               String name2 = resultSet.getString(2);
	               System.out.println(name.length());
	//               int price = resultSet.getInt(3);
	//               System.out.printf("%d. %s - %d \n", id, name, price);
	           }
	           conn.close();
	        }
	    }
	    catch(Exception ex){
	        System.out.println("Connection failed...");
	         
	        System.out.println(ex);
	    }
		
	}

	public String readDataFromDataBase() {
		String name = "";
		try{
	        String url = "jdbc:mysql://localhost:3306/sun?serverTimezone=Europe/Moscow&useSSL=false";
	        String username = "";
	        String password = "";
	        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	         System.out.println("+++");
	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	             
	           Statement statement = conn.createStatement();
	            
	           ResultSet resultSet = statement.executeQuery("SELECT * FROM javavac WHERE date='123'");
	           while(resultSet.next()) {
	                
	        	   name = resultSet.getString("data");
	               String name2 = resultSet.getString(2);
	               System.out.println(name.length());
	           }
	           conn.close();
	        }
	    }
	    catch(Exception ex){
	        System.out.println("Connection failed...");
	        System.out.println(ex);
	    }
		
		return name;
	}
}
