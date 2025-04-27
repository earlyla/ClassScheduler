package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;
/**
 * This class serves as a way to read in and write student records
 * 
 * @author Luke Early
 */
public class StudentRecordIO {

	/**
	 * Reads in Student records from the file represented by the given fileName. 
	 * 
	 * @param fileName the name of the file to read from
	 * @return studentDirectory A SortedList that contains student records
	 * @throws FileNotFoundException thrown if the file is not found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> studentDirectory = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				
				boolean duplicate = false;
				
				// verifies student not already in directory
				for (int i = 0; i < studentDirectory.size(); i++) {
					Student current = studentDirectory.get(i);
					
					if (student.getId().equals(current.getId())) {
						duplicate = true;
						break;
					}
				}
				// adds student if not duplicate
				if (!duplicate) {
					studentDirectory.add(student);
				}
			} catch (IllegalArgumentException e) {
				// do nothing
			}
		}
		
		fileReader.close();
		
		return studentDirectory;
	}

	/**
	 * This method writes student records from an SortedList to a text file
	 * 
	 * @param fileName the name of the file to write to
	 * @param studentDirectory the array list of all the student records
	 * @throws IOException thrown when student records cannot write to a file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		 
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		
		fileWriter.close();
	}
	
	/**
	 * Processes one line at a time from student records.
	 * 
	 * @param nextLine the next line in student records
	 * @return student to store in SortedList
	 * @throws IllegalArgumentException if any of the fields are invalid in Student
	 */
	private static Student processStudent(String nextLine) throws FileNotFoundException {
		Scanner scnr = new Scanner(nextLine);
		scnr.useDelimiter(",");
		
		Student student = null;
		
		try {
			String firstName = scnr.next();
			String lastName = scnr.next();
			String id = scnr.next();
			String email = scnr.next();
			String password = scnr.next();
			int credits = scnr.nextInt();
			
			if (scnr.hasNext() || scnr.hasNextInt()) {
				scnr.close();
				throw new IllegalArgumentException("Invalid student record");
			}
			
			if (credits >= 3) {
				scnr.close();
				student = new Student(firstName, lastName, id, email, password, credits);
				return student;		
			} else if (credits < 18) {
				scnr.close();
				student = new Student(firstName, lastName, id, email, password, credits);
				return student;
			} else {
				scnr.close();
				student = new Student(firstName, lastName, id, email, password);
				return student;
			}
			
		} catch (Exception e) {
			scnr.close();
			throw new IllegalArgumentException("Invalid student record", e);
		}	
	}
}
