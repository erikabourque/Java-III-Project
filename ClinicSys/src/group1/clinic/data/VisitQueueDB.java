/**
 * 
 */
package group1.clinic.data;

import group1.clinic.business.Priority;

import java.io.IOException;
import java.util.Optional;
import java.util.List;
import java.util.Queue;
import dw317.clinic.business.interfaces.PatientVisitFactory;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.NonExistingVisitException;
import dw317.clinic.data.interfaces.VisitDAO;

/**
 * @author Danieil Skrinikov
 * @version 11/03/2015
 *
 */
public class VisitQueueDB implements VisitDAO{

	private List<Queue<Visit>> database;
	private final ListPersistenceObject listPersistenceObject;
	private final PatientVisitFactory factory;
	
	/*public VisitQueueDB(ListPersistenceObject listPersistenceObject){
		
		this.listPersistenceObject = listPersistenceObject;
		//I do not understand how to get factory here. Plz help
		database = listPersistenceObject.getVisitDatabase();
		
	}*/
	
	public VisitQueueDB(ListPersistenceObject listPersistenceObject, PatientVisitFactory factory){
		
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		database = listPersistenceObject.getVisitDatabase();		
	}
	
	@Override
	public void add(Visit aVisit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Visit> getNextVisit(Priority priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Priority priority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size(Priority priority) {
		
		Object[] temp = database.toArray();
		int count = 0;
		for(int i = 0 ; i < database.size() ; i++){
			
			if(((String)temp[i]).lastIndexOf("*"))
			
		}
		
	}

	@Override
	public void update(Priority oldPriority, Priority newPriority)
			throws NonExistingVisitException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		
		int size = database.size();
		String toReturnString;
		Object[] databaseArray = database.toArray();
		
		for(int i = 0 ; i < size ; i++){
			
			System.out.println(databaseArray[i]);
			
		}
		
		
		// TODO
		return "TODO";
	}

	
	
}
