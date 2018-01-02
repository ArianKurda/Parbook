package de.hdm.ITProjekt.shared.bo;

/**
 * Realisierung einer exemplarischen Eigenschaft. Eine Eigenschaft wird von einem Info besitzt.
 * 
 */
public class Characteristic extends BusinessObject {

  private static final long serialVersionUID = 1L;
  
  /**
   * Deklaration der Parameter
   * 
   * @param characteristicName Überbegriff, d.h. Begriffsbezeichnung eines Hobbies
   * @param descriptiontext: Der eigentliche Name eines Hobbies
   */
  private String characteristicName = "";
  
  /**
   *  Textuelle Beschreibung der Eigenschaft für ein Profil
   */
  private String descriptiontext = "";
  
  /**
   * Auslesen des Parameters Eigenschafts-Name
   * 
   * @return characteristicName
   */
  public String getCharacteristicName() {
	  return characteristicName;
  }
  
  /**
   * Setzen des Parameters Eigenschafts-Name
   * 
   * @param cn
   */
  public void setCharacteristicName(String cn) {
	  this.characteristicName = cn;
  }
  
  /**
   * Auslesen des Beschreibungstextes einer Eigenschaft eines Profils
   * 
   *@return descriptiontext, textuelle Beschreibung für ein Profil
   */
  public String getDescriptiontext() {
	  return descriptiontext;
  }
  
  /**
   * Setzen des Beschreibungstextes
   * 
   * @param descriptiontext
   */
  public void setDescriptiontext(String descriptiontext) {
	  this.descriptiontext = descriptiontext;
  }
  
  /**
   * Einfache textuelle Darstellung der jeweiligen Instanzen.
   */
	public String toString() {
		return super.toString() + " " + this.characteristicName + " " + this.descriptiontext;
	}

}