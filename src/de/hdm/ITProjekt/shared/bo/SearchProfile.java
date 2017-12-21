package de.hdm.ITProjekt.shared.bo;

import java.sql.Date;

public class SearchProfile extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private String firstname = "";
	
	private String lastname = "";
	
	private String email = "";
	
	private Date birthdate; //müssen nachschauen wie wir das mit dem Geburtsdatum machen
	
	private String haircolor = "";
	
	private double bodyheight;
	
	private boolean smoker = true; /* true = raucher , false = nichtraucher*/
	
	private String religion = "";
	
	private boolean gender = true;  /* true = maennlich , false = weiblich*/
	
	private Profile profile;
	
	private boolean notVisited;

	public static final boolean raucher = true;
	public static final boolean nichtraucher = false;
	public static final boolean maennlich = true;
	public static final boolean weiblich = false;
	
	//Vorname des Suchprofils auslesen
	public String getFirstName() {
		return this.firstname;	
	}
	
	//Vorname des Suchprofils setzen
	public void setFirstName(String name) {
		this.firstname = name;
	}
	
	//Nachname des Suchprofils auslesen
	public String getLastName() {
		return this.lastname;
	}
	
	//Nachname des Suchprofils setzen
	public void setLastName (String name) {
		this.lastname = name;
	}
	
	//Haarfarbe des Suchprofils auslesen
	public String getHairColor() {
		return this.haircolor;
	}
	
	//Haarfarbe des Suchprofils setzen
	public void setHairColor (String haarfarbe) {
		this.haircolor = haarfarbe;
	}
	
	//Koerpergroesse des Suchprofils auslesen
	public double getBodyHeight() {
		return this.bodyheight;
	}
	
	//Koerpergroesse des Suchprofils setzen
	public void setBodyHeight (double bodyheight) {
		this.bodyheight = bodyheight ;
	}
	
	//Raucheroption des Suchprofils auslesen
	public boolean isSmoker() {
		return this.smoker;
	}
	
	//Raucheroption des Suchprofils setzen
	public void setSmoker(boolean smoker){
		this.smoker = raucher;
	}
	
	//Religion des Suchprofils auslesen
	public String getReligion() { 
		return this.religion;
	}
	
	//Religion des Suchprofils setzen
	public void setReligion (String religion) {
		this.religion = religion;
	}
	
	//Email des Suchprofils auslesen
	public String getEmail() {
		return this.email;
	}
	
	//Email des Suchprofils setzen
	public void setEmail (String email) {
		this.email = email;
	}
	
	//Geschlecht des Suchprofils auslesen
	public boolean isGender(){
		return this.gender;	
	}
	
	//Geschlecht des Suchprofils auslesen
	public void setGender (boolean maennlich){
		this.gender = maennlich;
	}
	
	//Geburtstag des Suchprofils setzen
	public void setBirthdate(Date newBirthdate){
		birthdate = newBirthdate;
	}
	
	//Nutzerprofil auslesen
	public Profile getProfile() {
		return profile;
	}
	
	//Name des Suchprofils auslesen
	public void setProfile (Profile profile) {
		this.profile= profile;
	}
	
	//Prüfen, ob ein Profil nicht besucht wurde
	public boolean isNotVisited() {
		return this.notVisited;
	}
	
	//Nicht besuchtes Profil setzen
	public void setNotVisited(boolean notVisited) {
		this.notVisited = notVisited;
	}
	
	//Textuelle Darstellung
	public String toString() {
		return super.toString() + "" + this.firstname + "" + this.lastname + "" + this.email + "" + this.birthdate + "" + this.haircolor + "" + this.bodyheight + ""
				+ "" + this.smoker + this.religion + "" + this.gender + "" + this.profile + "" + this.notVisited;
	}
}
