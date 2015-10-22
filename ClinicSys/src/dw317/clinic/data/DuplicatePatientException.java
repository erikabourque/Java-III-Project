package dw317.clinic.data;
/**
 * 
 * @author Katherine Richer
 *
 */
//signals that the provide RAMQ exists already
public class DuplicatePatientException  extends Exception{
	private static final long serialVersionUID = 42031768871L;
	public DuplicatePatientException(){
		super("The provided RAMQ already exists.");
	}
	
	public DuplicatePatientException(String message){
		super(message);
	}
	
	
}
