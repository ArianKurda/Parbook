package de.hdm.ITProjekt.shared;

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
	
	void createProfile(String firstname, String lastname, String email, Date birthdate, String haircolor,
			double bodyheight, boolean smoker, String religion, boolean gender, AsyncCallback<Profile> callback) throws IllegalArgumentException;
	
	/**
	 * Methode, um ein bestehendes Profil zu löschen.
	 */
	void deleteProfile(Profile profile, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	/**
	 * Methode, um ein Profil zu speichern.
	 */
	void saveProfile(Profile profile, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
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

	  void editStandardProfileAttributes(Profile p, String firstName, String lastName, String email,
			String password, java.util.Date dob, String hairColor, double bodyHeight, boolean smoker, String religion,
			boolean gender, AsyncCallback<Profile> callback);
	
	//-----Match-Methode, muss noch ausformuliert werden------
	  void findMatch(AsyncCallback<ArrayList<Profile>> callback);
	
	
		//------Merkzettel Methoden------
	/**
	   * Erstellen eines Merkzettels für ein Profil
	   */
	void createNotepad(Profile a, Profile b, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Löschen einer Notiz für ein Profil.
	   */
	  void deleteNote(Profile remover, Profile remoter, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Auslesen des Merkzettels für ein Profil
	   */
	  void getNotepadForProfile(Profile profile, AsyncCallback<Notepad> callback) throws IllegalArgumentException;
	
		
		//------Eigenschaft Methoden------
	void getAllSelection(AsyncCallback<ArrayList<Selection>> callback) throws IllegalArgumentException;
	
	  /**
	   * Auslesen der Auswahl mit einer bestimmten Id
	   */
	void getSelectionById(int id, AsyncCallback<Selection> callback) throws IllegalArgumentException;
	
	/**
	   * Auslesen der Auswahl von Profilattributen mit einem bestimmten Namen
	   */
	  void getSelectionProfileAttributeByName(String name, AsyncCallback<Selection> callback) throws IllegalArgumentException;

	  /**
	   * Löschen eines Auswahl-Objekts
	   */
	  void delete(Selection selection, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Speichern eines Auswahl-Objekts
	   */
	  void save(Selection selection, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Erstellen der Auswahl-Objekte
	   */
	  void createSelection(String name, String descriptiontext,
	      ArrayList<String> alternatives, AsyncCallback<Selection> callback) throws IllegalArgumentException;

	  /**
	   * Auslesen der Auswahl-Objekte eines Profilattributs
	   */
	  void getAllSelectionProfileAttributes(AsyncCallback<ArrayList<Selection>> callback);

	  /**
	   * Auslesen der Beschreibungs-Objekte von Profilattributen mit einem bestimmten Namen
	   */
	  void getDescriptionProfileAttributesByName(String name, AsyncCallback<Description> callback)
	      throws IllegalArgumentException;

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

	  void delete(Description description, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Speichern eines Beschreibungs-Objekt
	   */
	  void save(Description description, AsyncCallback<Void> callback) throws IllegalArgumentException;
	  
	  /**
	   * Erstellen eines Beschreibungs-Objekt
	   */

	  void createDescription(String name, String descriptiontext, AsyncCallback<Description> callback)
	      throws IllegalArgumentException;

	  /**
	   * Auslesen aller Beschreibungs-Objekte eines Profils
	   */

	  void getAllDescriptionProfileAttributes(AsyncCallback<ArrayList<Description>> callback);
	  
		//------Info Methoden------
	  
	  /**
	   * Erstellen von Info-Objekte für ein Profil mit vorgegebener Auswahl
	   */
	  void createInfoFor(Profile profile, Selection selection, String text, AsyncCallback<Info> callback)
	      throws IllegalArgumentException;

	  /**
	   * Erstellen von Info-Objekten für ein Profil mit Freitext
	   */
	  void createInfoFor(Profile profile, Description description, String text, AsyncCallback<Info> callback)
	      throws IllegalArgumentException;

	  /**
	   * Speichern von Info-Objekten
	   */
	  void save(Info info, AsyncCallback<Void> callback) throws IllegalArgumentException;
	  
	  /**
	   * Löschen von Info-Objekten
	   */
	  void delete(Info info, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Auslesen von Info-Objekten eines Profils
	   */
	  void getInfoByProfile(Profile profile, AsyncCallback<ArrayList<Info>> callback) throws IllegalArgumentException;

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
	  void createLock(Profile a, Profile b, AsyncCallback<Void> callback) throws IllegalArgumentException;

	  /**
	   * Löschen einer Kontaktsperre
	   */
	  void deleteLock(Profile remover, Profile remoter, AsyncCallback<Void> callback);

	  /**
	   * Auslesen der gesamten gesperrten Profile eines Profils
	   */
	  void getBlocklistForProfile(Profile profile, AsyncCallback<Blocklist> callback) throws IllegalArgumentException;

}
