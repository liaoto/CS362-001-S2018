
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.io.IOException;
import java.lang.Exception;
import java.io.*;



public class DataHandlerTest{

  
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {

  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {

  }
  
  @Test(timeout = 4000)
  public void test02() throws Throwable //test all arguments constructor
  {
	  DataHandler data = new DataHandler("Save", true);
	  GregorianCalendar today = new GregorianCalendar(1922,3-2,25);
	  GregorianCalendar future = new GregorianCalendar(1922,29-1,25);
	  data.getApptRange(today, future);
  }
  
  @Test
  public void test03() throws Throwable //test only file name
  {
	  DataHandler data = new DataHandler("Save");
	  GregorianCalendar today = new GregorianCalendar(1922,3-2,25);
	  GregorianCalendar future = new GregorianCalendar(1922,29-1,25);
	  data.getApptRange(today, future);
  }

  
  @Test
  public void test04() throws Throwable //test only file name
  {
	  try
	  {
		DataHandler data = new DataHandler();
	  }
	  catch (IOException e)
	  {
		  System.err.println(e.getMessage());
	  }
  }
  
  
  
  
  @Test
  public void test04A()
  {
	    try{
			DataHandler data = new DataHandler("Save", true);
			GregorianCalendar today = new GregorianCalendar(1922,27-1,25);
			GregorianCalendar future = new GregorianCalendar(1922,4-1,25);
			data.getApptRange(today, future);
		
		} 
		catch (DateOutOfRangeException d)
		{
			System.err.println(d.getMessage());
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
			
			
		
  }
  
 
  
  @Test
  public void test04B() throws Throwable //test filled calendar
  {
	  DataHandler data = new DataHandler("Save", true);
	  GregorianCalendar today = new GregorianCalendar(1922,13-1,25);
	  CalDay Day = new CalDay(today);
	  Appt appt = new Appt(14, 29, 9, 22, 2218, "Wello Party", "This is my birthday party", "xyz@gmail.com");
	  Day.addAppt(appt);
	  
	  GregorianCalendar future = new GregorianCalendar(1922,24-1,25);
	  CalDay Day2 = new CalDay(future);
	  Appt appt2 = new Appt(23, 29, 9, 22, 2218, "Hello Party", "This is my birthday party", "xyz@gmail.com");
	  Day2.addAppt(appt2);
	  data.getApptRange(today, future);
  }


  
  @Test
  public void test05() throws Throwable //test save appt
  {
	  DataHandler data = new DataHandler("Save", true);
	  Appt appt0 = new Appt(-1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertEquals(false, data.saveAppt(appt0));
	  Appt appt1 = new Appt(1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  assertEquals(true, data.saveAppt(appt1));
	  
  }
  
  @Test
  public void test06() throws Throwable //test save appt (auto save off)
  {
	  DataHandler data = new DataHandler("Save", false);
	  Appt appt1 = new Appt(1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  assertEquals(true, data.saveAppt(appt1));
	  
  }

  @Test
  public void test06A() throws Throwable //test mutables
  {
	  Appt appt1 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  DataHandler data = new DataHandler("Save", false);
	  appt1.setValid();
	  
	  assertEquals(true, data.save());

	  Appt appt2 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  Appt appt3 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  Appt appt4 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  Appt appt5 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  appt2.setValid();
	  appt3.setValid();
	  appt4.setValid();
	  appt5.setValid();
	  
	  data.saveAppt(appt2);
	  data.saveAppt(appt3);
	  data.saveAppt(appt4);
	  data.saveAppt(appt5);
	  assertEquals(true, data.saveAppt(appt1));
  }
   
  @Test
  public void test06B() throws Throwable //test mutables
  {   	  
	  DataHandler data5 = new DataHandler("Save", true);
      Appt appt1 = new Appt(5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	  GregorianCalendar today = new GregorianCalendar(2018,4,25);
	  today.add(today.DAY_OF_MONTH,1);
	  
	  GregorianCalendar future = new GregorianCalendar(2018,5,25);
	    
	  assertEquals(30, data5.getApptRange(today,future).size());

  }
  
  @Test 
  public void test06C() throws Throwable
  {
	  DataHandler data = new DataHandler("Save", true);
	  
	  GregorianCalendar today = new GregorianCalendar(2118,4,25);
	  CalDay Day = new CalDay(today);
	  
	  GregorianCalendar future = new GregorianCalendar(2118,5,25);
	  CalDay Day2 = new CalDay(future);
	  
	  assertEquals(0, data.getApptRange(today,future).get(0).getSizeAppts());


	  
  }
  
  @Test
  public void test07() throws Throwable //test delete apps
  {
	  DataHandler data = new DataHandler("Save", false);
	  Appt appt1 = new Appt(-1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  assertEquals(false, data.deleteAppt(appt1));
	  
	  Appt appt2 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  appt2.setValid();
	  DataHandler data2 = new DataHandler("Save", true);
	  data2.saveAppt(appt2);
	  assertEquals(true, data.deleteAppt(appt2));
	  
	  
	  assertEquals(null, appt2.getXmlElement());
	  appt2.setXmlElement(null);
	  assertEquals(null, appt2.getXmlElement());
	  
	  
  }
  
    @Test
  public void test08() throws Throwable //test delete apps
  {
	  DataHandler data = new DataHandler("Save", false);
	  Appt appt3 = new Appt(5,5,9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  assertEquals(false, data.deleteAppt(appt3));
	  
	  	  
	  DataHandler data_auto = new DataHandler("Save", true);  //try the save() function
	  Appt appt4 = new Appt(5,5,9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  assertEquals(false, data_auto.deleteAppt(appt4));
	  
  }
  
      @Test
  public void test09() throws Throwable //test save()
  {
	  DataHandler data = new DataHandler("Save", false);
	  assertEquals(true, data.save());
	  
  }
  
  @Test
  public void test10() throws Throwable //test diagnoses to true
  {
	  DataHandler data = new DataHandler("Save", false);
	  
	  GregorianCalendar today = new GregorianCalendar(1922,3-2,25);
	  CalDay Day = new CalDay(today);
	  Appt appt0 = new Appt(5,5,9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setRecurrence(null, 0, 0, 0);
	  Day.addAppt(appt0);
	  
	  GregorianCalendar future = new GregorianCalendar(1922,29-1,25);
	  CalDay Day2 = new CalDay(future);
	  Appt appt1 = new Appt(2,5,9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setRecurrence(null, 0, 0, 0);
	  Day2.addAppt(appt1);
	  
	  assertNotEquals(null, data.getApptRange(today,future));
	  
	  
  }
  
  @Test
  public void test11() throws Throwable //test diagnoses to true
  {
	  DataHandler data = new DataHandler("Save", true);
	  
	  GregorianCalendar today = new GregorianCalendar(1922,3-2,25);
	  CalDay Day = new CalDay(today);
	  Appt appt0 = new Appt(5,5,9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setRecurrence(null, -1, -1, -1);
	  Day.addAppt(appt0);
	  
	  GregorianCalendar future = new GregorianCalendar(1922,29-1,25);
	  CalDay Day2 = new CalDay(future);
	  Appt appt1 = new Appt(2,5,9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setRecurrence(null, -1, -1, -1);
	  Day2.addAppt(appt1);
	  
	  assertNotEquals(null, data.getApptRange(today,future));
	  
	  
  }
  

    @Test
  public void test12() throws Throwable //test bug 5
  {
	  DataHandler data = new DataHandler("Save", false);
	  
	  GregorianCalendar today = new GregorianCalendar(2018,2-1,1);
	  CalDay DayA = new CalDay(today);
	  CalDay DayB = new CalDay(today);

	 
 	  int[] todayRecurDays = new int[5];
	  Appt appt0 = new Appt(4,3,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt1 = new Appt(5,3,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt2 = new Appt(6,3,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  Appt appt3 = new Appt(7,3,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  
	  DayA.addAppt(appt0);
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt2);
	  DayA.addAppt(appt3);
	
			  
	  GregorianCalendar future = new GregorianCalendar(2018,15-1,25);
	  CalDay Day2 = new CalDay(future);
	 
	
	  Day2.addAppt(appt0);
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt2);
	  Day2.addAppt(appt3);
	  
	  
	  appt0.setRecurrence(null, -1, -1, -1);

	  
	  assertNotEquals(null, data.getApptRange(today,future));
	  
	  
  }

  @Test
  public void test13()
  {
	  try
	  {
		  DataHandler data = new DataHandler("Save", true);
	      GregorianCalendar today = new GregorianCalendar(-1,3-1,24);
	      GregorianCalendar future = new GregorianCalendar(10,3-1,25);
	      assertNotEquals(null, data.getApptRange(today,future));
	  }
	  catch (IOException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  catch (DateOutOfRangeException d)
	  {
		  System.err.println(d.getMessage());
	  }

	  
  }
  
  @Test
  public void test14() throws Throwable   //gets occurence day == null
  {
	  DataHandler data = new DataHandler("Save", true);
	  
	  GregorianCalendar today = new GregorianCalendar(2018,2-1,1);
	  CalDay DayA = new CalDay(today);

	 
	  Appt appt0 = new Appt(4,3,-10, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  DayA.addAppt(appt0);
			  
	  GregorianCalendar future = new GregorianCalendar(2018,15-1,25);
	  CalDay Day2 = new CalDay(future);
	 
	  Day2.addAppt(appt0);
	  
	  appt0.setRecurrence(null, -1, -1, -1);

	  
	  assertNotEquals(null, data.getApptRange(today,future));
	  
	  
  }
  
  @Test
  public void test15() throws Throwable   //tests reoccuring
  {
	  DataHandler data = new DataHandler("Save", true);
	  
	  GregorianCalendar today = new GregorianCalendar(2018,2-1,1);
	  CalDay DayA = new CalDay(today);

	 
	  Appt appt0 = new Appt(4,3,-10, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  DayA.addAppt(appt0);
			  
	  GregorianCalendar future = new GregorianCalendar(2018,15-1,25);
	  CalDay Day2 = new CalDay(future);
	 
	  Day2.addAppt(appt0);
	  
	  int[] todayRecurDays = new int[5];
	  appt0.setRecurrence(todayRecurDays, 0, 0, 0);

	  
	  assertNotEquals(null, data.getApptRange(today,future));
	  
  }
  
  @Test
  public void test16() throws Throwable   //tests reoccuring doesn't go into for loop because bug makes it start at 4
  {
	  DataHandler data = new DataHandler("Save", true);
	  
	  GregorianCalendar today = new GregorianCalendar(2018,3,1);
	  CalDay DayA = new CalDay(today);

	 
	  Appt apptnew = new Appt(1,1,2019, "Birthday Party", "This is my birthday party", "xyz@gmail.com");

			  
	  GregorianCalendar future = new GregorianCalendar(2019,5,25);
	  CalDay Day2 = new CalDay(future);
	 
	  Day2.addAppt(apptnew);
	  
	  int[] todayRecurDays = new int[5];
	  apptnew.setRecurrence(todayRecurDays, 3, 3, 3);

	  
	  assertNotEquals(null, data.getApptRange(today,future));
	  
  }
  
    @Test
  public void test17() throws Throwable   //tests reoccuring doesn't go into for loop because bug 5 makes it start at 4  **bug 5 changed due to this**
  {
	  DataHandler data = new DataHandler("Save", true);
	  int[] todayRecurDays = {2,3,4};
	  Appt appt1 = new Appt(25, 10, 2,2,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  
	  appt1.setRecurrence(todayRecurDays, 1, 1, 1000);
	  data.saveAppt(appt1);
	  GregorianCalendar today = new GregorianCalendar(2018,3,1);
	  
	  CalDay DayA = new CalDay(today);

	  today.add(today.DAY_OF_MONTH,1);
	  CalDay DayB = new CalDay(today);
	  
	  today.add(today.DAY_OF_MONTH,2);
	  CalDay DayC = new CalDay(today);
	  
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt1);
	  
	  appt1.setStartMonth(4);
	  DayB.addAppt(appt1);
	  
	  appt1.setStartMonth(7);
	  DayC.addAppt(appt1);
	  
			  
	  GregorianCalendar future = new GregorianCalendar(2019,5,25);
	  CalDay Day2 = new CalDay(future);
	  
	  future.add(future.DAY_OF_MONTH,1);
	  CalDay Day3 = new CalDay(future);
	  
	  future.add(future.DAY_OF_MONTH,2);
	  CalDay Day4 = new CalDay(today);
	  
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt1);
	  
	  appt1.setStartMonth(4);
	  Day3.addAppt(appt1);
	  
	  appt1.setStartMonth(7);
	  Day4.addAppt(appt1);
	  
	
	  assertNotEquals(null, data.getApptRange(today,future));
	  
  }
  
    @Test
  public void test18() throws Throwable   //Extra test cases for getAppOccurences
  {
	  DataHandler data = new DataHandler("Save", true);
	
	  Appt appt1 = new Appt(25, 10, 2,2,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  
	  appt1.setRecurrence(null, 0, 0, 1000);
	  data.saveAppt(appt1);
	  GregorianCalendar today = new GregorianCalendar(2018,3,1);
	  
	  CalDay DayA = new CalDay(today);

	  today.add(today.DAY_OF_MONTH,1);
	  CalDay DayB = new CalDay(today);
	  
	  today.add(today.DAY_OF_MONTH,2);
	  CalDay DayC = new CalDay(today);
	  
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt1);
	  DayA.addAppt(appt1);
	  
	  appt1.setStartMonth(4);
	  DayB.addAppt(appt1);
	  
	  appt1.setStartMonth(7);
	  DayC.addAppt(appt1);
	  
			  
	  GregorianCalendar future = new GregorianCalendar(2019,5,25);
	  CalDay Day2 = new CalDay(future);
	  
	  future.add(future.DAY_OF_MONTH,1);
	  CalDay Day3 = new CalDay(future);
	  
	  future.add(future.DAY_OF_MONTH,2);
	  CalDay Day4 = new CalDay(today);
	  
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt1);
	  Day2.addAppt(appt1);
	  
	  appt1.setStartMonth(4);
	  Day3.addAppt(appt1);
	  
	  appt1.setStartMonth(7);
	  Day4.addAppt(appt1);
	  
	
	  assertNotEquals(null, data.getApptRange(today,future));
	  
  }
  
   @Test
  public void test18A() throws Throwable   //Extra test cases for getAppOccurences
  {
	  DataHandler data = new DataHandler("Save", true);
	
	  Appt appt1 = new Appt(25, 10, 1,3,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  
	  appt1.setRecurrence(null, 3, 3, 3);
	  data.saveAppt(appt1);
	  GregorianCalendar today = new GregorianCalendar(2018,3,1);
	  CalDay DayA = new CalDay(today);
	  
	  DayA.addAppt(appt1);
	  
			  
	  GregorianCalendar future = new GregorianCalendar(2019,5,25);
	  CalDay Day2 = new CalDay(future);
	  
	  Day2.addAppt(appt1);
	  
	
	  assertNotEquals(null, data.getApptRange(today,future));
	  
  }
  
   @Test
  public void test18B() throws Throwable   //Extra test cases for getAppOccurences
  {
	  DataHandler data = new DataHandler("Save", true);
	
	  Appt appt1 = new Appt(25, 30, 25, 7, 2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	  int[] recurDaysArr4={2,3,4};
      appt1.setRecurrence( recurDaysArr4, Appt.RECUR_BY_WEEKLY, 3, Appt.RECUR_NUMBER_FOREVER);
	  appt1.setValid();
	  
	  boolean save = data.saveAppt(appt1);
	  assertEquals(true, save);

	  
	  GregorianCalendar today = new GregorianCalendar(2218,7-2,21);
	  
	  GregorianCalendar future = new GregorianCalendar(2218,8-2,29);
	  
	  
	  assertEquals(38, data.getApptRange(today,future).size());
	  assertNotEquals("", data.getApptRange(today,future).get(0).toString());
	  assertEquals(appt1.toString().charAt(0), data.getApptRange(today,future).get(37).getAppts().get(0).toString().charAt(0));

	  
	  save = data.deleteAppt(appt1);
	  assertEquals(true, save);
  }
  /*
     @Test
  public void test18C() throws Throwable   //Extra test cases for getAppOccurences
  {
	  DataHandler data = new DataHandler("Save", true);
	
	  Appt appt1 = new Appt(25, 30, 25, 7, 2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	  int[] recurDaysArr4={2,3,4};
      appt1.setRecurrence( recurDaysArr4, Appt.RECUR_BY_MONTHLY, 3, Appt.RECUR_NUMBER_FOREVER);
	  appt1.setValid();
	  
	  boolean save = data.saveAppt(appt1);
	  assertEquals(true, save);

	  
	  GregorianCalendar today = new GregorianCalendar(2218,7-2,21);
	  
	  GregorianCalendar future = new GregorianCalendar(2218,8-2,29);
	  
	  assertNotEquals("", data.getApptRange(today,future).get(0).toString());

	  
	  save = data.deleteAppt(appt1);
	  assertEquals(true, save);
  }
  
      @Test
  public void test18D() throws Throwable   //Extra test cases for getAppOccurences
  {
	  DataHandler data = new DataHandler("Save", true);
	
	  Appt appt1 = new Appt(25, 30, 25, 7, 2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	  int[] recurDaysArr4={2,3,4};
      appt1.setRecurrence( recurDaysArr4, Appt.RECUR_BY_YEARLY, 3, Appt.RECUR_NUMBER_FOREVER);
	  appt1.setValid();
	  
	  boolean save = data.saveAppt(appt1);
	  assertEquals(true, save);

	  
	  GregorianCalendar today = new GregorianCalendar(2218,7-2,21);
	  
	  GregorianCalendar future = new GregorianCalendar(2218,8-2,29);
	  
	  assertNotEquals("", data.getApptRange(today,future).get(0).toString());

	  
	  save = data.deleteAppt(appt1);
	  assertEquals(true, save);
  }
  */
  
  @Test
  public void test19() throws Throwable //delete app test
  {
	  DataHandler data = new DataHandler("Save", false);
	
	  Appt appt1 = new Appt(25, 10, 2,2,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  data.saveAppt(appt1);
	  assertEquals(true, data.deleteAppt(appt1));
	  
	  DataHandler data2 = new DataHandler("Save", true);
	  boolean saver = data2.save();
	  assertEquals(true, saver);
	  data2.saveAppt(appt1);
	  assertEquals(true, data2.deleteAppt(appt1));
	  
  }
  
  @Test
  public void test20() throws Throwable  //breaking functions save
  {
	  DataHandler data = new DataHandler("#^@&*!#!@(^!@# \n\n\n\n\n\t........... @#!#@!", true);
	  data.save();
	  data.save();
	  assertEquals(false, data.save());
	  Appt appt1 = new Appt(25, 10, 2,2,2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  assertEquals(false,data.saveAppt(appt1));
	  assertEquals(false,data.deleteAppt(appt1));
  }
  
    
	
  @Test
  public void test21() throws Throwable  //try to break datahandler
  {
	  DataHandler data3 = new DataHandler(" 0 1 0 1 ...!@&*#*!@#!*@#!#^&*!#????****^*&!@&^#*!@^&#!++++++><<", false);
	  GregorianCalendar today = new GregorianCalendar(2218,7-2,21);
	  GregorianCalendar future = new GregorianCalendar(2218,8-2,29);
	  data3.save();
	  data3.save();
	  data3.save();
	  data3.getApptRange(today,future);
	  assertNotEquals(null, data3.getApptRange(today,future));
	  
  }
 
  @Test 
  public void test22 () throws Throwable
  {
	 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	 System.setOut(new PrintStream(outContent));
	 DataHandler data3 = new DataHandler(" 0 1 0 1 ...!@&*#*!@#!*@#!#^&*!#????****^*&!@&^#*!@^&#!++++++><<", false);
	 GregorianCalendar today = new GregorianCalendar(2218,7-2,21);
	 GregorianCalendar future = new GregorianCalendar(2218,8-2,29);
	 
	 Appt appt1 = new Appt(25, 30, 25, 7, 2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	 int[] recurDaysArr4={2,3};
     appt1.setRecurrence( recurDaysArr4, Appt.RECUR_BY_MONTHLY, 3, Appt.RECUR_NUMBER_FOREVER);
	 appt1.setValid();
	 boolean save = data3.saveAppt(appt1);
	 assertEquals(true, save);
	 data3.getApptRange(today,future);
	 
     assertEquals("", outContent.toString());  
  }
  
  @Test 
  public void test32() throws Throwable
  {
	  DataHandler data3 = new DataHandler("", false);
	 
	  Appt appt1 = new Appt(25, 30, 25, 7, 2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  appt1.setValid();
	  appt1.setXmlElement(null);
	  appt1.setRecurrence(null, 0, 0, 0);
	  boolean save = data3.saveAppt(appt1);
	  assertEquals(true, save);
	  
  }

  
}