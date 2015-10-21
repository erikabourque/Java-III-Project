/**
 * Defines a VisitSorter type.
 */
package group1.clinic.business;

import java.util.Comparator;
import dw317.clinic.business.interfaces.Visit;

/**
 * This class represents a Visit Sorter.
 * Visits are sorted first by priority, and then by date.
 * 
 * @author Erika
 * @version 10/17/2015
 */
public class VisitSorter implements Comparator<Visit> {

	/**
	 * Overrides the compare method.
	 * 
	 * @param v1	The first visit.
	 * @param v2	The second visit. 
	 */
	@Override
	public int compare(Visit v1, Visit v2) {
		if (v1.equals(v2))
			return 0;
		if (v1.getPriority() != v2.getPriority())
			return v1.getPriority().compareTo(v2.getPriority());
		//if same priority, order by date
		return v1.compareTo(v2);
	}
}