package com.github.lukemajor.tower;

import java.io.BufferedReader;

/**
 * 
 * @author LukeT
 * @version 0.1.0
 * This program creates a Big 5 personality test for the user. It should be easily extendable
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
    	
    	ArrayList<String> dataset = new ArrayList<>();
    	StringBuilder sql = new StringBuilder("");
    	try (Scanner source = new Scanner( new FileReader("TestSheet.csv"))) {
			//source.useDelimiter("\\s*,\\*");
			dataset.add(source.next());
			while (source.hasNext()) {
				dataset.add(source.next());
			}
			for (String row : dataset) {
				row = "insert into Extraversion values (" + row + ")\n";
				sql.append(row);
				//System.out.println(row);
			}
			System.out.print(sql);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    public void addRow() {
    	String url = "jdbc:postgresql://52.15.170.98:5432/lukedb";
    	String username = "luke";
    	String password = "luke";
    	ArrayList<String> dataset = new ArrayList<>();
    	String header;
    	StringBuilder sql = new StringBuilder("");

    	try (
    	    Connection connection = DriverManager.getConnection(url, username, password);
    	    PreparedStatement statement = connection.prepareStatement("blah");
    	){
    		//Uses a scanner to parse the csv into a sql easy-to-use format
    		try (Scanner source = new Scanner( new FileReader("TestSheet.csv"))) {
    			//source.useDelimiter("\\s*,\\*");
    			dataset.add(source.next());
    			while (source.hasNext()) {
    				dataset.add(source.next());
    			}
    			header = dataset.get(0);
    			dataset.remove(0);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		//Add rows to the database
    		for (String row : dataset) {
				row = "insert into Extraversion values (" + row + ")\n";
				sql.append(row);
				
			}
    		
    		
    	    } catch (SQLException ex) {
    	    System.out.println(ex);
    	} 
	}


}