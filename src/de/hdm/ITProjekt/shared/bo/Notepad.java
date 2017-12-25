package de.hdm.ITProjekt.shared.bo;


import java.util.ArrayList;
import java.util.List;

public class Notepad extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private Profile fromProfile;
	
	private Profile toProfile;
	
	private ArrayList<Profile> profilelist = new ArrayList<Profile>();
	
	public List<String> notes = new ArrayList<String>();
	
	public ArrayList<Profile> getProfileList() {
		return profilelist;
	}
	
	public void setProfileList(ArrayList<Profile> profilelist) {
		this.profilelist = profilelist;
	}
	
	public Profile getFromProfile() {
		return fromProfile;
	}
	
	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}
	
	public Profile getToProfile() {
		return toProfile;
	}
	
	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "Merkzettel";
	}

}