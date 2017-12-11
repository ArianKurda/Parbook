package de.hdm.ITProjekt.shared.bo;

import java.util.ArrayList;
import java.util.List;

public class Blocklist extends BusinessObject {

  private static final long serialVersionUID = 1L;
  
  private String title;
  
  private Profile blocker;
  
  /*private int blockerID; */
  
  
  private ArrayList<Profile> blocklist = new ArrayList<Profile>();
  
  public List<String> blocks = new ArrayList<String>();
  
  public ArrayList<Profile> getBlockList() {
		return blocklist;
	}
	
	public void setBlockList(ArrayList<Profile> blocklist) {
		this.blocklist = blocklist;
	}
	
	public Profile getBlocker() {
		return blocker;
	}

	public void setBlocker(Profile blocker) {
		this.blocker = blocker;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "Kontaktsperre";
	} 
	

	
	public Blocklist() {};
	
	public Blocklist(Profile blocker, String title) {
		this.title = "Kontaktsperre";
		this.blocker = blocker;
		this.blocklist.add(blocker);	
		}

	public ArrayList<Profile> getBlockedProfiles() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
  
}

