import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//@Author Rowan Wiles
public class CourseDBManager_STUDENT_Test {
	
	CourseDBManager cdbm;
	
	@Before
	public void setUp() {
		cdbm = new CourseDBManager();
		cdbm.add("CMSC204", 11111, 3, "distance-learning", "Hulk Hogan");
	}
	
	@After
	public void tearDown() {
		cdbm = null;
	}
	
	@Test
	public void testAdd() {
		assertTrue(cdbm.get(11111).getID().equals("CMSC204"));
		
		cdbm.add("ARTS102", 22222, 3, "ToonVille", "Roger Rabbit");
		assertTrue(cdbm.get(22222).getID().equals("ARTS102"));
		cdbm.add("FORC206", 11333, 3, "The Death Star", "Emperor Palpatine");
		assertTrue(cdbm.get(11333).getID().equals("FORC206"));
		
		cdbm.add("GUNS233", 11111, 3, "MURICA!!!!", "Mr T.");
		assertTrue(cdbm.get(11111).getID().equals("GUNS233"));
	}

	@Test
	public void testGet() {
		assertTrue(cdbm.get(11111).getID().equals("CMSC204"));
		assertTrue(cdbm.get(11111).getCRN() == 11111);
		assertTrue(cdbm.get(11111).getCredits() == 3);
		assertTrue(cdbm.get(11111).getRoomNum().equals("distance-learning"));
		assertTrue(cdbm.get(11111).getInstructorName().equals("Hulk Hogan"));
	}

	@Test
	public void testReadFile() {
		try {
			File inputFile = new File("Test.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC204 11111 3 Distance-Learning Wile E. Coyote");
			inFile.print("FORC206 11333 3 DS666 Emperor \"The Senate\" Palpatine");
			
			inFile.close();
			cdbm.readFile(inputFile);
			assertEquals("CMSC204",cdbm.get(11111).getID());
			assertEquals("FORC206",cdbm.get(11333).getID());
			assertEquals("DS666",cdbm.get(11333).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}

	@Test
	public void testShowAll() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("\nCourse:ARTS102 CRN:22222 Credits:3 Instructor:Roger Rabbit Room:ToonVille");
		list.add("\nCourse:CMSC204 CRN:11111 Credits:3 Instructor:Hulk Hogan Room:distance-learning");
		list.add("\nCourse:FORC206 CRN:11333 Credits:3 Instructor:Emperor Palpatine Room:The Death Star");
		
		cdbm.add("ARTS102", 22222, 3, "ToonVille", "Roger Rabbit");
		assertTrue(cdbm.showAll().contains(list.get(0)));
		assertTrue(cdbm.showAll().contains(list.get(1)));
		assertFalse(cdbm.showAll().contains(list.get(2)));
		
		cdbm.add("FORC206", 11333, 3, "The Death Star", "Emperor Palpatine");
		assertTrue(cdbm.showAll().contains(list.get(0)));
		assertTrue(cdbm.showAll().contains(list.get(1)));
		assertTrue(cdbm.showAll().contains(list.get(2)));
	}

}
