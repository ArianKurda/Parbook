package de.hdm.ITProjekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {
	
	
	  private static Connection con = null;

	
	    private static String googleUrl = "";
	    private static String localUrl = "jdbc:mysql://127.0.0.1:3305/parbook?user=root&password=";

	    public static Connection connection() {
	        // Wenn es bisher keine Conncetion zur DB gab, ...
	        if (con == null) {
	            String url = null;
	            
	            try {
	                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	                    // Load the class that provides the new
	                    // "jdbc:google:mysql://" prefix.
	                    Class.forName("com.mysql.jdbc.GoogleDriver");
	                    url = googleUrl;
	                	
	                } else {
	                    // Local MySQL instance to use during development.
	                    Class.forName("com.mysql.jdbc.Driver");
	                    url = localUrl;
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