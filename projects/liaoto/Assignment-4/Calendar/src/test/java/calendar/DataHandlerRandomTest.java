package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;
import calendar.CalDay;
import java.util.GregorianCalendar;
import java.util.*;
import calendar.DataHandler;
import java.io.IOException;
import java.lang.Exception;


/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
    /**
     * Generate Random Tests that tests DataHandler Class.
     */
private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	   public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setTitle","setRecurrence"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	

	 @Test
	  public void randomDeleteAppt()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				boolean saveBeforeDelete = ValuesGenerator.getBoolean(.5f,random);
				String filename = ValuesGenerator.getString(random);;
				boolean autosave = ValuesGenerator.getBoolean(.5f,random);
				DataHandler data = new DataHandler(filename, autosave);
		
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random, 1, 24);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 60);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 31);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, -5, 10);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 String emailAddress="xyz@gmail.com";
				 
				  Appt appt = new Appt(startHour,
		                  startMinute ,
		                  startDay ,
		                  startMonth ,
		                  startYear ,
		                  title,
		                 description,
		                 emailAddress);

				 //Construct a new Appointment object with the initial data	 
				 //Construct a new Appointment object with the initial data	 
		    
				 GregorianCalendar today = new GregorianCalendar(1900,3,25);
				 CalDay day = new CalDay(today);
				 
				if (saveBeforeDelete == true)
					data.saveAppt(appt);
			
				for (int i = 0; i < NUM_TESTS; i++) {
						data.deleteAppt(appt);
						appt.setValid();
						
						elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
						if((iteration%10000)==0 && iteration!=0 )
							System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
					}
				
			 
				}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }
	 
	 
	  @Test
	  public void randomGetApptRanges()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				boolean saveBeforeDelete = ValuesGenerator.getBoolean(.5f,random);
				String filename = ValuesGenerator.getString(random);;
				boolean autosave = ValuesGenerator.getBoolean(.5f,random);
				DataHandler data = new DataHandler(filename, autosave);
		
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random, 1, 24);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 60);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 31);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, -5, 10);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 String emailAddress="xyz@gmail.com";
				 

				 //Construct a new Appointment object with the initial data	 
				 //Construct a new Appointment object with the initial data	 
				 
				 int todayStartMonth=ValuesGenerator.getRandomIntBetween(random, -5, 14);
				 int todayStartYear=ValuesGenerator.getRandomIntBetween(random, -5, 10);
				 int todayStartDay=ValuesGenerator.getRandomIntBetween(random, -5, 35);
				 
				 int futureStartMonth=ValuesGenerator.getRandomIntBetween(random, -5, 14);
				 int futureStartDay=ValuesGenerator.getRandomIntBetween(random, -5, 35);
				 int futureStartYear=ValuesGenerator.getRandomIntBetween(random, -5, 10);
		    
				 GregorianCalendar today = new GregorianCalendar(todayStartYear,todayStartMonth,todayStartYear);
				 GregorianCalendar future = new GregorianCalendar(futureStartYear,futureStartMonth,futureStartDay);
				 
				 Appt appt = new Appt(futureStartDay-ValuesGenerator.getRandomIntBetween(random, 1, 10),startMonth-ValuesGenerator.getRandomIntBetween(random, 1, 10),startYear-ValuesGenerator.getRandomIntBetween(random, 1, 10),title,description,emailAddress);
				 Appt appt2 = new Appt(futureStartDay-ValuesGenerator.getRandomIntBetween(random, 1, 10),startMonth-ValuesGenerator.getRandomIntBetween(random, 1, 10),startYear-ValuesGenerator.getRandomIntBetween(random, 1, 10),title,description,emailAddress);
				 
				 if(!appt.getValid())continue;
				 for (int i = 0; i < 20; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("setTitle")){
						   String newTitle=(String) ValuesGenerator.getString(random);
						   appt.setTitle(newTitle);						   
						}
					   else if (methodName.equals("setRecurrence")){
						   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						   int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						   int recur=ApptRandomTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						}				
				}
				
				 CalDay day = new CalDay(today);
				 
				 
				for (int i = 0; i < NUM_TESTS; i++) {
						
						try{
							data.saveAppt(appt);
							data.saveAppt(appt2);
							data.getApptRange(today,future);
						}
						catch (DateOutOfRangeException d)
						{
						}
						
						elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
						if((iteration%10000)==0 && iteration!=0 )
							System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
					}
				
			
			 
				}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }

	
}
