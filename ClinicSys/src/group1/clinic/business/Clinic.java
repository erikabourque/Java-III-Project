/**
 * 
 */
package group1.clinic.business;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dw317.clinic.ClinicFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitManager;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.clinic.data.interfaces.VisitDAO;
import dw317.lib.medication.Medication;
import group1.dawsonclinic.*;

/** Acts as an interface between the user and the code part. 
 * @author Danieil Skrinikov
 * @version 11/20/2015
 *
 */
public class Clinic  implements PatientVisitManager{
	
	private final PatientDAO patientConnection;
	private final VisitDAO visitConnection;
	private final ClinicFactory factory;
	
	/** Constructor. Instantiates the private parameters for the object.
	 * 
	 * @param patientConnection 	existing connections to Patient database.
	 * @param visitConnection 		existing connection to Visit database.
	 * @param factory				connection to existing factory type object.
	 *
	 */
	public Clinic(PatientDAO patientConnection, VisitDAO visitConnection,
			ClinicFactory factory){
		
		if(patientConnection == null || visitConnection == null || factory == null){
			
			throw new IllegalArgumentException("Clinic - One of the given parameters is null");
			
		}
		
		this.patientConnection = patientConnection;
		this.visitConnection = visitConnection;
		this.factory = factory;
		
	}
	
	/** Closes all connections of the clinic.
	 * 
	 * Throws IOException if there is a problem when saving any of the databases.
	 * 
	 */
	@Override
	public void closeClinic() throws IOException {
		patientConnection.disconnect();
		visitConnection.disconnect();
		
		
	}
	
	/** Creates a visit for a provided patient with a given complaint. Afterwards adds the visit into the database.
	 * 	
	 * 	@param patient		Existing Patient type order for which a visit needs to be created.
	 * 	@param complaint	complaint that the patient has.
	 */
	@Override
	public void createVisit(Patient patient, String complaint) {
		
		if(patient == null)
			throw new IllegalArgumentException("Clinic.createVisit() - Patient type parameter is null.");
		
		Visit aVisit = new ClinicVisit(patient);
		
		aVisit.setComplaint(Optional.of(complaint));
		
		visitConnection.add(aVisit);
		
	}
	
	/**
	 * 
	 */
	@Override
	public Patient findPatient(String ramq) throws NonExistingPatientException {
		
		//Validate ramq todo
		Ramq ramqPatient = new Ramq(ramq);
		
		return patientConnection.getPatient(ramqPatient);
		
	}

	@Override
	public List<Patient> findPatientsPrescribed(Medication meds) {
		
		//Validate meds
		return patientConnection.getPatientsPrescribed(meds);
	}
	
	/** First creates a DawsonClinicPriorityPolicy object. This object has implementation of a method that returns 
	 * 	the next visit in line.
	 *
	 * 	@return Optional visit that will be next for triage.
	 */
	@Override
	public Optional<Visit> nextForTriage() {
		
		DawsonClinicPriorityPolicy policy = new DawsonClinicPriorityPolicy(visitConnection);
		return policy.getNextVisit();
	}
	
	
	@Override
	public Optional<Visit> nextForExamination() {
		DawsonClinicPriorityPolicy policy = new DawsonClinicPriorityPolicy(visitConnection);
		return policy.getNextForExamination();
	}
	/** First creates a new patient. Then sets additional information about the patient. When all this is done adds 
	 *  the patient to the patientConnection database.
	 *  
	 *  @param firstName 	First name of the patient
	 *  @param lastName		Last name of the patient.
	 *  @param ramq			Ramq number of the patient.
	 *  @param telephone	Telephone of the patient.
	 *  @param meds			Medication that the patient is taking.
	 *  @param conditions	Conditions that the patient exhibits.
	 * 
	 */
	@Override
	public void registerNewPatient(String firstName, String lastName,
			String ramq, String telephone, Medication meds, String conditions)
			throws DuplicatePatientException {
		
		if(firstName == null || firstName.length() == 0)
			throw new IllegalArgumentException("Clinic.registerNewPatient() - FirstName is null or empty.");
		if(lastName == null || lastName.length() == 0)
			throw new IllegalArgumentException("Clinic.registerNewPatient() - LastName is null or empty.");
		if(ramq == null || ramq.length() != 12)
			throw new IllegalArgumentException("Clinic.registerNewPatient() - Ramq is null or has invalid amount of characters.");
		
		Patient patient = new ClinicPatient(firstName,lastName,ramq);
		patient.setExistingConditions(Optional.of(conditions));
		patient.setMedication(Optional.of(meds));
		patient.setTelephoneNumber(Optional.of(telephone));
		
		patientConnection.add(patient);
		
	}

}
