package group1.clinic.business;

import static java.lang.System.out;

import java.time.LocalDateTime;
import java.util.Optional;
import dw317.clinic.business.interfaces.Patient;

/**
 * @author Uen Yi Cindy Hung
 * @version 21/09/2015
 *
 */
public class ClinicVisitTest {

	private static ClinicPatient aPatient = new ClinicPatient("Alexia", "McCormick", "MCCA90553054");

	public static void main(String[] args) {
		testConstructor();
		testGetAndSetComplaint();
		testGetPatient();
		testGetAndSetPriority();
		test1ParamSetAndGetRegistrationDateAndTime();
		test5ParamSetAndGetRegistrationDateAndTime();
		test1ParamSetAndGetTriageDateAndTime();
		test5ParamSetAndGetTriageDateAndTime();
		testToString();

	}

	// CONSTRUCTOR TEST
	private static void testConstructor() {
		out.println("Testing the constructor");
		try {
			ClinicVisit aVisit = new ClinicVisit(aPatient);
			out.println("An instance of ClinicVisit has been created.");
			out.println("aVisit's priority is: " + aVisit.getPriority());
			out.println("aVisit's registration date and time is: " + aVisit.getRegistrationDateAndTime());
			out.println("aVisit's toString is: " + aVisit.toString());
		} catch (Exception e) {
			out.print("::: ERROR ::: TEST FAILED");
		}
		out.println("******************************************************************************\n");
	}

	// COMPLAINT TEST
	private static void testGetAndSetComplaint() {
		out.println("Testing the getComplaint method");

		testGetAndSetComplaint("Case 1: Valid Data - complaint without leading/trailing spaces (This is a complaint)",
				"This is a complaint", "This is a complaint");
		testGetAndSetComplaint(
				"Case 2: Invalid Data - complain with leading/trailing spaces (   This is a complaint   )",
				"   This is a complaint   ", "This is a complaint");
		testGetAndSetComplaint("Case 3: Invalid Data - only space", "   ", "");
		testGetAndSetComplaint("Case 4: Invalid Data - (null)", null, "");

		out.println("******************************************************************************\n");

	}

	private static void testGetAndSetComplaint(String testCase, String complaint, String expectedComplaint) {
		out.println(testCase + "\n");

		ClinicVisit aVisit = new ClinicVisit(aPatient);

		try {
			aVisit.setComplaint(Optional.of(complaint));
			if (aVisit.getComplaint().toString().equalsIgnoreCase(expectedComplaint))
				out.println("The expected complaint is: " + expectedComplaint + "The complaint returned is: "
						+ aVisit.getComplaint() + "\n");
			else
				out.println("::: ERROR ::: TEST FAILED");
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR ::: TEST FAILED");
		} catch (Exception e) {
			out.println("::: EROOR ::: TEST FAILED UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}

	}

	// PATIENT TEST
	private static void testGetPatient() {
		out.println("Testing the getPatient method");

		ClinicVisit aVisit = new ClinicVisit(aPatient);

		try {
			Patient testPatient = aVisit.getPatient();
			out.println("Patient's name: " + testPatient.getName().getFullName() + "\nPatient's gender: "
					+ testPatient.getGender() + "\nPatient's birthday: " + testPatient.getBirthday()
					+ "\nPatient's RAMQ: " + testPatient.getRamq());
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR ::: TEST FAILED");
		}

		out.println("******************************************************************************\n");

	}

	// PRIORITY TEST
	private static void testGetAndSetPriority() {
		out.println("Testing the getPriority method");

		testGetAndSetPriority("Case 1: Valid Data (NOTASSIGNED)", Priority.NOTASSIGNED, "NOTASSIGNED", 0);
		testGetAndSetPriority("Case 2: Valid Data (REANIMATION)", Priority.REANIMATION, "REANIMATION", 1);
		testGetAndSetPriority("Case 3: Valid Data (VERYURGENT)", Priority.VERYURGENT, "VERYURGENT", 2);
		testGetAndSetPriority("Case 4: Valid Data (URGENT)", Priority.URGENT, "URGENT", 3);
		testGetAndSetPriority("Case 5: Valid Data (LESSURGENT)", Priority.LESSURGENT, "LESSURGENT", 4);
		testGetAndSetPriority("Case 6: Valid Data (NOTURGENT)", Priority.NOTURGENT, "NOTURGENT", 5);

		out.println("******************************************************************************\n");
	}

	private static void testGetAndSetPriority(String testCase, Priority aPriority, String expectedPriority,
			int expectedPriorityCode) {
		out.println(testCase + "\n");

		try {
			ClinicVisit aVisit = new ClinicVisit(aPatient);
			aVisit.setPriority(aPriority);
			if (aVisit.getPriority().getCode() == expectedPriorityCode)
				out.println("The expected priority value is: " + expectedPriority + " The priority value returned is: "
						+ aVisit.getPriority());
			else
				out.println(":::ERROR TEST FAILED ::: Valid Expected");
		} catch (IllegalArgumentException iae) {
			out.print("::: ERROR TEST FAILED ::: Valid Expected." + iae.getMessage());
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}

		out.println("\n");

	}

	// REGISTRATION TEST
	private static void test1ParamSetAndGetRegistrationDateAndTime() {
		out.println("Testing the 1 parameter setRegistrationDateAndTime method");

		ClinicVisit aVisit = new ClinicVisit(aPatient);
		LocalDateTime dateTime = LocalDateTime.of(2018, 9, 22, 10, 15);
		try {
			aVisit.setRegistrationDateAndTime(Optional.of(dateTime));
			out.println("The expected resgistration date and time is: 2018-09-22T10:15:00."
					+ "The registration date and time returned is:" + aVisit.getRegistrationDateAndTime());
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR ::: TEST FAILED");
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}
		out.println("******************************************************************************\n");

	}

	private static void test5ParamSetAndGetRegistrationDateAndTime() {
		out.println("Testing the 5 parameters setRegistrationDateAndTime method");

		test5ParamSetAndGetRegistrationDateAndTime("Case 1: Valid Data (2018 9 22 10 900). ", 2018, 9, 22, 10, 900);
		test5ParamSetAndGetRegistrationDateAndTime("Case 2: Invalid Data (1520 7 8 23 1000).", 1520, 7, 8, 23, 1000);

		out.println("******************************************************************************\n");
	}

	private static void test5ParamSetAndGetRegistrationDateAndTime(String testCase, int year, int month, int day,
			int hour, int sec) {
		out.println(testCase + "\n");

		ClinicVisit aVisit = new ClinicVisit(aPatient);

		try {
			aVisit.setRegistrationDateAndTime(year, month, day, hour, sec);
			out.println("The expected resgistration date and time is: " + year + "-" + month + "-" + day + "T" + hour
					+ ":" + sec / 60 + " The registration date and time returned is:"
					+ aVisit.getRegistrationDateAndTime());
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR ::: TEST FAILED");
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}
	}

	// TRIAGE TEST
	private static void test1ParamSetAndGetTriageDateAndTime() {
		out.println("Testing the 1 parameter setTriageDateAndTime method");

		ClinicVisit aVisit = new ClinicVisit(aPatient);
		LocalDateTime dateTime = LocalDateTime.of(2015, 9, 22, 10, 15);

		try {
			aVisit.setTriageDateAndTime(Optional.of(dateTime));
			out.println("The expected triage date and time is: 2015-09-22T10:15:00."
					+ "The triage date and time returned is:" + aVisit.getTriageDateAndTime());
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR ::: TEST FAILED");
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}
		out.println("******************************************************************************\n");
	}

	private static void test5ParamSetAndGetTriageDateAndTime() {
		out.println("Testing the 5 parameters setTriageDateAndTime method");

		test5ParamSetAndGetTriageDateAndTime("Case 1: Valid Data (2018 9 22 10 900). ", 2018, 9, 22, 10, 900);
		test5ParamSetAndGetTriageDateAndTime("Case 2: Invalid Data (1520 7 8 23 1000).", 1520, 7, 8, 23, 1000);

		out.println("******************************************************************************\n");
	}

	private static void test5ParamSetAndGetTriageDateAndTime(String testCase, int year, int month, int day, int hour,
			int sec) {
		out.println(testCase + "\n");

		ClinicVisit aVisit = new ClinicVisit(aPatient);

		try {
			aVisit.setTriageDateAndTime(year, month, day, hour, sec);
			out.println("The expected triage date and time is: " + year + "-" + month + "-" + day + "T" + hour + ":"
					+ sec / 60 + "The triage date and time returned is:" + aVisit.getTriageDateAndTime());
		} catch (IllegalArgumentException iae) {
			out.println("::: ERROR ::: TEST FAILED");
		} catch (Exception e) {
			out.print("::: ERROR TEST FAILED ::: UNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
		}
	}

	// TOSRTING TEST
	private static void testToString() {
		out.println("Testing toString method");

		ClinicVisit aVisit = new ClinicVisit(aPatient);

		out.println("Case 1: Empty Visit.\n");
		aVisit.toString();

		out.println("\nCase 2: filled Visit (Triage date and time: 2016-08-12T10:20,\n" + " Piority: Not Urgent, \n"
				+ "Complaint: This clinic needs to offer some candies.\n");
		aVisit.setTriageDateAndTime(Optional.of(LocalDateTime.of(2016, 8, 12, 10, 25)));
		aVisit.setPriority(Priority.NOTURGENT);
		aVisit.setComplaint(Optional.of("This Clinic needs to offer some candies."));
		aVisit.toString();

		out.println("******************************************************************************\n");

	}
}
