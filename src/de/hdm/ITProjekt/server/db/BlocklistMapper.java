package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.ITProjekt.shared.bo.*;;

/**
 * Mapper-Klasse, die <code>Sperrliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 */
public class BlocklistMapper {

  /**
   * Die Klasse BlocklistMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see blocklistMapper()
   */
  private static BlocklistMapper blocklistMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected BlocklistMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>BlocklistMapper.blocklistMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>BlocklistMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> BlocklistMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>BlocklistMapper</code>-Objekt.
   * @see profilMapper
   */
  public static BlocklistMapper blocklistMapper() {
    if (blocklistMapper == null) {
      blocklistMapper = new BlocklistMapper();
    }

    return blocklistMapper;
  }

  /**
   * Suchen einer Sperrliste mit vorgegebener Sperrlistennummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Sperrlisten-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Blocklist findById(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT * FROM blocklist WHERE ID='" + id + "ORDER BY fromProfile");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
    	Blocklist b = new Blocklist();
        b.setId(rs.getInt("ID"));
        return b;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }
  
  /**
   * Einfügen eines <code>Sperrlisten</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param b das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Blocklist insert(Blocklist b) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM blocklist");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * b erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        b.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO blocklist (id, fromProfile, toProfile) " + "VALUES ("
        		+ b.getId() 
        		+ "," 
        		+ b.getFromProfile().getId()
        		+ ","
        		+ b.getToProfile().getId()
        		+ "')");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Sperrliste.
     */
    return b;
  }
  
  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param b das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Blocklist update(Blocklist b) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE blocklist SET fromProfile="
    		  + b.getFromProfile()
    		  + "',"
    		  + "toProfile="
    		  + b.getToProfile()
    		  + "',"
    		  + "WHERE ID="
    		  + b.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Blocklist b) zu wahren, geben wir b zurück
    return b;
  }
  
  /**
   * Löschen der Daten eines <code>Sperrlisten</code>-Objekts aus der Datenbank.
   * 
   * @param b das aus der DB zu löschende "Objekt"
   */
  public void delete(Blocklist b) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM blocklist " + "WHERE ID=" + b.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen aller Kontaktsperren eines Profils.
   * 
   * @param p das Profil, dessen Sperrliste wir auslesen möchten
   * @return Sperrliste des Profils
   */
  
  public ArrayList<Profile> findAll(Profile p) {
		  Connection con = DBConnection.connection();
		  
		  //Ergebnis vorbereiten
		  ArrayList<Profile> result = new ArrayList<>();
		  
		  try {
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery(
					  "SELECT toProfile" + "FROM profile WHERE fromProfile=" + p.getId());
			  
			  //Für jeden Eintrag im Suchergebnis wird nun ein Blocklist-Objekt erstellt.
			  while (rs.next()) {
				  Profile profile = ProfileMapper.profileMapper().findById(rs.getInt("toProfile"));
				  result.add(p);

			  }
		  }
		  catch (SQLException e2) {
			  e2.printStackTrace();
		  }
		  
		  //Ergebnis zurück geben
		  return result;

  }

}