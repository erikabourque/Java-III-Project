package dw317.clinic.data.interfaces;

import group1.clinic.business.Priority;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.NonExistingVisitException;
import java.util.Optional;
import java.io.IOException;

/**
 * Interface for Visit Data Access Object.
 * 
 * @author	Katherine Richer
 * @version	22/10/2015
 */
public interface VisitDAO {
	/**
	 * Adds a visit to the database. The visit is added based on the priority
	 * code behind other visits with the same priority code.
	 *
	 * @param aVisit
	 *            The visit to add to the database.
	 */
	void add(Visit aVisit);

	/**
	 * Saves the queues of visits and disconnects from the database.
	 *
	 * @throws IOException
	 *             Problems saving or disconnecting from database.
	 */
	void disconnect() throws IOException;

	/**
	 * Returns the next visit for a given priority.
	 *
	 * @param priority
	 *            The priority for the visit.
	 *
	 * @return The next visit for a given priority or null.
	 */
	Optional<Visit> getNextVisit(Priority priority);

	/**
	 * Removes the next visit for a given priority
	 *
	 * @param priority
	 *            The priority for the visit.
	 *
	 */
	void remove(Priority priority);

	/**
	 * Returns the count of visits for a given priority
	 *
	 * @param priority
	 *            The priority for the visits.
	 *
	 */
	int size(Priority priority);

	/**
	 * Modifies the next visit for a given priority to a new priority.
	 *
	 * @param oldPriority
	 *            The next visit with this priority will be updated.
	 * @param newPriority
	 *            The new priority to assign.
	 * @throws NonExistingVisitException
	 *             * No visit with the old priority found.
	 */
	void update(Priority oldPriority, Priority newPriority) throws NonExistingVisitException;

}
