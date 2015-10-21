package dw317.lib;

import static java.lang.System.out;

public class AddressTest {

	public static void main(String[] args) {
		testTheThreeParameterConstructor();
		testGetCivicNumber();
		testSetCivicNumber();
		testGetStreetName();
		testSetStreetName();
		testGetCity();
		testSetCity();
		testTheNoParameterConstructor();
	}

	public static void testTheThreeParameterConstructor() {
		out.println("\nTesting the three parameter constructor.");
		testTheThreeParameterConstructor(
				"Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040",
				"Sherbrooke", "Westmount", true);
		testTheThreeParameterConstructor(
				"Case 2 - Invalid data – empty civicNumber ( Sherbrooke Westmount)",
				"", "Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 3 - Invalid data – civic number all spaces (     Sherbrooke Westmount)",
				"    ", "", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 4 - Invalid data – empty streetName (3040  Westmount)",
				"3040", "", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 5 - Invalid data – empty city (3040 Sherbrooke )",
				"3040", "Sherbrooke", "", false);
		testTheThreeParameterConstructor(
				"Case 6 - Invalid data – streetName all spaces (3040     Westmount)",
				"3040", "   ", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 7 - Invalid data – city all spaces (3040 Sherbrooke    )",
				"3040", "Sherbrooke", "   ", false);
		testTheThreeParameterConstructor(
				"Case 8 - Invalid data – null civicNumber (null Sherbrooke Westmount)",
				null, "Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 9 - Invalid data – null streetName (3040 null Westmount)",
				"3040", null, "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 10 - Invalid data – null city (3040 Sherbrooke null)",
				"3040", "Sherbrooke", null, false);
	}

	private static void testTheThreeParameterConstructor(String testCase,
			String civicNumber, String streetName, String city,
			boolean expectValid) {

		out.println("   " + testCase);

		try {
			Address theAddress = new Address(civicNumber, streetName, city);
			out.print("\tThe Address instance was created: " + theAddress);

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

	public static void testGetCivicNumber() {
		out.println("\nTesting the getCivicNumber method.");
		testGetCivicNumber("Case 1: 3040 without leading/trailing spaces",
				"3040", "3040");
		testGetCivicNumber("Case 2: 3040 with leading/trailing spaces",
				"    3040    ", "3040");
	}

	private static void testGetCivicNumber(String testCase, String civicNumber,
			String expectedCivicNumber) {
		out.println("   " + testCase);
		Address theAddress = new Address(civicNumber, "Sherbrooke", "Westmount");
		out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

	public static void testSetCivicNumber() {
		out.println("\nTesting the setCivicNumber method.");
		testSetCivicNumber(
				"Case 1: Valid - 2086 without leading/trailing spaces", "2086",
				"2086", true);
		testSetCivicNumber("Case 2: Invalid null civic number", null, "", false);
		testSetCivicNumber("Case 2: Valid 2086 with leading/trailing spaces",
				"   2086   ", "2086", true);
	}

	private static void testSetCivicNumber(String testCase, String civicNumber,
			String expectedCivicNumber, boolean expectValid) {
		out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setCivicNumber(civicNumber);
			out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
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

	public static void testGetStreetName() {
		out.println("\nTesting the getStreetName method.");
		testGetStreetName("Case 1: Sherbrooke without leading/trailing spaces",
				"Sherbrooke", "Sherbrooke");
		testGetStreetName("Case 2: Sherbrooke with leading/trailing spaces",
				"    Sherbrooke    ", "Sherbrooke");
	}

	private static void testGetStreetName(String testCase, String streetName,
			String expectedStreetName) {
		out.println("   " + testCase);
		Address theAddress = new Address("3040", streetName, "Westmount");
		out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getStreetName().equals(expectedStreetName))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

	public static void testSetStreetName() {
		out.println("\nTesting the setStreetName method.");
		testSetStreetName(
				"Case 1: Valid - Wolfe without leading/trailing spaces",
				"Wolfe", "Wolfe", true);
		testSetStreetName("Case 2: Invalid null street name", null, "", false);
		testSetStreetName("Case 2: Valid Wolfe with leading/trailing spaces",
				"   Wolfe   ", "Wolfe", true);
	}

	private static void testSetStreetName(String testCase, String streetName,
			String expectedStreetName, boolean expectValid) {
		out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setStreetName(streetName);
			out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getStreetName().equals(expectedStreetName))
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

	public static void testGetCity() {
		out.println("\nTesting the getCity method.");
		testGetCity("Case 1: Westmount without leading/trailing spaces",
				"Westmount", "Westmount");
		testGetCity("Case 2: Westmount with leading/trailing spaces",
				"    Westmount    ", "Westmount");
	}

	private static void testGetCity(String testCase, String city,
			String expectedCity) {
		out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", city);
		out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCity().equals(expectedCity))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

	public static void testSetCity() {
		out.println("\nTesting the setCity method.");
		testSetCity(
				"Case 1: Valid - Longueuil without leading/trailing spaces",
				"Longueuil", "Longueuil", true);
		testSetCity("Case 2: Invalid null city", null, "", false);
		testSetCity("Case 2: Valid Longueuil with leading/trailing spaces",
				"   Longueuil   ", "Longueuil", true);
	}

	private static void testSetCity(String testCase, String city,
			String expectedCity, boolean expectValid) {
		out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setCity(city);
			out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getCity().equals(expectedCity))
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
	
	public static void testTheNoParameterConstructor() {
		out.println("\nTesting the no parameter constructor.");
		try {
			Address addressRef = new Address();
			out.print("\tThe Address instance was created: " + addressRef);
			
			int length = addressRef.getCivicNumber().length();
			out.print("\tThe length of civic number is: " + length);
		} catch (NullPointerException npe) {
			out.print("\t" + npe.getMessage());
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
		}

		out.println("\n");
	}
}
