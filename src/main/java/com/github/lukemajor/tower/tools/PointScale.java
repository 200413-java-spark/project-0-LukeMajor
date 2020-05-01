package com.github.lukemajor.tower.tools;

/**
 * 
 * @author LukeT
 * @version 0.1.0
 *This class can take in lists of X number of questions and Y number of answers to create a Y point scale measure. 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PointScale {
	private ArrayList<String> questions = new ArrayList<>();
	private ArrayList<String> scale = new ArrayList<>();
	private BufferedReader source;
	private ArrayList<Integer> measure = new ArrayList<>();
	
	public PointScale() {
		
	}
	
	public PointScale(File questions, String[] scale) {
		setQuestions(questions);
		setScale(scale);
	}
	
	public PointScale(File questions, ArrayList<String> scale) {
		setQuestions(questions);
		setScale(scale);
	}
	
	/**
	 * Sets the questions to be asked via a file.
	 * The questions should be separated by a line break.
	 * @param file
	 */
	public void setQuestions(File file) {
		try (BufferedReader source = new BufferedReader( new FileReader(file))) {
			String line = source.readLine();
			while (line != null) {
				questions.add(line);
				line = source.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//set the point-scale with an array of strings
	public void setScale(String [] scale) {
		for (int i = 0; i < scale.length; i++) {
			this.scale.add(scale[i]);
		}
	}
	
	//sets the point-scale with a list of strings
	public void setScale(ArrayList<String> scale) {
		this.scale = scale;
	}
	
	
	//initiates the measure and returns the results
	public ArrayList<Integer> measure() {
		Scanner userInput = new Scanner(System.in);
		
		for (String question : questions) {
			System.out.println("Input a number to answer the following statement or question:\n"
	    			+ "\"" + question + "\""); 
			for (String answer : scale) {
				System.out.println("[" + (scale.indexOf(answer) + 1) + ": " + answer + "]");
			}
			measure.add(Integer.parseInt(userInput.nextLine()));
		}
		
		return measure;
	}
	
	//Override of the toString method to return the resulting measurement, if it exists
	public String toString() {
		if (measure.isEmpty()) {
			return "No measurement";
		} else {
			return measure.toString();
		}
	}
	
	
	
}
