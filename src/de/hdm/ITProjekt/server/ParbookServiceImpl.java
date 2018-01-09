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
	private SearchProfileMapper searchprofileMapper = null;
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
		this.searchprofileMapper = SearchProfileMapper.searchProfileMapper();
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
	 
	//Methode, die ein Profil erstellt
	public Profile createProfile(String firstname, String lastname, String email, Date birthdate, String haircolor,
			int bodyheight, boolean smoker, String religion, boolean gender) throws IllegalArgumentException {
		
		Profile p = new Profile();
		
		p.setFirstName(firstname);
		p.setLastName(lastname);
		p.setEmail(email);
		p.setBirthdate(birthdate);
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
	
	//Methode, die ein Profil über ihre Email findet
	public Profile findByEmail(String email) {
		return profileMapper.findByEmail(email);
		}
	
	//Methode, um ein Profil hinzuzufügen
	public Profile insertProfile(Profile p) {
		return profileMapper.insert(p);
		}
	
	//Methode, um ein Profil zu aktualisieren
	public void updateProfile(Profile p) {
		profileMapper.update(p);
		}
	
	/**
	 * Methode, um ein bestehendes Profil zu löschen.
	 */
	
	public void deleteProfile(Profile p) throws IllegalArgumentException {
		this.profileMapper.delete(p);
	}
	
	
	
	/**
	 * Methode, um ein Profil zu speichern.
	 */
	public void saveProfile(Profile p) throws IllegalArgumentException {
		if (p.getId() != 0) {
			profileMapper.update(p);
			} else {
				profileMapper.insert(p);
				}
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
	    return profileMapper.findById(id);
	  }

	/**
	 * Auslesen eines Profils mit einer bestimmten E-Mail-Adresse
	 */

	  public Profile getProfileByEmail(String email) {
	    return profileMapper.findByEmail(email);

	  }
	  
	  /**
		 * Auslesen des Profils anhand des Such-Profils.
		 * 
		 */
		public ArrayList<Profile> getBySearchProfile(SearchProfile sp) {
			return this.profileMapper.findBySearchProfile(sp);
		}
	  
	  //------Suchprofil-Methoden------
	  public SearchProfile createSearchProfile(String firstname, String lastname, String email, Date birthdate, String haircolor,
				double bodyheight, boolean smoker, String religion, boolean gender) throws IllegalArgumentException {
			
			SearchProfile sp = new SearchProfile();
			
			sp.setFirstName(firstname);
			sp.setLastName(lastname);
			sp.setEmail(email);
			sp.setBirthdate(birthdate);
			sp.setHairColor(haircolor);
			sp.setBodyHeight(bodyheight);
			sp.setSmoker(smoker);
			sp.setReligion(religion);
			sp.setGender(gender);
			
			// Objekt in der DB speichern.
			return this.searchprofileMapper.insert(sp);
		}
	  
	  /**
		 * Methode, um ein bestehendes Suchprofil zu löschen.
		 */
		public void deleteSearchProfile(SearchProfile sp) throws IllegalArgumentException {
			
			ArrayList<Info> infos = infoMapper.findBySearchProfile(sp);
			
			for (Info info : infos) {
				this.infoMapper.delete(info);
				}
			this.searchprofileMapper.delete(sp);
			}
		
		/**
		 * Methode, um ein Suchprofil zu speichern.
		 */
		public void saveSearchProfile(SearchProfile sp) throws IllegalArgumentException {
			if (sp.getId() != 0) {
				searchprofileMapper.update(sp);
				} else {
					searchprofileMapper.insert(sp);
					}
			}
		
		/**
		   * Auslesen aller Suchprofile eines Profils
		   */
		  @Override
		  public ArrayList<SearchProfile> getAllSearchProfilesOfProfile(Profile p) throws IllegalArgumentException {
		    return searchprofileMapper.findByProfile(p);
		  }

		  /**
		   * Auslesen eines Profils mit einer bestimmten Id
		   */
		  public SearchProfile getSearchProfileById(int id) {
		    return searchprofileMapper.findById(id);
		  }
		  
		  /**
			 * Auslesen einer Info eines Such-Profils aus der Datenbank.
			 * 
			 */

		
		//------Merkzettel Methoden------
		
		/**
		 * Erstellen eines Merkzettels für ein Profil
		 */
	  public Notepad createNotepadOfProfile(Profile fromProfile, Profile toProfile) {
		  Notepad n = new Notepad();
		  
		  n.setFromProfile(fromProfile);
		  n.setToProfile(toProfile);
		  return n;
		  }

		/**
		 * Löschen einer Notiz für ein Profil.
		 */
	  public void deleteNotepad(Notepad n) throws IllegalArgumentException {
		  this.notepadMapper.delete(n);
		  }
	  
	  /**
	   * Speichern eines Merkzettel-Objekts
	   */
	  public void save(Notepad n) throws IllegalArgumentException {
		  if (n.getId() != 0) {
			  notepadMapper.update(n);
			  } else {
				  notepadMapper.insert(n);
				  }
		  }
	  
	  /**
	   * Auslesen des Merkzettels anhand der Id
	   */
		public Notepad getNotepadById(int id)
				throws IllegalArgumentException {
			return this.notepadMapper.findById(id);
		}
		
	  /**
	   * Auslesen eines Merkzettels eines Profils
	   */
	  public Notepad getNotepadOfProfile(int profileId) {
		  return this.notepadMapper.findById(profileId);
		  }
	  
	  //------Eigenschaft-Methoden------
	  
	  /**
	   * Auslesen der Eigenschaftsnamen mit einer bestimmten Id
	   */
	
	public String getCharacteristicNameById(int id) throws IllegalArgumentException {
		
		if (descriptionMapper.findById(id) != null) {
			Description d = descriptionMapper.findById(id);
			String name = d.getCharacteristicName();
			
			return name;
			
		} else if (selectionMapper.findById(id) != null) {
			
			Selection s = selectionMapper.findById(id);
			String name = s.getCharacteristicName();
			return name;
			}
		
		return null;
		}

	  /**
	   * Auslesen der Eigenschaftsbeschreibung mit einer bestimmten Id
	   */
	  @Override
	  public String getCharacteristicsDescriptionById(int id) throws IllegalArgumentException {
		  if (descriptionMapper.findById(id) != null) {
			  Description d = descriptionMapper.findById(id);
			  String name = d.getDescriptiontext();
			  
			  return name;
			  
		  } else if (selectionMapper.findById(id) != null) {
			  Selection s = selectionMapper.findById(id);
			  String name = s.getDescriptiontext();
			  return name;
			  }
		  
		  return null;
		  }

	  //------Auswahl-Methoden------//
	  
	  /**
	   * Erstellen eines Auswahl-Objekts
	   */
	  public Selection createSelection(int id, String characteristicName, String descriptiontext) throws IllegalArgumentException {
		  
		  Selection s = new Selection();
		  s.setId(id);
		  s.setCharacteristicName(characteristicName);
		  s.setDescriptiontext(descriptiontext);
		  
		  return this.selectionMapper.insert(s);
		  }
	  
	  /**
	   * Auslesen aller Auswahl-Objekte
	   */
	public ArrayList<Selection> getAllSelection() throws IllegalArgumentException {
		return selectionMapper.findAll();
		}

	  /**
	   * Auslesen der Auswahl mit einer bestimmten Id
	   */
	  public Selection getSelectionById(int id) throws IllegalArgumentException {
		  return selectionMapper.findById(id);
		  }

	  /**
	   * Löschen eines Auswahl-Objekts
	   */
	  public void delete(Selection selection) throws IllegalArgumentException {
		  ArrayList<Info> infos = infoMapper.findBySelection(selection);
		  
		  for (Info i : infos) {
			  this.infoMapper.delete(i);
			  }
		  
		  this.selectionMapper.delete(selection);
		  }

	  /**
	   * Speichern eines Auswahl-Objekts
	   */
	  public void save(Selection selection) throws IllegalArgumentException {
		  if (selection.getId() != 0) {
			  selectionMapper.update(selection);
			  
		  } else {
			  selectionMapper.insert(selection);
			  }
		  }

	  //------Beschreibung-Methoden------//
	  
	  /**
	   * Erstellen von Beschreibungs-Objekten für ein Profil mit Freitext
	   */
	  public Description createDescription(int id, String characteristicName, String descriptiontext) throws IllegalArgumentException {
		  Description d = new Description();
		  
		  d.setId(id);
		  d.setCharacteristicName(characteristicName);
		  d.setDescriptiontext(descriptiontext);
		  
		  return this.descriptionMapper.insert(d);
		  }

	  /**
	   * Auslesen der Beschreibung mit einer bestimmten Id
	   */
	  public Description getDescriptionById(int id) throws IllegalArgumentException {
		  return descriptionMapper.findById(id);
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
		  ArrayList<Info> infos = infoMapper.findByDescription(description);
		  
		  for (Info info : infos) {
			  this.infoMapper.delete(info);
			  }
		  this.descriptionMapper.delete(description);
		  }

	  /**
	   * Speichern eines Beschreibungs-Objekt
	   */
	  public void save(Description description) throws IllegalArgumentException {
		  
		  if (description.getId() != 0) {
			  descriptionMapper.update(description);
			  
		  } else {
				  descriptionMapper.insert(description);
				  }
		  }
	  
	  //------Info-Methoden------
	  
	  /**
	   * Erstellen eines Info-Objekts für ein Profil
	   */
	  public Info createInfo(int id, String infoValue) throws IllegalArgumentException {
		  
		  Info i = new Info();
		  i.setId(id);
		  i.setInfoValue(infoValue);
		  
		  return this.infoMapper.insert(i);
		  }

	  /**
	   * Speichern von Info-Objekten
	   */

	  @Override
	  public void save(Info i) throws IllegalArgumentException {
		  
		  if (i.getId() != 0) {
			  infoMapper.update(i);
			  
		  } else {
			  infoMapper.insert(i);
			  }
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
	  public ArrayList<Info> getInfoByProfile(Profile p) throws IllegalArgumentException {
		  return infoMapper.findByProfile(p.getId());
		  }

	  /**
	   * Auslesen von Info-Objekten mit einer bestimmten Eigenschafts-Id
	   */
	  @Override
	  public Info getInfoByCharacteristicID(int id) throws IllegalArgumentException {
		  return infoMapper.findById(id);
		  }

	  /**
	   * Auslesen eines Infoobjekts über seine ID
	   */
	  @Override
	  public Info getInfoById(int id) throws IllegalArgumentException {
		  return infoMapper.findById(id);
		  }
	  
	  //------Sperrliste-Methoden------
	  
	  /**
	   * Erstellen einer Kontaktsperre
	   */
	  public Blocklist createLock(Profile blockerProfile, Profile blockedProfile) throws IllegalArgumentException {
		  
		  Blocklist b = new Blocklist();
		  b.setBlockerProfile(blockerProfile);
		  b.setBlockedProfile(blockedProfile);
		  
		  return this.blocklistMapper.insert(b);
		  }

	  /**
	   * Löschen einer Kontaktsperre
	   */
	  public void deleteLock(Blocklist b) throws IllegalArgumentException {
		  this.blocklistMapper.delete(b);
		  }
	  
	  /**
	   * Speichern einer Kontaktsperre
	   */
	  public void saveLock(Blocklist b) throws IllegalArgumentException {
		  
		  if (b.getId() != 0) {
			  blocklistMapper.update(b);
			  
		  } else {
			  blocklistMapper.insert(b);
			  }
		  }
	  
	  /**
	   * Auslesen einer Kontaktsperre eines Profils
	   */
	  public ArrayList<Blocklist> getBlocklistOfProfile(Profile p) {
		  return this.blocklistMapper.findByProfile(p);
		  }
	  /**
	   * Auslesen einer Kontaktsperre anhand der Id
	   */
	  public Blocklist getBlocklistById(int id) throws IllegalArgumentException {
		  return blocklistMapper.findById(id);
		  }
	  
	  /**
	   * Prüft, ob eine Kontaktsperre vorhanden ist
	   */
	  /*public boolean isBlocked(Profile p) {
		  Profile currentProfile = LoginServiceImpl.loginService().getCurrentProfile();
		  
		  return blocklistMapper.doLockExistForProfile(currentProfile, p);
		  }
*/
	@Override
	public ArrayList<Profile> findMatch() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//------Ähnlichkeitsmaß-Methoden------
	
	/**
	   * Berechnen des Ähnlichkeitsmaß zwischen zwei Profilen
	   *
	   * @param p1
	   * @param p2
	   * @return result (Ähnlichkeitsmaß zwischen zwei Profilen)
	   */
	public int calculateSimilarity(Profile p1, Profile p2) {
	    // 6 Profilattribute die gegeben sind
		int count = 6;
	    int matches = 0;

	    // neues Profil mit "angepassten" Angaben anhand des Suchprofils erstellen
	    
	    if (p1.isSmoker() == p2.isSmoker()) {
	    	matches++;
	    }
	    
	    if (p1.isGender() == p2.isGender()) {
	    	matches++;
	    }
	    
	    if (p1.getReligion() == p2.getReligion()) {
	    	matches++;
	    }
	    
	    if (p1.getBirthdate() == p2.getBirthdate()) {
	    	matches++;
	    }
	    
	    if (p1.getBodyHeight() == p2.getBodyHeight()) {
	    	matches++;
	    }
	    
	    if (p1.getHairColor() == p2.getHairColor()) {
	    	matches++;
	    }
	    
	    ArrayList<Info> infosOfP1 = getInfoOf(p1);
	    ArrayList<Info> infosOfP2 = getInfoOf(p2);

	    for (Info infoOfP1 : infosOfP1) {
	    	for (Info infoOfP2 : infosOfP2) {
	    		if (infoOfP1.getDescription() != null && infoOfP2.getDescription() != null) {
	    			if (infoOfP1.getDescription().getId() == infoOfP2.getDescription().getId()) {
	    				count++;
	    				if (infoOfP1.getInfoValue().equals(infoOfP2.getInfoValue())) {
	    					matches++;
	    					}
	    				}
	    			}
	    		if (infoOfP1.getSelection() != null && infoOfP2.getSelection() != null) {
	    			if (infoOfP1.getSelection().getId() == infoOfP2.getSelection().getId()) {
	    				count++;
	    				if (infoOfP1.getInfoValue().equals(infoOfP2.getInfoValue())) {
	    					matches++;
	    				}
	    			}
	    		}
	    		int result = Math.round(matches * (100f / count)); //bearbeiten

	    	    return result;
	    	}
	    }
		return matches;
	 }
	//Wie gehts weiter?
	
	private ArrayList<Info> getInfoOf(Profile p1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Notepad> getNotepadOfProfile(Notepad n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile createProfile(String firstname, String lastname, String email, java.util.Date birthdate,
			String haircolor, int bodyheight, boolean smoker, String religion, boolean gender)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}