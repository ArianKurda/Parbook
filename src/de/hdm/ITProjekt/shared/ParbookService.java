package de.hdm.ITProjekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.client.ClientsideSettings;
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

	  public Profile editStandardProfileAttributes(Profile p, String firstName, String lastName, String email,
			String password, java.util.Date dob, String hairColor, double bodyHeight, boolean smoker, String religion,
			boolean gender);
	
	  	//-----Match-Methode, muss noch ausformuliert werden------
	public ArrayList<Profile> findMatch();
	
	//------Merkzettel Methoden------
	
	/**
	 * Erstellen eines Merkzettels für ein Profil
	 */
	public Notepad createNotepadOfProfile(Profile fromProfile, Profile toProfile) throws IllegalArgumentException;

	/**
	 * Löschen einer Notiz für ein Profil.
	 */
	  public void deleteNote(Profile remover, Profile remoter) throws IllegalArgumentException;

	/**
	 * Auslesen des Merkzettels für ein Profil
	 */
	  public Notepad getNotepadForProfile(Profile profile) throws IllegalArgumentException;
	  
	
	//------Eigenschaft-Methoden------
	  
	/**
	 * Auslesen der Eigenschaftsnamen mit einer bestimmten Id
	 */
	  public String getCharacteristicsNameById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen der Eigenschaftsbeschreibung mit einer bestimmten Id
	 */
	  public String getCharacteristicsDescriptionById(int id) throws IllegalArgumentException;

	  public ArrayList<Selection> getAllSelection() throws IllegalArgumentException;

	/**
	 * Auslesen der Auswahl mit einer bestimmten Id
	 */
	  public Selection getSelectionById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen der Auswahl von Profilattributen mit einem bestimmten Namen
	 */
	  public Selection getSelectionProfileAttributeByName(String name) throws IllegalArgumentException;

	/**
	 * Löschen eines Auswahl-Objekts
	 */
	  public void delete(Selection selection) throws IllegalArgumentException;

	/**
	 * Speichern eines Auswahl-Objekts
	 */
	  public void save(Selection selection) throws IllegalArgumentException;

	/**
	 * Erstellen der Auswahl-Objekte
	 */
	  public Selection createSelection(String name, String descriptiontext,
		      ArrayList<String> alternatives) throws IllegalArgumentException;

	/**
	 * Auslesen der Auswahl-Objekte eines Profilattributs
	 */
	  public ArrayList<Selection> getAllSelectionProfileAttributes();

	/**
	 * Auslesen der Beschreibungs-Objekte von Profilattributen mit einem bestimmten Namen
	 */
	  public Description getDescriptionProfileAttributesByName(String name)
		      throws IllegalArgumentException;

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
	  public void delete(Description description) throws IllegalArgumentException;

	/**
	 * Speichern eines Beschreibungs-Objekt
	 */
	  public void save(Description description) throws IllegalArgumentException;
		  
	/**
	 * Erstellen eines Beschreibungs-Objekt
	 */
	  public Description createDescription(String name, String descriptiontext)
		      throws IllegalArgumentException;

	/**
	 * Auslesen aller Beschreibungs-Objekte eines Profils
	 */

	  public ArrayList<Description> getAllDescriptionProfileAttributes();
	
	//------Info Methoden------
	
	/**
	 * Erstellen von Info-Objekte für ein Profil mit vorgegebener Auswahl
	 */
	  public Info createInfoFor(Profile profile, Selection selection, String text)
		      throws IllegalArgumentException;

	/**
	 * Erstellen von Info-Objekten für ein Profil mit Freitext
	 */
	  public Info createInfoFor(Profile profile, Description description, String text)
		      throws IllegalArgumentException;

	/**
	 * Speichern von Info-Objekten
	 */
	  public void save(Info info) throws IllegalArgumentException;
		  
	/**
	 * Löschen von Info-Objekten
	 */
	  public void delete(Info info) throws IllegalArgumentException;

	/**
	 * Auslesen von Info-Objekten eines Profils
	 */
	  public ArrayList<Info> getInfoByProfileId(int id) throws IllegalArgumentException;

	/**
	 * Auslesen von Info-Objekten mit einer bestimmten Eigenschafts-Id
	 */
	  public Info getInfoByCharacteristicID(int id) throws IllegalArgumentException;

	/**
	 * Auslesen eines Infoobjekts über seine ID
	 */
	  public Info getInfoById(int id) throws IllegalArgumentException;
	
		
		  //------Sperrliste-Methoden------
		  
	/**
	 * Erstellen einer Kontaktsperre
	 */
	  public Blocklist createLock(Profile fromProfile, Profile toProfile) throws IllegalArgumentException;

	/**
	 * Löschen einer Kontaktsperre
	 */
	  public void deleteLock(Blocklist b);
	  
	/**
	 * Speichern einer Kontaktsperre
	 */
	  public void saveLock(Blocklist b);


}
