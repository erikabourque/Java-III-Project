package dw317.lib.medication;

import static java.lang.System.out;


public class NDCMedicationTest {

	public static void main(String[] args) 
	{
		testTwoParameterConstructor();
	
	}
	
	public static void testTwoParameterConstructor() 
	{
		testTwoParameterConstructor("Case 1: Valid 1111-2222-33", "1111-2222-33", true);
		testTwoParameterConstructor("Case 2 : Valid 111-12222-33","111-12222-33", true);
		testTwoParameterConstructor("Case 3: Valid 11-22-333333", "11-22-333333",true);
		testTwoParameterConstructor("Case 4 : inValid too many hyphens 1102-3-4-543","1102-3-4-543",false);
		testTwoParameterConstructor("Case 5: invalid not enough hyphens112345-14253","112345-14253",false);
		testTwoParameterConstructor("Case 6: inValid not numbers asd-sfagh-et","asd-sfagh-et",false);
		testTwoParameterConstructor("Case 7: invalid hyphens are next to eachother 112345--1524","112345--1524",false);
		testTwoParameterConstructor("Case 8: invalid hyphen at the beginning -12353627-82", "-12353627-82", false);
		testTwoParameterConstructor("Case 9: invalid hyphen at the end 12353627-82-", "12353627-82-", false);
		testTwoParameterConstructor("Case 10: invalid null", null, false);
		testTwoParameterConstructor("Case 11: invalid empty","             ", false);


		
		
	}
	public static void testTwoParameterConstructor(String testCase, String number, boolean expectValid ) 
	{
		
		String name = "name";
		out.println("   " + testCase);

		try {
			NDCMedication theMed = new NDCMedication(number, name);
			out.print("\tThe NDC Medication instance was created: " + theMed);

			if (!expectValid)
				out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage());
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				out.print(" Expected Valid.");
		}

		out.println("\n");
		
	}
	

}
