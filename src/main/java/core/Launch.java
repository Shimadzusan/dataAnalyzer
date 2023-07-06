package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import util.DataOperation;

public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * 1 method for analysis java total vac
		 * 2 method for analsis all vac
		 * */
		
		new JavaTotalVacHeadHunterAnalysis();
		new TotalVacHeadHunterAnalysis();
		
		System.out.println("+");
		//DataOperation dop = new DataOperation();
//		dop.divideFile("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\resultHeadHunterJava.csv", 548576, "temp\\");
		
//		String[] s = dop.getListFilesFromDirectory("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\temp");
//		System.out.println(s.length);
//		String text = "";
//		for (int i = 0; i < s.length; i++) {
//			text = text + dop.readDataFromFile("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\temp\\" + "output" + i + ".txt");
//			System.out.println(i);
//		}
//		
//		String text2 = text.replaceAll("20230609;", "{\"javaVac\":[");
//		text2 = text2.replaceAll("};\\{", "},{");
//		text2 = text2 + "]}";
//		text2 = text2.replaceAll("\\\\\"", "");
//		text2 = text2.replaceAll("\n", "");
//		dop.writeDataToFile("result.json", text2);
		
//		dop.deleteDirectory("");
		
		// divide comlete json for reading and sending to db
		//dop.divideFile("C:\\Users\\user\\eclipse-workspace\\dataAnalyzer\\resultHeadHunterJava2.csv", 548576, "temp2\\");
		
		// send data from json to db
		
		
		//Analysis.sendVacanciesDataToDatabase();
	}

}
