/**
 * Provides a way to start the DawsonClinicTextApp.
 */
package group1.clinic;

import dw317.clinic.ClinicFactory;
import dw317.clinic.data.interfaces.PatientDAO;
import dw317.clinic.data.interfaces.VisitDAO;
import group1.clinic.business.Clinic;
import group1.clinic.data.PatientListDB;
import group1.clinic.data.VisitQueueDB;
import group1.clinic.data.ObjectSerializedList;
import group1.clinic.ui.TextController;
import group1.clinic.ui.TextView;
import group1.dawsonclinic.DawsonClinicFactory;

/**
 * Runs the Text Controller using Clinic as a Model.
 * 
 * @author Katherine Richer
 * @author Erika Bourque
 */
public class DawsonClinicTextApp 
{

	public static void main(String[] args) 
	{
		ClinicFactory factory = DawsonClinicFactory.DAWSON_CLINIC;
		PatientDAO patientDb = new PatientListDB(
				new ObjectSerializedList("datafiles/database/patients.ser", "datafiles/database/visits.ser"),
				factory.getPatientVisitFactory());
		VisitDAO visitDb = new VisitQueueDB(
				new ObjectSerializedList("datafiles/database/patients.ser", "datafiles/database/visits.ser"),
				factory.getPatientVisitFactory());
		Clinic model = new Clinic(patientDb, visitDb, factory);
		// TextView view = new TextView(model);
		TextController controller = new TextController(model);
		
		controller.run();
	}

}
