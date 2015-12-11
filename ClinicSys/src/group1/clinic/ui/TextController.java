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

/**
 * Controller for the TextApp for a PatientVisitManager.  Provides a text-based menu that allows a person to
 * view patient information, add new patients, add new visits, get the next triage visit and assign priorities
 * to them, and request the next examination visits.
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
				
				// Start a newline after any commands
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
			Patient thePatient;
			
			// Request and get the RAMQ
			out.print("Please enter the ramq: ");
			try
			{
				ramq = userInput.readLine();
				thePatient = model.findPatient(ramq);
				
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
		 * Adds a new patient to the database.  Requests Information
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
				
				// Check if user had any to input, make it null
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
		
		private void addVisit()
		{
			
		}
		
		private void getTriageVisit()
		{
			
		}
		
		private void changePriority()
		{
			
		}
		
		private void getExaminationVisit()
		{
			
		}
}
