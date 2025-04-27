package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

class FacultyRecordIOTest {
	/** Valid faculty directory */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** LinkedList to test with */
	private LinkedList<Faculty> faculty = null;
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());
			
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading file");
		}
	}

	/**
	 * Test valid faculty records
	 */
	@Test
	public void testReadFacultyRecordsValid() {
		try {
			LinkedList<Faculty> facReader = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, facReader.size());
			
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading file");
		}
	}
	
	/**
	 * Tests writeStudentRecords() method
	 */
	@Test
	public void testWriteStudentRecordsValid() {
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
			checkFiles(validTestFile, "test-files/actual_faculty_records.txt");
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
	}

	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
