package de.hdm.ITProjekt.shared.bo;

import java.sql.Date;

public class Profile extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	

	private String firstname = "";
	
	private String lastname = "";
	
	private String email = "";
	
	private String password = "";
	
	private int googleID;
	
	private Date birthdate; /** müssen nachschauen wie wir das mit dem Geburtsdatum machen*/
	
	private String haircolor = "";
	
	private double bodyheight;
	
	private boolean smoker = true; /* true = raucher , false = nichtraucher*/
	
	private String religion = "";
	
	private boolean gender = true;  /* true = maennlich , false = weiblich*/
	

	public static final boolean raucher = true;
	public static final boolean nichtraucher = false;
	public static final boolean maennlich = true;
	public static final boolean weiblich = false;

	
	
	public String getFirstName() {
		return this.firstname;	
	}
	
	public void setFirstName(String name) {
		this.firstname = name;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public void setLastName (String name) {
		this.lastname = name;
	}
	
	/* geburtsdatum*/
	
	public String getHairColor() {
		return this.haircolor;
	}
	
	public void setHairColor (String haarfarbe) {
		this.haircolor = haarfarbe;
	}
	
	public double getBodyHeight() {
		return this.bodyheight;
	}
	
	public void setBodyHeight (double bodyheight ){
		this.bodyheight = bodyheight ;
	}
	
	public boolean Smoker() {
		return this.smoker;
	}
	
	public void setSmoker(boolean smoker){
		this.smoker = raucher;
	}

	
	public String getReligion() { 
		return this.religion;
	}
	
	public void setReligion (String religion) {
		this.religion = religion;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public int getGoogleID() {
		return googleID;
	}
	
	public void setGoogleID(int googleID) {
		this.googleID = googleID;
	}
	

	public boolean gender(){
		return this.gender;	
	}
	
	public void setGender (boolean maennlich){
		this.gender = maennlich;
	}
	
	
	public void setDateOfBirth(Date newBirthdate){
		birthdate = newBirthdate;
	}
	
	/** Überprüfung, ob Profil angelegt wurde
     *
     * @return isCreated
     */
	
	public boolean isCreated() {
		return isCreated;
	}

	 /**
	   * Setzen des Parameters isCreated
	   *
	   * @param isCreated
	   */
	
	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
		
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
		
}
