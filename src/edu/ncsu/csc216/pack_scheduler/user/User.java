package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The abstract class representing a User. This class maintains the common state and behavior shared between
 * a Registrar object and a Student object.
 * 
 * @author Luke Early
 */
public abstract class User {

	/** first name of the user */
	private String firstName;
	/** last name of the user */
	private String lastName;
	/** unique user ID */
	private String id;
	/** email of the user */
	private String email;
	/** unique user password */
	private String password;

	/**
	 * Constructor for User object
	 * 
	 * @param firstName first name of user.
	 * @param lastName last name of user
	 * @param id unique id of user
	 * @param email email of user
	 * @param password hashed PW of user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}
	
	/**
	 * Sets the users first name
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		
		this.firstName = firstName;
	}

	/**
	 * Getter for users first name
	 * 
	 * @return user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets user's last name
	 * 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the lastName field
	 * 
	 * @return users last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the user's id.
	 *  
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty
	 */
	protected void setId(String id) {
		// TODO figure out how to test this
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		this.id = id;
	}

	/**
	 * Returns the id field.
	 * 
	 * @return users unique ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the user's email
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is null or empty, doesn't have an '@', '.',
	 *    								or the final '.' exists prior to the '@'.
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		
		int dotIndex = 0;
		int atIndex = 0;
		
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				atIndex = i;
			} else if (email.charAt(i) == '.') {
				dotIndex = i;
			}
		}
		
		if (atIndex == 0 || dotIndex == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		
		if (dotIndex < atIndex) {
			throw new IllegalArgumentException("Invalid email");
		}
		
		this.email = email;
	}

	/**
	 * Returns the email field.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or empty
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		this.password = password;
	}

	/**
	 * Returns the password field
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Generates hash code for User Object.
	 * 
	 * @return result an integer representing the hashCode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Returns true if object compared to is the same as object being compared, false if not.
	 * 
	 * @return true if objects are the same, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	
}