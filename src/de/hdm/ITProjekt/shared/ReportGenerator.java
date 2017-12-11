package de.hdm.ITProjekt.shared;

import de.hdm.ITProjekt.shared.bo.Profile;

/**
 * Synchrones Interface welches das Interface RemoteService erweitert.
* In ihr finden sich sämtliche Methodensignaturen der Methoden, welche von
* der Klasse ReportGeneratorImpl zu implementieren sind.
* 
 * @author Thies, Kurda
 *
 */
@RemoteServiceRelativePath("rg")
public interface ReportGenerator extends RemoteService {

  public void init() throws IllegalArgumentException;

  public abstract ProfileReport createProfilReport(Profile p) throws IllegalArgumentException;

  public abstract AllNewProfileReport createAllNewProfilesReport(Profile p);

  public abstract AllProfilesReport createAllProfilesReport(Profile profile)
      throws IllegalArgumentException;

}