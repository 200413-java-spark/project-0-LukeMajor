package com.github.lukemajor.tower;

/**
 * 
 * @author LukeT
 * @version 0.1.0
 * This program creates a Big 5 personality test for the user. It should be easily extendable
 * to create other tests as well
 *
 */

import java.io.File;
import java.util.ArrayList;

import com.github.lukemajor.tower.tools.FillTest;
import com.github.lukemajor.tower.tools.PointScale;

public class Tower {
    public static void main(String[] args) {
    	
    	//Personality quiz
    	File questions = new File("Big5ExtraversionStatements.txt");
    	String[] scale = {"I completely disagree", "I moderately disagree", "I niether agree nor disagree",
    			" I moderately agree", "I completely agree" };
    	PointScale extraversion = new PointScale(questions, scale);
    	ArrayList<Integer> results = extraversion.measure();
    	
    	for (Integer result : results) {
    		System.out.print(result);
    	}
    	System.out.print("\n");
    	
    	String[] identity = { "Please state your nickname:" , "Please state your Age:",
    			"Please state your race:", "Please state your sex:" };
    	
    	FillTest id = new FillTest();
    	id.setQuestions(identity);
    	id.test();
    	
    	//transform data
    	
    	//output results. perhaps a list for every category in a big list so more categories can be added?
    	
    	
    }

}