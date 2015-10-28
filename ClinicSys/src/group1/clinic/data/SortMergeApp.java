package group1.clinic.data;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import group1.util.ListUtilities;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.VisitSorter;

/**
 * An app that will load, sort, merge and save data text files.
 *  
 *  @author Uen Yi Hung
 */
public class SortMergeApp {

	/**
	 * The main class. Everything is called from here. It will load, sort, merge
	 * and save patient list and visit list data text files with arrays. It will
	 * also create the output directories and files if they do not exist.
	 * 
	 * Precondition: Assume for now that there is only 2 sets of 13 files.
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// variables
		int numOfFiles = 13;
		String filename = null;
		String saveFilename = null;
		Comparable<Patient>[] patientListAll = null;
		Patient[] patientList = null;
		Comparable<Visit>[] visitListAll = null;
		Visit temp;
		Visit[] visitList = new Visit[10];
		VisitSorter sortVisit = new VisitSorter();

		// CREATING DIRECTORIES
		File sortedDir = new File("datafiles/sorted");
		if (!sortedDir.exists())
			sortedDir.mkdirs();
		File databaseDir = new File("datafiles/database");
		if (!databaseDir.exists())
			databaseDir.mkdirs();

		// LOADING SORTING DATA
		for (int i = 1; i <= numOfFiles; i++) {
			try {
				filename = "datafiles/unsorted/" + "patients" + i + ".txt";
				patientList = ClinicFileLoader.getPatientListFromSequentialFile(filename);
				// patientList = (Patient[]) (PatientLoadList(filename));
				ListUtilities.sort(patientList);

				filename = "datafiles/unsorted/" + "visits" + i + ".txt";
				visitList = ClinicFileLoader.getVisitListFromSequentialFile(filename, patientList);
				// visitList = (Visit[]) (VisitLoadList(patientList, filename));
				ListUtilities.sort(visitList);

				/**
				 * If we were to sort by priority for (int x = 0; x <
				 * visitList.length - 1; x++) { for (int y = x + 1; y <
				 * visitList.length; y++) { if (sortVisit.compare(visitList[x],
				 * visitList[y]) > 0) {
				 * 
				 * temp = visitList[y]; visitList[y] = visitList[x];
				 * visitList[x] = temp; temp = null; } } } COMMENTED OUT SORT BY
				 * PRIORITY
				 */
			} catch (IOException ioe) {
				out.println(ioe.getMessage());
				out.println("Error with loading file: " + filename + ".");
			} catch (NullPointerException npe) {
				out.println(npe.getMessage());
				out.println("Invalid NULL value, please verify and correct the file: " + filename);
			} catch (IllegalArgumentException iae) {
				out.println(iae.getMessage());
				out.println("Please verify and correct the file: " + filename);
			}

			// SAVING SORTED DATA
			try {
				saveFilename = sortedDir + "/sortedPatients" + i + ".txt";
				ListUtilities.saveListToTextFile(patientList, saveFilename);

				saveFilename = sortedDir + "/sortedVisits" + i + ".txt";
				ListUtilities.saveListToTextFile(visitList, saveFilename);
			} catch (FileNotFoundException fnfe) {
				out.println("Error, the file " + saveFilename + " does not exists.");
			} catch (UnsupportedEncodingException uee) {
				out.println("Error, the encoding is not supported.");
			}

			// MERGING
			if (patientListAll == null) {
				patientListAll = patientList;
				visitListAll = visitList;
			} else {
				try {
					patientListAll = ListUtilities.merge(patientListAll, patientList,
							"datafiles/sorted/duplicatePatients.txt");
					visitListAll = ListUtilities.merge(visitListAll, visitList, "datafiles/sorted/duplicateVisits.txt");

					for (int x = 0; x < visitListAll.length - 1; x++) {
						for (int y = x + 1; y < visitListAll.length; y++) {
							if (sortVisit.compare((Visit) visitListAll[x], (Visit) visitListAll[y]) > 0) {

								temp = (Visit) visitListAll[y];
								visitListAll[y] = visitListAll[x];
								visitListAll[x] = temp;
								temp = null;
							}
						}
					}
				} catch (IllegalArgumentException iae) {
					iae.getMessage();
				} catch (IOException ioe) {
					out.println("Error, The file(s) does not exist.");
				}
			}
		}

		// SAVING THE MERGED PATIENT[] and Visit[].
		try {
			saveFilename = databaseDir + "/Patient.txt";
			ListUtilities.saveListToTextFile(patientListAll, saveFilename);

			saveFilename = databaseDir + "/Visit.txt";
			ListUtilities.saveListToTextFile(visitListAll, saveFilename);
		} catch (FileNotFoundException fnfe) {
			out.println("Error, the file " + saveFilename + " does not exists.");
		} catch (UnsupportedEncodingException uee) {
			out.println("Error, the encoding is not supported.");
		}

	}
	/**
	 * This was not suppose to be in the merge app but will leave it here for
	 * now for future possible need or reference /** This method will load line
	 * by line the text data from the specified visit text file. Create a visit
	 * array with the data extracted from the record, after matching a RAMQ ID
	 * with a patient of its patient list.
	 * 
	 * Precondition: Assumes patientList is not null and the text file to load
	 * from must exist.
	 * 
	 * @return Visit[] An full array containing the visit records of the
	 *         patients.
	 * 
	 *
	 *         private static Visit[] VisitLoadList(Patient[] patientList,
	 *         String filename) {
	 * 
	 *         // VARIABLES boolean recordStillHas; int visitNum = 0; int
	 *         regYear, regMonth, regDay, regHour, regSecond; int triageYear,
	 *         triageMonth, triageDay, triageHour, triageSecond; Priority
	 *         priorityCode; Scanner inputStream = null; String bkRecord; String
	 *         complaint; String ramq; String record; String[] patientListRamq =
	 *         new String[patientList.length]; Visit[] aVisitList = new
	 *         Visit[10];
	 * 
	 *         try { File file = new File(filename); if (file.exists()) {
	 *         BufferedReader bufferedReader = new BufferedReader( new
	 *         InputStreamReader(new FileInputStream(filename),
	 *         StandardCharsets.UTF_8)); inputStream = new
	 *         Scanner(bufferedReader);
	 * 
	 *         for (int i = 0; i < patientList.length; i++) patientListRamq[i] =
	 *         patientList[i].getRamq().getRamq();
	 * 
	 *         while (inputStream.hasNext()) { record = inputStream.nextLine();
	 *         bkRecord = record; recordStillHas = true;
	 * 
	 *         if (record.trim().equals("")) continue; while (recordStillHas) {
	 *         if ((record.split("\\*", 20).length - 1) < 11) { out.println(
	 *         "Not enough delimiters in " + filename);
	 *         out.println(bkRecord.trim() + "\n"); recordStillHas = false;
	 *         continue; }
	 * 
	 *         ramq = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         try { aVisitList[visitNum] = new ClinicVisit( (Patient)
	 *         (patientList[ListUtilities.binarySearch(patientListRamq,
	 *         ramq)]));
	 * 
	 *         regYear = Integer.parseInt(record.substring(0,
	 *         record.indexOf("*"))); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         regMonth = Integer.parseInt(record.substring(0,
	 *         record.indexOf("*"))); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         regDay = Integer.parseInt(record.substring(0,
	 *         record.indexOf("*"))); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         regHour = Integer.parseInt(record.substring(0,
	 *         record.indexOf("*"))); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         regSecond = Integer.parseInt(record.substring(0,
	 *         record.indexOf("*"))); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         if (record.substring(0, record.indexOf("*")).equals(""))
	 *         triageYear = 0; else triageYear =
	 *         Integer.parseInt(record.substring(0, record.indexOf("*")));
	 *         record = record.substring(record.indexOf("*") + 1);
	 * 
	 *         if (record.substring(0, record.indexOf("*")).equals(""))
	 *         triageMonth = 0; else triageMonth =
	 *         Integer.parseInt(record.substring(0, record.indexOf("*")));
	 *         record = record.substring(record.indexOf("*") + 1);
	 * 
	 *         if (record.substring(0, record.indexOf("*")).equals(""))
	 *         triageDay = 0; else triageDay =
	 *         Integer.parseInt(record.substring(0, record.indexOf("*")));
	 *         record = record.substring(record.indexOf("*") + 1);
	 * 
	 *         if (record.substring(0, record.indexOf("*")).equals(""))
	 *         triageHour = 0; else triageHour =
	 *         Integer.parseInt(record.substring(0, record.indexOf("*")));
	 *         record = record.substring(record.indexOf("*") + 1);
	 * 
	 *         if (record.substring(0, record.indexOf("*")).equals(""))
	 *         triageSecond = 0; else triageSecond =
	 *         Integer.parseInt(record.substring(0, record.indexOf("*")));
	 *         record = record.substring(record.indexOf("*") + 1);
	 * 
	 *         if (record.substring(0, record.indexOf("*")).equals(""))
	 *         priorityCode = Priority.NOTASSIGNED; else priorityCode = Priority
	 *         .getPriorityCode(Integer.parseInt(record.substring(0,
	 *         record.indexOf("*")))); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         if ((bkRecord.split("\\*", 20).length - 1) > 12) { complaint =
	 *         record.substring(0, record.indexOf(" ")); record =
	 *         record.substring(record.indexOf(" ") + 1); recordStillHas = true;
	 *         } else { complaint = record; recordStillHas = false; }
	 * 
	 *         aVisitList[visitNum].setPriority(priorityCode);
	 *         aVisitList[visitNum].setRegistrationDateAndTime(regYear,
	 *         regMonth, regDay, regHour, regSecond/60); if (triageYear != 0)
	 *         aVisitList[visitNum].setTriageDateAndTime(triageYear,
	 *         triageMonth, triageDay, triageHour, triageSecond/60);
	 *         aVisitList[visitNum].setComplaint(Optional.ofNullable(complaint))
	 *         ; visitNum++;
	 * 
	 *         } catch (NumberFormatException nfe) { out.println(
	 *         "Please enter valid whole numbers.\n" + bkRecord); recordStillHas
	 *         = false; } catch (ArrayIndexOutOfBoundsException aioobe) {
	 *         out.println("No matching patient in datafiles/unsorted/patients"
	 *         + filename.substring(25) + " for:"); recordStillHas = false;
	 *         out.println(bkRecord + "\n"); } catch (IllegalArgumentException
	 *         iae) { iae.getMessage(); recordStillHas = false;
	 *         out.println(bkRecord); } } } } } catch (IOException e) {
	 *         System.out.println("Error loading file - " + e.getMessage()); }
	 *         finally { if (inputStream != null) inputStream.close(); }
	 * 
	 *         Visit[] temp = new Visit[visitNum]; for (int i = 0; i <
	 *         temp.length; i++) { temp[i] = aVisitList[i]; } return temp;
	 * 
	 *         }
	 * 
	 *         /** This method will load line by line the text data from the
	 *         specified patient text file. Create a patient array with the data
	 *         extracted from each record.
	 * 
	 *         Precondition: The text file to load from must exist.
	 * 
	 * @return Patient[] An full array containing the patient records.
	 * 
	 *
	 *         private static Object[] PatientLoadList(String filename) { //
	 *         VARIABLES boolean recordStillHas; int patientNum = 0; Medication
	 *         aMedication = null; Patient[] aPatientList = new Patient[10];
	 *         Scanner inputStream = null; String recordBK; String condition;
	 *         String firstName, lastName; String medName, medNumber; String
	 *         record = null; String ramq; String scheme; String telephone;
	 * 
	 *         try { File file = new File(filename); if (file.exists()) {
	 *         BufferedReader bufferedReader = new BufferedReader( new
	 *         InputStreamReader(new FileInputStream(filename),
	 *         StandardCharsets.UTF_8)); inputStream = new
	 *         Scanner(bufferedReader);
	 * 
	 *         while (inputStream.hasNext()) { record = inputStream.nextLine();
	 *         recordBK = record; recordStillHas = true;
	 * 
	 *         if (record.trim().equals("")) continue; while (recordStillHas) {
	 *         if ((record.split("\\*", 20).length - 1) < 7) { out.println(
	 *         "Not enough delimiters in " + filename);
	 *         out.println(recordBK.trim() + "\n"); recordStillHas = false;
	 *         continue; }
	 * 
	 *         ramq = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         firstName = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         lastName = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         telephone = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         scheme = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1);
	 * 
	 *         medNumber = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1); if
	 *         (!scheme.trim().equals("") && medNumber.trim().equals("")) {
	 *         out.println("Error, " + scheme + " number may not be null.");
	 *         out.println(recordBK + "\n"); recordStillHas = false; continue; }
	 * 
	 *         medName = record.substring(0, record.indexOf("*")); record =
	 *         record.substring(record.indexOf("*") + 1); if
	 *         (!scheme.trim().equals("") && medName.trim().equals("")) {
	 *         out.println("Error, medication name may not be null.");
	 *         recordStillHas = false; continue; }
	 * 
	 *         if (record.split("\\*", 20).length > 1) { condition =
	 *         record.substring(0, record.indexOf(" ")); record =
	 *         record.substring(record.indexOf(" ") + 1); recordBK = record;
	 *         recordStillHas = true; } else { condition = record;
	 *         recordStillHas = false; } try { aPatientList[patientNum] = new
	 *         ClinicPatient(firstName, lastName, ramq);
	 *         aPatientList[patientNum].setTelephoneNumber(Optional.ofNullable(
	 *         telephone));
	 *         aPatientList[patientNum].setExistingConditions(Optional.
	 *         ofNullable(condition));
	 * 
	 *         if (scheme.equalsIgnoreCase("DIN")) aMedication = new
	 *         DINMedication(medNumber, medName); else if
	 *         (scheme.equalsIgnoreCase("NDC")) aMedication = new
	 *         NDCMedication(medNumber, medName);
	 * 
	 *         aPatientList[patientNum].setMedication(Optional.ofNullable(
	 *         aMedication)); patientNum++;
	 * 
	 *         } catch (IllegalArgumentException iae) {
	 *         out.println(iae.getMessage()); out.println("In file: " + filename
	 *         + " for: " + recordBK + "\n"); recordStillHas = false; } // end
	 *         of try-catch } // end of while record still got data } // end of
	 *         while input stream } // end of if file exists
	 * 
	 *         } catch (IOException e) { System.out.println(
	 *         "Error loading file - " + e.getMessage()); } finally { if
	 *         (inputStream != null) inputStream.close(); }
	 * 
	 *         Patient[] temp = new Patient[patientNum]; for (int i = 0; i <
	 *         temp.length; i++) { temp[i] = aPatientList[i]; } return temp;
	 * 
	 *         } END OF COMMENTED OUT LOAD METHODS
	 */

}// END OF APP
