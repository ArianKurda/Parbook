package de.hdm.ITProjekt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.CommonSettings;
import de.hdm.ITProjekt.shared.LoginServiceAsync;
import de.hdm.ITProjekt.shared.ParbookService;
import de.hdm.ITProjekt.shared.ParbookServiceAsync;
import de.hdm.ITProjekt.shared.ReportGenerator;
import de.hdm.ITProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Profile;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen relevant sind.
 *
 * @author Thies, Kurda
 *
 */
public class ClientsideSettings extends CommonSettings {

  /**
   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst namens
   * <code>ParbookServiceAsync</code>.
   */

  private static ParbookServiceAsync parbookService = null;

  /**
   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst namens
   * <code>ReportGeneratorAsync</code>.
   */
  private static ReportGeneratorAsync reportGenerator = null;

  /**
   * Der momentane Benutzer wird gesetzt.
   */
  private static Profile currentUser = null;

  /**
   * Name des Client-seitigen Loggers.
   */
  private static final String LOGGER_NAME = "Partnerbörse Web Client";

  /**
   * Instanz des Client-seitigen Loggers.
   */
  private static final Logger log = Logger.getLogger(LOGGER_NAME);

  /**
   * Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
   *
   * @return die Logger-Instanz für die Server-Seite
   */
  public static Logger getLogger() {
    return log;
  }

  /**
   * Anlegen und Auslesen der applikationsweit eindeutigen ParbookService. Diese Methode
   * erstellt die ParbookService, sofern sie noch nicht existiert. Bei wiederholtem Aufruf
   * dieser Methode wird stets das bereits zuvor angelegte Objekt zurückgegeben.
   *
   * @return eindeutige Instanz des Typs <code>ParbookServiceAsync</code>
   * @author Thies, Kurda
   */
  public static ParbookServiceAsync getParbookService() {
  
	  /*
	   * Wenn es bisher noch keine parbookService-Instanz gab, wird hiermit nun
	   * eine erzeugt.
	   */
    if (parbookService == null) {
      	/*
    	 * In diesem Schritt wird die ParbookService instantiiert.
    	 */
      parbookService = GWT.create(ParbookService.class);
    }

    /*
     * Die ParbookService wird zurückgegeben.
     */
    return parbookService;
  }

  /**
   * Anlegen und Auslesen des applikationsweit eindeutigen ReportGenerators. Diese Methode erstellt
   * den ReportGenerator, sofern dieser noch nicht existiert. Bei wiederholtem Aufruf dieser Methode
   * wird stets das bereits zuvor angelegte Objekt zurückgegeben.
   * 
   * @return eindeutige Instanz des Typs <code>ReportGeneratorAsync</code>
   * @author Thies, Kurda
   */
  public static ReportGeneratorAsync getReportGenerator() {
  
	  /*
	   * Wenn es noch keine ReportGenerator-Instanz gab, wird diese hier erzeugt.
	   */
    if (reportGenerator == null) {
      
    	/*
    	 * Hier wird der ReportGenerator instantiiert.
    	 */
      reportGenerator = GWT.create(ReportGenerator.class);

      final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {
        @Override
        public void onFailure(Throwable caught) {
          ClientsideSettings.getLogger()
              .severe("Der ReportGenerator konnte nicht initialisiert werden!");
        }

        @Override
        public void onSuccess(Void result) {
          ClientsideSettings.getLogger().info("Der ReportGenerator wurde initialisiert.");
        }
      };

      reportGenerator.init(initReportGeneratorCallback);
    }

    /*
     * Report Generator wird zurückgegeben.
     */
    return reportGenerator;
  }

  /**
   * Auslesen des momentanen Benutzers
   *
   * @return currentUser Momentaner Benutzer
   */
  public static Profile getCurrentUser() {
    return currentUser;
  }

  /**
   * Setzen des momentanen Benutzers
   *
   * @param currentUser Momentaner Benutzer
   *
   */
  public static void setCurrentUser(Profile currentUser) {
    ClientsideSettings.currentUser = currentUser;
  }

public static LoginServiceAsync getLoginService() {
	// TODO Auto-generated method stub
	return null;
}

}
