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
import static java.lang.System.out;

/**
 * Represents a serialized list as an object.
 * 
 * @author Katherine Richer 1434389
 * @version 11-23-2015
 * 
 */
public class ObjectSerializedList implements ListPersistenceObject {

	private String patientFilename;
	private String visitFilename;

	/**
	 * Two parameter Constructor.
	 * 
	 * @param patientFileName
	 *            the patient file to be used.
	 * @param VisitFileName
	 *            the visit file to be used.
	 */
	public ObjectSerializedList(String patientFilename, String visitFilename) {
		this.patientFilename = patientFilename;
		this.visitFilename = visitFilename;

	}

	/**
	 * Converts a sequential file list to serialized objects.
	 * 
	 * @param sequentialPatients
	 *            the sequential list of patients.
	 * @param sequentialVisits
	 *            the sequential list of visits.
	 * @throws IOException
	 */
	public void convertSequentialFilesToSerialized(String sequentialPatients, String sequentialVisits)
			throws IOException {
		SequentialTextFileList textFile = new SequentialTextFileList(sequentialPatients, sequentialVisits);
		// patients
		List<Patient> patients = textFile.getPatientDatabase();
		Utilities.serializeObject(patients, patientFilename);
		List<Queue<Visit>> visits = textFile.getVisitDatabase();
		Utilities.serializeObject(visits, visitFilename);
	}

	/**
	 * Returns the patient database.
	 * 
	 * @return the patient database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getPatientDatabase() {
		List<Patient> patients;
		try {
			patients = (List<Patient>) Utilities.deserializeObject(patientFilename);
		} catch (IOException ioe) {
			out.println("Error, object is not serializable, please verify the data." + ioe.getMessage());
			return new ArrayList<Patient>();
		} catch (ClassNotFoundException cnfe) {
			out.println("Error, class not found, please verify your data." + cnfe.getMessage());
			return new ArrayList<Patient>();
		}
		return patients;
	}

	/**
	 * Returns the visit database.
	 * 
	 * @return the visit database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Queue<Visit>> getVisitDatabase() {

		List<Queue<Visit>> visits;
		try {
			// Get visits
			visits = (List<Queue<Visit>>) Utilities.deserializeObject(visitFilename);
		} catch (IOException ioe) {
			out.println("Error, object is not serializable, please verify the data." + ioe.getMessage());
			return new ArrayList<Queue<Visit>>(0); // return empty arraylist
		} catch (ClassNotFoundException cnfe) {
			out.println("Error, class not found, please verify the data." + cnfe.getMessage());
			return new ArrayList<Queue<Visit>>(0); // return empty arraylist
		}
		return visits;
	}

	/**
	 * Saves the given list of patients to the serialized object.
	 * 
	 * @param patients
	 *            the patient list to be serialzed
	 * @throws IOException
	 */
	@Override
	public void savePatientDatabase(List<Patient> patients) throws IOException {
		Utilities.serializeObject(patients, patientFilename);
	}

	/**
	 * Saves the given list of visits to the serialized object.
	 * 
	 * @param visits
	 *            the visits list to be serialized
	 * @throws IOException
	 */
	@Override
	public void saveVisitDatabase(List<Queue<Visit>> visits) throws IOException {
		Utilities.serializeObject(visits, visitFilename);
	}

}
