package de.hdm.ITProjekt.shared.report;

/**
 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur Verfügung gestellten
 * Report-Objekte in ein menschenlesbares Format zu überführen.
 *  In dieser Klasse werden die Signaturen
 * der Methoden deklariert, die für die Prozessierung der Quellinformation zuständig sind.
 * </p>
 *
 * @author Thies, Kurda
 */
public abstract class ReportWriter {

  public abstract void process(ProfileReport r);

  public abstract void process(AllProfilesReport r);
  
  // Evtl. weitere Methoden.


}