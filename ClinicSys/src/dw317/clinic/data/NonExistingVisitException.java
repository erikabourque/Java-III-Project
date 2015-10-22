package dw317.clinic.data;

public class NonExistingVisitException extends Exception {
	private static final long serialVersionUID = 42031768871L;
	public  NonExistingVisitException(){
		super("The provided Patient already exists.");
	}
	
	public NonExistingVisitException(String message){
		super(message);
	}
}
