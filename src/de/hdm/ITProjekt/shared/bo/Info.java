package de.hdm.ITProjekt.shared.bo;

import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.BusinessObject;

/**
 * Realisierung eines exemplarischen Profilkontos. Ein Profil besitzt ein Info.
 * 
 * @author kurda
 */
public class Info extends BusinessObject {

  private static final long serialVersionUID = 1L;

  /**
   * Fremdschlüsselbeziehung zum Inhaber des Infos.
   */
  private int ownerID = 0;

  /**
   * Auslesen des Fremdschlüssels zum Infoinhaber.
   */
  public int getOwnerID() {
    return this.ownerID;
  }

  /**
   * Setzen des Fremdschlüssels zum Infoinhaber.
   */
  public void setOwnerID(int profilID) {
    this.ownerID = profilID;
  }

  /**
   * Erzeugen einer einfachen textuellen Repräsentation der jeweiligen
   * Profilinstanz.
   */
  @Override
public String toString() {
    return super.toString() + " inhaber, Info-ID: #" + this.ownerID;
  }

  /**
   * <p>
   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier Profil-Objekte.
   * Die Gleichheit wird in diesem Beispiel auf eine identische Profilnummer
   * beschränkt.
   * </p>
   * <p>
   * <b>ACHTUNG:</b> Die inhaltliche Gleichheit nicht mit dem Vergleich der
   * <em>Identität</em> eines Objekts mit einem anderen verwechseln!!! Dies
   * würde durch den Operator <code>==</code> bestimmt. Bei Unklarheit hierzu
   * können Sie nocheinmal in die Definition des Sprachkerns von Java schauen.
   * Die Methode <code>equals(...)</code> ist für jeden Referenzdatentyp
   * definiert, da sie bereits in der Klasse <code>Object</code> in einfachster
   * Form realisiert ist. Dort ist sie allerdings auf die simple Bestimmung der 
   * Gleicheit der Java-internen Objekt-ID der verglichenen Objekte beschränkt.
   * In unseren eigenen Klassen können wir diese Methode überschreiben und ihr
   * mehr Intelligenz verleihen.
   * </p>
   */
  @Override
public boolean equals(Object o) {
    /*
     * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet werden
     * kann, sind immer wichtig!
     */
    if (o != null && o instanceof Info) {
      Info i = (Info) o;
      try {
        return super.equals(i);
      }
      catch (IllegalArgumentException e) {
        return false;
      }
    }
    return false;
  }
}