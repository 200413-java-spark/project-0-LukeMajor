package io;

/**
 * This class isn't mentioned in the help. It's a convenience to populate my DB until I make a server side 
 * app to handle it away from the normal user.
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlDBFill {
	private SqlSource sqlSource = SqlSource.getInstance();
	ArrayList<String> dataset = new ArrayList<>();
	String header;
	StringBuilder sql = new StringBuilder("");
	
	public SqlDBFill (String[] args) {
		if (args[1].equalsIgnoreCase("Openness")) {
			uploadCsv(new File("src/main/Resources/OSheet.csv"), "Openness");
		} else if (args[1].equalsIgnoreCase("Conscientiousness")) {
			uploadCsv(new File("src/main/Resources/CSheet.csv"), "Conscientiousness");
		} else if (args[1].equalsIgnoreCase("Extraversion")) {
			uploadCsv(new File("src/main/Resources/ESheet.csv"), "Extraversion");
		} else if (args[1].equalsIgnoreCase("Agreeableness")) {
			uploadCsv(new File("src/main/Resources/ASheet.csv"), "Agreeableness");
		} else if (args[1].equalsIgnoreCase("Neuroticism")) {
			uploadCsv(new File("src/main/Resources/NSheet.csv"), "Neuroticism");
		} else {
			System.out.println("Stay away from this functio unless you know what you are doing!");
		}
	}
	
	
	public void uploadCsv(File file, String title) {
		//Uses a scanner to parse the csv into a sql easy-to-use format
		try (Scanner source = new Scanner( new FileReader(file))) {
			
			//parse the csv
			dataset.add(source.next());
			while (source.hasNext()) {
				dataset.add(source.next());
			}
			header = dataset.get(0);
			dataset.remove(0);
			
			//Add rows to the sql statement
			for (String row : dataset) {
				row = "INSERT INTO " + title + " VALUES (" + row + ");\n";
				sql.append(row);
			}
			
			//add rows to the database
			try (
		    	    Connection connection = sqlSource.getConnection();
		    	    PreparedStatement statement = connection.prepareStatement(sql.toString());
		    	){
		    		statement.execute();
		    			
		    		}
		    	    } catch (SQLException ex) {
		    	    System.out.println(ex);
		    	    
		    	} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
