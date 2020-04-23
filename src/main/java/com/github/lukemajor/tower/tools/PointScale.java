package com.github.lukemajor.tower.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointScale {
	ArrayList<String> questions = new ArrayList<>();
	int scale;
	BufferedReader source;
	ArrayList<String> answers;
	
	public PointScale() {
		
	}
	
	//read questions from a file
	public void createScale() {
		try {
			source = new BufferedReader( new FileReader("Big5ExtraversionStatements.txt"));
			String line = source.readLine();
			while (line != null) {
				questions.add(line);
				line = source.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String question : questions) {
			System.out.println(question);
		}
	}
	
	
	
	
}
