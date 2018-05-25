/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
import org.w3c.dom.Element;
import java.io.*;

public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }
	
  @Test
  public void test02() //Tests introduced bug 1
  {
												//setValid will make the appointment false because of the bug in setValid if less than 23. It will be false
	  Appt appt0 = new Appt(-1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertEquals(false, appt0.getValid());
  }
  
  @Test
  public void test02A() //Tests introduced bug 1
  {
												//setValid will make the appointment false because of the bug in setValid if less than 23. It will be false
	  Appt appt0 = new Appt(5, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertEquals(false, appt0.getValid());
  }
  
  @Test
  public void test03() //Tests introduced bug 2
  {
												//setValid will make the appointment true because of the bug in setValid regardless of where the day lies in the month
	  Appt appt0 = new Appt(26, 30, 0, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertEquals(true, appt0.getValid());
	  
	  Appt appt1 = new Appt(26, 30, 1010, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  assertEquals(true, appt1.getValid());
  }
  
    @Test 
  public void test03A() //Catch mutations
  {
											
	  Appt apptMonth = new Appt(1, 30, 3, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  apptMonth.setValid();
	  assertEquals(false, apptMonth.getValid()); 
	  
	  Appt apptXML = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); //toString 
	  assertEquals(null, apptXML.getXmlElement()); 
	  
  }
  
    @Test 
  public void test03B() //Catch mutations setValid 
  {
	  
	  Appt appt1 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); //toString 
	  assertNotEquals(null, appt1.toString()); 
	  
	  Appt appt2 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  Appt appt3 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); 
	  Appt appt4 = new Appt(25, 30, 5, 5, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); 

	  appt1.setStartMinute(59);   //minutes
	  appt1.setValid();
	  assertEquals(true, appt1.getValid()); 
	  
	  appt1.setStartMinute(0);
	  appt1.setValid();
	  assertEquals(true, appt1.getValid()); 
	  
	  int NumDaysInMonth = CalendarUtil.NumDaysInMonth(appt2.getStartYear(), appt2.getStartMonth() - 1);
	  
	  appt2.setStartDay(NumDaysInMonth); //Days
	  appt2.setValid();
	  assertEquals(true, appt2.getValid()); 
	  
	  appt2.setStartDay(1);
	  appt2.setValid();
	  assertEquals(true, appt2.getValid()); 
	  
  
	  appt3.setStartMonth(1); //Months
	  appt3.setValid();
	  assertEquals(true, appt3.getValid()); 
	  
	  appt3.setStartMonth(12);
	  appt3.setValid();
	  assertEquals(true, appt3.getValid()); 
	  
	  
	  appt4.setStartHour(23); //hours
	  appt4.setValid();
	  assertEquals(true, appt4.getValid()); 
	  
	  appt4.setStartHour(0); //hours
	  appt4.setValid();
	  assertEquals(false, appt4.getValid()); 
	  
	  appt4.setStartHour(12); //hours
	  appt4.setValid();
	  assertEquals(false, appt4.getValid()); 
	  
	  appt4.setXmlElement(null);
	  assertEquals(null, appt4.getXmlElement());
	 

  }
  
  
  @Test
  public void test04() //test remainder setValid
  {
	  Appt appt0 = new Appt(26, 61, 2, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); //minutes branch
	  appt0.setValid();
	  assertEquals(false, appt0.getValid());
	  
	  Appt appt1 = new Appt(26, 46, 2, 3, -110100101, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  assertEquals(false, appt1.getValid());
	  
	  Appt appt3 = new Appt(26, 46, 2, 3, 0, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt3.setValid();
	  assertEquals(false, appt3.getValid());
	  
	   Appt appt4 = new Appt(26, -1, 2, 3, 0, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt4.setValid();
	  assertEquals(false, appt4.getValid());
	  
	  
  }
  
   @Test
   public void test05() //test setTitle
   {
	  Appt appt0 = new Appt(26, 22, 2, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); //minutes branch
	  appt0.setTitle(null);
	  assertEquals("", appt0.getTitle());
	  
	  Appt appt1 = new Appt(26, 22, 2, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setTitle("CS362");
	  assertEquals("CS362", appt1.getTitle());
	  
	  
   }
  
   @Test
   public void test06() //test setDescriptor
   {
	  Appt appt0 = new Appt(26, 22, 2, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com"); 
	  appt0.setDescription(null);
	  assertEquals("", appt0.getDescription());
	  
	  Appt appt1 = new Appt(26, 22, 2, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt1.setDescription("CS362");
	  assertEquals("CS362", appt1.getDescription());
	  
	  
   }
  
   @Test
   public void test07() //test setEmailAddress
   {
	  Appt appt0 = new Appt(26, 22, 2, 3, 2018, "Birthday Party", "This is my birthday party", null); 
	  assertEquals("", appt0.getEmailAddress());
	  
	  Appt appt1 = new Appt(26, 22, 2, 3, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  assertEquals("xyz@gmail.com", appt1.getEmailAddress());
	  
	  
   }
   
   @Test
   public void test08() //test bug 3 and represntationApp()
   {
	  Appt appt0 = new Appt(13, 22, 22, 2, 2222, "Birthday Party", "This is my birthday party", "cs362@gmail.com"); 
	  
	  assertNotEquals(-1, appt0.toString().indexOf('1'));
	  
	  
	  
	  Appt appt1 = new Appt(0, 22, 2, 11, 2222, "Birthday Party", "This is my birthday party", "cs362@gmail.com"); //there is a 0 in this because of the bug 3

	  assertNotEquals(-1, appt1.toString().indexOf('0'));
	  
	  Appt appt2 = new Appt(11, 22, 2, 22, 2222, "Birthday Party", "This is my birthday party", "cs362@gmail.com"); 
	  
	  assertEquals(14, appt2.toString().indexOf('1'));
	  assertEquals(15, appt2.toString().indexOf('1')+1);
	  
   }
   
   @Test
   public void test09()
   {
	   Appt appt0 = new Appt(-1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //this section of branch doesn't return
	   appt0.toString();
	   
   }
   
    @Test
   public void test10() //test setRecurDay function
   {
	   Appt appt0 = new Appt(1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //this branch doesn't return
	   appt0.setRecurrence(null, 1, 1, 1);
	   assertEquals(0,appt0.getRecurDays().length);
	   
	   int test_array[] = new int[]{1};
	   Appt appt1 = new Appt(1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //this branch doesn't return
	   appt1.setRecurrence(test_array, 1, 1, 1);
	   assertEquals(1,appt1.getRecurDays().length);
	   
   }
   
       @Test
   public void test10A() //test setRecurDay getters 
   {
	   
	   int test_array[] = new int[]{1};
	   Appt appt1 = new Appt(1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //this branch doesn't return
	   appt1.setRecurrence(test_array, 1, 1, 1);
	   
	   assertEquals(true, appt1.isRecurring());
	   assertEquals(1, appt1.getRecurIncrement());
	   assertEquals(1, appt1.getRecurBy());
	   assertEquals(1, appt1.getRecurNumber());
	   
   }
   
   
    @Test
   public void test11() //setters and getters
   {
	   Appt appt0 = new Appt(1, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	   appt0.setStartHour(10);
	   assertEquals(10,appt0.getStartHour());
	   
	   appt0.setStartMinute(11);
	   assertEquals(11,appt0.getStartMinute());
	   
	   appt0.setStartDay(12);
	   assertEquals(12,appt0.getStartDay());
	   
	   appt0.setStartMonth(7);
	   assertEquals(7,appt0.getStartMonth());
	   
	   appt0.setStartYear(2000);
	   assertEquals(2000,appt0.getStartYear());
	   
   }
   
  @Test
  public void test12()   //tests no time appointment
  {
	  Appt appt0 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  assertEquals(true, appt0.getValid());
  }
  
  @Test 
  public void test13() //test isON
  {
	  Appt appt0 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  assertEquals(true, appt0.isOn(9, 14, 2018));
  }
  
  @Test 
  public void test14() //test hasTimeSet
  {
	  Appt appt0 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  assertEquals(false, appt0.hasTimeSet());
  }
  
   
  @Test 
  public void test15 () throws Throwable
  {
	 ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	 System.setErr(new PrintStream(errContent));
	 
	 Appt appt1 = new Appt(25, 10000, 25, 7, 2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	 appt1.setValid();
	 appt1.toString();
     assertNotEquals("", errContent.toString());  
  }
	
  @Test 
  public void test16 () throws Throwable
  {
	 
	 Appt appt1 = new Appt(25, 10, 25, 7, -2218, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  
	  
	 appt1.setValid();
	 assertEquals(false, appt1.getValid());

  }
 
 
}

	
			 
