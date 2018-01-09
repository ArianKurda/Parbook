package de.hdm.ITProjekt.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.server.db.ProfileMapper;
import de.hdm.ITProjekt.shared.LoginInfo;
import de.hdm.ITProjekt.shared.LoginService;
import de.hdm.ITProjekt.shared.bo.Profile;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	
		private static final long serialVersionUID = 1L;
		
		private static LoginService loginService;
		
		public static LoginService loginService(){
			if(loginService == null){
				loginService = new LoginServiceImpl();
			}
			return loginService;
		}

	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();
		
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setProfile(ProfileMapper.profileMapper().findByEmail(user.getEmail()));
		      loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
		      loginInfo.setLoggedIn(false);
		      loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	
	/*public Profile getCurrentProfile() {
		LoginInfo login = login("");
		if(login.isLoggedIn()){
			return login.getProfile();
		} else {
			return null;
		}
	}
	*/
}
