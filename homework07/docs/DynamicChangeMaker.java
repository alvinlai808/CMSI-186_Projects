/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name     :  DynamicChangeMaker.java
 * Purpose       :  Contains methods that define the DynamicChangeMaker class
 * @author       :  Alvin Lai
 * Date written  :  2018-04-21
 * Description   :  This class provides methods that evaluates the slots in a table to make change. 
 *                  This class is intended to be used as part of homework 7, the coin
 *                  changemaker, which is a "Dynamic Programming" algorithm.
 * Notes         :  None
 * Warnings      :  None
 * Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-04-21  Alvin Lai     Created file
 *  1.1.0  2018-04-25  Alvin Lai     Completed makeChangeWithDynamicProgramming() method
 *  1.3.0  2018-04-30  Alvin Lai     Completed DynamicChangeMaker main
 *  1.4.0  2018-05-01  Alvin Lai     Added javadocs
 *  1.5.0  2018-05-02  Alvin Lai     Updated main, added comments
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class DynamicChangeMaker {

  /**
   *  Main program that finds optimal amount of change
   *  @param args = coin denominations and target value
   */
   public static void main( String[] args ) {

      String[] stringDenoms = args[0].split(",");        // create array of strings by checking input args
                                                         // splits them everytime a comma appears
      int[] intDenoms = new int[ stringDenoms.length ];  // creates int array with length of the string array
      int target = Integer.parseInt( args[1] );          // sets target = the value separated by a space in the args     

      // Checks argument length
      if ( args.length <= 0 || args.length > 2 ) {
         System.out.println( "Error: you can only have 2 arguments\n" +
                             "Please separate denominations with commas\n" +
                             "and use a space to separate the denominations from the target value." );
         System.exit( 1 );
      }

      // Turns array of strings into array of ints
      try {
         for ( int i = 0 ; i <= stringDenoms.length - 1 ; i++ ) {
            intDenoms[i] = Integer.parseInt( stringDenoms[i] );
         }
      }
      catch (Exception e) {
         System.out.println( "Invalid Argument: please input your arguments as integers.\n"+
                             "Please separate denominations with commas\n"+
                             "and use a space to separate the denominations from the target value." );
         throw new IllegalArgumentException();
      }

      // Checks for impossible 'tuple'
      if ( DynamicChangeMaker.makeChangeWithDynamicProgramming( intDenoms, target ) == Tuple.IMPOSSIBLE ) {
         System.out.println( "This tuple is impossible." );
      }

   }

  /**
   *  Finds optimal change given some denominations
   *  @param   denominations = type of coins that can be used
   *  @param   amount = target value for denominations to satisfy
   *  @return  answer = optimal denomination amount for desired amount
   */
   public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int amount ) {
      // Creates a table with # rows = # of denominations and # columns = target amount + 1
      Tuple[][] table = new Tuple[ denominations.length ][ amount + 1 ];
      Tuple answer = new Tuple( denominations.length );

      // Loops through each denomination (rows = i)
      for ( int i = 0; i < denominations.length; i++ ) {
         // Loops through amount of coin (columns = j)
         for ( int j = 0; j < amount + 1; j++ ) {
            // If the coin index is less than the denomination, Tuple is impossible.
            if ( j < denominations[i] ) {
               // Establishes 0, at very beginning of table count, otherwise Tuple is impossible
               if ( j == 0 ) {
                  table[i][0] = new Tuple( denominations.length );
               } else {
                  table[i][j] = Tuple.IMPOSSIBLE;    
               }
            } else {
               int remainder = j - denominations[i];
               table[i][j] = new Tuple ( denominations.length );
               table[i][j].setElement( i, 1 );

               // Go back remainder spaces and see if Tuple is impossible or not
               if ( !table[i][remainder].isImpossible() ) {
                  table[i][j] = table[i][j].add( table[i][remainder] );
               } else {
                  table[i][j] = Tuple.IMPOSSIBLE;
               }
            }

            // Look up and see if there is anything above. If so, bring it down to current array
            // Gets the most optimal change
            if (i > 0) {
               if ( table[i][j].isImpossible() && !table[i - 1][j].isImpossible() ) {
                  table[i][j] = table[i - 1][j];
               } else if ( !table[i][j].isImpossible() && !table[i - 1][j].isImpossible() ) {
                  if ( table[i][j].total() > table[i - 1][j].total() ) {
                     table[i][j] = table[i - 1][j];
                  }
               }
            }

            answer = table[i][j];
         }
      }

      return answer;
   }

}
