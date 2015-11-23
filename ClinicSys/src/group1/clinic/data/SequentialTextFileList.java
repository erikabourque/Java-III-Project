/**
 * Defines a SequentialTextFileList type.
 */
package group1.clinic.data;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.Priority;
import group1.util.ListUtilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Provides a Sequential Text File List
 * 
 * @author	Uen Yi Hung (Cindy)
 * @version 20/11/2015
 */
public class SequentialTextFileList implements ListPersistenceObject {
	//variables
	private final String patientFilename;
	private final String visitFilename;

	/**
	 * A 2 parameters constructor.
	 * 
	 * @param patientFilename
	 * @param visitFilename
	 */
	public SequentialTextFileList(String patientFilename, String visitFilename) {
		this.patientFilename = patientFilename;
		this.visitFilename = visitFilename;
	}

	/**
	 * Returns a reference to an array containing the loaded patients. If an
	 * IOException occurs an ArrayList of size zero will be returned.
	 * 
	 * @return a list (collection) of patients.
	 */
	@Override
	public List<Patient> getPatientDatabase() {
		Patient[] patients;
		try {
			patients = ClinicFileLoader.getPatientListFromSequentialFile(patientFilename);
		} catch (IOException e) {
			return new ArrayList<Patient>();
		}
		// Create the adapter object that will be used as an argument to
		// instantiate an ArrayList instance.
		List<Patient> listAdapter = java.util.Arrays.asList(patients);
		// return a reference to an ArrayList instance.
		return new ArrayList<Patient>(listAdapter);
	}

	/**
	 * Returns a reference to an array containing the loaded visits with the
	 * given priority. If an exception occurs, an array of size zero will be
	 * returned.
	 * 
	 * @return a list (collection) of visits.
	 */
	@Override
	public List<Queue<Visit>> getVisitDatabase() {
		Visit[] visits;
		Patient[] patients;
		List<Queue<Visit>> db = new ArrayList<Queue<Visit>>(Priority.values().length);
		// get Patients
		try {
			patients = ClinicFileLoader.getPatientListFromSequentialFile(patientFilename);
		} catch (IOException e) {
			return new ArrayList<Queue<Visit>>(0); // return empty arraylist
		}
		// get Visits
		try {
			visits = ClinicFileLoader.getVisitListFromSequentialFile(visitFilename, patients);
		} catch (IOException e) {
			return new ArrayList<Queue<Visit>>(0);
		}
		// separate visit array (already sorted) into queues by Priority
		Visit[][] priority = separateSortedVisitArray(visits);
		// Create the adapter object that will be used as an argument to
		// instantiate a LinkedList instance.
		for (int i = 0; i < Priority.values().length; i++) {
			List<Visit> listAdapter = java.util.Arrays.asList(priority[i]);
			// insert a reference to an LinkedList instance into array of queues
			db.add(i, new LinkedList<Visit>(listAdapter));
		}
		return db;
	}

	/**
	 * Merges a list of queues into a single array
	 * 
	 * @param visits
	 *            List of queues of Visits
	 * @return a single array
	 */
	private Visit[] merge(List<Queue<Visit>> visits) {
		int numQueues = visits.size();
		List<Visit> all = new ArrayList<Visit>();
		for (int i = 0; i < numQueues; i++) {
			// iterate through the queues, converting to array
			Visit[] visitArray = visits.get(i).toArray(new Visit[visits.get(i).size()]);
			Collections.addAll(all, visitArray);
		}
		return all.toArray(new Visit[all.size()]);
	}

	/**
	 * Saves the list of patients to the text file
	 * 
	 * @param patients a list (collection) of patients.
	 * @throws IOException
	 */
	@Override
	public void savePatientDatabase(List<Patient> patients) throws IOException {
		// For now we’ll use the existing saveListToTextFile utility method.
		Patient[] patientArray = patients.toArray(new Patient[patients.size()]);
		ListUtilities.saveListToTextFile(patientArray, patientFilename);
	}

	/**
	 * Saves the queues of visits to the text file.
	 * 
	 * @param visits a list (collection) of visits.
	 * @throws IOException
	 */
	@Override
	public void saveVisitDatabase(List<Queue<Visit>> visits) throws IOException {
		// merge the queues into an array
		Visit[] visitArray = merge(visits);
		// use the existing saveListToTextFile utility method
		ListUtilities.saveListToTextFile(visitArray, visitFilename);
	}

	/**
	 * Returns a reference to an array containing the visits at the given
	 * priority
	 * 
	 * @param sorted
	 *            The array of all sorted visits
	 * @return an array of arrays with the subset of visits with the given
	 *         priority
	 */
	private Visit[][] separateSortedVisitArray(Visit[] sorted) {
		int length = sorted.length;
		Visit[][] subsets = new Visit[Priority.values().length][length];
		int subsetCtr = 0;
		int allCtr = 0;
		int p = 0;
		// iterate through priorities
		while (allCtr < length && p < Priority.values().length) {
			while (allCtr < length && sorted[allCtr].getPriority() == Priority.values()[p]) {
				subsets[p][subsetCtr] = sorted[allCtr];
				allCtr++;
				subsetCtr++;
			}
			// resize
			if (subsetCtr < length) {
				Visit[] resized = new Visit[subsetCtr];
				for (int i = 0; i < subsetCtr; i++) {
					resized[i] = subsets[p][i];
				}
				subsets[p] = resized;
			}
			// next priority
			p++;
			subsetCtr = 0;
		}
		
		/**
		 * 20/11/2015: I had to add these line of codes in or it would break my code.
		 * Without these lines, the priorities that are not gone through are not resized,
		 * causing errors in DawsonClinicPriorityPolicy.
		 */
		while (p < Priority.values().length){
			Visit[] resized = new Visit[subsetCtr];
			for (int i = 0; i < subsetCtr; i++) {
				resized[i] = subsets[p][i];
			}
			subsets[p] = resized;
			p++;
		}
		return subsets;
	}
}