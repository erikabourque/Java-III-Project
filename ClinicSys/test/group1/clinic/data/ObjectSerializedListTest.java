package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import dw317.clinic.DefaultPatientVisitFactory;
import group1.util.ListUtilities;

public class ObjectSerializedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testTwoParamConstructor();
		constructorsTest();
	}
	

	
	public static void testTwoParamConstructor() {
		out.println("\nTesting the Two Param Constructor");
		setupPatient();

		testTwoParamConstructor("Case 1 valid data",
				"testfiles/testPatients.txt", "testfiles/testVisits.txt",
				DefaultPatientVisitFactory.DEFAULT, true);
		testTwoParamConstructor("Case 2 null SequentialTextFileList", null, null, DefaultPatientVisitFactory.DEFAULT, false);
		testTwoParamConstructor("Case 3 null DefaultPatientVisitFactory",
			"testfiles/testPatients.txt", "testfiles/testVisits.txt", null, false);
		testTwoParamConstructor("Case 4 all null data", null,null, null, false);

		teardownPatient();
	}

	private static void testTwoParamConstructor(String testcase, String patientFile,String visitFile,
			DefaultPatientVisitFactory factory, boolean expectValid) {
		try {
			out.println(testcase);
			ObjectSerializedList listObject = new ObjectSerializedList("datafiles/database/patients.ser","datafiles/database/visits.ser");
			listObject.convertSequentialFilesToSerialized(patientFile, visitFile);
			out.println("\tListObject creation success.");
			
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
public static void constructorsTest(){
		
		setup();
		System.out.println("Testing the constructors\n");
		
		
		try{
			System.out.println("expected; failed");
		 VisitQueueDB db = new VisitQueueDB(null);
		
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
			
		}
		try{
			System.out.println("expected; failed");
			 VisitQueueDB db = new VisitQueueDB(null,null);
			
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				
			}
		try{
			System.out.println("expected; failed");
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(a,null);
			
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				
			}
		try{
			System.out.println("expected; failed");
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(null,DefaultPatientVisitFactory.DEFAULT);
			
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				
			}
		System.out.println("expected; true");
		ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
		VisitQueueDB db = new VisitQueueDB(a,DefaultPatientVisitFactory.DEFAULT);
		System.out.println("SUCCESS");
			
		
		teardown();
		
	}
private static void setupPatient() {
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
private static void teardownPatient() {
	File theFile = new File("testfiles/testPatients.txt");
	if (theFile.exists()) {
		theFile.delete();
	}
	theFile = new File("testfiles/testVisits.txt");
	if (theFile.exists()) {
		theFile.delete();
	}
}
private static void setup()  {
	
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
			"SUCF34050513*2015*1*7*6*10*2015*1*7*14*59*5*Finger injury"
			
	};
	
	File dir = new File("testfiles");
	try{
		if (!dir.exists()){
			dir.mkdirs();
		} 
		ListUtilities.saveListToTextFile(patients, "testfiles/testPatients.txt");
		ListUtilities.saveListToTextFile(visits, "testfiles/testVisits.txt");
	}
	catch(IOException io){
		System.out.println("Error creating file in setUp()");
	}
}
private static void teardown() {
	File theFile = new File("testfiles/testPatients.txt");
	if (theFile.exists()) {
		theFile.delete();
	}
	theFile = new File("testfiles/testVisits.txt");
	if (theFile.exists()){
		theFile.delete();
	} 
}

}
