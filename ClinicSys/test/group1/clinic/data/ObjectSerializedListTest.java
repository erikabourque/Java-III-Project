package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import dw317.clinic.DefaultPatientVisitFactory;
import group1.util.ListUtilities;

public class ObjectSerializedListTest {

	public static void main(String[] args) {		
		testTwoParamConstructor();
		//get patient database
		// get visit database
		// save patient database
		// save visit database
		
		// for each testing, you start by doing setup and end with teardown
		// test for nulls when you can
		
	}
	

	
	public static void testTwoParamConstructor() {
		out.println("\nTesting the Two Param Constructor");
		setup();

		testTwoParamConstructor("Case 1 valid data",
				"testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testTwoParamConstructor("Case 2 null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT, false);
		testTwoParamConstructor("Case 3 null DefaultPatientVisitFactory",
			"testfiles/testPatients.txt", "testfiles/testVisits.txt", null, false);
		testTwoParamConstructor("Case 4 all null data", null,null, null, false);

		teardown();
	}

	private static void testTwoParamConstructor(String testcase, String patientFile,String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser","datafiles/database/visits.ser");
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
		}catch (Exception e) {
			out.println(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
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
