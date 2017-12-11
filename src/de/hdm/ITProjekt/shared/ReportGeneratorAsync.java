package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.report.AllProfilesReport;
import de.hdm.ITProjekt.shared.report.ProfileReport;


/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird semiautomatisch durch
 * das Google Plugin erstellt und gepflegt.
 * Es enthält sämtliche Methodensignaturen analog zum synchronen Interface, allerdings
 * sind sämtliche Rückgabetypen vom Typ void und es wird ein zusätzlicher Übergabeparameter
 * "AsyncCallback<>" übergeben. Dies ist notwendig um die asynchrone kommunikation zwischen
 * Client und Server zu realisieren.
 *
 * @author Thies, Kurda
 */
public interface ReportGeneratorAsync {

  void init(AsyncCallback<Void> callback);

  void createProfileReport(Profile p, AsyncCallback<ProfileReport> callback);
  
  void createAllProfilesReport(Profile profile, AsyncCallback<AllProfilesReport> callback);

//Müssen schauen was wir noch als Report wollen.



}