/**
 * Provides a Controller for the Text App
 */
package group1.clinic.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import static java.lang.System.out;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitManager;
import dw317.clinic.data.DuplicatePatientException;
import dw317.clinic.data.NonExistingPatientException;
import dw317.clinic.data.NonExistingVisitException;
import group1.clinic.business.Priority;

/**
 * Controller for the TextApp for a PatientVisitManager.  Provides a text-based menu that allows a person to
 * view patient information, add new patients, add new visits, get the next triage visit and assign priorities
 * to it, and request the next examination visits.
 * 
 * @author Katherine Richer
 * @author Erika Bourque
 * @version 11/12/2015
 */
public class TextController {

		private PatientVisitManager model;
		private BufferedReader userInput = new BufferedReader (new InputStreamReader(System.in));
		
		private enum Command
		{
		 PATIENT_INFO, NEW_PATIENT, NEW_VISIT, NEXT_TO_TRIAGE, CHANGE_PRIORITY,
		NEXT_TO_EXAMINE, STOP
		}
		
		/**
		 * One Param Constructor.  Assigns given PatientVisitManager as
		 * the model to use.
		 * 
		 * @param model	The PatientVisitManager to be used.
		 */
		public TextController(PatientVisitManager model)
		{
			this.model = model;
		}
		
		/**
		 * Method used to start interacting with the text-based interface.
		 * Loops until user specifies to stop.
		 */
		public void run()
		{
			Command userCommand = null;
			
			while (userCommand != Command.STOP)
			{
				printMenu();
				
				// Getting user input				
				userCommand = getCommand();
				
				// Take action depending on that input
				if (userCommand == Command.PATIENT_INFO)
				{
					getPatient();
				}
				
				if (userCommand == Command.NEW_PATIENT)
				{
					addPatient();
				}
				
				if (userCommand == Command.NEW_VISIT)
				{
					addVisit();
				}
				
				if (userCommand == Command.NEXT_TO_TRIAGE)
				{
					getTriageVisit();
				}
				
				if (userCommand == Command.CHANGE_PRIORITY)
				{
					changePriority();
				}
				
				if (userCommand == Command.NEXT_TO_EXAMINE)
				{
					getExaminationVisit();
				}
				
				if (userCommand == null)
				{
					out.println("That is not a valid choice, please try again.");
				}
				
				// Start a newline after each command completed
				out.println();
			}			
		}
		
		/**
		 * Gets the user's command through their input.  Checks to make sure
		 * input is valid.
		 * 
		 * @return	The command that was chosen by the user's input
		 */
		private Command getCommand()
		{
			int commandNum = 0;
						
			try
			{
				commandNum = Integer.parseInt(userInput.readLine());
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("Please enter one digit only.");
			}
			catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}
			
			switch(commandNum)
			{
				case 1: 
					return Command.PATIENT_INFO;
				case 2: 
					return Command.NEW_PATIENT;
				case 3:
					return Command.NEW_VISIT;
				case 4:
					return Command.NEXT_TO_TRIAGE;
				case 5:
					return Command.CHANGE_PRIORITY;
				case 6:
					return Command.NEXT_TO_EXAMINE;
				case 7:
					return Command.STOP;
				default:
					return null;
			}
		}
		
		/**
		 * Prints the text-based menu.
		 */
		private void printMenu()
		{
			out.println("Dawson Clinic Menu");
			out.println("Select a choice from the menu:");
			out.println("\t1 - PATIENT_INFO");
			out.println("\t2 - NEW_PATIENT");
			out.println("\t3 - NEW_VISIT");
			out.println("\t4 - NEXT_TO_TRIAGE");
			out.println("\t5 - CHANGE_PRIORITY");
			out.println("\t6 - NEXT_TO_EXAMINE");
			out.println("\t7 - STOP");
			out.print("\nEnter your choice: ");			
		}
		
		/**
		 * Retrieves the patient with the ramq specified 
		 * by the user's input.
		 */
		private void getPatient()
		{
			String ramq;
			
			// THIS WILL ALSO NO LONGER BE HERE
			Patient thePatient;
			
			try
			{
				// Request and get the RAMQ
				out.print("Please enter the ramq: ");
				ramq = userInput.readLine();
				
				// Get the patient from model
				thePatient = model.findPatient(ramq);
				
				// TO PUT IN KAT'S CODE NOT HERE
				// Print the resulting patient
				out.println("Patient information");
				out.println("RAMQ: " + thePatient.getRamq());
				out.println(thePatient.getName().getLastName() + ", " + thePatient.getName().getFirstName());
				out.println("Telephone: " + thePatient.getTelephoneNumber());
			} 
			catch (IllegalArgumentException iae)
			{
				out.println(iae.getMessage());
			} 
			catch (IOException ioe) 
			{
				out.println(ioe.getMessage());
			} 
			catch (NonExistingPatientException nepe) 
			{
				out.println(nepe.getMessage());
			}			
		}
		
		/**
		 * Adds a new patient to the database.  Requests information
		 * from the user, and then creates the patient.
		 */
		private void addPatient()
		{
			String fname;
			String lname;
			String ramq;
			String phone;
			String existingMedCond;

			try
			{
				// Requests each piece of information about the patient and saves them
				out.print("Please enter the first name: ");
				fname = userInput.readLine();
				
				out.print("Please enter the last name: ");
				lname = userInput.readLine();
				
				out.print("Please enter the ramq: ");
				ramq = userInput.readLine();
				
				out.print("Please enter the phone number: ");
				phone = userInput.readLine();
				
				out.print("Please enter any existing medical conditions, or "
						+ "press enter if none available: ");
				existingMedCond = userInput.readLine();
				
				// Check if user did not put any input for existing medical conditions
				// if no input, make it null
				if (existingMedCond.isEmpty())
				{
					existingMedCond = null;
				}				
				
				// Create the patient
				model.registerNewPatient(fname, lname, ramq, phone, null, existingMedCond);				
			}
			catch (IOException ioe)
			{
				out.println(ioe.getMessage());
			}
			catch (DuplicatePatientException dpe) 
			{
				out.println(dpe.getMessage());
			}
			catch (IllegalArgumentException iae)
			{
				out.println(iae.getMessage());
			}
		}
		
		// Still gotta test it, first get visit
		/**
		 * Adds a new visit to the database.  Requests information
		 * from the user, then creates the visit.
		 */
		private void addVisit()
		{
			String ramq;
			String complaint;
			Patient thePatient;
			
			try
			{
				// Requests the ramq of the patient associated to the visit
				out.print("Please enter the ramq: ");
				ramq = userInput.readLine();
				
				out.print("Please enter the primary complaint of patients, or "
						+ "press enter if unknown: ");
				complaint = userInput.readLine();
				
				// Check if user did not put any input for complaint
				// if no input, make it null
				if (complaint.isEmpty())
				{
					complaint = null;
				}
				
				// Gets the patient
				thePatient = model.findPatient(ramq);
				
				// Create the visit
				model.createVisit(thePatient, complaint);				
			}
			catch (IOException ioe)
			{
				out.println(ioe.getMessage());
			}
			catch (IllegalArgumentException iae)
			{
				out.println(iae.getMessage());
			} 
			catch (NonExistingPatientException npe) 
			{
				out.println(npe.getMessage());
			}
		}
		
		// also gotta test this
		/**
		 * Gets the next visit for triage from the model.
		 */
		private void getTriageVisit()
		{
			try
			{
				model.nextForTriage();
			}
			catch (IllegalArgumentException iae)
			{
				out.println(iae.getMessage());
			}
		}
		
		// also gotta test this
		/**
		 * Changes the priority of the next triage patient to
		 * the priority specified by the user's input.
		 */
		private void changePriority()
		{		
			Priority newPriority;
			int priority;
			
			out.println("Select the new priority from the menu:");
			out.println("\t1 - REANIMATION");
			out.println("\t2 - VERYURGENT");
			out.println("\t3 - URGENT");
			out.println("\t4 - LESSURGENT");
			out.println("\t5 - NOTURGENT");
			out.print("\nEnter your choice: ");
			
			try
			{
				priority = Integer.parseInt(userInput.readLine());
				
				switch(priority)
				{
					case 1:
						newPriority = Priority.REANIMATION;
						break;
					case 2:
						newPriority = Priority.VERYURGENT;
						break;
					case 3:
						newPriority = Priority.URGENT;
						break;
					case 4:
						newPriority = Priority.LESSURGENT;
						break;
					case 5:
						newPriority = Priority.NOTURGENT;
						break;
					default:
						newPriority = null;
						break;
				}
				
				if (newPriority == null)
				{
					out.println("That is not a valid choice, please try again.");
				}
				else
				{
					model.changeTriageVisitPriority(newPriority);
				}
				
			}
			catch (IOException ioe)
			{
				out.println(ioe.getMessage());
			}
			catch (NumberFormatException nfe)
			{
				out.println("Please enter one digit only.");
			}
			catch (NonExistingVisitException neve)
			{
				out.println(neve.getMessage());
			}
		}
		
		// also gotta test this
		/**
		 * Gets the next visit for examination from the model.
		 */
		private void getExaminationVisit()
		{
			try
			{
				model.nextForExamination();
			}
			catch (IllegalArgumentException iae)
			{
				out.println(iae.getMessage());
			}
		}
}
