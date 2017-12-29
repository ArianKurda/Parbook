package de.hdm.ITProjekt.shared.bo;

public class Blocklist extends BusinessObject {

  private static final long serialVersionUID = 1L;
  
  	//Eingeloggtes Profil von dem die Kontaktsperre ausgeht.
  	private Profile blockerProfile = null;
  
  	//Profil, das gesperrt werden soll.
  	private Profile blockedProfile = null;
	
  	/**
  	 * Eingeloggtes Profil auslesen.
	 * 
	 * @return Nutzerprofil
	 */
	public Profile getBlockerProfile() {
		return blockerProfile;
	}
	
	public void setBlockerProfile(Profile blockerProfile) {
		this.blockerProfile = blockerProfile;
	}
	
	/**
	 * Auslesen des gesperrten Profils.
	 * 
	 * @return toProfile
	 */
	public Profile getBlockedProfile() {
		return blockedProfile;
	}
	
	/**
	 * Profil, das gesperrt werden soll
	 * 
	 * @param toProfile
	 */
	public void setBlockedProfile(Profile blockedProfile) {
		this.blockedProfile = blockedProfile;
	}
	
	/*
	 * Textuelle Darstellung der jeweiligen Instanz.
	 */
	public String toString() {
		return super.toString()+ this.blockerProfile + " "+ this.blockedProfile;
	} 
}