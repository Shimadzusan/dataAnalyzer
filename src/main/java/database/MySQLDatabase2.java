package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.DataOperation;

public class MySQLDatabase2 {
	public static Connection connection;
	public static Statement statmt;
	public static ResultSet resSet;	//..for displays result from database	(это результат выполнения запроса)
	private static final String SQL = "DELETE FROM users WHERE id = \"3\"";
	
	//static List<InstanceData> instanceData;
		
	/* method executes connecting to data_base */
	public static void conn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/workforce", "", "");
		System.out.println("connect to data_base ...complete");
//		instanceData = id;
	}
		
	public static void createDB() throws ClassNotFoundException, SQLException {
		statmt = connection.createStatement();
	}
		
	public static void writeDB(String name, int hhid, String url, String location, String products) throws SQLException, ClassNotFoundException {
		statmt = connection.createStatement();
//		String s = "insert itSector(date,vacMoscowJavaDev,vacMoscowCplplDev,vacMoscowCshDev) values(20230115,10,40,50)";	
//		String s = buildRequest();
		String s = "INSERT INTO workforce.company (`name`,`hhid`,`url`,`location`,`products`) VALUES('" + name + "'," + hhid + ",'" + url + "','" + location + "','" + products + "')";
		statmt.execute(s);			  
		System.out.println("writing to data_base ...complete");
	}
	
	public static void writeDB2(String name, int vacancyId,  int publicationTime, int lastChangeTime, int responsesCount, int totalResponsesCount, String company, String requirements, String skill, String location) throws SQLException, ClassNotFoundException {
		statmt = connection.createStatement();
//		String s = "insert itSector(date,vacMoscowJavaDev,vacMoscowCplplDev,vacMoscowCshDev) values(20230115,10,40,50)";	
//		String s = buildRequest();
		String s = "INSERT INTO workforce.vacancies (`date`, `name`, `vacancyId`, `publicationTime`, `lastChangeTime`, `responsesCount`, `totalResponsesCount`, `company`, `requirements`, `skill`, `location`) VALUES ('20230627', '" + name + "', " + vacancyId + ", " + publicationTime + ", " + lastChangeTime + ", " + responsesCount + ", " + totalResponsesCount + ", '" + company + "', '" + requirements + "', '" + skill + "', '" + location + "')";
		statmt.execute(s);			  
		System.out.println("writing to data_base ...complete");
	}
		
	public static void closeDB() throws ClassNotFoundException, SQLException {
		connection.close();
		statmt.close();
		System.out.println("data_base is closed");
	}
	
//	public static String buildRequest() throws ClassNotFoundException, SQLException {
//		String columns = "";
//		String values = "";
//		DataOperation var1 = new DataOperation();//.. generate date for sql
//		
//		for (int i = 0; i < instanceData.size(); i++) {
//			System.out.println(instanceData.get(i).getColumnName());
//			System.out.println(instanceData.get(i).getValue());
//			columns = columns + instanceData.get(i).getColumnName() + ",";
//			values = values + instanceData.get(i).getValue() + ",";
//		}
//		
//		columns = columns.substring(0, columns.length() - 1);
//		values = values.substring(0, values.length() - 1);
//		
//		String s = "insert itSector(date," + columns + ") values(" + var1.getDate("YYYYMMdd") + "," + values + ")";	
//		System.out.println("buildRequest ...complete");
//		return s;
//	}
}
