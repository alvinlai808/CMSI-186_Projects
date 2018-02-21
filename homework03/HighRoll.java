import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {
	public static void main( String[] args ) {

		BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );

		System.out.println( "\n   Welcome to the MainProgLoopDemo!!\n" );
		DiceSet ds = new DiceSet (5, 6);

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
           	 	} else if( '2' == inputLine.charAt(0) ) {
               		ds.rollIndividual();
           	 	} else if( '3' == inputLine.charAt(0) ) {
               		ds.sum();
           	 	} else if( '4' == inputLine.charAt(0) ) {
               		break;
           	 	} else if( '5' == inputLine.charAt(0) ) {
               		break;
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
