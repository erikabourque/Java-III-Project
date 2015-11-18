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

		testbase("2 of priority 1, then one of each priority.","datafiles/prioritypolicy/test1Patient.txt", "datafiles/prioritypolicy/test1Visit.txt");
		testbase("2 of each priority BUT requires a second round to go down the list","datafiles/prioritypolicy/test2Patient.txt", "datafiles/prioritypolicy/test2Visit.txt");
		testbase("at least 2 of each priority WITH 2 rounds BUT no priority 3","datafiles/prioritypolicy/test3Patient.txt", "datafiles/prioritypolicy/test3Visit.txt");
		testbase("2 of each priority WITH 2 rounds BUT all priority 5 are dequeued in second round","datafiles/prioritypolicy/test4Patient.txt", "datafiles/prioritypolicy/test4Visit.txt");
		testbase("2 of each priority WITH 2 rounds WITH empty lines","datafiles/prioritypolicy/test5Patient.txt", "datafiles/prioritypolicy/test5Visit.txt");
		//testbase("","datafiles/prioritypolicy/test6Patient.txt", "datafiles/prioritypolicy/test6Visit.txt");
		//testbase("","datafiles/prioritypolicy/test7Patient.txt", "datafiles/prioritypolicy/test7Visit.txt");

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
