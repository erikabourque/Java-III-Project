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
	private int current = 2;
	private int next = current + 1;
	private LocalDateTime currentPriorityTime;
	private LocalDateTime nextPriorityTime;
	private Optional<Visit> aVisit;

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
	 * Priority 1 first. Then takes Visits from Priority 2 to 5 while taking in
	 * consideration the registration time and removing patients that had their
	 * visits registered first. Therefore, remove visits by priority then by
	 * registration time.
	 * 
	 * @return Optional<Visit> The visit being called.
	 */
	@Override
	public Optional<Visit> getNextVisit() {
		// Check if anymore visits to dequeue
		for (int i = 1; i < Priority.values().length+1; i++) {
			if (visitDB.getNextVisit(Priority.getPriorityCode(i)) != null)
				break;
			if (i == 6)
				return Optional.empty();
		}

		// Dequeue all priority 1 first
		if (visitDB.getNextVisit(Priority.REANIMATION) != null) {
			aVisit = visitDB.getNextVisit(Priority.REANIMATION);
			visitDB.remove(Priority.REANIMATION);
			return aVisit;

		} else {
			// No Priority 1 left, onto the next Priorities
			// Check if there is any Visits with the current Priority
			while (visitDB.getNextVisit(Priority.getPriorityCode(current)) == null) {
				if (current >= Priority.values().length - 1)
					current = 2;
				else
					current++;
			}

			next = current + 1;
			if (next >= Priority.values().length - 1)
				next = 2;
			// Check if there is any Visits with the next Priority
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
			nextPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(next))).get()
					.getRegistrationDateAndTime();
			currentPriorityTime = (visitDB.getNextVisit(Priority.getPriorityCode(current))).get()
					.getRegistrationDateAndTime();

			// The current priority patient registered BEFORE the next priority
			// patient
			if (currentPriorityTime.compareTo(nextPriorityTime) < 0)

			{
				aVisit = visitDB.getNextVisit(Priority.getPriorityCode(current));
				visitDB.remove(Priority.getPriorityCode(current));
				return aVisit;
			}
			// The current priority patient registered AFTER the next priority
			// patient
			else
			{
				// Increment 'current' to serve the next priority patients
				current = next;
				next = (current >= Priority.values().length-1) ? 2 : current + 1;
				aVisit = visitDB.getNextVisit(Priority.getPriorityCode(current));
				visitDB.remove(Priority.getPriorityCode(current));
				return aVisit;
			}
		// There is only 1 type of Priority left
		}else{
			aVisit = visitDB.getNextVisit(Priority.getPriorityCode(current));
			visitDB.remove(Priority.getPriorityCode(current));
			return aVisit;
		}
	}

}
