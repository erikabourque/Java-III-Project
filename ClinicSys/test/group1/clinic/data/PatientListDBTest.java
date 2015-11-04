package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import dw317.clinic.DefaultPatientVisitFactory;
import group1.clinic.business.Ramq;
import group1.util.ListUtilities;

public class PatientListDBTest {

	public static void main(String[] args) {
		testOneParamConstructor();
		testTwoParamConstructor();
		testToString();
		testExists();
		
	}

	public static void testOneParamConstructor(){
		out.println("\nTesting the One Param Constructor");
		
		try{
		setup();
		
		SequentialTextFileList listObject = new SequentialTextFileList(
				"testfiles/testPatients.txt", "testfiles/testVisits.txt");
		PatientListDB patients = new PatientListDB (listObject);
		out.println("\tConstructor success.");
		
		teardown();
		}
		catch (Exception e){
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
		}
	}
	
	public static void testTwoParamConstructor(){
		out.println("\nTesting the Two Param Constructor");
		
		try{
		setup();
		
		SequentialTextFileList listObject = new SequentialTextFileList(
				"testfiles/testPatients.txt", "testfiles/testVisits.txt");
		DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
		PatientListDB patients = new PatientListDB (listObject, factory);
		out.println("\tConstructor success.");
		
		teardown();
		}
		catch (Exception e){
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
		}
	}
	
	public static void testToString(){
		out.println("\nTesting the toString method");
		
		try{
			setup();
			
			SequentialTextFileList listObject = new SequentialTextFileList(
					"testfiles/testPatients.txt", "testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB (listObject, factory);
			out.println("\tConstructor success.");
			
			out.println(patients.toString());
			
			teardown();
		}
		catch (Exception e){
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
		}
	}

	public static void testExists(){
		try{
			out.println("\nTesting the exists method");
			setup();
			
			SequentialTextFileList listObject = new SequentialTextFileList(
					"testfiles/testPatients.txt", "testfiles/testVisits.txt");
			DefaultPatientVisitFactory factory = DefaultPatientVisitFactory.DEFAULT;
			PatientListDB patients = new PatientListDB (listObject, factory);
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
		}
		catch (Exception e){
			out.println(e + " ==== FAILED TEST ====");
		}
		
	}
	
	public static void testExists(PatientListDB patients, String testcase, Ramq ramq, boolean expected){

		try{			
			boolean result = patients.exists(ramq);
			out.println(testcase + "\t" + ramq.getRamq() + "\t" + result);
			
			if (result != expected){
				out.println("Expected " + expected + " ==== FAILED TEST ====\n");
			}
			
			teardown();
		}
		catch (Exception e){
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
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
		patients[7] = "WAKN60022987*Norio*Wakamoto*4389945870*" + "NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction";
		
		
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
