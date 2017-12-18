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
   * @param name Überbegriff, d.h. Begriffsbezeichnung eines Hobbies
   * @param descriptiontext: Der eigentliche Name eines Hobbies
   */
  private String name = "";
  
  private String descriptiontext = "";
  
  /**
   * Auslesen des Parameters Name
   * 
   * @return name
   */
  public String getName() {
	  return name;
  }
  
  /**
   * Setzen des Parameters Name
   * 
   * @param name
   */
  public void setName(String name) {
	  this.name = name;
  }
  
  /**
   * Auslesen des Beschreibungstextes
   * 
   *@return descriptiontext 
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

}