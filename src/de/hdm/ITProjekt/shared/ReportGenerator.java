package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.report.AllProfilesReport;
import de.hdm.ITProjekt.shared.report.ProfileReport;

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

  public abstract ProfileReport createProfileReport(Profile p) throws IllegalArgumentException;
  
  public abstract AllProfilesReport createAllProfilesReport(Profile profile)
	      throws IllegalArgumentException;

// Was wollen wir noch als Report?


}