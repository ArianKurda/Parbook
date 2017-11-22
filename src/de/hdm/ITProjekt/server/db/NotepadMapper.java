package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.Vector;
import de.hdm.ITProjekt.shared.bo.Notepad;
import de.hdm.ITProjekt.shared.bo.Profile;

/**
 * Mapper-Klasse, die <code>Merkzettel</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 */
public class NotepadMapper {

  /**
   * Die Klasse NotepadMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see notepadMapper()
   */
  private static NotepadMapper notepadMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected NotepadMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>NotepadMapper.profilMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>ProfilMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> NotepadMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>MerkzettelMapper</code>-Objekt.
   * @see profilMapper
   */
  public static NotepadMapper notepadMapper() {
    if (notepadMapper == null) {
      notepadMapper = new NotepadMapper();
    }

    return notepadMapper;
  }

  /**
   * Suchen eines Merkzettels mit vorgegebener Merkzettelnummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Merkzettel-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Notepad findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM notepads "
          + "WHERE id=" + id + " ORDER BY owner");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Notepad n = new Notepad();
        n.setId(rs.getInt("id"));
        n.setOwnerID(rs.getInt("owner"));
        return n;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Merkzettel.
   * 
   * @return Ein Vektor mit Merkzettel-Objekten, die sämtliche Profile
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Notepad> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Notepad> result = new Vector<Notepad>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM notepads "
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Merkzettel-Objekt erstellt.
      while (rs.next()) {
    	  Notepad n = new Notepad();
        n.setId(rs.getInt("id"));
        n.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(n);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen aller Profile eines durch Fremdschlüssel (Kundennr.) gegebenen
   * Kunden.
   * 
   * @see findByOwner(Merkzettel owner)
   * @param ownerID Schlüssel des zugehörigen Profils.
   * @return Ein Vektor mit Merkzettel-Objekten, die dden Merkzettel des
   *         betreffenden Profils repräsentiert. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Notepad> findByOwner(int ownerID) {
    Connection con = DBConnection.connection();
    Vector<Notepad> result = new Vector<Notepad>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM notepads "
          + "WHERE owner=" + ownerID + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Merkzettel-Objekt erstellt.
      while (rs.next()) {
    	  Notepad n = new Notepad();
        n.setId(rs.getInt("id"));
        n.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(n);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen des Merkzettels eines Profils (durch <code>Merkzettel</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Profilobjekt, dessen Merkzettel wir auslesen möchten.
   * @return Merkzettel des Profils
   */
  public Vector<Notepad> findByOwner(Profile owner) {

    /*
     * Wir lesen einfach die Profilnummer (Primärschlüssel) des Profil-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByOwner(owner.getId());
  }

  /**
   * Einfügen eines <code>Merkzettel</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param m das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Notepad insert(Notepad n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM notepads ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * p erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        n.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO accounts (id, owner) " + "VALUES ("
            + n.getId() + "," + n.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Merkzettels.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Profil-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von p ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return n;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param p das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Notepad update(Notepad n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE notepads " + "SET owner=\"" + n.getOwnerID()
          + "\" " + "WHERE id=" + n.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Merkzettel m) zu wahren, geben wir m zurück
    return n;
  }

  /**
   * Löschen der Daten eines <code>Merkzettel</code>-Objekts aus der Datenbank.
   * 
   * @param m das aus der DB zu löschende "Objekt"
   */
  public void delete(Notepad n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM notepads " + "WHERE id=" + n.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Merkzettel (<code>Merkzettel</code>-Objekt) eines Profils.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Merkzettel</code>
   * -Objekt gelöscht wird.
   * 
   * @param m das <code>Merkzettel</code>-Objekt, zu dem das Profil gehört.
   */
  public void deleteNotepadsOf(Profile p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM notepads " + "WHERE owner=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen des zugehörigen <code>Merkzettel</code>-Objekts zu einem gegebenen
   * Profil.
   * 
   * @param p das Merkzettel, dessen Profil wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Profils darstellt
   */
  public Profile getOwner(Notepad n) {
    /*
     * Wir bedienen uns hier einfach des ProfilMapper. Diesem geben wir
     * einfach den in dem Merkzettel-Objekt enthaltenen Fremdschlüssel für den
     * Profilinhaber. Der ProfilMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return ProfileMapper.profileMapper().findByKey(n.getOwnerID());
  }

}