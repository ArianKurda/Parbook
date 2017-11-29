package de.hdm.ITProjekt.shared.bo;


import java.util.ArrayList;
import java.util.List;

public class Notepad extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private Profile creator;
	
	/*private int creatorID;*/
	
	private ArrayList<Profile> profilelist = new ArrayList<Profile>();
	
	
	
	
	public List<String> notes = new ArrayList<String>();
	
	
	public ArrayList<Profile> getProfileList() {
		return profilelist;
	}
	
	public void setProfileList(ArrayList<Profile> profilelist) {
		this.profilelist = profilelist;
	}
	
	public Profile getCreator(){
		return this.creator;
	}
	
	public void setCreator(Profile profile){
		this.creator = profile;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "Merkzettel";
	}
	
	
	/* public int CreatorID() {
	 * return creatorID;
	 }
	 
	 public void setCreatorID(int creator ID) {
	 this.creatorID = creatorID;
	 
	 weiß nicht ob das benötigt wird
	 */
	 public Notepad (Profile creator, String title){
		 this.title = "Merkzettel";
		 this.creator = creator;
		 this.profilelist.add(creator);
	 }

}