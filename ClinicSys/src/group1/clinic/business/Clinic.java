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

/** Acts as an Interface between the user and the code part. 
 * @author Danieil Skrinikov
 * @version 11/20/2015
 *
 */
public class Clinic  implements PatientVisitManager{
	
	private final PatientDAO patientConnection;
	private final VisitDAO visitConnection;
	private final ClinicFactory factory;
	private static final long serialVersionUID = 42031768871L;
	
	private boolean isConnected = true;
	
	/** Constructor. Instantiates the private parameters for the object.
	 * 
	 * @param patientConnection 	existing connections to Patient database.
	 * @param visitConnection 		existing connection to Visit database.
	 * @param factory				connection to existing factory type object.
	 *
	 */
	public Clinic(PatientDAO patientConnection, VisitDAO visitConnection,
			ClinicFactory factory){
		
		if(patientConnection == null || visitConnection == null){
			
			throw new IllegalArgumentException("Clinic - One of the given parameters is null");
			
		}
		
		this.patientConnection = patientConnection;
		this.visitConnection = visitConnection;
		
		if(factory == null)
			this.factory = DawsonClinicFactory.DAWSON_CLINIC;
		else
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
		
		isConnected = false;
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
		
		if(!isConnected)
			throw new IllegalArgumentException("Clinic - Connection to the database has been closed.");
		
		Visit aVisit = new ClinicVisit(patient);
		
		aVisit.setComplaint(Optional.of(complaint));
		
		visitConnection.add(aVisit);
		
	}
	
	/** Looks through the patientConnection database to find a patient which has a matching ramq number.
	 * 
	 * Throws NonExistingPatientException if the patient can not be found in the database.
	 * 
	 * @param ramq String representation of the ramq.
	 * 
	 */
	@Override
	public Patient findPatient(String ramq) throws NonExistingPatientException {
		
		if(!isConnected)
			throw new IllegalArgumentException("Clinic - Connection to the database has been closed.");
		
		validateRamq(ramq);
		Ramq ramqPatient = new Ramq(ramq);
		
		return patientConnection.getPatient(ramqPatient);
		
	}
	
	/** Returns a list of patients which use the given medications.
	 * 
	 * @param meds Medication that will be used to search for patients.
	 * @return List<Patient> List of Patients which all use the given medication.
	 */
	@Override
	public List<Patient> findPatientsPrescribed(Medication meds) {
		
		if(!isConnected)
			throw new IllegalArgumentException("Clinic - Connection to the database has been closed.");
		
		if(meds == null)
			throw new IllegalArgumentException("Clinic findPatientsPrescribed() - Given parameter is null");
		
		return patientConnection.getPatientsPrescribed(meds);
	}
	
	/** Returns the next Visit with a non assigned priority.
	 *
	 * 	@return Optional visit that will be next for triage.
	 */
	@Override
	public Optional<Visit> nextForTriage() {
		
		if(!isConnected)
			throw new IllegalArgumentException("Clinic - Connection to the database has been closed.");
		
		return visitConnection.getNextVisit(Priority.NOTASSIGNED);
	}
	
	/** Instantiates the DawsonClinicPriorityPolicy.
	 * 	Using the object returns next patient for examination.
	 * 
	 * @return Optional<Visit> Represents the next Visit that will be examined.
	 * 
	 */
	@Override
	public Optional<Visit> nextForExamination() {
		
		if(!isConnected)
			throw new IllegalArgumentException("Clinic - Connection to the database has been closed.");
		
		DawsonClinicPriorityPolicy policy = new DawsonClinicPriorityPolicy(visitConnection);
		return policy.getNextVisit();
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
		
		if(!isConnected)
			throw new IllegalArgumentException("Clinic - Connection to the database has been closed.");
		
		if(firstName == null || firstName.length() == 0)
			throw new IllegalArgumentException("Clinic.registerNewPatient() - FirstName is null or empty.");
		if(lastName == null || lastName.length() == 0)
			throw new IllegalArgumentException("Clinic.registerNewPatient() - LastName is null or empty.");
		if(ramq == null || ramq.length() != 12)
			throw new IllegalArgumentException("Clinic.registerNewPatient() - Ramq is null or has invalid amount of characters.");
		
		Patient patient = new ClinicPatient(firstName,lastName,ramq);
		patient.setExistingConditions(Optional.ofNullable(conditions));
		patient.setMedication(Optional.ofNullable(meds));
		patient.setTelephoneNumber(Optional.ofNullable(telephone));
		
		patientConnection.add(patient);
		
	}
	
	/** Checks if the ramq is null, if the ramq contains 12 characters. Then looks at the first 4 characters of the ramq to look if they are
	 *  alphabet characters. Then looks if the last 8 characters are digits.
	 *  
	 *  Throws IllegalArgumentException 
	 *  						If any of the conditions are not met.
	 * 
	 * @param ramq String representation of a ramq which will be checked.
	 */
	private void validateRamq(String ramq){
		
		if(ramq == null || ramq.length() != 12)
			throw new IllegalArgumentException("Clinic.findPatient() - Ramq string is either null or does not contain 12 characters.");
		
		String letters = ramq.substring(0,4);
		String numbers = ramq.substring(4);
		
		for(int i = 0 ; i < 4 ; i++)
			if(!(Character.isAlphabetic(letters.charAt(i))))
				throw new IllegalArgumentException("Clinic.findPatient() - Ramq contains digits in the first 4 characters.");
		
		for(int i = 0 ; i < 8 ; i++ )
			if(!(Character.isDigit(numbers.charAt(i))))
				throw new IllegalArgumentException("Clinic.findPatient() - Ramq contains non digits in last 8 characters.");
	}

}
