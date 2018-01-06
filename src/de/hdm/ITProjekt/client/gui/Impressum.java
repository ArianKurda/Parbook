package de.hdm.ITProjekt.client.gui;

import com.google.gwt.user.client.ui.HTML;

/**
 * In dieser Klasse wird das Impressum in HTML-Form ausgegeben.
 * 
 * @author kurda
 */

public class Impressum extends HTML {

	private HTML impressumString = new HTML("<div class='" + 
			"Impressum"+ 
			"'><h2>Impressum</h2>" + 
			"<h2>Angaben gemäß § 5 TMG:</h2>" +
			"<p>Arian Kurda<br />" +
			"Nobelstraße 10<br />" +
			"70569 Stuttgart" +
			"</p>" +
			"<h2>Kontakt:</h2>" +
			"<table><tr>" +
			"<td>Telefon:</td>" +
			"<td>0711 8923 10</td></tr>" +
			"<tr><td>E-Mail:</td>" +
			"<td>ty002@hdm-stuttgart.de</td>" +
			"</tr></table><p></div>");

	public HTML getHtmlImpressum() {
		return impressumString;
	}
}
