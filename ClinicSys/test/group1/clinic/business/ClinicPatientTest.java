package group1.clinic.business;

import static java.lang.System.out;

import java.util.Optional;

import dw317.lib.medication.DINMedication;
import dw317.lib.medication.Medication;
import dw317.lib.medication.NDCMedication;

/**
 * @author Uen Yi Cindy Hung
 * @version 22/011/2015
 *
 */
public class ClinicPatientTest {

	public static void main(String[] args) {
		testConstructor();
		testGetBday();
		testGetGender();
		testGetName();
		testGetRamq();
		testGetAndSetExistingConditions();
		testGetAndSetMedication();
		TestGetAndSetTelephoneNumber();
		testToString();

	}

	// CONSTRUCTOR TEST
	private static void testConstructor() {
		out.println("Testing the constructor\n");
		testConstructor("Case 1: Valid Data (Alexia McCormick MCCA90553054)", "Alexia", "McCormick", "MCCA90553054",
				true);

		testConstructor("Case 2: Invalid Data - no first name ( McCormick MCCA90553054)", "", "McCormick",
				"MCCA90553054", false);
		testConstructor("Case 3: Invalid Data - no last name (Alexia  MCCA90553054)", "Alexia", "", "MCCA90553054",
				false);
		testConstructor("Case 4: Invalid Data - no ramq id (Alexia McCormick )", "Alexia", "McCormick", "", false);

		testConstructor("Case 5: Invalid Data - space first name (     McCormick MCCA90553054 )", "   ", "McCormick",
				"MCCA90553054", false);
		testConstructor("Case 6: Invalid Data - space last name (Alexia     MCCA90553054)", "Alexia", "   ",
				"MCCA90553054", false);
		testConstructor("Case 7: Invalid Data - space ramq (Alexia McCormick    )", "Alexia", "McCormick", "   ",
				false);

		testConstructor("Case 8: Invalid Data - null first name(null McCormick MCCA90553054)", null, "McCormick",
				"MCCA90553054", false);
		testConstructor("Case 9: Invalid Data - null last name(Alexia null MCCA90553054)", "Alexia", null,
				"MCCA90553054", false);
		testConstructor("Case 10: Invalid Data - null ramq(Alexia McCormick null)", "Alexia", "McCormick", null, false);

		testConstructor("Case 11: Invalid Data - numeric first name (123 McCormick MCCA90553054)", "123", "McCormick",
				"MCCA90553054", false);
		testConstructor("Case 12: Invalid Data - numeric last name (Alexia 123 MCCA90553054)", "Alexia", "123",
				"MCCA90553054", false);
		testConstructor("Case 13: Invalid Data - numeric ramq (Alexia McCormick 123)", "Alexia", "McCormick", "123",
				false);

		testConstructor("Case 14: Invalid Data - single character first name (A McCormick MCCA90553054)", "A",
				"McCormick", "MCCA90553054", false);
		testConstructor("Case 15: Invalid Data - single character last name (Alexia M MCCA90553054)", "Alexia", "M",
				"MCCA90553054", false);
		testConstructor("Case 16: Invalid Data - single character ramq (Alexia McCormick M)", "Alexia", "McCormick",
				"M", false);

		testConstructor("Case 17: Invalid Data - only characters ramq (Alexia McCormick MCCAYYMMDDRR)", "Alexia",
				"McCormick", "MCCAYYMMDDRR", false);
		testConstructor("Case 18: Invalid Data - wrong ramq format (Alexia McCormick M90C55C30A54)", "Alexia",
				"McCormick", "M90C55C30A54", false);
		testConstructor("Case 19: Invalid Data - wrong ramq id first name (Alexia McCormick MCCB90553054)", "Alexia",
				"McCormick", "MCCB90553054", false);
		testConstructor("Case 20: Invalid Data - wrong ramq id last name (Alexia McCormick MTCA90553054)", "Alexia",
				"McCormick", "MTCA90553054", false);

		out.println("******************************************************************************\n");
	}

	private static void testConstructor(String testCase, String firstName, String lastName, String ramq,
			boolean expectValid) {

		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient(firstName, lastName, ramq);
			out.print("\tThe ClinicPatient instance was created: Name: " + aPatient.getName() + " RAMQ ID: "
					+ aPatient.getRamq());

			if (!expectValid)
				out.print("::: ERROR TEST FAILED ::: Invalid Expected.");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage());
			if (expectValid)
				out.print("::: ERROR TEST FAILED ::: Valid Expected.");
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage()
					+ " " + firstName + lastName + ramq);
			if (expectValid)
				out.print("Valid Expected.");
		}

		out.println("\n");
	}

	// BIRTHDAY TEST
	private static void testGetBday() {
		out.println("Testing the getBirthday method\n");

		testGetBday("Case 1: Female patient (Alexia McCormick MCCA90553054)", "Alexia", "McCormick", "MCCA90553054",
				"1990-05-30");
		testGetBday("Case 2: Male patient (George Ross ROSG70030168)", "George", "Ross", "ROSG70030168", "1970-03-01");

		out.println("******************************************************************************\n");
	}

	private static void testGetBday(String testCase, String firstName, String lastName, String ramq,
			String expectedBDay) {
		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient(firstName, lastName, ramq);

			if (aPatient.getBirthday().toString().equals(expectedBDay))
				out.println("The expected birthday value is: " + expectedBDay + "The birthday value returned is: "
						+ aPatient.getBirthday());
			else
				out.println("::: ERROR TEST FAILED :::");
		} catch (IllegalArgumentException iae) {
			out.print("::: ERROR TEST FAILED ::: " + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");
	}

	// GENDER TEST
	private static void testGetGender() {
		out.println("Testing the getGender method");

		testGetGender("Case 1: Female patient (Alexia McCormick MCCA90553054)", "Alexia", "McCormick", "MCCA90553054",
				"FEMALE");
		testGetGender("Case 2: Male patient (George Ross ROSG70030168) ", "George", "Ross", "ROSG70030168", "MALE");

		out.println("******************************************************************************\n");
	}

	private static void testGetGender(String testCase, String firstName, String lastName, String ramq,
			String expectedGender) {
		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient(firstName, lastName, ramq);

			if (aPatient.getGender().toString().equals(expectedGender))
				out.println("The expected gender value is: " + expectedGender + " The gender value returned is: "
						+ aPatient.getGender());
			else
				out.println("::: ERROR TEST FAILED :::");
		} catch (IllegalArgumentException iae) {
			out.print("::: ERROR TEST FAILED ::: " + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");
	}

	// NAME TEST
	private static void testGetName() {
		out.println("Testing the getName method");

		testGetName("Case 1: name without leading/trailing spaces (Alexia McCormick)", "Alexia", "McCormick",
				"Alexia*McCormick");
		testGetName("Case 2: name with leading/trailing spaces (   Alexia      McCormick   )", "   Alexia   ",
				"   McCormick   ", "Alexia*McCormick");

		out.println("******************************************************************************\n");
	}

	private static void testGetName(String testCase, String firstName, String lastName, String expectedName) {
		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient(firstName, lastName, "MCCA90553054");

			if (aPatient.getName().toString().equals(expectedName))
				out.println("The expected name value is: " + expectedName + " The name value returned is: "
						+ aPatient.getName().toString());
			else
				out.println("::: ERROR TEST FAILED :::");
		} catch (IllegalArgumentException iae) {
			out.print("::: ERROR TEST FAILED ::: " + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");
	}

	// RAMQ TEST
	private static void testGetRamq() {
		out.println("Testing the getRamq method");

		testGetRamq("Case 1: ramq without leading/trailing spaces (MCCA90553054)", "MCCA90553054", "MCCA90553054");
		testGetRamq("Case 2: ramq with leading/trailing spaces (   MCCA90553054   )", "   MCCA90553054   ",
				"MCCA90553054");

		out.println("******************************************************************************\n");
	}

	private static void testGetRamq(String testCase, String ramq, String expectedRamq) {
		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient("Alexia", "McCormick", ramq);

			if (aPatient.getRamq().toString().equals(expectedRamq))
				out.println("The expected ramq value is: " + expectedRamq + "The ramq value returned is: "
						+ aPatient.getRamq());
			else
				out.println("::: ERROR TEST FAILED :::");
		} catch (IllegalArgumentException iae) {
			out.print("::: ERROR TEST FAILED ::: " + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");
	}

	// CONDITION TEST
	private static void testGetAndSetExistingConditions() {
		out.println("Testing the Get and Set ExistingConditions method");

		testGetAndSetExistingConditions("Case 1: complaint without leading/trailing spaces (This is a condition)",
				"This is a condition", "This is a condition");
		testGetAndSetExistingConditions("Case 2: complain with leading/trailing spaces (   This is a condition   )",
				"   This is a condition   ", "This is a condition");
		testGetAndSetExistingConditions("Case 3: only space", "   ", "");
		// can't tell for NULL as Optional.of(NULL) is not valid.
		testGetAndSetExistingConditions("Case 4: null", null, "");

		out.println("******************************************************************************\n");
	}

	private static void testGetAndSetExistingConditions(String testCase, String condition, String expectedCondition) {
		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient("Alexia", "McCormick", "MCCA90553054");
			aPatient.setExistingConditions(Optional.ofNullable(condition));

			if (aPatient.getExistingConditions().equals(expectedCondition))
				out.println("The expected existing condition value is: " + expectedCondition
						+ " The existing condition value returned is: " + aPatient.getExistingConditions());
			else
				out.println("::: ERROR TEST FAILED :::");
		} catch (IllegalArgumentException iae) {
			out.print("::: ERROR TEST FAILED ::: " + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");
	}

	// MEDICATION TEST
	private static void testGetAndSetMedication() {
		out.println("Testing the Get and Set Medication method");

		testGetAndSetMedication("Case 1: DIN 02244353 NovoRapid", "DIN", "02244353", "NovoRapid",
				"DIN*02244353*NovoRapid");
		testGetAndSetMedication("Case 2: NDC 50458-587-01 Concerta 54mg", "NDC", "50458-587-01", "Concerta 54mg",
				"NDC*50458-587-01*Concerta 54mg");
		// testGetAndSetMedication("Case 3: " , "", "" , "", "");

		out.println("******************************************************************************\n");

	}

	private static void testGetAndSetMedication(String testCase, String scheme, String medNum, String medName,
			String expectedMedication) {
		out.println(testCase + "\n");

		ClinicPatient aPatient = new ClinicPatient("Alexia", "McCormick", "MCCA90553054");
		Medication aMedication;

		try {
			if (scheme.equalsIgnoreCase("DIN"))
				aMedication = new DINMedication(medNum, medName);
			else
				aMedication = new NDCMedication(medNum, medName);

			aPatient.setMedication(Optional.ofNullable(aMedication));
			out.println(aPatient.getMedication().get() + expectedMedication);
			if (aPatient.getMedication().get().toString().equals(expectedMedication))
				out.println("The expected Medication is: " + expectedMedication + ". The Medication returned is: "
						+ aPatient.getMedication().get());
			else
				out.println("::: ERROR ::: TEST FAILED");
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR TEST FAILED :::" + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}
		out.println("\n");
	}

	// TELEPHONE TEST
	private static void TestGetAndSetTelephoneNumber() {
		out.println("Testing the Get and Set TelephoneNumber method");

		TestGetAndSetTelephoneNumber("Case 1: Valid Data (4506762645)", "4506762645", "4506762645", true);
		// Can't test as Optional of null is invalid.
		TestGetAndSetTelephoneNumber("Case 2: Invalid Data (null)", null, "", false);
		TestGetAndSetTelephoneNumber("Case 3: Invalid Data (empty space)", "   ", "", false);
		TestGetAndSetTelephoneNumber("Case 4: Invalid Data (asdjfnvhgy)", "asdjfnvhgy", "", false);

		out.println("******************************************************************************\n");
	}

	private static void TestGetAndSetTelephoneNumber(String testCase, String telephone, String expectedTelephone,
			boolean expectValid) {
		out.println(testCase + "\n");

		try {
			ClinicPatient aPatient = new ClinicPatient("Alexia", "McCormick", "MCCA90553054");

			aPatient.setTelephoneNumber(Optional.ofNullable(telephone));
			if (aPatient.getTelephoneNumber().equals(expectedTelephone))
				out.println("The expected telephone number value is: " + expectedTelephone
						+ ". The telephone number value returned is: " + aPatient.getTelephoneNumber());
			else
				out.println(":::ERROR TEST FAILED ::: Valid Expected");

			if (!expectValid)
				System.out.print("::: ERROR TEST FAILED. ::: Invalid expected");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage());
			if (expectValid)
				out.println("::: ERROR TEST FAILED ::: Valid Expected.");
		} catch (Exception e) {
			out.println("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");
	}

	// TOSTRING TEST
	private static void testToString() {
		out.println("Testing the toString method");

		ClinicPatient aPatient = new ClinicPatient("Alexia", "McCormick", "MCCA90553054");
		out.println("Case 1: Empty Patient.\n");
		aPatient.toString();
		out.println("\nCase 2: Filled Patient (Condition: Oompa Loompa,\n"
				+ "Medication: DIN, 02247163, crestor 20mg,\n" + "telephone: 4506784572.\n");
		aPatient.setExistingConditions(Optional.of("Oompa Loompa"));
		Medication aMedication = new DINMedication("02247163", "Crestor 20mg");
		aPatient.setMedication(Optional.of(aMedication));
		aPatient.setTelephoneNumber(Optional.of("4506784572"));

		out.println("******************************************************************************\n");
	}

}
