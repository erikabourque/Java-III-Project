package dw317.lib;

import static java.lang.System.out;

public class NameTest {

	public static void main(String[] args) {
		testTheTwoParameterConstructor();
		testGetFirstName();
		testSetFirstName();
		testGetLastName();
		testSetLastName();
		testTheNoParameterConstructor();
	}

	public static void testTheTwoParameterConstructor() {
		out.println("\nTesting the two parameter constructor.");
		testTheTwoParameterConstructor("Case 1 - Valid data (John Doe)",
				"John", "Doe", true);
		testTheTwoParameterConstructor(
				"Case 2 - Invalid data – empty first name ( Doe)", "", "Doe",
				false);
		testTheTwoParameterConstructor(
				"Case 3 - Invalid data – first name all spaces (    Doe)",
				"    ", "Doe", false);
		testTheTwoParameterConstructor(
				"Case 4 - Invalid data – empty last name (John  )", "John", "",
				false);
		testTheTwoParameterConstructor(
				"Case 5 - Invalid data – last Name all spaces (John    )",
				"John", "   ", false);
		testTheTwoParameterConstructor(
				"Case 8 - Invalid data – null first name (null Doe)", null,
				"Doe", false);
		testTheTwoParameterConstructor(
				"Case 9 - Invalid data – null last Name (John null)", "John",
				null, false);
	}

	private static void testTheTwoParameterConstructor(String testCase,
			String firstName, String lastName, boolean expectValid) {

		out.println("   " + testCase);

		try {
			Name theName = new Name(firstName, lastName);
			out.print("\tThe Name instance was created: " + theName);

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

	public static void testGetFirstName() {
		out.println("\nTesting the getFirstName method.");
		testGetFirstName("Case 1: John without leading/trailing spaces",
				"John", "John");
		testGetFirstName("Case 2: John with leading/trailing spaces",
				"    John    ", "John");
	}

	private static void testGetFirstName(String testCase, String firstName,
			String expectedFirstName) {
		out.println("   " + testCase);
		Name theName = new Name(firstName, "Doe");
		out.print("\tThe Name instance was created: " + theName);

		if (!theName.getFirstName().equals(expectedFirstName))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

	public static void testSetFirstName() {
		out.println("\nTesting the setFirstName method.");
		testSetFirstName(
				"Case 1: Valid - Oliver without leading/trailing spaces",
				"Oliver", "Oliver", true);
		testSetFirstName("Case 2: Invalid null first name", null, "", false);
		testSetFirstName("Case 2: Valid Oliver with leading/trailing spaces",
				"   Oliver   ", "Oliver", true);
	}

	private static void testSetFirstName(String testCase, String firstName,
			String expectedFirstName, boolean expectValid) {
		out.println("   " + testCase);
		Name theName = new Name("John", "Doe");
		try {
			theName.setFirstName(firstName);
			out.print("\tThe Name instance was created: " + theName);

			if (!theName.getFirstName().equals(expectedFirstName))
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

	public static void testGetLastName() {
		out.println("\nTesting the getLastName method.");
		testGetLastName("Case 1: Doe without leading/trailing spaces", "Doe",
				"Doe");
		testGetLastName("Case 2: Doe with leading/trailing spaces",
				"    Doe    ", "Doe");
	}

	private static void testGetLastName(String testCase, String lastName,
			String expectedLastName) {
		out.println("   " + testCase);
		Name theName = new Name("John", lastName);
		out.print("\tThe Name instance was created: " + theName);

		if (!theName.getLastName().equals(expectedLastName))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

	public static void testSetLastName() {
		out.println("\nTesting the setLastName method.");
		testSetLastName(
				"Case 1: Valid - Edison without leading/trailing spaces",
				"Edison", "Edison", true);
		testSetLastName("Case 2: Invalid null last name", null, "", false);
		testSetLastName("Case 2: Valid Edison with leading/trailing spaces",
				"   Edison   ", "Edison", true);
	}

	private static void testSetLastName(String testCase, String lastName,
			String expectedLastName, boolean expectValid) {
		out.println("   " + testCase);
		Name theName = new Name("John", "Doe");
		try {
			theName.setLastName(lastName);
			out.print("\tThe Name instance was created: " + theName);

			if (!theName.getLastName().equals(expectedLastName))
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
			Name nameRef = new Name();
			out.print("\tThe Name instance was created: " + nameRef);

			int length = nameRef.getFirstName().length();
			out.print("\tThe length of first name is: " + length);
		} catch (NullPointerException npe) {
			out.print("\t" + npe.getMessage());
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
		}

		out.println("\n");
	}
}
