/**
 * Provides the View for DawsonClinicTextApp.
 */
package group1.clinic.ui;

import group1.clinic.business.Clinic;
import group1.clinic.business.Priority;

import static java.lang.System.out;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;

/**
 * View for the DawsonClinicTextApp. Displays updated information from the
 * specifed model, including visits (optional/next visits and new visits),
 * patients, and updated priorities.
 * 
 * @author Erika Bourque
 * @author Katherine Richer
 * @version 13/12/2015
 */
public class TextView implements Observer {

	private Clinic model;

	/**
	 * One param constructor for TextView. Model is the model to be observed,
	 * constructor adds the class to the model's observers list.
	 * 
	 * @param model
	 *            the model to be observed
	 */
	public TextView(Observable model) {
		this.model = (Clinic) model;
		model.addObserver(this);
		update(model, null);
	}

	/**
	 * Printing of the Optional<Visit>
	 * 
	 * @param theVisit
	 *            the visit to be printed.
	 */
	private void printOptionalVisit(Optional<Visit> theVisit) {
		out.println();
		out.println("Next visit: ");
		out.println(theVisit.get().getPatient().getName().getLastName() + ", "
				+ theVisit.get().getPatient().getName().getFirstName());
	}

	/**
	 * Printing of the patient.
	 * 
	 * @param thePatient
	 *            the patient to be printed.
	 */
	private void printPatient(Patient thePatient) {
		out.println();
		out.println("Patient information");
		out.println("RAMQ: " + thePatient.getRamq());
		out.println(thePatient.getName().getLastName() + ", " + thePatient.getName().getFirstName());
		out.println("Telephone: " + thePatient.getTelephoneNumber());
	}

	/**
	 * Printing of the Priority.
	 * 
	 * @param thePriority
	 *            the priority to be printed.
	 */
	private void printPriority(Priority thePriority) {
		out.println();
		out.println("Triaged visit priority changed to: " + thePriority);
	}

	/**
	 * Printing of the visit.
	 * 
	 * @param theVisit
	 *            the visit to be printed.
	 */
	private void printVisit(Visit theVisit) {
		out.println();
		out.println("New visit for: ");
		out.println(
				theVisit.getPatient().getName().getLastName() + ", " + theVisit.getPatient().getName().getFirstName());
	}

	/**
	 * Observable update override. Checks for updates on Optional<Visit>,
	 * visits, patients, and priorities, and displays them accordingly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observableObject, Object arg) {
		if (arg instanceof Optional<?>) {
			printOptionalVisit((Optional<Visit>) arg);
		} else if (arg instanceof Patient) {
			printPatient((Patient) arg);
		} else if (arg instanceof Visit) {
			printVisit((Visit) arg);
		} else if (arg instanceof Priority) {
			printPriority((Priority) arg);
		}
	}

}
