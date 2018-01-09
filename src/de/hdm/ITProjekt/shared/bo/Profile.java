package de.hdm.ITProjekt.shared.bo;

import java.sql.Date;

public class Profile extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String firstname = "";

	private String lastname = "";

	private String email = "";

	private String password = "";

	private int googleID;
	
	private int id;

	private Date birthdate;

	private String haircolor = "";

	private int bodyheight;

	private boolean smoker = true; /* true = raucher , false = nichtraucher */

	private String religion = "";

	private boolean gender = true; /* true = maennlich , false = weiblich */

	private boolean loggedIn = false;

	private int match;

	@SuppressWarnings("unused")
	private String createLogoutURL;

	@SuppressWarnings("unused")
	private String createLoginURL;

	public static final boolean raucher = true;
	public static final boolean nichtraucher = false;
	public static final boolean maennlich = true;
	public static final boolean weiblich = false;

	// ------ Methoden ------//
	public String getFirstName() {
		return this.firstname;
	}

	public void setFirstName(String name) {
		this.firstname = name;
	}

	public String getLastName() {
		return this.lastname;
	}

	public void setLastName(String name) {
		this.lastname = name;
	}

	public String getHairColor() {
		return this.haircolor;
	}

	public void setHairColor(String haarfarbe) {
		this.haircolor = haarfarbe;
	}

	public double getBodyHeight() {
		return this.bodyheight;
	}

	public void setBodyHeight(int bodyheight) {
		this.bodyheight = bodyheight;
	}

	public boolean isSmoker() {
		return this.smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = raucher;
	}

	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGoogleID() {
		return googleID;
	}

	public void setGoogleID(int googleID) {
		this.googleID = googleID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isGender() {
		return this.gender;
	}

	public void setGender(boolean maennlich) {
		this.gender = maennlich;
	}

	/**
	 * Auslesen des Geburtsdatums.
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date date) {
		birthdate = date;
	}


	/**
	 * Überprüfung, ob Profil angelegt wurde
	 *
	 * @return isCreated
	 */

	public boolean isCreated() {
		return isCreated();
	}

	/**
	 * Auslesen der Ähnlichkeit zu einem anderen Profil
	 *
	 * @return match
	 */
	public int getMatch() {
		return match;
	}

	/**
	 * Setzen des Parameter Match
	 *
	 * @param match
	 */
	public void setMatch(int match) {
		this.match = match;
	}

	/**
	 * Überprüfen ob User eingeloggt ist
	 *
	 * @return loggedIn Wert ob User eingeloggt ist
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Setzen des Parameters loggedIn
	 *
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Auslesen des Logins
	 *
	 * @return loginUrl Wert mit welcher ID User eingeloggt ist
	 */
	public String getLoginUrl() {
		return getLoginUrl();
	}
	
	public String getLogoutUrl() {
		return getLogoutUrl();
	}

	public void setLogoutUrl(String createLogoutURL) {
		this.createLogoutURL = createLogoutURL;
		
	}

	public void setLoginUrl(String createLoginURL) {
		this.createLoginURL = createLoginURL;
		// TODO Auto-generated method stub
		
	}

}