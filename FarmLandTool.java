import java.util.ArrayList;
import java.util.Scanner;

/*
 * FarmLandTool
 * 
 * Main function to collect input, run the farmland tool, and display output
 * 
 */
class FarmLandTool {
	
    public static void main(String[] args) {
    	
        String input;
        String output;
        Scanner scanner;
        String answer;
        int test = 0;
    
        /*
         * Create a fresh farm land grid
         */
        FarmLand farm = new FarmLand();

        /*
         * Prompt user for input
         */
        System.out.println("Welcome to the Target Farm Land Analyzer!\n");
        System.out.println("Please do one of the following:");
        System.out.println("Enter [1] to run sample test - Large Box");
        System.out.println("Enter [2] to run sample test - Tic-Tac-Toe");
        System.out.println("Enter [3] to run an out of bounds test");
        System.out.println("Enter custom input:\n");

        scanner = new Scanner(System.in);
        answer = scanner.nextLine();
        
	    switch (answer) {
	    case "1":
	    	input = "{“0 292 399 307”}";
	    	test = 1;
	        break;
	    case "2":
	    	input = "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}";
	    	test = 2;
		    break;
	    case "3":
	    	input = "{“48 192 351 20700”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}";
	    	test = 3;
		    break;
	    default:
	        input = answer;
	    }

		/*
		 * Clean up the input
		 */
        input = input.replace("{", "");
        input = input.replace("}", "");
        input = input.replace("\"", ""); // this isn't working for some reason
        
		/*
		 * Parse each line and put in a temporary array of strings 
		 * Then this array of strings needs to be further parsed into integers
         * These integers are then used to setup the barren farmland
         */
        String[] barrenPointsString = input.split(",");
        String[] stringArray;
        for (int i = 0; i < barrenPointsString.length; i++) {
        	/*
        	 * This is a hack to get rid of the leading white space and quotes.
        	 * For some reason the quote cleanup above isn't doing the trick.
        	 */
        	barrenPointsString[i] = barrenPointsString[i].trim();
        	barrenPointsString[i] = barrenPointsString[i].substring(1, barrenPointsString[i].length()-1);
        	
            /*
             * Set the barren farm land based on the input
             */
        	stringArray = barrenPointsString[i].split(" ");
        	if (stringArray.length != 4) {
        		System.out.println("Input is not in expected format.");
        		return;
        	}
        	farm.setBarrenFarmLand(Integer.valueOf(stringArray[0]),
        			               Integer.valueOf(stringArray[1]),
        			               Integer.valueOf(stringArray[2]),
        			               Integer.valueOf(stringArray[3])); 
        }
        
        /*
         * Analyze the farm land and display output.
         */
        System.out.println("\nAnalyzing...");
        output = farm.analyzeFarmLand().trim();
        System.out.println(output);
        
        /*
         * If testing, compare output against expected values
         */
        if (test == 1) {
        	if (!output.equals("116800 116800")) {
        		System.out.println("Test 1 failed.");
        	} else {
        		System.out.println("Test 1 passed!");	
        	}
        } else if (test == 2) {
        	if (!output.equals("22816 192608")) {
        		System.out.println("Test 2 failed.");
        	} else {
        		System.out.println("Test 2 passed!");
        	}
        }
    }
}
