
package finalprojectB;

import junit.framework.TestCase;
import java.util.Random;
import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Test;
import java.io.*;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {

	private static final long TestTimeout = 3000; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   @Test   
   public void testManualTest()
   {

		UrlValidator urlDefault = new UrlValidator();   //test the normal default constructor
		System.out.println(urlDefault.isValid(null));   //Note: (possible bug) All cases fails for the default constructor. 
		System.out.println(urlDefault.isValid("Work"));
		System.out.println(urlDefault.isValid("http:Work"));
		System.out.println(urlDefault.isValid("http://minigo.com"));
		System.out.println(urlDefault.isValid("http:minigo.com"));
		System.out.println(urlDefault.isValid("https://minigo.com"));
		System.out.println(urlDefault.isValid("https:/minigo.com"));
		System.out.println(urlDefault.isValid("https:minigo.com"));
		System.out.println(urlDefault.isValid("https:minigo.com//"));
		System.out.println(urlDefault.isValid("https://www.minigo.com"));
		System.out.println(urlDefault.isValid("http://www.minigo.com"));
		System.out.println(urlDefault.isValid("http://www.google.com/"));
		System.out.println(urlDefault.isValid("https://www.google.com/"));
		System.out.println(urlDefault.isValid("http://www.google.com"));
		System.out.println(urlDefault.isValid("www.google.com"));
		System.out.println(urlDefault.isValid("google.com"));
		System.out.println(urlDefault.isValid("http://www.google.com//"));
		System.out.println(urlDefault.isValid("http://www.google.COM//"));
		System.out.println(urlDefault.isValid("http://WWW.GOOOGLE.COM//"));
		System.out.println(urlDefault.isValid("ftp:/WWW.GOOOGLE.COM//"));
		System.out.println(urlDefault.isValid("ftp:/WWW.G    OOO      GLE.COM//"));
		System.out.println(urlDefault.isValid("http://WWW.GOOOGLE.COM//http://WWW.GOOOGLE.COM//"));
		System.out.println(urlDefault.isValid("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?"));
		System.out.println(urlDefault.isValid("http://www.google.com/port"));
		
		System.out.println("End of default testing");
		System.out.println("");
		
		UrlValidator urlScheme = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);   //test the constructor with ALLOW_ALL_SCHEMES flag on
		System.out.println(urlScheme.isValid(null));
		System.out.println(urlScheme.isValid("Work"));
		System.out.println(urlScheme.isValid("http:Work"));
		System.out.println(urlScheme.isValid("http://minigo.com"));
		System.out.println(urlScheme.isValid("http:minigo.com"));
		System.out.println(urlScheme.isValid("https:/minigo.com"));
		System.out.println(urlScheme.isValid("https:minigo.com"));
		System.out.println(urlScheme.isValid("https:minigo.com//"));
		System.out.println(urlScheme.isValid("http://www.minigo.com"));
		System.out.println(urlScheme.isValid("http://www.google.com/"));	
		System.out.println(urlScheme.isValid("http://www.google.com"));
		System.out.println(urlScheme.isValid("www.google.com"));
		System.out.println(urlScheme.isValid("google.com"));
		System.out.println(urlScheme.isValid("http://www.google.com//"));
		System.out.println(urlScheme.isValid("http://www.google.COM//"));
		System.out.println(urlScheme.isValid("http://WWW.GOOOGLE.COM//"));
		System.out.println(urlScheme.isValid("ftp:/WWW.GOOOGLE.COM//"));
		System.out.println(urlScheme.isValid("ftp:/WWW.G    OOO      GLE.COM//"));
		System.out.println(urlScheme.isValid("http://WWW.GOOOGLE.COM//http://WWW.GOOOGLE.COM//"));
		System.out.println(urlScheme.isValid("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?"));
		System.out.println(urlScheme.isValid("http:/WWW.GOOOGLE.COM/"));
		System.out.println(urlScheme.isValid("http://www.google.com/port"));
		System.out.println(urlScheme.isValid("http://www.google.com/port/"));
		System.out.println(urlScheme.isValid("http://www.google.com/port//"));
		System.out.println(urlScheme.isValid("http://www.google.com/port//"));
		System.out.println(urlScheme.isValid("http://www.google.com/port/edit/"));
																				//bug: any scheme besides "http" will crash the program if there it is proper format. E.g. https://www.google.com/ 
		//	System.out.println(urlScheme.isValid("https://minigo.com"));        //code that crashes program. They are commented out below
		//	System.out.println(urlScheme.isValid("https://www.minigo.com"));
		//	System.out.println(urlScheme.isValid("https://www.google.com/"));
		
		System.out.println("End of allow_all_scheme testing"); 
		System.out.println("");
		
											           
		String[] schemes = {"HTTP", "http", "https"};                       //testing urls with allow_2_slashes and allow all schemes flags on.
		UrlValidator urlScheme2 = new UrlValidator(schemes, UrlValidator.ALLOW_2_SLASHES+UrlValidator.ALLOW_ALL_SCHEMES);
		System.out.println(urlScheme2.isValid("http://www.google.com/"));
		System.out.println(urlScheme2.isValid("http://www.google.com/port"));
		System.out.println(urlScheme2.isValid("http://www.google.com/port/"));
		System.out.println(urlScheme2.isValid("http://www.google.com/port//"));
		System.out.println(urlScheme2.isValid("http://www.google.com//port"));
		System.out.println(urlScheme2.isValid("http://www.google.com/port/edit/"));
		//	System.out.println(urlScheme2.isValid("https://www.google.com/"));  //commented out code crashes program
		//	System.out.println(urlScheme.isValid("ftp://www.google.com/"));
		//	System.out.println(urlScheme2.isValid("HTTP://www.google.com//"));
		
		System.out.println("End of allow_all_scheme + allow 2 slash testing"); 
		System.out.println(""); 
	
    }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   
   
   
   
										   
   
   private String return_random_symbol_string() //generate a random string
   {											//possible chars to generate (32 - 126)  	
   
	     Random rand = new Random(); 
	     int length = rand.nextInt(50);
	     int random_char;
	     String s_value = "";
	     for (int i = 0; i < length; i++)
	     {
			random_char = rand.nextInt((126-32)+1)+32;
		   
		    s_value = s_value + Character.toString ((char) random_char);
	     }
	     return s_value;
   }
   
											
    private String return_random_string()  //generate random alphabet char only string   (65-90) + (97-121)
   {
	   
	     Random rand = new Random(); 
	     int length = rand.nextInt(50);
	     int random_char;
	     String s_value = "";
	     for (int i = 0; i < length; i++)
	     {
			do
			{
				random_char = rand.nextInt((121-65)+1)+65;
		    }
			while (random_char > 90 && random_char < 97);
		    s_value = s_value + Character.toString ((char) random_char);
	     }
	     return s_value;
   }
   
   @Test(timeout = 4000)      //build a random url and test it against the validator with different flags on 
   public void testIsValid()
   {
								
												//15 scheme values that the random generator will pick from. http is included multiple times to increase weight of it. 
	    String[] schemes = {"HTTP", "http", "https", "http", "http", "https", "https", " sadasd", "123211", "$209321", "    ", "", " ", "AAA", "HttP"};
		String[] connection_slash = {"/","/","/","/",":",""," "};
		String[] connection_semi = {":",":",":",":","/",""," "};
		String[] end = {".com", ".com", ".com", ".com", ".net", ".gov", "com", "fajds;kjdls",""," "}; 
		
		int r_scheme;
		int r_slash;
		int r_semi;
		int r_end;
		int r_input_type;
		String input;	
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Start Random testing...");

		
		String valid_schemes[] = {"http", "https", "ftp", "HTPP"};
		UrlValidator urlDefault = new UrlValidator();   //test the normal default constructor
		UrlValidator urlSchemeAll = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);   //test the constructor with flags on
		UrlValidator urlSchemeSlash = new UrlValidator(valid_schemes, UrlValidator.ALLOW_2_SLASHES+UrlValidator.ALLOW_ALL_SCHEMES);
		UrlValidator urlSchemeFrag = new UrlValidator(valid_schemes, UrlValidator.NO_FRAGMENTS+UrlValidator.ALLOW_ALL_SCHEMES);
		UrlValidator urlSchemeLocal = new UrlValidator(valid_schemes, UrlValidator.ALLOW_LOCAL_URLS+UrlValidator.ALLOW_ALL_SCHEMES);
		UrlValidator urlSchemeAllFlags = new UrlValidator(valid_schemes, UrlValidator.ALLOW_LOCAL_URLS+UrlValidator.ALLOW_ALL_SCHEMES+UrlValidator.ALLOW_2_SLASHES+UrlValidator.NO_FRAGMENTS);
		
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) 
			{
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				
				Random random = new Random(randomseed);
				

				for (int i = 0; i < NUM_TESTS; i++) 
				{
				
					r_scheme = random.nextInt(15);    //randomly pick from pool
					r_slash = random.nextInt(7);
					r_semi = random.nextInt(7);
					r_end = random.nextInt(10);
					r_input_type = random.nextInt(5);
					
					if (r_input_type == 1)   //construct url string
						input = schemes[r_scheme] + connection_semi[r_semi] + connection_slash[r_slash] + connection_semi[r_slash] + return_random_string() + end[r_end] + connection_slash[r_slash];
					else if (r_input_type == 2)
						input = schemes[r_scheme] + connection_semi[r_semi] + connection_slash[r_slash] + connection_semi[r_slash] + return_random_symbol_string() + end[r_end] + connection_slash[r_slash];
					else if (r_input_type == 3)
						input = schemes[r_scheme] + connection_semi[r_semi] + connection_slash[r_slash] + connection_semi[r_slash] + return_random_symbol_string() + end[r_end] + connection_slash[r_slash] + return_random_string();
					else 
						input = schemes[r_scheme] + connection_semi[r_semi] + connection_slash[r_slash] + connection_semi[r_slash] + return_random_symbol_string() + end[r_end] + connection_slash[r_slash] + return_random_symbol_string();
					
					System.out.println(input);
					
					urlDefault.isValid(input); //test each validators with different flags
					System.out.println("Passed default");
					urlSchemeAll.isValid(input);
					System.out.println("Passed all schemes flag");
					urlSchemeSlash.isValid(input);
					System.out.println("Passed all schemes + 2 slash flag");
					urlSchemeFrag.isValid(input);
					System.out.println("Passed all schemes + fragment flag");
					urlSchemeLocal.isValid(input);
					System.out.println("Passed all schemes + local flag");
					urlSchemeAllFlags.isValid(input);
					System.out.println("Passed all flags");
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%100000)==0 && iteration!=0 )
			        System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}
		}catch(NullPointerException e)
		{
			
		}
	 
		System.out.println("Done testing...");
	 
   }

}
