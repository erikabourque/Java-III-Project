package dw317.lib;

import static java.lang.System.out;

public class PersonTest {

	public static void main(String[] args) {
		testTheTwoParameterConstructor();
		testTheThreeParameterConstructor();
		testGetAddress();
		testGetName();
		testSetAddress();
		testSetName();
	}
	
	public static void testTheTwoParameterConstructor() {
		out.print("\nTesting the two parameter constructor.\n");
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

		out.print("\n" + testCase + "\n");

		try {
			Person thePerson = new Person(firstName, lastName);
			out.print("\tThe Person instance was created: " + thePerson + "\n");

			if (!expectValid)
				out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				out.print(" Expected Valid.");
		}
	}
	
	public static void testTheThreeParameterConstructor() {
		out.print("\nTesting the three parameter constructor.\n");
		testTheThreeParameterConstructor(
				"Case 1 - Valid data (John Doe 3040 Sherbrooke Westmount)", "John",
				"Doe", "3040", "Sherbrooke", "Westmount", true);
		testTheThreeParameterConstructor(
				"Case 2 - Invalid data – empty civicNumber (John Doe  Sherbrooke Westmount)",
				 "John", "Doe", "", "Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 3 - Invalid data – civic number all spaces (John Doe      Sherbrooke Westmount)",
				"John", "Doe", "    ", "", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 4 - Invalid data – empty streetName (John Doe 3040  Westmount)",
				"John", "Doe", "3040", "", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 5 - Invalid data – empty city (John Doe 3040 Sherbrooke )",
				"John", "Doe", "3040", "Sherbrooke", "", false);
		testTheThreeParameterConstructor(
				"Case 6 - Invalid data – streetName all spaces (John Doe 3040     Westmount)",
				"John", "Doe", "3040", "   ", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 7 - Invalid data – city all spaces (John Doe 3040 Sherbrooke    )",
				"John", "Doe", "3040", "Sherbrooke", "   ", false);
		testTheThreeParameterConstructor(
				"Case 8 - Invalid data – null civicNumber (John Doe null Sherbrooke Westmount)",
				"John", "Doe", null, "Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 9 - Invalid data – null streetName (John Doe 3040 null Westmount)",
				"John", "Doe", "3040", null, "Westmount", false);
		testTheThreeParameterConstructor(
				"Case 10 - Invalid data – null city (John Doe 3040 Sherbrooke null)",
				"John", "Doe", "3040", "Sherbrooke", null, false);
	}
	
	private static void testTheThreeParameterConstructor(String testCase,
			String firstName, String lastName, String civicNumber, String streetName, String city,
			boolean expectValid) {

		out.print("\n" + testCase + "\n");

		try {
			Address address = new Address(civicNumber, streetName, city);
			Person thePerson = new Person(firstName, lastName, address);
			out.print("\tThe Person instance was created: " + thePerson + "\n");

			if (!expectValid)
				out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				out.print(" Expected Valid.");
		}
	}
	
	public static void testGetAddress() {
		out.print("\nTesting the getAddress method.\n");
		testGetAddress("Case 1: all without leading/trailing spaces",
				new Address("3040", "Sherbrooke", "Westmount"), "3040*Sherbrooke*Westmount**");
		testGetAddress("Case 2: civic number with leading/trailing spaces",
				new Address("    3040    ", "Sherbrooke", "Westmount"), "3040*Sherbrooke*Westmount**");
		testGetAddress("Case 3: streetname with leading/trailing spaces",
				new Address("3040", "   Sherbrooke   ", "Westmount"), "3040*Sherbrooke*Westmount**");
		testGetAddress("Case 4: city with leading/trailing spaces",
				new Address("3040", "Sherbrooke", "   Westmount   "), "3040*Sherbrooke*Westmount**");
	}

	private static void testGetAddress(String testCase, Address address,
			String expectedAddress) {
		out.print("\n" + testCase + "\n");
		Person thePerson = new Person("John", "Doe", address);
		out.print("\tThe Person instance was created: " + thePerson + "\n");
		out.print("\tThe Person address: " + thePerson.getAddress() + "\n");

		if (!thePerson.getAddress().toString().equals(expectedAddress))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
	}
	
	public static void testGetName() {
		out.print("\nTesting the getName method.\n");
		testGetName("Case 1: all without leading/trailing spaces", "John", "Doe",
				new Address("3040", "Sherbrooke", "Westmount"), "John*Doe");
		testGetName("Case 2: first name with leading/trailing spaces", "   John   ", "Doe",
				new Address("3040", "Sherbrooke", "Westmount"), "John*Doe");
		testGetName("Case 3: last name with leading/trailing spaces", "John", "   Doe   ",
				new Address("3040", "   Sherbrooke   ", "Westmount"), "John*Doe");
	}

	private static void testGetName(String testCase, String firstName, String lastName,
			Address address, String expectedName) {
		out.print("\n" + testCase + "\n");
		Person thePerson = new Person(firstName, lastName, address);
		out.print("\tThe Person instance was created: " + thePerson + "\n");
		out.print("\tThe Person name: " + thePerson.getName() + "\n");
		
		if (!thePerson.getName().toString().equals(expectedName))
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
	}
	
	public static void testSetAddress() {
		out.print("\nTesting the setAddress method.\n");
		testSetAddress("Case 1: all without leading/trailing spaces",
				new Address("500", "Wolfe", "Longueuil"), "500*Wolfe*Longueuil**", true);
		testSetAddress("Case 2: civic number with leading/trailing spaces",
				new Address("   500   ", "Wolfe", "Longueuil"), "500*Wolfe*Longueuil**", true);
		testSetAddress("Case 3: streetname with leading/trailing spaces",
				new Address("500", "   Wolfe   ", "Longueuil"), "500*Wolfe*Longueuil**", true);
		testSetAddress("Case 4: city with leading/trailing spaces",
				new Address("500", "Wolfe", "   Longueuil   "), "500*Wolfe*Longueuil**", true);
	}

	private static void testSetAddress(String testCase, Address newAddress,
			String expectedAddress, boolean expectValid) {
		out.print("\n" + testCase + "\n");
		Address address = new Address("3040", "Sherbrooke", "Westmount");
		
		try {
			Person thePerson = new Person("John", "Doe", address);
			out.print("\tThe Person instance was created: " + thePerson + "\n");
			
			thePerson.setAddress(newAddress);
			out.print("\tThe Person instance was changed: " + thePerson + "\n");

			if (!thePerson.getAddress().toString().equals(expectedAddress))
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage() + "\n");
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				out.print(" Expected Valid.");
		}
	}
	
	public static void testSetName() {
		out.print("\nTesting the setName method.\n");
		testSetName("Case 1: first name change without leading/trailing spaces", "Oliver", "Doe",
				new Address("3040", "Sherbrooke", "Westmount"), "Oliver*Doe", true);
		testSetName("Case 2: first name change with leading/trailing spaces", "   Oliver   ", "Doe",
				new Address("3040", "Sherbrooke", "Westmount"), "Oliver*Doe", true);
		testSetName("Case 3: last name change without leading/trailing spaces", "John", "Edison",
				new Address("3040", "Sherbrooke", "Westmount"), "John*Edison", true);
		testSetName("Case 4: last name change with leading/trailing spaces", "John", "   Edison   ",
				new Address("3040", "Sherbrooke", "Westmount"), "John*Edison", true);
	}

	private static void testSetName(String testCase, String firstName, String lastName,
			Address address, String expectedFullName, boolean expectValid) {
		out.print("\n" + testCase + "\n");
		try {
			Person thePerson = new Person("John", "Doe", address);
			out.print("\tThe Person instance was created: " + thePerson + "\n");
			
			thePerson.setName(firstName, lastName);
			out.print("\tThe Person name was changed: " + thePerson + "\n");

			if (!thePerson.getName().toString().equals(expectedFullName))
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
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
	}
}
