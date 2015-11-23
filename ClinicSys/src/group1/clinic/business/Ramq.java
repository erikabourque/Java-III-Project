/**
 * Defines a Ramq type.
 */
package group1.clinic.business;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import dw317.lib.Gender;
import dw317.lib.Name;

/**
 * This class represents a Ramq number. When constructed with a Name type, it
 * validates that Ramq matches Name. Ramq alphabetical characters are
 * automatically converted to upper case.
 * 
 * @author Erika Bourque
 * @version 1.1 22/11/2015
 */
public class Ramq implements Comparable<Ramq>, Serializable{

	static final long serialVersionUID = 42031768871L;
	private String ramq = "";

	/**
	 * Default no parameter constructor of Ramq
	 */
	public Ramq() {

	}

	/**
	 * One parameter constructor of Ramq. Validates ramq numeric using
	 * validateRamqNumeric method.
	 * 
	 * @param ramq
	 *            the Ramq
	 */
	public Ramq(String ramq) {
		if (validateRamqNumeric(ramq)) {
			this.ramq = ramq.toUpperCase().trim();
		}
	}

	/**
	 * Two parameter constructor of Ramq. Includes Name. Validates Ramq with
	 * Name using validateRamq method.
	 * 
	 * @param ramq
	 *            the Ramq
	 * @param name
	 *            the Name
	 */
	public Ramq(String ramq, Name name) {
		if (validateRamq(ramq, name)) {
			this.ramq = ramq.toUpperCase().trim();
		}
	}

	/**
	 * Overrides the compareTo method.
	 */
	@Override
	public int compareTo(Ramq comparingRamq) {
		return ramq.compareTo(comparingRamq.getRamq());
	}

	/**
	 * Two Ramqs are equal if -they point to same object -the ramq strings are
	 * the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ramq other = (Ramq) obj;
		if (ramq == null) {
			if (other.ramq != null)
				return false;
		} else if (!ramq.equals(other.ramq))
			return false;
		return true;
	}

	/**
	 * Returns the date of birth according to the Ramq. Uses validatebirthdate
	 * to check for errors.
	 * 
	 * @return birthDate the birth date in localDate form
	 */
	public LocalDate getBirthdate() {
		String date = ramq.substring(4, 10);
		LocalDate birthDate = validateBirthdate(date);
		return birthDate;
	}

	/**
	 * Returns the gender according to the Ramq. Uses validateGender to check
	 * for errors.
	 * 
	 * @return gender The Gender according to the Ramq
	 */
	public Gender getGender() {
		Gender gender = validateGender(ramq);
		return gender;
	}

	/**
	 * Returns the Ramq.
	 * 
	 * @return ramq the Ramq
	 */
	public String getRamq() {
		return ramq;
	}

	/**
	 * Overrides the hashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ramq == null) ? 0 : ramq.hashCode());
		return result;
	}

	/**
	 * Overrides the toString method.
	 */
	@Override
	public String toString() {
		return ramq;

	}

	/**
	 * Tests if birth date is valid. Validation separate from getter method to
	 * allow validation before assigning ramq. Assumes birthdate cannot be equal
	 * to or over 100 years ago.
	 * 
	 * @param date
	 *            the date portion of the Ramq
	 * @returns birthDate the birthdate in LocalDate form
	 * @throws IllegalArgumentException
	 */
	private LocalDate validateBirthdate(String date)
			throws IllegalArgumentException {
		LocalDate birthdate;

		int year = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2, 4));
		int day = Integer.parseInt(date.substring(4));

		// If Ramq belongs to a female, removes 50 from birth month
		if (month > 50) {
			month = month - 50;
		}

		// Checks for which century birthdate belongs in
		// Difference of less than 2000 demonstrates that birth was in 1900s
		int currentYear = LocalDate.now().getYear();
		if ((currentYear - year) < 2000) {
			year = 1900 + year;
		} else {
			year = 2000 + year;
		}

		try {
			birthdate = LocalDate.of(year, month, day);
		} catch (DateTimeException dtpe) {
			throw new IllegalArgumentException("Cannot identify date.  "
					+ "Ramq is invalid.\n" + dtpe.getMessage());
		}

		return birthdate;
	}

	/**
	 * Tests if Gender is valid. Validation separate from getter method to allow
	 * validation before assigning ramq.
	 * 
	 * @param ramq
	 *            the Ramq
	 * @return returns the gender
	 * @throws IllegalArgumentException
	 */
	private Gender validateGender(String ramq) throws IllegalArgumentException {
		// Checks birth month, +50 symbolizes female
		if (ramq.startsWith("5", 6) || ramq.startsWith("6", 6)) {
			return Gender.FEMALE;
		} else if (ramq.startsWith("0", 6) || ramq.startsWith("1", 6)) {
			return Gender.MALE;
		} else {
			throw new IllegalArgumentException(
					"Cannot identify gender.  Ramq is not valid.");
		}
	}

	/**
	 * Checks if the Ramq number is valid by using validateRamqNumeric then
	 * comparing it to the name. First 3 chars (or 2 if only 2 chars long) must
	 * match last name Next char (or 2 if last name is 2 chars) must match first
	 * name. Name String values and Ramq name Strings are converted to upper
	 * case for validation.
	 * 
	 * @param ramq
	 *            the Ramq
	 * @param name
	 *            the Name to compare the Ramq against
	 * @return returns true if Ramq is valid
	 * @throws IllegalArgumentException
	 */
	private boolean validateRamq(String ramq, Name name)
			throws IllegalArgumentException {
		String lastName = name.getLastName().toUpperCase();
		String firstName = name.getFirstName().toUpperCase();
		String ramqLastName;
		String ramqFirstName;

		// Checks for numeric validity
		validateRamqNumeric(ramq);

		// Checks if last name is 2 letters or longer
		if (lastName.length() < 3) {

			// Checking if last name matches
			ramqLastName = ramq.substring(0, 2).toUpperCase();
			if (lastName.startsWith(ramqLastName) == false) {
				throw new IllegalArgumentException(
						"Ramq does not match last name.  Ramq invalid.");
			}

			// Checking if first name matches
			ramqFirstName = ramq.substring(2, 4).toUpperCase();
			if (firstName.startsWith(ramqFirstName) == false) {
				throw new IllegalArgumentException(
						"Ramq does not match first name.  Ramq invalid.");
			}
		} else {

			// Checking if last name matches
			ramqLastName = ramq.substring(0, 3).toUpperCase();
			if (lastName.startsWith(ramqLastName) == false) {
				throw new IllegalArgumentException(
						"Ramq does not match last name.  Ramq invalid.");
			}

			// Checking if first name matches
			ramqFirstName = ramq.substring(3, 4).toUpperCase();
			if (firstName.startsWith(ramqFirstName) == false) {
				throw new IllegalArgumentException(
						"Ramq does not match first name.  Ramq invalid.");
			}
		}

		return true;
	}

	/**
	 * Checks if Ramq numeric is valid: Must be 12 chars in length, must start
	 * with 4 alphabet characters, followed by valid birth date, with gender
	 * addition +50 to birth month if female, ends with 2 digits for
	 * administrative use
	 * 
	 * @param ramq
	 *            the Ramq
	 * @return returns true if Ramq is valid
	 * @throws IllegalArgumentException
	 */
	private boolean validateRamqNumeric(String ramq)
			throws IllegalArgumentException {
		// Tests for appropriate length
		if (ramq == null)
			throw new IllegalArgumentException ("RAMQ must not be empty.");
		
		if (ramq.trim().length() != 12) {
			throw new IllegalArgumentException(
					"Ramq is not correct length.  Ramq is not valid.");
		}

		// Tests for alphabet characters for name
		String ramqName = ramq.substring(0, 4);
		if (ramqName.matches("[a-zA-Z]{4}") == false) 
			throw new IllegalArgumentException(
					"Ramq must only contain alphabetical characters "
							+ "in name portion.  Ramq is not valid.");
		

		// Tests for valid gender identifier
		validateGender(ramq);

		// Tests for valid birthdate
		String birthDate = ramq.substring(4, 10);
		validateBirthdate(birthDate);

		// Tests for digits for administrative numbers
		String ramqAdminNum = ramq.substring(10);
		if (ramqAdminNum.matches("[\\d]{2}") == false) {
			throw new IllegalArgumentException(
					"Ramq must only contain digits "
							+ "in the administrative numbers portion.  Ramq is not valid.");
		}

		return true;
	}

}
