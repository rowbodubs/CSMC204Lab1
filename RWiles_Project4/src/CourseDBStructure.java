//@Author Rowan Wiles

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {

	LinkedList<CourseDBElement>[] courses;
	
	public CourseDBStructure(int estimatedNum) {
		int length = ((int) (estimatedNum / 4.5)) * 3;
		if (length != (int) (estimatedNum / 1.5))
			length +=3;
		while (!isPrime(length)) {
			length += 4;
		}
		courses = new LinkedList[length];
	}
	
	public CourseDBStructure(String testing, int size) {
		if (testing.equals("Testing"))
			courses = new LinkedList[size];
	}

	@Override
	public void add(CourseDBElement element) {
		try {
			LinkedList<CourseDBElement> courseList = courses[hashCode(element.getCRN() + "")];
			
			for (int i = 0; i < courseList.size(); i++) {
				if (courseList.get(i).getCRN() == element.getCRN()) {
					courseList.remove(i);
				}
			}
			courses[hashCode(element.getCRN() + "")].add(element); 
		} catch (Exception e) {
			courses[hashCode(element.getCRN() + "")] = new LinkedList<CourseDBElement>();
			courses[hashCode(element.getCRN() + "")].add(element);
		}
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		try {
			LinkedList<CourseDBElement> courseList = courses[hashCode(crn + "")];
			for (int i = 0; i < courseList.size(); i ++) {
				if (courseList.get(i).getCRN() == crn) {
					return courseList.get(i);
				}
			}
			throw new IOException();
		} catch (Exception e) {
			throw new IOException();
		}
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0; i < courses.length; i ++) {
			try {
				for (int j = 0; j < courses[i].size(); j++) {
					output.add("\n" + courses[i].get(j).toString());
				}
			} catch (Exception e) {}
		}
		Collections.reverse(output);
		return output;
	}

	@Override
	public int getTableSize() {
		return courses.length;
	}
	
	//gotten from https://www.educative.io/answers/how-to-check-if-a-number-is-prime-in-java
	static boolean isPrime(int num)
    {
        if(num<=1)
        {
            return false;
        }
       for(int i=2;i<=num/2;i++)
       {
           if((num%i)==0)
               return  false;
       }
       return true;
    }
	
	int hashCode(String element) {
		if (element.length() == 0) {
			return 0;
		} else if (element.length() == 1) {
			return element.charAt(0) % courses.length;
		}
		return (element.charAt(0) * element.charAt(1)) % courses.length;
	}
}
