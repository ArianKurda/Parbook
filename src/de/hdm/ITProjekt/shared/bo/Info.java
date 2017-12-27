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
  private String infoText = "";
  private Profile profile;
  private SearchProfile searchProfile;
  private Selection selection;
  private Description description;

  /**
   * Auslesen des Parameters infoText
   * 
   * @param infoText
   */
  public String getInfoText() {
	  return infoText;
  }
  
  /**
   * Setzen des Parameters infoText
   * 
   * @param infoText
   */
  public void setInfoText(String infoText) {
	  this.infoText = infoText;
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
	
	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */
	public String toString() {
		return super.toString() + " " + " " + this.selection + " " + this.description + " "
				+ this.profile + " " + this.searchProfile;
	}

}