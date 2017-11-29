package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.ITProjekt.shared.bo.Profile;



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

  public Profile findByKey(int id) {
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
        p.setID(rs.getInt("id"));
        p.setFirstName(rs.getString("firstname"));
        p.setLastName(rs.getString("lastname"));
        p.setEmail(rs.getString("email"));
        p.setHairColor(rs.getString("haircolo"));
        p.setBodyHeight(rs.getDouble("bodyheight"));
       /* p.setBirthdate(rs.getString("firstname")); */
        p.setSmoker(rs.getBoolean("smoker"));
        p.setNonSmoker(rs.getBoolean("nonsmoker"));
        p.setReligion(rs.getString("religion"));
        p.setMale(rs.getBoolean("male"));
        p.setFemale(rs.getBoolean("female"));
        
        return p;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
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
	      		+ "smoker, nonsmoker, religion, male, female, dbo "
	    		  + "FROM Profile");
	  
	  while (rs.next()) {
		  
	  }
		  Profile p = new Profile();
	        p.setID(rs.getInt("id"));
	        p.setFirstName(rs.getString("firstname"));
	        p.setLastName(rs.getString("lastname"));
	        p.setEmail(rs.getString("email"));
	        p.setHairColor(rs.getString("haircolo"));
	        p.setBodyHeight(rs.getDouble("bodyheight"));
	       /* p.setBirthdate(rs.getString("birthdate")); */
	        p.setSmoker(rs.getBoolean("smoker"));
	        p.setNonSmoker(rs.getBoolean("nonsmoker"));
	        p.setReligion(rs.getString("religion"));
	        p.setMale(rs.getBoolean("male"));
	        p.setFemale(rs.getBoolean("female"));
	  
	        result.add(p);
	        
	  } catch (SQLException e) {
	      e.printStackTrace();
	      
	  }
	return result;
  }
}
  

  


