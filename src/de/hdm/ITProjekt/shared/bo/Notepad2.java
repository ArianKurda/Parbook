package de.hdm.ITProjekt.shared.bo;

import java.util.ArrayList;

/**
 * Die Klasse Kontaktsperre wird benötigt, um zu realisieren, dass
 * Nutzer andere Nutzer merken können.
 * Sie enthält eine ArrayList an Profil (gemerkte Profile)
 * Außerdem eine merker und gemerkter ID
 *
 * @author kurda
 */
public class Notepad extends BusinessObject {

  private static final long serialVersionUID = 1L;

  /**
   * Deklaration der Parameter eines Merkzettels
   *
   * @param gemerkteProfile Dieser Parameter ist eine ArrayList vom Typ Profil. In dieser ArrayList
   * werden alle gemerkten Profile gespeichert.
   * @param gemerkterId Die Id des Profils, welches gemerkt wurde.
   * @param merkerID Die Id des Profils, dass sich andere Profile merkt.
   */
  private ArrayList<Profile> noticedProfile;
  private int noticedID = 0;
  private int flagID = 0;


  /**
   * Aulesen der flagID (MerkerID)
   *
   * @return flagID
   */
  public int getFlagID() {
    return flagID;
  }

  /**
   * Setzen der FlagID
   *
   * @param flagID
   */
  public void setFlagID(int flagID) {
    this.flagID = flagID;
  }

  /**
   * Auslesen der gemerkten Profile
   *
   * @return noticedProfile
   */
  public ArrayList<Profile> getnoticedProfile() {
	return noticedProfile;
  }


  /**
   * Setzen der gemerkten Profile
   *
   * @param noticedProfile
   */
  public void setnoticedProfile(ArrayList<Profile> noticedProfile) {
    this.noticedProfile = noticedProfile;
  }

  /**
   * Ein neues gemerktes Profil hinzufügen
   *
   * @param p
   */
  public void addnoticedProfil(Profile p) {
    noticedProfile.add(p);
  }

  /**
   * Auslesen der noticedID (GemerkterID)
   *
   * @return noticedID
   */
  public int getNoticedID() {
    return noticedID;
  }

  /**
   * Setzen der NoticedID
   *
   * @param noticedID
   */
  public void setNoticedID(int noticedID) {
    this.noticedID = noticedID;
  }

}
