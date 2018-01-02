package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.bo.Description;

/**
 * Klasse, die die Aufgabe erfüllt, die Objekte einer persistenten Klasse auf die Datenbank
 * abzubilden und dort zu speichern. Die zu speichernden Objekte werden dematerialisiert und zu
 * gewinnende Objekte aus der Datenbank entsprechend materialisiert. Dies wird als indirektes
 * Mapping bezeichnet. Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden zur Suche, zum Speichern, Löschen und Modifizieren von Objekten.
 *
 * @see DBConnection
 * @see ProfileMapper
 * @see NotepadMapper
 * @see BlocklistMapper
 * @see InfoMapper
 * @see CharacteristicMapper
 * @see SelectionMapper
 *
 * @author Thies, Kurda
 */

public class DescriptionMapper {

  /**
   * Von der Mapper-Klasse DescriptionMapper kann nur eine Instanz erzeugt werden. Sie erfüllt die
   * Singleton-Eigenschaft. Dies geschieht mittels eines private default-Konstruktors und genau
   * einer statischen Variablen vom Typ DescriptionMapper, die die einzige Instanz der Klasse
   * darstellt.
   *
   *
   */
  private static DescriptionMapper DescriptionMapper = null;

  /**
   * Durch den Modifier "private" geschützter Konstruktor, der verhindert das weiter Instanzen der
   * Mapper-Klasse erzeugt werden können.
   *
   */
  protected DescriptionMapper() {}

  /**
   * Von der Klasse DescriptionMapper kann nur eine Instanz erzeugt werden. Sie erfüllt die
   * Singleton-Eigenschaft. Dies geschieht mittels eines private default-Konstruktors und genau
   * einer statischen Variablen vom Typ DescriptionMapper, die die einzige Instanz der Mapper-Klasse darstellt.
   */

  public static DescriptionMapper descriptionMapper() {
    if (DescriptionMapper == null) {
    	DescriptionMapper = new DescriptionMapper();
    }

    return DescriptionMapper;
  }


  /**
   * Die Methode findByName erfüllt eine Suchfunktion und liefert Objekte
   * des Typs Description aus der Datenbank zurück.
   *
   * @param name
   * 
   * @return Description - Ein Description-Objekt, in dem Informationen des Objekts Description
   *         aus der Datenbank gespeichert werden.
   */


  // evlt. findByName-Methode hinzufügen.

  /**
   * Die Methode findById implementiert die Suche nach genau einer id aus der Datenbank,
   * entsprechend wird genau ein Objekt zurückgegeben.
   *
   * @param id
   *
   * @return Description, Description-Objekt, das der übergegebenen id entspricht, bzw. null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Description findById(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery(
          "SELECT id, Name, Beschreibungstext FROM Characteristic WHERE id=" + id + " AND e_typ='d'");


      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein
       * Ergebnis vorliegt.
       */

      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
    	Description d = new Description();
        d.setId(rs.getInt("id"));
        d.setCharacteristicName(rs.getString("Name"));
        d.setDescriptiontext(rs.getString("Beschreibungstext"));


        return d;
      }
    } catch (SQLException a) {
      a.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Description-Tupel.
   *
   * @return Eine ArrayList mit Description-Objekten
   */
  public ArrayList<Description> findAll() {
    Connection con = DBConnection.connection();
    // Ergebnisvektor vorbereiten
    ArrayList<Description> result = new ArrayList<Description>();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT id, Name, Beschreibungstext FROM Characteristic WHERE e_typ='d'");


      // Für jeden Eintrag im Suchergebnis wird nun ein Description-Objekt
      // erstellt.

      while (rs.next()) {
    	Description d = new Description();
        d.setId(rs.getInt("id"));
        d.setCharacteristicName(rs.getString("Name"));
        d.setDescriptiontext(rs.getString("Beschreibungstext"));
        result.add(d);

      }

    } catch (SQLException e) {
      e.printStackTrace();
      ClientsideSettings.getLogger().severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Hinzufügen eines Description-Objekts in die Datenbank.
   *
   * @param d - das zu speichernde Objekt
   *
   * @return das an die Datenbank übergebene Objekt
   */
  public Description insert(Description d) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM characteristic ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * a erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
         */
        d.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO characteristic (id, Name, Beschreibungstext, e_typ) VALUES ("
            + d.getId() + ",'" + d.getCharacteristicName() + "','" + d.getDescriptiontext() + "','d')");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return d;
  }


  /**
   * Die Methode update modifiziert ein auf die Datenbank abgebildetes Description-Objekt.
   *
   * @param d - das Objekt, welches in der Datenbank geändert wird
   * @return das als Parameter übergebene Objekt
   */
  public Description update(Description d) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE characteristic SET Name='" + d.getCharacteristicName() + "', Beschreibungstext='"
          + d.getDescriptiontext() + "', e_typ='d' WHERE id=" + d.getId());

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Um Analogie zu insert(Description d) zu wahren, geben wir d zurück
    return d;
  }

  /**
   * Löschen eines auf die Datenbank abgebildeteten Description-Objekts
   *
   * @param d das aus der DB zu löschende Objekt
   */
  public void delete(Description d) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM Characteristic WHERE id=" + d.getId());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}