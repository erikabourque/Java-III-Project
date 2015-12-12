package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.ClinicPatient;
import group1.clinic.business.ClinicVisit;
import group1.util.ListUtilities;

/**
 * Test app for ObjectSerializedList class.
 * 
 * @author Katherine Richer 1434389
 * @version 11-23-2015
 *
 */
public class ObjectSerializedListTest {

	public static void main(String[] args) {
		testTwoParamConstructor();
		testGetPatientDatabase();
		testGetVisitDatabase();
		testSavePatientDatabase();
		testSaveVisitDatabase();
	}

	public static void testTwoParamConstructor() {
		out.println("Testing the Two Param Constructor\n");
		setup();

		testTwoParamConstructor("Case 1: valid data", "testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testTwoParamConstructor("Case 2: null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT,
				false);
		testTwoParamConstructor("Case 3: null DefaultPatientVisitFactory", "testfiles/testPatients.txt",
				"testfiles/testVisits.txt", null, false);
		testTwoParamConstructor("Case 4: all null data", null, null, null, false);

		teardown();
		out.println("*********************************************************************************************");
	}

	private static void testTwoParamConstructor(String testcase, String patientFile, String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser",
					"datafiles/database/visits.ser");
			listObject.convertSequentialFilesToSerialized(patientFile, visitFile);
			out.println("\tListObject creation success.");

			@SuppressWarnings("unused")
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");

			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println("\t" + iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (NullPointerException npe) {
			out.println("\t" + npe.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
		out.println();
	}
	
	public static void testGetPatientDatabase() {
		out.println("Testing the getPatientDatabase\n");
		setup();

		testGetPatientDatabase("Case 1: valid data", "testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testGetPatientDatabase("Case 2: null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT,
				false);
		testGetPatientDatabase("Case 3: null DefaultPatientVisitFactory", "testfiles/testPatients.txt",
				"testfiles/testVisits.txt", null, false);
		testGetPatientDatabase("Case 4: all null data", null, null, null, false);

		teardown();
		out.println("*********************************************************************************************");
	}

	private static void testGetPatientDatabase(String testcase, String patientFile, String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser",
					"datafiles/database/visits.ser");
			
			listObject.convertSequentialFilesToSerialized(patientFile, visitFile);
			out.println("\tListObject creation success.");

			@SuppressWarnings("unused")
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");
			out.println(listObject.getPatientDatabase());
			
			if (!expectValid) 
				out.println("Expected invalid ==== FAILED TEST ====");
			
		} catch (IllegalArgumentException iae) {
			out.println("\t" + iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (NullPointerException npe) {
			out.println("\t" + npe.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
		out.println();
	}

	public static void testGetVisitDatabase() {
		out.println("Testing the getVisitDatabase\n");
		setup();

		testGetVisitDatabase("Case 1: valid data", "testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testGetVisitDatabase("Case 2: null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT,
				false);
		testGetVisitDatabase("Case 3: null DefaultPatientVisitFactory", "testfiles/testPatients.txt",
				"testfiles/testVisits.txt", null, false);
		testGetVisitDatabase("Case 4: all null data", null, null, null, false);

		teardown();
		out.println("*********************************************************************************************");
	}

	private static void testGetVisitDatabase(String testcase, String patientFile, String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser",
					"datafiles/database/visits.ser");
			listObject.convertSequentialFilesToSerialized(patientFile, visitFile);
			out.println("\tListObject creation success.");
			out.println("\tConstructor success.");
			out.println(listObject.getVisitDatabase());
			
			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println("\t" + iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (NullPointerException npe) {
			out.println("\t" + npe.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
		out.println();
	}

	public static void testSavePatientDatabase() {
		out.println("Testing the savePatientDatabase\n");
		setup();

		testSavePatientDatabase("Case 1: valid data", "testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testSavePatientDatabase("Case 2: null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT,
				false);
		testSavePatientDatabase("Case 3: null DefaultPatientVisitFactory", "testfiles/testPatients.txt",
				"testfiles/testVisits.txt", null, false);
		testSavePatientDatabase("Case 4: all null data", null, null, null, false);

		teardown();
		out.println("*********************************************************************************************");

	}

	private static void testSavePatientDatabase(String testcase, String patientFile, String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser",
					"datafiles/database/visits.ser");
			listObject.convertSequentialFilesToSerialized(patientFile, visitFile);
			out.println("\tListObject creation success.");

			@SuppressWarnings("unused")
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");
			Patient aPatient = new ClinicPatient("David", "An", "ANDA94082123");
			patients.add(aPatient);
			patients.disconnect();
			out.println(listObject.getPatientDatabase());
			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println("\t" + iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (NullPointerException npe) {
			out.println("\t" + npe.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
		out.println();
	}

	public static void testSaveVisitDatabase() {
		out.println("Testing the saveVisitDatabase\n");
		setup();

		testSaveVisitDatabase("Case 1: valid data", "testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testSaveVisitDatabase("Case 2: null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT,
				false);
		testSaveVisitDatabase("Case 3: null DefaultPatientVisitFactory", "testfiles/testPatients.txt",
				"testfiles/testVisits.txt", null, false);
		testSaveVisitDatabase("Case 4: all null data", null, null, null, false);

		teardown();
		out.println("*********************************************************************************************");
	}

	private static void testSaveVisitDatabase(String testcase, String patientFile, String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser",
					"datafiles/database/visits.ser");
			listObject.convertSequentialFilesToSerialized(patientFile, visitFile);
			out.println("\tListObject creation success.");

			VisitQueueDB visits = new VisitQueueDB(listObject, factory);
			out.println("\tConstructor success.");
			Patient aPatient = new ClinicPatient("David", "An", "ANDA94082123");
			Visit aVisit = new ClinicVisit(aPatient);
			visits.add(aVisit);
			visits.disconnect();
			out.println(listObject.getVisitDatabase());
			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println("\t" + iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (NullPointerException npe) {
			out.println("\t" + npe.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
		out.println();
	}

	private static void setup() {
		String[] patients = new String[]{
				"AZNC86031129*Char*Aznable*4167572322*NDC*54868-5987-1*Omeprazole*Stomach",
				"CENJ82100365*John*Cena*4385632147****",
				"JEDJ89561206*Joanna*Jedrzejczyk*4385562118*DIN*00223107*Conray 30*Brain Medication",
				"LARR80072061*Renato*Laranja*5147341013*DIN*00800430*Vancocin*Staph Infection",
				"MARK05092322*Karl*Marx*5811648900*NDC*61979-001-59*Body Time Anti-Aging Day Cream Spf 30*Wrinkled Skin",
				"MCDR63080910*Ronald*McDonald*5144871981*DIN*02369362*Acuvail*Eye Pain",
				"SUCF34050513*Fernando*Sucre*5813085502****",
				"TORD83511514*Diana*Torres*6379315732*DIN*02204266*Dexamethasone-Omega*Asthma",
				"WAKN60022987*Norio*Wakamoto*4389945870*NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction"
		};

		String[] visits = new String[]{
				"JEDJ89561206*2015*12*9*4*45*******",
				"MCDR63080910*2015*12*9*8*23*******",
				"WAKN60022987*2014*12*31*23*52*2015*1*1*0*8*1*Heart stroke",
				"TORD83511514*2012*2*29*11*30*2012*2*29*12*0*2*Asthmatic spasm",
				"AZNC86031129*2015*3*12*23*17*2015*3*13*0*11*3*Acute allergy",
				"LARR80072061*2015*4*2*12*50*2015*4*2*13*35*4*Stomach pain",
				"SUCF34050513*2015*1*7*6*10*2015*1*7*14*59*5*Finger injury"};

		File dir = new File("testfiles");
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(patients, "testfiles/testPatients.txt");
			ListUtilities.saveListToTextFile(visits, "testfiles/testVisits.txt");
		} catch (IOException io) {
			System.out.println("Error creating file in setUp()");
		}
	}

	private static void teardown() {
		File theFile = new File("testfiles/testPatients.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testVisits.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
	}

}
