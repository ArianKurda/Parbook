package de.hdm.ITProjekt.shared.report;

import java.io.Serializable;
import java.util.Date;

import de.hdm.ITProjekt.shared.bo.Profile;

/**
 * Basisklasse aller Reports. Reports sind als <code>Serializable</code> deklariert, damit sie von
 * dem Server an den Client gesendet werden können.
 * Ein Report besitzt eine Reihe von Standardelementen. Sie werden mittels Attributen modelliert und
 * dort dokumentiert.
 *
 * @see Report
 * @author Thies, Kurda
 */
public abstract class Report implements Serializable {


  private static final long serialVersionUID = 1L;

  private Profile profile = null;
  private Paragraph name = null;
  
  /**
   * ein Composite Paragraph für die Profil Attribute
   */
  private CompositeParagraph attribute = null;
  
  /**
   * ein Composite Paragraph für die Bezeichnungen der Profil Attribute
   */
  private CompositeParagraph attributeDes = null;

  /**
   * ein Paragraph für die Ähnlichkeit
   */
  private Paragraph match = null; //TODO

  /**
   * Titel des Reports
   */
  private String title = "Report";
  /**
   * Unterüberschrift des Reports
   */
  private String subtitle = "";
  /**
   * Datum der Erstellung des Reports
   */
  private Date created = new Date();

  
  public Paragraph getName() {
    return name;
  }

  public void setName(Paragraph name) {
    this.name = name;
  }

  /**
   * Setzen der Profilattribute
   *
   * @param attribute Text dbuteer Attri
   */
  public void setProfileAttribute(CompositeParagraph attribute) {
    this.attribute = attribute;
  }

  public CompositeParagraph getProfileAttribute() {
    return attribute;
  }

  public void setProfileAttributeDes(CompositeParagraph attributeDes) {
    this.attributeDes = attributeDes;
  }

  public CompositeParagraph getProfilAttributeDes() {
    return attributeDes;
  }

  /**
   * Auslesen der Ähnlichkeit.
   *
   * @return Match.
   */
  public Paragraph getMatch() {
    return match;
  }

  /**
   * Setzen der Ähnlichkeit.
   *
   * @param match 
   */
  public void setMatch(Paragraph match) {
    this.match = match;
  }

  /**
   * Auslesen des Titels.
   *
   * @return Titeltext
   */
  public String getTitle() {
    return title;
  }
  /**
   * Setzen des Titels.
   *
   * @param title Titeltext
   */
  public void setTitle(String title) {
	  this.title = title;
  }

  /**
   * Setzen der Unterüberschrift.
   *
   * @param title Titeltext
   */
  public void setSubTitle(String subtitle) {
    this.subtitle = subtitle;
  }
  /**
   * Auslesen der Unterüberschrift
   *
   * @return Unterüberschrift
   */
  public String getSubTitle() {
    return subtitle;
  }


  /**
   * Auslesen des Erstellungsdatums.
   *
   * @return Datum der Erstellung des Reports
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Setzen des Erstellungsdatums.
   *
   * @param created Zeitpunkt der Erstellung
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * gibt das gesetzte Profil zurück
   * @return Profil
   */
  public Profile getProfile() {
    return profile;
  }
/**
 * setzen des Profils
 * @param profile
 */
  public void setProfil(Profile profile) {
    this.profile = profile;
  }
}
