package dw317.clinic.business.interfaces;

import java.io.Serializable;
import java.util.Optional;

/**
 * Interface that allows user to call next patient
 * depending on their patient calling algorithm.
 * 
 * @author Uen Yi Cindy Hung
 * @version 16/11/2015
 *
 */
public interface PriorityPolicy extends Serializable{
/**
* Returns the next visit of the next patient that must be examined.
*
* @return The visit of the next patient that must be examined.
*/
Optional<Visit> getNextVisit();

}
