package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.data.NonExistingPatientException;
import dw317.lib.medication.DINMedication;
import dw317.lib.medication.Medication;
import dw317.lib.medication.NDCMedication;
import group1.clinic.business.ClinicPatient;
import group1.clinic.business.Ramq;
import group1.util.ListUtilities;

public class PatientListDBTest {

	public static void main(String[] args) {
		testOneParamConstructor();
		testTwoParamConstructor();
		testToString();
		testExists();
		testGetPatient();
		testgetPatientsPrescribed();
		testDisconnect();
		testAddPatient();
		testUpdate();
	}

	public static void testOneParamConstructor() {
		out.println("\nTesting the One Param Constructor");
		setup();

		testOneParamConstructor("Case 1 valid data",
				new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt"), true);
		testOneParamConstructor("Case 2 null data", null, false);

		teardown();
	}

	private static void testOneParamConstructor(String testcase, SequentialTextFileList listObject, 
			boolean expectValid) {
		try {
			out.println(testcase);
			PatientListDB patients = new PatientListDB(listObject);
			out.println("\tConstructor success.");

			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println("\t" + iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
	}

	public static void testTwoParamConstructor() {
		out.println("\nTesting the Two Param Constructor");
		setup();

		testTwoParamConstructor("Case 1 valid data",
				new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt"),
				DefaultPatientVisitFactory.DEFAULT, true);
		testTwoParamConstructor("Case 2 null SequentialTextFileList", null, DefaultPatientVisitFactory.DEFAULT, false);
		testTwoParamConstructor("Case 3 null DefaultPatientVisitFactory",
				new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt"), null, false);
		testTwoParamConstructor("Case 4 all null data", null, null, false);

		teardown();
	}

	private static void testTwoParamConstructor(String testcase, SequentialTextFileList listObject,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);

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
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
	}

	public static void testToString() {
		out.println("\nTesting the toString method");

		try {
			setup();

			SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
					"testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");

			out.println(patients.toString());
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		} finally {
			teardown();
		}
	}

	public static void testExists() {
		try {
			out.println("\nTesting the exists method");
			setup();

			SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
					"testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");

			// Chart for Test Data
			out.println("\nTest Data and Expected Results Chart\nCase #\tValue\t\tExpected Result");
			out.println("Case 1\tLARR80072061\ttrue");
			out.println("Case 2\tLISH87100101\ttrue");
			out.println("Case 3\tRAOV86112001\ttrue");
			out.println("Case 4\tRODM90571001\ttrue");
			out.println("Case 5\tSMIM85122501\ttrue");
			out.println("Case 6\tSUCF34050513\ttrue");
			out.println("Case 7\tTORD83511514\ttrue");
			out.println("Case 8\tWAKN60022987\ttrue");
			out.println("Case 9\tBOBN60022987\tfalse");
			out.println("Case 10\tnull\terror");

			// Test Data
			out.println("\nResults Chart\nCase #\tValue\t\tResult");
			testExists(patients, "Case 1", new Ramq("LARR80072061"), true);
			testExists(patients, "Case 2", new Ramq("LISH87100101"), true);
			testExists(patients, "Case 3", new Ramq("RAOV86112001"), true);
			testExists(patients, "Case 4", new Ramq("RODM90571001"), true);
			testExists(patients, "Case 5", new Ramq("SMIM85122501"), true);
			testExists(patients, "Case 6", new Ramq("SUCF34050513"), true);
			testExists(patients, "Case 7", new Ramq("TORD83511514"), true);
			testExists(patients, "Case 8", new Ramq("WAKN60022987"), true);
			testExists(patients, "Case 9", new Ramq("BOBN60022987"), false);
			testExists(patients, "Case 9", null, false);

			teardown();
		} catch (Exception e) {
			out.println(e + " ==== FAILED TEST ====");
		}

	}

	private static void testExists(PatientListDB patients, String testcase, Ramq ramq, boolean expected) {

		try {
			out.print(testcase + "\t" + ramq + "\t");

			boolean result = patients.exists(ramq);

			out.println(result);

			if (result != expected) {
				out.println("Expected " + expected + " ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println(iae.getMessage());
			if (expected) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
	}

	public static void testGetPatient() {
		try {
			out.println("\nTesting the getPatient method");
			setup();

			SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
					"testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");

			// Chart for Test Data
			out.println("\nTest Data and Expected Results Chart\nCase #\tValue\t\tExpected Result");
			out.println("Case 1\tLARR80072061"
					+ "\tLARR80072061*Renato*Laranja*5147341013*DIN*00800430*Vancocin*Staph Infection");
			out.println("Case 2\tLISH87100101"
					+ "\tLISH87100101*Shao*Li**DIN*02238645*292 tablets*Pain");
			out.println("Case 3\tRAOV86112001"
					+ "\tRAOV86112001*Vishal*Rao*5143634564*NDC*43479-501-51*Pimple punisher*Acne");
			out.println("Case 4\tRODM90571001"
					+ "\tRODM90571001*Maria*Rodriguez*5145555511****");
			out.println("Case 5\tSMIM85122501"
					+ "\tSMIM85122501*Mike*Smith*5143634564*DIN*02239497*Absorbine Jr*Athlete’s foot");
			out.println("Case 6\tSUCF34050513"
					+ "\tSUCF34050513*Fernando*Sucre*5813085502****");
			out.println("Case 7\tTORD83511514"
					+ "\tTORD83511514*Diana*Torres*6379315732*DIN*02204266*Dexamethasone-Omega*Asthma");
			out.println("Case 8\tWAKN60022987"
					+ "\tWAKN60022987*Norio*Wakamoto*4389945870*NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction");
			out.println("Case 9\tBOBN60022987\tError");
			out.println("Case 10\tnull\tError");

			// Test Data
			out.println("\nResults Chart\nCase #\tValue\t\tResult");
			testgetPatient(patients, "Case 1", new Ramq("LARR80072061"), true);
			testgetPatient(patients, "Case 2", new Ramq("LISH87100101"), true);
			testgetPatient(patients, "Case 3", new Ramq("RAOV86112001"), true);
			testgetPatient(patients, "Case 4", new Ramq("RODM90571001"), true);
			testgetPatient(patients, "Case 5", new Ramq("SMIM85122501"), true);
			testgetPatient(patients, "Case 6", new Ramq("SUCF34050513"), true);
			testgetPatient(patients, "Case 7", new Ramq("TORD83511514"), true);
			testgetPatient(patients, "Case 8", new Ramq("WAKN60022987"), true);
			testgetPatient(patients, "Case 9", new Ramq("BOBN60022987"), false);
			testgetPatient(patients, "Case 10", null, false);

			teardown();
		} catch (Exception e) {
			out.println(e + " ==== FAILED TEST ====");
		}

	}

	private static void testgetPatient(PatientListDB patients, String testcase, Ramq ramq, boolean expectValid) {

		try {

			out.print(testcase + "\t" + ramq + "\t");
			out.println(patients.getPatient(ramq));

			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println(iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (NonExistingPatientException nepe) {
			out.println(nepe.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
	}

	public static void testgetPatientsPrescribed() {
		try {
			out.println("\nTesting the getPatientsPrescribed method");
			setup();

			SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
					"testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB(listObject, factory);
			out.println("\tConstructor success.");

			// Chart for Test Data
			out.println("\nTest Data and Expected Results Chart\nCase #\t" + String.format("%-45s", "Medication")
					+ "\tResults");
			out.println("Case 1\t" + String.format("%-45s", "DIN*00800430*Vancocin")
					+ "\tLARR80072061*Renato*Laranja*5147341013*DIN*00800430*Vancocin*Staph Infection");
			out.println("Case 2\t" + String.format("%-45s", "DIN*02238645*292 tablets")
					+ "\tLISH87100101*Shao*Li**DIN*02238645*292 tablets*Pain");
			out.println("Case 3\t" + String.format("%-45s", "NDC*43479-501-51*Pimple punisher")
					+ "\tRAOV86112001*Vishal*Rao*5143634564*NDC*43479-501-51*Pimple punisher*Acne");
			out.println("Case 4\t" + String.format("%-45s", "DIN*02239497*Absorbine Jr")
					+ "\tSMIM85122501*Mike*Smith*5143634564*DIN*02239497*Absorbine Jr*Athlete’s foot");
			out.println("Case 5\t" + String.format("%-45s", "DIN*02204266*Dexamethasone-Omega")
					+ "\tTORD83511514*Diana*Torres*6379315732*DIN*02204266*Dexamethasone-Omega*Asthma");
			out.println("Case 6\t" + String.format("%-45s", "NDC*0363-8001-01*Nicotine Transdermal System")
					+ "\tWAKN60022987*Norio*Wakamoto*4389945870*NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction");
			out.println("Case 7\t" + String.format("%-45s", "DIN*00000000*testvalue") + "\tempty list");
			out.println("Case 8\t" + String.format("%-45s", "DIN*00000000*testvalue") + "\tempty list");
			out.println("Case 9\t" + String.format("%-45s", "null") + "\tError");

			// Test Data
			out.println("\nResults Chart\nCase #\t" + String.format("%-45s", "Medication") + "\tResults");
			testgetPatientsPrescribed(patients, "Case 1", new DINMedication("00800430", "Vancocin"), true);
			testgetPatientsPrescribed(patients, "Case 2", new DINMedication("02238645", "292 tablets"), true);
			testgetPatientsPrescribed(patients, "Case 3", new NDCMedication("43479-501-51", "Pimple punisher"), true);
			testgetPatientsPrescribed(patients, "Case 4", new DINMedication("02239497", "Absorbine Jr"), true);
			testgetPatientsPrescribed(patients, "Case 5", new DINMedication("02204266", "Dexamethasone-Omega"), true);
			testgetPatientsPrescribed(patients, "Case 6",
					new NDCMedication("0363-8001-01", "Nicotine Transdermal System"), true);
			testgetPatientsPrescribed(patients, "Case 7", new DINMedication("00000000", "testvalue"), true);
			testgetPatientsPrescribed(patients, "Case 8", new NDCMedication("0000-0000-00", "testvalue"), true);
			testgetPatientsPrescribed(patients, "Case 9", null, false);

			teardown();
		} catch (Exception e) {
			out.println(e + " ==== FAILED TEST ====");
		}

	}

	private static void testgetPatientsPrescribed(PatientListDB patients, String testcase, Medication medication,
			boolean expectValid) {

		try {

			out.print(testcase + "\t" + String.format("%-45s", medication) + "\t");
			out.println(patients.getPatientsPrescribed(medication));

			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			out.println(iae.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		}
	}

	public static void testDisconnect(){
		out.println("\nTesting the disconnect method");
		
		setup();
		
		SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
				"testfiles/testVisits.txt");
		DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
		PatientListDB patients = new PatientListDB(listObject, factory);
		out.println("\tConstructor success.");
		
		try {
			out.println("\tDisconnecting now.");
			patients.disconnect();
			out.println("Contents of database after disconnect: " + patients);
		} catch (IOException e) {
			out.println(e.getMessage());
		} finally {
			teardown();
		}
	}
	
	public static void testAddPatient(){
		out.println("\nTest Data and Expected Results Chart\nCase #\t"
				+ String.format("%-55s", "New Patient") + "\tLocation in Results");
		out.println("Case 1\t" + String.format("%-55s", "AZNC86031129*Char*Aznable") + "\t1");
		out.println("Case 2\t" + String.format("%-55s", "SHNJ78042312*John*Shnaucker") + "\t5");
		out.println("Case 3\t" + String.format("%-55s", "ZAKN60022987*Norio*Zakamoto") + "\t9");
		out.println("Case 4\t" + String.format("%-55s", "null") + "\terror");
		out.println("Case 5\t" + String.format("%-55s", "AZNC86031129*Char*Aznable*4167572322") + "\t1");
		out.println("Case 6\t" + String.format("%-55s", "AZNC86031129*Char*Aznable**NDC*54868-5987-1*Omeprazole") + "\t1");
		out.println("Case 7\t" + String.format("%-55s", "AZNC86031129*Char*Aznable*****Stomach") + "\t1");
		
		out.println("\nResults");
		testAddPatient("Case 1", new ClinicPatient("Char", "Aznable", "AZNC86031129"), true);
		testAddPatient("Case 2", new ClinicPatient("John", "Shnaucker", "SHNJ78042312"), true);
		testAddPatient("Case 3", new ClinicPatient("Norio", "Zakamoto", "ZAKN60022987"), true);
		testAddPatient("Case 4", null, false);
		
		Patient caseFive = new ClinicPatient("Char", "Aznable", "AZNC86031129");
		caseFive.setTelephoneNumber(Optional.of("4167572322"));
		testAddPatient("Case 5", caseFive, true);
		
		Patient caseSix = new ClinicPatient("Char", "Aznable", "AZNC86031129");
		caseSix.setMedication(Optional.of(new NDCMedication("54868-5987-1", "Omeprazole")));
		testAddPatient("Case 6", caseSix, true);
		
		Patient caseSeven = new ClinicPatient("Char", "Aznable", "AZNC86031129");
		caseSeven.setExistingConditions(Optional.of("Stomach"));
		testAddPatient("Case 7", caseSeven, true);
	}
	
	private static void testAddPatient(String testcase, Patient aPatient, boolean expectValid){
		setup();
		
		SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
				"testfiles/testVisits.txt");
		DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
		PatientListDB patients = new PatientListDB(listObject, factory);
		
		out.print(testcase + "\t");
		try{
			patients.add(aPatient);
			out.println(aPatient);
			patients.disconnect();
			PatientListDB patientsAgain = new PatientListDB(listObject, factory);
			
			out.println(patientsAgain);
			
			if (!expectValid) {
				out.println("Expected invalid ==== FAILED TEST ====");
			}
		}
		catch(Exception e){
			out.println(e.getMessage());
			if (expectValid) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} finally {
			teardown();
		}
	}
	
	public static void testUpdate(){
		try {
			out.println("\nTesting the update method");

			// Chart for Test Data
			out.println("\nTest Data and Expected Results Chart\nCase #\t" + String.format("%-55s", "Changed Value")
				+ "\tExpected Changed Result");
			out.println("Case 1\t" + String.format("%-55s", "0000000000*DIN*00800430*Vancocin*Staph Infection")
				+ "\tLARR80072061*Renato*Laranja*0000000000*DIN*00800430*Vancocin*Staph Infection\ttrue");
			out.println("Case 2\tLISH87100101\ttrue");
			out.println("Case 3\tRAOV86112001\ttrue");
			out.println("Case 4\tRODM90571001\ttrue");
			out.println("Case 5\tSMIM85122501\ttrue");
			out.println("Case 6\tSUCF34050513\ttrue");
			out.println("Case 7\tTORD83511514\ttrue");
			out.println("Case 8\tWAKN60022987\ttrue");
			out.println("Case 9\tBOBN60022987\tfalse");
			out.println("Case 10\tnull\terror");

			// Test Data
			out.println("\nResults Chart\nCase #\t" + String.format("%-55s", "Changed Value"));
			testUpdate("Case 1", "Renato", "Laranja", "LARR80072061", 
					"0000000000", "DIN", "00800430", "Vancocin", "Staph Infection", true);
			testUpdate("Case 2", "Renato", "Laranja", "LARR80072061", 
					"5147341013", "DIN", "00000000", "Vancocin", "Staph Infection", true);
			testUpdate("Case 3", "Renato", "Laranja", "LARR80072061", 
					"5147341013", "DIN", "00800430", "Lorazopan", "Staph Infection", true);
			testUpdate("Case 4", "Renato", "Laranja", "LARR80072061", 
					"5147341013", "DIN", "00800430", "Vancocin", "Nausea", true);
			testUpdate("Case 5", "Renato", "Laranja", "LARR80072061", 
					"5147341013", "NDC", "43479-501-51", "Vancocin", "Staph Infection", true);
			testUpdate("Case 6", "Renato", "Laranja", "LARR80072061", 
					"0000000000", "NDC", "43479-501-51", "Lorazopan", "Nausea", true);
			testUpdate("Case 7", "Renato", "Laranja", "LARR80072061", 
					"", "DIN", "00800430", "Vancocin", "Staph Infection", true);
			testUpdate("Case 8", "Renato", "Laranja", "LARR80072061", 
					"5147341013", "", "00000000", "Vancocin", "Staph Infection", true);
			testUpdate("Case 9", "Renato", "Laranja", "LARR80072061", 
					"5147341013", "DIN", "00800430", "Vancocin", "", true);
			testUpdate("Case 10", null, false);
		} catch (Exception e) {
			out.println(e + " ==== FAILED TEST ====");
		}

	}

	private static void testUpdate(String testcase, String fname, 
			String lname, String ramq, String phone, String medScheme, String medNumber, 
			String medName, String ailment, boolean expected) {

		try {
			setup();

			SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
					"testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB(listObject, factory);
			
			// Creating modified patient
			Patient modifiedPatient = new ClinicPatient(fname, lname, ramq);
			modifiedPatient.setTelephoneNumber(Optional.of(phone));
			
			Medication meds = null;
			if (medScheme.equals("DIN")){
				meds = new DINMedication(medNumber, medName);
			}
			else if (medScheme.equals("NDC")){
				meds = new NDCMedication(medNumber, medName);
			}
			
			if (meds == null){
				modifiedPatient.setMedication(Optional.empty());
			}
			else{
				modifiedPatient.setMedication(Optional.of(meds));
			}
			
			modifiedPatient.setExistingConditions(Optional.of(ailment));
			
			// Starting Testing
			out.println(testcase + "\t" + modifiedPatient);

			patients.update(modifiedPatient);
			patients.disconnect();

			PatientListDB patientsAgain = new PatientListDB(listObject, factory);
			
			out.println(patientsAgain);

			if (!expected) {
				out.println("Expected " + expected + " ==== FAILED TEST ====");
			}
			
		} catch (IllegalArgumentException iae) {
			out.println(iae.getMessage());
			if (expected) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		} finally {
			teardown();
		}
	}
	
	private static void testUpdate(String testcase, Patient modifiedPatient, boolean expected) {

		try {
			setup();

			SequentialTextFileList listObject = new SequentialTextFileList("testfiles/testPatients.txt",
					"testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB(listObject, factory);
			
			// Starting Testing
			out.println(testcase + "\t" + modifiedPatient);

			patients.update(modifiedPatient);
			patients.disconnect();

			PatientListDB patientsAgain = new PatientListDB(listObject, factory);
			
			out.println(patientsAgain);

			if (!expected) {
				out.println("Expected " + expected + " ==== FAILED TEST ====");
			}
			
		} catch (IllegalArgumentException iae) {
			out.println(iae.getMessage());
			if (expected) {
				out.println("Expected valid ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
		} finally {
			teardown();
		}
	}
	
	private static void setup() {
		String[] patients = new String[8];
		patients[0] = "LARR80072061*Renato*Laranja*5147341013*" + "DIN*00800430*Vancocin*Staph Infection";
		patients[1] = "LISH87100101*Shao*Li**" + "DIN*02238645*292 tablets*Pain";
		patients[2] = "RAOV86112001*Vishal*Rao*5143634564*" + "NDC*43479-501-51*Pimple punisher*Acne";
		patients[3] = "RODM90571001*Maria*Rodriguez*5145555511****";
		patients[4] = "SMIM85122501*Mike*Smith*5143634564*" + "DIN*02239497*Absorbine Jr*Athlete’s foot";
		patients[5] = "SUCF34050513*Fernando*Sucre*5813085502****";
		patients[6] = "TORD83511514*Diana*Torres*6379315732*" + "DIN*02204266*Dexamethasone-Omega*Asthma";
		patients[7] = "WAKN60022987*Norio*Wakamoto*4389945870*"
				+ "NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction";

		String[] visits = new String[8];
		visits[0] = "LARR80072061*2015*4*2*12*50*" + "2015*4*2*13*35*4*Stomach pain";
		visits[1] = "LISH87100101*2015*9*1*13*20*" + "2015*12*1*13*45*2*Severe rash";
		visits[2] = "RAOV86112001*2015*9*1*13*50*" + "2015*12*1*14*10*5*Bored";
		visits[3] = "RODM90571001*2015*9*1*14*45*******";
		visits[4] = "SMIM85122501*2015*9*1*13*30*******";
		visits[5] = "SUCF34050513*2015*1*7*6*10*" + "2015*1*7*14*59*5*Finger injury";
		visits[6] = "TORD83511514*2012*2*29*11*30*" + "2012*2*29*12*00*2*Asthmatic spasm";
		visits[7] = "WAKN60022987*2014*12*31*23*52*" + "2015*1*1*00*08*1*Heart stroke";

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
