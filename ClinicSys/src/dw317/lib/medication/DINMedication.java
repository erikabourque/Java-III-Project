/**
 * Defines a DINMedication type.
 */
package dw317.lib.medication;

/**
 * This class represents a DINMedication.
 * DINMedication has a number and name.
 * Extends the AbstractMedication class.
 * 
 * @author	Erika Bourque
 * @author	Katherine Richer
 * @version	9/27/2015
 */
public final class DINMedication extends AbstractMedication {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Two parameter constructor of DINMedication.
	 * Requires a number and name.
	 * 
	 * @param number	the DIN's number
	 * @param name		the DIN's name
	 */
	public DINMedication(String number, String name) {
		super(Scheme.DIN, validateNumber(number), name);
	}

	/**
	 * Tests if the DIN number is valid.
	 * DIN number must be exactly 8 digits long.
	 * 
	 * @param number	the number to be tested
	 * @return			the number after being validated
	 * @throws IllegalArgumentException
	 */
	private static String validateNumber(String number)
			throws IllegalArgumentException {
		if (number.matches("[\\d]{8}")) {
			return number;
		} else
			throw new IllegalArgumentException(
					"String invalid, string does not contain only numbers");
	}

}