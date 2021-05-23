package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Courses;
import models.Student_Courses;

public class Student_CourseService {
	private static Statement stmt;
	private static Student_CourseService student_courseService;
	private static ObservableList<Student_Courses> list;
	
	private Student_CourseService() throws SQLException {
		stmt = DatabaseConnection.getConeection().createStatement();
		list = FXCollections.observableArrayList();
	}
	
	public static Student_CourseService getService() throws SQLException {
		if(student_courseService == null)
			student_courseService = new Student_CourseService();
		return student_courseService;
	}
	
	public ObservableList<Student_Courses> getAllStudent_Courses() throws SQLException{
		list.clear();
		ResultSet rs = stmt.executeQuery("select * from student_courses");
		while(rs.next()) 
			list.add(new Student_Courses(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2))));
		
		return list;
	}
	
	public void Insert(String sid, String cid) throws SQLException {
		String sql = "insert into student_courses values ( "+Integer.parseInt(sid)+", "+Integer.parseInt(cid)+")";
		stmt.executeUpdate(sql);
	}
	
	public String Search(String id) throws SQLException {
		String result = id+": Not Found";
		String sql = "select * from student_courses where sid="+id;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
			result = "Studnet ID = "+rs.getString(1);
		return result;
	}
	
	public String Delete(String id) throws SQLException {
		String result = Search(id);
		String sql = "delete from student_courses where sid="+id;
		if(!result.contains("Not Found")) {
			try {
				stmt.execute(sql);
				result +=" has Deleted";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
