package models;

public class Trainer {
	private int TrainerID;
	private String TrainerName;
	
	public Trainer(int iD, String name) {
		TrainerID = iD;
		TrainerName = name;
	}

	public int getID() {
		return TrainerID;
	}

	public void setID(int iD) {
		TrainerID = iD;
	}

	public String getName() {
		return TrainerName;
	}

	public void setName(String name) {
		TrainerName = name;
	}
	
	
}
