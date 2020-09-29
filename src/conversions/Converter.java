package conversions;

public class Converter {
	
	private static final String[] units = new String[] {"zero","one","two","three","four","five","six","seven","eight","nine"};
	private static final String [] teens = new String[] {"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
	private static final String [] tens = new String[] {"ten", "twenty", "thirty", "forty", "fifty", "sixty","seventy","eighty","ninety"};
	private static String [] special = new String[] {"hundered", "thousand", "million", "billion", "trillion"}; //powers of thousand (1-5)
	
	public Converter() 
	{			
	
	}

	public String convertToWords(String number) 
	{
		try 
		{
			/*Check the input is a number*/
			long digits = Long.parseLong(number);
			
			int length = number.length(); //stores the length of the number
			
			if (length == 1) //the number to convert is a single digit
			{
				return convertOne(number);
			}
			else if(length == 2) //the number to convert is a two digits
			{
				return convertTwo(number);
			}
			else if(length == 3) //the number to convert is a three digits
			{
				if(number.equals("000"))
					return convertOne(number);
				else
					return convertThree(number);
			}
			else 
			{
				return convertMoreThanThree(number);
			}
			
		}
		catch(NumberFormatException e) 
		{
			
			System.out.println("Number Invalid");
		    //e.printStackTrace();
		    
		      
		}
		return "";
	
	}
	
	public String convertToWords(long number) 
	{
		String str = Long.toString(number);
		return convertToWords(str);
	}
	
	public String convertToWords(int number) 
	{
		String str = Integer.toString(number);
		return convertToWords(str);
	}
	
	
	private String convertMoreThanThree(String number) 
	{
		// TODO Auto-generated method stub
	
		int length = number.length();
		
		String result=""; //number in words
		int power = -1; //1000^1 = 1000, 1000^2 = mil, 1000^3 = billion
		
		/* Break up number into sub sets of threes starting from the right , 12345674 (12-345-674)*/
		for(int endIndex=length; endIndex>=0;endIndex=endIndex) { 

			power++;
			int beginIndex = endIndex - 3;
			if(beginIndex >=0) { //then deal with subsets of length three
				String subset = number.substring(beginIndex, endIndex); //holds subset of the number of size 3
				endIndex = beginIndex; 
				
				if (power==0) { // convertThree adds "hundred" from special array
					result = convertThree(subset)+ " " + result;
				}
				else { //convertThree applied to other elements from special array
					if(!subset.equals("000")){
						result = convertThree(subset)+ " " + special[power] + ", " + result;
					}
					
				}
					
				
			}else {
				
				int overflow = length - (int)(Math.floor(length/3))*3; //calculate overflow (the one subset without three digits)
				
				if(overflow==0) {
					break;
				}else {
					if(overflow==1) { // subset has only one digit
						String subset = number.substring(0,1);
						result = convertOne(subset)+ " " + special[power] + ", " + result;
					}else { //subset has two digits
						String subset = number.substring(0,2);
						result = convertTwo(subset)+ " " + special[power] + ", " + result;
					}
				}
				break;
			}
		
		}
		
		return result;
		
	}

	/*
	 * converts a three digit numbers to corresponding words
	 */
	private String convertThree(String number) 
	{
		// TODO Auto-generated method stub
		
		String last_two_digits = number.substring(1,3); // cannot have 00 as int 
		
		int firstdigit = Integer.parseInt(number.charAt(0)+"");
		int second_digit = Integer.parseInt(number.charAt(1)+"");
		int third_digit = Integer.parseInt(number.charAt(2)+"");
		
		if(firstdigit==0 && second_digit==0 && third_digit==0) { //do not direct to convertOne 
			return "";
		}
		else if(firstdigit == 0 && second_digit == 0) //003 then direct it to convertOne
		{
			return "and " + convertOne(Integer.toString(third_digit));
		}
		else if(firstdigit == 0) { //022 then direct it to convertTwo
			return "and " + convertTwo(last_two_digits);
		}
		else if(last_two_digits.equals("00")) { // three digits of the form 100, 200, 300, ..., 900
			return convertOne(Integer.toString(firstdigit)) + " " + special[0];
		}
		else if(second_digit == 0) // three digits of the form 101-109, 201-209, 301-309, ..., 901-909
		{
				return convertOne(Integer.toString(firstdigit)) + " " + special[0] + " and " + convertOne(Integer.toString(third_digit));
		}
		else { // 111-199, 211-299, 311,399, ... , 911-999
			return convertOne(Integer.toString(firstdigit)) + " " + special[0] + " and "+ convertTwo(last_two_digits); 
		}
	}

	/*
	 * converts a two digit numbers to corresponding words
	 */
	private String convertTwo(String number) 
	{ 
		// TODO Auto-generated method stub
		
		int first_digit = Integer.parseInt(number.charAt(0)+"");
		int second_digit = Integer.parseInt(number.charAt(1)+"");
		
		if(first_digit==0) 
		{ //04 direct to convertOne - long x = 04
			return convertOne(number.charAt(1)+"");
		}
		else if(second_digit == 0) //if the two digit number is of the form 10,20,30,...,90 (tens)
		{
			return tens[first_digit-1];
		}
		else if(first_digit == 1) //&& second_digit >= 1 && second_digit <= 9 if the two digit number is of the form 11,12,13...,19 (teens)
		{
			return teens[second_digit-1];
		}
		else
		{ //(first_digit != 1 && second_digit!=0 if the two digit number is of the form 21-29, 31-39, 41-49, ..., 91-99
			return tens[first_digit-1] + "-" +  convertOne(Integer.toString(second_digit));
		}
		
	}

	/*
	 * converts single digit numbers to corresponding word
	 */
	
	private String convertOne(String number) 
	{ 
		// TODO Auto-generated method stub
		return units[Integer.parseInt(number)];
	}
	
	public static void setSpecial(String [] s) 
	{
		if(special.length >= s.length) {
			System.out.println("Cannot decrease scope");
		}
		else 
		{
			special = new String[s.length];
			for(int i=0; i<s.length;i++) {
				special[i] = s[i];
			}
		}
	}
	
	
	public static String[] getSpecial() 
	{
		return special;
	}
	
	
	

}
