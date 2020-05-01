package io;

/**
 * This class sets controls for more interesting results, namely by age and gender.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Control {
	public static int AGE;
	public static int GENDER;
	
	public Control(String[] args) {
		for (int i = 0; i< args.length; i++) {
			if (args[i].equalsIgnoreCase("age")) {
				try {
					AGE = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e) {
					System.out.println("Format is: age [#]");
					break;
				}
				
			} else if (args[i].equalsIgnoreCase("gender")) {
				if (args[i+1].equalsIgnoreCase("male") || equals("1")) {
					GENDER = 1;
				} else if (args[i+1].equalsIgnoreCase("female") || equals("2")) {
					GENDER = 2;
				} else if (args[i+1].equalsIgnoreCase("other") || equals("3")) {
					GENDER = 3;
				} else if (args[i].equalsIgnoreCase("help")) {
					System.out.println("Use control to set controls for age and gender./n"
							+ "EXAMPLE: control age 28 gender other");
				} else {
				System.out.println("Format is: gender [male/female/other/1/2/3]");
				break;
				} 
			} 
			if (AGE != 0) {
				try (PrintWriter controlWriter = new PrintWriter( new FileWriter(".AGE"))) {
					controlWriter.println(AGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (GENDER != 0) {
				try (PrintWriter controlWriter = new PrintWriter( new FileWriter(".GENDER"))) {
					controlWriter.println(GENDER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
