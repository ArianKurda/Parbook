package de.hdm.ITProjekt.shared;

import java.util.ArrayList;
import java.util.Date;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.shared.bo.Blocklist;
import de.hdm.ITProjekt.shared.bo.Description;
import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.Notepad;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.bo.SearchProfile;
import de.hdm.ITProjekt.shared.bo.Selection;

@RemoteServiceRelativePath("parbookservice")
public interface ParbookService extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	//------Login Methoden------
	public Profile login(String requestUri);
	
	//------Profil Methoden------
	public Profile createProfile(String firstname, String lastname, String email, Date birthdate, String haircolor,
			double bodyheight, boolean smoker, String religion, boolean gender) throws IllegalArgumentException;
	
	/**
	 * Methode, um ein bestehendes Profil zu löschen.
	 */
	public void deleteProfile(Profile p) throws IllegalArgumentException;
	
	/**
	 * Methode, um ein Profil zu speichern.
	 */
	public void saveProfile(Profile p) throws IllegalArgumentException;
	
	/**
	 * Auslesen aller Profile
	 */
	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException;

	/**
	 * Auslesen eines Profils mit einer bestimmten Id
	 */
	public Profile getProfileById(int id);

	/**
	 * Auslesen eines Profils mit einer bestimmten E-Mail-Adresse
	 */
	public Profile getProfileByMail(String email);
	
	//-----Match-Methode, muss noch ausformuliert werden------
	public ArrayList<Profile> findMatch();
	
	//------Merkzettel Methoden------
	
	/**
	 * Erstellen eines Merkzettels für ein Profil
	 */
	public Notepad createNotepadOfProfile(Profile fromProfile, Profile toProfile) throws IllegalArgumentException;

	/**
	 * Löschen eines Merkzettels
	 */
	public void deleteNotepad(Notepad n) throws IllegalArgumentException;

	/**
	 * Auslesen des Merkzettels für ein Profil
	 */
	public Notepad getNotepadOfProfile(int profileId) throws IllegalArgumentException;
	  
	
	//------Eigenschaft-Methoden------
	  
	/**
	 * Auslesen der Eigenschaftsnamen mit einer bestimmten Id
	 */
	public String getCharacteristicNameById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen der Eigenschaftsbeschreibung mit einer bestimmten Id
	 */
	public String getCharacteristicsDescriptionById(int id) throws IllegalArgumentException;
	
	//------Auswahleigenschaft-Methoden------
	
	/**
	 * Erstellen der Auswahl-Objekte
	 */
	public Selection createSelection(int id, String characteristicName, String descriptiontext) throws IllegalArgumentException;
	
	/**
	 * Auslesen aller Auswahl-Objekte
	 */
	public ArrayList<Selection> getAllSelection() throws IllegalArgumentException;

	/**
	 * Auslesen der Auswahl mit einer bestimmten Id
	 */
	public Selection getSelectionById(int id) throws IllegalArgumentException;
	
	/**
	 * Löschen eines Auswahl-Objekts
	 */
	public void delete(Selection s) throws IllegalArgumentException;

	/**
	 * Speichern eines Auswahl-Objekts
	 */
	public void save(Selection s) throws IllegalArgumentException;
	
	//------Beschreibungeigenschaft-Methoden------
	
	/**
	 * Erstellen eines Beschreibungs-Objekt
	 */
	public Description createDescription(int id, String characteristicName, String descriptiontext) throws IllegalArgumentException;

	/**
	 * Auslesen der Beschreibung mit einer bestimmten Id
	 */
	public Description getDescriptionById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen aller Beschreibungs-Objekte
	 */
	public ArrayList<Description> getAllDescription() throws IllegalArgumentException;

	/**
	 * Löschen eines Beschreibungs-Objekt
	 */
	public void delete(Description d) throws IllegalArgumentException;

	/**
	 * Speichern eines Beschreibungs-Objekt
	 */
	public void save(Description d) throws IllegalArgumentException;

	/**
	 * Auslesen aller Beschreibungs-Objekte eines Profils
	 */
	
	//------Info Methoden------
	
	/**
	 * Erstellen eines Info-Objekts
	 * @param id
	 * @param InfoValue
	 * @throws IllegalArgumentException
	 */
	public Info createInfo(int id, String InfoValue) throws IllegalArgumentException;

	/**
	 * Speichern von Info-Objekten
	 */
	public void save(Info i) throws IllegalArgumentException;
		  
	/**
	 * Löschen von Info-Objekten
	 */
	public void delete(Info i) throws IllegalArgumentException;

	/**
	 * Auslesen von Info-Objekten eines Profils
	 */
	public ArrayList<Info> getInfoByProfile(Profile p) throws IllegalArgumentException;

	/**
	 * Auslesen von Info-Objekten mit einer bestimmten Eigenschafts-Id
	 */
	public Info getInfoByCharacteristicID(int id) throws IllegalArgumentException;

	/**
	 * Auslesen eines Info-Objekts über seine ID
	 */
	public Info getInfoById(int id) throws IllegalArgumentException;
	
	//------Sperrliste-Methoden------
		  
	/**
	 * Erstellen einer Kontaktsperre
	 */
	public Blocklist createLock(Profile blockerProfile, Profile blockedProfile) throws IllegalArgumentException;

	/**
	 * Löschen einer Kontaktsperre
	 */
	public void deleteLock(Blocklist b);
	  
	/**
	 * Speichern einer Kontaktsperre
	 */
	public void saveLock(Blocklist b);
	
	/**
	 * Auslesen aller Kontaktsperren einer Sperrliste
	 */

	/**
	 * Auslesen der Kontaktsperre anhand der Id
	 */
	public Blocklist getBlocklistById(int id) throws IllegalArgumentException;

	ArrayList<SearchProfile> getAllSearchProfilesOfProfile(Profile p) throws IllegalArgumentException;

	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	Profile findByEmail(String email);

	Profile insertProfile(Profile p);

	void updateProfile(Profile p);
	
}
