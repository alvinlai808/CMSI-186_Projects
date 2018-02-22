import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class HighRoll {
	public static void main( String[] args ) {


		int nDice = 0;
		int nSides = 0;

    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
    Scanner sc = new Scanner(System.in);

      try {
        System.out.println( "Enter the number of dice in set" );
        nDice = sc.nextInt();
        while ( nDice < 1 ) {
          System.out.print( "Error: Please enter an integers at least 1.\n>>" );
          nDice = sc.nextInt();
        }
      } 
      catch( Exception e ) {
        System.out.println( "Caught Exception" );
      }

      try {
        System.out.println( "Enter the number of sides of each dice" );
        nSides = sc.nextInt();
        while ( nSides < 4 ) {
          System.out.print( "Error: Please enter an integers at least 4.\n>>" );
          nSides = sc.nextInt();
        }
      }
      catch( Exception e ) {
        System.out.println( "Caught Exception" );
      }


		DiceSet ds = new DiceSet(nDice, nSides);
		int maxSoFar = ds.sum();

		System.out.println( "\n   Welcome to the MainProgLoopDemo!!\n" );

		while( true ) {

    
			System.out.println( "Please select your option or press q to quit" );
			System.out.println( "1. ROLL ALL THE DICE" );
			System.out.println( "2. ROLL A SINGLE DIE" );
			System.out.println( "3. CALCULATE THE SCORE FOR THIS SET" );
			System.out.println( "4. SAVE THIS SCORE AS HIGH SCORE" );
			System.out.println( "5. DISPLAY THE HIGH SCORE" );
			System.out.println( "6. ENTER 'q' TO QUIT THE PROGRAM" );
         	System.out.print( ">>" );

         	String inputLine = null;

         	try {

           	 	inputLine = input.readLine();
             	if( 0 == inputLine.length() ) {
              		System.out.println( "Enter a number between 1 and 5 (inclusive) or press 'q' to quit." );
           	 	} else if( 'q' == inputLine.charAt(0) ) {
               		break;
           	 	} else if ( '1' == inputLine.charAt(0) ) {
               		ds.roll();
               		System.out.println( "Rolled all dice: " + ds.toString() + "\n" );
           	 	} else if ( '2' == inputLine.charAt(0) ) {
           	 		System.out.print("\nWhich die? >> ");
           	 		
           	 		try{
           	 			inputLine = input.readLine();
           	 			while( inputLine.length() == 0 ){
           	 				System.out.print( "Error: Please enter at least 1 character.\n>>" );
           	 				inputLine = input.readLine();
           	 			}

           	 			int dieToRoll = (int)( inputLine.charAt(0) ) - 48;
           	 			while ( dieToRoll < 1 || dieToRoll > nDice ) {
           	 				System.out.print( "Error: Please enter a number between 1 and " + nDice + "\n>>" );
           	 				inputLine = input.readLine();
	           	 			dieToRoll = (int)( inputLine.charAt(0) ) - 48;
           	 			}

           	 			System.out.println( "Before rolling die " + dieToRoll + ": " + ds.toString() );
           	 			ds.rollIndividual( dieToRoll-1 );
           	 			System.out.println( "Result: " + ds.getIndividual( dieToRoll - 1 ) );
           	 			System.out.println( "After rolling die " + dieToRoll + ": " +  ds.toString() );

           	 		} catch( IOException ioe ) {
			            System.out.println( "Caught IOException" );
		        	}
               		System.out.println();
           	 	} else if ( '3' == inputLine.charAt(0) ) {
               		System.out.println( "The score for this set is " + ds.sum() + "\n" );
           	 	} else if ( '4' == inputLine.charAt(0) ) {
               		maxSoFar = ds.sum();
               		System.out.println( "Saved " + maxSoFar + " as current high score.\n" );
           	 	} else if ( '5' == inputLine.charAt(0) ) {
               		System.out.println( "The saved high score is: " + maxSoFar + "\n" );
           	 	} else {
           	 		System.out.println( "Enter a number between 1 and 5 or press 'q' to quit." );
           	 	}
        	}

	        catch( IOException ioe ) {
	            System.out.println( "Caught IOException" );
        	}

      	}
   	}
}
