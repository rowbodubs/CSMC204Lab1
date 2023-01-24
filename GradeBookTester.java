import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
	private GradeBook gradeBook1;
	private GradeBook gradeBook2;

	@BeforeEach
	void setUp() throws Exception {
		gradeBook1 = new GradeBook(5);
		gradeBook2 = new GradeBook(6);
		gradeBook1.addScore(93);
		gradeBook1.addScore(90);
		gradeBook1.addScore(89);
		gradeBook2.addScore(3);
		gradeBook2.addScore(55);
	}

	@AfterEach
	void tearDown() throws Exception {
		gradeBook1 = null;
		gradeBook2 = null;
	}
	@Test
	void testToString() {
		assertTrue("93.0 90.0 89.0".equals(gradeBook1.toString()));
		assertTrue("3.0 55.0".equals(gradeBook2.toString()));
	}
	@Test
	void testAddScore() {
		gradeBook1.addScore(87);
		gradeBook2.addScore(34);
		assertTrue("93.0 90.0 89.0 87.0".equals(gradeBook1.toString()));
		assertTrue("3.0 55.0 34.0".equals(gradeBook2.toString()));
		assertEquals(4,gradeBook1.getScoreSize(),.0001);
		assertEquals(3,gradeBook2.getScoreSize(),.0001);
	}

	@Test
	void testSum() {
		assertEquals(272, gradeBook1.sum(), .0001);
		assertEquals(58, gradeBook2.sum(), .0001);
	}

	@Test
	void testMinimum() {
		assertEquals(89, gradeBook1.minimum(), .0001);
		assertEquals(3, gradeBook2.minimum(), .0001);
	}

	@Test
	void testFinalScore() {
		assertEquals(183, gradeBook1.finalScore(), .0001);
		assertEquals(55, gradeBook2.finalScore(), .0001);
	}

	@Test
	void testGetScoreSize() {
		assertEquals(3, gradeBook1.getScoreSize());
		assertEquals(2, gradeBook2.getScoreSize());
	}

	

}
