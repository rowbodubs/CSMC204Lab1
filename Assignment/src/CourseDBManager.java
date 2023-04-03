//@Author Rowan Wiles

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseDBManager implements CourseDBManagerInterface {

	CourseDBStructure courses;
	
	public CourseDBManager() {
		courses = new CourseDBStructure(300);
	}
	
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		courses.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return courses.get(crn);
		} catch (IOException e) {
			return new CourseDBElement(); //this should throw an exception but I can't because of the interface
		}
	}

	public void readFile(File input) throws FileNotFoundException {
		FileReader fileReader = new FileReader(input);
		
		String fileAsString = "";
		int character = 0;
		try {
			character = fileReader.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}
		while (character != -1) {
			
			fileAsString += (char) character;
			
			try {
				character = fileReader.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		
		String[] fileArrayUnSplit = fileAsString.split("\n");
		String[][] fileArraySplit = new String[fileArrayUnSplit.length][];
		
		for (int i = 0; i < fileArrayUnSplit.length; i++) {
			fileArraySplit[i] = fileArrayUnSplit[i].split(" ");
		}
		
		CourseDBElement newCourse = new CourseDBElement();
		int index = 0;
		String name = "";
		
		for (String[] course : fileArraySplit) {
			
			//check if course is actually a course
			if (course.length == 0 || course.length == 1) {
				continue;
			}
			if (course[0].charAt(0) == '#') {
				continue;
			}
			
			//check if current element in course is the course name
			if (course[index].length() == 7) {
				if (//check if the first 4 characters are capital letters as they should be in a course name
							course[index].charAt(0) >= 65 && course[index].charAt(0) <= 90 &&
							course[index].charAt(1) >= 65 && course[index].charAt(1) <= 90 &&
							course[index].charAt(2) >= 65 && course[index].charAt(2) <= 90 &&
							course[index].charAt(3) >= 65 && course[index].charAt(3) <= 90 &&
							//check if the last 3 are numbers
							course[index].charAt(4) >= 48 && course[index].charAt(4) <= 57 &&
							course[index].charAt(5) >= 48 && course[index].charAt(5) <= 57 &&
							course[index].charAt(6) >= 48 && course[index].charAt(6) <= 57
					) {
					newCourse.setID(course[index++]);
				}
			}
			
			//check if current element in course is the CRN
			if (course[index].length() == 5) {
				if (//make sure all the characters are numbers like in a CRN
						course[index].charAt(0) >= 48 && course[index].charAt(0) <= 57 &&
						course[index].charAt(1) >= 48 && course[index].charAt(1) <= 57 &&
						course[index].charAt(2) >= 48 && course[index].charAt(2) <= 57 &&
						course[index].charAt(3) >= 48 && course[index].charAt(3) <= 57 &&
						course[index].charAt(4) >= 48 && course[index].charAt(4) <= 57) {
					newCourse.setCRN(Integer.parseInt(course[index++]));;
				}
			}
			
			//check if current element in course is credits
			//this part of the program assumes that all classes have single digit credit hours
			if (course[index].length() == 1) {
				if (course[index].charAt(0) >= 48 && course[index].charAt(0) <= 57) {
					newCourse.setCredits(Integer.parseInt(course[index++]));
				}
			}
			
			//Check if the next part is the format
			//check if the format is distance learning
			//check if the current element is "distance"
			//this part of the program assumes there are no professors named Distance
			if (course[index].toLowerCase().equals("distance")) {
				index++;
				if (course[index].toLowerCase().equals("learning")) {
					index++;
				}
				newCourse.setRoomNum("Distance-Learning");
			}
			else if (course[index].toLowerCase().equals("distance-learning")) {
				newCourse.setRoomNum("Distance-Learning");
				index++;
			} //check if the next part is a room
			
			if (course[index].length() == 5 && 
					course[index].charAt(0) >= 65 && course[index].charAt(0) <= 90 &&
					course[index].charAt(1) >= 65 && course[index].charAt(1) <= 90 &&
					course[index].charAt(2) >= 48 && course[index].charAt(2) <= 57 &&
					course[index].charAt(3) >= 48 && course[index].charAt(3) <= 57 &&
					course[index].charAt(4) >= 48 && course[index].charAt(4) <= 57) {
				newCourse.setRoomNum(course[index++]);
			}
			
			
			//hopefully at this point in the program, the only elements left will be part of the professor's name
			for (index = index; index < course.length; index++) {
				name += course[index] + " ";
			}
			newCourse.setInstructorName(name);
			
			//reset for the next run
			name = "";
			//System.out.println(newCourse.toString());
			courses.add(newCourse);
			newCourse = new CourseDBElement();
			index = 0;
		}
	}

	@Override
	public ArrayList<String> showAll() {
		return courses.showAll();
	}

}
