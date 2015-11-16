package group1.dawsonclinic;

import java.awt.List;
import java.util.Optional;
import java.util.Queue;

import dw317.clinic.business.interfaces.PriorityPolicy;
import group1.clinic.data.ListPersistenceObject;
import group1.clinic.data.SequentialTextFileList;
import group1.dawsonclinic.DawsonClinicPriorityPolicy;
import dw317.clinic.data.interfaces.VisitDAO;

public class DawsonClinicPriorityPolicyTest {

	public static void main(String[] args) {
		/**
		 * The one parameter constructor will not be tested, since invalid
		 * parameter will cause either compiler error or throw exception on the
		 * application side, invalid data will not be able to be passed to the code.
		 */
		
		test1();
	}

	public static void test1(){
		ListPersistenceObject aList = new SequentialTextFileList("datafiles/prioritypolicy/test1Patient.txt","datafiles/prioritypolicy/test1Visit.text");
		VisitDAO visits = aList.getVisitDatabase());
	}
}
