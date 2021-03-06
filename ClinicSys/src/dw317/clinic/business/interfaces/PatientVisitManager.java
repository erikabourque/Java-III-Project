package dw317.clinic.business.interfaces;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.NonExistingVisitException;
import dw317.lib.medication.Medication;
import group1.clinic.business.Priority;

/**
 * Interface for a Patient Visit Manager.
 * 
 * @author	Erika Bourque
 * @author  Danieil Skrinikov
 * @version	09/12/2015
 */
public interface PatientVisitManager extends Serializable {
	/**
	 * Saves all data and closes the clinic.
	 *
	 * @throws IOException
	 *             If there is a problem closing the clinic files.
	 */
	void closeClinic() throws IOException;

	/**
	 * Creates a visit for a patient.
	 *
	 * @param patient
	 *            The patient for whom the visit is created.
	 */
	void createVisit(Patient patient, String complaint);

	/**
	 * Finds and returns a patient record.
	 *
	 * @param ramq
	 *            The patientís ramq id.
	 * @return Patient object with the ramq id
	 *
	 * @throws NonExistingPatientException
	 *             If the patient with the ramq does not exist.
	 */
	Patient findPatient(String ramq) throws NonExistingPatientException;

	/**
	 * Finds and returns a list of all patients prescribed the given medication.
	 *
	 * @param meds
	 *            The medication that will be used to find the associated
	 *            patients.
	 * @return A list of patients who are taking the given medication. A list of
	 *         size zero will be returned if no patients are taking a medication
	 *         that matches the parameter value.
	 *
	 */
	List<Patient> findPatientsPrescribed(Medication meds);

	/**
	 * Get next visit for triage.
	 * 
	 * @return Optional<Visit> object with Priority NOTASSIGNED
	 *
	 */
	Optional<Visit> nextForTriage();

	/**
	 * Get next visit for examination.
	 * 
	 * @return Optional<Visit> object with next visit to be examined.
	 *
	 */
	Optional<Visit> nextForExamination();

	/**
	 * Registers a new patient.
	 *
	 * @param firstName
	 *            The patientís first name.
	 * @param lastName
	 *            The patientís last name.
	 * @param ramq
	 *            The patientís ramq id.
	 * @param telephone
	 *            The patientís telephone.
	 * @param meds
	 *            The patientís medication.
	 * @param conditions
	 *            The patientís conditions.
	 *
	 * @throws DuplicatePatientException
	 *             If the patient with the ramq already exists.
	 */
	void registerNewPatient(String firstName, String lastName, String ramq, String telephone, Medication meds,
			String conditions) throws DuplicatePatientException;
	
	/**
	*	Updates the	priority of	the	first visit	in	the	triage	queue to
	*	a new priority.
	*
	*	@param newPriority
	*					The	new	priority after triage	
	*/
	void	changeTriageVisitPriority(Priority	newPriority)
	throws	NonExistingVisitException;
}