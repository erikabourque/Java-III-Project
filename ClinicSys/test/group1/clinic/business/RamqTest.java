package group1.clinic.business;

import static java.lang.System.out;
import dw317.lib.Name;

public class RamqTest {

	public static void main(String[] args) {
		testOneParamConstructor();
		testTwoParamConstructor();
		testGetBirthdate();
		testGetGender();
		testGetRamq();
		testCompareTo();
		testEquals();
	}
	
	public static void testOneParamConstructor(){
		out.print("\nTesting the one parameter constructor");
		
		testOneParamConstructor("Case 1-Valid Male-XXYY01010122", "XXYY01010122", true);
		testOneParamConstructor("Case 2-Valid Female-XXYY01510122", "XXYY01510122", true);
		testOneParamConstructor("Case 3-Valid Female-XXYY01610122", "XXYY01610122", true);
		testOneParamConstructor("Case 4-Invalid Length-XXY01010122", "XXY01010122", false);
		testOneParamConstructor("Case 5-Invalid digits in name-1XYY01510122", "1XYY01010122", false);
		testOneParamConstructor("Case 6-Invalid birthday month not in calender year-XXYY01130122",
				"XXYY01130122", false);
		testOneParamConstructor("Case 7-Invalid birthday day not in calender year-XXYY01016122",
				"XXYY01016122", false);
		testOneParamConstructor("Case 8-Invalid Gender identifier-XXYY01710122", "XXYY01710122", false);
		testOneParamConstructor("Case 9-Invalid administrative numbers-XXYY0101012A", "XXYY0101012A", false);
		testOneParamConstructor("Case 10-Testing to UpperCase Male-xxyy01010122", "xxyy01010122", true);
		testOneParamConstructor("Case 11-Testing to UpperCase Female-xxyy01510122", "xxyy01510122", true);
	}
	
	public static void testOneParamConstructor(String testCase, String ramq, boolean expectValid){
		out.print("\n" + testCase + "\n");
		
		try{
			Ramq theRamq = new Ramq(ramq);
			out.print("\tThe Ramq instance was created: " + theRamq + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}

	public static void testTwoParamConstructor(){
		out.print("\nTesting the two parameter constructor");
		testTwoParamConstructor("Case 1-Valid Male JOHN DOE-DOEJ01010122", "DOEJ01010122",
				new Name("JOHN", "DOE"), true);
		testTwoParamConstructor("Case 2-Valid Female JANE SMITH-SMIJ01510122", "SMIJ01510122",
				new Name("JANE", "SMITH"), true);
		testTwoParamConstructor("Case 3-Valid Male short last name JOHN DO-DOJO01010122", "DOJO01010122",
				new Name("JOHN", "DO"), true);
		testTwoParamConstructor("Case 4-Valid Female short last name JANE DO-DOJA01510122", "DOJA01510122",
				new Name("JANE", "DO"), true);
		testTwoParamConstructor("Case 5-Invalid last name JOHN DOE-DOJO01010122", "DOJO01010122",
				new Name("JOHN", "DOE"), false);
		testTwoParamConstructor("Case 6-Invalid first name JOHN DOE-DOEA01010122", "DOEA01010122",
				new Name("JOHN", "DOE"), false);
		testTwoParamConstructor("Case 7-Invalid short last name JOHN DO-DAJO01010122", "DAJO01010122",
				new Name("JOHN", "DOE"), false);
		testTwoParamConstructor("Case 8-Invalid short first name JOHN DOE-DOJA01010122", "DOJA01010122",
				new Name("JOHN", "DOE"), false);
		testTwoParamConstructor("Case 9-LowerCase ramq UpperCase Name Male JOHN DOE-doej01010122", "doej01010122",
				new Name("JOHN", "DOE"), true);
		testTwoParamConstructor("Case 10-LowerCase Name UpperCase Ramq Male john doe-DOEJ01010122", "DOEJ01010122",
				new Name("john", "doe"), true);
		testTwoParamConstructor("Case 11-All LowerCase Name Ramq Male john doe-doej01010122", "doej01010122",
				new Name("john", "doe"), true);
	}
	
	public static void testTwoParamConstructor(String testCase, String ramq, Name name, boolean expectValid){
		out.print("\n" + testCase + "\n");
		
		try{
			Ramq theRamq = new Ramq(ramq, name);
			out.print("\tThe Ramq instance was created: " + theRamq + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}
	
	public static void testGetBirthdate(){
		out.print("\nTesting the getBirthday method");
		testGetBirthdate("Case 1-Valid Male 2001-01-01-DOEJ01010122", "DOEJ01010122", true);
		testGetBirthdate("Case 2-Valid Female 2001-01-01-DOEJ01510122", "DOEJ01510122", true);
		testGetBirthdate("Case 3-Valid Female 2001-11-01-DOEJ01610122", "DOEJ01610122", true);
		testGetBirthdate("Case 4-Valid Female 2001-10-01-DOEJ01600122", "DOEJ01600122", true);
		testGetBirthdate("Case 5-Valid Male older 1997-01-01-DOEJ97010122", "DOEJ97010122", true);
		testGetBirthdate("Case 6-Valid Male older year limits 1916-01-01-DOEJ16010122", "DOEJ16010122", true);
		testGetBirthdate("Case 7-Valid Male younger year limits 2015-01-01-DOEJ15010122", "DOEJ15010122", true);
	}
	
	public static void testGetBirthdate(String testCase, String ramq, boolean expectValid){
		out.print("\n\n" + testCase + "\n");
		
		try{
			Ramq theRamq = new Ramq(ramq);
			out.print("\tThe Ramq instance was created: " + theRamq + "\n");
			out.print("\tThe birthdate is: " + theRamq.getBirthdate() + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}

	public static void testGetGender(){
		out.print("\nTesting the getBirthday method");
		testGetGender("Case 1-Valid Male Month 01-DOEJ01010122", "DOEJ01010122", true);
		testGetGender("Case 1-Valid Male Month 11-DOEJ01110122", "DOEJ01110122", true);
		testGetGender("Case 1-Valid Female Month 01-DOEJ01510122", "DOEJ01510122", true);
		testGetGender("Case 1-Valid Female Month 11-DOEJ01610122", "DOEJ01610122", true);
	}
	
	public static void testGetGender(String testCase, String ramq, boolean expectValid){
		out.print("\n\n" + testCase + "\n");
		
		try{
			Ramq theRamq = new Ramq(ramq);
			out.print("\tThe Ramq instance was created: " + theRamq + "\n");
			out.print("\tThe gender is: " + theRamq.getGender() + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}
	
	public static void testGetRamq(){
		out.print("\nTesting the getRamq method");
		testGetRamq("Case 1-Valid Male-XXYY01010122", "XXYY01010122", true);
		testGetRamq("Case 2-Valid Female-XXYY01510122", "XXYY01510122", true);
		testGetRamq("Case 3-Valid Female-XXYY01610122", "XXYY01610122", true);
	}
	
	public static void testGetRamq(String testCase, String ramq, boolean expectValid){
		out.print("\n\n" + testCase + "\n");
		
		try{
			Ramq theRamq = new Ramq(ramq);
			out.print("\tThe Ramq instance was created: " + theRamq + "\n");
			out.print("\tThe returned Ramq is: " + theRamq.getRamq() + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}

	public static void testCompareTo(){
		out.print("\nTesting the compareTo method");
		Ramq ramq1 = new Ramq("XXYY01010122"); 
		testCompareTo("Case 1-Different Ramq XXYY01010122 vs XXYY01510122", new Ramq("XXYY01010122"),
				new Ramq("XXYY01510122"), true);
		testCompareTo("Case 2-Same Ramq Digits XXYY01010122 vs XXYY01010122", new Ramq("XXYY01010122"),
				new Ramq("XXYY01010122"), true);
		testCompareTo("Case 3-Same Ramq Pointer? XXYY01010122 vs XXYY01010122", ramq1,
				new Ramq(ramq1.getRamq()), true);
		testCompareTo("Case 4-One empty constructor XXYY01010122 vs empty string", new Ramq("XXYY01010122"),
				new Ramq(), true);
	}
	
	public static void testCompareTo(String testCase, Ramq ramq1, Ramq ramq2, boolean expectValid){
		out.print("\n\n" + testCase + "\n");
		
		try{
			out.print("\tThe first Ramq instance was created: " + ramq1 + "\n");
			out.print("\tThe Second Ramq instance was created: " + ramq2 + "\n");
			out.print("\tThe comparison result is: " + ramq1.compareTo(ramq2) + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}
	
	public static void testEquals(){
		out.print("\nTesting the equals method");
		Ramq ramq1 = new Ramq("XXYY01010122"); 
		testEquals("Case 1-Different Ramq XXYY01010122 vs XXYY01510122", new Ramq("XXYY01010122"),
				new Ramq("XXYY01510122"), true);
		testEquals("Case 2-Same Ramq Digits XXYY01010122 vs XXYY01010122", new Ramq("XXYY01010122"),
				new Ramq("XXYY01010122"), true);
		testEquals("Case 3-Same Ramq Pointer? XXYY01010122 vs XXYY01010122", ramq1,
				new Ramq(ramq1.getRamq()), true);
		testEquals("Case 4-One empty constructor XXYY01010122 vs empty string", new Ramq("XXYY01010122"),
				new Ramq(), true);
	}
	
	public static void testEquals(String testCase, Ramq ramq1, Ramq ramq2, boolean expectValid){
		out.print("\n\n" + testCase + "\n");
		
		try{
			out.print("\tThe first Ramq instance was created: " + ramq1 + "\n");
			out.print("\tThe Second Ramq instance was created: " + ramq2 + "\n");
			out.print("\tThe equals result is: " + ramq1.equals(ramq2) + "\n");
			if (!expectValid){
				out.print("Error! Expected Invalid. ==== FAILED TEST ====\n");
			}
		}
		catch (IllegalArgumentException iae){
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid){
				out.print("Error! Expected Valid. ==== FAILED TEST ====\n");
			}
				
		}
		catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
			if (expectValid)
				out.print("\tExpected Valid.");
		}
	}
}
