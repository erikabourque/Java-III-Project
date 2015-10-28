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
 * @version 25/10/2015
 */
public interface ListPersistenceObject {
	List<Patient> getPatientDatabase();

	Queue<Visit>[] getVisitDatabase();

	void savePatientDatabase(List<Patient> patients) throws IOException;

	void saveVisitDatabase(Queue<Visit>[] visits) throws IOException;
}
