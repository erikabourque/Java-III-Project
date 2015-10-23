package dw317.clinic.data;

/**
 * Signals that the provided visit exists already.
 * 
 * @author Katherine Richer
 * @version 22/10/2015
 */
public class NonExistingVisitException extends Exception {
	private static final long serialVersionUID = 42031768871L;

	public NonExistingVisitException() {
		super("The provided visit already exists.");
	}

	public NonExistingVisitException(String message) {
		super(message);
	}
}
