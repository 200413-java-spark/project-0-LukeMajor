package com.github.lukemajor.tower.tools;

import java.io.File;
import java.util.ArrayList;

public class MultipleChoice {
	ArrayList<String> questions = new ArrayList<>();
	ArrayList<ArrayList<String>> choices = new ArrayList<>();
	
	public MultipleChoice(ArrayList<String> questions, ArrayList<ArrayList<String>> choices) {
		this.questions = questions;
		this.choices = choices;
	}
	
	public ArrayList<ArrayList<String>> choicesFromFile(File file) {
		return choices;
	}

}
