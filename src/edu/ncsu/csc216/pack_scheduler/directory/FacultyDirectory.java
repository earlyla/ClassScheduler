package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * FacultyDirectory class
 */
public class FacultyDirectory {
	/** hashing algorithm constant */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** LinkedList for the faculty directory */
	private LinkedList<Faculty> facultyDirectory; 
	
	/**
	 * Constructs a FacultyDirectory object.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * creates a new LinkedList of Faculty objects.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Reads in a file, passed as a fileName string param, and populates facultyDirectory
	 * 	
	 * @param fileName name of file to read in
	 * @throws IllegalArgumentException if unable to read the file
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds faculty to directory if faculty not already in directory
	 * 
	 * @param firstName fn of faculty
	 * @param lastName ln of faculty
	 * @param id of faculty
	 * @param email of faculty
	 * @param password of faculty
	 * @param repeatPassword of faculty
	 * @param maxCourses faculty mem can teach
	 * @return true if able to add, false otherwise
	 * @throws IllegalArgumentException if password is invalid, passwords do not match
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		Faculty facultyToAdd = null;
		
		try {
			facultyToAdd = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		
		if (facultyDirectory.contains(facultyToAdd)) {
			return false;
		} else {
			facultyDirectory.add(facultyDirectory.size(), facultyToAdd);
			return true;
		}
		
	}
	
	/**
	 * removes fac from the directory
	 * 
	 * @param id of faculty to remove
	 * @return true if removed, false else
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns a 2D array of faculty directory where each column is one faculty, row 0 is first name
	 * row 1 is last name, row 2 is id
	 * 
	 * @return 2D String array with all the above info
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		return directory;
	}
	
	/**
	 * Writes facultyDirectory to the specified file.
	 * 
	 * @param fileName name of file to save facultyDirectory to.
	 * @throws IllegalArgumentException if unable to write to fileName
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/**
	 * This method searches for a faculty objects ID and returns that faculty if in the directory
	 * 
	 * @param id String ID of the faculty to search for
	 * @return Faculty with the matching ID
	 */
	public Faculty getFacultyById(String id) {
		Faculty foundFaculty = null;
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty currFaculty = facultyDirectory.get(i);
			if (currFaculty.getId().equals(id)) {
				foundFaculty = facultyDirectory.get(i);
			}
		}
		
		return foundFaculty;
	}
	
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if there is no hash algorithm
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
}
