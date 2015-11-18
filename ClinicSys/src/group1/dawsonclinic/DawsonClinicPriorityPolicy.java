package group1.dawsonclinic;

import java.time.LocalDateTime;
import java.util.Optional;
import dw317.clinic.business.interfaces.PriorityPolicy;
import dw317.clinic.business.interfaces.Visit;
import dw317.clinic.data.interfaces.VisitDAO;
import group1.clinic.business.Priority;

public class DawsonClinicPriorityPolicy implements PriorityPolicy {

	// VARIABLES
	private static final long serialVersionUID = 42031768871L;
	private VisitDAO visitDB;
	private LocalDateTime currentPriorityTime;
	private LocalDateTime higherPriorityTime;
	private Optional<Visit> aVisit;
	int[] policy = { 2, 3, 2, 4, 3, 2, 5, 2, 3, 4 };
	int counter = 0;
	int higherCounter = 1;

	// CONSTRUCTOR
	/**
	 * A one parameter constructor.
	 * 
	 * @param visitDB
	 */
	public DawsonClinicPriorityPolicy(VisitDAO visitDB) {
		this.visitDB = visitDB;
	}

	/**
	 * Return a Visit with an algorithm that takes/remove all visit with
	 * Priority 1 first. Then in the following priority order
	 * 2, 3, 2, 4, 3, 2, 5, 2, 3, 4. Lower priority are dequeued only if
	 * no other higher priorities registered before them.
	 * 
	 * @return Optional<Visit> The visit being called.
	 */
	@Override
	public Optional<Visit> getNextVisit() {
		// Check if anymore visits to dequeue
		for (int i = 1; i < Priority.values().length + 1; i++) {
			if (i == 6)
				return Optional.empty();
			if (visitDB.getNextVisit(Priority.getPriorityCode(i)) != null)
				break;
		}

		// Dequeue all priority 1 first
		if (visitDB.getNextVisit(Priority.REANIMATION) != null) {
			aVisit = visitDB.getNextVisit(Priority.REANIMATION);
			visitDB.remove(Priority.REANIMATION);
			return aVisit;

		} else {
			do{
			if (counter >= policy.length)
				counter = 0;
			
			// Dequeue where there is something, going down the priority policy
			while ((visitDB.getNextVisit(Priority.getPriorityCode(policy[counter])) == null)) {
				counter++;
				if (counter >= policy.length)
					counter = 0;
			}
			
			// Dequeue priority 2 right away since there should not be any priority 1 left
			if (policy[counter] == 2) {
				aVisit = visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]));
				visitDB.remove(Priority.getPriorityCode(policy[counter]));
				counter++;
				return aVisit;
			}
			
			// Check if there is any higher priority than current priority
			while (visitDB.getNextVisit(Priority.getPriorityCode(policy[counter] - higherCounter)) == null) {
				higherCounter++;
				if (policy[counter] - higherCounter < 2){
					higherCounter=1;
					aVisit = visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]));
					visitDB.remove(Priority.getPriorityCode(policy[counter]));
					counter++;
					return aVisit;
				}					
			}

			
			// Get the registration times
			currentPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]))).get()
					.getRegistrationDateAndTime();
			
			higherPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]-higherCounter))).get()
					.getRegistrationDateAndTime();
			
			// Check if there is any higher priority who registered first
			while(currentPriorityTime.compareTo(higherPriorityTime) < 0){
				higherCounter++;
				if (policy[counter] - higherCounter < 2){
					higherCounter=1;
					aVisit = visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]));
					visitDB.remove(Priority.getPriorityCode(policy[counter]));
					counter++;
					return aVisit;
				}	
				if(visitDB.getNextVisit(Priority.getPriorityCode(policy[counter] - higherCounter)) != null)
					higherPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]-higherCounter))).get()
						.getRegistrationDateAndTime();
			}			
			counter++;
			higherCounter=1;
			}while(currentPriorityTime.compareTo(higherPriorityTime) > 0);
			
			return Optional.empty();			
		}
	}

}
/****************************************************************
 * 
 * EXTRA CODE - CODED BECAUSE OF MISUNDERSTANDING OF THE SPECS,
 * WILL BE KEPT FOR POSSIBLE FUTURE REFERENCE, PLEASE IGNORE AND DO NOT
 * DELETE

	private int current = 2;
	private int next;
	private LocalDateTime nextPriorityTime;
	
if (currentPriorityTime.compareTo(higherPriorityTime) < 0) {
	aVisit = visitDB.getNextVisit(Priority.getPriorityCode(policy[counter]));
	visitDB.remove(Priority.getPriorityCode(policy[counter]));
	return aVisit;
}

// No Priority 1 left, onto the next Priorities
// Check if there is any Visits with the current Priority
while (visitDB.getNextVisit(Priority.getPriorityCode(current)) == null) {
	if (current >= Priority.values().length - 1)
		current = 2;
	else
		current++;
}

next = current + 1;
if (next >= Priority.values().length)
	next = 2; // Check if there is any Visits with the next Priority
while (visitDB.getNextVisit(Priority.getPriorityCode(next)) == null) {
	if (next >= Priority.values().length - 1)
		next = 2;
	else
		next++;
}
}

// There is more than 1 type of Priority left
if (current != next) {
// Check the registration time
currentPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(current))).get()
		.getRegistrationDateAndTime();
nextPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(next))).get()
		.getRegistrationDateAndTime();

// The current priority patient registered BEFORE the next priority
// patient
if (currentPriorityTime.compareTo(nextPriorityTime) < 0)

{
	aVisit = visitDB.getNextVisit(Priority.getPriorityCode(current));
	visitDB.remove(Priority.getPriorityCode(current));
	return aVisit;
} // The current priority patient registered AFTER the next priority
	// patient
else {
	// Increment 'current' to serve the next priority patients
	current = next;
	next = (current >= Priority.values().length) ? 2 : current + 1;
	aVisit = visitDB.getNextVisit(Priority.getPriorityCode(current));
	visitDB.remove(Priority.getPriorityCode(current));
	return aVisit;
}
// There is only 1 type of Priority left
} else {
aVisit = visitDB.getNextVisit(Priority.getPriorityCode(current));
visitDB.remove(Priority.getPriorityCode(current));
return aVisit;
*/
