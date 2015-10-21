package dw317.clinic.business.interfaces;

import java.io.Serializable;
import java.util.Optional;
import java.time.LocalDateTime;
import group1.clinic.business.Priority;

/**
 * Visit Interface.
 * 
 * @author Danieil Skrinikov
 * @version 9/16/2015
 *
 */

public interface Visit extends Comparable<Visit>, Serializable {

	/**
	 * Returns a String which represents a complaint.
	 * 
	 * @return complaint that a user may have.
	 */
	public String getComplaint();

	/**
	 * Returns a deep Copy of a Patient Instance.
	 * 
	 * @return deep copy of a patient instance.
	 */
	public Patient getPatient();

	/**
	 * Returns an instance of Priority.
	 * 
	 * @return instance of Priority
	 */
	public Priority getPriority();

	/**
	 * Returns an instance ofLocalDateTime.
	 * 
	 * @return instance of LocalDateTime.
	 */
	public LocalDateTime getRegistrationDateAndTime();

	/**
	 * Returns an instance of Optional<LocalDateTime>.
	 * 
	 * @return instance of Optional<LocalDateTime>.
	 */
	public Optional<LocalDateTime> getTriageDateAndTime();

	/**
	 * Calls the setPriority method which changes the a person's complaint
	 * depending on the supplied parameter.
	 * 
	 * @param complaint
	 *            Optional<String> instance.
	 */
	public void setComplaint(Optional<String> complaint);

	/**
	 * Calls the setPriority method which changes a person's priority depending
	 * on the supplied parameter.
	 * 
	 * @param aPriority
	 *            Priority instance.
	 */
	public void setPriority(Priority aPriority);

	/**
	 * Calls the setRegistrationDateAndTime method which changes the
	 * registration time of a person.
	 * 
	 * @param year
	 *            new registration year.
	 * @param month
	 *            new registration month in form of a number. 1 to 12 expected.
	 * @param day
	 *            new registration day of a month in integer form. 1 to maximum
	 *            32 expected.
	 * @param hour
	 *            new registration hour in 24 hour format. 0 to 23 expected.
	 * @param sec
	 *            new registration seconds. Expected to have a value from 1 to
	 *            3599.
	 */
	public void setRegistrationDateAndTime(int year, int month, int day,
			int hour, int sec);

	/**
	 * Overloaded method. Calls the setregistrationDateAndTime method which will
	 * use the optional<LocalDateTime> instance to change the value of
	 * registration time for a person.
	 * 
	 * @param datetime
	 *            Optional<LocalDateTime> instance.
	 */
	public void setRegistrationDateAndTime(Optional<LocalDateTime> datetime);

	/**
	 * Calls the setTriageDateAndTime method which will change a person's triage
	 * time depending on the supplied parameters.
	 * 
	 * @param year
	 *            new triage year.
	 * @param month
	 *            new triage month in intger form. 1 to 12 expected.
	 * @param day
	 *            new triage day in integer form. 1 to maximum 31 expected.
	 * @param hour
	 *            new triage hour in 24 hour format. 0 to 23 expected.
	 * @param sec
	 *            new triage seconds. Expected to have a value from 1 to 3599.
	 */
	public void setTriageDateAndTime(int year, int month, int day, int hour,
			int sec);

	/**
	 * Overloaded method. Calls the setTriageDateAndTime method which will
	 * change a person's triage time with the Optional<LocalDateAndTime>
	 * instance.
	 * 
	 * @param datetime
	 *            Optional<LocalDateTime> instance.
	 */
	public void setTriageDateAndTime(Optional<LocalDateTime> datetime);

}
