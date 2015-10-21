/**
 * 
 */
package group1.clinic.business;

/**
 * @author
 * @version 18/09/2015
 *
 */
public enum Priority {
	NOTASSIGNED(0), REANIMATION(1), VERYURGENT(2), URGENT(3), LESSURGENT(4), NOTURGENT(5);

	private int code;

	private Priority(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static Priority getPriorityCode(int number) {
		Priority codes[] = Priority.values();
		for (int i = 0; i < codes.length; i++)
			if (codes[i].getCode() == number)
				return codes[i];
		throw new IllegalArgumentException("Priority " + number + " is not a valid priority code.");
	}
}
