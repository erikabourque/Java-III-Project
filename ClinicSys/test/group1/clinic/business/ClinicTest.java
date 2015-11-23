/**
 * 
 */
package group1.clinic.business;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dw317.clinic.ClinicFactory;
import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.business.interfaces.PatientVisitManager;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.clinic.data.interfaces.VisitDAO;
import group1.clinic.data.*;
import group1.dawsonclinic.DawsonClinicFactory;
import group1.util.ListUtilities;
import dw317.lib.medication.*;

/**
 * @author Danieil Skrinikov
 *
 */
public class ClinicTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ERIKA@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		//When merged with Cindy's part uncomment the nextForExaminationTest.
		
		constructorTest();
		createVisitTest();
		findPatientTest();
		findPatientPrescribedTest();
		getNextForTriageTest();
		//nextForExaminationTest();
		registerNewPatientTest();
		closeClinicTest();

	}
	

	public static void constructorTest(){
		
		System.out.println("--Constructor Test");
		
		setup();
		
			ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.tx");
			VisitDAO visit = new VisitQueueDB(p);
			PatientDAO patient = new PatientListDB(p);
			ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
			
				//Testing
				System.out.println("-Test 1 - null parameters");
				try{
					
					Clinic c = new Clinic(null,null,null);
					
				}
				catch(IllegalArgumentException iae){
					
					System.out.println("Test Passed.\n\t" + iae);
					
				}
				
				System.out.println("-Test 2 - null patient");
				try{
					
					
					Clinic c = new Clinic(null,visit,f);
					
				}
				catch(IllegalArgumentException iae){
					
					System.out.println("Test Passed.\n\t" +iae);
					
				}
				
				System.out.println("-Test 3 - null visit");
				try{
					
					
					Clinic c = new Clinic(patient,null,f);
					
				}
				catch(IllegalArgumentException iae){
					
					System.out.println("Test Passed.\n\t" +iae);
					
				}
				
				System.out.println("-Test 4 - null factory - Should Pass");
				try{
					
					
					Clinic c = new Clinic(patient,visit,null);
					System.out.println("Test Passed - Object initialized.");
					
				}
				catch(IllegalArgumentException iae){
					
					System.out.println("Test Failed.\n\t" +iae);
					
				}
				
				//Working test
				System.out.println("-Test 5 - Object should be initialized - Should Pass.");
				try{
					
					
					Clinic c = new Clinic(patient,visit,f);
					System.out.println("Test Passed - Object initialized.");
					
				}
				catch(IllegalArgumentException iae){
					
					System.out.println("Test Failed.\n\t" +iae);
					
				}
			
		
		teardown();
		
		System.out.println("\n");
		
		
	}
	
	public static void createVisitTest(){
		
		System.out.println("--Create a visit Test");
		
		setup();
		
			ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
			VisitDAO visit = new VisitQueueDB(p);
			PatientDAO patient = new PatientListDB(p);
			ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
			PatientVisitManager clinic = new Clinic(patient,visit,f);
			Patient patient1 = new ClinicPatient("Rob","Ford","FORR42042087");
			Patient patient2 = new ClinicPatient("Vladimir","Putin","PUTV11111111");
			String complaint = "@@@@@@@@@@@@@@@@@@@@@";
			
				System.out.println("-Test 1 - null parameters.");
				try{
					
					clinic.createVisit(null, null);
					
				}
				catch(IllegalArgumentException iae){
					System.out.println("Test Passed\n\t" + iae);
				}
				
				System.out.println("-Test 2 - null patient.");
				try{
					
					clinic.createVisit(null, complaint);
					
				}
				catch(IllegalArgumentException iae){
					System.out.println("Test Passed\n\t" + iae);
				}
				
				System.out.println("-Test 3 - null complaint.");
				try{
					
					clinic.createVisit(null, complaint);
					
				}
				catch(IllegalArgumentException iae){
					System.out.println("Test Passed\n\t" + iae);
				}
				
				System.out.println("-Test 4 - Full parameters - Should Work");
				try{
					
					clinic.createVisit(patient1, complaint);
					System.out.println("Test Passed\n"+visit);
					
				}
				catch(IllegalArgumentException iae){
					System.out.println("Test Failed\n\t" + iae);
				}
				
				System.out.println("-Test 5 - Full parameters - Should Work");
				try{
					
					clinic.createVisit(patient2, complaint);
					System.out.println("Test Passed\n"+visit);
					
				}
				catch(IllegalArgumentException iae){
					System.out.println("Test Failed\n\t" + iae);
				
				}
			
		
		teardown();
		System.out.println("\n");
		
	}
	
	public static void findPatientTest(){
		
		System.out.println("--Find Patient Test");
		setup();
		
		ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
		VisitDAO visit = new VisitQueueDB(p);
		PatientDAO patient = new PatientListDB(p);
		ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
		PatientVisitManager clinic = new Clinic(patient,visit,f);
		String testRamq = "LARR80072061";
		
			System.out.println("-Test 1 - null parameter");	
			try{
				
				clinic.findPatient(null);
				
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t" + iae);
			}
			catch(Exception e){
			}
			
			System.out.println("-Test 2 - Inccorect ramq formatting");	
			try{
				
				clinic.findPatient("fdskhgdshg");
				
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t" + iae);
			}
			catch(Exception e){
			}
			
			System.out.println("-Test 3 - Digits in 4 first characters");	
			try{
				
				clinic.findPatient("234G55555555");
				
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t" + iae);
			}
			catch(Exception e){
			}
			
			System.out.println("-Test 4 - Letters in 8 last characters");	
			try{
				
				clinic.findPatient("GAGA0101010A");
				
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t" + iae);
			}
			catch(Exception e){
			}
			
			System.out.println("-Test 5 - Empty String");	
			try{
				
				clinic.findPatient("");
				
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t" + iae);
			}
			catch(Exception e){
			}
			
			System.out.println("-Test 6 - Good ramq - Should Pass");	
			try{
				
				Patient temp = clinic.findPatient(testRamq);
				System.out.println("Expected Result: LARR80072061*Renato*Laranja*5147341013*DIN*00800430*Vancocin*Staph Infection \n\t\t"+temp);
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Failed\n\t" + iae);
			}
			catch(Exception e){
			}

		teardown();
		
		System.out.println("\n\n");
		
	}

	public static void findPatientPrescribedTest(){
		
		System.out.println("--Find patient prescribed Test");
		setup();
		
		ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
		VisitDAO visit = new VisitQueueDB(p);
		PatientDAO patient = new PatientListDB(p);
		ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
		PatientVisitManager clinic = new Clinic(patient,visit,f);
		Medication medsA = new DINMedication("00800430","Vancocin");
		Medication medsB = new NDCMedication("43479-501-51","Pimple punisher");
		
			System.out.println("Test 1 - null param");
			try{
				clinic.findPatientsPrescribed(null);
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t" + iae);
			}
			
			System.out.println("Test 2 - DIN Medication - Expected Result 1 patient.");
			try{
				List<Patient> v = clinic.findPatientsPrescribed(medsA);
				System.out.println("Test Passed\n\t" +v);
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Failed\n\t" + iae);
			}
			
			System.out.println("Test 3 - NDC Medication - Expected Result 2 patients.");
			try{
				List<Patient> v = clinic.findPatientsPrescribed(medsB);
				System.out.println("Test Passed\n\t" +v);
			}
			catch(IllegalArgumentException iae){
				System.out.println("Test Failed\n\t" + iae);
			}
			
		
		teardown();
		
	}

	public static void getNextForTriageTest(){
		
		System.out.println("--Next For Triage Test");
		
		setup();
		
		ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
		VisitDAO visit = new VisitQueueDB(p);
		PatientDAO patient = new PatientListDB(p);
		ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
		PatientVisitManager clinic = new Clinic(patient,visit,f);
		
			System.out.println(clinic.nextForTriage());
		
		teardown();
		System.out.println("\n");
		
	}
	
	//Need Cindy's part to work.
	public static void nextForExaminationTest(){
	
		
		System.out.println("--Next for Examination Test");
		
		ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
		VisitDAO visit = new VisitQueueDB(p);
		PatientDAO patient = new PatientListDB(p);
		ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
		PatientVisitManager clinic = new Clinic(patient,visit,f);
		
		System.out.println(clinic.nextForExamination());
		System.out.println("\n");
		
	}
	
	public static void registerNewPatientTest(){
		
		System.out.println("--Register New Patient Test");
		setup();
		
		ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
		VisitDAO visit = new VisitQueueDB(p);
		PatientDAO patient = new PatientListDB(p);
		ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
		PatientVisitManager clinic = new Clinic(patient,visit,f);
		Medication medsA = new DINMedication("00800430","Vancocin");
		
			System.out.println("-Test 1 - null params");
			try{
				
				clinic.registerNewPatient(null, null, null, null, null, null);
				
			}
			catch(DuplicatePatientException dpe){}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t"+iae);
			}
			System.out.println("-Test 2 - null lastName");
			try{
				
				clinic.registerNewPatient("Jimmy", null, "HENJ87145986", null, null, null);
				
			}
			catch(DuplicatePatientException dpe){}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t"+iae);
			}
			System.out.println("-Test 3 - null Ramq");
			try{
				
				clinic.registerNewPatient("John", "Doe", null, "5149999999", medsA, "kfjhsd");
				
			}
			catch(DuplicatePatientException dpe){}
			catch(IllegalArgumentException iae){
				System.out.println("Test Passed\n\t"+iae);
			}
			System.out.println("\n-Test 4 - Working set, no Optional");
			try{
				
				clinic.registerNewPatient("Phillipe","Couillard", "COUP12121212",null,null,null);
				System.out.println(patient);
				
			}
			catch(DuplicatePatientException dpe){}
			catch(IllegalArgumentException iae){
				System.out.println("Test Failed\n\t"+iae);
			}
			System.out.println("\n-Test 5 - Working set, With Optional params");
			try{
				
				clinic.registerNewPatient("Pauline","Marrois", "MARP09090909","5145145145",medsA, "Quebec pain");
				System.out.println(patient);
				
			}
			catch(DuplicatePatientException dpe){}
			catch(IllegalArgumentException iae){
				System.out.println("Test Failed\n\t"+iae);
			}
			System.out.println("\n-Test 6 - DuplicatePatientException");
			try{
				clinic.registerNewPatient("Renato", "Laranja", "LARR80072061", "5147341013", null, "Staph Exception");
			}
			catch(DuplicatePatientException dpe){
				System.out.println("Test Passed\n\t"+dpe);
			}
		
		
		teardown();
		
	}
	
	public static void closeClinicTest(){
		
		System.out.println("\n--Close Clinic Test");
		setup();
		
			
		ListPersistenceObject p = new SequentialTextFileList("testfiles/testPatients.txt","testfiles/testVisits.txt");
		VisitDAO visit = new VisitQueueDB(p);
		PatientDAO patient = new PatientListDB(p);
		ClinicFactory f = DawsonClinicFactory.DAWSON_CLINIC;
		PatientVisitManager clinic = new Clinic(patient,visit,f);
		
			try{
				clinic.closeClinic();
			}
			catch(IOException ioe){
				
				System.out.println(ioe);
			}
			
			try{
			clinic.nextForTriage();
				
			}catch(IllegalArgumentException iae){
				
				System.out.println("-Test Passed - Any other operations are not permitted.\n\t"+iae);
				
			}
			
		teardown();
		
	}
	
	private static void setup() {
		String[] patients = new String[10];
		patients[0] = "LARR80072061*Renato*Laranja*5147341013*" + "DIN*00800430*Vancocin*Staph Infection";
		patients[1] = "LISH87100101*Shao*Li**" + "DIN*02238645*292 tablets*Pain";
		patients[2] = "RAOV86112001*Vishal*Rao*5143634564*" + "NDC*43479-501-51*Pimple punisher*Acne";
		patients[3] = "RODM90571001*Maria*Rodriguez*5145555511****";
		patients[4] = "SMIM85122501*Mike*Smith*5143634564*" + "DIN*02239497*Absorbine Jr*Athlete’s foot";
		patients[5] = "SUCF34050513*Fernando*Sucre*5813085502****";
		patients[6] = "TORD83511514*Diana*Torres*6379315732*" + "DIN*02204266*Dexamethasone-Omega*Asthma";
		patients[7] = "WAKN60022987*Norio*Wakamoto*4389945870*"
				+ "NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction";
		patients[8]	= "FORR42042087*Robb*Ford*5145145145*" + "DIN*02204266*Dexamethasone-Omega*Asthma";	
		patients[9] = "PUTV11111111*Vladimir*Putin**"+"NDC*43479-501-51*Pimple punisher*Acne";

		String[] visits = new String[8];
		
		visits[0] = "RODM90571001*2015*9*1*14*45*******";
		visits[1] = "SMIM85122501*2015*9*1*13*30*******";
		visits[2] = "LARR80072061*2015*4*2*12*50*" + "2015*4*2*13*35*1*Stomach pain";
		visits[3] = "LISH87100101*2015*9*1*13*20*" + "2015*12*1*13*45*2*Severe rash";
		visits[4] = "RAOV86112001*2015*9*1*13*50*" + "2015*12*1*14*10*3*Bored";
		visits[5] = "SUCF34050513*2015*1*7*6*10*" + "2015*1*7*14*59*3*Finger injury";
		visits[6] = "TORD83511514*2012*2*29*11*30*" + "2012*2*29*12*00*4*Asthmatic spasm";
		visits[7] = "WAKN60022987*2014*12*31*23*52*" + "2015*1*1*00*08*5*Heart stroke";

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
