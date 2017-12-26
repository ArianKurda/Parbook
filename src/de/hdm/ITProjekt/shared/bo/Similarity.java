package de.hdm.ITProjekt.shared.bo;

import de.hdm.ITProjekt.shared.bo.BusinessObject;


/**
 * Realisierung eines exemplarischen Profilkontos. Ein Profil besitzt einen Inhaber
 * sowie eine Reihe von Attributen.
 * 
 * @author kurda
 */
public class Similarity extends BusinessObject {

  private static final long serialVersionUID = 1L;

	//Profil des eingeloggten Nutzers
  	private Profile fromProfile = null;

	//Profil, das zur Aehnlichkeitsberechnung mit dem eingeloggten Profil herangezogen wird.
	private Profile toProfile = null;

	//Berechneter Aehnlichkeitswert
	private double similarityValue = 0;

	/**
	 * Auslesen des Nutzerprofils
	 * 
	 * @return fromProfile
	 */

	public Profile getFromProfile() {
		return fromProfile;
	}

	/**
	 * Setzen des Nutzerprofils
	 * 
	 * @param fromProfile
	 */
	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}

	/**
	 * Auslesen des Profils, das zur Aehnlichkeitsberechnung herangezogen wird.
	 * 
	 * @return toProfile
	 */
	public Profile getToProfile() {
		return toProfile;
	}

	/**
	 * Setzen des Profils, das zur Aehnlichkeitsberechnung herangezogen wird.
	 * 
	 * @param toProfile
	 */
	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	/**
	 * Auslesen des Aehnlichkeitswertes der beiden Profile
	 * 
	 * @return similarityValue
	 */
	public double getSimilarityValue() {
		return similarityValue;
	}

	/**
	 * Setzen des Ähnlichkeitswertes der beiden Profile
	 * 
	 * @param similarityValue
	 */
	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */
	public String toString() {
		return super.toString() + this.fromProfile + " " + this.toProfile + " " + this.similarityValue;
	}
}