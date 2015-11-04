/**
 * Represents the patient database.
 */
package group1.clinic.data;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.lib.medication.Medication;
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
	
	/* (non-Javadoc)
	 * @see dw317.clinic.data.interfaces.PatientDAO#add(dw317.clinic.business.interfaces.Patient)
	 */
	@Override
	public void add(Patient aPatient) throws DuplicatePatientException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dw317.clinic.data.interfaces.PatientDAO#disconnect()
	 */
	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dw317.clinic.data.interfaces.PatientDAO#getPatient(group1.clinic.business.Ramq)
	 */
	@Override
	public Patient getPatient(Ramq ramq) throws NonExistingPatientException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean exists(Ramq ramq) {
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

	/* (non-Javadoc)
	 * @see dw317.clinic.data.interfaces.PatientDAO#getPatientsPrescribed(dw317.lib.medication.Medication)
	 */
	@Override
	public List<Patient> getPatientsPrescribed(Medication medication) {
		// TODO Auto-generated method stub
		return null;
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
