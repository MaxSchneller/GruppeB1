package Tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.DatenzugriffXML;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.SpielBean;
import Spiel.WuerfelErgebnis;
import Spiel.ZugErgebnis;
import Spiel.iBediener;

public class DatenzugriffXMLTest {

	private iBediener spiel;

	@Before
	public void before() throws SpielerFarbeVorhandenException {
		this.spiel = new SpielBean("Hans", FarbEnum.ROT, KiTypEnum.DEFENSIV);
		this.spiel.spielerHinzufuegen("Hallo", FarbEnum.BLAU, KiTypEnum.AGGRESIV);

		try {
			spiel.debugSetzeFigur(FarbEnum.ROT, 0, "E1 ROT");
			spiel.debugSetzeFigur(FarbEnum.ROT, 3, "20");
			spiel.debugSetzeFigur(FarbEnum.ROT, 0, "24");
		} catch (SpielerNichtGefundenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void test() throws IOException, JAXBException,
			ClassNotFoundException, SpielerFarbeVorhandenException,
			SpielerNichtGefundenException {
		iDatenzugriff id = new DatenzugriffXML();

		id.spielSpeichern(spiel, "./XML-UnitTest-Gespeichert/spiel");

		iBediener anderes = (iBediener) id
				.spielLaden("./XML-UnitTest-Gespeichert/spiel.xml");

		vergleichePositionen(this.spiel.getAlleFigurenPositionen(),
				anderes.getAlleFigurenPositionen());
	}
	@Test
	public void test2() throws SpielerFarbeVorhandenException, KannNichtWuerfelnException, JAXBException, ClassNotFoundException, FileNotFoundException, IOException, SpielerNichtGefundenException {
		for (int i = 0; i < 1; i++) {
			iBediener s = new SpielBean("Karl", FarbEnum.ROT, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.BLAU, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.GELB, KiTypEnum.DEFENSIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.AGGRESIV);
			
			int durchgaenge = 0;
			
			iDatenzugriff blah = new DatenzugriffXML();
			s = (iBediener) blah.spielLaden("./XML-UnitTest-Gespeichert/spiel.xml");
			
			while (true) {
				
				//durchgaenge++;
				
				if (durchgaenge == 20) {
					iDatenzugriff dzg = new DatenzugriffXML();
					
					
					
					try {
						
						FarbEnum spielerAmZugFarbe = s.getSpielerAmZugFarbe();
						String[][] figurenVorLaden = s.getAlleFigurenPositionen();
						dzg.spielSpeichern((SpielBean)s, "./XML-UnitTest-Gespeichert/spiel");
						
						dzg = new DatenzugriffCSV();
						dzg.spielSpeichern((SpielBean)s, "./XML-UnitTest-Gespeichert/spiel.csv");
						
					
						dzg = new DatenzugriffXML();
						SpielBean s1 = (SpielBean)dzg.spielLaden("./XML-UnitTest-Gespeichert/spiel.xml");
						String[][] figurenNachLaden = s1.getAlleFigurenPositionen();
 						vergleichePositionen(figurenVorLaden, figurenNachLaden);
						assertEquals(spielerAmZugFarbe, s.getSpielerAmZugFarbe());
						
						s = s1;
						s1.checkSpielerFiguren();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				WuerfelErgebnis wuerfelErgebnis = s.sWuerfeln();
				
				while (wuerfelErgebnis.isKannNochmalWuerfeln()) {
					wuerfelErgebnis = s.sWuerfeln();
				}
				
				if (wuerfelErgebnis.isKannZugAusfuehren() == false) {
					continue;
				}
				else {
					ZugErgebnis zugErgebnis = s.ziehen(0);
					
					if (zugErgebnis.isSpielGewonnen()) {
						System.out.println("Gewinner: " + zugErgebnis.getGewinnerName());
						System.out.println("Farbe: " + zugErgebnis.getGewinnerFarbe());
						
						String[][] figuren = s.getAlleFigurenPositionen();
						
						FarbEnum gewinnerFarbe = zugErgebnis.getGewinnerFarbe();
						
						for (int j = 0; j < figuren.length; ++j) {
							String figurFarbe = figuren[j][0];
							String gewinnerFarbeString = gewinnerFarbe.name();
							
							if (figurFarbe.equals(gewinnerFarbeString)) {
								for (int k = 0; k < 4; ++k) {
									assertTrue(figuren[j+k][2].startsWith("E"));
								}
								break;
							}
						}
						break;
					}
				}
			}
		}
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
