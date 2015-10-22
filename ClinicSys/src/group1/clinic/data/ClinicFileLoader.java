/**
 * Defines a ClinicFileLoader type.
 */
package group1.clinic.data;

import group1.clinic.business.ClinicPatient;
import group1.clinic.business.ClinicVisit;
import group1.util.ListUtilities;
import group1.clinic.business.Priority;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import dw317.lib.medication.DINMedication;
import dw317.lib.medication.Medication;
import dw317.lib.medication.NDCMedication;

/**
 * This class represents a File Loader.
 * It provides methods to load files containing Patients and Visits.

 * 
 * @author Katherine Richer
 * @author Erika Bourque
 * @version 18/10/2015
 *
 */

public class ClinicFileLoader {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ClinicFileLoader() {
	}

	/**
	 * for every line given a file it creates a new patient and is stored in an
	 * array of Patients.
	 * 
	 * 
	 * @param String
	 *            filename
	 * @throws IOException
	 * @return Patient[]
	 */
	public static Patient[] getPatientListFromSequentialFile(String filename) throws IOException {
		Patient[] patientInfo = new Patient[30];
		Scanner inputStream = null;
		int patientCounter = 0;
		String ramq;
		String firstName;
		String lastName;
		String telephone;
		String medScheme;
		String medNumber;
		String medName;
		String condition;
		Medication medication = null;

		try {
			// If file is not found, throws IOException
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputStream = new Scanner(bufferedReader);
			String record = null;

			// Loop for getting all the records.
			while (inputStream.hasNext()) {

				record = inputStream.nextLine().trim();

				if (!record.isEmpty()) {

					try {
						// Getting and splitting data from row
						String[] recordArray = record.split("\\*");

						// If array has too little or too much data
						// skips over rest of current loop
						if ((recordArray.length < 3) || (recordArray.length > 8)) {
							throw new IllegalArgumentException("The record contains too much or too little data.");
						}

						ramq = recordArray[0].trim();
						firstName = recordArray[1].trim();
						lastName = recordArray[2].trim();

						// Attempting to create a patient using the data given.
						// Sets telephone, medication, etc if present.

						patientInfo[patientCounter] = new ClinicPatient(firstName, lastName, ramq);

						// Checks if telephone number is present, sets it.
						if (recordArray.length >= 4) {
							telephone = recordArray[3].trim();
							patientInfo[patientCounter].setTelephoneNumber(Optional.of(telephone));
						}

						// Checks if medication is present, sets it.
						if (recordArray.length >= 7) {
							medScheme = recordArray[4].trim();
							medNumber = recordArray[5].trim();
							medName = recordArray[6].trim();

							// Checking to make sure all aspects of medication
							// exist, then set it.
							if ((!medScheme.equals("")) && (!medNumber.equals("")) && (!medName.equals(""))) {
								if (medScheme.equalsIgnoreCase("DIN")) {
									medication = new DINMedication(medNumber, medName);
									patientInfo[patientCounter].setMedication(Optional.of(medication));
								}

								else if (medScheme.equalsIgnoreCase("NDC")) {
									medication = new NDCMedication(medNumber, medName);
									patientInfo[patientCounter].setMedication(Optional.of(medication));
								}
							}
						}

						if (recordArray.length == 8) {
							condition = recordArray[7].trim();

							// if condition exists, set it
							if (!condition.equals("")) {
								patientInfo[patientCounter].setExistingConditions(Optional.of(condition));
							}
						}
						// Moves patient array index up.
						patientCounter++;

					} // End of Try
					catch (IllegalArgumentException iae) {
						System.out.println("The following record caused an error.");
						System.out.println(record);
						System.out.println(iae.getMessage() + "\n");
						continue;
					}

				} // end of if statement is not empty

			} // end of while

			// } // end of if statement

			patientInfo = resizePatient(patientInfo, patientCounter);

			return patientInfo;
		} // end of try
		catch (IOException e) {
			throw new IOException("File not found.\n" + e.getMessage() + "\n");
		} finally {
			if (inputStream != null)
				inputStream.close();
		}

	}

	/**
	 * takes a patient array and resizes it full to capacity
	 * 
	 * 
	 * 
	 * @param Patient[]
	 *            big
	 * @param int
	 *            counter
	 * @return Patient[]
	 */
	private static Patient[] resizePatient(Patient[] big, int counter) {
		Patient[] newPatient = new Patient[counter];

		for (int i = 0; i < counter; i++) {
			newPatient[i] = big[i];
		}

		return newPatient;

	}

	/**
	 * creates a new visit element for each visit with the same ramq as the
	 * patient and stores it in an array or visits
	 * 
	 * @param String
	 *            filename
	 * @param Patient[]
	 *            patientList
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 * @return Visit[]
	 */

	public static Visit[] getVisitListFromSequentialFile(String filename, Patient[] patientList)
			throws IOException, IllegalArgumentException, NullPointerException {
		Visit[] visitInfo = new Visit[30];
		Scanner inputStream = null;
		int visitCounter = 0;
		String ramq;
		
		int patientLocation;
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int min = 0;
		int priorityCode;
		String yearStr;
		String monthStr;
		String dayStr;
		String hourStr;
		String minStr;
		String priorityCodeStr;
		String complaint;

		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputStream = new Scanner(bufferedReader);
			String record = null;

			// Gets the RAMQ only from patient list
			// Will throw a NullPoiterException if patientList is null.
			if (patientList == null)
			{
				throw new NullPointerException("Patient list is null.");
			}
			String[] ramqArray = new String[patientList.length];
			for (int i = 0; i < patientList.length; i++) {
				ramqArray[i] = patientList[i].getRamq().getRamq();
			}
			
			while (inputStream.hasNext()) {

				record = inputStream.nextLine().trim();

				if (!record.isEmpty()) {

					try {

						String[] recordArray = record.split("\\*");
						if ((recordArray.length < 6) || (recordArray.length > 13)) {
							throw new IllegalArgumentException("The record contains too much or too little data.");
						}

						ramq = recordArray[0];

						// Checks if RAMQ exists in patient file, gets location
						patientLocation = ListUtilities.binarySearch(ramqArray, ramq);

						if (patientLocation == -1) {
							throw new IllegalArgumentException("The record RAMQ is not a patient.");
						}

						// Will be valid because patientList is valid
						visitInfo[visitCounter] = new ClinicVisit(patientList[patientLocation]);

						// Represents the registration date
						yearStr = recordArray[1];
						monthStr = recordArray[2];
						dayStr = recordArray[3];
						hourStr = recordArray[4];
						minStr = recordArray[5];

						if ((!yearStr.equals("")) && (!monthStr.equals("")) && (!dayStr.equals(""))
								&& (!hourStr.equals("")) && (!minStr.equals(""))) {
							// Attempting to parse ints for localdatetime
							year = Integer.parseInt(yearStr);
							month = Integer.parseInt(monthStr);
							day = Integer.parseInt(dayStr);
							hour = Integer.parseInt(hourStr);
							min = Integer.parseInt(minStr);

							LocalDateTime registrationTime = LocalDateTime.of(year, month, day, hour, min);
							visitInfo[visitCounter].setRegistrationDateAndTime(Optional.of(registrationTime));
						}

						// Checks if triage date is present, sets it.
						if (recordArray.length >= 11) {
							// Represents the triage date
							yearStr = recordArray[6];
							monthStr = recordArray[7];
							dayStr = recordArray[8];
							hourStr = recordArray[9];
							minStr = recordArray[10];

							if ((!yearStr.equals("")) && (!monthStr.equals("")) && (!dayStr.equals(""))
									&& (!hourStr.equals("")) && (!minStr.equals(""))) {
								// Attempting to parse ints for localdatetime
								year = Integer.parseInt(yearStr);
								month = Integer.parseInt(monthStr);
								day = Integer.parseInt(dayStr);
								hour = Integer.parseInt(hourStr);
								min = Integer.parseInt(minStr);

								LocalDateTime triageTime = LocalDateTime.of(year, month, day, hour, min);
								visitInfo[visitCounter].setTriageDateAndTime(Optional.of(triageTime));
							}
						}

						// Checks if priority is present, sets it.
						if (recordArray.length >= 12) {
							priorityCodeStr = recordArray[11];

							if (!priorityCodeStr.equals("")) {
								priorityCode = Integer.parseInt(priorityCodeStr);
								visitInfo[visitCounter].setPriority(Priority.getPriorityCode(priorityCode));
							}
						}
						// Checks if complaint is present, sets it.
						if (recordArray.length == 13) {
							complaint = recordArray[12];
							if (!complaint.equals("")) {
								visitInfo[visitCounter].setComplaint(Optional.of(complaint));
							}
						}
						visitCounter++;
					} catch (IllegalArgumentException iae) {
						System.out.println("The following record caused an error.");
						System.out.println(record);
						System.out.println(iae.getMessage() + "\n");
						continue;
					} catch (DateTimeException dte) {
						System.out.println("The following record caused an error.");
						System.out.println(record);
						System.out.println(dte.getMessage() + "\n");
						continue;
					}

				} // end if record is not empty

			} // end of while loop
			visitInfo = resizeVisit(visitInfo, visitCounter);

			return visitInfo;
		}

		catch (IOException e) {
			throw new IOException("File not found.\n" + e.getMessage());
		} catch (NullPointerException n) {
			throw new NullPointerException(n.getMessage());
		} finally {
			if (inputStream != null)
				inputStream.close();
		}

	}

	/**
	 * takes a visit array and resizes it full to capacity
	 * 
	 * 
	 * 
	 * @param Visit[]
	 *            big
	 * @param int
	 *            counter
	 * @return Visit[]
	 */
	private static Visit[] resizeVisit(Visit[] big, int counter) {
		Visit[] newVisit = new Visit[counter];

		for (int i = 0; i < counter; i++) {
			newVisit[i] = big[i];
		}

		return newVisit;
	}

}
