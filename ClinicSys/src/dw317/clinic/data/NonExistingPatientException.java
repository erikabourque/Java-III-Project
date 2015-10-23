package dw317.clinic.data;

/**
 * Signals that the provided patient exists already.
 * 
 * @author Katherine Richer
 * @version 22/10/2015
 */
public class NonExistingPatientException extends Exception {
	private static final long serialVersionUID = 42031768871L;

	public NonExistingPatientException() {
		super("The provided patient already exists.");
	}

	public NonExistingPatientException(String message) {
		super(message);
	}
}
