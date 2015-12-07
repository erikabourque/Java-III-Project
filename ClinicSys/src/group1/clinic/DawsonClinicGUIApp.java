package group1.clinic;

import group1.clinic.business.Clinic;
import group1.clinic.data.ObjectSerializedList;
import group1.clinic.data.PatientListDB;
import group1.clinic.data.VisitQueueDB;
import group1.clinic.ui.GUIViewController;
import group1.dawsonclinic.DawsonClinicFactory;
import dw317.clinic.ClinicFactory;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.clinic.data.interfaces.VisitDAO;

public class DawsonClinicGUIApp {

	public static void main(String[] args) {

		ClinicFactory factory = DawsonClinicFactory.DAWSON_CLINIC;

		PatientDAO patientDb = new PatientListDB(
				new ObjectSerializedList("datafiles/database/patients.ser", "datafiles/database/visits.ser"),
				factory.getPatientVisitFactory());

		VisitDAO visitDb = new VisitQueueDB(
				new ObjectSerializedList("datafiles/database/patients.ser", "datafiles/database/visits.ser"),
				factory.getPatientVisitFactory());

		Clinic model = new Clinic(patientDb, visitDb, factory);
		GUIViewController app = new GUIViewController(model);
	}
}