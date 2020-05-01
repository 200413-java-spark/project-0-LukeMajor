package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlDBFill {
	private SqlSource sqlSource = SqlSource.getInstance();
	ArrayList<String> dataset = new ArrayList<>();
	String header;
	StringBuilder sql = new StringBuilder("");
	
	public SqlDBFill () {
		
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
