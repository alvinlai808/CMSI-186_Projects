/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  B.J. Johnson
 *  Date          :  2017-02-06
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-06  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-02-17  B.J. Johnson  Filled in method code
 *  @version 1.2.0  2018-02-15  Alvin Lai     Finished methods except setSides and main
 *  @version 1.3.0  2018-02-21  Alvin Lai     Finished setSides, edited other methods
 *  @version 2.0.0  2018-02-22  Alvin Lai     Completed and revised
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips = 1;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) throws IllegalArgumentException {
      // Tell user their input isn't enough if they enter a # < 4
      // If valid input, set the input to sides
      if ( nSides < MINIMUM_SIDES ) {
        throw new IllegalArgumentException ( "Not enough sides, enter # at least 4" );
      }
      sides = nSides;
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
      // Generates random number and assigns it to pip, then returns pips' value
      pips = ( int )( Math.random() * sides ) + 1;
      return pips;
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return pips;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides( int newNumSides ) throws IllegalArgumentException {
      //change the # of sides in the dice, code similar to constructor
      if ( newNumSides < MINIMUM_SIDES ) {
        throw new IllegalArgumentException ( "Not enough sides, enter # at least 4" );
      }
      sides = newNumSides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      // returns pips in string format
      return "[" + pips + "] ";
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
      // gets value of object d and turns it into a string
      return "[" + d.getValue() + "] ";
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println( "Hello world from the Die class..." );
      System.out.println("Running test harness...");

      // Constructor test
      // Good input case nSides > minSides
      Die d1 = new Die (6);

      // Bad input case nSides <= minSides
      try {
      	Die d2 = new Die (3);
      } catch(IllegalArgumentException iae) {
      	System.out.println("Caught IllegalArgumentException as expected.");
      }

      // Set sides test
      // Good input case nSides >= minSides
      Die d3 = new Die (6);
      d3.setSides(8);

      // Bad input case nSides < minSides
      Die d4 = new Die (6);
      try {
      	d4.setSides(3);
      } catch(IllegalArgumentException iae) {
      	System.out.println("Caught IllegalArgumentException as expected.");
      }


      // Simple test all functions
      Die d5 = new Die(6);
      System.out.println(toString(d5));
      System.out.println(d5.toString());
      System.out.println(d5.getValue());
      d5.setSides(100);
      System.out.println(d5.roll());
      System.out.println(d5.toString());

      System.out.println("No errors for testing as expected. Tests passed!");

   }

}
