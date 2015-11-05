/**
 * Represents the patient database.
 */
package group1.clinic.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.lib.medication.Medication;
import dw317.lib.medication.Medication.Scheme;
import group1.clinic.business.ClinicPatient;
import group1.clinic.business.Ramq;
import group1.util.ListUtilities;

/**
 * Represents the patient database as an internal list of patients.
 * 
 * @author Erika Bourque
 * @version 4/11/2015
 */
public class PatientListDB implements PatientDAO {

	private List<Patient> database;
	private final ListPersistenceObject listPersistenceObject;
	private final PatientVisitFactory factory;

	/**
	 * One param constructor. Assigns factory to default.
	 * 
	 * @param listPersistenceObject
	 *            The ListPersistenceObject to be assigned.
	 */
	public PatientListDB(ListPersistenceObject listPersistenceObject) {
		// Data validation
		if (listPersistenceObject == null) {
			throw new IllegalArgumentException("PatientVisitDB error - ListPersistenceObject is null");
		}

		this.listPersistenceObject = listPersistenceObject;
		factory = DefaultPatientVisitFactory.DEFAULT;
		database = listPersistenceObject.getPatientDatabase();
	}

	/**
	 * Two param constructor.
	 * 
	 * @param listPersistenceObject
	 *            The ListPersistenceObject to be assigned.
	 * @param factory
	 *            The PatientVisitFactory to be assigned.
	 */
	public PatientListDB(ListPersistenceObject listPersistenceObject, PatientVisitFactory factory) {
		// Data validation
		if (listPersistenceObject == null) {
			throw new IllegalArgumentException("PatientVisitDB error - ListPersistenceObject is null");
		}

		if (factory == null) {
			throw new IllegalArgumentException("PatientVisitDB error - PatientVisitFactory is null");
		}

		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		database = listPersistenceObject.getPatientDatabase();
	}

	/**
	 * Adds a patient to the database. The patient is added in ramq order as to
	 * keep the database in sorted order. Only one patient can be inserted for a
	 * ramq.
	 *
	 * @param aPatient
	 *            The patient to add to the database.
	 * @throws DuplicatePatientException;
	 *             The requested ramq already exists.
	 */
	@Override
	public void add(Patient aPatient) throws DuplicatePatientException {
		// Make sure aPatient is not null
		if (aPatient == null) {
			throw new IllegalArgumentException("add error - patient is null");
		}

		// Create a copy of aPatient
		Patient copy = new ClinicPatient(aPatient.getName().getFirstName(), aPatient.getName().getLastName(),
				aPatient.getRamq().getRamq());
		copy.setMedication(aPatient.getMedication());
		copy.setExistingConditions(Optional.of(aPatient.getExistingConditions()));
		copy.setTelephoneNumber(Optional.of(aPatient.getTelephoneNumber()));

		int location = 0;
		int comparisonResult;
		boolean found = false;

		// Check for where to place
		while ((location < database.size()) && (!found)) {
			// Positive means copy has not been reached yet in alphabet
			// Negative means copy has been past in alphabet
			comparisonResult = copy.compareTo(database.get(location));

			if (comparisonResult < 0) {
				found = true;
				break;
			}

			// Checks if patient exists already in database
			if (comparisonResult == 0) {
				throw new DuplicatePatientException("Patient already exists in database: " + copy);
			}

			location++;
		}

		database.add(location, copy);
	}

	/**
	 * Saves the list of patients and disconnects from the database.
	 *
	 * @throws IOException
	 *             Problems saving or disconnecting from database.
	 */
	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.savePatientDatabase(database);

		database = null;
	}

	/**
	 * Returns the patient with the specified ramq id.
	 *
	 * @param ramq
	 *            The ramq id of the requested patient.
	 *
	 * @return The patient with the specified ramq id.
	 *
	 * @throws NonExistingPatientException
	 *             If there is no patient with the specified ramq id.
	 */
	@Override
	public Patient getPatient(Ramq ramq) throws NonExistingPatientException {
		// Data validation
		if (ramq == null) {
			throw new IllegalArgumentException("getPatient error - ramq is null.");
		}

		// Make pattern to check for using the Ramq given
		int result;
		String pattern = ramq.getRamq();

		String array[] = new String[database.size()];

		for (int i = 0; i < database.size(); i++) {
			array[i] = database.get(i).getRamq().getRamq();
		}

		// Patient[] array = database.toArray(new Patient[database.size()]);

		result = ListUtilities.binarySearch(array, pattern);

		if (result == -1) {
			throw new NonExistingPatientException("getPatient error - Ramq does not match existing patients.");
		}

		// Return reference to specified patient
		return database.get(result);
	}

	/**
	 * Determines if a patient with the specified ramq id exists in the database
	 *
	 * @param ramq
	 *            The ramq id of the requested patient.
	 *
	 * @return true if the specified ramq exists; false if it does not
	 *
	 */
	@Override
	public boolean exists(Ramq ramq) {
		// Data validation
		if (ramq == null) {
			throw new IllegalArgumentException("exists error - ramq is null.");
		}

		String pattern = ramq.getRamq();
		String array[] = new String[database.size()];

		// Creating array of String ramq to search with
		for (int i = 0; i < database.size(); i++) {
			array[i] = database.get(i).getRamq().getRamq();
		}

		// Use ListUtilities binarySearch
		int result = ListUtilities.binarySearch(array, pattern);

		if (result == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns a list of the patients taking a given medication. The list will
	 * contain all patients associated with the given medication.
	 *
	 * @param medication
	 *            The medication that will be used to find the associated
	 *            patients.
	 *
	 * @return A list of patients who are prescribed the given medication. A
	 *         list of size zero will be returned if no patients are prescribed
	 *         the medication that is equal to the parameter value.
	 *
	 */
	@Override
	public List<Patient> getPatientsPrescribed(Medication medication) {
		// Data validation
		if (medication == null) {
			throw new IllegalArgumentException("getPatientsPrescribed error - medication is null.");
		}

		Scheme medScheme = medication.getScheme();
		String medNumber = medication.getNumber();
		Optional<Medication> patientMedication;
		Scheme patientScheme;
		String patientNumber;
		List<Patient> prescribed = new ArrayList<Patient>();

		// Search through database for medications, checks for match, adds match
		// to list
		for (int i = 0; i < database.size(); i++) {
			patientMedication = database.get(i).getMedication();
			if (patientMedication.isPresent()) {
				patientScheme = patientMedication.get().getScheme();
				patientNumber = patientMedication.get().getNumber();

				if (medScheme.equals(patientScheme) && medNumber.equals(patientNumber)) {
					prescribed.add(database.get(i));
				}
			}
		}

		return prescribed;
	}

	/**
	 * Modifies a patient’s details. Only the telephone number, medication and
	 * the conditions can be changed for an existing patient.
	 *
	 * @param modifiedPatient
	 *            The patient to be update.
	 * @throws NonExistingPatientException
	 *             The patient is not in database.
	 */
	@Override
	public void update(Patient modifiedPatient) throws NonExistingPatientException {
		// Data validation
		if (modifiedPatient == null) {
			throw new IllegalArgumentException("update error - modified patient is null.");
		}

		String modRamq = modifiedPatient.getRamq().getRamq();
		String array[] = new String[database.size()];

		// Creating array of String ramq to search with
		for (int i = 0; i < database.size(); i++) {
			array[i] = database.get(i).getRamq().getRamq();
		}

		// Use ListUtilities binarySearch
		int result = ListUtilities.binarySearch(array, modRamq);

		// Throw error if patient does not exist
		if (result == -1) {
			throw new NonExistingPatientException("Modified patient RAMQ does not match an existing patient.");
		}

		// Set telephone number, medication and condition from modified patient
		database.get(result).setTelephoneNumber(Optional.of(modifiedPatient.getTelephoneNumber()));

		if (modifiedPatient.getMedication().isPresent()) {
			database.get(result).setMedication(modifiedPatient.getMedication());
		} else {
			database.get(result).setMedication(Optional.empty());
		}

		database.get(result).setExistingConditions(Optional.of(modifiedPatient.getExistingConditions()));

	}

	/**
	 * Overrides toString. Returns number of patients in database, then lists
	 * each patient.
	 * 
	 * @return string The number of patients and each patient.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (database == null) {
			return "Database is null.";
		}

		sb.append("Number of patients in database: " + database.size() + "\n");

		// Iterate through list, append each element to string builder.
		for (Iterator<Patient> iterator = database.iterator(); iterator.hasNext();) {
			sb.append(iterator.next() + "\n");
		}

		String string = sb.toString();
		return string;
	}

}
