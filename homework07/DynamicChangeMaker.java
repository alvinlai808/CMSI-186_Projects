/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name     :  DynamicChangeMaker.java
 * Purpose       :  Contains methods that define the DynamicChangeMaker class
 * @author       :  Alvin Lai
 * Date written  :  2018-04-28
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
 *  1.1.0  2018-04-25  Alvin Lai     Completed makeChangeWithDynamicProgramming() method, began printUsage()
 *                                    and getSimplePluralSuffix() methods
 *  1.2.0  2018-04-26  Alvin Lai     Completed printUsage() and getSimplePluralSuffix() methods
 *  1.3.0  2018-04-30  Alvin Lai     Completed DynamicChangeMaker methods
 *  1.4.0  2018-05-01  Alvin Lai     Added javadocs
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
      if ( args.length != 2 ) {
         printUsage();
         return;
      }

      try {
         String[] denominationStrings = args[0].split( "," );
         int[] denominations = new int[ denominationStrings.length ];

         for ( int i = 0; i < denominations.length; i++ ) {
            denominations[i] = Integer.parseInt( denominationStrings[i] );
            if ( denominations[i] <= 0 ) {
               System.out.println( "Denominations must all be greater than zero.\n" );
               printUsage();
               return;
            }

            for ( int j = 0; j < i; j++ ) {
               if ( denominations[j] == denominations[i] ) {
                  System.out.println( "Duplicate denominations are not allowed.\n" );
                  printUsage();
                  return;
               }
            }
         }

         int amount = Integer.parseInt( args[1] );
         if ( amount < 0 ) {
            System.out.println( "Change cannot be made for negative amounts.\n" );
            printUsage();
            return;
         }

         Tuple change = makeChangeWithDynamicProgramming( denominations, amount );
         if ( change.isImpossible() ) {
            System.out.println( "It is impossible to make " + amount + " cents with those denominations." );
         } else {
            int coinTotal = change.total();
            System.out.println( amount + " cents can be made with " + coinTotal + " coin" +
                                       getSimplePluralSuffix( coinTotal ) + " as follows:" );

            for ( int i = 0; i < denominations.length; i++ ) {
               int coinCount = change.getElement(i);
               System.out.println( "- "  + coinCount + " " + denominations[i] + "-cent coin" +
                                                        getSimplePluralSuffix( coinCount ) );
            }
         }
      } catch ( NumberFormatException nfe ) {
         System.out.println( "Denominations and amount must all be integers.\n" );
         printUsage();
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

  /**
   *  Prints error message to help user see how to input correct values
   *  Shows when user puts in values wrong
   */
   private static void printUsage() {
      System.out.println( "Usage: java DynamicChangeMaker <denominations> <amount>" );
      System.out.println( "  - <denominations> is a comma-separated list of denominations (no spaces)" );
      System.out.println( "  - <amount> is the amount for which to make change" );
   }

  /**
   *  adds plurals to coins that are used more than once in the optimal change
   *  @param   count = # of certain coins
   *  @return  nothing or an s depending on amount of coins
   */
   private static String getSimplePluralSuffix( int count ) {
      return count == 1 ? "" : "s";
   }

}
