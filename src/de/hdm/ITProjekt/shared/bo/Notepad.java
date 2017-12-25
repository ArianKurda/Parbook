package de.hdm.ITProjekt.shared.bo;


import java.util.ArrayList;
import java.util.List;

public class Notepad extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private Profile creator;
	
	private Profile fromProfile;
	
	private Profile toProfile;
	
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
		return creator;
	}
	
	public Profile getFromProfile() {
		return fromProfile;
	}
	
	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}
	
	public Profile toProfile() {
		return toProfile;
	}
	
	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
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
	 
	 wei� nicht ob das ben�tigt wird
	 */
	 public Notepad (Profile creator, String title){
		 this.title = "Merkzettel";
		 this.creator = creator;
		 this.profilelist.add(creator);
	 }

	public Notepad() {
		// TODO Auto-generated constructor stub
	}
	
	public String getOwnerId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOwnerId(int int1) {
		// TODO Auto-generated method stub
		
	}

}