//@Author Rowan Wiles

public class CourseDBElement implements Comparable<CourseDBElement> {
	public String courseID, roomNum, instructorName;
	public int CRN, credits;
	
	public CourseDBElement(String courseID, int CRN, int credits, String roomNumber, String instructorName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.credits = credits;
		this.roomNum = roomNumber;
		this.instructorName = instructorName;
	}
	
	public CourseDBElement() {
		
	}
	
	public String getID() {
		return courseID;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public int getCRN() {
		return CRN;
	}
	public int getCredits() {
		return credits;
	}
	
	
	public void setID(String courseID) {
		this.courseID = courseID;
	}
	public void setRoomNum(String roomNumber) {
		this.roomNum = roomNumber;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int compareTo(CourseDBElement comparee) {
		return this.CRN - comparee.CRN;
	}
	
	public String toString() {
		String output = "";
		
		if (courseID != null) {
			output += "Course:" + this.courseID + " ";
		}
		try {
			output += "CRN:" + this.CRN + " ";
		} catch (Exception e) {}
		try {
			output += "Credits:" + this.credits + " ";
		} catch (Exception e) {}
		if (instructorName != null) {
			output += "Instructor:" + this.instructorName + " ";
		}
		if (roomNum != null) {
			output += "Room:" + this.roomNum;
		}
		
		return output;
	}
}
