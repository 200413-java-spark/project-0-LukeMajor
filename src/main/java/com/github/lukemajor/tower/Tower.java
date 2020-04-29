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

public class Tower {
    public static void main(String[] args) {
    	
    	/*
    	//Personality quiz
    	File questions = new File("Big5ExtraversionStatements.txt");
    	String[] scale = {"I completely disagree", "I moderately disagree", "I niether agree nor disagree",
    			" I moderately agree", "I completely agree" };
    	PointScale extraversion = new PointScale(questions, scale);
    	ArrayList<Integer> results = extraversion.measure();
    	
    	String[] identity = { "Please state your nickname:", "Please state your Age:",
    			"Please state your race:", "Please state your sex:" };
    	
    	FillTest id = new FillTest();
    	id.setQuestions(identity);
    	id.test();
    	
    	//transform data
    	results = 
    	
    	//output results. perhaps a list for every category in a big list so more categories can be added?
    	*/
    	
    	String url = System.getProperty("database.url", "jdbc:postgresql://192.168.99.100:5432/dbluke");
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
				row = "insert into Extraversion values (" + row + ")\n";
				sql.append(row);
			}
			
			
			//add rows to the database
			try (
		    	    Connection connection = DriverManager.getConnection(url, username, password);
		    	   // PreparedStatement statement = connection.prepareStatement(sql.toString());
		    	){
		    		//statement.execute();
		    			
		    		}
		    	    } catch (SQLException ex) {
		    	    System.out.println(ex);
		    	    
		    	} catch (IOException e) {
			e.printStackTrace();
		    	}
    	
    }
    
    static void addRow() {
    	String url = System.getProperty("database.url", "jdbc:postgresql://192.168.99.100:5432/lukedb");
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
				row = "insert into Extraversion values (" + row + ")\n";
				sql.append(row);
			}
			
			
			//add rows to the database
			try (
		    	    Connection connection = DriverManager.getConnection(url, username, password);
		    	   // PreparedStatement statement = connection.prepareStatement(sql.toString());
		    	){
		    		//statement.execute();
		    			
		    		}
		    	    } catch (SQLException ex) {
		    	    System.out.println(ex);
		    	    
		    	} catch (IOException e) {
			e.printStackTrace();
		}
		

    	
	}


}