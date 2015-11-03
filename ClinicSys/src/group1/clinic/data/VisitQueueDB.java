/**
 * 
 */
package group1.clinic.data;

import group1.clinic.business.Priority;

import java.io.IOException;
import java.util.Optional;

import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.NonExistingVisitException;
import dw317.clinic.data.interfaces.VisitDAO;

/**
 * @author 1430468
 *
 */
public class VisitQueueDB implements VisitDAO{

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Priority oldPriority, Priority newPriority)
			throws NonExistingVisitException {
		// TODO Auto-generated method stub
		
	}

	
	
}
