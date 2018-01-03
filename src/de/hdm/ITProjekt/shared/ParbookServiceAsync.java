package de.hdm.ITProjekt.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.bo.Blocklist;
import de.hdm.ITProjekt.shared.bo.Description;
import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.Notepad;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.bo.SearchProfile;
import de.hdm.ITProjekt.shared.bo.Selection;

public interface ParbookServiceAsync {
	void init(AsyncCallback<Void> callback);
	
	//------Login Methoden------
	void login(String requestUri, AsyncCallback<Profile> callback);
	
	//------Profil Methoden------
	void createProfile(String firstname, String lastname, String email, Date birthdate, String haircolor, double bodyheight,
			boolean smoker, String religion, boolean gender, AsyncCallback<Profile> callback) throws IllegalArgumentException;
	
	/**
	 * Methode, um ein bestehendes Profil zu löschen.
	 */
	void deleteProfile(Profile p, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	/**
	 * Methode, um ein Profil zu speichern.
	 */
	void saveProfile(Profile p, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	/**
	 * Auslesen aller Profile
	 */
	void getAllProfiles(AsyncCallback<ArrayList<Profile>> callback) throws IllegalArgumentException;
	  
	/**
	 * Auslesen eines Profils mit einer bestimmten Id
	 */
	void getProfileById(int id, AsyncCallback<Profile> callback);
	 
	/**
	 * Auslesen eines Profils mit einer bestimmten E-Mail-Adresse
	 */
	void getProfileByMail(String email, AsyncCallback<Profile> callback);
	
	//-----Match-Methode, muss noch ausformuliert werden------
	void findMatch(AsyncCallback<ArrayList<Profile>> callback);
	
	//------Merkzettel Methoden------
	
	/**
	 * Erstellen eines Merkzettels für ein Profil
	 */
	void createNotepadOfProfile(Profile fromProfile, Profile toProfile, AsyncCallback<Notepad> callback) throws IllegalArgumentException;

	/**
	 * Löschen eines Merkzettels
	 */
	void deleteNotepad(Notepad n, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
	 * Auslesen des Merkzettels für ein Profil
	 */
	void getNotepadOfProfile(int profileId, AsyncCallback<Notepad> callback) throws IllegalArgumentException;
	
	//------Eigenschaft Methoden------
	  
	/**
	 * Auslesen der Eigenschaftsnamen mit einer bestimmten Id
	 */
	void getCharacteristicNameById(int id, AsyncCallback<String> callback) throws IllegalArgumentException;

	/**
	 * Auslesen der Eigenschaftsbeschreibung mit einer bestimmten Id
	 */
	void getCharacteristicsDescriptionById(int id, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	//------Auswahl-Methoden------
	
	void getAllSelection(AsyncCallback<ArrayList<Selection>> callback) throws IllegalArgumentException;
	
	/**
	 * Auslesen der Auswahl mit einer bestimmten Id
	 */
	void getSelectionById(int id, AsyncCallback<Selection> callback) throws IllegalArgumentException;

	/**
	 * Löschen eines Auswahl-Objekts
	 */
	void delete(Selection s, AsyncCallback<Void> callback) throws IllegalArgumentException;

	/**
	 * Speichern eines Auswahl-Objekts
	 */
	void save(Selection s, AsyncCallback<Void> callback) throws IllegalArgumentException;
     
	/**
	 * Erstellen der Auswahl-Objekte
	 */
	void createSelection(int id, String characteristicName, String descriptiontext, AsyncCallback<Selection> callback) throws IllegalArgumentException;

	/**
	 * Auslesen der Beschreibung mit einer bestimmten Id
	 */
	void getDescriptionById(int id, AsyncCallback<Description> callback) throws IllegalArgumentException;

    /**
	 * Auslesen aller Beschreibungs-Objekte
	 */
	void getAllDescription(AsyncCallback<ArrayList<Description>> callback) throws IllegalArgumentException;

	/**
	 * Löschen eines Beschreibungs-Objekt
	 */
	void delete(Description d, AsyncCallback<Void> callback) throws IllegalArgumentException;

	/**
	 * Speichern eines Beschreibungs-Objekt
	 */
	void save(Description d, AsyncCallback<Void> callback) throws IllegalArgumentException;
	  
	/**
	 * Erstellen eines Beschreibungs-Objekt
	 */
	void createDescription(int id, String characteristicName, String descriptiontext, AsyncCallback<Description> callback) throws IllegalArgumentException;

	/**
	 * Auslesen aller Beschreibungs-Objekte eines Profils
	 */
	
	
	//------Info Methoden------
	
	/**
	 * Erstellen von Info-Objekten für ein Profil
	 */
	void createInfo(int id, String InfoValue, AsyncCallback<Info> callback) throws IllegalArgumentException;

	/**
	 * Speichern von Info-Objekten
	 */
	void save(Info i, AsyncCallback<Void> callback) throws IllegalArgumentException;
	  
	/**
	 * Löschen von Info-Objekten
	 */
	void delete(Info i, AsyncCallback<Void> callback) throws IllegalArgumentException;

	/**
	 * Auslesen von Info-Objekten eines Profils
	 */
	void getInfoByProfile(Profile p, AsyncCallback<ArrayList<Info>> callback) throws IllegalArgumentException;

	/**
	 * Auslesen von Info-Objekten mit einer bestimmten Eigenschafts-Id
	 */
	void getInfoByCharacteristicID(int id, AsyncCallback<Info> callback) throws IllegalArgumentException;

	/**
	 * Auslesen eines Infoobjekts über seine ID
	 */
	void getInfoById(int id, AsyncCallback<Info> callback) throws IllegalArgumentException;
	
	//------Sperrliste-Methoden------
	  
	/**
	 * Erstellen einer Kontaktsperre
	 */
	void createLock(Profile blockerProfile, Profile blockedProfile, AsyncCallback<Blocklist> callback) throws IllegalArgumentException;

	/**
	 * Löschen einer Kontaktsperre
	 */
	void deleteLock(Blocklist b, AsyncCallback<Void> callback);
	  
	/**
	 * Speichern einer Kontaktsperre
	 */
	void saveLock(Blocklist b, AsyncCallback<Void> callback);
	
	/**
	 * Auslesen aller Kontaktsperren einer Sperrliste
	 */

	/**
	 * Auslesen der Kontaktsperre anhand der Id
	 */
	void getBlocklistById(int id, AsyncCallback<Blocklist> callback) throws IllegalArgumentException;

	void getAllSearchProfilesOfProfile(Profile p, AsyncCallback<ArrayList<SearchProfile>> callback);
	
}
