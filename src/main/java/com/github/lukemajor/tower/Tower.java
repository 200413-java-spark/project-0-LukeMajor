package com.github.lukemajor.tower;

import java.io.BufferedReader;

/**
 * 
 * @author LukeT
 * @version 0.1.0
 * This program creates a Big 5 personality test for the user. It should be easily extendible
 * to create other tests as well
 *
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.github.lukemajor.tower.tools.FillTest;
import com.github.lukemajor.tower.tools.PointScale;
import com.github.lukemajor.tower.tools.Tester;

import io.Control;
import io.SqlDBFill;

public class Tower {
    public static void main(String[] args) {
    	
    	
    	if (args[0].equalsIgnoreCase("control")) {
    		Control control = new Control(args);
    	} else if (args[0].equalsIgnoreCase("measure")) {
    		Tester tester = new Tester(args);
    	} else if  (args[0].equalsIgnoreCase("fill")) {
    		try {
    			SqlDBFill extra = new SqlDBFill();
    			extra.uploadCsv(new File("ESheet.csv"), "Extraversion");
    		} finally {
    			System.out.println("oops");
    		}
    	}
    	
    	//addRows();
    	
    	System.out.println("Your extraversion quotient is: ");
    	
    }
    
    static void addRows() {
    	String url = System.getProperty("database.url", "jdbc:postgresql://52.15.170.98:5432/luke");
    	String username = System.getProperty("databse.username", "luke");
    	String password = System.getProperty("database.password", "luke");
    	ArrayList<String> dataset = new ArrayList<>();
    	String header;
    	StringBuilder sql = new StringBuilder("");
    	
    	//Uses a scanner to parse the csv into a sql easy-to-use format
		try (Scanner source = new Scanner( new FileReader("TestSheet.csv"))) {
			
			//parse the csv
			dataset.add(source.next());
			while (source.hasNext()) {
				dataset.add(source.next());
			}
			header = dataset.get(0);
			dataset.remove(0);
			
			//Add rows to the sql statement
			for (String row : dataset) {
				row = "INSERT INTO extraversion (race,age,engnat,gender,hand,E1,E2,E3,E4,E5,E6,E7,E8,E9,E10) VALUES (" + row + ");\n";
				sql.append(row);
			}
			
			//add rows to the database
			try (
		    	    Connection connection = DriverManager.getConnection(url, username, password);
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