package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hdm.ITProjekt.shared.bo.SearchProfile;

/**
 * Mapper-Klasse, die die Aufgabe hat, die Objekte einer persistenten Klasse auf die Datenbank
 * abzubilden und dort zu speichern.
 * 
 * Die zu speichernden Objekte werden dematerialisiert und zu
 * gewinnende Objekte aus der Datenbank entsprechend materialisiert.
 * 
 * Dies wird als indirektes Mapping bezeichnet. Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden zur Suche, zum Speichern, Löschen und Modifizieren von Objekten.
 * 
 * @see DBConnection
 * @see ProfileMapper
 * @see NotepadMapper
 * @see BlocklistMapper
 * @see InfoMapper
 * @see CharacteristicMapper
 * @see DescriptionMapper
 * @see SelectionMapper
 * 
 *
 * @author Thies, Kurda
 */

public class SearchProfileMapper {
	
	/**
	 * Von der Klasse SearchProfileMapper kann nur eine Instanz erzeugt werden. Sie erfüllt die
	 * Singleton-Eigenschaft. Dies geschieht mittels eines private default-Konstruktors und genau
	 * einer statischen Variablen vom Typ SelectionMapper, die die einzige Instanz der Klasse darstellt.
	 * 
	 */
	
	private static SearchProfileMapper searchProfileMapper = null;
	  
	/**
	 * Durch den Modifier "private" geschützter Konstruktor, der verhindert das weiter Instanzen der
	 * Mapper-Klasse erzeugt werden können.
	 * 
	 */
	
	protected SearchProfileMapper() {
		
	}
	
	/**
	 * Von der Klasse SearchProfileMapper kann nur eine Instanz erzeugt werden. Sie erfüllt die
	 * Singleton-Eigenschaft. Dies geschieht mittels eines private default-Konstruktors und genau
	 * einer statischen Variablen vom Typ SelectionMapper, die die einzige Instanz der Klasse darstellt.
	 */
	
	public static SearchProfileMapper searchProfileMapper() {
		if (searchProfileMapper == null) {
			searchProfileMapper = new SearchProfileMapper();
		}
		return searchProfileMapper;
	}
	
	/**
	   * Die Methode findById implementiert die Suche nach genau einer id aus der Datenbank,
	   * entsprechend wird genau ein Objekt zurückgegeben.
	   *
	   * @param id
	   *
	   * @return Selection-Objekt, das der übergegebenen id entspricht bzw. null bei nicht vorhandenem
	   *         DB-Tupel.
	   */
	
	public SearchProfile findById(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, firstname, lastname, email, haircolor, bodyheight,"
					+ "smoker, nonsmoker, religion, male, female, dbo "
					+ "FROM Searchprofile"
					+ "WHERE id=" + id);
			
			if (rs.next()) {
				SearchProfile sp = new SearchProfile();
				sp.setId(rs.getInt("id"));
				sp.setFirstName(rs.getString("firstname"));
				sp.setLastName(rs.getString("lastname"));
				sp.setEmail(rs.getString("email"));
				sp.setHairColor(rs.getString("haircolor"));
				sp.setBodyHeight(rs.getDouble("bodyheight"));
				sp.setBirthdate(rs.getDate("birthdate"));
				sp.setSmoker(rs.getBoolean("smoker"));
				sp.setReligion(rs.getString("religion"));
				sp.setGender(rs.getBoolean("gender"));
				
				return sp;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
			return null;
		
	}
	
	/**
	   * Hinzufügen eines SearchProfile-Objekts in die Datenbank.
	   *
	   * @param sp - das zu speichernde Objekt
	   *
	   * @return das an die Datenbank übergebene Objekt
	   */
	
	public SearchProfile insert(SearchProfile sp) {
		Connection con = DBConnection.connection();
		
		try {
			// Leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
			
			//Momentan höchsten Primärschlüssel prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM searchprofiles");
			
			if (rs.next()) {
				
				/**
				 * searchProfile erhält den bisher maximalen, um 1 inkrementierten Primärschlüssel.
				 */
				sp.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO searchprofile (id,firstname,lastname,email,"
						+ "haircolor, bodyheight, smoker, religion, gender, dbo)	"
						+ "VALUES (" + sp.getId() + ",'" + sp.getFirstName() + "','" + sp.getLastName()
			            + "','" + sp.getEmail() + "','" + sp.getHairColor() + "'," + sp.getBodyHeight() + ",'"
			            + sp.isSmoker() + "','" + sp.getReligion() + "','" + sp.isGender() + "','"
			            /*+ sp.getDateOfBirth()*/ + "')");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return sp;
		
	}
	
	/**
	 * Modifizieren eines bereits auf die DB abgebildete SearchProfile-Objekts.
	 * 
	 * @param sp
	 * 
	 * @return SearchProfile
	 */
	
	public SearchProfile update(SearchProfile sp) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE searchprofile SET  Firstname='" + sp.getFirstName() + "', Lastname='"
			          + sp.getLastName() + "', Haircolor ='" + sp.getHairColor() + "', Bodyheight="
			          + sp.getBodyHeight() + ", Smoker='" + sp.isSmoker() + "', Religion='" + sp.getReligion()
			          + "', Birthdate='" + sp.getBirthdate() + "' WHERE id=" + sp.getId()); // TODO
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}
	
	/**
	 * Löschen eines Suchprofils aus der DB.
	 * 
	 * @param SearchProfile sp
	 */
	
	public void delete(SearchProfile sp) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM searchprofile WHERE ID=" + sp.getId());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
