package group1.clinic.ui;

import group1.clinic.business.Clinic;
import group1.clinic.business.Priority;

import static java.lang.System.out;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;

public class TextView implements Observer {

	private Clinic model; 
	
	public TextView(Observable model)
	{
		this.model = (Clinic) model;
		model.addObserver(this);
		update(model, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observableObject, Object arg) {
		if (arg instanceof Optional<?>)
		{
			printOptionalVisit((Optional<Visit>)arg);
		}
		else if (arg instanceof Patient)
		{
			printPatient((Patient) arg);
		}
		else if (arg instanceof Visit)
		{
			printVisit((Visit)arg);
		}
		else if (arg instanceof Priority)
		{
			printPriority((Priority)arg);
		}
	}
	
	private void printOptionalVisit(Optional<Visit> theVisit)
	{
		out.println();
		out.println("Next visit: ");
		out.println(theVisit.get().getPatient().getName().getLastName() + ", " 
				+ theVisit.get().getPatient().getName().getFirstName());
	}
	
	private void printPatient(Patient thePatient)
	{
		out.println();
		out.println("Patient information");
		out.println("RAMQ: " + thePatient.getRamq());
		out.println(thePatient.getName().getLastName() + ", " + thePatient.getName().getFirstName());
		out.println("Telephone: " + thePatient.getTelephoneNumber());
	}
	
	private void printVisit(Visit theVisit)
	{
		out.println();
		out.println("New visit for: ");
		out.println(theVisit.getPatient().getName().getLastName() + ", " + theVisit.getPatient().getName().getFirstName());
	}
	
	private void printPriority(Priority thePriority)
	{
		out.println();
		out.println("Triaged visit priority changed to: " + thePriority);
	}

}
