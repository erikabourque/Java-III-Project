/**
 * 
 */
package group1.clinic.data;

import group1.clinic.business.Priority;
import group1.util.Utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;

/**
 * @author Katherine Richer 1434389
 *
 */
public class ObjectSerializedList implements ListPersistenceObject {
	
	private String patientFilename;
	private String visitFilename;
	FileOutputStream fout;
	ObjectOutputStream oos;
	
	public void convertSequentialFilesToSerialized(String
			sequentialPatients, String sequentialVisits)
			throws IOException {
			SequentialTextFileList textFile = new
			SequentialTextFileList(sequentialPatients, sequentialVisits);
			//patients
			List<Patient> patients = textFile.getPatientDatabase();
			Utilities.serializeObject(patients, patientFilename);
			List<Queue<Visit>> visits = textFile.getVisitDatabase();
			Utilities.serializeObject(visits, visitFilename);
			}
	/* (non-Javadoc)
	 * @see group1.clinic.data.ListPersistenceObject#getPatientDatabase()
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

	/* (non-Javadoc)
	 * @see group1.clinic.data.ListPersistenceObject#getVisitDatabase()
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

	/* (non-Javadoc)
	 * @see group1.clinic.data.ListPersistenceObject#savePatientDatabase(java.util.List)
	 */
	@Override
	public void savePatientDatabase(List<Patient> patients) throws IOException {
		// TODO Auto-generated method stub
		
		for (int i = 0;  i < patients.size(); i++) {
		  fout = new FileOutputStream("datafiles/database/patients.ser", true);
		    oos = new ObjectOutputStream(fout);
		    oos.writeObject(patients.get(i).toString());
		}
		fout.close();
		oos.close();

	}

	/* (non-Javadoc)
	 * @see group1.clinic.data.ListPersistenceObject#saveVisitDatabase(java.util.List)
	 */
	@Override
	public void saveVisitDatabase(List<Queue<Visit>> visits) throws IOException {
		// TODO Auto-generated method stub
		for (int i = 0;  i < visits.size(); i++) {
			  fout = new FileOutputStream("datafiles/database/visit.ser", true);
			    oos = new ObjectOutputStream(fout);
			    oos.writeObject(visits.get(i).toString());
			}
			fout.close();
			oos.close();
	}
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
		return subsets;
	}

}
