package group1.dawsonclinic;

import java.awt.List;
import java.io.IOException;
import java.util.Optional;
import java.util.Queue;
import static java.lang.System.out;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PriorityPolicy;
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

		try {
			test1();
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test1() throws IllegalArgumentException, IOException {
		ListPersistenceObject aList = new SequentialTextFileList("datafiles/prioritypolicy/test1Patient.txt",
				"datafiles/prioritypolicy/test1Visit.txt");
		
		Patient[] patientList = ClinicFileLoader
				.getPatientListFromSequentialFile("datafiles/prioritypolicy/test1Patient.txt");
		
		ListUtilities.sort(patientList);
		ListUtilities.saveListToTextFile(patientList, "datafiles/prioritypolicy/test1Patient.txt");
		
		Visit[] visitList = ClinicFileLoader.getVisitListFromSequentialFile("datafiles/prioritypolicy/test1Visit.txt", patientList);
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
		ListUtilities.saveListToTextFile(visitList, "datafiles/prioritypolicy/test1Visit.txt");
		VisitDAO visitsDB = new VisitQueueDB(aList);
		
		DawsonClinicPriorityPolicy dawsonPolicy = new DawsonClinicPriorityPolicy(visitsDB);
		out.println(dawsonPolicy.getNextVisit().get());
		out.println(dawsonPolicy.getNextVisit().get());
		out.println(dawsonPolicy.getNextVisit().get());
		out.println(dawsonPolicy.getNextVisit().get());
		out.println(dawsonPolicy.getNextVisit().get());
		out.println(dawsonPolicy.getNextVisit().get());
		out.println(dawsonPolicy.getNextVisit());
	}
}
