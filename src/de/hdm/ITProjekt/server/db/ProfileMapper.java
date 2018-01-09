package de.hdm.ITProjekt.server.db;

import java.sql.*;
import java.util.ArrayList;
import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.bo.SearchProfile;
import de.hdm.ITProjekt.server.db.DBConnection;

/**
 * Die Mapper-Klasse <code>ProfileMapper</code> bildet <code>Profile
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see SearchProfileMapper
 * @see BlocklistMapper
 * @see NotepadMapper
 * @see CharacteristicMapper 
 * @see InfoMapper 
 * @see SelectionMapper
 * @see DescriptionMapper
 * 
 * @author Son, Arian
 */

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
    	ResultSet rs = stmt.executeQuery("SELECT * FROM profile" + "WHERE id=" + id);
    	
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
    		p.setBodyHeight(rs.getInt("bodyheight"));
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
		Profile p = new Profile();
		
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT * FROM profile" + "WHERE email=" + email);
			
			while (rs.next()) {
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
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return p;
		}
	
	/** Ausgabe aller Profile**/
	public ArrayList<Profile> findAll() {
		
		Connection con = DBConnection.connection();
		
		ArrayList<Profile> result = new ArrayList<>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM profile");
			
			while (rs.next()) {
				
			}
			
			Profile p = new Profile();
			p.setId(rs.getInt("id"));
	        p.setFirstName(rs.getString("firstname"));
	        p.setLastName(rs.getString("lastname"));
	        p.setEmail(rs.getString("email"));
	        p.setHairColor(rs.getString("haircolor"));
	        p.setBodyHeight(rs.getInt("bodyheight"));
	        p.setBirthdate(rs.getDate("birthdate"));
	        p.setSmoker(rs.getBoolean("smoker"));
	        p.setReligion(rs.getString("religion"));
	        p.setGender(rs.getBoolean("gender"));
	        
	        result.add(p);
	        
		} catch (SQLException e) {
			e.printStackTrace();
			
			ClientsideSettings.getLogger().severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
			}
		
		return result;
		}
	
	/** Profil abbilden*/
	public Profile insert(Profile p) {
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			/* Zunaechst wird ueberprueft, welches momentan der hoechste Primaerschluesselwert ist*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM profile");
			
			if (rs.next()) {
				p.setId(rs.getInt("maxid") +1);
				
				stmt = con.createStatement();
				
				stmt.executeQuery("INSERT INTO profile (id, firstname, lastname, email,"
						+ "haircolor, bodyheight, birthdate, smoker, religion, gender) " + "VALUES (" 
						+ p.getId() + "," 
						+ p.getFirstName() + "," 
						+ p.getLastName() + "," 
						+ p.getEmail() + "," 
						+ p.getHairColor() + "," 
						+ p.getBodyHeight() + "," 
						+ p.getBirthdate() + ","
						+ (p.isSmoker() ? 1 : 0) + "," 
						+ p.getReligion() + "," 
						+ (p.isGender() ? 1 : 0) + ")");
				
				ClientsideSettings.getLogger().info("profile " + p.getLastName() + "  in DB geschrieben");
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			ClientsideSettings.getLogger().severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
			}
		return p;
		}
	
	/** Profil aendern**/
	public Profile update(Profile p) {
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			//smoker, gender
			stmt.executeUpdate("UPDATE profile " + "SET firstname=\"" + p.getFirstName() + "\", "
			+ "lastname=\"" + p.getLastName() + "\", "
		    + "email=\"" + p.getEmail() + "\", "
		    + "haircolor=\"" + p.getHairColor() + "\", "
		    + "bodyheight=\"" + p.getBodyHeight() + "\", "
		    + "birthdate=\"" + p.getBirthdate() + "\", "
		    + "smoker=\"" + (p.isSmoker() ? 1 : 0) + "\", "
		    + "religion=\"" + p.getReligion() + "\", "
		    + "gender=\"" + (p.isGender() ? 1 : 0) + "\""
		    + "WHERE id='" + p.getId());
			
			ClientsideSettings.getLogger().info("Profiländerungen " + p.getLastName() + " in DB geschrieben");
			
		}catch (SQLException e) {
			e.printStackTrace();
			
			ClientsideSettings.getLogger().severe("Fehler beim schreiben in die DB" + e.getMessage() + " " + e.getCause() + " ");
			}
		return p;
		} 
	
	/** Profil loeschen*/
	public void delete(Profile p) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM profile " + "WHERE id=" + p.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	
	public ArrayList<Profile> findBySearchProfile(SearchProfile sp) {
	// TODO Auto-generated method stub
		return null;
		}
	}
