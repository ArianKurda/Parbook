package de.hdm.ITProjekt.shared.report;

import java.io.Serializable;

/**
 * Report für das anzeigen eines Profils
 * @author Kurda
 */
public class ProfileReport extends SimpleReport implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int profileId;

  public int getProfileId() {
    return profileId;
  }

  public void setProfileId(int profilId) {
    this.profileId = profileId;
  }

}