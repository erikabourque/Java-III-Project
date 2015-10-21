/**
 * Defines a NDCMedication type.
 */
package dw317.lib.medication;

/**
 * This class represents a NDCMedication.
 * NDCMedication has a number and name.
 * Extends the AbstractMedication class.
 * 
 * @author	Erika Bourque
 * @author	Katherine Richer
 * @version	9/27/2015
 */
public final class NDCMedication extends AbstractMedication {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Two parameter constructor of NDCMedication.
	 * Requires a number and name.
	 * 
	 * @param number	the NDC's number
	 * @param name		the NDC's name
	 */
	public NDCMedication(String number, String name) {
		super(Scheme.NDC, validateNumber(number), name);
	}

	/**
	 * Tests if the NDC number is valid.
	 * NDC number must be exactly 12 chars long.
	 * NDC number must contain 3 sets of digits separated by hyphens.
	 * 
	 * @param number	the number to be tested
	 * @return			the number after being validated
	 * @throws IllegalArgumentException
	 */
	private static String validateNumber(String number)
			throws IllegalArgumentException {

		int hyphenCounter = 0;
		int index = 0;
		String delimeter = "-";
		
		if (number == null)
			throw new IllegalArgumentException("String invalid, it is null");
		
		// checks number of characters
		if (number.length() != 12)
			throw new IllegalArgumentException("NDC format not correct, "
					+ "please check the number, missing characters");
				
		// checks for only digits and hyphens
		if (number.matches("[\\d-]{12}") == false)
			throw new IllegalArgumentException(
					"String invalid, string must contain only digits with 2 hyphens");

		// checks number of hyphens
		while (index != -1) {
			index = number.indexOf(delimeter, index);

			if (index != -1) {
				hyphenCounter++;
				index++;
			}

		}

		if (hyphenCounter > 2)
			throw new IllegalArgumentException(
					"NDC format not correct, please check the number, too many hyphens");

		else if (hyphenCounter < 2)
			throw new IllegalArgumentException(
					"NDC format not correct, please check the number, missing hyphens");

		// checks for two hyphens next to each other
		if (number.contains("--"))
			throw new IllegalArgumentException(
					"NDC format not correct, please check the number, hyphens cannot be next to each other");

		// checks if the number starts with or ends with a hyphen
		if (number.startsWith("-") || number.endsWith("-"))
			throw new IllegalArgumentException(
					"NDC format not correct, please check the number, cannot start or end with a hyphen ");

		return number;

	}
}
