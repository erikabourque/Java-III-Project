/**
 * 
 */
package dw317.clinic.business.interfaces;

import java.io.Serializable;
import dw317.lib.medication.Medication;

/**
 * @author
 * @version 16/09/2015
 *
 */
public interface PatientVisitFactory extends Serializable {
	Patient getPatientInstance(String firstName, String lastName, String ramq);

	Medication.Scheme getScheme();

	Visit getVisitInstance(Patient aPatient);

}
