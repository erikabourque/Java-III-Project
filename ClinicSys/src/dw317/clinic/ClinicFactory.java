package dw317.clinic;

import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.business.interfaces.PriorityPolicy;
import dw317.clinic.data.interfaces.VisitDAO;
import java.io.Serializable;

/**
 * Interface for a Clinic Factory.
 * 
 * @author	Erika Bourque
 * @version 12/11/2015
 */
public interface ClinicFactory extends Serializable {
	PatientVisitFactory getPatientVisitFactory();

	PriorityPolicy getPriorityPolicyInstance(VisitDAO visistConnection);
}