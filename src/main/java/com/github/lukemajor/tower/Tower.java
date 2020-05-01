package com.github.lukemajor.tower;



/**
 * 
 * @author LukeT
 * @version 0.1.0
 * This program creates a Big 5 personality test for the user. It should be easily extendible
 * to create other tests as well
 *
 */

import com.github.lukemajor.tower.tools.Tester;

import io.Control;
import io.SqlDBFill;

public class Tower {
    public static void main(String[] args) {
    	
    	
    	if (args[0].equalsIgnoreCase("control")) {
    		new Control(args);
    	} else if (args[0].equalsIgnoreCase("measure")) {
    		new Tester(args);
    	} else if  (args[0].equalsIgnoreCase("fill")) {
    		new SqlDBFill(args);
    	} else {
    		System.out.println("###################################################################");
    		System.out.println("Tower is a personal psychometric tool. The current version measures");
    		System.out.println("the user's Big5 personality traits, the most scientifically valid  ");
    		System.out.println("personality metric. Be sure to look up what your traits corelate with");
    		System.out.println("after taking the tests.");
    		System.out.println("To get started use the comand measure [Extraversion/Agreeableness--");
    		System.out.println("--Openness/Neurotocism/Conscientiousness] you may specify more than ");
    		System.out.println("one and use the first letters instead of the full names.");
    		System.out.println("###################################################################");
    	} 
    	
    	
    }

}