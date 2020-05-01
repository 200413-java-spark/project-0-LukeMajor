package com.github.lukemajor.tower.tools;

/**
 * This class is a factory that creates the correct test objects based on user input.
 * The input options are Openness / e, Conscientiousness / c , Extraversion / e , 
 * Agreeableness / a, and Neuroticism. 
 */

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import io.Control;
import io.SqlSource;

public class Tester {
	private File opennessQuestions = new File("OpennessStatements.txt");
	private File conscientiousnessQuestions = new File("ConscientiousnessStatements.txt");
	private File agreeablenessQuestions = new File("AgreeablenessStatements.txt");
	private File extraversionQuestions = new File("ExtraversionStatements.txt");
	private File neuroticismQuestions = new File("NeuroticismStatements.txt");
	private String[] OceanScale = {"I completely disagree", "I moderately disagree", "I niether agree nor disagree",
			" I moderately agree", "I completely agree" };
	private SqlSource sqlSource = SqlSource.getInstance();
	
	public Tester(String[] args ) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("openness") || args[i].equalsIgnoreCase("o")) {
				
		    	//openness quiz
		    	PointScale openness = new PointScale(opennessQuestions, OceanScale);
		    	ArrayList<Integer> opennessResults = openness.measure();
		    	getData(sqlSource, "openness", "e", opennessResults);
			} else if (args[i].equalsIgnoreCase("openness") || args[i].equalsIgnoreCase("o")) {
				
		    	//conscientiousness quiz
		    	PointScale conscientiousness = new PointScale(conscientiousnessQuestions, OceanScale);
		    	ArrayList<Integer> conscientiousnessResults = conscientiousness.measure();
		    	getData(sqlSource, "conscientiousness", "c", conscientiousnessResults);
			} else if (args[i].equalsIgnoreCase("conscientiousness") || args[i].equalsIgnoreCase("c")) {
				
		    	//agreeableness quiz
		    	PointScale agreeableness = new PointScale(agreeablenessQuestions, OceanScale);
		    	ArrayList<Integer> agreeablenessResults = agreeableness.measure();
		    	getData(sqlSource, "agreeableness", "a", agreeablenessResults);
			} else if (args[i].equalsIgnoreCase("agreeableness") || args[i].equalsIgnoreCase("a")) {
				
		    	//extraversion quiz
		    	PointScale extraversion = new PointScale(extraversionQuestions, OceanScale);
		    	ArrayList<Integer> extraversionResults = extraversion.measure();
		    	getData(sqlSource, "extraversion", "e", extraversionResults);
			} else if (args[i].equalsIgnoreCase("extraversion") || args[i].equalsIgnoreCase("e")) {
				
		    	//neuroticism quiz
		    	PointScale neuroticism = new PointScale(neuroticismQuestions, OceanScale);
		    	ArrayList<Integer> neuroticismResults = neuroticism.measure();
		    	getData(sqlSource, "neuroticism", "n", neuroticismResults);
			}
		}
		
	}
	
	private String getData(SqlSource sqlSource, String test, String testKey, ArrayList<Integer> testResults) {
		
		ArrayList<String> dataset = new ArrayList<>();
    	String sql;
    	String sqlRequest;
    	int score = 20;
    	double avgScore = 0;
		int lowerScore = 0;
		double sameScore = 0;
		int allScores = 0;
    	
		
    	if (test.equals("openness")) {
			score += (testResults.get(0) + testResults.get(2) + testResults.get(4) + testResults.get(6)
			 + testResults.get(7) + testResults.get(8) + testResults.get(9)) - (testResults.get(1)
			 + testResults.get(3) + testResults.get(6));
			//prepare new data for DB
			sql = "INSERT INTO " + test + " (age,gender," + testKey + "1," + testKey + "2," + testKey + "3," + testKey
					+ "4," + testKey + "5," + testKey + "6," + testKey + "7," + testKey + "8," + testKey + "9,"
					+ testKey + "10) VALUES (" + Control.AGE + ", " + Control.GENDER + ", "
					+ testResults.toString().replace("[", "").replace("]", ")") + "; \n" + "Update " + test
					+ " Set score = 8 + ((o1 +o3 + o5 +o7 + o8 + o9 + o10) - (e2 + e4 + e6));\n";
    	} else if (test.equals("conscientiousness")) {
    		score += (testResults.get(0) + testResults.get(2) + testResults.get(4) + testResults.get(6)
			+ testResults.get(8) + testResults.get(9)) - (testResults.get(1)
			 + testResults.get(3) + testResults.get(5) + testResults.get(7));
			//prepare new data for DB
			sql = "INSERT INTO " + test + " (age,gender," + testKey + "1," + testKey + "2," + testKey + "3," + testKey
					+ "4," + testKey + "5," + testKey + "6," + testKey + "7," + testKey + "8," + testKey + "9,"
					+ testKey + "10) VALUES (" + Control.AGE + ", " + Control.GENDER + ", "
					+ testResults.toString().replace("[", "").replace("]", ")") + "; \n" + "Update " + test
					+ " Set score = 14 + ((c1 +c3 + c5 +c7 +c9 + c10) - (c2 + o4 + o6 +o8));\n";
		} else if (test.equals("extraversion")) {
			for (int i = 0; i < testResults.size(); i++) {
				if (i % 2 == 0) {
					score += testResults.get(i);
				} else {
					score -= testResults.get(i);
				}
			}
			//prepare new data for DB
			sql = "INSERT INTO " + test + " (age,gender," + testKey + "1," + testKey + "2," + testKey + "3," + testKey
					+ "4," + testKey + "5," + testKey + "6," + testKey + "7," + testKey + "8," + testKey + "9,"
					+ testKey + "10) VALUES (" + Control.AGE + ", " + Control.GENDER + ", "
					+ testResults.toString().replace("[", "").replace("]", ")") + "; \n" + "Update " + test
					+ " Set score = 20 + ((e1 +e3 + e5 +e7 +e9) - (e2 + e4 + e6 +e8 + e10));\n";
		} else if (test.equals("agreeableness")) {
			score += (testResults.get(0) + testResults.get(2) + testResults.get(4) + testResults.get(6)
			+ testResults.get(8) + testResults.get(9)) - (testResults.get(1)
			 + testResults.get(3) + testResults.get(5) + testResults.get(7));
			//prepare new data for DB
			sql = "INSERT INTO " + test + " (age,gender," + testKey + "1," + testKey + "2," + testKey + "3," + testKey
					+ "4," + testKey + "5," + testKey + "6," + testKey + "7," + testKey + "8," + testKey + "9,"
					+ testKey + "10) VALUES (" + Control.AGE + ", " + Control.GENDER + ", "
					+ testResults.toString().replace("[", "").replace("]", ")") + "; \n" + "Update " + test
					+ " Set score = 14 + ((a1 +a3 + a5 +a7 +a9 + a10) - (a2 + a4 + a6 +a8));\n";
		} else if (test.equals("neuroticism")) {
			score += (testResults.get(0) + testResults.get(2) + testResults.get(4) + testResults.get(5)
			+ testResults.get(6) + testResults.get(7) + testResults.get(8) + testResults.get(9)) - (testResults.get(1)
			 + testResults.get(3));
			//prepare new data for DB
			sql = "INSERT INTO " + test + " (age,gender," + testKey + "1," + testKey + "2," + testKey + "3," + testKey
					+ "4," + testKey + "5," + testKey + "6," + testKey + "7," + testKey + "8," + testKey + "9,"
					+ testKey + "10) VALUES (" + Control.AGE + ", " + Control.GENDER + ", "
					+ testResults.toString().replace("[", "").replace("]", ")") + "; \n" + "Update " + test
					+ " Set score = 2 + ((n1 +n3 + n5 +n6 +n7 + n8 +n9 +n10) - (e2 + e4));\n";
		} else {
			sql = "";
		}
		
		sqlRequest = "select AVG(score) as AV from " + test + ";\n"
    			+ "select Count(score) as lowerScore from " + test + " where score < " + score + ";\n"
    			+ "select Count(score) as sameScore from " + test + " where score = " + score + ";\n"
    			+ "select Count(score) as allScores from " + test + ";\n";
    	
    	//add row to the database
		try (
	    	    Connection connection = sqlSource.getConnection();
	    	    PreparedStatement statement = connection.prepareStatement(sql);
	    	){
	    		statement.execute();	
	    	} catch (SQLException ex) {
	    	    System.out.println(ex);
	    	    
	    	} 
		
		//retrieve cool data
		try (
	    	    Connection connection = sqlSource.getConnection();
	    	    Statement statement = connection.createStatement();
	    	){
			System.out.print(sqlRequest);
			ResultSet rs = statement.executeQuery("select AVG(score) as AV from " + test + ";\n");	
			rs.next() ;
			avgScore = rs.getDouble("AV");
			
			rs = statement.executeQuery("select Count(score) as lowerScore from " + test + " where score < " + score + ";\n");
			rs.next();
			lowerScore = rs.getInt("lowerScore");
			
			rs = statement.executeQuery("select Count(score) as sameScore from " + test + " where score = " + score + ";\n");
			rs.next();
			sameScore = rs.getDouble("sameScore");
			
			rs = statement.executeQuery("select Count(score) as allScores from " + test + ";\n");
			rs.next();
			allScores = rs.getInt("allScores");
				
	    	} catch (SQLException ex) {
	    	    System.out.println(ex);
	    	    
	    	} 
		
		double percentile = (100/allScores)*(lowerScore + 0.5 *sameScore);
		System.out.println(percentile);
		
		
		return "data";
	}

}
