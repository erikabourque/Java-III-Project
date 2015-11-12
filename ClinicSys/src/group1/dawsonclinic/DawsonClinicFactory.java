package group1.dawsonclinic;

import dw317.clinic.ClinicFactory;
import dw317.clinic.DefaultPatientVisitFactory;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.business.interfaces.PriorityPolicy;
import dw317.clinic.data.interfaces.VisitDAO;

public enum DawsonClinicFactory implements ClinicFactory {
	DAWSON_CLINIC;
	private static final long serialVersionUID = 42031768871L;

	@Override
	public PatientVisitFactory getPatientVisitFactory() {
		return DefaultPatientVisitFactory.DEFAULT;
	}

	@Override
	public PriorityPolicy getPriorityPolicyInstance(VisitDAO visitDB) {
		return new DawsonClinicPriorityPolicy(visitDB);
	}
}