/**
 * Represents the patient database.
 */
package group1.clinic.data;

import java.io.IOException;
import java.util.List;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.lib.medication.Medication;
import group1.clinic.business.Ramq;

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
		this.listPersistenceObject = listPersistenceObject;
		factory = DefaultPatientVisitFactory.DEFAULT;
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
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
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

	/* (non-Javadoc)
	 * @see dw317.clinic.data.interfaces.PatientDAO#exists(group1.clinic.business.Ramq)
	 */
	@Override
	public boolean exists(Ramq ramq) {
		// TODO Auto-generated method stub
		return false;
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

}
