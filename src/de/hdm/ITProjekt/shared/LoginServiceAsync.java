package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.bo.Profile;

public interface LoginServiceAsync {
	
  void login(String requestUri, AsyncCallback<LoginInfo> async);
  
  void getCurrentProfile(AsyncCallback<Profile> callback);
}