package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.bo.Description;
import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.bo.Selection;

/**
 * Mapper-Klasse, die <code>Info</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 */
public class InfoMapper {

  /**
   * Die Klasse InfoMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see characterMapper()
   */
  private static InfoMapper infoMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected InfoMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>InfoMapper.infoMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>InfoMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> InfoMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>InfoMapper</code>-Objekt.
   * @see infoMapper
   */
  public static InfoMapper infoMapper() {
    if (infoMapper == null) {
      infoMapper = new InfoMapper();
    }

    return infoMapper;
  }

  /**
   * Auslesen eines Info-Objekts.
   * 
   * Die Methode findByKey implementiert die Suche nach einer id aus der DB.
   * Dadurch wird nur ein Objekt zurückgegeben.
   * 
   * @param id
   * 
   * @return Info-Objekt, das der id entspricht.
   */
  public Info findById(int id) {
	//DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement anlegen.
      Statement stmt = con.createStatement();

      // Statement ausfüllen und an die DB zurückschicken.
      ResultSet rs = stmt.executeQuery("SELECT id FROM info " + "WHERE id=" + id);

      // Für jeden Eintrag im Suchergebnis wird nun ein Info-Objekt erstellt.
      if (rs.next()) {
    	Info i = new Info();
        i.setId(rs.getInt("id"));

        return i;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return null;
  }

  /**
   * Auslesen aller Infos eines durch Fremdschlüssel (ProfileID) gegebenen
   * Profilen.
   * 
   * @return Eine ArrayList mit Info-Objekten.
   */
  public ArrayList<Info> findAll() {
    Connection con = DBConnection.connection();
    // Ergebnisvektor vorbereiten
    ArrayList<Info> result = new ArrayList<Info>();

    try {
      // Leeres SQL-Statement (JDBC) anlegen.
      Statement stmt = con.createStatement();
      
      // Statement ausfüllen und an die DB zurückgeben.
      ResultSet rs = stmt.executeQuery("SELECT infoValue" + "FROM info");

      /**
       * Da die id den Primärschlüssel wiederspiegelt, kann höchstens
       * nur ein Tupel zurückgegeben werden.
       * Prüfen, ob ein Ergebnis vorliegt.
       */
      while (rs.next()) {
    	Info i = new Info();
        i.setId(rs.getInt("id"));
        i.setInfoValue(rs.getString("infovalue"));

        result.add(i);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }

  /**
   * Einfügen eines Info-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   * 
   * @param i das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   * id.
   */
  public Info insert(Info i) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      /*
       * Zunächst schauen wir nach, welches der momentan höchste
       * Primärschlüsselwert ist.
       */
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM Info ");

      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
      if (rs.next()) {
        /*
         * i erhält den bisher maximalen, nun um 1 inkrementierten
         * Primärschlüssel.
         */
        i.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
        stmt.executeUpdate("INSERT INTO Info (infoValue, Profile, id) " 
        		+ "VALUES ('"
        		+ i.getInfoValue() 
        		+ "'," 
        		+ i.getProfile() 
        		+ "," 
        		+ i.getId() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    /*
     * Rückgabe, des evtl. korrigierten Infos.
     * 
     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
     * Objekte übergeben werden, wäre die Anpassung des Profil-Objekts auch
     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
     * explizite Rückgabe von p ist eher ein Stilmittel, um zu signalisieren,
     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
     */
    return i;
  }

  /**
   * Die Methode update() modifiziert ein auf die DB abgebildetes Info-Objekt.
   * 
   * @param i das Objekt, das in der DB abgeändert werden soll
   * @return das als Parameter übergebene Objekt
   */
  public Info update(Info i) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Info SET Value='" 
    		  + i.getInfoValue() 
    		  + "' WHERE Profile="
    		  + i.getProfile() 
    		  + " AND Id=" 
    		  + i.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // Um Analogie zu insert(Info i) zu wahren, geben wir i zurück
    return i;
  }

  /**
   * Löschen der Daten eines <code>Info</code>-Objekts aus der Datenbank.
   * 
   * @param i das aus der DB zu löschende "Objekt"
   */
  public void delete(Info i) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM Info " + "WHERE id=" + i.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  public ArrayList<Info> findByProfile(int profileId) {
	  ArrayList<Info> result = new ArrayList<Info>();
	  
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT id, infoValue, profileId WHERE info.profileId=" + profileId + " ORDER BY id");
		  
		  while (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Info i = new Info();
		        i.setId(rs.getInt("id"));
		        i.setInfoValue(rs.getString("infovalue"));
		        i.setProfileId(rs.getInt("profileId"));

		        result.add(i);
		      }
		      return result;
		    } catch (SQLException e) {
		      e.printStackTrace();
		      ClientsideSettings.getLogger()
		          .severe("Fehler beim Lesen aus der DB" + e.getMessage() + " " + e.getCause() + " ");
		      return null;
		    }
  }
  
  /**
   * Auslesen aller Infos eines bestimmten Profils mit Hilfe eines
   * Profil-Objekts. Da ein Profil mehrere Infos hat, können mehrere
   * Info-Objekte in einer ArrayList ausgegeben werden.
   * 
   * @param p Profil-Objekt
   * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos des vorgegebenen Profils repräsentieren.
   */
  public ArrayList<Info> findByProfile(Profile p) {
	  return findByProfile(p.getId());
	  }
  
  /**
	 * Auslesen aller Infos einer bestimmten Auwahl mit Hilfe der Auswahl-ID. Da
	 * eine Auswahl mehrere Infos haben kann, können mehrere Info-Objekte in
	 * einer ArrayList ausgegeben werden.
	 * 
	 * @param selectionId Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Auswahl repräsentieren.
	 */

	public ArrayList<Info> findBySelection(int id) {

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, infoValue, profileId, selectionId FROM info WHERE profileId=" + id + " ORDER BY id");
			  
			  while (rs.next()) {
			        // Ergebnis-Tupel in Objekt umwandeln
			        Info i = new Info();
			        i.setId(rs.getInt("id"));
			        i.setInfoValue(rs.getString("infovalue"));
			        i.setProfileId(rs.getInt("profileId"));
			        i.setSelectionId(rs.getInt("selectionid"));

			        result.add(i);
			      }
			      return result;
			    } catch (SQLException e) {
			      e.printStackTrace();
			      ClientsideSettings.getLogger()
			          .severe("Fehler beim Lesen aus der DB" + e.getMessage() + " " + e.getCause() + " ");
			      return null;
			    }
	}

	/**
	 * Auslesen aller Infos einer bestimmten Auswahl mit Hilfe eines
	 * Selection-Objekts. Da ein Auswahl mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param selection Selection Objekt
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Selection repräsentieren.
	 */
	public ArrayList<Info> findBySelection(Selection selection) {

		return findBySelection(selection.getId());
	}

	/**
	 * Auslesen aller Infos einer bestimmten Beschreibung mit Hilfe der
	 * Beschreibungs-ID. Da eine Beschreibung mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param descriptionId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Description repräsentieren.
	 */
	public ArrayList<Info> findByDescription(int id) {

		// Vorbereiten der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, infoText, profileId, descriptionId FROM info WHERE profileId=" + id + " ORDER BY id");
			  
			  while (rs.next()) {
			        // Ergebnis-Tupel in Objekt umwandeln
			        Info i = new Info();
			        i.setId(rs.getInt("id"));
			        i.setInfoValue(rs.getString("infovalue"));
			        i.setProfileId(rs.getInt("profileId"));
			        i.setDescriptionId(rs.getInt("descriptionid"));

			        result.add(i);
			      }
			      return result;
			    } catch (SQLException e) {
			      e.printStackTrace();
			      ClientsideSettings.getLogger()
			          .severe("Fehler beim Lesen aus der DB" + e.getMessage() + " " + e.getCause() + " ");
			      return null;
			    }
	}


	/**
	 * Auslesen aller Infos einer bestimmten Beschreibung mit Hilfe eines
	 * Description-Objekts. Da ein Beschreibung mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param description Description Objekt
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Description repräsentieren.
	 */
	public ArrayList<Info> findByDescription(Description description) {
		return findByDescription(description.getId());
	}

}