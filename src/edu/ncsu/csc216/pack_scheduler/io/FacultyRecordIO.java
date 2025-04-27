package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * reads in and prints faculty record to a txt file
 */
public class FacultyRecordIO {
	
	/**
	 * reads in a .txt file to a LinkedList of faculty
	 * @param fileName name of file to read in
	 * @return LinkedList of Faculty objects
	 * @throws FileNotFoundException if file is not able to be loaded
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyDirectory = new LinkedList<Faculty>();
		while (fileReader.hasNext()) {
			try {
				Faculty fac = processFaculty(fileReader.nextLine());
				
				facultyDirectory.add(facultyDirectory.size(), fac);
				
			} catch (IllegalArgumentException e){
				// throw IAE
			}
			
		}
		
		return facultyDirectory;
	}
	
	/**
	 * Parses a string and constructs a faculty object
	 * @param facultyString string containing all info needed to construct Faculty object
	 * @return Faculty object
	 */
	private static Faculty processFaculty(String facultyString) {
		Scanner scnr = new Scanner(facultyString);
		scnr.useDelimiter(",");
		
		Faculty fac = null;
		
		try {
			String firstName = scnr.next();
			String lastName = scnr.next();
			String id = scnr.next();
			String email = scnr.next();
			String password = scnr.next();
			int maxCourses = scnr.nextInt();
			
			if (scnr.hasNext() || scnr.hasNextInt()) {
				scnr.close();
				throw new IllegalArgumentException("Invalid faculty record");
			}
			
			scnr.close();
			fac = new Faculty(firstName, lastName, id, email, password, maxCourses);
			
		} catch (Exception e) {
			scnr.close();
			throw new IllegalArgumentException("Invalid faculty record", e);
		}
		
		return fac;
	}
	
	/**
	 * Writes faculty directory to a test file
	 * 
	 * @param fileName name of file to write directory to
	 * @param facultyDirectory directory to write to a file
	 * @throws IOException if there is an issue writing to file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		 
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (i == facultyDirectory.size() - 1) {
				fileWriter.print(facultyDirectory.get(i).toString());
			} else {
				fileWriter.println(facultyDirectory.get(i).toString());
			}
		}
		
		fileWriter.close();
	}
 
}
