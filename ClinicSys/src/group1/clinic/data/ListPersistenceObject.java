/**
 * Defines a ListPersistenceObject type.
 */
package group1.clinic.data;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

/**
 * Provides a List Persistence Object interface.
 * 
 * @author	Uen Yi Hung (Cindy)
 * @version 28/10/2015
 */
public interface ListPersistenceObject {
	/**
	 * Gets the patient database.
	 * 
	 * @return a reference to an array containing the loaded patients.
	 */
	List<Patient> getPatientDatabase();

	/**
	 * Gets the visit database.
	 * 
	 * @return a reference to an array containing the loaded visits with the
	 * given priority.
	 */
	List<Queue<Visit>> getVisitDatabase();
	
	/**
	 * Saves the list of patients to the text file.
	 * 
	 * @param patients a list (collection) of patients.
	 * @throws IOException
	 */
	void savePatientDatabase(List<Patient> patients) throws IOException;

	/**
	 * Saves the list of visit to the text file.
	 * 
	 * @param visits a list (collection) of visits.
	 * @throws IOException
	 */
	void saveVisitDatabase(List<Queue<Visit>> visits) throws IOException;
}
