/**
 * 
 */
package dw317.clinic;

import group1.clinic.business.ClinicPatient;
import group1.clinic.business.ClinicVisit;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.business.interfaces.Visit;
import dw317.lib.medication.Medication;

/**
 * @author
 * @version 16/09/2015
 *
 */
public enum DefaultPatientVisitFactory implements PatientVisitFactory {
	DEFAULT;

	private static final long serialVersionUID = 42031768871L;

	@Override
	public Patient getPatientInstance(String firstName, String lastName,
			String ramq) {
		return new ClinicPatient(firstName, lastName, ramq);
	}

	@Override
	public Medication.Scheme getScheme() {
		return Medication.Scheme.DIN;
	}

	@Override
	public Visit getVisitInstance(Patient aPatient) {
		return new ClinicVisit(aPatient);
	}

}
