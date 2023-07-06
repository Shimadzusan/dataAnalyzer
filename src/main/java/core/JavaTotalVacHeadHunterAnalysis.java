package core;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import database.MySQLDatabase2;
import util.DataOperation;

public class JavaTotalVacHeadHunterAnalysis {

	public JavaTotalVacHeadHunterAnalysis() throws IOException {
		/* here we deploy methods consicvence */
		//String s = "C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\resultHeadHunterJava.csv";
		DataOperation dOper = new DataOperation();
		
//		dOper.createDirectory("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\temporary");
		
//		String addressFile = "C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\resultHeadHunterJava.csv";
//		int lengthPart = 358400;//350KB
//		String destination = "C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\temporary\\";
//		dOper.divideFile(addressFile, lengthPart, destination);
		
		String[] s = dOper.getListFilesFromDirectory("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\temporary");
		System.out.println(s.length);
		int volume = s.length;
		String text = "";
		for (int i = 0; i < s.length; i++) {
			text = text + dOper.readDataFromFile("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\temporary\\" + "output" + i + ".txt");
			System.out.println(i);
		}
		
		String text2 = text.replaceAll("20230627;", "{\"javaVac\":[");
		text2 = text2.replaceAll("};\\{", "},{");
		text2 = text2 + "]}";
		text2 = text2.replaceAll("\\\\\"", "");
		text2 = text2.replaceAll("\n", "");
//		dop.writeDataToFile("result.json", text2);
		
long t = System.currentTimeMillis();
		
		int vacancyId,timestamp,timestamp1 = 0,responsesCount,totalResponsesCount;
		String req = "", skill = "";
		
		DataOperation dataOper = new DataOperation();
		//int volume = dataOper.getListFilesFromDirectory("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\temp2\\").length;
		//String s = "";
		String result = "";
		
//		for (int i = 0; i < volume; i++) {
//			System.out.println(i);
//			s = s + dataOper.readDataFromFile("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\temp2\\output" + i + ".txt");
//		}
		
		System.out.println(text2.length());
		JSONObject jobj = new JSONObject(text2.replaceAll("\n", ""));
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
		
//		dOper.deleteDirectory("C:\\Users\\user\\Desktop\\eclipse-workspace\\webDataCollector\\temporary");
		// clearFile
//		dOper.writeDataToFile(s, "abc");
		
	}
}
