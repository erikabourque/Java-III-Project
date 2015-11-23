/**
 * 
 */
package group1.clinic.data;

import group1.util.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.data.SequentialTextFileList;

/**
 * @author Katherine Richer 1434389
 *
 */
public class ObjectSerializedList implements ListPersistenceObject {

	private String patientFilename;
	private String visitFilename;

	public ObjectSerializedList(String patientFilename, String visitFilename) {
		this.patientFilename = patientFilename;
		this.visitFilename = visitFilename;

	}

	public void convertSequentialFilesToSerialized(String sequentialPatients, String sequentialVisits)
			throws IOException {
		SequentialTextFileList textFile = new SequentialTextFileList(sequentialPatients, sequentialVisits);
		// patients
		List<Patient> patients = textFile.getPatientDatabase();
		Utilities.serializeObject(patients, patientFilename);
		List<Queue<Visit>> visits = textFile.getVisitDatabase();
		Utilities.serializeObject(visits, visitFilename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see group1.clinic.data.ListPersistenceObject#getPatientDatabase()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getPatientDatabase() {
		List<Patient> patients;
		try {
			patients = (List<Patient>) Utilities.deserializeObject(patientFilename);
		} catch (IOException ioe) {
			return new ArrayList<Patient>();
		} catch (ClassNotFoundException cnfe) {
			return new ArrayList<Patient>();
		}
		return patients;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see group1.clinic.data.ListPersistenceObject#getVisitDatabase()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Queue<Visit>> getVisitDatabase() {

		List<Queue<Visit>> visits;
		try {
			// Get visits
			visits = (List<Queue<Visit>>) Utilities.deserializeObject(visitFilename);
		} catch (IOException ioe) {
			return new ArrayList<Queue<Visit>>(0); // return empty arraylist
		} catch (ClassNotFoundException cnfe) {
			return new ArrayList<Queue<Visit>>(0); // return empty arraylist
		}
		return visits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * group1.clinic.data.ListPersistenceObject#savePatientDatabase(java.util.
	 * List)
	 */
	@Override
	public void savePatientDatabase(List<Patient> patients) throws IOException {
		Utilities.serializeObject(patients, patientFilename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * group1.clinic.data.ListPersistenceObject#saveVisitDatabase(java.util.
	 * List)
	 */
	@Override
	public void saveVisitDatabase(List<Queue<Visit>> visits) throws IOException {
		Utilities.serializeObject(visits, visitFilename);
	}

}
