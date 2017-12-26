package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.ArrayList;
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
        p.setBirthdate(rs.getDate("birthdate"));
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
          "SELECT id, Firstname, Lastname, Email, Birthdate, HairColor, "
              + "BodyHeight, Smoker, Religion, Gender FROM Profile " + "WHERE Email="
              + email + "'");

      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Profile p = new Profile();
        p.setId(rs.getInt("id"));
        p.setFirstName(rs.getString("firstname"));
        p.setLastName(rs.getString("lastname"));
        p.setEmail(rs.getString("email"));
        p.setBirthdate(rs.getDate("birthdate"));
        p.setHairColor(rs.getString("haircolor"));
        p.setBodyHeight(rs.getInt("bodyheight"));
        p.setSmoker(rs.getBoolean("smoker"));
        p.setReligion(rs.getString("religion"));
        p.setGender(rs.getBoolean("gender"));
        p.setCreated(true);

        return p;
      }
    } catch (SQLException e) {
    	
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
	  
	  ResultSet rs = stmt.executeQuery("SELECT id,firstname,lastname,email,haircolor,bodyheight, birthdate"
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
	        p.setBirthdate(rs.getDate("birthdate"));
	        p.setSmoker(rs.getBoolean("smoker"));
	        p.setReligion(rs.getString("religion"));
	        p.setGender(rs.getBoolean("gender"));
	       
	  
	        result.add(p);
	        
	  } catch (SQLException e) {
	      e.printStackTrace();
	      
	      ClientsideSettings.getLogger()
          .severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
	      
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
		
		ResultSet rs = stmt.executeQuery("SELECT MAX(id) As maxid FROM Profile");
				
				if (rs.next()) {
					
					p.setId(rs.getInt("maxid")+1);
					
					stmt = con.createStatement();
					
					stmt.executeUpdate("INSERT INTO Profile (id,firstname,lastname,email,"
							+ "haircolor, bodyheight, smoker, religion, gender, dbo)	"
							+ "VALUES (" + p.getId() + ",'" + p.getFirstName() + "','" + p.getLastName()
				            + "','" + p.getEmail() + "','" + p.getHairColor() + "'," + p.getBodyHeight() + ",'"
				            + p.isSmoker() + "','" + p.getReligion() + "','" + p.isGender() + "','"
				            + p.getBirthdate() + "')");
					
					ClientsideSettings.getLogger().info("Profile " + p.getLastName() + "  in DB geschrieben");
							
				}
	} catch (SQLException e) {
	      e.printStackTrace();
	      ClientsideSettings.getLogger()
          .severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
}
    return p;

}
/** Profil aendern**/

public Profile update(Profile p) {
	Connection con = DBConnection.connection();
	
	try{
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate("UPDATE Profile SET  Firstname='" + p.getFirstName() + "', Lastname='"
		          + p.getLastName() + "', Haircolor ='" + p.getHairColor() + "', Bodyheight="
		          + p.getBodyHeight() + ", Smoker='" + p.isSmoker() + "', Religion='" + p.getReligion()
		          + "', Dbo='" + p.getBirthdate() + "' WHERE id=" + p.getId());
		
		ClientsideSettings.getLogger()
        .info("Profiländerungen " + p.getLastName() + " in DB geschrieben");
		
	}catch (SQLException e) {
		e.printStackTrace();
		
		ClientsideSettings.getLogger()
		        .severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
}
	return p;
} 
/** Profil loeschen*/

public void delete(Profile p) {
	Connection con = DBConnection.connection();
	
	try {
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate("DELETE FROM Profile WHERE profileId=" + p.getId());

	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	
  }
}
