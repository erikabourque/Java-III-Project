package group1.clinic.data;

import java.io.File;
import java.io.IOException;

import group1.util.ListUtilities;

public class PatientListDBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static void setup() {
		String[] patients = new String[8];
		patients[0] = "LISH87100101*Shao*Li**" + "DIN*02238645*292 tablets*Pain";
		patients[1] = "RAOV86112001*Vishal*Rao*5143634564*" + "NDC*43479-501-51*Pimple punisher*Acne";

		// add more patients
		patients[6] = "RODM90571001*Maria*Rodriguez*5145555511****";
		patients[7] = "SMIM85122501*Mike*Smith*5143634564*" + "DIN*02239497*Absorbine Jr*Athlete’s foot";
		String[] visits = new String[8];
		visits[0] = "SMIM85122501*2015*9*1*13*30*******";
		visits[1] = "RODM90571001*2015*9*1*14*45*******";
		// add more visits
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
