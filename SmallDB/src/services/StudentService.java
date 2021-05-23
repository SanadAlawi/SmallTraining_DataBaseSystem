package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Courses;
import models.Student;

public class StudentService {
	private static Statement stmt;
	private static StudentService studentService;
	private static ObservableList<Student> list;
	
	private StudentService() throws SQLException {
		stmt = DatabaseConnection.getConeection().createStatement();
		list = FXCollections.observableArrayList();
	}
	
	public static StudentService getService() throws SQLException {
		if(studentService == null)
			studentService = new StudentService();
		return studentService;
	}
	
	public ObservableList<Student> getAllStudent() throws SQLException{
		list.clear();
		ResultSet rs = stmt.executeQuery("select * from student");
		int count = 0;
		while(rs.next()) { 
			count++;
			list.add(new Student(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), Integer.parseInt(rs.getString(3)), rs.getString(4)));
		}
		rs.close();
		while(count > 0) {
			getNumberCourse(list.get(--count).getStudentID(), count);
		}
		
		return list;
	}
	
	private void getNumberCourse(int id, int index) throws SQLException {
		String sql = "select count(s.sid) from student_courses s  where s.sid = '"+id+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			String number = rs.getString(1);
			list.get(index).setNumberOfCourses(number);
		}
		rs.close();
	}
	
	public void Insert(String name, String age) throws SQLException {
		String sql = "insert into student values (null, 1, '"+age+"', '"+name+"')";
		stmt.executeUpdate(sql);
	}
	
	public String Search(String id) throws SQLException {
		String result = id+": Not Found";
		String sql = "select * from student where id="+id;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
			result = "Student Name = "+rs.getString(4);
		return result;
	}
	
	public String Delete(String id) throws SQLException {
		String result = Search(id);
		String sql = "delete from student where id="+id;
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
