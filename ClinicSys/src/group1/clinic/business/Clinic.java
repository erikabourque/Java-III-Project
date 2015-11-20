/**
 * 
 */
package group1.clinic.business;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dw317.clinic.ClinicFactory;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitManager;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.clinic.data.interfaces.VisitDAO;
import dw317.lib.medication.Medication;

/**
 * @author 1430468
 *
 */
public class Clinic  implements PatientVisitManager{
	
	private PatientDAO patientConnection;
	private VisitDAO visitConnection;
	private ClinicFactory factory;
	
	public Clinic(PatientDAO patientConnection, VisitDAO visitConnection,
			ClinicFactory factory){
		
		if(patientConnection == null || visitConnection == null || factory == null){
			
			throw new IllegalArgumentException("Clinic - One of the given parameters is null");
			
		}
		
		this.patientConnection = patientConnection;
		this.visitConnection = visitConnection;
		this.factory = factory;
		
	}

	@Override
	public void closeClinic() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createVisit(Patient patient, String complaint) {
		
		if(patient == null)
			throw new IllegalArgumentException("Clinic.createVisit() - Patient type parameter is null.");
		
		Visit aVisit = new ClinicVisit(patient);
		
		aVisit.setComplaint(Optional.of(complaint));
		
		visitConnection.add(aVisit);
		
	}

	@Override
	public Patient findPatient(String ramq) throws NonExistingPatientException {
		
		//Validate ramq todo
		Ramq ramqPatient = new Ramq(ramq);
		
		return patientConnection.getPatient(ramqPatient);
		
	}

	@Override
	public List<Patient> findPatientsPrescribed(Medication meds) {
		
		//Validate meds
		return patientConnection.getPatientsPrescribed(meds);
	}

	@Override
	public Optional<Visit> nextForTriage() {
		
		return;
		
	}

	@Override
	public Optional<Visit> nextForExamination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerNewPatient(String firstName, String lastName,
			String ramq, String telephone, Medication meds, String conditions)
			throws DuplicatePatientException {
		// TODO Auto-generated method stub
		
	}

}
