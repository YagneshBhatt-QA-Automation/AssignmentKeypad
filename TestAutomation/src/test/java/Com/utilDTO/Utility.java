/*************************
Description : This is class for all common functions
CreatedBy & date: Yagnesh Bhatt
Function Added : getKeypadCombination, generateCombos,generateCombination,writeAllPossibleCombinationTxtFile
*************************/
package Com.utilDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;



public class Utility {
	
	// Number-to-letter mappings in order from zero to nine
    public static String mappings[][] = {
        {"0"}, {"1"}, {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"},
        {"J", "K", "L"}, {"M", "N", "O"}, {"P", "Q", "R", "S"}, 
        {"T", "U", "V"}, {"W", "X", "Y", "Z"}
    };
    
  /* ************************
  Function Description : In Function , Calling GenerateCombos function and get List of combination 
  CreatedBy & date: Yagnesh Bhatt
  Function Parameters : Test Case Phone number, TimeStamp for Folder name, TestCase Name
  *************************/
	public List<String> getKeypadCombination(String Phone, long timestamp, String TestCaseName) throws IOException
	{
		
        List<String> combos = generateCombos(Phone);
        writeAllPossibleCombinationTxtFile(combos,timestamp,TestCaseName);
		return combos;
	}
	
	/* ************************
	  Function Description : In Function , Calling generateCombination function and create all possible List of combination
	  CreatedBy & date: Yagnesh Bhatt
	  Function Parameters : Test case Phone number
	  *************************/

	public static List<String> generateCombos(String phoneNumber) {
        // This will hold the final list of combinations
        List<String> combos = new LinkedList<String>();

        // Call the helper method with an empty prefix and the entire 
        // phone number as the remaining part.
        generateCombination(combos, "", phoneNumber);

        return combos;
    }	
	
	/* ************************
	  Function Description : In Function, Getting Value from generateCombos function and defined all possible list of combination  
	  CreatedBy & date: Yagnesh Bhatt
	  Function Parameters :  Empty Combos list, Prefix of Defined keypad, Remaining Number of TestCase Phone number
	  *************************/
	public static void generateCombination(List<String> combosList, 
            String prefix, String remaining) {
        // The current digit we are working with
    	
        int digit = Integer.parseInt(remaining.substring(0, 1));
        //System.out.println(digit);
        
        if (remaining.length() == 1) {
            // We have reached the last digit in the phone number, so add 
            // all possible prefix-digit combinations to the list
            for (int i = 0; i <mappings[digit].length; i++) {
            	combosList.add(prefix + mappings[digit][i]);
                //System.out.println(prefix);                 
            }
        } else {
            // Recursively call this method with each possible new 
            // prefix and the remaining part of the phone number.
        	//System.out.println(prefix + mappings[digit][0]);
        	//System.out.println(remaining.substring(1));
            for (int i = 0; i <mappings[digit].length; i++) {            	
            	//System.out.println(combos);
            	generateCombination(combosList, prefix + mappings[digit][i], 
                        remaining.substring(1));
                
            }
        }        
     }
	
	/* ************************
	  Function Description : In Function, Creating txt file for all possible combination 
	  CreatedBy & date: Yagnesh Bhatt
	  Function Parameters : List of Combination, timestamp for folder name, Test Case name for txt file name
	  *************************/
     public void writeAllPossibleCombinationTxtFile(List<String> combosCopy, long timestamp, String TestCaseName) throws IOException
     {    	    	    
    	    String PATH = System.getProperty("user.dir") + "\\Result\\"+timestamp;			    	    
			String directoryName = PATH;			
    	    String fileName = "TestCase";
    	    File directory = new File(directoryName);
    	    if (! directory.exists()){
    	        directory.mkdir();
    	        // If you require it to make the entire directory path including parents,
    	        // use directory.mkdirs(); here instead.
    	    }
    	    //Write all combination in Text File
    	    File file = new File(directoryName + "/" + fileName + TestCaseName +".txt");    	    
    	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);

    	    for (String s : combosCopy) {	    	    
	    	    try{
	    	    	bw.write(s + "\n"); //All individual entry adding here	    	        
	    	    }
	    	    catch (IOException e){
	    	        e.printStackTrace();
	    	        //System.exit(-1);
	    	    }    	    
			}
    	    bw.close();
     }	
}
