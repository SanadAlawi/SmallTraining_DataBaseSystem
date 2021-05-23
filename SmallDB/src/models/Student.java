package models;

import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;

public class Student {
	private int StudentID;
	private int TrainerID;
	private int Age;
	private String StudentName;
	private String NumberOfCourses;
	private Statement stmt;

	
	public Student(int studentID, int trainerID, int age, String studentName, String courseName) {
		super();
		StudentID = studentID;
		TrainerID = trainerID;
		StudentName = studentName;
		Age = age;
		NumberOfCourses = courseName;
	}
	
	
	
	public Student(int studentID, int trainerID, int age, String studentName) {
		super();
		StudentID = studentID;
		TrainerID = trainerID;
		StudentName = studentName;
		Age = age;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
			stmt.executeUpdate("update student set sname='"+studentName+"' where id='"+StudentID+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
		try {
			stmt = DatabaseConnection.getConeection().createStatement();
			stmt.executeUpdate("update student set age='"+age+"' where id='"+StudentID+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNumberOfCourses() {
		return NumberOfCourses;
	}

	public void setNumberOfCourses(String numberOfCourses) {
		NumberOfCourses = numberOfCourses;
	}

	public int getTrainerID() {
		return TrainerID;
	}
	
	
}
