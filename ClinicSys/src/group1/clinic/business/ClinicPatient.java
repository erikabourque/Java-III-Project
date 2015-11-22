/**
 * 
 */
package group1.clinic.business;

import dw317.clinic.business.interfaces.Patient;
import dw317.lib.Gender;
import dw317.lib.Name;
import dw317.lib.medication.Medication;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Uen Yi Cindy Hung
 * @author Danield Skrinikov 
 * @version 21/09/2015
 *
 */
public final class ClinicPatient implements Patient {

	// VARIABLES
	static final long serialVersionUID = 42031768871L;
	private LocalDate birthday;
	private Optional<String> existingConditions = Optional.empty();
	private Gender gender;
	private Optional<Medication> medication = Optional.empty();
	private Name name;
	private Optional<String> phoneNumber = Optional.empty();
	private Ramq ramq;

	// CONSTRUCTOR
	/**
	 * A three parameters constructor to build a Patient instance.
	 * 
	 * @param String
	 *            firstName
	 * @param String
	 *            lastName
	 * @param String
	 *            ramq
	 */
	public ClinicPatient(String firstName, String lastName, String ramq) {
		validateName("first name", firstName);
		validateName("last name", lastName);
		name = new Name(firstName.trim(), lastName.trim());
		this.ramq = new Ramq(ramq);
		gender = this.ramq.getGender();
		birthday = this.ramq.getBirthdate();
	}

	// GETTERS

	/**
	 * Compare if the two objects are the same, if yes then return a 0 else
	 * return a 1.
	 * 
	 * @param Patient
	 *            toCompare
	 * @return int
	 */
	@Override
	public final int compareTo(Patient toCompare) {
		if (this == toCompare)
			return 0;
		if (toCompare == null)
			return 1;
		if (ramq.getRamq().compareTo(toCompare.getRamq().getRamq()) <= -1)
			return -1;
		else if (ramq.getRamq().compareTo(toCompare.getRamq().getRamq()) == 0)
			return 0;

		return 1;
	}

	/**
	 * Determine if the two objects are the exact same object, if they are equal
	 * to each other.
	 * 
	 * @param Object
	 *            obj
	 * @return boolean
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Patient))
			return false;

		ClinicPatient other = (ClinicPatient) obj;

		if (ramq == null) {
			if (other.getRamq() != null)
				return false;
		} else if (!ramq.equals(other.getRamq()))
			return false;

		return true;
	}

	/**
	 * Returns a LocalDate instance.
	 * 
	 * @return LocalDate
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * Returns a String which contains the conditions that a person may have.
	 * 
	 * @return String
	 */
	public String getExistingConditions() {
		if (existingConditions.isPresent())
			return existingConditions.get();
		return "";
	}

	/**
	 * Returns a Gender instance.
	 * 
	 * @return Gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Returns an Optional<Medication> instance.
	 * 
	 * @return Medication
	 */
	public Optional<Medication> getMedication() {
		if (medication.isPresent())
			return medication;
		return Optional.empty();
	}

	/**
	 * Returns a deep copy of the Name class.
	 * 
	 * @return Name
	 */
	public Name getName() {
		return name;
	}

	// SETTERS

	/**
	 * Returns a Ramq (Regie Assurance Maladie Quebec), instance.
	 * 
	 * @return Ramq
	 */
	public Ramq getRamq() {
		return ramq;
	}

	/**
	 * Returns a string which represents a phone number, if it exists.
	 * 
	 * @return String
	 */
	public String getTelephoneNumber() {
		if (phoneNumber.isPresent())
			return phoneNumber.get();
		return "";
	}

	/**
	 * Returns the hash code value of the ramq value.
	 * 
	 * @return int
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ramq.hashCode();
		return result;
	}

	// CUSTOM

	/**
	 * Calls the setExistingConditions method which changes the conditions that
	 * a person has.
	 * 
	 * @param String
	 *            ailment
	 */
	public void setExistingConditions(Optional<String> ailment) {
		if (validateExistence(ailment)) {
			ailment = Optional.of(ailment.get().trim());
			existingConditions = ailment;
		} else if (!(existingConditions.isPresent())) {
			existingConditions = Optional.empty();
		} else {
			if (ailment.get().length() < 1)
				existingConditions = Optional.empty();
		}
	}

	/**
	 * Calls the setMediaction method which changes the medication that a person
	 * may take.
	 * 
	 * @param String
	 *            medication
	 */
	public void setMedication(Optional<Medication> medication) {
		if (medication.isPresent())
			this.medication = medication;
		else {
			this.medication = Optional.empty();
		}
	}

	/**
	 * Calls the set Telephone number method. Which changes the value of
	 * phoneNumber. for a person.
	 * 
	 * @param String
	 *            telephoneNumber
	 */
	public void setTelephoneNumber(Optional<String> telephoneNumber) {
		if (validateExistence(telephoneNumber)) {
			if (!(telephoneNumber.get().matches("[\\d]{10}")))
				throw new IllegalArgumentException(
						"Error, the telephone number must contain exactly 10 numeric characters and only numeric characters");
			phoneNumber = Optional.of(telephoneNumber.get().trim());
		}

		else if (!(phoneNumber.isPresent())) {
			phoneNumber = Optional.empty();
		} else {
			if (telephoneNumber.get().length() < 1)
				phoneNumber = Optional.empty();
		}
	}

	/**
	 * Return a patient file String in the format
	 * ramqId*firstName*lastName*phoneNumber*scheme*medicationNumber*
	 * medicationName*conditions of the object Patient.
	 * 
	 * @return String
	 */
	@Override
	public final String toString() {
		String toReturn = "";

		toReturn += ramq.getRamq() + "*" + name.toString() + "*";

		toReturn += getTelephoneNumber();

		toReturn += "*";

		if (medication.isPresent())
			toReturn += medication.get().getScheme() + "*" + medication.get().getNumber() + "*"
					+ medication.get().getName() + "*";
		else
			toReturn += "***";

		toReturn += getExistingConditions();

		return toReturn;
	}

	/**
	 * Checks if field values is null, empty or present.
	 * 
	 * 21/11/2015 change. added is present.
	 * 
	 * @param String
	 *            fieldValue
	 * @return boolean
	 */
	private final boolean validateExistence(Optional<String> fieldValue) {
		
		if(!(fieldValue.isPresent()))
			return false;
		
		if (!(fieldValue.get().equals(null))) {
			if (fieldValue.isPresent()) {
				fieldValue.get().trim();
				if (fieldValue.get().length() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if field values are null, contains at least 2 characters and
	 * contains only alphabetic character.
	 * 
	 * @param String
	 *            fieldName
	 * @param String
	 *            fieldValue
	 */
	private final void validateName(String fieldName, String fieldValue) {
		if (fieldValue == null)
			throw new IllegalArgumentException(
					"Error, " + fieldName + " may not be null. Invalid value = " + fieldValue);

		if (fieldValue.trim().length() < 2)
			throw new IllegalArgumentException(
					"Error, " + fieldName + " must containt at least 2 characters. Invalid value = " + fieldValue);

		fieldValue = fieldValue.toUpperCase().trim();

		//for (int i = 0; i < fieldValue.length(); i++) {
			if (!fieldValue.matches("^[a-zA-Z]+[\\-\\' ]*[a-zA-Z]+$"))
			//if (fieldValue.charAt(i) < 65 || fieldValue.charAt(i) > 90)
				throw new IllegalArgumentException("Error, " + fieldName
						+ " may only contains alphabetic character. Invalid Value = " + fieldValue);
		//}

	}
}