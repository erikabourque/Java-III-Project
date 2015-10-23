package dw317.clinic.data.interfaces;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.lib.medication.Medication;
import group1.clinic.business.Ramq;
import java.io.IOException;
import java.util.List;


/**
 * Interface for Patient Data Access Object.
 * 
 * @author Katherine Richer
 * @version 22/10/2015
 */
public interface PatientDAO {
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
	void add(Patient aPatient) throws DuplicatePatientException;

	/**
	 * Saves the list of patients and disconnects from the database.
	 *
	 * @throws IOException
	 *             Problems saving or disconnecting from database.
	 */
	void disconnect() throws IOException;

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
	Patient getPatient(Ramq ramq) throws NonExistingPatientException;

	/**
	 * Determines if a patient with the specified ramq id exists in the database
	 *
	 * @param ramq
	 *            The ramq id of the requested patient.
	 *
	 * @return true if the specified ramq exists; false if it does not
	 *
	 */
	boolean exists(Ramq ramq);

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
	List<Patient> getPatientsPrescribed(Medication medication);

	/**
	 * Modifies a patient’s details. Only the telephone number, medication and
	 * the conditions can be changed for an existing patient.
	 *
	 * @param modifiedPatient
	 *            The patient to be update.
	 * @throws NonExistingPatientException
	 *             The patient is not in database.
	 */
	void update(Patient modifiedPatient) throws NonExistingPatientException;
}
