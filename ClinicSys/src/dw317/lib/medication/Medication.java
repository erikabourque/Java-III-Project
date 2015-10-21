/**
 * Defines a Medication type.
 */
package dw317.lib.medication;

import java.io.Serializable;

/**
 * Medication Interface
 * 
 * @author	Erika Bourque
 * @author	Katherine Richer
 * @Version: 27/09/2015
 */
public interface Medication extends Serializable {
	/**
	 * The Schemes that can be used for medication.
	 */
	public enum Scheme {
		DIN("XXXXXXXX"), NDC("XXXX-XXXX-XX");

		private String format;

		private Scheme(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}

	/**
	 * Returns the name of the medication
	 * 
	 * @return	the name of the medication
	 */
	String getName();

	/**
	 * Returns the number of the medication.
	 * 
	 * @return the number of the medication.
	 */
	String getNumber();

	/**
	 * Returns the scheme of the medication
	 * 
	 * @return	the scheme of the medication
	 */
	Scheme getScheme();

	// Medication factory method based on Scheme
	public static Medication getInstance(Scheme scheme, String number,
			String name) {
		Medication medication = null;
		switch (scheme) {
		case DIN:
			medication = new DINMedication(number, name);
			break;
		case NDC:
			medication = new NDCMedication(number, name);
		}
		return medication;
	}
}
