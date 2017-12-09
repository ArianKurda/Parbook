package de.hdm.ITProjekt.shared;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.bo.Blocklist;
import de.hdm.ITProjekt.shared.bo.Characteristic;
import de.hdm.ITProjekt.shared.bo.Description;
import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.Notepad;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.bo.Selection;

public interface ParbookServiceAsync {
	
	void init(AsyncCallback<Void> callback);
	
	//------Profil Methoden------
	void createProfile(String firstName, String lastName, String email, String password, AsyncCallback<Profile> callback);
		
	void editStandardProfileAttributes(Profile p, String firstName, String lastName, String email, String password, Date dob,
				String hairColor, double bodyHeight, boolean smoker, String religion, boolean gender, AsyncCallback<Profile> callback);
		
	void editProfile(Profile p, String firstName, String lastName, String email, String password, Date dob,
				String hairColor, double bodyHeight, boolean smoker, String religion, boolean gender, AsyncCallback<Profile> callback);
		
	void deleteProfile(Profile p, AsyncCallback<Void> callback);
		
	void findProfileByName(String firstName, String lastName, AsyncCallback<ArrayList<Profile>> callback);
	
	//-----Match-Methode, muss noch ausformuliert werden------
	void findMatch(AsyncCallback<ArrayList<Profile>> callback);
	
		//------Merkzettel Methoden------
	void createNotepad(AsyncCallback<Notepad> callback);
		
	void editNotepad(Notepad n, String text, AsyncCallback<Void> callback);
		
	void addProfileToNotepad(Profile p, AsyncCallback<Notepad> callback);
		
	void deleteProfileFromNotepad(Notepad n, Profile p, AsyncCallback<Void> callback);
		
	void findNotepadByProfileID(int profileId, AsyncCallback<ArrayList<Notepad>> callback);
		
		//------Eigenschaft Methoden------
	void createCharacteristic(String text, AsyncCallback<Characteristic> callback);
		
	void deleteCharacteristic(Characteristic c, AsyncCallback<Void> callback);
		
	void createDescription(String text, AsyncCallback<Description> callback);
	void deleteDescription(Description description, AsyncCallback<Void> callback);
		
	void createSelection(String text, AsyncCallback<Selection> callback);
	void deleteSelection(Selection selection, AsyncCallback<Void> callback);
		
		//------Info Methoden------
	void createInfo(String text, AsyncCallback<Info> callback);
		
	void addCharacteristicToInfo(Characteristic c, AsyncCallback<Info> callback);
	void addDescriptionToInfo(Description description, AsyncCallback<Info> callback);
	void addSelectionToInfo(Selection selection, AsyncCallback<Info> callback);
		
	void deleteInfo(Info i, AsyncCallback<Void> callback);
		
		//------Sperrliste Methoden------
	void createBlocklist(Blocklist b, AsyncCallback<Blocklist> callback);
		
	void addProfileToBlocklist(Profile p, AsyncCallback<Blocklist> callback);
		
	void deleteBlocklist(Blocklist b, AsyncCallback<Void> callback);

}
=======
public interface ParbookServiceAsync {

}
>>>>>>> refs/remotes/origin/arian
