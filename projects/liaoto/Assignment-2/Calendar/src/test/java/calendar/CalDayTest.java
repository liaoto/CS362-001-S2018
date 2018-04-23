/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.GregorianCalendar;
import java.util.*;


public class CalDayTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {

  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {

  }
  
  @Test
  public void test02() //tests default constructor and invalid methods in makes
  {
	  CalDay day = new CalDay();
      assertEquals(false, day.isValid());
  }
  
  @Test
  public void test03() //tests non-default constructor
  {
	 
	  GregorianCalendar today = new GregorianCalendar(1900,3,25);
	  CalDay Day = new CalDay(today);
	  assertEquals(25, Day.getDay());
	  assertEquals(4, Day.getMonth());
	  assertEquals(1900, Day.getYear());
  }
  
  @Test
  public void test04()  //tests bug 4 sorting algorithm in addAppt() for appointments
  {
	  Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt1 = new Appt(13, 30, 9, 14, 2018, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt2 = new Appt(1, 30, 9, 14, 2018, "Removed Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt3 = new Appt(12, 30, 9, 14, 2018, "Thanksgiving Party", "This is my birthday party", "xyz@gmail.com");
	  GregorianCalendar today = new GregorianCalendar(1900,3,25);
	  CalDay Day = new CalDay(today);
	  Day.addAppt(appt0);
	  Day.addAppt(appt1);
	  Day.addAppt(appt2);
	  Day.addAppt(appt3);
	  
	  assertEquals(4, Day.getSizeAppts());

									//this assertion should catch introduced bug #4. If appointments are ordered by time, the second event should be appt3 not appt2
	  String event = Day.getAppts().get(1).getTitle(); //checks and asserts the first char of the 2nd event that is 'ordered'
	  assertEquals('R', event.charAt(0));       
	 
  }
  
  @Test
  public void test05()   //tests iterator method
  {
	  CalDay Invalid_day = new CalDay();
      assertEquals(null, Invalid_day.iterator());
	  
	  GregorianCalendar today = new GregorianCalendar(1900,3,25);
	  CalDay Day = new CalDay(today);
	  assertNotEquals(null, Day.iterator());
	  
  }
  
  @Test
  public void test06()  //tests for adding an invalid appointment
  {
									
	  Appt appt0 = new Appt(-1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  GregorianCalendar today = new GregorianCalendar(1900,3,25);
	  CalDay Day = new CalDay(today);
	  Day.addAppt(appt0);
	  assertEquals(0, Day.getSizeAppts());
  }
  
  @Test
  public void test07()  //tests tostring()
  {
	  CalDay Invalid_Day = new CalDay();
	  
      assertEquals("", Invalid_Day.toString());
	  
	  
	  
	  GregorianCalendar today = new GregorianCalendar(1900,3-1,25);
	  CalDay Day = new CalDay(today);
	  assertNotEquals("", Day.toString());
									
  }
  
  @Test
  public void test08()  //tests getFullInfomrationApp()   Tests PM AM Branch
  {
	  
	  GregorianCalendar today = new GregorianCalendar(1900,3-1,25);
	  CalDay Day = new CalDay(today);
	  Appt appt1 = new Appt(13, 30, 9, 14, 2018, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day.addAppt(appt1);
	  String fnVer = Day.getFullInfomrationApp(Day);
	  assertEquals('P', fnVer.charAt(16));
	
	  
	  GregorianCalendar today2 = new GregorianCalendar(1900,3-1,25);
	  CalDay Day2 = new CalDay(today2);
	  Appt appt2 = new Appt(11, 30, 9, 14, 2018, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day2.addAppt(appt2);
	  String fnVer2 = Day2.getFullInfomrationApp(Day2);
	  
	  assertEquals('A', fnVer2.charAt(17));
	    	  
  }
  
  @Test
  public void test09()  //tests getFullInfomrationApp()   Tests add 0 to minutes
  {
	  
	  GregorianCalendar today = new GregorianCalendar(1977,3-1,25);
	  CalDay Day = new CalDay(today);
	  Appt appt1 = new Appt(13, 9, 9, 14, 2218, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day.addAppt(appt1);
	  String fnVer = Day.getFullInfomrationApp(Day);
	  assertNotEquals(-1, fnVer.indexOf('0'));
	
	
	  
	  GregorianCalendar today2 = new GregorianCalendar(1922,3-1,25);
	  CalDay Day2 = new CalDay(today2);
	  Appt appt2 = new Appt(11, 31, 9, 14, 2218, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day2.addAppt(appt2);
	  String fnVer2 = Day2.getFullInfomrationApp(Day2);
	  assertEquals(-1, fnVer2.indexOf('0'));
	  
	  
  }
  
  @Test
  public void test10() //tests getFullInfomrationApp()   Tests hour 24 format -> 12 conversion
  {
	  GregorianCalendar today2 = new GregorianCalendar(1922,3-2,25);    //adds a 1 when converting to 1 hour from 23 hour
	  CalDay Day2 = new CalDay(today2);
	  Appt appt2 = new Appt(23, 29, 9, 22, 2218, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day2.addAppt(appt2);
	  String fnVer2 = Day2.getFullInfomrationApp(Day2);
	  assertNotEquals(-1, fnVer2.indexOf('1'));
	  
	  GregorianCalendar today = new GregorianCalendar(3977,9-1,25);
	  CalDay Day = new CalDay(today);
	  Appt appt1 = new Appt(0, 9, 9, 14, 2218, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day.addAppt(appt1);
	  String fnVer = Day.getFullInfomrationApp(Day);
	  assertNotEquals(-1, fnVer.indexOf("12"));
	  
	  
  }
  
  
  
}
	
