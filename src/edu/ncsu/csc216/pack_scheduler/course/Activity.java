package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The abstract superclass for Course and Event
 * 
 * Fields include title, meetingDays, startTime, endTime
 * 
 * Class constants include UPPER_ and LOWER_HOUR and the conversion factor for 24hr 
 * to 12hr time, MIL_CIV_CONVERT
 * 
 * Includes getters / setters for all state
 * 
 * hashCode and equals overridden
 * 
 * getShort and getLongDisplayArray abstract methods for GUI
 * 
 * isDuplicate abstract method to verify if an activity is a duplicate
 * 
 * @author Luke Early
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** latest time for course */
	public static final int UPPER_HOUR = 2359;
	/** earliest time for course */
	public static final int LOWER_HOUR = 0000;
	/** conversion factor for military hour to civilian hour */
	public static final int MIL_CIV_CONVERT = 12;

	/**
	 * Constructor for Course or event.
	 * @param title title of activity
	 * @param meetingDays meeting days of activity
	 * @param startTime start time of activity
	 * @param endTime end time of activity
	 * @throws IllegalArgumentException if meetingDays is null or empty
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		if (meetingDays == null || meetingDays.length() == 0) {
        	throw new IllegalArgumentException("Invalid meeting days and times.");
        }
		setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the courses title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the courses title.
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;	
	
	}

	/**
	 * Returns the courses meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the courses start time.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the courses end time.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the courses meeting days and time.
	 * @param meetingDays the course meeting days
	 * @param startTime the start time to set
	 * @param endTime the endTime to set
	 * @throws IllegalArgumentException if meetingDays is null or empty
	 * 									if endTime is smaller than startTime
	 * 									if startHour / endHour is less than 0 or greater than 23
	 * 									if startMin / endMin is less than 0 or greater than 59
	 *
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		
		if (startHour < 00 || startHour > 23) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (startMin < 00 || startMin > 59) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (endHour < 00 || endHour > 23) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (endMin < 00 || endMin > 59) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;				
	}

	/**
	 * This method prints date and time information about a course.
	 * @return meeting string representing the date and time of each course.
	 */
	public String getMeetingString() {
		String meeting = "";
		
		if ("A".equals(getMeetingDays())) {
			meeting = "Arranged";
		} else {
			meeting = meetingDays + " " + getTimeString(getStartTime()) + "-" + getTimeString(getEndTime());
		}
		
		return meeting;
	}

	/**
	 * Converts int 24hr time to String 12hr time
	 * @param time 24hr time to be converted to standard time
	 * @return standardTime the time that a 12hr digital clock would display
	 */
	private String getTimeString(int time) {
		String standardTime = "";
		String amPm = "AM";
		
		int hour = time / 100;
		
		if (hour == MIL_CIV_CONVERT) {
			amPm = "PM";
		}
		
		if (hour > MIL_CIV_CONVERT) {
			hour = hour - MIL_CIV_CONVERT;
			amPm = "PM";
		}
		
		int minute = time % 100;
		
		if (minute == 0) {
			standardTime = hour + ":" + minute + "0" + amPm;
		} else {
			standardTime = hour + ":" + minute + amPm;
		}		
		
		return standardTime;
	}
	
	/**
	 * Provides short version of array of information to the GUI
	 * @return String[] String array for Course name, section, title, and meeting string
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Provides long version of array of information to the GUI
	 * @return String[] String array for Course name, section, title, credits, 
	 * 					instructorId, meeting string, empty string, 
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * This method determines if an activity is a duplicate of another.
	 * @param activity the activity that will be compared
	 * @return boolean true if a duplicate is found, false if not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Compares current instance of Activity with possibleConflictingActivity.
	 * 
	 * If at least one day is overlapping and on that day at least one minute is overlapping, ConflictException
	 * is thrown. 
	 * 
	 * Else, nothing happens and the rest of the code following checkConflict() is executed.
	 * 
	 * @param possibleConflictingActivity Activity object to check for overlap.
	 * @throws ConflictException if activities overlap
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// check if overlapping days
		Boolean meetingDayConflict = false;
		
		char[] currentMeetingDays = this.getMeetingDays().toCharArray();
		char[] possibleConflictMeetingDays = possibleConflictingActivity.getMeetingDays().toCharArray();
		
		for (int i = 0; i < possibleConflictMeetingDays.length; i++) {
			for (int j = 0; j < currentMeetingDays.length; j++) {
				if (currentMeetingDays[j] == possibleConflictMeetingDays[i]) {
					meetingDayConflict = true;
				}
			}
		}
		
		if (currentMeetingDays[0] == 'A' && possibleConflictMeetingDays[0] == 'A') {
			meetingDayConflict = false;
		}
		
		// check if overlapping times
		int currentMeetingStartTime = this.getStartTime();
		int currentMeetingEndTime = this.getEndTime();
		
		int possibleConflictStartTime = possibleConflictingActivity.getStartTime();
		int possibleConflictEndTime = possibleConflictingActivity.getEndTime();
		
		// check if overlapping times
		if (meetingDayConflict) {
			if (possibleConflictStartTime >= currentMeetingStartTime && possibleConflictStartTime <= currentMeetingEndTime ||
			   possibleConflictEndTime >= currentMeetingStartTime && possibleConflictEndTime <= currentMeetingEndTime) {
				throw new ConflictException();
			} else if (possibleConflictStartTime >= currentMeetingStartTime && possibleConflictEndTime <= currentMeetingEndTime ||
					  currentMeetingStartTime >= possibleConflictStartTime && currentMeetingEndTime <= possibleConflictEndTime) {
				throw new ConflictException();
			}
		}
	}
	
	/**
	 * generates the hashCode for an activity object based on endTime, meetingDays, startTime and title.
	 * 
	 * @return result int representing the hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Method returns true if object comparing to obj is the same, and false if not.
	 * 
	 * @return true if objects are the same, false if they are different
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}