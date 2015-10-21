package dw317.lib.medication;
import static java.lang.System.out;


public class DINMedicationTest {

	public static void main(String[] args) {
		
		testTwoParameterConstructor();

	}
	public static void testTwoParameterConstructor() 
	{
		testTwoParameterConstructor("Case 1: Valid all numbers and length of 8 ", "98567950", true);
		testTwoParameterConstructor("Case 2 : invalid all numbers but over length ","123456789", false);
		testTwoParameterConstructor("Case 3: invalid length of 8 but not all digits ", "asd23456",false);
		testTwoParameterConstructor("Case 4 : inValid empty"," ",false);
		testTwoParameterConstructor("Case 5: invalid null","null",false);
		testTwoParameterConstructor("Case 6: inValid space containing digits "," 1 2 3 4",false);
		

		
		
	}
	
	public static void testTwoParameterConstructor(String testCase, String number, boolean expectValid ) 
	{
		
		String name = "name";
		out.println("   " + testCase);

		try {
			DINMedication theMed = new DINMedication(number, name);
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


