/**
 * Provides a Controller for the Text App
 */
package group1.clinic.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import dw317.clinic.business.interfaces.PatientVisitManager;

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
		
		public TextController(PatientVisitManager model)
		{
			this.model = model;
		}
		
		public void run()
		{
			Command userCommand = null;
			
			while (userCommand != Command.STOP)
			{
				printMenu();
				// Getting user input
				userCommand = getCommand();
			}			
		}
		
		private Command getCommand()
		{
			int commandNum = 0;
						
			try
			{
				commandNum = Integer.parseInt(userInput.readLine());
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("Please enter an integer only.");
			}
			catch (IOException ioe)
			{
				System.out.println("Please enter an integer only.");
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
		
		private void printMenu()
		{
			System.out.println("Dawson Clinic Menu");
			System.out.println("Select a choice from the menu:");
			System.out.println("\t1 - PATIENT_INFO");
			System.out.println("\t2 - NEW_PATIENT");
			System.out.println("\t3 - NEW_VISIT");
			System.out.println("\t4 - NEXT_TO_TRIAGE");
			System.out.println("\t5 - CHANGE_PRIORITY");
			System.out.println("\t6 - NEXT_TO_EXAMINE");
			System.out.println("\t7 - STOP");
			System.out.print("\nEnter your choice: ");
			
		}
}
