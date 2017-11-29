package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notepad extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	
	private Profile creator;
	
	private int creatorID;
	
	private ArrayList<Profile> profilelist = new ArrayList<Profile>();
	
	
	
	
	public List<String> notes = new ArrayList<String>();
	
	public ArrayList<Profile> getProfileList
	
}