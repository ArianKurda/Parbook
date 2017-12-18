package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.bo.Selection;

/**
 * Mapper-Klasse, die die Aufgabe hat, die Objekte einer persistenten Klasse auf die Datenbank
 * abzubilden und dort zu speichern.
 * 
 * Die zu speichernden Objekte werden dematerialisiert und zu
 * gewinnende Objekte aus der Datenbank entsprechend materialisiert.
 * 
 * Dies wird als indirektes Mapping bezeichnet. Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden zur Suche, zum Speichern, Löschen und Modifizieren von Objekten.
 * 
 * @see DBConnection
 * @see ProfileMapper
 * @see NotepadMapper
 * @see BlocklistMapper
 * @see InfoMapper
 * @see CharacteristicMapper
 * @see DescriptionMapper
 * 
 *
 * @author Thies, Kurda
 */

public class SelectionMapper {


  /**
   * Von der Klasse SelectionMapper kann nur eine Instanz erzeugt werden. Sie erfüllt die
   * Singleton-Eigenschaft. Dies geschieht mittels eines private default-Konstruktors und genau
   * einer statischen Variablen vom Typ SelectionMapper, die die einzige Instanz der Klasse darstellt.
   *
   *
   */
  private static SelectionMapper SelectionMapper = null;

  /**
   * Durch den Modifier "private" geschützter Konstruktor, der verhindert das weiter Instanzen der
   * Mapper-Klasse erzeugt werden können.
   *
   */
  protected SelectionMapper() {}

  /**
   * Von der Klasse SelectionMapper kann nur eine Instanz erzeugt werden. Sie erfüllt die
   * Singleton-Eigenschaft. Dies geschieht mittels eines private default-Konstruktors und genau
   * einer statischen Variablen vom Typ SelectionMapper, die die einzige Instanz der Klasse darstellt.
   */
  public static SelectionMapper selectionMapper() {
    if (SelectionMapper == null) {
    	SelectionMapper = new SelectionMapper();
    }

    return SelectionMapper;

  }

  /**
   * Die Methode findByName erfüllt eine Suchfunktion und liefert Objekte des Typs Selection aus der
   * Datenbank zurück.
   *
   * @param name
   * @return Selection - Ein Selection-Objekt in dem Informationen des Objekts Selection aus der Datenbank gespeichert werden.
   */

  public Selection findByName(String name) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();
      Statement stmt2 = con.createStatement();

      ResultSet rs1 =
          stmt.executeQuery("SELECT id, Name, Beschreibungstext FROM Characteristic WHERE Name=\""
              + name + "\" AND e_typ='p_s'"); //was heißt das nochmal?


      if (rs1.next()) {
    	Selection s = new Selection();
        s.setId(rs1.getInt("id"));
        s.setName(rs1.getString("Name"));
        s.setDescriptiontext(rs1.getString("Beschreibungstext"));
        ResultSet rs2 =
            stmt2.executeQuery("SELECT Text FROM Alternative WHERE Selection_id=" + rs1.getInt("id"));
        ArrayList<String> al = new ArrayList<String>();
        while (rs2.next()) {
          al.add(rs2.getString("Text"));
        }
        s.setAlternatives(al);

        return s;
      }
    } catch (SQLException a) {
      a.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Die Methode findById implementiert die Suche nach genau einer id aus der Datenbank,
   * entsprechend wird genau ein Objekt zurückgegeben.
   *
   * @param id
   *
   * @return Selection-Objekt, das der übergegebenen id entspricht bzw. null bei nicht vorhandenem
   *         DB-Tupel.
   */
  public Selection findById(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();
      Statement stmt2 = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs1 = stmt.executeQuery(
          "SELECT id, Name, Beschreibungstext FROM Characteristic WHERE id=" + id + " AND e_typ='s'");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben werden. Prüfe, ob ein
       * Ergebnis vorliegt.
       */

      if (rs1.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
    	Selection s = new Selection();
        s.setId(rs1.getInt("id"));
        s.setName(rs1.getString("Name"));
        s.setDescriptiontext(rs1.getString("Beschreibungstext"));
        ResultSet rs2 =
            stmt2.executeQuery("SELECT Text FROM Alternative WHERE Selection_id=" + rs1.getInt("id"));
        ArrayList<String> al = new ArrayList<String>();
        while (rs2.next()) {
          al.add(rs2.getString("Text"));
        }
        s.setAlternatives(al);

        return s;
      }
    } catch (SQLException a) {
      a.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Selection-Tupel.
   *
   * @return Eine ArrayList mit Selection-Objekten
   */
  public ArrayList<Selection> findAll() {
    Connection con = DBConnection.connection();
    ArrayList<Selection> result = new ArrayList<Selection>();

    try {
      Statement stmt1 = con.createStatement();
      ResultSet rs1 = stmt1
          .executeQuery("SELECT id, Name, Beschreibungstext" + " FROM Characteristic WHERE e_typ='s'");

      Statement stmt2 = con.createStatement();



      while (rs1.next()) {
    	Selection s = new Selection();
        s.setId(rs1.getInt("id"));
        s.setName(rs1.getString("Name"));
        s.setDescriptiontext(rs1.getString("Beschreibungstext"));
        ResultSet rs2 = stmt2
            .executeQuery("SELECT Text FROM Alternative WHERE Selection_id=" + rs1.getInt("id"));
        ArrayList<String> al = new ArrayList<String>();
        while (rs2.next()) {
          al.add(rs2.getString("Text"));
        }
        s.setAlternatives(al);
        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.add(s);

      }

    } catch (SQLException e) {
      e.printStackTrace();
      ClientsideSettings.getLogger().severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
    }

    return result;
  }

  /**
   * Auslesen aller Selection-Tupel.
   *
   * @return Eine ArrayList mit Selection-Objekten, bei einem Fehler wird eine SQL-Exception ausgelöst.
   */
  public ArrayList<Selection> findAllProfilAtrribute() {
    Connection con = DBConnection.connection();
    // Ergebnisvektor vorbereiten
    ArrayList<Selection> result = new ArrayList<Selection>();

    try {
      Statement stmt1 = con.createStatement();
      ResultSet rs1 = stmt1.executeQuery(
          "SELECT id, Name, Beschreibungstext" + " FROM Characteristic WHERE e_typ='p_s'");

      Statement stmt2 = con.createStatement();

      while (rs1.next()) {
    	Selection s = new Selection();
        s.setId(rs1.getInt("id"));
        s.setName(rs1.getString("Name"));
        s.setDescriptiontext(rs1.getString("Beschreibungstext"));
        ResultSet rs2 = stmt2
            .executeQuery("SELECT Text FROM Alternative " + "WHERE Selection_id=" + rs1.getInt("id"));
        ArrayList<String> al = new ArrayList<String>();
        while (rs2.next()) {
          al.add(rs2.getString("Text"));
        }
        s.setAlternatives(al);
        result.add(s);

      }

    } catch (SQLException e) {
      e.printStackTrace();
      ClientsideSettings.getLogger().severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
    }

    return result;
  }

  /**
   * Hinzufügen eines Selection-Objekts in die Datenbank.
   *
   * @param s - das zu speichernde Objekt
   *
   * @return das an die Datenbank übergebene Objekt
   */
  public Selection insert(Selection s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();


      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Characteristic ");

      if (rs.next()) {

        s.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate(
            "INSERT INTO Eigenschaft (id, Name, " + "Beschreibungstext, e_typ) VALUES (" + s.getId()
                + ",'" + s.getName() + "','" + s.getDescriptiontext() + "','a')");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return s;
  }

  /**
   * Die Methode update modifiziert ein auf die Datenbank abgebildetes Selection-Objekt.
   *
   * @param s - das Objekt, welches in der Datenbank geändert wird
   * @return das als Parameter übergebene Objekt
   */
  public Selection update(Selection s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Eigenschaft SET Name='" + s.getName() + "', Beschreibungstext='"
          + s.getDescriptiontext() + "', e_typ='s' WHERE id=" + s.getId());

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Um Analogie zu insert(Selection s) zu wahren, geben wir s zurück
    return s;
  }

  /**
   * Löschen eines auf die Datenbank abgebildeteten Selection-Objekts.
   *
   * @param s das aus der DB zu löschende Objekt
   */
  public void delete(Selection s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM Characteristic " + "WHERE id=" + s.getId());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
