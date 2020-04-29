package com.github.lukemajor.tower.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author LukeT
 * @version 0.1.0
 * This class asks for a name and typical controls, but can be used to create any free-form quiz with an
 * array or list of questions.
 *
 */

public class FillTest {
	
	private ArrayList<String> questions = new ArrayList<>();
	private ArrayList<String> results = new ArrayList<>();
	
	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}
	
	public void setQuestions(String[] questions) {
		for (int i = 0; i < questions.length; i++) {
			this.questions.add(questions[i]);
		}
	}
	
	public FillTest() {
		
	}
	
	public void test() {
		final Scanner userInput = new Scanner(System.in);
		
		for (String question : questions) {
			System.out.println(question);
			results.add(userInput.nextLine());
		}
		
		
	}

}
