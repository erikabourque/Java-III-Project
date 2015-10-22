package dw317.clinic.data;

public class NonExistingPatientException extends Exception {
	private static final long serialVersionUID = 42031768871L;
	public  NonExistingPatientException(){
		super("The provided Patient already exists.");
	}
	
	public NonExistingPatientException(String message){
		super(message);
	}
}
