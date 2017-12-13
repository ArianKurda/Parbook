package de.hdm.ITProjekt.server;

import java.util.ArrayList;
import java.sql.Date;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.server.db.*;
import de.hdm.ITProjekt.shared.ParbookService;
import de.hdm.ITProjekt.shared.bo.*;

/**
 * <p>
 * Implementierungsklasse des Interface PartnerboerseAdministration. Diese Klasse ist die Klasse,
 * die neben {@link ReportGeneratorImpl} sämtliche Applikationslogik beinhaltet. Die
 * Applikationslogik befindet sich in den Methoden dieser Klasse. Sie ist der Dreh- und Angelpunkt
 * des Projekts.
 * 
 * PartnerboerseAdministrationImpl und PartnerboerseAdministration bilden nur die Server-seitige
 * Sicht der Applikationslogik ab. Diese basiert vollständig auf synchronen Funktionsaufrufen. Jede
 * Server-seitig instantiierbare und Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
 * RemoteServiceServlet implementieren.
 * 
 * @see PartnerboerseAdministration
 * @see PartnerboerseAdministrationAsync
 * @see RemoteServiceServlet
 * @author Thies, Kurda
 */

public class ParbookServiceImpl extends RemoteServiceServlet implements ParbookService{
	
	private static final long serialVersionUID = 1L;
	
	private ProfileMapper profileMapper = null;
	private NotepadMapper notepadMapper = null;
	private InfoMapper infoMapper = null;
	private BlocklistMapper blocklistMapper = null;
	private SelectionMapper selectionMapper = null;
	private DescriptionMapper descriptionMapper = null;
	
	/**
	 * 
	 * Ein RemoteServiceServlet wird unter GWT mittels GWT.create(Klassenname.class) Client-seitig
	 * erzeugt. Hierzu ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	 * Konstruktors ist durch die Client-seitige Instantiierung durch GWT.create(Klassenname.class)
	 * nach derzeitigem Stand nicht möglich. Es bietet sich also an, eine separate Instanzenmethode zu
	 * erstellen, die Client-seitig direkt nach GWT.create(Klassenname.class) aufgerufen wird, um eine
	 * Initialisierung der Instanz vorzunehmen.
	 *
	 */

	public ParbookServiceImpl() throws IllegalArgumentException {

	}
	
	/**
	 * 
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor
	 * {@link #ReportGeneratorImpl()}. Diese Methode muss für jede Instanz von
	 * PartnerboerseAdministrationImpl aufgerufen werden.
	 *
	 * @see #ReportGeneratorImpl()
	 * 
	 */

	public void init() throws IllegalArgumentException {
		
		this.profileMapper = ProfileMapper.profileMapper();
		this.notepadMapper(NotepadMapper.notepadMapper());
		this.infoMapper = InfoMapper.infoMapper();
		this.selectionMapper = SelectionMapper.selectionMapper();
		this.descriptionMapper = DescriptionMapper.descriptionMapper();
		this.blocklistMapper = BlocklistMapper.blocklistMapper();

	}
	
	private void notepadMapper(NotepadMapper notepadMapper) {
		// TODO Auto-generated method stub
		
	}

	//------Login Methoden------
	@Override
	  public Profile login(String requestUri) {

	// addInfosToUsers();
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();

	    Profile profile = new Profile();

	    if (user != null) {
	      Profile existProfile = profileMapper.findByEmail(user.getEmail());

	      if (existProfile != null) {
	        ClientsideSettings.getLogger().severe("Userobjekt E-Mail = " + user.getEmail()
	            + "  Bestehender User: E-Mail  =" + existProfile.getEmail());
	        existProfile.setLoggedIn(true);
	        existProfile.setLogoutUrl(userService.createLogoutURL(requestUri));

	        return existProfile;
	      }

	      profile.setLoggedIn(true);
	      profile.setLogoutUrl(userService.createLogoutURL(requestUri));
	      profile.setEmail(user.getEmail());

	    } else {
	      profile.setLoggedIn(false);
	      profile.setLoginUrl(userService.createLoginURL(requestUri));
	      profile.setLogoutUrl(userService.createLogoutURL(requestUri));
	    }

	    return profile;

	  }

	  //------Profil-Methoden------
	  
	public Profile createProfile(String firstname, String lastname, String email, Date birthdate, String haircolor,
			double bodyheight, boolean smoker, String religion, boolean gender) throws IllegalArgumentException {
		
		Profile p = new Profile();
		
		p.setFirstName(firstname);
		p.setLastName(lastname);
		p.setEmail(email);
		p.setDateOfBirth(birthdate);
		p.setHairColor(haircolor);
		p.setBodyHeight(bodyheight);
		p.setSmoker(smoker);
		p.setReligion(religion);
		p.setGender(gender);
		
		/*
	     * Setzen einer vorläufigen Profilnr. Der insert-Aufruf liefert dann ein
	     * Objekt, dessen Nummer mit der Datenbank konsistent ist.
	     */
		p.setId(1);
		ClientsideSettings.setCurrentUser(p);
		ClientsideSettings.getLogger().info("user " + p.getLastName() + " erstellt");
		
		// Objekt in der DB speichern.
		return this.profileMapper.insert(p);
	}
	
	/**
	 * Methode, um ein bestehendes Profil zu löschen.
	 */
	
	public void deleteProfile(Profile profile) throws IllegalArgumentException {
		profileMapper.delete(profile);
	}
	
	/**
	 * Methode, um ein Profil zu speichern.
	 */
	public void saveProfile(Profile profile) throws IllegalArgumentException {
		profileMapper.update(profile);
	}
	
	/**
	   * Auslesen aller Profile
	   */

	  @Override
	  public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException {
	    return profileMapper.findAll();
	  }

	  /**
	   * Auslesen eines Profils mit einer bestimmten Id
	   */

	  @Override
	  public Profile getProfileById(int id) {
	    return profileMapper.findByKey(id);
	  }

	  /**
	   * Auslesen eines Profils mit einer bestimmten E-Mail-Adresse
	   */

	  @Override
	  public Profile getProfileByMail(String email) {
	    return profileMapper.findByEmail(email);

	  }

	
		
		//------Merkzettel Methoden------
		
		/**
		 * Erstellen eines Merkzettels für ein Profil
		 */

			
		

		/**
		 * Löschen einer Notiz für ein Profil.
		 */
		  
		  /**
		   * Auslesen des Merkzettels für ein Profil
		   */

	
	//------Eigenschaft-Methoden------
	
	/**
	   * Auslesen der Eigenschaftsnamen mit einer bestimmten Id
	   */

	  @Override
	  public String getCharacteristicsNameById(int id) throws IllegalArgumentException {
	    if (descriptionMapper.findByKey(id) != null) {
	      Description d = descriptionMapper.findByKey(id);
	      String name = d.getName();
	      return name;
	    } else if (selectionMapper.findByKey(id) != null) {
	      Selection s = selectionMapper.findByKey(id);
	      String name = s.getName();
	      return name;
	    }
	    return null;
	  }

	  /**
	   * Auslesen der Eigenschaftsbeschreibung mit einer bestimmten Id
	   */

	  @Override
	  public String getCharacteristicsDescriptionById(int id) throws IllegalArgumentException {
	    if (descriptionMapper.findByKey(id) != null) {
	      Description d = descriptionMapper.findByKey(id);
	      String name = d.getDescriptiontext();
	      return name;
	    } else if (selectionMapper.findByKey(id) != null) {
	      Selection s = selectionMapper.findByKey(id);
	      String name = s.getDescriptiontext();
	      return name;
	    }
	    return null;
	  }

	
	public ArrayList<Selection> getAllSelection() throws IllegalArgumentException {
	    return selectionMapper.findAll();
	  }

	  /**
	   * Auslesen der Auswahl mit einer bestimmten Id
	   */
	  public Selection getSelectionById(int id) throws IllegalArgumentException {
	    return selectionMapper.findByKey(id);
	  }

	  /**
	   * Auslesen der Auswahl von Profilattributen mit einem bestimmten Namen
	   */
	  public Selection getSelectionProfileAttributeByName(String name) throws IllegalArgumentException {
	    return selectionMapper.findByName(name);
	  }

	  /**
	   * Löschen eines Auswahl-Objekts
	   */
	  public void delete(Selection selection) throws IllegalArgumentException {
	    // TODO Auto-generated method stub

	  }

	  /**
	   * Speichern eines Auswahl-Objekts
	   */
	  public void save(Selection selection) throws IllegalArgumentException {
	    // TODO Auto-generated method stub

	  }

	  /**
	   * Erstellen der Auswahl-Objekte
	   */
	  public Selection createSelection(String name, String descriptiontext,
	      ArrayList<String> alternatives) throws IllegalArgumentException {
	    // TODO Auto-generated method stub
	    return null;
	  }

	  /**
	   * Auslesen der Auswahl-Objekte eines Profilattributs
	   */
	  public ArrayList<Selection> getAllSelectionProfileAttributes() {
	    return selectionMapper.findAllProfilAtrribute();
	  }

	  /**
	   * Auslesen der Beschreibungs-Objekte von Profilattributen mit einem bestimmten Namen
	   */
	  public Description getDescriptionProfileAttributesByName(String name)
	      throws IllegalArgumentException {
	    return descriptionMapper.findByName(name);
	  }

	  /**
	   * Auslesen der Beschreibung mit einer bestimmten Id
	   */
	  public Description getDescriptionById(int id) throws IllegalArgumentException {
	    return descriptionMapper.findByKey(id);
	  }

	  /**
	   * Auslesen aller Beschreibungs-Objekte
	   */
	  public ArrayList<Description> getAllDescription() throws IllegalArgumentException {
	    return descriptionMapper.findAll();
	  }

	  /**
	   * Löschen eines Beschreibungs-Objekt
	   */
	  public void delete(Description description) throws IllegalArgumentException {
	  }

	  /**
	   * Speichern eines Beschreibungs-Objekt
	   */
	  public void save(Description description) throws IllegalArgumentException {

	  }

	  /**
	   * Erstellen eines Beschreibungs-Objekt
	   */
	  public Description createDescription(String name, String descriptiontext)
	      throws IllegalArgumentException {
	    return null;
	  }

	  /**
	   * Auslesen aller Beschreibungs-Objekte eines Profils
	   */
	  public ArrayList<Description> getAllDescriptionProfileAttributes() {
	    return descriptionMapper.findAllProfilAttribute();
	  }
	  
	  //------Info-Methoden------
	  
	  /**
	   * Erstellen von Info-Objekte für ein Profil mit vorgegebener Auswahl
	   */
	  public Info createInfoFor(Profile profile, Selection selection, String text)
	      throws IllegalArgumentException {


	    Info i = new Info();
	    i.setText(text);
	    i.setCharacteristicID(selection.getId());
	    i.setProfileID(profile.getId());

	    ArrayList<Info> infoListe = infoMapper.findAllByProfileID(profile.getId());

	    for (Info info : infoListe) {
	      if ((info.getCharacteristicID() == i.getCharacteristicID())
	          && (info.getProfileID() == i.getProfileID()) && !info.getText().equals(i.getText())) {

	        this.log("Info upgedatet");
	        return infoMapper.update(i);

	      } else if ((info.getCharacteristicID() == i.getCharacteristicID())
	          && (info.getProfileID() == i.getProfileID()) && info.getText().equals(i.getText())) {
	        return null;
	      }
	    }
	    this.log("Info neuangelegt");
	    return infoMapper.insert(i);

	  }

	  /**
	   * Erstellen von Info-Objekten für ein Profil mit Freitext
	   */

	  @Override
	  public Info createInfoFor(Profile profile, Description description, String text)
	      throws IllegalArgumentException {
	    Info i = new Info();
	    i.setText(text);
	    i.setCharacteristicID(description.getId());
	    i.setProfileID(profile.getId());

	    ArrayList<Info> infoListe = infoMapper.findAllByProfileID(profile.getId());

	    for (Info info : infoListe) {
	      if ((info.getCharacteristicID() == i.getCharacteristicID())
	          && (info.getProfileID() == i.getProfileID()) && !info.getText().equals(i.getText())) {

	        this.log("Info upgedatet");
	        return infoMapper.update(i);
	      } else if ((info.getCharacteristicID() == i.getCharacteristicID())
	          && (info.getProfileID() == i.getProfileID()) && info.getText().equals(i.getText())) {
	        return null;
	      }
	    }
	    this.log("Info wurde angelegt");
	    return infoMapper.insert(i);
	  }

	  /**
	   * Speichern von Info-Objekten
	   */

	  @Override
	  public void save(Info info) throws IllegalArgumentException {
	    infoMapper.update(info);
	  }

	  /**
	   * Löschen von Info-Objekten
	   */

	  @Override
	  public void delete(Info info) throws IllegalArgumentException {
	    if (info != null) {
	      infoMapper.delete(info);
	    }
	  }

	  /**
	   * Auslesen von Info-Objekten eines Profils
	   */
	  @Override
	  public ArrayList<Info> getInfoByProfile(Profile profile) throws IllegalArgumentException {
	    return infoMapper.findAllByProfileID(profile.getId());
	  }

	  /**
	   * Auslesen von Info-Objekten mit einer bestimmten Eigenschafts-Id
	   */

	  @Override
	  public Info getInfoByCharacteristicID(int id) throws IllegalArgumentException {
	    return infoMapper.findByKey(id);
	  }

	  /**
	   * Auslesen eines Infoobjekts über seine ID
	   */

	  @Override
	  public Info getInfoById(int id) throws IllegalArgumentException {
	    return infoMapper.findByKey(id);
	  }
	  
	  //------Sperrliste-Methoden------
	  
	  /**
	   * Erstellen einer Kontaktsperre
	   */
	  public void createLock(Profile a, Profile b) throws IllegalArgumentException {
	    blocklistMapper.insertForProfile(a, b);

	  }

	  /**
	   * Löschen einer Kontaktsperre
	   */
	  public void deleteLock(Profile remover, Profile remoter) throws IllegalArgumentException {
	    blocklistMapper.deleteLockFor(remover, remoter);
	  }

	  /**
	   * Auslesen der gesamten gesperrten Profile eines Profils
	   */
	  public Blocklist getBlocklistForProfile(Profile profile) throws IllegalArgumentException {
	    Blocklist b = blocklistMapper.findAllForProfile(profile);
	    b.getBlockedProfiles();

	    return b;
		
	}

	@Override
	public Profile createProfile(String firstname, String lastname, String email, java.util.Date birthdate,
			String haircolor, double bodyheight, boolean smoker, String religion, boolean gender)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Profile> findMatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notepad createNotepad(Profile a, Profile b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNote(Profile remover, Profile remoter) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Notepad getNotepadForProfile(Profile profile) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile editStandardProfileAttributes(Profile p, String firstName, String lastName, String email,
			String password, java.util.Date dob, String hairColor, double bodyHeight, boolean smoker, String religion,
			boolean gender) {
		// TODO Auto-generated method stub
		return null;
	}

	public NotepadMapper getNotepadMapper() {
		return notepadMapper;
	}

	public void setNotepadMapper(NotepadMapper notepadMapper) {
		this.notepadMapper = notepadMapper;
	}
};

	
