package models;

import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;

public class Student_Courses {
	private int StudentID;
	private int CourseID;
	private Statement stmt;

	
	public Student_Courses(int studentID, int trainerID) {
		StudentID = studentID;
		CourseID = trainerID;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public int getCourseID() {
		return CourseID;
	}

	public void setCourseID(int courseID) {
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
			stmt.executeUpdate("update student_courses set cid='"+courseID+"' where sid='"+StudentID+"' AND cid='"+CourseID+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CourseID = courseID;
	}
}
