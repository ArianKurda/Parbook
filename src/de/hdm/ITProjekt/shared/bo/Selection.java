package de.hdm.ITProjekt.shared.bo;

import java.util.ArrayList;

/**
 * Die Klasse Auswahl erweitert die Klasse Eigenschaft.
 * Sie enthält eine Reihe von Auswahlalternativen
 * vom Typ String.
 *
 * @author kurda
 */
public class Selection extends Characteristic {

  private static final long serialVersionUID = 1L;

  /**
   * Deklaration des Parameters alternatives
   *
   * @param alternativen Dieser Parameter ist eine ArrayList vom Typ String. In dieser ArrayList
   * werden alle vorgegebenen Werte für eine Eigenschaft gespeichert.
   */
  private ArrayList<String> alternatives;

  /**
   * Auslesen der Alternativen
   *
   * @return alternativen
   */
  public ArrayList<String> getAlternatives() {
    return alternatives;
  }

  /**
   * Setzen der Alternativen
   *
   * @param alternatives
   */
  public void setAlternatives(ArrayList<String> alternatives) {
    this.alternatives = alternatives;
  }

}
