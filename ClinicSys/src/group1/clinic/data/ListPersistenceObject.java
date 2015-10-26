<<<<<<< Upstream, based on branch 'master' of https://github.com/erikabourque/Java-III-Project.git
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
=======
package group1.clinic.data;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

>>>>>>> 26631f6 ListPersistenceObject Interface
public interface ListPersistenceObject {
	List<Patient> getPatientDatabase();

	Queue<Visit>[] getVisitDatabase();

	void savePatientDatabase(List<Patient> patients) throws IOException;

	void saveVisitDatabase(Queue<Visit>[] visits) throws IOException;
}
