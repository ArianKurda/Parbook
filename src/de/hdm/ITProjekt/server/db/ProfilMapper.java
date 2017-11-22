package de.hdm.ITProjekt.server.db;


import java.sql.*;
import java.util.Vector;
import de.hdm.ITProjekt.shared.bo.Profil;

/**
 * Mapper-Klasse, die <code>Profil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kurda
 */
public class ProfilMapper {

  /**
   * Die Klasse ProfilMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see profilMapper()
   */
  private static ProfilMapper profilMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected ProfilMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>ProfilMapper.profilMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>ProfilMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> ProfilMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>ProfilMapper</code>-Objekt.
   * @see profilMapper
   */
  public static ProfilMapper profilMapper() {
    if (profilMapper == null) {
      profilMapper = new ProfilMapper();
    }

    return profilMapper;
  }

  /**
   * Suchen eines Profils mit vorgegebener Profilnummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param id Primärschlüsselattribut (->DB)
   * @return Profil-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Profil findByKey(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM profiles "
          + "WHERE id=" + id + " ORDER BY owner");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Profil p = new Profil();
        p.setId(rs.getInt("id"));
        p.setOwnerID(rs.getInt("owner"));
        return p;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

  /**
   * Auslesen aller Profile.
   * 
   * @return Ein Vektor mit Profil-Objekten, die sämtliche Profile
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Profil> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Profil> result = new Vector<Profil>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM profiles "
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Profil-Objekt erstellt.
      while (rs.next()) {
        Profil p = new Profil();
        p.setId(rs.getInt("id"));
        p.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(p);
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
   * @see findByOwner(Profil owner)
   * @param ownerID Schlüssel des zugehörigen Profils.
   * @return Ein Vektor mit Profil-Objekten, die das Profil des
   *         betreffenden Nutzers repräsentiert. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Profil> findByOwner(int ownerID) {
    Connection con = DBConnection.connection();
    Vector<Profil> result = new Vector<Profil>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM profiles "
          + "WHERE owner=" + ownerID + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Profil-Objekt erstellt.
      while (rs.next()) {
        Profil p = new Profil();
        p.setId(rs.getInt("id"));
        p.setOwnerID(rs.getInt("owner"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(p);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Auslesen des Profils eines Nutzers (durch <code>Profil</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Nutzerobjekt, dessen Profil wir auslesen möchten.
   * @return Profil des Kunden
   */
  public Vector<Profil> findByOwner(Profil owner) {

    /*
     * Wir lesen einfach die Profilnummer (Primärschlüssel) des Profil-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByOwner(owner.getId());
  }

  /**
   * Einfügen eines <code>Profil</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Profil insert(Profil p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM profiles ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * p erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        p.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO accounts (id, owner) " + "VALUES ("
            + p.getId() + "," + p.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Accounts.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Profil-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von p ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return p;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param p das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Profil update(Profil p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE accounts " + "SET owner=\"" + p.getOwnerID()
          + "\" " + "WHERE id=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Profil p) zu wahren, geben wir p zurück
    return p;
  }

  /**
   * Löschen der Daten eines <code>Profil</code>-Objekts aus der Datenbank.
   * 
   * @param p das aus der DB zu löschende "Objekt"
   */
  public void delete(Profil p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM profiles " + "WHERE id=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen des Profils (<code>Profil</code>-Objekt) eines Nutzers.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Profil</code>
   * -Objekt gelöscht wird.
   * 
   * @param p das <code>Profil</code>-Objekt, zu dem der Nutzer gehört.
   */
  public void deleteProfilOf(Profil p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM profiles " + "WHERE owner=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen des zugehörigen <code>Profil</code>-Objekts zu einem gegebenen
   * Nutzer.
   * 
   * @param p das Profil, dessen Inhaber wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Profils darstellt
   */
  public Profil getOwner(Profil p) {
    /*
     * Wir bedienen uns hier einfach des CustomerMapper. Diesem geben wir
     * einfach den in dem Account-Objekt enthaltenen Fremdschlüssel für den
     * Kontoinhaber. Der CustomerMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return ProfilMapper.profilMapper().findByKey(p.getOwnerID());
  }

}

