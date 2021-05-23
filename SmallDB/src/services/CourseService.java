package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Courses;

public class CourseService {
	private static Statement stmt;
	private static CourseService courseService;
	private static ObservableList<Courses> list;
	
	private CourseService() throws SQLException {
		stmt = DatabaseConnection.getConeection().createStatement();
		list = FXCollections.observableArrayList();
	}
	
	public static CourseService getService() throws SQLException {
		if(courseService == null)
			courseService = new CourseService();
		return courseService;
	}
	
	public ObservableList<Courses> getAllCourses() throws SQLException{
		list.clear();
		ResultSet rs = stmt.executeQuery("select * from courses");
		while(rs.next()) 
			list.add(new Courses(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), rs.getString(3)));
		
		return list;
	}
	
	public void Insert(String name) throws SQLException {
		String sql = "insert into courses values (null, '1', '"+name+"')";
		stmt.executeUpdate(sql);
	}
	
	public String Search(String id) throws SQLException {
		String result = id+": Not Found";
		String sql = "select * from courses where id="+id;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
			result = "Course Name = "+rs.getString(3);
		return result;
	}
	
	public String Delete(String id) throws SQLException {
		String result = Search(id);
		String sql = "delete from courses where id="+id;
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
