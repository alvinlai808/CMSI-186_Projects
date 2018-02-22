/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2018-02-21  Alvin Lai     Had every method completed except static toString method and main
 *  @version 2.0.0  2018-02-22  Alvin Lai     Completed and revised
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) throws IllegalArgumentException {
      if ( count < 1 || sides < 4 ) {
        throw new IllegalArgumentException ( "Count must be at least 1 and sides must be at least 4" );
      }
      this.count = count;
      this.sides = sides;
      ds = new Die[ count ];
      for ( int i = 0; i < count; i++ ) {
        ds[ i ] = new Die( sides );
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int sum = 0;
      for ( int i = 0; i < count; i++ ) {
        sum += ds[ i ].getValue();
      }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
      String result = "";
      for ( int i = 0; i < count; i++ ) {
        result += new Integer( ds[i].roll() ).toString() + " ";
      }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) throws IllegalArgumentException {
      if ( dieIndex > ds.length ) {
        throw new IllegalArgumentException ( "Index is out of bounds, please try a new index." );
      }
      return ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if ( dieIndex > ds.length ) {
        throw new IllegalArgumentException ( "Index is out of bounds, please try a new index." );
      }
      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String output = "";
      for ( int i = 0; i < count; i++ ) {
        output += ds[i].toString();
      }
      return output;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
   	
   		int[] a1 = new int[count];
   		int[] a2 = new int[count];
   		for (int i = 0; i < count; i++) {
   			a1[i] = this.getIndividual(i);
   			a2[i] = ds.getIndividual(i);
   		}

   		Arrays.sort(a1);
   		Arrays.sort(a2);

   		boolean valuesAreEqual = true;
   		for (int i = 0; i < count; i++) {
   			if (a1[i] != a2[i]) {
   				valuesAreEqual = false;
   			}
   		}


      return (( this.count == ds.count ) &&
              ( this.sides == ds.sides ) &&
              ( valuesAreEqual ));
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
    	// Constructor tests
   		// Good input (count > 0, nSides >= minSides)
   		DiceSet ds1 = new DiceSet(5, 6);

   		// Bad input 1 (count <= 0)
   		try {
   			DiceSet ds2 = new DiceSet(0, 6);
   		} catch (IllegalArgumentException iae) {
   			System.out.println("Caught IllegalArgumentException as expected.");
   		}

   		// Bad input 2 (nSides < minSides)
   		try {
   			DiceSet ds3 = new DiceSet(5, 3);
   		} catch (IllegalArgumentException iae) {
   			System.out.println("Caught IllegalArgumentException as expected.");
   		}

   		// Bad input 3 (count <= 0 and nSides < minSides)
   		try {
   			DiceSet ds4 = new DiceSet(0, 3);
   		} catch (IllegalArgumentException iae) {
   			System.out.println("Caught IllegalArgumentException as expected.");
   		}

   		// Normal input conditions test
   		DiceSet ds5 = new DiceSet(5, 6);
   		System.out.println(toString(ds5));
   		System.out.println(ds5.toString());
   		System.out.println(ds5.sum());
   		System.out.println(ds5.rollIndividual(0));
   		System.out.println(ds5.getIndividual(0));
   		System.out.println("Normal input conditions pass testing without error!");


   		// Test identical
   		DiceSet ds6 = new DiceSet(5, 6);
   		ds6.roll();
   		DiceSet ds7 = ds6;
   		System.out.println("Comparing " + toString(ds6) + " with " + toString(ds7) + "...");
   		if (ds6.isIdentical(ds7)){
   			System.out.println("Is identical function works!");
   		} else {
   			System.out.println("Is identical function failed.");
   		}

   		// Test non-identical (different dice values)
   		DiceSet ds8 = new DiceSet(5, 1000);
   		DiceSet ds9 = new DiceSet(5, 1000);
   		ds8.roll();
   		ds9.roll();
   		System.out.println("Comparing " + toString(ds8) + " with " + toString(ds9) + "...");
   		if (!ds8.isIdentical(ds9)){
   			System.out.println("Is identical function works!");
   		} else {
   			System.out.println("Is identical function failed.");
   		}

   		// Test non-identical (different dice numbers)
   		DiceSet ds10 = new DiceSet(5, 6);
   		DiceSet ds11 = new DiceSet(10, 6);
   		System.out.println("Comparing " + toString(ds10) + " with " + toString(ds11) + "...");
   		if (!ds10.isIdentical(ds11)){
   			System.out.println("Is identical function works!");
   		} else {
   			System.out.println("Is identical function failed.");
   		}

   		// Test non-identical (different dice numbers)
   		DiceSet ds12 = new DiceSet(5, 6);
   		DiceSet ds13 = new DiceSet(5, 1000);
   		System.out.println("Comparing " + toString(ds12) + " with " + toString(ds13) + "...");
   		if (!ds12.isIdentical(ds13)){
   			System.out.println("Is identical function works!");
   		} else {
   			System.out.println("Is identical function failed.");
   		}

   }

}
