/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input argumeninputTimeSlice are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 2.0.0  2018-03-12  Alvin Lai     Finished program
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   public double sec, min, hr, handAngle, minAngle, hrAngle, timeSlice, totalSec;
   private static final double SEC_IN_MIN = 60;
   private static final int    MIN_IN_HR = 60;
   private static final double DEFAULT_TIME_SLICE_IN_SEC = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HR_HAND_DEGREES_PER_SEC = 0.00834;
   private static final double MIN_HAND_DEGREES_PER_SEC = 0.1;

  /**
   *  Constructor goes here
   */
   public Clock() {
    this.sec = 0;
    this.min = 0;
    this.hr =  0;
    this.timeSlice = this.DEFAULT_TIME_SLICE_IN_SEC;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick( double inputTimeSlice) throws IllegalArgumentException {
      if( inputTimeSlice < 0.0 || inputTimeSlice > 1800.0) {
        throw new IllegalArgumentException( "Invalid argument. Enter value between 0 and 1800" );
      }

      totalSec += inputTimeSlice;
      this.sec += inputTimeSlice;
      this.min += Math.floor( this.sec / 60.0 );
      if( min >= 60.0 ) {
        this.hr++;
        this.min = this.min - 60.0;
      }
      this.sec = this.sec % 60.0;
      if( hr == 12.0 ) {
        this.hr = 0;
      }
      return totalSec;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      // checks if input argument is within range
      double angleVal = Double.parseDouble( argValue );
      if( angleVal < 0.0 || angleVal > 360.0) {
        throw new NumberFormatException( "Not a valid angle value" );
      }
      return angleVal;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      // checks if input argument is within range
      double timeSliceVal = Double.parseDouble( argValue );
      if (timeSliceVal < 0.0 || timeSliceVal > 1800.0 ) {
        return -1.0;
      }
      return timeSliceVal;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return this.hr;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return this.min;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      // convert # seconds to degrees, same with minutes and hours
      // if angle at certain point, reset
      hrAngle =  HR_HAND_DEGREES_PER_SEC * totalSec;
      minAngle = 0.1 * totalSec;
      if( minAngle >= 360.0 ) {
        minAngle = minAngle % 360.0;
      }

      handAngle = Math.abs( hrAngle - minAngle );
      if( handAngle > 180.0 ) {
        handAngle = 360.0 - handAngle;
      }

      if( handAngle > 360 - 0.1 && handAngle < 360.0 + 0.1 ) {
        handAngle = 0.0;
      }

      if( handAngle > 0.0 - 0.1 && handAngle < 0.0 + 0.1) {
        handAngle = 0;
      }
      return handAngle;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return this.totalSec;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      String secString = Double.toString( sec );
      String minString = Double.toString( min );
      String hrString  = Double.toString( hr );

      return ("The time is " + hrString + ":" + minString + ":" + secString);
   }

  /**
   *  The main program starinputTimeSlice here
   *  remember the constraininputTimeSlice from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOinputTimeSlice of tesinputTimeSlice!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );

  /**
    * Tests for validateAngleArg()
    */
      System.out.println( "    Testing validateAngleArg()....");

      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  29.46 degrees', expecting double value   29.46" );
      try { System.out.println( (29.46 == clock.validateAngleArg( "29.46" )) ? " - got 29.46" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  360 degrees', expecting double value   360.0" );
      try { System.out.println( (360.0 == clock.validateAngleArg( "360" )) ? " - got 360.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  -1 degrees', expecting NumberFormatException:" );
      try { System.out.println( (-1.0 == clock.validateAngleArg( "-1" )) ? " - got -1.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  361 degrees', expecting NumberFormatException:" );
      try { System.out.println( (361.0 == clock.validateAngleArg( "361" )) ? " - got 361.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  1000 degrees', expecting NumberFormatException:" );
      try { System.out.println( (1000.0 == clock.validateAngleArg( "1000" )) ? " - got 1000.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending ' who would type this? ', expecting NumberFormatException:" );
      try { System.out.println( clock.validateAngleArg( " who would type this?" ) ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

  /**
    * Tests for validateTimeSliceArg()
    */

      System.out.println( "    Testing validateTimeSliceArg()....");

      System.out.print( "      sending '  0 seconds', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  1800 seconds', expecting double value   29.46" );
      try { System.out.println( (29.46 == clock.validateTimeSliceArg( "29.46" )) ? " - got 29.46" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  -1 seconds', expecting NumberFormatException:" );
      try { System.out.println( (-1.0 == clock.validateTimeSliceArg( "-1" )) ? " - got -1.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  1801 seconds', expecting NumberFormatException:" );
      try { System.out.println( (1801.0 == clock.validateTimeSliceArg( "1801" )) ? " - got 1801.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  10000 seconds', expecting NumberFormatException:" );
      try { System.out.println( (10000.0 == clock.validateTimeSliceArg( "10000" )) ? " - got 10000.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending ' who would type this? ', expecting NumberFormatException:" );
      try { System.out.println( clock.validateTimeSliceArg( " who would type this?" ) ); }
      catch( Exception e ) { System.out.println ( "\n - Exception thrown: " + e.toString() ); }

  /**
    * Testing getHourHandAngle
    */

      System.out.println( "\nTesting getHourHandAngle..." );
      System.out.println( "Expected hour angle: 0.0 \n Returned hour angle: " + clock.getHourHandAngle() );

  /**
    * Testing getMinuteHandAngle
    */

      System.out.println( "\nTesting getMinuteHandAngle..." );
      System.out.println( "Expected minute angle: 0.0 \n Returned minute angle: " + clock.getMinuteHandAngle() );

  /**
    * Testing getHourHandAngle
    */

      System.out.println( "\nTesting getHandAngle..." );
      System.out.println( "Expected hand angle: 0.0 \n Returned hand angle: " + clock.getHandAngle() );

        
   }
}
