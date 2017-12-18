package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.server.db.DBConnection;




public class ProfileMapper {


  private static ProfileMapper profileMapper = null;

  protected ProfileMapper() {
  }

 
  public static ProfileMapper profileMapper() {
    if (profileMapper == null) {
      profileMapper = new ProfileMapper();
    }

    return profileMapper;
  }

  /** Profile anhand der ID suchen**/

  public Profile findById(int id) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery("SELECT id,firstname,lastname,email,haircolor,bodyheight,"
      		+ "smoker, nonsmoker, religion, male, female, dbo "
    		  + "FROM Profile"
          + "WHERE id=" + id);

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Profile p = new Profile();
        p.setId(rs.getInt("id"));
        p.setFirstName(rs.getString("firstname"));
        p.setLastName(rs.getString("lastname"));
        p.setEmail(rs.getString("email"));
        p.setHairColor(rs.getString("haircolor"));
        p.setBodyHeight(rs.getDouble("bodyheight"));
       /* p.setBirthdate(rs.getString("firstname")); */
        p.setSmoker(rs.getBoolean("smoker"));
        p.setReligion(rs.getString("religion"));
        p.setGender(rs.getBoolean("gender"));
        
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
   * Suchen eines Profils anhand der E-Mail-Adresse
   *
   * @param email
   *
   * @return Profile p
   */
  public Profile findByEmail(String email) {
    // DB-Verbindung holen
    Connection con = DBConnection.connection();

    try {
      // Leeres SQL-Statement (JDBC) anlegen
      Statement stmt = con.createStatement();

      // Statement ausfüllen und als Query an die DB schicken
      ResultSet rs = stmt.executeQuery(
          "SELECT id, Firstname, Lastname, Email, DateOfBirth, HairColor, "
              + "BodyHeight, Smoker, Religion, Gender FROM Profile " + "WHERE Email LIKE '"
              + email + "'");

      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Profile p = new Profile();
        p.setId(rs.getInt("id"));
        p.setFirstName(rs.getString("Vorname"));
        p.setLastName(rs.getString("Nachname"));
        p.setEmail(rs.getString("Email"));
        p.setDateOfBirth(rs.getDate("Geburtsdatum"));
        p.setHairColor(rs.getString("Haarfarbe"));
        p.setBodyHeight(rs.getInt("Koerpergroesse"));
        p.setSmoker(rs.getBoolean("Raucher"));
        p.setReligion(rs.getString("Religion"));
        p.setGender(rs.getBoolean("Geschlecht"));
        p.setCreated(true);

        return p;
      }
    } catch (SQLException e) {
      //
      ClientsideSettings.getLogger().severe("Fehler beim Zurückgbeen byEmail");
      return null;
    }

    return null;
  }

  /** Ausgabe aller Profile**/
  
  public ArrayList<Profile> findAll() {
	  Connection con = DBConnection.connection();
	  
	  ArrayList<Profile> result = new ArrayList<>();
	  
	  try {
	      Statement stmt = con.createStatement();
	  
	  ResultSet rs = stmt.executeQuery("SELECT id,firstname,lastname,email,haircolor,bodyheight,"
	      		+ "smoker, religion,gender, dbo "
	    		  + "FROM Profile");
	  
	  while (rs.next()) {
		  
	  }
		  Profile p = new Profile();
	        p.setId(rs.getInt("id"));
	        p.setFirstName(rs.getString("firstname"));
	        p.setLastName(rs.getString("lastname"));
	        p.setEmail(rs.getString("email"));
	        p.setHairColor(rs.getString("haircolor"));
	        p.setBodyHeight(rs.getDouble("bodyheight"));
	       /* p.setBirthdate(rs.getString("birthdate")); */
	        p.setSmoker(rs.getBoolean("smoker"));
	        p.setReligion(rs.getString("religion"));
	        p.setGender(rs.getBoolean("gender"));
	       
	  
	        result.add(p);
	        
	  } catch (SQLException e) {
	      e.printStackTrace();
	      
	  }
	return result;
  }

  /** Profil abbilden*/
public Profile insert(Profile p) {
	Connection con = DBConnection.connection();
	try{
		Statement stmt = con.createStatement();
		
		/* Zunaechst wird ueberprueft , 
		 * welches momentan der hoechste Primaerschluesselwert ist*/
		
		ResultSet rs1 = stmt.executeQuery("SELECT MAX(id) As maxid FROM Profile");
				
				if (rs1.next()) {
					
					p.setId(rs1.getInt("maxid")+1);
					
					stmt = con.createStatement();
					
					stmt.executeUpdate("INSERT INTO Profile (id,firstname,lastname,email,"
							+ "haircolor, bodyheight, smoker, religion, gender, dbo)	"
							+ "VALUES (" + p.getId() + ",'" + p.getFirstName() + "','" + p.getLastName()
				            + "','" + p.getEmail() + "','" + p.getHairColor() + "'," + p.getBodyHeight() + ",'"
				            + p.isSmoker() + "','" + p.getReligion() + "','" + p.isGender() + "','"
				           /* + p.getdbo()*/ + "')"); // TODO
							
				}
	} catch (SQLException e) {
	      e.printStackTrace();
}
    return p;

}
/** Profil �ndern**/

public Profile update(Profile p) {
	Connection con = DBConnection.connection();
	
	try{
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate("UPDATE Profile SET  Firstname='" + p.getFirstName() + "', Lastname='"
		          + p.getLastName() + "', Haircolor ='" + p.getHairColor() + "', Bodyheight="
		          + p.getBodyHeight() + ", Smoker='" + p.isSmoker() + "', Religion='" + p.getReligion()
		          + /*"', Dbo='" + p.getDbo() +*/ "' WHERE id=" + p.getId()); // TODO
		
	}catch (SQLException e) {
		      e.printStackTrace();
}
	return p;
} 
/** Profil loeschen*/

public void delete(Profile p) {
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate("DELETE FROM Info WHERE Profile_id=" + p.getId());
	      stmt.executeUpdate(
	          "DELETE FROM Notepad WHERE Flag_id=" + p.getId() + " OR Noted_id=" + p.getId());
	      stmt.executeUpdate("DELETE FROM Blocklist WHERE Blocker_id=" + p.getId()
	          + " OR Locked_id=" + p.getId());
	      stmt.executeUpdate("DELETE FROM Profile WHERE id=" + p.getId());
	      ClientsideSettings.getLogger().info("Profile " + p.getLastName() + "  aus DB gelöscht");

	    } catch (SQLException e) {
	      e.printStackTrace();
	      ClientsideSettings.getLogger()
	          .severe("Fehler beim schreiben in die DB: " + e.getMessage() + " " + e.getCause() + " ");
	    }
	
}


public Vector<Profile> findByLastName(String name) {
	Connection con = DBConnection.connection();
    Vector<Profile> result = new Vector<Profile>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName "
          + "FROM profiles " + "WHERE lastName LIKE '" + name
          + "' ORDER BY lastName");

      // Für jeden Eintrag im Suchergebnis wird nun ein Profile-Objekt
      // erstellt.
      while (rs.next()) {
        Profile p = new Profile();
        p.setId(rs.getInt("id"));
        p.setFirstName(rs.getString("firstName"));
        p.setLastName(rs.getString("lastName"));

        // Hinzufügen des neuen Objekts zum Ergebnisvektor
        result.addElement(p);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    // Ergebnisvektor zurückgeben
    return result;
  }
}




