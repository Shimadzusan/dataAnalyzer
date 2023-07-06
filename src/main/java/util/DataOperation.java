package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class DataOperation {
	
	/** description */
	public void createDirectory(String address) throws IOException {
		Path path = Paths.get(address);
		Files.createDirectory(path);
	}
	
	/** description */
	public String[] getListFilesFromDirectory(String address) {
		File folder = new File(address);
		File[] listOfFiles = folder.listFiles();
		String[] arrayFiles = new String[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		    arrayFiles[i] = listOfFiles[i].getName();
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		
		return arrayFiles;
	}
	
	/** description */
	public boolean deleteDirectory(String address) {
		try {
			FileUtils.deleteDirectory(new File(address));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String readDataFromFile(String fileName) throws FileNotFoundException, IOException {
		String result = "";
		String line;
		int bufferSize = 16384;
	        // defaultCharBufferSize = 8192; or 8k
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName), bufferSize)) {  
	    	while ((line = br.readLine()) != null) {
	    		result = result + line + "\n";
	        }
	    }
		return result;
	}
	
	 public String readDataFromFile2(String fileName) throws IOException {
		 String result = "";
		 List<String> lines = Files.readAllLines(Paths.get(fileName),StandardCharsets.UTF_8);
	     for (int i = 0; i < lines.size(); i++) {
			result = result + lines.get(i);
		}
		return result;
	 }
	
	public boolean writeDataToFile(String fileName, String text) throws IOException {
//		try (FileWriter fw = new FileWriter(fileName);
//			       BufferedWriter bw = new BufferedWriter(fw)) {
//			      bw.write(text);
//			      bw.newLine(); // add new line, System.lineSeparator()
//			  }

				 // append mode
			  try (FileWriter fw = new FileWriter(fileName, false);
			       BufferedWriter bw = new BufferedWriter(fw)) {
			      bw.write(text);
			      bw.newLine();
			  }
		return true;
	}

	/* example YYYYMMDD for input */
	public String getDate(String pattern) {
		Calendar calendar = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat(pattern);
		String date = formatter.format(calendar.getTime());
		return date;
	}
	
	/** ..description lengthPart(bytes)*/
	public boolean divideFile(String addressFile, int lengthPart, String destination) {
		//String inputFileName = "02052023.json";
	    List<String> outputFiles = new ArrayList<>(); //{"output1.txt", "output2.txt", "output3.txt", "output4.txt", "output5.txt"};

	      // Define the buffer size
	      //int bufferSize = 548576;

	      try (FileInputStream inputStream = new FileInputStream(addressFile)) {
	         System.out.println("size: " + inputStream.available());
	         System.out.println(inputStream.available() / lengthPart);
	         int x = inputStream.available() / lengthPart + 1;
	         for (int i = 0; i < x; i++) {
				outputFiles.add(destination + "output" + i + ".txt");
			}
	    	 byte[] buffer = new byte[lengthPart];
	         int bytesRead = 0;
	         int fileNumber = 1;

	         // Create output files and write data to them
	         for (String outputFile : outputFiles) {
	            FileOutputStream outputStream = new FileOutputStream(outputFile);
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	               outputStream.write(buffer, 0, bytesRead);
	               if (outputStream.getChannel().size() >= lengthPart) {
	                  break;
	               }
	            }
	            outputStream.close();
	            System.out.println("File " + fileNumber + " created.");
	            fileNumber++;
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		return true;
	}
}