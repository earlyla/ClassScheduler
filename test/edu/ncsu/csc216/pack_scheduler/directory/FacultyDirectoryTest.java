package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

class FacultyDirectoryTest {
	/** test directory */
	FacultyDirectory fd = new FacultyDirectory();
	/** Valid faculty directory file */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** save file to test */
	private String validSaveFile = "test-files/test_save_fac-dir.txt";
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			fd.loadFacultyFromFile(validTestFile);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFacDirConstr() {
		FacultyDirectory testFD = new FacultyDirectory();
		
		try {
			testFD.loadFacultyFromFile(validTestFile);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertThrows(IllegalArgumentException.class, 
				() -> fd.loadFacultyFromFile(""));
		
		String [][] facDir = testFD.getFacultyDirectory();
		
		assertEquals(facDir[0][0], "Ashely");
	}
	
	@Test
	void testAdd() {
		fd.addFaculty("fn", "ln", "id", "f@n.edu", "pw", "pw", 2);
		
		String [][] facDir = fd.getFacultyDirectory();
		
		assertEquals(facDir[8][0], "fn");
		assertFalse(fd.addFaculty("fn", "ln", "id", "f@n.edu", "pw", "pw", 2));
		
		assertThrows(IllegalArgumentException.class, 
				() -> fd.addFaculty("fn", "ln", "unqId", "f@n.edu", "pw", "pw2", 2));
		assertThrows(IllegalArgumentException.class, 
				() -> fd.addFaculty("fn", "ln", "unqId", "f@n.edu", null, "pw2", 2));
		assertThrows(IllegalArgumentException.class, 
				() -> fd.addFaculty("fn", "ln", "unqId", "f@n.edu", "pw", null, 2));
		assertThrows(IllegalArgumentException.class, 
				() -> fd.addFaculty("fn", "ln", "unqId", "f@n.edu", "", "pw2", 2));
		assertThrows(IllegalArgumentException.class, 
				() -> fd.addFaculty("fn", "ln", "unqId", "f@n.edu", "pw", "", 2));
	}
	
	@Test
	void testRemove() {
		fd.addFaculty("fn", "ln", "id", "f@n.edu", "pw", "pw", 2);
		
		String [][] facDir = fd.getFacultyDirectory();
		
		assertEquals(facDir[8][0], "fn");
		assertTrue(fd.removeFaculty("id"));
		
		String [][] facDir2 = fd.getFacultyDirectory();
		
		assertEquals(8, facDir2.length);
		
		assertFalse(fd.removeFaculty("fakeId"));
	}
	
	@Test
	void testSaveFacultyDirectory() {
		fd.saveFacultyDirectory(validSaveFile);
		
		assertThrows(IllegalArgumentException.class, 
				() -> fd.saveFacultyDirectory(""));
	}
	
	@Test
	void testgetFacultyByStringId() {
		Faculty facToFind = fd.getFacultyById("haguirr");
		
		assertEquals(facToFind.getId(), "haguirr");
	}

}