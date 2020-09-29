package conversions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

public class Run {
	
	public static void main(String[]args) {
		Converter convert = new Converter();
		
		java.net.URL url = Run.class.getResource("test-cases.txt");
		
		ArrayList<String> statements = readFile(url.getPath());
		System.out.println("-------------------------------- Predefined Test Cases --------------------------------");
		convert(statements, convert);
		System.out.println("-------------------------------- User Defined Test Cases --------------------------------");
		System.out.println("12451213456789 = " + convert.convertToWords(12451213456789L));
		System.out.println("579656167854452 = " + convert.convertToWords(579656167854452L));
		System.out.println("579656167 = " + convert.convertToWords(579656167));
		System.out.println("12451213456789 = " +convert.convertToWords("12451213456789"));
		System.out.println("10 = " + convert.convertToWords("10"));
		System.out.println("9 = " +convert.convertToWords("9"));
		System.out.println("53 = " +convert.convertToWords("53"));
		System.out.println("13 = " +convert.convertToWords("13"));
		
		/* Extend from trillion to quadrillion*/
		String [] array = new String[Converter.getSpecial().length+1];
		for(int i=0; i < Converter.getSpecial().length;i++ ) {
			array[i] = Converter.getSpecial()[i];
		}
		array[Converter.getSpecial().length] = "quadrillion";
		Converter.setSpecial(array);
		
		
		System.out.println("-------------------------------- Extended Number Scope User Defined Test Cases --------------------------------");
		System.out.println("1245121345678977 = " +convert.convertToWords(1245121345678977L));
		System.out.println("983647277273111114 = " +convert.convertToWords(983647277273111114L));
		
		
	}
	

	public static ArrayList<String> readFile(String filepath) {//"C:/Users/Nikita/eclipse-workspace/ninety-one-task/src/test-cases.txt"
		ArrayList<String> statements = new ArrayList<String>();
		try {
		      File myObj = new File(filepath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        //System.out.println(data);
		        String[] words =data.split(" ");
		        String number ="";
		        boolean numberFound = false;
		        for(int i=0; i<words.length;i++) {
		        	for(int j=0; j<words[i].length();j++) { //atleast 1 character in word is a number
		        		if(Character.isDigit(words[i].charAt(j))) {
		        			numberFound  = true;
		        		}
		        	}
		        	
		        	if(numberFound) {
		        		number += words[i];
		        		numberFound = false;
		        	}
		        	
		        }
		        
		        statements.add(number); //1 sentence
		       
		      }
		      myReader.close();
		} 
		catch (FileNotFoundException e) 
		{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return statements;

	}
	
	public static void convert(ArrayList<String> statements, Converter convert) {
		for(int i=0; i<statements.size();i++) {
			String str = convert.convertToWords(statements.get(i));
			if(!str.equals("")) {
				System.out.println(statements.get(i) + " = " + str);
			}
				
		}
	}
	
	
}
