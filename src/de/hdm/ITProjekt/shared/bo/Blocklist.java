package de.hdm.ITProjekt.shared.bo;

public class Blocklist extends BusinessObject {

  private static final long serialVersionUID = 1L;
  
  	/*
  	 * Eingeloggtes Profil von dem die Kontaktsperre ausgeht.
  	 */
  	private Profile fromProfile = null;
  
  	/*
  	 * Profil, das gesperrt werden soll.
  	 */
  	private Profile toProfile = null;
	
  	/*
  	 * Eingeloggtes Profil auslesen.
	 * 
	 * @return Nutzerprofil
	 */
	public Profile getFromProfile() {
		return fromProfile;
	}
	
	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}
	
	/**
	 * Auslesen des gesperrten Profils.
	 * 
	 * @return Gesperrtes Profil
	 */
	public Profile getToProfile() {
		return toProfile;
	}
	
	/**
	 * Profil, das gesperrt werden soll
	 * 
	 * @param toProfile Profil, das gesperrt wird.
	 */
	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}
	
	/*
	 * Textuelle Darstellung der jeweiligen Instanz.
	 */
	public String toString() {
		return super.toString()+ this.fromProfile + " "+ this.toProfile;
	}
  
}

