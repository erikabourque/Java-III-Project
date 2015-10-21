/**
 * Defines a Name type.
 */
package dw317.lib;

import java.io.Serializable;

/**
 * This class represents a name. Names can be blank, or can include a first name
 * and last name.
 * 
 * @author Erika Bourque
 * @version 1.1
 */
public class Name implements Serializable {

	private static final long serialVersionUID = 42031768871L;
	private String firstName = "";
	private String lastName = "";

	/**
	 * Default constructor of Name.
	 */
	public Name() {
	}

	/**
	 * Two parameter constructor of Name. Name includes the first name and last
	 * name.
	 * 
	 * @param firstName
	 *            The first name
	 * @param lastName
	 *            The last name
	 */
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Returns a String with the name's first name.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return Returns a String with the first name followed by the last name.
	 */
	public String getFullName() {
		return (firstName + " " + lastName);
	}

	/**
	 * Returns a String with the name's last name.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Uses inputed string to set the first name.
	 * 
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Uses inputed string to set the last name.
	 * 
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Overrides toString method to represent name as a String.
	 */
	@Override
	public String toString() {
		return (firstName + "*" + lastName);
	}
}
