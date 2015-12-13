/**
 * Defines a VisitQueueDB type.
 */
package group1.clinic.data;

import group1.clinic.business.Priority;

import java.io.IOException;
import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.NonExistingVisitException;
import dw317.clinic.data.interfaces.VisitDAO;

/**
 * Class which implements various methods for a visit queue type of class.
 * 
 * 
 * @author Danieil Skrinikov
 * @version 11/03/2015
 *
 */
public class VisitQueueDB implements VisitDAO {

	private List<Queue<Visit>> database;
	private final ListPersistenceObject listPersistenceObject;
	private final PatientVisitFactory factory;

	/**
	 * One parameter constructor.
	 * 
	 * @param listPersistenceObject
	 *            the listPersistenceObject to be used
	 */
	public VisitQueueDB(ListPersistenceObject listPersistenceObject) {
		if (listPersistenceObject == null)
			throw new IllegalArgumentException("VisitQueueDB - ListPersistenceObject is null");

		if (!(listPersistenceObject instanceof ListPersistenceObject))
			throw new IllegalArgumentException("VisitQueueDB - Wrong ListPersistenceObject parameter type");

		this.listPersistenceObject = listPersistenceObject;
		this.factory = DefaultPatientVisitFactory.DEFAULT;
		database = listPersistenceObject.getVisitDatabase();

	}

	/**
	 * Two parameter constructor.
	 * 
	 * @param listPersistenceObject
	 *            the listPersistenceObject to be used
	 * @param factory
	 *            the factor to be used
	 */
	public VisitQueueDB(ListPersistenceObject listPersistenceObject, PatientVisitFactory factory) {

		if (listPersistenceObject == null)
			throw new IllegalArgumentException("VisitQueueDB - ListPersistenceObject is null");

		if (!(listPersistenceObject instanceof ListPersistenceObject))
			throw new IllegalArgumentException("VisitQueueDB - Wrong ListPersistenceObject parameter type");

		if (factory == null)
			throw new IllegalArgumentException("VisitQueueDB - PatientVisitFactory is null");

		if (!(factory instanceof PatientVisitFactory))
			throw new IllegalArgumentException("VisitQueueDB - Wrong PatientVisitFactory parameter type");

		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		database = listPersistenceObject.getVisitDatabase();
	}

	/**
	 * Adds a referenced copy of a visit to the end of the list
	 * 
	 * @param aVisit
	 *            reference of the object to add.
	 */
	@Override
	public void add(Visit aVisit) {
		if (aVisit == null)
			throw new IllegalArgumentException("VisitQueueDB.add() - Parameter can not be null");
		if (!(aVisit instanceof Visit))
			throw new IllegalArgumentException("VisitQueueDB.add() - Parameter must be a visit");

		database.get(aVisit.getPriority().getCode()).add(aVisit);

	}

	/**
	 * Saves the text file and then disconnects from the database.
	 * 
	 */
	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.saveVisitDatabase(database);

		database = null;

	}

	/**
	 * Returns the first patient with the given priority. If there is no client
	 * with this priority returns null.
	 * 
	 * @param priority
	 *            Used to find first patient with its value.
	 * @return Optional<Visit> if there is a visit returns it's value else
	 *         returns null.
	 */
	@Override
	public Optional<Visit> getNextVisit(Priority priority) {

		if (priority == null)
			throw new IllegalArgumentException("VisitQueueDB.getNextVisit() - Priority is null");

		if (!(priority instanceof Priority))
			throw new IllegalArgumentException("VisitQueueDB.getNextVisit() - parameter is not of Priority type");

		try {

			return Optional.ofNullable(database.get(priority.getCode()).element());

		} catch (NoSuchElementException nsee) {
			return null;
		}
	}

	/**
	 * Removes the first person with the priority from the list.
	 * 
	 * @param Priority
	 *            looks for the first occurrence of this priority.
	 * 
	 */
	@Override
	public void remove(Priority priority) {

		if (priority == null)
			throw new IllegalArgumentException("VisitQueueDB.remove() - Priority is null");

		if (!(priority instanceof Priority))
			throw new IllegalArgumentException("VisitQueueDB.remove() - parameter is not of Priority type");

		try {
			database.get(priority.getCode()).remove();

		} catch (NoSuchElementException nsee) {
			throw new IllegalArgumentException("VisitQueueDB.remove() - No visits with such priority.");

		}

	}

	/**
	 * Returns the amount of patients with the given priority.
	 * 
	 * @param priority
	 *            is the priority list which will be checked.
	 * @return count which represents the amount of people with this priority.
	 */
	@Override
	public int size(Priority priority) {

		if (priority == null)
			throw new IllegalArgumentException("VisitQueueDB.size - parameter must not be null.");
		if (!(priority instanceof Priority))
			throw new IllegalArgumentException("VisitQueueDB.size - parameter must not be null.");

		try {
			return database.get(priority.getCode()).size();
		} catch (NoSuchElementException nsee) {
			return 0;

		}

	}

	/**
	 * Updates the first occurrence of the priority with a new priority and then
	 * puts it at the end of the priority list.
	 * 
	 * @param oldPriority
	 *            priority which is used to get the first patient with this
	 *            priority.
	 * @param newPriority
	 *            place where to put the patient with the new priority.
	 */
	@Override
	public void update(Priority oldPriority, Priority newPriority) throws NonExistingVisitException {

		if (oldPriority == null || newPriority == null)
			throw new IllegalArgumentException("VisitQueueDB.update() - One of the priorities is null.");
		if (!(oldPriority instanceof Priority && newPriority instanceof Priority))
			throw new IllegalArgumentException(
					"VisitQueueDB.update() - One of the priorities is not Of Priority type.");

		Visit temp;

		try {

			temp = database.get(oldPriority.getCode()).poll();
			if(temp == null)
				throw new NonExistingVisitException("VisitQueueDB - No patient found with such priority.");
			temp.setPriority(newPriority);
			database.get(newPriority.getCode()).add(temp);

		} catch (NoSuchElementException nsee) {
			throw new NonExistingVisitException("VisitQueueDB - No patient found with such priority.");
		}

	}

	/**
	 * Overriden to string method. Returns a String which represents the
	 * priority with number of patients with this priority and then returns the
	 * names of the people.
	 * 
	 */
	@Override
	public String toString() {

		String returnString = "";

		for (int i = 0; i < database.size(); i++) {
			try {
				returnString += "Number of priority " + database.get(i).element().getPriority().getCode()
						+ " visits in databse: ";

				returnString += database.get(i).size() + "\n";
			} catch (NoSuchElementException nsee) {
				// This code is void to not show that there is 0 priority X
				// visits in the database.

			}

			// We know that this has at least one occurence in the array.
			Object[] obj = database.get(i).toArray();
			for (int j = 0; j < obj.length; j++)
				returnString += obj[j] + "\n";

		}

		return returnString;
	}

}
