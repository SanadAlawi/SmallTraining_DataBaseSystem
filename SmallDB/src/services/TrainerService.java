package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configurations.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Trainer;

public class TrainerService {
	private static TrainerService trainerService;
	private static Statement stmt;
	private static ObservableList<Trainer> list;
	
	private TrainerService() throws SQLException {
		stmt = DatabaseConnection.getConeection().createStatement();
		list = FXCollections.observableArrayList();
	}
	
	public static TrainerService getService() throws SQLException {
		if(trainerService == null)
			trainerService = new TrainerService();
		return trainerService;
	}
	
	public ObservableList<Trainer> getAllTrainer() throws SQLException{
		list.clear();
		ResultSet rs = stmt.executeQuery("select * from trainer");
		while(rs.next())
			list.add(new Trainer(Integer.parseInt(rs.getString(1)), rs.getString(2)));
			return list;
	}
	
	public void Insert(String name) throws SQLException {
		String sql = "insert into trainer values (null,'"+name+"')";
		stmt.executeUpdate(sql);
	}
	
	public String Search(String id) throws SQLException {
		String result = id+": Not Found";
		String sql = "select * from trainer where id="+id;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
			result = "Trainer Name = "+rs.getString(2);
		return result;
	}
	
	public String Delete(String id) throws SQLException {
		String result = Search(id);
		String sql = "delete from trainer where id="+id;
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
