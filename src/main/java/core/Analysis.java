package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import database.MySQLDatabase2;
import database.MySQLExe;
import util.DataOperation;

public class Analysis {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
//		readFromDB();
//		sendCompanyDataToDatabase();
		sendVacanciesDataToDatabase();
		
//		try {
//			MySQLDatabase2.conn();
//			MySQLDatabase2.createDB();
//			//String name, int vacancyId,  int publicationTime, int lastChangeTime, int responsesCount, int totalResponsesCount, String company, String requirements, String skill, String location
//			MySQLDatabase2.writeDB2("Java", 45454, 111, 45454, 55, 66, "Java", "888", "6565", "6669");
//			MySQLDatabase2.closeDB();
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void readFromDB() throws IOException {
		new MySQLExe();
	}
	
	public static void sendCompanyDataToDatabase() throws IOException {
		long t = System.currentTimeMillis();
		
		DataOperation dataOper = new DataOperation();
		String s = "";
		String result = "";
		//String[] arr = {"02052023.001","02052023.002","02052023.003","02052023.004","02052023.005","02052023.006","02052023.007","02052023.008","02052023.009","02052023.010","02052023.011","02052023.012","02052023.013","02052023.014","02052023.015"};

		String[] arr = {"30042023.001","30042023.002","30042023.003","30042023.004","30042023.005","30042023.006","30042023.007","30042023.008","30042023.009","30042023.010","30042023.011","30042023.012","30042023.013","30042023.014","30042023.015","30042023.016","30042023.017","30042023.018","30042023.019","30042023.020","30042023.021","30042023.022","30042023.023","30042023.024","30042023.025","30042023.026","30042023.027","30042023.028","30042023.029","30042023.030","30042023.031","30042023.032","30042023.033","30042023.034","30042023.035","30042023.036","30042023.037","30042023.038","30042023.039","30042023.040","30042023.041","30042023.042","30042023.043","30042023.044","30042023.045","30042023.046","30042023.047","30042023.048","30042023.049","30042023.050","30042023.051","30042023.052","30042023.053","30042023.054","30042023.055","30042023.056","30042023.057","30042023.058","30042023.059","30042023.060","30042023.061","30042023.062","30042023.063","30042023.064","30042023.065","30042023.066","30042023.067","30042023.068","30042023.069","30042023.070","30042023.071","30042023.072","30042023.073","30042023.074","30042023.075","30042023.076","30042023.077","30042023.078","30042023.079","30042023.080","30042023.081"};
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i);
			//s = s + dataOper.readDataFromFile("C:\\Users\\user\\Desktop\\eclipse-workspace\\" + arr[i]);
			s = s + dataOper.readDataFromFile("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\30042023\\" + arr[i]);
		}
		
//		try {
////			s = dataOper.readDataFromFile("C:\\Users\\user\\Desktop\\eclipse-workspace\\02052023.002");
//			//s = new MySQLExe().readDataFromDataBase();
//		}
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println(s.length());
		JSONObject jobj = new JSONObject(s.replaceAll("\n", ""));
		JSONArray vArray = new JSONArray(jobj.getJSONArray("javaVac").toString());
		
		System.out.println(vArray.length());
		
		for (int i = 0; i < vArray.length(); i++) {
			
			JSONObject jobj2 = new JSONObject(vArray.get(i).toString());
			JSONObject jobj3 = new JSONObject(jobj2.getJSONObject("vacancySearchResult").toString());
	
			JSONArray vArray2 = new JSONArray(jobj3.getJSONArray("vacancies").toString());
			
			
			for (int j = 0; j < vArray2.length(); j++) {
				try {
				result = result + vArray2.get(j).toString() + ";";
				JSONObject jobj4 = new JSONObject(vArray2.get(j).toString());
				JSONObject jobj5 = new JSONObject(jobj4.getJSONObject("company").toString());
				JSONObject jobj6 = new JSONObject(jobj4.getJSONObject("area").toString());
				
				try {
					MySQLDatabase2.conn();
					MySQLDatabase2.createDB();
					MySQLDatabase2.writeDB(jobj5.getString("name"), jobj5.getInt("id"), jobj5.getString("companySiteUrl"), jobj6.getString("name"), "");
					MySQLDatabase2.closeDB();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(jobj5.getString("name"));
				System.out.println(jobj5.getString("companySiteUrl"));
				System.out.println(jobj5.getInt("id"));
				
				System.out.println(jobj6.getString("name"));
				System.out.println();
				
				} catch(org.json.JSONException e) {
					System.out.println("JSONObject not found");
					
				}
			}

			System.out.println(vArray2.length());
		}
		
//		try {
//			MySQLDatabase2.conn();
//			MySQLDatabase2.createDB();
//			MySQLDatabase2.writeDB();
//			MySQLDatabase2.closeDB();
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//dataOper.writeDataToFile("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\resultJavaDirect2.csv", result);

		long t2 = (System.currentTimeMillis() - t) / 1000;
		System.out.println("time: " + t2 + " sec.");
	}
	
	public static void sendVacanciesDataToDatabase() throws IOException {
		long t = System.currentTimeMillis();
		
		int vacancyId,timestamp,timestamp1 = 0,responsesCount,totalResponsesCount;
		String req = "", skill = "";
		
		DataOperation dataOper = new DataOperation();
		int volume = dataOper.getListFilesFromDirectory("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\temp2\\").length;
		String s = "";
		String result = "";
		
		for (int i = 0; i < volume; i++) {
			System.out.println(i);
			s = s + dataOper.readDataFromFile("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\temp2\\output" + i + ".txt");
		}
		
		System.out.println(s.length());
		JSONObject jobj = new JSONObject(s.replaceAll("\n", ""));
		JSONArray vArray = new JSONArray(jobj.getJSONArray("javaVac").toString());
		
		System.out.println(vArray.length());
		
		for (int i = 0; i < vArray.length(); i++) {
			
			JSONObject jobj2 = new JSONObject(vArray.get(i).toString());
			JSONObject jobj3 = new JSONObject(jobj2.getJSONObject("vacancySearchResult").toString());
	
			JSONArray vArray2 = new JSONArray(jobj3.getJSONArray("vacancies").toString());
			
			
			for (int j = 0; j < vArray2.length(); j++) {
				try {
				result = result + vArray2.get(j).toString() + ";";
				JSONObject jobj4 = new JSONObject(vArray2.get(j).toString());
				JSONObject jobj5 = new JSONObject(jobj4.getJSONObject("company").toString());
				JSONObject jobj6 = new JSONObject(jobj4.getJSONObject("area").toString());
				
				//JSONObject jobj10 = new JSONObject(jobj4.getJSONObject("company").toString());
				System.out.println("vacName: " + jobj4.getString("name"));
				System.out.println(jobj4.getInt("responsesCount"));
				System.out.println(jobj4.getInt("totalResponsesCount"));
				System.out.println(jobj4.getInt("vacancyId"));
				
				JSONObject jobj10 = new JSONObject(jobj4.getJSONObject("snippet").toString());
				
				try {
					skill = jobj10.getString("skill");
					req = jobj10.getString("req");
				System.out.println(jobj10.getString("skill"));
				System.out.println(jobj10.getString("req"));
				}
				catch(org.json.JSONException e) {
					System.out.println("JSONObject not found");
					
				}
				
				JSONObject jobj11 = new JSONObject(jobj4.getJSONObject("publicationTime").toString());
				System.out.println(jobj11.getInt("@timestamp"));
				
				try {
				JSONObject jobj12 = new JSONObject(jobj4.getJSONObject("lastChangeTime").toString());
				System.out.println(jobj12.getInt("@timestamp"));
				timestamp1 = jobj12.getInt("@timestamp");
				}
				catch(org.json.JSONException e) {
					System.out.println("JSONObject not found");
					
				}
				
				System.out.println("company: " + jobj5.getString("name"));
				System.out.println("area: " + jobj6.getString("name"));
				
				try {
					MySQLDatabase2.conn();
					MySQLDatabase2.createDB();
					//String name, int vacancyId,  int publicationTime, int lastChangeTime, int responsesCount, int totalResponsesCount, String company, String requirements, String skill, String location
					MySQLDatabase2.writeDB2(jobj4.getString("name"), jobj4.getInt("vacancyId"), jobj11.getInt("@timestamp"), timestamp1, jobj4.getInt("responsesCount"), jobj4.getInt("totalResponsesCount"), jobj5.getString("name"), req, skill, jobj6.getString("name"));
					MySQLDatabase2.closeDB();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println();
				
				} catch(org.json.JSONException e) {
					System.out.println("JSONObject not found");
				}
			}

			System.out.println(vArray2.length());
		}
		
		//dataOper.writeDataToFile("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\resultJavaDirect2.csv", result);

		long t2 = (System.currentTimeMillis() - t) / 1000;
		System.out.println("time: " + t2 + " sec.");
	}

}
