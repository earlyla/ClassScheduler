package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests CourseNameValidatorFSM
 */
class CourseNameValidatorTest {

	/** CourseNameValidatorFSM Object used for testing throughout */
	CourseNameValidator courseNameValidator;
	
	// All valid test Strings
	/** Valid course name with LDDD format */
	private static final String VALID_L_DDD = "C216";
	/** Valid course name with LLDDD format */
	private static final String VALID_LL_DDD = "CS216";
	/** Valid course name with LLLDDD format */
	private static final String VALID_LLL_DDD = "CSC216";
	/** Valid course name with LLLLDDD format */
	private static final String VALID_LLLL_DDD = "CSCA116";
	
	/** Valid course name with LDDDS format */
	private static final String VALID_L_DDD_S = "C216a";
	/** Valid course name with LLDDDS format */
	private static final String VALID_LL_DDD_S = "CS216a";
	/** Valid course name with LLLDDDS format */
	private static final String VALID_LLL_DDD_S = "ABC123s";
	/** Valid course name with LLLLDDDS format */
	private static final String VALID_LLLL_DDD_S = "ABCD123s";
	
	
	// All invalid test Strings
	/** Invalid course name with L */
	private static final String INVALID_L = "C";
	/** Invalid course name with D */
	private static final String INVALID_D = "1";
	/** Invalid course name with LD */
	private static final String INVALID_LD = "A1";
	/** Invalid course name with LDS */
	private static final String INVALID_LDS = "A1S";
	/** Invalid course name with LLD */
	private static final String INVALID_LLD = "AB1";
	/** Invalid course name with LLDS */
	private static final String INVALID_LLDS = "AB1S";
	/** Invalid course name with LLDDS */
	private static final String INVALID_LLDDS = "AB12A";
	/** Invalid course name with LLLDDS*/
	private static final String INVALID_LLLDDS = "ABC12A";
	/** Invalid course name with LLLLDDDSD*/
	private static final String INVALID_LLLLDDDSD = "ABCD123A1";
	/** Invalid course name with non-alphanumeric characters */
	private static final String INVALID_PUNCTUATION = "!ABC";
	/** Invalid course name with 5 leading letters */
	private static final String INVALID_LLLLL = "ABCDE";
	/** Invalid course name with 4 digits */
	private static final String INVALID_LLLDDDD = "ABC1234";
	/** Invalid course name with 2 suffixes */
	private static final String INVALID_LLLLDDDSS = "ABCD123AA";
	
	@BeforeEach
	void setUp() {
		courseNameValidator = new CourseNameValidator();
	}
	
	/**
	 * Tests known good course names:
	 * 		LLLDDD, LLLDDD
	 */
	@Test
	void testIsValidMultipleValid() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LLL_DDD));
			assertTrue(courseNameValidator.isValid(VALID_LLL_DDD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LDDD
	 */
	@Test
	void testIsValidLDDD() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_L_DDD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LLDDD
	 */
	@Test
	void testIsValidLLDDD() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LL_DDD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LLLDDD
	 */
	@Test
	void testIsValidLLLDDD() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LLL_DDD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LLLLDDD
	 */
	@Test
	void testIsValidLLLLDDD() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LLLL_DDD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LDDDS
	 */
	@Test
	void testIsValidLDDDS() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_L_DDD_S));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LLDDDS
	 */
	@Test
	void testIsValidLLDDDS() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LL_DDD_S));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LLLDDDs
	 */
	@Test
	void testIsValidLLLDDDS() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LLL_DDD_S));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests known good course names:
	 * 		LLLLDDDs
	 */
	@Test
	void testIsValidLLLLDDDS() {
		try {
			assertTrue(courseNameValidator.isValid(VALID_LLLL_DDD_S));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests invalid course name:
	 * 		L
	 */
	@Test
	void testIsValidBadCourseNameL() {
		try {
			assertFalse(courseNameValidator.isValid(INVALID_L));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests invalid course name:
	 * 		D
	 */
	@Test
	void testIsValidBadCourseNameD() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
						() -> courseNameValidator.isValid(INVALID_D));
		
		assertEquals("Course name must start with a letter.", e1.getMessage());
	}
	
	/**
	 * Tests invalid course name:
	 * 		!
	 */
	@Test
	void testIsValidBadCourseNamePunctuation() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
						() -> courseNameValidator.isValid(INVALID_PUNCTUATION));
		
		assertEquals("Course name can only contain letters and digits.", e1.getMessage());

	}
	
	/**
	 * Tests invalid course name:
	 * 		LD
	 */
	@Test
	void testIsValidBadCourseNameLD() {
		try {	
			assertFalse(courseNameValidator.isValid(INVALID_LD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests invalid course name:
	 * 		LDS
	 */
	@Test
	void testIsValidBadCourseNameLDS() {
		Exception e1 = assertThrows(InvalidTransitionException.class,
										() -> courseNameValidator.isValid(INVALID_LDS));
		
		assertEquals(e1.getMessage(), "Course name must have 3 digits.");
	}
	
	/**
	 * Tests invalid course name:
	 * 		LLD
	 */
	@Test
	void testIsValidBadCourseNameLLD() {
		try {
			assertFalse(courseNameValidator.isValid(INVALID_LLD));
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Tests invalid course name:
	 * 		LLDS
	 */
	@Test
	void testIsValidBadCourseNameLLDS() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLDS));

		assertEquals("Course name must have 3 digits.", e1.getMessage());

	}
	
	/**
	 * Tests invalid course name:
	 * 		LLDDS
	 */
	@Test
	void testIsValidBadCourseNameLLDDS() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLDDS));

		assertEquals("Course name must have 3 digits.", e1.getMessage());

	}
	
	/**
	 * Tests invalid course name:
	 * 		LLLDDS
	 */
	@Test
	void testIsValidBadCourseNameLLLDDS() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLLDDS));

		assertEquals("Course name must have 3 digits.", e1.getMessage());

	}
	
	/**
	 * Tests invalid course name:
	 * 		LLLDDDD
	 */
	@Test
	void testIsValidBadCourseNameLLLDDDD() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLLDDDD));

		assertEquals("Course name can only have 3 digits.", e1.getMessage());

	}
	
	/**
	 * Tests invalid course name:
	 * 		LLLLDDDSD
	 */
	@Test
	void testIsValidBadCourseNameLLLLDDDSD() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLLLDDDSD));

		assertEquals("Course name cannot contain digits after the suffix.", e1.getMessage());
	}
	
	/**
	 * Tests invalid course name:
	 * 		LLLLDDDSS
	 */
	@Test
	void testIsValidBadCourseNameLLLLDDDSS() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLLLDDDSS));

		assertEquals("Course name can only have a 1 letter suffix.", e1.getMessage());

	}
	
	/**
	 * Tests invalid course name:
	 * 		LLLLL
	 */
	@Test
	void testIsValidBadCourseNameLLLLL() {
		Exception e1 = assertThrows(InvalidTransitionException.class, 
				() -> courseNameValidator.isValid(INVALID_LLLLL));

		assertEquals("Course name cannot start with more than 4 letters.", e1.getMessage());

	}
	
}
