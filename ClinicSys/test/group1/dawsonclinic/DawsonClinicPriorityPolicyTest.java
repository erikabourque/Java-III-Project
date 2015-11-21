package group1.dawsonclinic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.VisitSorter;
import group1.clinic.data.ClinicFileLoader;
import group1.clinic.data.ListPersistenceObject;
import group1.clinic.data.SequentialTextFileList;
import group1.clinic.data.VisitQueueDB;
import group1.dawsonclinic.DawsonClinicPriorityPolicy;
import group1.util.ListUtilities;
import dw317.clinic.data.interfaces.VisitDAO;

public class DawsonClinicPriorityPolicyTest {

	public static void main(String[] args) {
		/**
		 * The one parameter constructor will not be tested, since invalid
		 * parameter will cause either compiler error or throw exception on the
		 * application side, invalid data will not be able to be passed to the
		 * code.
		 */

		// TEST getNext() method
		testbase("Test 1: 1 of each priority: 1 1 2 3 4 5", "datafiles/prioritypolicy/test1Patient.txt",
				"datafiles/prioritypolicy/test1Visit.txt");

		testbase("Test 2: 2 of each priority: 1 1 2 3 2 4 3 5 4 5", "datafiles/prioritypolicy/test2Patient.txt",
				"datafiles/prioritypolicy/test2Visit.txt");

		testbase("Test 3: 2 of each priority BUT no priority 3: 1 1 2 2 4 2 5 4 4 5",
				"datafiles/prioritypolicy/test3Patient.txt", "datafiles/prioritypolicy/test3Visit.txt");

		testbase("Test 4: 2 of each priority with alternating years 2010 and 2015: 1 1 2 3 2 4 3 5 4 5",
				"datafiles/prioritypolicy/test4Patient.txt", "datafiles/prioritypolicy/test4Visit.txt");

		testbase("Test 5: WITH empty lines: 1 1 2 3 2 4 3 4 5 5", "datafiles/prioritypolicy/test5Patient.txt",
				"datafiles/prioritypolicy/test5Visit.txt");

		testbase("Test 6: 1 full cycle: 2 3 2 4 3 2 5 2 3 4", "datafiles/prioritypolicy/test6Patient.txt",
				"datafiles/prioritypolicy/test6Visit.txt");

		testbase("Test 7: 2 2 4 3 2 5 2 4 5 5 5", "datafiles/prioritypolicy/test7Patient.txt",
				"datafiles/prioritypolicy/test7Visit.txt");

		testbase("Test 8: 4 4 4 4 4 4 4 4 4 4", "datafiles/prioritypolicy/test8Patient.txt",
				"datafiles/prioritypolicy/test8Visit.txt");

		testbase("Test 9: 5 5 5 5 5 5 5 5 5 5", "datafiles/prioritypolicy/test8Patient.txt",
				"datafiles/prioritypolicy/test9Visit.txt");

		testbase("Test 10: 3 3 3 3 3 3 3 3 3 3 ", "datafiles/prioritypolicy/test8Patient.txt",
				"datafiles/prioritypolicy/test10Visit.txt");

		testbase("Test 11: 2 2 2 2 2 2 2 2 2 2", "datafiles/prioritypolicy/test8Patient.txt",
				"datafiles/prioritypolicy/test11Visit.txt");

		testbase("Test 12: 1 1 1 1 1 1 1 1 1 1", "datafiles/prioritypolicy/test8Patient.txt",
				"datafiles/prioritypolicy/test12Visit.txt");
	}

	public static void testbase(String description, String patientFilename, String visitFilename) {
		out.println(description + "\n");
		ListPersistenceObject aList = new SequentialTextFileList(patientFilename, visitFilename);

		Patient[] patientList = null;
		try {
			patientList = ClinicFileLoader.getPatientListFromSequentialFile(patientFilename);
		} catch (IOException e3) {
			out.println("Error, please check your file.");
			e3.printStackTrace();
		}

		ListUtilities.sort(patientList);
		try {
			ListUtilities.saveListToTextFile(patientList, patientFilename);
		} catch (FileNotFoundException e2) {
			out.println("Error, file not found.");
			e2.printStackTrace();
		} catch (UnsupportedEncodingException e2) {
			out.println("Error, unsupported encoding.");
			e2.printStackTrace();
		}

		Visit[] visitList = null;
		try {
			visitList = ClinicFileLoader.getVisitListFromSequentialFile(visitFilename, patientList);
		} catch (IllegalArgumentException e1) {
			out.println("Error" + e1.getMessage());
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			out.println("Error" + e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e1) {
			out.println("Error, please check your file.");
			e1.printStackTrace();
		}
		VisitSorter sortVisit = new VisitSorter();
		for (int x = 0; x < visitList.length - 1; x++) {
			for (int y = x + 1; y < visitList.length; y++) {
				if (sortVisit.compare((Visit) visitList[x], (Visit) visitList[y]) > 0) {
					Visit temp = (Visit) visitList[y];
					visitList[y] = visitList[x];
					visitList[x] = temp;
					temp = null;
				}
			}
		}
		try {
			ListUtilities.saveListToTextFile(visitList, visitFilename);
		} catch (FileNotFoundException e) {
			out.println("Error, file not found.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			out.println("Error, unsupported encoding.");
			e.printStackTrace();
		}
		VisitDAO visitsDB = new VisitQueueDB(aList);

		DawsonClinicPriorityPolicy dawsonPolicy = new DawsonClinicPriorityPolicy(visitsDB);
		for (int i = 0; i < visitList.length; i++)
			out.println(dawsonPolicy.getNextVisit().get());

		out.println("***************************************************************************************\n");
	}
}
