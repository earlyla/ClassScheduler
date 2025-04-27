package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface used to check Activity objects (Course or Event) for overlapping time frames.
 * @author Luke Early
 */
public interface Conflict {
	/**
	 * Abstract method to check if Activity passed as a parameter conflicts with another on the schedule
	 * 
	 * @param possibleConflictingActivity Activity (Event or Course) to check against current schedule for
	 * 									  conflict.
	 * @throws ConflictException thrown when an Activity (Event or Course) is scheduled to occur at anytime 
	 * 							 where another event is occurring.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
