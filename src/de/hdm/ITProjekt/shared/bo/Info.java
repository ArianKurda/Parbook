package de.hdm.ITProjekt.shared.bo;

/**
 * Realisierung eines exemplarischen Infoobjekts eines Profils.
 * Ein Profil besitzt also ein oder mehrere Infos.
 * 
 * @author kurda
 */
public class Info extends BusinessObject {

  private static final long serialVersionUID = 1L;
  
  /**
   * Deklaration der Parameter eines Infoobjektes
   *
   * @param infoText: Informationsbezeichnung des Profilinfos
   * @param profile: Nutzerprofil der anzulegenden Info.
   * @param searchProfile: Suchprofil der anzulegenden Info.
   * @param selection: Auswahl einer Profilinfo.
   * @param description: Beschreibung der Profilinfo.
   */
  private String infoValue = "";
  private Profile profile;
  private int profileId;
  private SearchProfile searchProfile;
  private Selection selection;
  private int selectionId;
  private Description description;
  private int descriptionId;

  /**
   * Auslesen des Parameters infoText
   * 
   * @param infoText
   */
  public String getInfoValue() {
	  return infoValue;
  }
  
  /**
   * Setzen des Parameters infoText
   * 
   * @param infoText
   */
  public void setInfoValue(String infoValue) {
	  this.infoValue = infoValue;
  }
  
  /**
	 * Auslesen des Nutzerprofils einer Profilinfo
	 * 
	 * @return profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Setzen des Nutzerprofils einer Profilinfo
	 * 
	 * @param profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public int getProfileId() {
		return profileId;
	}
	
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * Auslesen des Suchprofils einer Profilinfo
	 * 
	 * @return searchProfile
	 */

	public SearchProfile getSearchProfile() {
		return searchProfile;
	}

	/**
	 * Setzen des Suchprofils einer Profilinfo
	 * 
	 * @param searchProfile
	 */

	public void setSearchProfile(SearchProfile searchProfile) {
		this.searchProfile = searchProfile;
	}
  
  /**
	 * Auslesen einer Auswahl einer Profilinfo
	 * 
	 * @return selection
	 */

	public Selection getSelection() {
		return selection;
	}

	/**
	 * Setzen einer Auswahl einer Profilinfo.
	 * 
	 * @param selection
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	
	public int getSelectionId(){
		return selectionId;
	}
	
	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}

	/**
	 * Auslesen einer textuellen Beschreibung einer Profilinfo
	 * 
	 * @return description
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Setzen einer textuellen Beschreibung einer Profilinfo
	 * 
	 * @param description
	 */
	public void setDescription(Description description) {
		this.description = description;
	}
	
	public int getDescriptionId() {
		return descriptionId;
	}
	
	public void setDescriptionId(int descriptionId) {
		this.descriptionId = descriptionId;
	}
	
	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */
	public String toString() {
		return super.toString() + " " + " " + this.selection + " " + this.description + " "
				+ this.profile + " " + this.searchProfile;
	}
}