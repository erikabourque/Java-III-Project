package dw317.clinic.data;

/**
 * Signals that the provided RAMQ exists already.
 * 
 * @author	Katherine Richer
 * @version	22/10/2015
 */

public class DuplicatePatientException extends Exception {
	private static final long serialVersionUID = 42031768871L;

	public DuplicatePatientException() {
		super("The provided RAMQ already exists.");
	}

	public DuplicatePatientException(String message) {
		super(message);
	}

}
