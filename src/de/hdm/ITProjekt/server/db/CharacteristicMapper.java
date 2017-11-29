package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Characteristic;
import de.hdm.ITProjekt.shared.bo.Info;

/**
 * Mapper-Klasse, die <code>Eigenschaft</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 */
public class CharacteristicMapper {

  /**
   * Die Klasse CharacterMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see characterMapper()
   */
  private static CharacteristicMapper characterMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected CharacteristicMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>CharacterMapper.characterMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>CharacterMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> CharacterMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>EigenschaftMapper</code>-Objekt.
   * @see characterMapper
   */
  public static CharacteristicMapper characterMapper() {
    if (characterMapper == null) {
      characterMapper = new CharacteristicMapper();
    }

    return characterMapper;
  }

  /**
   * Suchen eines Eigenschaftes mit vorgegebener Eigenschaftnummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Eigenschaft-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   * nicht vorhandenem DB-Tupel.
   */
  public Characteristic findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM characters "
          + "WHERE id=" + id + " ORDER BY owner");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
    	  Characteristic c = new Characteristic();
        c.setID(rs.getInt("id"));
        c.setOwnerID(rs.getInt("owner"));
        return c;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Eigenschaften.
   * 
   * @return Ein Vektor mit Eigenschaft-Objekten, die sämtliche Profile
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Characteristic> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Characteristic> result = new Vector<Characteristic>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM characters "
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Eigenschaft-Objekt erstellt.
      while (rs.next()) {
    	  Characteristic c = new Characteristic();
        c.setID(rs.getInt("id"));
        c.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(c);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen aller Eigenschaften eines durch Fremdschlüssel (Infonr.) gegebenen
   * Profilen.
   * 
   * @see findByOwner(Eigenschaft owner)
   * @param ownerID Schlüssel der zugehörigen Eigenschaft.
   * @return Ein Vektor mit Eigenschaft-Objekten, die die Eigenschaft des
   *         betreffenden Infos repräsentiert. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Characteristic> findByOwner(int ownerID) {
    Connection con = DBConnection.connection();
    Vector<Characteristic> result = new Vector<Characteristic>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM characters "
          + "WHERE owner=" + ownerID + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Eigenschaft-Objekt erstellt.
      while (rs.next()) {
    	  Characteristic c = new Characteristic();
        c.setID(rs.getInt("id"));
        c.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(c);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen der Eigenschaft eines Infos (durch <code>Eigenschaft</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Infoobjekt, dessen Eigenschaft wir auslesen möchten.
   * @return Eigenschaft des Infos
   */
  public Vector<Characteristic> findByOwner(Info owner) {

    /*
     * Wir lesen einfach die Eigenschaftummer (Primärschlüssel) des Eigenschaft-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByOwner(owner.getID());
  }

  /**
   * Einfügen eines <code>Eigenschaft</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param m das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   * <code>id</code>.
   */
  public Characteristic insert(Characteristic c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM characters ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * c erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        c.setID(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO characters (id, owner) " + "VALUES ("
            + c.getID() + "," + c.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Eigenschaftes.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Profil-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von p ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return c;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param c das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Characteristic update(Characteristic c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE characters " + "SET owner=\"" + c.getOwnerID()
          + "\" " + "WHERE id=" + c.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Character c) zu wahren, geben wir c zurück
    return c;
  }

  /**
   * Löschen der Daten eines <code>Eigenschaft</code>-Objekts aus der Datenbank.
   * 
   * @param c das aus der DB zu löschende "Objekt"
   */
  public void delete(Characteristic c) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM characters " + "WHERE id=" + c.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Merkzettel (<code>Eigenschaft</code>-Objekt) eines Profils.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Eigenschaft</code>
   * -Objekt gelöscht wird.
   * 
   * @param m das <code>Eigenschaft</code>-Objekt, zu dem das Profil gehört.
   */
  public void deleteNotepadsOf(Info i) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM characters " + "WHERE owner=" + i.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen des zugehörigen <code>Eigenschaft</code>-Objekts zu einer gegebenen
   * Info.
   * 
   * @param c die Eigenschaft, dessen Info wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Infos darstellt
   */
  public Info getOwner(Characteristic c) {
    /*
     * Wir bedienen uns hier einfach des InfoMapper. Diesem geben wir
     * einfach den in dem Eigenschaft-Objekt enthaltenen Fremdschlüssel für den
     * Infoinhaber. Der InfoMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return InfoMapper.infoMapper().findByKey(c.getOwnerID());
  }

}