package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.ITProjekt.shared.bo.Profile;

/**
 * Mapper-Klasse, die <code>Account</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see CustomerMapper, TransactionMapper
 * @author Thies
 */
public class ProfileMapper {

  /**
   * Die Klasse ProfileMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see accountMapper()
   */
  private static ProfileMapper profileMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected ProfileMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>AccountMapper.accountMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>AccountMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> AccountMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>AccountMapper</code>-Objekt.
   * @see accountMapper
   */
  public static ProfileMapper profileMapper() {
    if (profileMapper == null) {
      profileMapper = new ProfileMapper();
    }

    return profileMapper;
  }

  /**
   * Suchen eines Kontos mit vorgegebener Kontonummer. Da diese eindeutig ist,
   * wird genau ein Objekt zur�ckgegeben.
   * 
   * @param string Primärschlüsselattribut (->DB)
   * @return Konto-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   *         nicht vorhandenem DB-Tupel.
   */
  public Profile findByKey(String string) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM profiles "
          + "WHERE id=" + string + " ORDER BY owner");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Profile p = new Profile();
        p.setID(rs.getInt("id"));
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
   * Auslesen aller Konten.
   * 
   * @return Ein Vektor mit Account-Objekten, die sämtliche Konten
   *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
   *         oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Profile> findAll() {
    Connection con = DBConnection.connection();

    // Ergebnisvektor vorbereiten
    Vector<Profile> result = new Vector<Profile>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM profiles "
          + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
      while (rs.next()) {
        Profile p = new Profile();
        p.setID(rs.getInt("id"));
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
   * Auslesen aller Konten eines durch Fremdschlüssel (Kundennr.) gegebenen
   * Kunden.
   * 
   * @see findByOwner(Customer owner)
   * @param ownerID Schlüssel des zugehörigen Kunden.
   * @return Ein Vektor mit Account-Objekten, die sämtliche Konten des
   *         betreffenden Kunden repräsentieren. Bei evtl. Exceptions wird ein
   *         partiell gefüllter oder ggf. auch leerer Vetor zurückgeliefert.
   */
  public Vector<Profile> findByOwner(int ownerID) {
    Connection con = DBConnection.connection();
    Vector<Profile> result = new Vector<Profile>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM profiles "
          + "WHERE owner=" + ownerID + " ORDER BY id");

      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
      while (rs.next()) {
    	  Profile p = new Profile();
        p.setID(rs.getInt("id"));
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
   * Auslesen aller Konten eines Kunden (durch <code>Customer</code>-Objekt
   * gegeben).
   * 
   * @see findByOwner(int ownerID)
   * @param owner Kundenobjekt, dessen Konten wir auslesen möchten.
   * @return alle Konten des Kunden
   */
  public Vector<Profile> findByOwner(Profile owner) {

    /*
     * Wir lesen einfach die Kundennummer (Primärschlüssel) des Customer-Objekts
     * aus und delegieren die weitere Bearbeitung an findByOwner(int ownerID).
     */
    return findByOwner(owner.getID());
  }

  /**
   * Einfügen eines <code>Account</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param a das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   *         <code>id</code>.
   */
  public Profile insert(Profile p) {
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
         * a erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        p.setID(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO accounts (id, owner) " + "VALUES ("
            + p.getID() + "," + p.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Accounts.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Account-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von a ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return p;
  }

  /**
   * Wiederholtes Schreiben eines Objekts in die Datenbank.
   * 
   * @param a das Objekt, das in die DB geschrieben werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Profile update(Profile p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE profiles " + "SET owner=\"" + p.getOwnerID()
          + "\" " + "WHERE id=" + p.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Profile p) zu wahren, geben wir a zurück
    return p;
  }

  /**
   * Löschen der Daten eines <code>Account</code>-Objekts aus der Datenbank.
   * 
   * @param a das aus der DB zu löschende "Objekt"
   */
  public void delete(Profile p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM profiles " + "WHERE id=" + p.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Löschen sämtlicher Konten (<code>Profile</code>-Objekte) eines Kunden.
   * Diese Methode sollte aufgerufen werden, bevor ein <code>Customer</code>
   * -Objekt gelöscht wird.
   * 
   * @param c das <code>Customer</code>-Objekt, zu dem die Konten gehören
   */
  public void deleteProfileOf(Profile p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM profiles " + "WHERE owner=" + p.getID());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  /**
   * Auslesen des zugehörigen <code>Customer</code>-Objekts zu einem gegebenen
   * Konto.
   * 
   * @param a das Konto, dessen Inhaber wir auslesen möchten
   * @return ein Objekt, das den Eigentümer des Kontos darstellt
   */
  public Profile getOwner(Profile p) {
    /*
     * Wir bedienen uns hier einfach des CustomerMapper. Diesem geben wir
     * einfach den in dem Account-Objekt enthaltenen Fremdschlüssel für den
     * Kontoinhaber. Der CustomerMapper lässt uns dann diese ID in ein Objekt
     * auf.
     */
    return ProfileMapper.profileMapper().findByKey(p.getOwnerID());
  }

public Profile findByKey(int ownerID) {
	// TODO Auto-generated method stub
	return null;
}

}
