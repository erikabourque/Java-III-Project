package group1.clinic.data;

import group1.clinic.data.ClinicFileLoader;
import group1.util.ListUtilities;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;

import static java.lang.System.out;

import java.io.IOException;

public class ClinicFileLoaderTest {
	public static void main(String[] args) {
		
		testPatientSequentialFile();
		testVisitSequentialFile();
	}

	public static void testPatientSequentialFile() 
	{
		testPatientSequentialFile("Case 1: test with patients1", "../ClinicSys/datafiles/unsorted/patients1.txt", true );
		testPatientSequentialFile("Case 2 : test with patients2","../ClinicSys/datafiles/unsorted/patients2.txt",true);
		testPatientSequentialFile("Case 3: test with patients4","../ClinicSys/datafiles/unsorted/patients4.txt",true);
		testPatientSequentialFile("Case 4 : test an empty file","../ClinicSys/datafiles/unsorted/emptyFile.txt",true);
		testPatientSequentialFile("Case 5 : test an empty Line","../ClinicSys/datafiles/unsorted/emptyLine.txt",true);
		testPatientSequentialFile("Case 6 : test  with empty multiple lines","../ClinicSys/datafiles/unsorted/emptyMultipleLines.txt",true);
		testPatientSequentialFile("Case 7 : test with missing Asterrisks","../ClinicSys/datafiles/unsorted/missingAsterisks.txt",true);
		testPatientSequentialFile("Case 8 : test missing first name","../ClinicSys/datafiles/unsorted/missingFName.txt",true);
		testPatientSequentialFile("Case 9 : test missing last name","../ClinicSys/datafiles/unsorted/missingLname.txt",true);
		testPatientSequentialFile("Case 10 : test missing ramq","../ClinicSys/datafiles/unsorted/missingRAMQ.txt",true);
	}
	
	public static void testPatientSequentialFile(String testCase, String filename, boolean expectValid ) 
	{
		
		
		out.println("   " + testCase);
	
		try {
			Patient[] p = ClinicFileLoader.getPatientListFromSequentialFile(filename);
			out.print("\tThe Patient array has been created was created: \n" );
	
			for (int i = 0; i < p.length; i++)
			{
				System.out.println(p[i].toString());
			}
			
			if (!expectValid)
				out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage());
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (IOException ioe) {
			out.print("\t" + ioe.getMessage());
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

	public static void testVisitSequentialFile(){
		Patient[] patients;
		try {
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 1: test with visit1", 
					"../ClinicSys/datafiles/unsorted/visits1.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients2.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 2: test with visit2", 
					"../ClinicSys/datafiles/unsorted/visits2.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients4.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 3: test with visit4", 
					"../ClinicSys/datafiles/unsorted/visits4.txt", patients, true );
			
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 4: test with empty line", 
					"../ClinicSys/datafiles/unsorted/emptyLineVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 5: test with empty multiple lines", 
					"../ClinicSys/datafiles/unsorted/emptyMultipleLinesVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 6: test with missing asterisks", 
					"../ClinicSys/datafiles/unsorted/missingAsterisksVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 7: test with missing registration", 
					"../ClinicSys/datafiles/unsorted/missingAllRegistrationTimeVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 8: test with missing Ramq", 
					"../ClinicSys/datafiles/unsorted/missingRAMQVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 9: test with missing year ", 
					"../ClinicSys/datafiles/unsorted/missingYearVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 10: test with not enough values", 
					"../ClinicSys/datafiles/unsorted/notEnoughValuesVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 11: test with wrong ramq", 
					"../ClinicSys/datafiles/unsorted/ramqWrongVisit.txt", patients, true );
			patients = ClinicFileLoader.getPatientListFromSequentialFile("../ClinicSys/datafiles/unsorted/patients1.txt");
			ListUtilities.sort(patients);
			testVisitSequentialFile("Case 12: test with too many values", 
					"../ClinicSys/datafiles/unsorted/tooManyValuesVisit.txt", patients, true );
			patients = null;
			testVisitSequentialFile("Case 4: test with null patients", 
					"../ClinicSys/datafiles/unsorted/visits1.txt", patients, false );
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}
	
	public static void testVisitSequentialFile(String testCase, String filename, Patient[] patients, boolean expectValid){
		out.println("   " + testCase);

		try {
			Visit[] v = ClinicFileLoader.getVisitListFromSequentialFile(filename, patients);
			out.print("\tThe Visit array has been created : \n" );

			for (int i = 0; i < v.length; i++)
			{
				System.out.println(v[i].toString());
			}
			
			if (!expectValid)
				out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			out.print("\t" + iae.getMessage());
			if (expectValid)
				out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (IOException ioe) {
			out.print("\t" + ioe.getMessage());
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
