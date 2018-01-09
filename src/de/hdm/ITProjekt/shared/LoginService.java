package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.shared.bo.Profile;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	
	/**
	 * Ausführen des Logins und ablegen aller relevanten Nutzer Informationen in einem LoginInfo Objekt.
	 * 
	 * @param requestUri
	 */
  public LoginInfo login(String requestUri);
  
  //Auslesen des aktuellen Profils
	//Profile getCurrentProfile();
  
}