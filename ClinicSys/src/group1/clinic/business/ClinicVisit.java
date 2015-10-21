package group1.clinic.business;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.Visit;
import group1.clinic.business.Priority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Uen Yi Cindy Hung
 * @version 18/09/2015
 *
 */
public final class ClinicVisit implements Visit {

	// VARIABLES
	static final long serialVersionUID = 42031768871L;
	private Patient aPatient;
	private Optional<String> complaint=Optional.empty();
	private Priority priority;
	private LocalDateTime registrationDateTime;
	private Optional<LocalDateTime> triageDateTime=Optional.empty();

	// CONSTRUCTOR
	/**
	 * A one parameter constructor to built a Visit instance.
	 * 
	 * @param aPatient
	 */
	public ClinicVisit(Patient aPatient) {
		this.aPatient = aPatient;
		priority = Priority.NOTASSIGNED;
		registrationDateTime = LocalDateTime.now();
	}

	// GETTERS

	/**
	 * Compare if the two objects are the same, if yes then return a 0 else
	 * return a 1.
	 * 
	 * @return int
	 */
	@Override
	public final int compareTo(Visit toCompare) {
		if (this == toCompare)
			return 0;
		if (toCompare == null)
			return 1;
		if (aPatient.getRamq().getRamq().compareTo(toCompare.getPatient().getRamq().getRamq()) == 0)
			return 0;
		if (registrationDateTime.compareTo(toCompare.getRegistrationDateAndTime()) == 0)
			return 0;
		if (registrationDateTime.compareTo(toCompare.getRegistrationDateAndTime()) > 0)
			return 1;
		return -1;
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
		if (!(obj instanceof Visit))
			return false;

		ClinicVisit other = (ClinicVisit) obj;

		if (!aPatient.getRamq().equals(other.getPatient().getRamq()))
			return false;
		if (registrationDateTime.equals(other.getRegistrationDateAndTime()))
			return true;

		return false;
	}

	/**
	 * Returns a String which represents a complaint.
	 * 
	 * @return String
	 */
	public String getComplaint() {
		if (complaint.isPresent())
			return complaint.get();
		return "";
	}

	/**
	 * Returns a deep copy Patient instance.
	 * 
	 * @return Patient
	 */
	public Patient getPatient() {
		ClinicPatient tempPatient = new ClinicPatient(aPatient.getName()
				.getFirstName(), aPatient.getName().getLastName(),
				aPatient.getRamq().getRamq());
		tempPatient.setExistingConditions(Optional.of(aPatient
				.getExistingConditions()));
		if (aPatient.getMedication().isPresent())
			tempPatient.setMedication(Optional.of(aPatient.getMedication().get()));
		tempPatient.setTelephoneNumber(Optional.of(aPatient
				.getTelephoneNumber()));

		return tempPatient;
	}

	/**
	 * Returns an instance of Priority.
	 * 
	 * @return Priority
	 */
	public Priority getPriority() {
		return priority;
	}

	// SETTERS

	/**
	 * Returns an instance ofLocalDateTime.
	 * 
	 * @return LocalDateTime
	 */
	public LocalDateTime getRegistrationDateAndTime() {
		return registrationDateTime;
	}

	/**
	 * Returns an instance of Optional<LocalDateTime>, if it exists.
	 * 
	 * @return Optional <LocalDateTime>
	 */
	public Optional<LocalDateTime> getTriageDateAndTime() {
		if (triageDateTime.isPresent())
			return triageDateTime;
		return Optional.empty();
	}

	/**
	 * Returns the hash code value of the registrationDateTime value.
	 * 
	 * @return int
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + registrationDateTime.hashCode();
		return result;
	}

	/**
	 * Calls the setPriority method which changes the a person's complaint
	 * depending on the supplied parameter, it there is one.
	 * 
	 * @param Optinal
	 *            <String>
	 */
	public void setComplaint(Optional<String> complaint) {
		if (validateExistence(complaint))
			this.complaint = Optional.of(complaint.get().trim());
	}

	/**
	 * Calls the setPriority method which changes a person's priority depending
	 * on the supplied parameter.
	 * 
	 * @param Priority
	 *            aPriority
	 */
	public void setPriority(Priority aPriority) {
		try {
			priority = aPriority;
		} catch (Exception e) {
			System.out
					.println("Error, the priority entered does not exists. Invalid value = "
							+ aPriority);
		}
	}

	/**
	 * Sets a Date and Time value to the registration time of the object
	 * Patient.
	 * 
	 * @param year
	 *            new registration year.
	 * @param month
	 *            new registration month in form of a number. 1 to 12 expected.
	 * @param day
	 *            new registration day of a month in integer form. 1 to maximum
	 *            31 expected.
	 * @param hour
	 *            new registration hour in 24 hour format. 0 to 23 expected.
	 * @param sec
	 *            new registration seconds. Expected to have a value from 1 to
	 *            3599.
	 */
	public void setRegistrationDateAndTime(int year, int month, int day,
			int hour, int sec) {
		try {
			if ((LocalDateTime.now().getYear() - year) > 150)
			throw new IllegalArgumentException(
					"Error, the date entered is too old.\n" + "Today we are: "
							+ LocalDate.now() + ". Invalid value = " + year
							+ "-" + month + "-" + day);
		
			registrationDateTime = LocalDateTime.of(year, month, day, hour,
					(sec / 60));
		} catch (Exception e) {
			System.out
					.print("Error, please enter a valid date and time. Invalid values = year:"
							+ year
							+ " month: "
							+ month
							+ " day: "
							+ day
							+ " hour: " + hour + " seconds: " + sec);
		}
	}

	// CUSTOM

	/**
	 * Overloaded method. Calls the setregistrationDateAndTime method which will
	 * use the optional<LocalDateTime> instance to change the value of
	 * registration time for a person.
	 * 
	 * @param datetime
	 *            Optional<LocalDateTime> instance.
	 */
	public void setRegistrationDateAndTime(Optional<LocalDateTime> datetime) {
		if (!(datetime.equals(null)))
			if (datetime.isPresent())
				registrationDateTime = datetime.get();
	}

	/**
	 * Calls the setTriageDateAndTime method which will change a person's triage
	 * time depending on the supplied parameters.
	 * 
	 * @param year
	 *            new triage year.
	 * @param month
	 *            new triage month in integer form. 1 to 12 expected.
	 * @param day
	 *            new triage day in integer form. 1 to maximum 31 expected.
	 * @param hour
	 *            new triage hour in 24 hour format. 0 to 23 expected.
	 * @param sec
	 *            new triage seconds. Expected to have a value from 1 to 3599.
	 */
	public void setTriageDateAndTime(int year, int month, int day, int hour,
			int sec) {
		try {
			triageDateTime = Optional.of(LocalDateTime.of(year, month, day,
					hour, (sec / 60)));
			if (registrationDateTime.compareTo(triageDateTime.get()) > 1)
				throw new IllegalArgumentException(
						"Error, triage's date and time may not be before registration's date and time. \n"
								+ "Registration date and time is: "
								+ registrationDateTime
								+ "Invalid value = "
								+ triageDateTime.get());
		} catch (Exception e) {
			System.out
					.print("Error, please enter a valid date and time. Invalid values = year:"
							+ year
							+ " month: "
							+ month
							+ " day: "
							+ day
							+ " hour: " + hour + " seconds: " + sec);
		}

	}

	/**
	 * Overloaded method. Calls the setTriageDateAndTime method which will
	 * change a person's triage time with the Optional<LocalDateAndTime>
	 * instance.
	 * 
	 * @param datetime
	 *            Optional<LocalDateTime> instance.
	 */
	public void setTriageDateAndTime(Optional<LocalDateTime> datetime) {
		if (!(datetime.equals(null)))
			if (datetime.isPresent())
				triageDateTime = datetime;
	}

	/**
	 * Returns a String with all information of the Visit object in the
	 * following format:
	 * ramq*registrationYear*registrationMonth*registrationDay*
	 * registrationHr*registrationMin*
	 * triageYear*triageMonth*triageDay*triageHr*triageMin*
	 * priorityCode*complaint
	 * 
	 * @return String
	 */
	@Override
	public final String toString() {
		String toReturn = "";

		toReturn += aPatient.getRamq().getRamq() + "*";
		toReturn += registrationDateTime.getYear() + "*"
				+ registrationDateTime.getMonthValue() + "*"
				+ registrationDateTime.getDayOfMonth() + "*";
		toReturn += registrationDateTime.getHour() + "*"
				+ registrationDateTime.getMinute() + "*";

		if (triageDateTime.isPresent()) {
			toReturn += triageDateTime.get().getYear() + "*"
					+ triageDateTime.get().getMonthValue() + "*"
					+ triageDateTime.get().getDayOfMonth() + "*";
			toReturn += triageDateTime.get().getHour() + "*"
					+ triageDateTime.get().getMinute();
		}

		toReturn += "*";

		if (priority.getCode() > 0)
			toReturn += priority.getCode();

		toReturn += "*";

		if (complaint.isPresent())
			toReturn += getComplaint();

		return toReturn;
	}

	/**
	 * Checks if field values is null, empty or present.
	 * 
	 * @param String
	 *            fieldValue
	 * @return boolean
	 */
	private final boolean validateExistence(Optional<String> fieldValue) {
		if (!(fieldValue.equals(null))) {
			if (fieldValue.isPresent()) {
				fieldValue.get().trim();
				if (fieldValue.get().length() > 0) {
					return true;
				}
			}
		}
		return false;
	}
}
