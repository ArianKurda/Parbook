package de.hdm.ITProjekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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
	Profile createProfile(String firstname, String lastname, String email, Date birthdate, String haircolor,
			double bodyheight, boolean smoker, String religion, boolean gender) throws IllegalArgumentException;
	
	public Profile editStandardProfileAttributes(Profile p, String firstName, String lastName, String email, String password, Date dob,
			String hairColor, double bodyHeight, boolean smoker, String religion, boolean gender);
	
	public Profile editProfile(Profile p);
	
	public void deleteProfile(Profile p);
	
	public ArrayList<Profile> findProfileByName(String firstName, String lastName);
	
	//-----Match-Methode, muss noch ausformuliert werden------
	public ArrayList<Profile> findMatch();
	
	//------Merkzettel Methoden------
	public void createNotepad(Profile a, Profile b) throws IllegalArgumentException;
	
	//------Eigenschaft-Methoden------
	
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
		  public ArrayList<Info> getInfoByProfile(Profile profile) throws IllegalArgumentException;

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
		  public void createLock(Profile a, Profile b) throws IllegalArgumentException;

		  /**
		   * Löschen einer Kontaktsperre
		   */
		  public void deleteLock(Profile remover, Profile remoter);

		  /**
		   * Auslesen der gesamten gesperrten Profile eines Profils
		   */
		  public Blocklist getBlocklistForProfile(Profile profile) throws IllegalArgumentException;


}
