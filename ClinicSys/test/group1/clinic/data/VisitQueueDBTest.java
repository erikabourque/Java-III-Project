/**
 * 
 */
package group1.clinic.data;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.ClinicPatient;
import group1.clinic.business.ClinicVisit;
import group1.clinic.business.Priority;
import group1.util.ListUtilities;

/**
 * @author Danieil Skrinikov
 * @version 11/03/2015
 *
 */
public class VisitQueueDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		toStringTest();
		addTest();
		removeTest();
		updateTest();
		getNextVisitTest();
		sizeTest();
		disconnectTest();
		constructorsTest();
	}
	
	@SuppressWarnings("unused")
	public static void constructorsTest(){
		
		setup();
		System.out.println("Testing the constructors\n");
		
		
		try{
		 VisitQueueDB db = new VisitQueueDB(null);
		
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
			
		}
		try{
			 VisitQueueDB db = new VisitQueueDB(null,null);
			
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				
			}
		try{
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(a,null);
			
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				
			}
		try{
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(null,DefaultPatientVisitFactory.DEFAULT);
			
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				
			}
		
		ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
		VisitQueueDB db = new VisitQueueDB(a,DefaultPatientVisitFactory.DEFAULT);
			
		
		teardown();
		
	}
	
	public static void disconnectTest(){
		
		setup();
		
		ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
		VisitQueueDB db = new VisitQueueDB(a);
		
		try{
			db.disconnect();
			System.out.println("Disconnect Test - Should not see this.\n"+db);
		}
		catch(IOException ioe){
			System.out.println(ioe);
			
		}
		catch(NullPointerException e){
			
			System.out.println("Disconnect test - passed");
			
		}
		
		teardown();
		
	}
	
	public static void sizeTest(){
		
		setup();
		ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
		VisitQueueDB db = new VisitQueueDB(a);
		
		int size = db.size(Priority.NOTASSIGNED);
		
		System.out.println("Size Test - Must return 2\n" + size);
		
		db.remove(Priority.NOTURGENT);
		size = db.size(Priority.NOTURGENT);
		System.out.println("Size Test - Must return 0\n" + size);
		db.remove(Priority.REANIMATION);
		size = db.size(Priority.REANIMATION);
		System.out.println("Size Test - Must return 0\n" + size);
		size = db.size(Priority.URGENT);
		System.out.println("Size Test - Must return 1\n" + size);
		
		
		teardown();
		
	}
	
	public static void getNextVisitTest(){
		
		setup();
		
		ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
		VisitQueueDB db = new VisitQueueDB(a);
		
		Optional<Visit> v = db.getNextVisit(Priority.REANIMATION);
		
		System.out.println("getNextVisitTest - Must return WAKN6\n"+v);
		
		db.remove(Priority.REANIMATION);
		v = db.getNextVisit(Priority.REANIMATION);
		System.out.println("getNextVisitTest - Must return null \n"+v);
		
		teardown();
		
	}
	
	public static void updateTest(){
		
		setup();
		ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
		VisitQueueDB db = new VisitQueueDB(a);
		try{
		db.update(Priority.NOTASSIGNED, Priority.URGENT);
		System.out.println("Update Test - JEDJ must become priority 2\n\n"+db);
		
		}
		catch(Exception e){
			System.out.println(e);
			
		}
		
		teardown();
		
	}
	
	public static void removeTest(){
	
		setup();
		
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(a);
			db.remove(Priority.LESSURGENT);
			System.out.println("Remove Test - Priority 4 must be empty\n\n"+db);
		
		teardown();

	}
	
	public static void addTest(){
		
		setup();
		
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(a);
			Patient p = new ClinicPatient("James","Bolton","BOLJ65031129");
			Visit v = new ClinicVisit(p);
			db.add(v);
			System.out.println("Add Test - James Bolton was added\n"+db);
			
		teardown();
		
	}
	
	public static void toStringTest(){
		
		setup();
		
			ListPersistenceObject a = new SequentialTextFileList("testfiles/testPatients.txt", "testfiles/testVisits.txt");
			VisitQueueDB db = new VisitQueueDB(a);
			System.out.println(db);
		
		teardown();
		
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
