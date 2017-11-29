package de.hdm.ITProjekt.shared.bo;


/**
 * BO-Klasse Beschreibung, welche die Klasse Eigenschaft erweitert.
 * Sie enthält lediglich einen String (die Beschreibung) und die
 * dazugehörigen getter- und setter-Methoden
 * 
 * @author kurda
 */
public class Description extends Characteristic {

  private static final long serialVersionUID = 1L;

  private String text = "";

  /**
   * Auslesen des Texts
   *
   * @return text Inhalt der Beschreibung
   */
  public String getText() {
    return text;
  }

  /**
   * Setzen des Textes
   * @param text: Inhalt der Beschreibung
   */
  public void setText(String text) {
    this.text = text;
  }

}
