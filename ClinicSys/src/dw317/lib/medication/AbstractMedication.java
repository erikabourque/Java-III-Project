/**
 * Defines a AbstractMedication type.
 */
package dw317.lib.medication;

/**
 * This class represents an AbstractMedication.
 * AbstractMedication has a scheme, number and name.
 * Implements the Medication interface.
 * 
 * @author Erika Bourque
 * @author Katherine Richer
 * @version 1.0
 */
public abstract class AbstractMedication implements Medication {

	private static final long serialVersionUID = 42031768871L;
	private final String name;
	private final String number;
	private final Scheme scheme;

	/**
	 * Three parameter constructor of AbstractMedication.  Requires
	 * a scheme, number and name.
	 * 
	 * @param scheme	the medication's scheme
	 * @param number	the medication's number
	 * @param name		the medication's name
	 */
	public AbstractMedication(Scheme scheme, String number, String name) {
		this.scheme = scheme;
		this.number = number;
		this.name = name;
	}

	/**
	 * Overrides the equals method.
	 * Two AbstractMedications are equal if their number
	 * and scheme are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMedication other = (AbstractMedication) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (scheme != other.scheme)
			return false;
		return true;
	}

	/**
	 * Returns the medication's name.
	 * 
	 * @return	the name of the medication
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Returns the medications number.
	 * 
	 * @return the number of the medication.
	 */
	@Override
	public String getNumber() {
		return number;
	}

	/**
	 * Returns the medication's scheme
	 * 
	 * @return	the scheme of the medication
	 */
	@Override
	public Scheme getScheme() {
		return scheme;
	}

	/**
	 * Overrides the hashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((scheme == null) ? 0 : scheme.hashCode());
		return result;
	}

	/**
	 * Overrides the toString method.
	 */
	@Override
	public String toString() {
		return scheme + "*" + number + "*" + name;
	}
}
