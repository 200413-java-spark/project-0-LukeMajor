package com.github.lukemajor.tower;

import java.util.Scanner;

import com.github.lukemajor.tower.tools.PointScale;

public class Tower {
    public static void main(String[] args) {
        /**
         * Tower is a psychometric tool to help one better know oneself. Version 1 will offer a
         * Big5 personality measure and give back percentiles.
         */
    	
    	//while loop allows for multiple commands
    	boolean waiting = true;
    	Scanner userInput = new Scanner(System.in);
    	String command;
    	PointScale extraversion = new PointScale();
    	extraversion.createScale();
    	
    	
    	/*
    	while (waiting) {
    		command = userInput.nextLine();
    		//System.out.println(write);
    	
    		if (command.contentEquals("exit")) {
    			waiting = false;
    		}
    	} */
    	
    	//ask for ID and controls
    	System.out.println("Please state your nickname:");
    	String name = userInput.nextLine();
    	
    	System.out.println("Please state your Age:");
    	int age = Integer.parseInt(userInput.nextLine());
    	
    	System.out.println("Please state your race:");
    	String race = userInput.nextLine();
    	
    	System.out.println("Please state your sex:");
    	String sex = userInput.nextLine();
    	
    	System.out.println("Nickname: " + name + " Age: " + age + " Race: " + race + " Sex: " + sex);
    	
    	//ask for answers to personality questions
    	System.out.println("Input a number to agree or disagree with the following statement about yourself:\n"
    			+ "\"I am the life of the party.\"\n"
    			+ "[1: I disagree completely]\n"
    			+ "[2: I disagree moderately]\n"
    			+ "[3: I niether agree nor disagree]\n"
    			+ "[4: I agree moderately]\n"
    			+ "[5: I agree Completely]");
    	int e1 = Integer.parseInt(userInput.nextLine());
    	
    	//output answers
    	System.out.println("Extraversion 1 answer: " + e1);
    	
    	//transform data
    	
    	//output results. perhaps a list for every category in a big list so more categories can be added?
    	
    	
    	
    }

}