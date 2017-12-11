package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Blocklist;
import de.hdm.ITProjekt.shared.bo.Profile;

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
  public Blocklist findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM blocklists "
          + "WHERE id=" + id + " ORDER BY owner");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
    	Blocklist b = new Blocklist();
        b.setId(rs.getInt("id"));
        b.setOwnerId(rs.getInt("owner"));
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
   * Auslesen der Sperrliste.
   * 
   * @return Ein Vektor mit Sperrlisten-Objekten, die sämtliche Profile
   * repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   * oder ggf. auch leerer Vetor zurückgeliefert.
   */
  
  public Vector<Blocklist> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Blocklist> result = new Vector<Blocklist>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM blocklists "
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Sperrlisten-Objekt erstellt.
      while (rs.next()) {
    	  Blocklist b = new Blocklist();
        b.setId(rs.getInt("id"));
        b.setOwnerId(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(b);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen aller Sperrlisten eines durch Fremdschlüssel (ProfilID) gegebenen
   * Profilen.
   * 
   * @see findByOwner(Blocklist owner)
   * @param ownerID Schlüssel des zugehörigen Profils.
   * @return Ein Vektor mit Sperrlisten-Objekten, die die Sperrliste des
   *         betreffenden Profils repräsentiert. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Blocklist> findByOwner(int ownerID) {
    Connection con = DBConnection.connection();
    Vector<Blocklist> result = new Vector<Blocklist>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM blocklists "
          + "WHERE owner=" + ownerID + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Sperrlisten-Objekt erstellt.
      while (rs.next()) {
    	  Blocklist b = new Blocklist();
        b.setId(rs.getInt("id"));
        b.setOwnerId(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(b);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen der Sperrliste eines Profils (durch <code>Sperrlisten</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Profilobjekt, dessen Sperrzettel wir auslesen möchten.
   * @return Sperrzettel des Profils
   */
  public Vector<Blocklist> findByOwner(Profile owner) {

    /*
     * Wir lesen einfach die Profilnummer (Primärschlüssel) des Profil-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByOwner(owner.getId());
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
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM blocklists ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * b erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        b.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO blocklists (id, owner) " + "VALUES ("
            + b.getId() + "," + b.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Sperrliste.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Profil-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von p ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
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

      stmt.executeUpdate("UPDATE blocklists " + "SET owner=\"" + b.getOwnerID()
          + "\" " + "WHERE id=" + b.getId());

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

      stmt.executeUpdate("DELETE FROM blocklists " + "WHERE id=" + b.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Profile (<code>Profil</code>-Objekt) einer Sperrliste.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Sperrlisten</code>
   * -Objekt gelöscht wird.
   * 
   * @param p das <code>Profil</code>-Objekt, zu dem der Sperrliste gehört.
   */
  public void deleteBlocklistsOf(Profile p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM blocklists " + "WHERE owner=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen des zugehörigen <code>Sperrlisten</code>-Objekts zu einem gegebenen
   * Profil.
   * 
   * @param b das Sperrlisten, dessen Profil wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Profils darstellt
   */
  public Profile getOwner(Blocklist b) {
    /*
     * Wir bedienen uns hier einfach des ProfilMapper. Diesem geben wir
     * einfach den in dem Sperrlisten-Objekt enthaltenen Fremdschlüssel für den
     * Profilinhaber. Der ProfilMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return ProfileMapper.profileMapper().findByKey(b.getOwnerID());
  }

public void insertForProfile(Profile a, Profile b) {
	// TODO Auto-generated method stub
	
}

public void deleteLockFor(Profile remover, Profile remoter) {
	// TODO Auto-generated method stub
	
}

public Blocklist findAllForProfile(Profile profile) {
	// TODO Auto-generated method stub
	return null;
}

}