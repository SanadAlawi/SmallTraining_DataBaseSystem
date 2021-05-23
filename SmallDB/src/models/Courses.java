package models;

import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;

public class Courses {
	private int CourseID;
	private int TrainerID;
	private String CourseName;
	private Statement stmt;
	
	public Courses(int courseID, int trainerID, String courseName) {
		CourseID = courseID;
		TrainerID = trainerID;
		CourseName = courseName;
	}

	public int getCourseID() {
		return CourseID;
	}

	public void setCourseID(int courseID) {
		CourseID = courseID;
	}

	public int getTrainerID() {
		return TrainerID;
	}

	public void setTrainerID(int trainerID) {
		TrainerID = trainerID;
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
			stmt.executeUpdate("update courses set tid='"+TrainerID+"' where id='"+CourseID+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
			stmt.executeUpdate("update courses set cname='"+CourseName+"' where id='"+CourseID+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
