package group1.clinic.business;

import static java.lang.System.out;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.VisitSorter;

public class VisitSorterTest {

	public static void main(String[] args) {
		testCompare();

	}
	
	public static void testCompare(){
		out.println("\nTesting the compare method.");
		ClinicPatient patient1 = new ClinicPatient("John", "Doe", "DOEJ55050511");
		ClinicPatient patient2 = new ClinicPatient("Thomas", "Edison", "THOE66060611");
		ClinicPatient patient3 = new ClinicPatient("Jane", "Doe", "DOEJ77070711");
		ClinicVisit visit1 = new ClinicVisit(patient1);
		ClinicVisit visit2 = new ClinicVisit(patient2);
		ClinicVisit visit3 = new ClinicVisit(patient3);
		visit1.setPriority(Priority.REANIMATION);
		visit2.setPriority(Priority.NOTURGENT);
		visit3.setPriority(Priority.NOTURGENT);
		
		testCompare("Case 1 - Valid data priority unassigned (John Doe vs Thomas Edison)", new ClinicVisit(patient1),
				new ClinicVisit(patient2), true);
		testCompare("Case 2 - Valid data priority unassigned (Thomas Edison vs John Doe)", new ClinicVisit(patient2),
				new ClinicVisit(patient1), true);
		testCompare("Case 3 - Invalid data (John Doe vs null)", new ClinicVisit(patient1),
				new ClinicVisit(null), false);
		testCompare("Case 4 - Valid data different priorities (reanimation vs not urgent)", visit1,
				visit2, true);
		testCompare("Case 5 - Valid data different priorities (not urgent vs reanimation)", visit2,
				visit1, true);
		testCompare("Case 6 - Valid data same priorities (not urgent vs not urgent)", visit2,
				visit3, true);
		testCompare("Case 7 - Valid data same priorities (not urgent vs not urgent)", visit3,
				visit2, true);
		testCompare("Case 8 - Valid data same visit (visit1 vs visit1)", visit1,
				visit1, true);
	}

	public static void testCompare(String testCase, ClinicVisit visit1, 
			ClinicVisit visit2, boolean expectValid){
		out.println("   " + testCase);
		int comparison;
		VisitSorter sorter = new VisitSorter();
		
		try {
			out.print("\tThe visits that are being compared:\n\t" + visit1.toString() 
					+ "\n\t" + visit2.toString());
			comparison = sorter.compare((Visit)visit1, (Visit)visit2);
			out.print("\n\tVisit1 compared to Visit2 is: " + comparison);
			
			if (!expectValid)
				out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage());
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (NullPointerException npe) {
			out.print("\t" + npe.getMessage());
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " "
					+ e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				out.print(" Expected Valid.");
		}

		out.println("\n");
	}
}
