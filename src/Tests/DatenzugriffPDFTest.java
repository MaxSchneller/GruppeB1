package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import jdk.internal.dynalink.support.DefaultPrelinkFilter;

import org.junit.Before;
import org.junit.Test;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;

public class DatenzugriffPDFTest {

	protected Spiel spiel;

	@Before
	public void before() throws SpielerFarbeVorhandenException,
			SpielerNichtGefundenException {
		this.spiel = new Spiel("Foxtrott", FarbEnum.BLAU, null);
		spiel.spielerHinzufuegen("Uniform", FarbEnum.GELB, null);
		spiel.spielerHinzufuegen("Charlie", FarbEnum.ROT, null);
		spiel.spielerHinzufuegen("Kilo", FarbEnum.GRUEN, null);

		// Blauer Spieler
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "40");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "E4 BLAU");

		// Gelber Spieler
		spiel.debugSetzeFigur(FarbEnum.GELB, 0, "1");
		spiel.debugSetzeFigur(FarbEnum.GELB, 1, "30");

		spiel.debugSetzeFigur(FarbEnum.ROT, 1, "E3 ROT");
		spiel.debugSetzeFigur(FarbEnum.GRUEN, 1, "E3 GRUEN");
	}

	@Test
	public void test() throws IOException, ClassNotFoundException,
			SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		iDatenzugriff d = new DatenzugriffPDF();

		d.spielSpeichern(spiel, "Dateien_Gespeichert/test.pdf");
		String[][] alleFigurenPositionen = spiel.getAlleFigurenPositionen();

		Spiel geladen = (Spiel) d.spielLaden("Dateien_Gespeichert/test.pdf");

		String[][] alleFigurenPositionen2 = geladen.getAlleFigurenPositionen();

		vergleichePositionen(alleFigurenPositionen, alleFigurenPositionen2);
		assertEquals(spiel.getSpielerAmZugFarbe(),
				geladen.getSpielerAmZugFarbe());
	}

	public void vergleichePositionen(String[][] p1, String[][] p2) {

		assertEquals(p1.length, p2.length);

		for (int i = 0; i < p1.length; ++i) {
			for (int j = 0; j < 3; ++j) {
				assertEquals(p1[i][j], p2[i][j]);
			}
		}
	}

}
