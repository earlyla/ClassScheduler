package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class allows for file processing. 
 * Provides the following static methods:
 * 		readCourseRecords(String) - takes a string representing a file name and 
 * 									parses through that file appending each entry to
 * 									an SortedList (returns SortedList)
 * 		
 * 		writeCourseRecords(String, SortedList) - takes a filename and an SortedList, 
 * 														writes contents of SortedList to a new 
 * 														file 
 * 		
 * 		readCourse(String) - takes a string representing a single line from a file and parses
 * 							 all course information from that line. (returns Course object)
 * 
 * @author Luke Early
 */
public class CourseRecordIO {

	/**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    SortedList<Course> courses = new SortedList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While  we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the SortedList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the SortedList with all the courses we read!
	    return courses;
	}

	/**
	 * This helper method parses all of the information in a course
	 * @param nextLine string representing the next line to be parsed
	 * @return course object parsed from String nextLine
	 * @throws IllegalArgumentException "Bad course." if:
	 * 									- meetingDays is "A" but there is more data in the course
	 * 									- there is more data to parse after endTime
	 * 									- the String cannot be read for any reason
	 */
	private static Course readCourse(String nextLine) {
		Scanner scnr = new Scanner(nextLine);
		scnr.useDelimiter(",");
		
		Course newCourse = null;
		
		try {
			String courseName = scnr.next();
			String title = scnr.next();
			String section = scnr.next();
			int credits = scnr.nextInt();
			String instructorId = scnr.next();
			int enrollmentCap = scnr.nextInt();
			String meetingDays = scnr.next();
			
			if ("A".equals(meetingDays)) {
				if (scnr.hasNext()) {
					scnr.close();
					throw new IllegalArgumentException("Bad course.");
				}
				else {
					scnr.close();
				 
					newCourse = new Course(courseName, title, section, credits, null, enrollmentCap, meetingDays);
										
					return newCourse;
				}
			} else {		
				int startTime = scnr.nextInt();
				int endTime = scnr.nextInt();
				
				if (scnr.hasNext()) {
					scnr.close();
					throw new IllegalArgumentException("Bad course.");
				}
				
				newCourse = new Course(courseName, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
				scnr.close();
			}
			
			try {
				if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(newCourse)) {
					newCourse.setInstructorId(instructorId);
				}
				
			} catch (Exception e) {
				// do nothing
			}
				 
		} catch (Exception e) {
			scnr.close();
			throw new IllegalArgumentException("Bad course.", e);
		}
		
		scnr.close();
	    return newCourse;
	}

	/**
     * Writes the given list of Courses to 
     * @param fileName file to write schedule of Courses to
     * @param courses list of Courses to write
     * @throws IOException if cannot write to file
     */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
		
	}

}
