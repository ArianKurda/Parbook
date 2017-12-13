package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;


public class DBConnection {

	
	private static Connection con = null;
	
	/*private static String googleUrl = wir müssen hier die url einfügen
	 * private static String localUrl = ""
	 */
	
	public static Connection connection() {
		
		if (con == null) {
			
			String url = null;
			
			try{
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
					
					Class.forName("com.mysql.jbdc.GoogleDriver");
					/*url = googleUrl;*/
				} else {
					
					Class.forName("com.mysql.jbdc.Driver");
				}
				
				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}
		
		return con;
	}
	
}
