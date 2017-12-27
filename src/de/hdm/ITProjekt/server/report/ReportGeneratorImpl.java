package de.hdm.ITProjekt.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.client.ClientsideSettings;
import de.hdm.ITProjekt.server.ParbookServiceImpl;
import de.hdm.ITProjekt.shared.ReportGenerator;
import de.hdm.ITProjekt.shared.bo.Info;
import de.hdm.ITProjekt.shared.bo.Profile;
import de.hdm.ITProjekt.shared.report.AllProfilesReport;
import de.hdm.ITProjekt.shared.report.AllProfilesReport;
import de.hdm.ITProjekt.shared.report.Column;
import de.hdm.ITProjekt.shared.report.CompositeParagraph;
import de.hdm.ITProjekt.shared.report.Paragraph;
import de.hdm.ITProjekt.shared.report.ProfileReport;
import de.hdm.ITProjekt.shared.report.Row;
import de.hdm.ITProjekt.shared.report.SimpleParagraph;

/**
 * Implementierung des synchronen Interfaces ReportGenerator. Diese enthält sämtliche
 * Applikationslogik die für die Erstellung und Verwaltung von Reports notwendig ist.
 * 
 * @author Thies, Kurda
 *
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

  private static final long serialVersionUID = 1L;
  private ParbookServiceImpl service = null;

  public ReportGeneratorImpl() throws IllegalArgumentException {}

  @Override
  public void init() throws IllegalArgumentException {
    /**
     * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
     * pbAdministration-Instanz.
     */
    ParbookServiceImpl pb = new ParbookServiceImpl();
    pb.init();
    service = pb;
  }

  /**
   * Diese Methode erwartet als Übergabeparameter ein Profil-Objekt, welches in ein
   * ProfilReport-Objekt "umgewandelt" wird. Dies geschieht mit Hilfe der Klassen welche sich in
   * shared.report befinden. So werden z.B. einzelne Profilattribute in SimpleParagraph 's
   * gespeichert.
 * @param profileAttributeDes 
   */
  @Override
  public ProfileReport createProfileReport(Profile p) throws IllegalArgumentException {
    if (service == null) {
      return null;
    }

    ProfileReport result = new ProfileReport();
    result.setProfileId(p.getId());

    result.setTitle("Mein Profil");

    SimpleParagraph match = new SimpleParagraph(String.valueOf(p.getMatch())); //TODO

    SimpleParagraph name = new SimpleParagraph(p.getFirstName() + " " + p.getLastName());
    result.setName(name);

    CompositeParagraph profilAttributDes = new CompositeParagraph();
    profilAttributDes.addSubParagraph(new SimpleParagraph("Email: "));
    profilAttributDes.addSubParagraph(new SimpleParagraph("Alter: "));
    profilAttributDes.addSubParagraph(new SimpleParagraph("Geschlecht: "));
    profilAttributDes.addSubParagraph(new SimpleParagraph("Haarfarbe: "));
    profilAttributDes.addSubParagraph(new SimpleParagraph("Religion: "));
    profilAttributDes.addSubParagraph(new SimpleParagraph("Raucher: "));

    CompositeParagraph profileAttribute = new CompositeParagraph();
    profileAttribute.addSubParagraph(new SimpleParagraph(p.getEmail()));
    profileAttribute.addSubParagraph(new SimpleParagraph(p.isGender()));
    //profileAttribute.addSubParagraph(new SimpleParagraph("" + p.getDateOfBirth()));
    profileAttribute.addSubParagraph(new SimpleParagraph(p.isSmoker()));
    profileAttribute.addSubParagraph(new SimpleParagraph(p.getReligion()));
    profileAttribute.addSubParagraph(new SimpleParagraph(p.getHairColor()));
    result.setProfileAttribute(profileAttribute);

    ArrayList<Info> infos = service.getInfoByProfile(p);

    if (infos != null) {
      for (Info i : infos) {
        Row infoRow = new Row();

        infoRow.addColumn(
            new Column(service.getCharacteristicsDescriptionById(i.getCharacteristicId())));
        infoRow.addColumn(new Column(i.getInfoText()));
        result.addRow(infoRow);
      }
    }

    return result;

  }

  /**
   * Methode zum Erstellen des Reports für alle Profile. Diese Methode macht von der bereits
   * implementierten Methode createProfilReport gebrauch, welche für jedes einzelne anzuzeigende
   * Profil aufgerufen wird.
   */
  @Override
  public AllProfilesReport createAllProfilesReport(Profile p) throws IllegalArgumentException {
    if (service == null) {
      return null;
    }

    // zu befüllenden Report erstellen
    AllProfilesReport result = new AllProfilesReport();


    result.setTitle("Alle Profile");
    result.setCreated(new Date());
	return result;


    // TODO: alle Profile abfragen

  }
}