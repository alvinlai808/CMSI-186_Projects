/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Alvin Lai
 *  Date          :  2018-01-25
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 *  @version 2.0.0  2018-01-25  Alvin Lai     Finished Assignment
 */
 
 public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;
  // you can finish the rest on your own
  
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;
  // you can finish these on your own, too
  
  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static int[]    leapDays    = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      //return ((year % 4 == 0) && (year % 100 != 0) && (year % 400 == 0));
      if (year % 4 == 0) {
        if (year % 100 == 0) {
          if (year % 400 == 0) {
            return true;
          } else {
            return false;
          }
        } else {
          return true;
        }
      } else {
        return false;
      }
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      if (isLeapYear(year)) {
          return leapDays[(int)(--month)];
      } else {
          return days[(int)(--month)];
      }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return (month1 == month2 && day1 == day2 && year1 == year2);
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
    if (dateEquals (month1,  day1,  year1,  month2,  day2,  year2)) {
      return 0;
    } else if (year2 - year1 > 0) {
      return -1;
    } else if (year2 - year1 < 0) {
      return 1;
    } else {
      if (month2 - month1 > 0) {
        return -1;
      } else if (month2 - month1 < 0) {
        return 1;
      } else {
        if (day2 - day1 > 0) {
          return -1;
        } else if (day2 - day1 < 0) {
          return 1;
        } else {
          return 0;
        }
      }
    }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      if (year <= 0) {
        return false;
      }

      if (month < 1 || month > 12) {
        return false;
      }

      if (day < 1) {
        return false;
      } else if (isLeapYear(year)) {
        if (day > leapDays[(int)(--month)]) {
          return false;
        } else {
          return true;
        }
      } else {
        if (day > days[(int)(--month)]) {
          return false;
        } else {
          return true;
        }
      }

   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      int compareVal = compareDate( month1,  day1,  year1,  month2,  day2,  year2);
      if (compareVal == 1) {
        long temp = year2;
        year2 = year1;
        year1 = temp;

        temp = day2;
        day2 = day1;
        day1 = temp;

        temp = month2;
        month2 = month1;
        month1 = temp;

      }

      long dayCount = 0;
      if (dateEquals(month1,  day1,  year1,  month2,  day2,  year2)) {
        return 0;
      }

      // Find max number of days between years assuming all years are regular years
      
      dayCount = 365 * Math.abs(year2 - year1 + 1);
      

      // Add one day for each leap year in range
      for (long curYear = year1; curYear <= year2; curYear++) {
        if (isLeapYear(curYear)) {
          dayCount++;
        }
      }

      // Now dayCount contains the max number of days between the year ranges

      // Subtract number of days in months before month1
      for (int i = JANUARY; i < month1 - 1; i++) {
        if (isLeapYear(year1)) {
          dayCount -= leapDays[i];
        } else {
          dayCount -= days[i];
        }
      }

      // Subtract number of days before day1 in start date
      dayCount -= day1;

      // Subtract number of days in months after month2
      for (int i = DECEMBER; i > month2 - 1; i--) {
        if (isLeapYear(year2)) {
          dayCount -= leapDays[i];
        } else {
          dayCount -= days[i];
        }
      }

      // Subtract days that come after day2
      if (isLeapYear(year2)) {
        dayCount -= leapDays[(int)month2 - 1] - day2;
      } else {
        dayCount -= days[(int)month2 - 1] - day2;
      }
      return dayCount;
   }


   // public static String toDayOfWeekString(long day) {
   //    switch ((int)day)
   //    {
   //      case 1:
   //        return "Sunday";
   //      case 2:
   //        return "Monday";
   //      case 3:
   //        return "Tuesday";
   //      case 4:
   //        return "Wednesday";
   //      case 5:
   //        return "Thursday";
   //      case 6:
   //        return "Friday";
   //      case 7:
   //        return "Saturday";
   //      default:
   //        break;
   //    }
   // }

   // public static String toMonthString(long month) {
   //    switch ((int)month)
   //    {
   //      case 1:
   //        return "January";
   //      case 2:
   //        return "February";
   //      case 3:
   //        return "March";
   //      case 4:
   //        return "April";
   //      case 5:
   //        return "May";
   //      case 6:
   //        return "June";
   //      case 7:
   //        return "July";
   //      case 8:
   //        return "August";
   //      case 9:
   //        return "September";
   //      case 10:
   //        return "October";
   //      case 11:
   //        return "November";
   //      case 12:
   //        return "December";
          
   //    }
   // }

}
