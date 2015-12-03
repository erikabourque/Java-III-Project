/**
 * 
 */
package group1.clinic.ui;

import dw317.clinic.business.interfaces.PatientVisitManager;

/**
 * @author Katherine Richer
 *
 */
public class TextController {

		private PatientVisitManager model;
		
		private enum Command
		{
		 PATIENT_INFO, NEW_PATIENT, NEW_VISIT, NEXT_TO_TRIAGE, CHANGE_PRIORITY,
		NEXT_TO_EXAMINE, STOP
		}
		public TextController(PatientVisitManager model)
		{
			this.model = model;
		}
		
		public void run()
		{
			
		}
}
