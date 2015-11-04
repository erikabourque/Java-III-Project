package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import group1.util.ListUtilities;

public class PatientListDBTest {

	public static void main(String[] args) {
		testOneParamConstructor();
		testToString();

	}

	public static void testOneParamConstructor(){
		out.print("\nTesting the One Param Constructor\n");
		
		try{
		setup();
		SequentialTextFileList listObject = new SequentialTextFileList(
				"testfiles/testPatients.txt", "testfiles/testVisits.txt");
		PatientListDB patients = new PatientListDB (listObject);
		out.println("Success.");
		teardown();
		}
		catch (Exception e){
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====\n");
		}
	}
	
	public static void testToString(){
		setup();
		
	}

	private static void setup() {
		String[] patients = new String[8];
		patients[0] = "LISH87100101*Shao*Li**" + "DIN*02238645*292 tablets*Pain";
		patients[1] = "RAOV86112001*Vishal*Rao*5143634564*" + "NDC*43479-501-51*Pimple punisher*Acne";
		patients[2] = "LARR80072061*Renato*Laranja*5147341013*" + "DIN*00800430*Vancocin*Staph Infection";
		patients[3] = "TORD83511514*Diana*Torres*6379315732*" + "DIN*02204266*Dexamethasone-Omega*Asthma";
		patients[4] = "SUCF34050513*Fernando*Sucre*5813085502****";
		patients[5] = "WAKN60022987*Norio*Wakamoto*4389945870*" + "NDC*0363-8001-01*Nicotine Transdermal System*Smoking Addiction";
		patients[6] = "RODM90571001*Maria*Rodriguez*5145555511****";
		patients[7] = "SMIM85122501*Mike*Smith*5143634564*" + "DIN*02239497*Absorbine Jr*Athlete’s foot";
		
		String[] visits = new String[8];
		visits[0] = "SMIM85122501*2015*9*1*13*30*******";
		visits[1] = "RODM90571001*2015*9*1*14*45*******";
		visits[2] = "LARR80072061*2015*4*2*12*50*" + "2015*4*2*13*35*4*Stomach pain";
		visits[3] = "TORD83511514*2012*2*29*11*30*" + "2012*2*29*12*00*2*Asthmatic spasm";
		visits[4] = "SUCF34050513*2015*1*7*6*10*" + "2015*1*7*14*59*5*Finger injury";
		visits[5] = "WAKN60022987*2014*12*31*23*52*" + "2015*1*1*00*08*1*Heart stroke";
		visits[6] = "LISH87100101*2015*9*1*13*20*" + "2015*12*1*13*45*2*Severe rash";
		visits[7] = "RAOV86112001*2015*9*1*13*50*" + "2015*12*1*14*10*5*Bored";
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
