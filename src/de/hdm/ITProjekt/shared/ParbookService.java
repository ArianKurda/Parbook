package de.hdm.ITProjekt.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.shared.bo.Blocklist;
import de.hdm.ITProjekt.shared.bo.Characteristic;
import de.hdm.ITProjekt.shared.bo.Description;
import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.Notepad;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.bo.Selection;

@RemoteServiceRelativePath("parbookservice")
public interface ParbookService extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	//------Profil Methoden------
	public Profile createProfile(String firstName, String lastName, String email, String password);
	
	public Profile editStandardProfileAttributes(Profile p, String firstName, String lastName, String email, String password, Date dob,
			String hairColor, double bodyHeight, boolean smoker, String religion, boolean gender);
	
	Profile editProfile(Profile p, String firstName, String lastName, String email, String password, Date dob,
			String hairColor, double bodyHeight, boolean smoker, String religion, boolean gender);
	
	public void deleteProfile(Profile p);
	
	public ArrayList<Profile> findProfileByName(String firstName, String lastName);
	
	//-----Match-Methode, muss noch ausformuliert werden------
	public ArrayList<Profile> findMatch();
	
	//------Merkzettel Methoden------
	public Notepad createNotepad();
	void editNotepad(Notepad n, String text);
	
	public Notepad addProfileToNotepad(Profile p);
	
	public void deleteProfileFromNotepad(Notepad n, Profile p);
	
	public ArrayList<Notepad> findNotepadByProfileID(int profileId);
	
	//------Eigenschaft Methoden------
	public Characteristic createCharacteristic(String text);
	void deleteCharacteristic(Characteristic c);
	
	public Description createDescription(String text);
	void deleteDescription(Description description);
	
	public Selection createSelection(String text);
	void deleteSelection(Selection selection);
	
	//------Info Methoden------
	public Info createInfo(String text);
	
	public Info addCharacteristicToInfo(Characteristic c);
	public Info addDescriptionToInfo(Description description);
	public Info addSelectionToInfo(Selection selection);
	
	void deleteInfo(Info i);
	
	//------Sperrliste Methoden------
	public Blocklist createBlocklist(Blocklist b);
	
	public Blocklist addProfileToBlocklist(Profile p);
	
	void deleteBlocklist(Blocklist b);
	
}
