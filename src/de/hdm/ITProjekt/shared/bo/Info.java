package de.hdm.ITProjekt.shared.bo;

/**
 * Realisierung eines exemplarischen Profilkontos. Ein Profil besitzt ein Info.
 * 
 * @author kurda
 */
public class Info extends BusinessObject {

  private static final long serialVersionUID = 1L;
  
  /**
   * Deklaration der Parameter eines Infoobjektes
   *
   * @param text Die Bezeichnung der Eigenschaft
   * @param eigenschaftId Die ID der Eigenschaft, zu der das Infoobjekt gehört
   * @param profilId Die ID des Profils des eingeloggten Users, zu dem das Infoobjekt gehört
   */
  private String text = "";
  private int characteristicID = 0;
  private int profileID = 0;

  /**
   * Auslesen des Parameters text
   * 
   * @param text
   */
  public String getText() {
	  return text;
  }
  
  /**
   * Setzen des Parameters Text
   * 
   * @param text
   */
  public void setText(String text) {
	  this.text = text;
  }
  
  /**
   * Erzeugen einer ganzen Zahl, die das Info-Objekt charakterisiert.
   * 
   * @see java.lang.Object#hashcode()
   */
  @Override
  public int hashCode() {
	  final int prime = 31;
	  int result = super.hashCode();
	  result = (prime * result) + characteristicID;
	  result = (prime * result) + ((text == null) ? 0 : text.hashCode());
	  return result;
  }
  
  /**
   * Feststellen der inhaltlichen Gleichheit zweier Info-Objekte anhand
   * der CharacteristicID
   * 
   * @see java.lang.Object#equals(Object)
   */
  @Override
  public boolean equals(Object object) {
	  boolean result = false;
	  if ((object == null) || (object.getClass() != this.getClass())) {
		  result = false;
	  } else {
		  Info i = (Info) object;
		  if (getText().equals(i.getText()) && (getCharacteristicID() == i.getCharacteristicID())) {
			  result = true;
		  }
	    }
	    return result;
	  }
  
  /**
   * Auslesen der CharacteristicID
   * 
   * @return characteristicID
   */
  public int getCharacteristicID() {
	  return characteristicID;
  }
  
  /**
   * Setzen der CharateristicID
   * 
   * @param characteristicID
   */
  public void setCharaceristicID(int characteristicID) {
	  characteristicID = characteristicID;
  }
  
  /**
   * Auslesen der ProfileID
   * 
   * @return profileID
   */
  public int getProfileID() {
	  return profileID;
  }
  
  /**
   * Setzen der ProfileID
   * 
   * @param profileID
   */
  public void setProfileID(int profileID) {
	  this.profileID = profileID;
  }

}