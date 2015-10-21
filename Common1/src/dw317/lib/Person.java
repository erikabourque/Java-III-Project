/**
 * Defines a Person type.
 */
package dw317.lib;

/**
 * This class represents a person. A person must have a names, and could also
 * have an address.
 * 
 * @author Erika Bourque
 * @version 1.1
 */
public class Person {

	private Address address;
	private Name name;

	/**
	 * Two parameter constructor of the person. Person must have a name, address
	 * can be blank.
	 * 
	 * @param firstName
	 *            The first name of person's name
	 * @param lastName
	 *            The last name of person's name
	 */
	public Person(String firstName, String lastName) {
		name = new Name(validateName(firstName, "First Name"), validateName(
				lastName, "Last Name"));
		address = new Address();
	}

	/**
	 * Three parameter constructor of the person. Person must have a name, and
	 * address.
	 * 
	 * @param firstName
	 *            The first name of person's name
	 * @param lastName
	 *            The last name of person's name
	 * @param address
	 *            The address of person
	 */
	public Person(String firstName, String lastName, Address address) {
		name = new Name(validateName(firstName, "First Name"), validateName(
				lastName, "Last Name"));
		if (address.getCivicNumber() != "" && address.getStreetName() != ""
				&& address.getCity() != "") {
			this.address = new Address(address.getCivicNumber(),
					address.getStreetName(), address.getCity());
		} else {
			this.address = new Address();
		}

	}

	/**
	 * Returns a copy of the person's address.
	 * 
	 * @return copyAddress a copy of the address.
	 */
	public Address getAddress() {
		Address copyAddress;
		if (address.getCivicNumber() != "" && address.getStreetName() != ""
				&& address.getCity() != "") {
			copyAddress = new Address(address.getCivicNumber(),
					address.getStreetName(), address.getCity());
		} else {
			copyAddress = new Address();
		}

		return copyAddress;
	}

	/**
	 * Returns a copy of the person's name.
	 * 
	 * @return copyName a copy of the name.
	 */
	public Name getName() {
		Name copyName = new Name(name.getFirstName(), name.getLastName());
		return copyName;
	}

	/**
	 * Uses inputed address to set the address.
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address.setCivicNumber(address.getCivicNumber());
		this.address.setStreetName(address.getStreetName());
		this.address.setCity(address.getCity());
	}

	/**
	 * Uses inputed name to set the name. Uses validateName to make sure there
	 * are no null or spaces
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String firstName, String lastName) {
		this.name.setFirstName(validateName(firstName, "First Name"));
		this.name.setLastName(validateName(lastName, "Last Name"));
	}

	/**
	 * Overrides toString method to represent person as a String.
	 */
	@Override
	public String toString() {

		return name.toString() + "*"
				+ (address == null ? "" : address.toString());
	}

	/**
	 * Validates that the values are not null. if values contain spaces, it
	 * trims the value.
	 * 
	 * @param fieldValue
	 *            the value to be set
	 * @param fieldName
	 *            the name of the field that is being set
	 * @return returns the value, trimmed
	 */
	private String validateName(String fieldValue, String fieldName) {
		if (fieldValue == null)
			throw new IllegalArgumentException("Name Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue);

		String trimmedString = fieldValue.trim();

		if (trimmedString.trim().isEmpty())
			throw new IllegalArgumentException("Name Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue);
		return trimmedString;
	}

}
