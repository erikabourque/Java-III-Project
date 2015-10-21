package dw317.clinic.business.interfaces;

import dw317.lib.*;
import dw317.lib.medication.Medication;
import group1.clinic.business.Ramq;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Patient Interface
 * 
 * @author: Danieil Skrinikov
 * @Version: 16/09/2015
 * 
 */

public interface Patient extends Comparable<Patient>, Serializable {

	/**
	 * Returns a LocalDate instance.
	 * 
	 * @return an instance of LocalDate
	 */
	public LocalDate getBirthday();

	/**
	 * Returns a String which contains the conditions that a person may have.
	 * 
	 * @return the conditions that the client may have in a String.
	 */
	public String getExistingConditions();

	/**
	 * Returns a Gender instance.
	 * 
	 * @return an instance of Gender
	 */
	public Gender getGender();

	/**
	 * Returns an Optional<Medication> instance.
	 * 
	 * @return an instance of Optional<Medication>
	 */
	public Optional<Medication> getMedication();

	/**
	 * Returns a deep copy of the Name class.
	 * 
	 * @return deep copy of a Name instance
	 */
	public Name getName();

	/**
	 * Returns a Ramq instance.
	 * 
	 * @return an instance of Ramq
	 */
	public Ramq getRamq();

	/**
	 * Returns a string which represents a phone number.
	 * 
	 * @return phone number in a String
	 */
	public String getTelephoneNumber();

	/**
	 * Calls the setExistingConditions method which changes the conditions that
	 * a person has.
	 * 
	 * @param ailment
	 *            new condition for a person.
	 */
	public void setExistingConditions(Optional<String> ailment);

	/**
	 * Calls the setMediaction method which changes the medication that a person
	 * may take.
	 * 
	 * @param medication
	 *            new medication for a person.
	 */
	public void setMedication(Optional<Medication> medication);

	/**
	 * Calls the set Telephone number method. Which changes a telephone number
	 * for a person.
	 * 
	 * @param telephoneNumber
	 *            the new telephone number for a person.
	 */
	public void setTelephoneNumber(Optional<String> telephoneNumber);

}
