/**
 * Defines an Address type.
 */
package dw317.lib;

import java.util.Optional;

/**
 * This class represents an address. Addresses can be blank, or can include a
 * civic number, street name and city.
 * 
 * @author Erika Bourque
 * @version 1.3
 */

public class Address {

	private String city = "";
	private String civicNumber = "";
	private String code = "";
	private String province = "";
	private String streetName = "";

	/**
	 * Default constructor of the address.
	 */
	public Address() {
	}

	/**
	 * Three parameter constructor of the address. Address includes the civic
	 * number, street name, and city. Uses validateExistence method to trim
	 * excess space and insure String is not empty.
	 * 
	 * @param civicNumber
	 *            Civic number of the address
	 * @param streetName
	 *            Street name of the address
	 * @param city
	 *            City of the address
	 */
	public Address(String civicNumber, String streetName, String city) {
		this.civicNumber = validateExistence("civic number", civicNumber);
		this.streetName = validateExistence("street name", streetName);
		this.city = validateExistence("city", city);
	}

	public Address(String civicNumber, String streetName, String city,
			Optional<String> province, Optional<String> code) {
		this.civicNumber = validateExistence("civic number", civicNumber);
		this.streetName = validateExistence("street name", streetName);
		this.city = validateExistence("city", city);
	}

	/**
	 * Returns a String representation of the address.
	 * 
	 * @return address a formatted address.
	 */
	public String getAddress() {
		String address = civicNumber + " " + streetName + "\n" + city;
		address += (province.equals("") ? "" : (", " + province))
				+ (code.equals("") ? "" : ("\n" + code));

		return address;
	}

	/**
	 * Returns a String with the address's city.
	 * 
	 * @return city the address's city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns a String with the address's civic number.
	 * 
	 * @return civicNumber the address's civic number.
	 */
	public String getCivicNumber() {
		return civicNumber;
	}

	/**
	 * Returns a String with the address's postal code.
	 * 
	 * @return code the address's postal code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns a String with the address's province.
	 * 
	 * @return province the address's province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Returns a String with the address's street name.
	 * 
	 * @return streetName the address's street name.
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Uses inputed string to set the city. Uses validateExistence method to
	 * trim excess space and insure String is not empty.
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = validateExistence("city", city);
	}

	/**
	 * Uses inputed string to set the civic number. Uses validateExistence
	 * method to trim excess space and insure String is not empty.
	 * 
	 * @param civicNumber
	 *            the civic number to set
	 */
	public void setCivicNumber(String civicNumber) {
		this.civicNumber = validateExistence("civic number", civicNumber);
	}

	/**
	 * Uses inputed string to set the postal code. Uses validateExistence method
	 * to trim excess space and insure String is not empty.
	 * 
	 * @param code
	 *            the postal code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Uses inputed string to set the province. Uses validateExistence method to
	 * trim excess space and insure String is not empty.
	 * 
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Uses inputed string to set the street name. Uses validateExistence method
	 * to trim excess space and insure String is not empty.
	 * 
	 * @param streetName
	 *            the street name to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = validateExistence("street name", streetName);
	}

	/**
	 * Overrides toString method to represent address as a String.
	 */
	@Override
	public String toString() {
		return (civicNumber + "*" + streetName + "*" + city + "*" + province
				+ "*" + code);
	}

	/**
	 * Checks if field values are null or empty, and trims the field values to
	 * remove excess spaces.
	 * 
	 * @param fieldName
	 *            the field that is being changed
	 * @param fieldValue
	 *            the content that the field is being changed to
	 * @return content of the field after being trimmed
	 */
	private String validateExistence(String fieldName, String fieldValue) {
		if (fieldValue == null)
			throw new IllegalArgumentException("Address Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue);

		String trimmedString = fieldValue.trim();
		if (trimmedString.trim().isEmpty())
			throw new IllegalArgumentException("Address Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue);
		return trimmedString;
	}

}
