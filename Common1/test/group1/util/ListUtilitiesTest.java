/**
 * 
 */
package group1.util;

import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This Class tests all the methods except the main method from the
 * ListUtilities class.
 * 
 * @author Danieil Skrinikov
 * @author Erika Bourque (two param sort and binary search)
 * @version 10/14/2015
 *
 */
public class ListUtilitiesTest {

	/**
	 * Main method of the test class used to call different test methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		testSaveListToTextFile();
		sortTest();
		mergeTest();
		testTwoParamSortMethod();
		testBinarySearch();

	}

	/**
	 * Testing the saveListToTextFile Method
	 * 
	 * 
	 */
	public static void testSaveListToTextFile() {

		String[] objects = {};
		// Testing the first method which only takes an array of Objects.
		// saveListToTextFile(Object[] objects, String fileName)

		// Empty String array.
		try {
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/NullTest.txt");
			// NullTest should not have anything inside it.
			System.out
					.println("Completed saving empty array. No Append. No Encoding.");
		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

		// String array with Empty lines.
		objects = new String[5];
		objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
		objects[2] = "CARA55021080*Andrew*Carrey*5146441477****";
		objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

		try {
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTest.txt");
			System.out
					.println("Completed saving with empty lines. No Append. No Encoding.");
		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

		// To capacity String[] testing
		objects = new String[5];
		objects[0] = "LONS10571222*Sera*London*5148888764*DIN*02238830*Clavulin400*Cold";
		objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
		objects[2] = "CARA55021080*Andrew*Carrey*5146441477****";
		objects[3] = "CAMS05581734*Stephanie*Cambell*4382091979*NDC*50458-587-01*Concerta 54mg*ADD";
		objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

		try {

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityArrayTest.txt");
			System.out
					.println("Completed saving full to capacity array. No Append. No Encoding.\n");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}
		/*
		 * First method check up completed. Now we check if the overloaded
		 * method with append on.
		 */

		// Null objects write with append test. Should not create any files.
		try {

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/unsorted/NullTest.txt", true);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/unsorted/NullTest.txt", false);

			// NullTest should not have any values.
			System.out
					.println("Completed saving empty array. With Append. No Encoding");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

		// Test with appendable uncomplete files.
		try {

			objects = new String[5];
			objects[0] = "LONS10571222*Sera*London*5148888764*DIN*02238830*Clavulin400*Cold";
			objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
			objects[3] = "CAMS05581734*Stephanie*Cambell*4382091979*NDC*50458-587-01*Concerta 54mg*ADD";
			objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestAppendable.txt", true);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestAppendable.txt", true);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestNonAppendable.txt",
					false);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestNonAppendable.txt",
					false);

			// LinesTestAppendable should have the array twice and
			// LinesTestAppendable only once

			System.out
					.println("Completed saving empty lines array. With Append. No Encoding");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

		// Full to capacity testing
		try {

			objects = new String[5];
			objects[0] = "LONS10571222*Sera*London*5148888764*DIN*02238830*Clavulin400*Cold";
			objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
			objects[2] = "LESA85582812*Samantha*Le*5146795431*DIN*02042479*Marvelon28*Birth Control";
			objects[3] = "CAMS05581734*Stephanie*Cambell*4382091979*NDC*50458-587-01*Concerta 54mg*ADD";
			objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestAppendable.txt", true);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestAppendable.txt", true);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestNonAppendable.txt",
					false);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestNonAppendable.txt",
					false);

			// LinesTestAppendable should have the array twice and
			// LinesTestAppendable only once

			System.out
					.println("Completed saving full to capacity array. With Append. No Encoding\n");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}
		/*
		 * Now we need to check if we can call the character encoding method
		 */
		// Null objects write with append test. Should not create any files.
		
		Charset encoding = StandardCharsets.UTF_8;
		try {

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/unsorted/NullTest.txt", true, encoding);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/unsorted/NullTest.txt", false, encoding);

			// NullTest should not have any values.
			System.out
					.println("Completed saving empty array. With Append. With Encoding");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

		// Test with appendable uncomplete files.
		try {

			objects = new String[5];
			objects[0] = "LONS10571222*Sera*London*5148888764*DIN*02238830*Clavulin400*Cold";
			objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
			objects[3] = "CAMS05581734*Stephanie*Cambell*4382091979*NDC*50458-587-01*Concerta 54mg*ADD";
			objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestAppendable.txt", true, encoding);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestAppendable.txt", true , encoding);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestNonAppendable.txt",
					false, encoding);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/EmptyLinesTestNonAppendable.txt",
					false, encoding);

			// LinesTestAppendable should have the array twice and
			// LinesTestAppendable only once

			System.out
					.println("Completed saving empty lines array. With Append. With Encoding");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

		// Full to capacity testing
		try {

			objects = new String[5];
			objects[0] = "LONS10571222*Sera*London*5148888764*DIN*02238830*Clavulin400*Cold";
			objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
			objects[2] = "LESA85582812*Samantha*Le*5146795431*DIN*02042479*Marvelon28*Birth Control";
			objects[3] = "CAMS05581734*Stephanie*Cambell*4382091979*NDC*50458-587-01*Concerta 54mg*ADD";
			objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestAppendable.txt", true, encoding);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestAppendable.txt", true, encoding);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestNonAppendable.txt",
					false, encoding);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/ToCapacityTestNonAppendable.txt",
					false, encoding);

			// LinesTestAppendable should have the array twice and
			// LinesTestAppendable only once

			System.out
					.println("Completed saving full to capacity array. With Append. With Encoding\n");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Encoding Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}
		
		//-------------------------------------------------------------------------------------------------
		//Exception handling tests. Charsets.
		try {

			objects = new String[5];
			objects[0] = "LONS10571222*Sera*London*5148888764*DIN*02238830*Clavulin400*Cold";
			objects[1] = "MCCA90553054*Alexia*McCormick*4506762645*DIN*02276194*Pregvit5*Pregnancy";
			objects[2] = "LESA85582812*Samantha*Le*5146795431*DIN*02042479*Marvelon28*Birth Control";
			objects[3] = "CAMS05581734*Stephanie*Cambell*4382091979*NDC*50458-587-01*Concerta 54mg*ADD";
			objects[4] = "GUAL79012075*Laurence*Guay*5149262214*DIN*02362171*Pataday*Eye allergy";

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/CharsetTest.txt", true, StandardCharsets.US_ASCII);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/CharsetTest.txt", true, StandardCharsets.ISO_8859_1);

			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/CharsetTest2.txt",
					false, StandardCharsets.UTF_16LE);
			ListUtilities.saveListToTextFile(objects,
					"../ClinicSys/datafiles/testoutput/CharsetTest2.txt",
					false, StandardCharsets.UTF_16);

			// LinesTestAppendable should have the array twice and
			// LinesTestAppendable only once

			System.out
					.println("Tested saving with different charsets.\n");

		} catch (UnsupportedEncodingException uee) {

			System.out.println("Unsupported Exception Called");
			System.out.println(uee);
		} catch (FileNotFoundException fne) {

			System.out.println("File Not Found Exception Called");
			System.out.println(fne);

		}

	}
	/** Here we test the sort method. Reasoning behind the use of Strings is that Strings are a comparable objects and 
	 * since we could not build path to ClinicSys project we could not use ClinicPatient as our test object.
	 * 
	 * 
	 */
	public static void sortTest() {

		// Empty Object array testing.
		try {
			System.out.println("\nTest 1 - Sort: Null object test.");
			Comparable<String>[] list = null;
			ListUtilities.sort(list);
		} catch (IllegalArgumentException iae) {

			System.out.println("Empty Object Array: " + iae);
		}

		// Not to full capacity array testing
		try {
			
			
			System.out.println("Test 2 - Sort: Not Full to capacity array");

			Comparable<String>[] list = new String[7];
			
			list[0] = new String("MCCA90553054, Alexia, McCormick");
			list[3] = new String("CAUP35120577, Philippe, Cauchon");
			list[6] = new String("MCCA90553054, Alexia, McCormick");
			

			ListUtilities.sort(list);

			
			
		} catch (IllegalArgumentException iae) {

			System.out.println("Not To Full Capacity Test: " + iae);
		}

		// Full to capacity Array testing. Error Message not expected.
		try {
			
			System.out.println("Test 3 - Sort: Full To capacity");
			Comparable<String>[] list = new String[8];
			list[0] = new String("LESA85582812,Smantha, Le");
			list[1] = new String("MCCA90553054, Alexia, McCormick");
			list[2] = new String("CAUP35120577, Philippe, Cauchon");
			list[3] = new String("CARA55021080, Andrew, Carry");
			list[4] = new String("LONS10571222, Sera, Longeuil");
			list[5] = new String("ROSG70030168, Geaorge, Ross");
			list[6] = new String("THOR73070488, Richard, Thompson");
			list[7] = new String("RUTC99560563, Cynthia, Ruttenburg");
			
			ListUtilities.sort(list);
			System.out.println("\nSorterd Array should be here.\n");
			for(Comparable<String> patient : list){
				
				System.out.println(patient);
			}
			

		} catch (IllegalArgumentException iae) {
			
			System.out.println("Error must not be here");
			System.out.println(iae);
			
		}

	}
	/** Testing the merge method.
	 * 
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public static void mergeTest(){
		//Full to capacity methods testing
		
		Comparable<String>[] list1 = new String[8];
		list1[0] = new String("LESA85582812, Samantha, Le");
		list1[1] = new String("MCCA90553054, Alexia, McCormick");
		list1[2] = new String("CAUP35120577, Pierre, Cauchon");
		list1[3] = new String("CARA55021080, Andrew, Carrey");
		list1[4] = new String("LONS10571222, Sera, London");
		list1[5] = new String("ROSG70030168, George, Ross");
		list1[6] = new String("THOR73070488, Richard, Thompson");
		list1[7] = new String("RUTC99560563, Cynthia, Ruttenberg");
		
		Comparable<String>[] list2 = new String[10];
		list2[0] = new String("SMIM85122501, Mike, Smith");
		list2[1] = new String("RAOV86112001, Vishal, Rao");
		list2[2] = new String("LISH87100101. Shao, Li");
		list2[3] = new String("KHAA87090601, Abdul, Khaan");
		list2[4] = new String("JUAD89080901, Diego, Juan");
		list2[5] = new String("RODM90571050, Maria, Rodriguez");
		list2[6] = new String("SMIJ90053004, Jane, Smiths");
		list2[7] = new String("ALIM91561401, Cmina, Ali");
		list2[8] = new String("CAMS05581734, Stephanie, Cambell");
		list2[9] = new String("GUAL79012075, Laurence, Guay");
		
		ListUtilities.sort(list1);
		ListUtilities.sort(list2);
		try{
			System.out.println("\nTest 1 - Merge: Full Arrays. No duplicates. Different Array sizes.");
		Comparable<String>[] list3 =
			ListUtilities.merge(list1, list2, "../ClinicSys/datafiles/testoutput/testOutputNoDupes.txt");
		
		for(int i = 0 ; i < list3.length ; i++){
			System.out.println(list3[i]);
			
		}
		}
		catch(IOException ioe){
			System.out.println("IOException chought" + ioe);
		}
		
		//Passing same duplicate array into the merge method.
		try{
			System.out.println("\nTest 2 - Merge: Full Arrays. All Dupliactes. (Same array passed)");
			Comparable<String>[] list3 =
					ListUtilities.merge(list1, list1, "../ClinicSys/datafiles/testoutput/testOutputWithDupes.txt");
			for(int i = 0 ; i < list3.length ; i++){
				System.out.println(list3[i]);
				
			}
			
		}
		catch(IOException ioe){
			System.out.println("IOException chought" + ioe);
		}
		
		try{
			Comparable[] list5,list4;
			list4 = null;
			list5 = null;
			System.out.println("\nTest 3 - Merge: Null Arrays.");
			
			ListUtilities.merge(list4, list5, "../ClinicSys/datafiles/testoutput/testOutputNullArrays.txt");
			
		}
		catch(IOException ioe){
			
			System.out.println("IOException chought" + ioe);
			
		}
		catch(IllegalArgumentException iae){
			
			System.out.println(iae);
			
		}
		
		try{
			Comparable<String>[] list4 = new String[3];
			list4[0] = new String("SMIJ90053004, Jane, Smiths");
			list4[1] = new String("SMIJ90053004, Jane, Smiths");
			list4[2] = new String("SMIJ90053004, Jane, Smiths");
			
			System.out.println("\nTest 4 - Merge: Duplicates in same array.");
			
			Comparable[] list3 = ListUtilities.merge(list1, list4, "../ClinicSys/datafiles/testoutput/testOutputWithDupesSameArray.txt");
			for(int i = 0 ; i < list3.length ; i++){
				System.out.println(list3[i]);
				
			}
			
		}
		catch(IOException ioe){
			
			System.out.println("IOException chought" + ioe);
			
			
		}
		catch(IllegalArgumentException iae){
			
			System.out.println(iae);
			
		}
		
		list2[4] = null;
		list2[6] = null;
		list2[9] = null;
		
		try{
			System.out.println("\nTest 5 - Merge: Not to full capacity array.");
			
			ListUtilities.merge(list1, list2, "../ClinicSys/datafiles/testoutput/testOutputWithDupes.txt");
			
		}
		catch(IOException ioe){
			
			System.out.println("IOException chought" + ioe);
			
			
		}
		catch(IllegalArgumentException iae){
			
			System.out.println(iae);
			
		}

		

	}
	
	// Using Strings as test object, null as Comparator for natural ordering of Strings
		public static void testTwoParamSortMethod(){
			out.println("\nTesting the two parameter sort method.");
			testTwoParamSortMethod(
					"Case 1 - Valid data (Apples,Oranges,Bananas)", new String[]{"Apples","Oranges","Bananas"},
					null, true);
			testTwoParamSortMethod(
					"Case 1 - Valid data already sorted (Apples,Bananas,Oranges)", new String[]{"Apples","Bananas","Oranges"},
					null, true);
			testTwoParamSortMethod(
					"Case 1 - Invalid data null field (Apples,null,Bananas)", new String[]{"Apples",null,"Bananas"},
					null, false);
		}
		
		@SuppressWarnings("rawtypes")
		private static void testTwoParamSortMethod(String testCase, Comparable[] list, 
				Comparator sortOrder, boolean expectValid){
			out.println("   " + testCase);

			try {
				out.print("\tThe unsorted list: " + Arrays.toString(list));
				ListUtilities.sort(list, sortOrder);
				out.print("\n\tThe sorted list: " + Arrays.toString(list));

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
		
		public static void testBinarySearch(){
			out.println("\nTesting the binarySearch method.");
			testBinarySearch(
					"Case 1 - Valid data (10 items) search 1st term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Apples", true);
			testBinarySearch(
					"Case 2 - Valid data (10 items) search 2nd term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Bananas", true);
			testBinarySearch(
					"Case 3 - Valid data (10 items) search 3rd term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Blueberries", true);
			testBinarySearch(
					"Case 4 - Valid data (10 items) search 3rd term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Cherries", true);
			testBinarySearch(
					"Case 5 - Valid data (10 items) search 5th term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Kiwis", true);
			testBinarySearch(
					"Case 6 - Valid data (10 items) search 8th term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Oranges", true);
			testBinarySearch(
					"Case 7 - Valid data (10 items) search 6th term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Peaches", true);
			testBinarySearch(
					"Case 8 - Valid data (10 items) search 7th term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Pears", true);
			testBinarySearch(
					"Case 9 - Valid data (10 items) search 9th term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Raspberries", true);
			testBinarySearch(
					"Case 10 - Valid data (10 items) search last term (Apples,Bananas,Blueberries,Cherries,Kiwis,Oranges,Peaches,Pears,Raspberries,Strawberries)", 
					new String[]{"Apples","Bananas","Blueberries","Cherries","Kiwis","Oranges","Peaches","Pears","Raspberries","Strawberries"},
					"Strawberries", true);
			testBinarySearch(
					"Case 11 - Valid data (3 items) search 1st term (Apples,Bananas,Blueberries)", 
					new String[]{"Apples","Bananas","Blueberries"},
					"Apples", true);
			testBinarySearch(
					"Case 12 - Valid data (3 items) search 2nd term (Apples,Bananas,Blueberries)", 
					new String[]{"Apples","Bananas","Blueberries"},
					"Bananas", true);
			testBinarySearch(
					"Case 13 - Valid data (3 items) search 3rd term (Apples,Bananas,Blueberries)", 
					new String[]{"Apples","Bananas","Blueberries"},
					"Blueberries", true);
			testBinarySearch(
					"Case 14 - Valid data (2 items) search 1st term (Apples,Bananas)", 
					new String[]{"Apples","Bananas"},
					"Apples", true);
			testBinarySearch(
					"Case 15 - Valid data (2 items) search 1st term (Apples,Bananas)", 
					new String[]{"Apples","Bananas"},
					"Bananas", true);
			testBinarySearch(
					"Case 16 - Valid data (1 item) search 1st term (Apples)", 
					new String[]{"Apples"},
					"Apples", true);
			testBinarySearch(
					"Case 17 - Invalid data (3 items) search 1st term (Apples,null,Blueberries)", 
					new String[]{"Apples",null,"Blueberries"},
					"Apples", false);
			testBinarySearch(
					"Case 18 - Valid data (3 items) search missing term after list (Apples,Bananas,Blueberries)", 
					new String[]{"Apples","Bananas","Blueberries"},
					"Pears", true);
			testBinarySearch(
					"Case 19 - Valid data (3 items) search missing term before list (Apples,Bananas,Blueberries)", 
					new String[]{"Apples","Bananas","Blueberries"},
					"Ananas", true);
			testBinarySearch(
					"Case 19 - Valid data (3 items) search missing term middle of list (Apples,Bananas,Cherries)", 
					new String[]{"Apples","Bananas","Cherries"},
					"Blueberries", true);
			
		}
		
		private static void testBinarySearch(String testCase, String[] list, 
				String item, boolean expectValid){
			int index;
			out.println("   " + testCase);

			try {
				out.print("\tThe list: " + Arrays.toString(list));
				out.print("\n\tThe item searched for: " + item);
				index = ListUtilities.binarySearch(list, item);
				if (index == -1)
				{
					out.print("\n\tThe result: " + index + ". Result not found.\n\n");
					return;
				}
				out.print("\n\tThe result: " + list[index]);

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
