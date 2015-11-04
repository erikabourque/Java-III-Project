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
 * Represents the patient database as an internal
 * list of patients.
 * 
 * @author	Erika Bourque
 * @version	23/10/2015
 */
public class PatientListDB implements PatientDAO {

	private List<Patient> database;
	private final ListPersistenceObject listPersistenceObject;
	private final PatientVisitFactory factory;
	
	/**
	 * One param constructor.  Assigns factory to default.
	 * 
	 * @param listPersistenceObject
	 * 						The ListPersistenceObject to be assigned.
	 */
	public PatientListDB (ListPersistenceObject listPersistenceObject){
		// Data validation
		if(listPersistenceObject == null){
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
	 * 						The ListPersistenceObject to be assigned.
	 * @param factory
	 * 						The PatientVisitFactory to be assigned.
	 */
	public PatientListDB (ListPersistenceObject listPersistenceObject,
	PatientVisitFactory factory){
		// Data validation
		if(listPersistenceObject == null){
			throw new IllegalArgumentException("PatientVisitDB error - ListPersistenceObject is null");
		}
		
		if(factory == null){
			throw new IllegalArgumentException("PatientVisitDB error - PatientVisitFactory is null");
		}
		
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		database = listPersistenceObject.getPatientDatabase();
	}
	
	@Override
	public void add(Patient aPatient) throws DuplicatePatientException {
		// Make sure aPatient is not null
		if(aPatient == null){
			throw new IllegalArgumentException("add error - patient is null");
		}
		
		// Create a copy of aPatient
		Patient copy = new ClinicPatient(aPatient.getName().getFirstName(), 
				aPatient.getName().getLastName(), aPatient.getRamq().getRamq());
		copy.setMedication(aPatient.getMedication());
		copy.setExistingConditions(Optional.of(aPatient.getExistingConditions()));
		copy.setTelephoneNumber(Optional.of(aPatient.getTelephoneNumber()));
		
		int location = 0;
		int comparisonResult;
		boolean found = false;
		
		// Check for where to place 
		while ((location < database.size()) && (!found))
		{
			// Positive means copy has not been reached yet in alphabet
			// Negative means copy has been past in alphabet
			comparisonResult = copy.compareTo(database.get(location));
			
			if (comparisonResult < 0){
				found = true;
				break;
			}
			
			// Checks if patient exists already in database
			if (comparisonResult == 0){
				throw new DuplicatePatientException("Patient already exists in database: " + copy);
			}
			
			location++;
		}
		
		database.add(location, copy);
	}

	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.savePatientDatabase(database);
		
		database = null;
	}


	@Override
	public Patient getPatient(Ramq ramq) throws NonExistingPatientException {
		// Data validation
		if (ramq == null){
			throw new IllegalArgumentException("getPatient error - ramq is null.");
		}
		
		// Make pattern to check for using the Ramq given
		int result;
		String pattern = ramq.getRamq();
		
		String array[] = new String[database.size()];
		
		for (int i = 0; i < database.size(); i++)
		{
			array[i] = database.get(i).getRamq().getRamq();
		}
		
		// Patient[] array = database.toArray(new Patient[database.size()]); 
		
		result = ListUtilities.binarySearch(array, pattern);
		
		if (result == -1){
			throw new NonExistingPatientException("getPatient error - Ramq does not match existing patients.");
		}
		
		// Return reference to specified patient
		return database.get(result);
	}


	@Override
	public boolean exists(Ramq ramq) {
		// Data validation
		if (ramq == null){
			throw new IllegalArgumentException("exists error - ramq is null.");
		}
		
		// Make pattern to check for using the Ramq given
		String pattern = ramq.getRamq();
		
		String array[] = new String[database.size()];
		
		for (int i = 0; i < database.size(); i++)
		{
			array[i] = database.get(i).getRamq().getRamq();
		}
		
		// Patient[] array = database.toArray(new Patient[database.size()]); 
		
		int result = ListUtilities.binarySearch(array, pattern);
		
		if (result != -1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<Patient> getPatientsPrescribed(Medication medication) {
		// Data validation
		if (medication == null){
			throw new IllegalArgumentException("getPatientsPrescribed error - medication is null.");
		}
		
		Scheme medScheme = medication.getScheme();
		String medNumber = medication.getNumber();
		Optional<Medication> patientMedication;
		Scheme patientScheme;
		String patientNumber;
		
		List<Patient> prescribed = new ArrayList<Patient>();
		
		for (int i = 0; i < database.size(); i++)
		{
			patientMedication = database.get(i).getMedication();
			if (patientMedication.isPresent()){
				patientScheme = patientMedication.get().getScheme();
				patientNumber = patientMedication.get().getNumber();
				
				if (medScheme.equals(patientScheme) && medNumber.equals(patientNumber)){
					prescribed.add(database.get(i));
				}
			}
		}
		
		return prescribed;
	}

	/* (non-Javadoc)
	 * @see dw317.clinic.data.interfaces.PatientDAO#update(dw317.clinic.business.interfaces.Patient)
	 */
	@Override
	public void update(Patient modifiedPatient) throws NonExistingPatientException {
		// TODO Auto-generated method stub

	}
	/**
	 * Overrides toString.  Returns number of patients in database,
	 * then lists each patient.
	 * 
	 * @return string	The number of patients and each patient.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		if (database == null){
			return "Database is null.";
		}
		
		sb.append("Number of patients in database: " + database.size() + "\n");
		
		// Iterate through list, append each element to string builder.
		for (Iterator<Patient> iterator = database.iterator(); iterator.hasNext(); )
		{
			sb.append(iterator.next() + "\n");
		}
		
		String string = sb.toString();
		return string;
	}

}
