package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;
import Spiel.WuerfelErgebnis;
import Spiel.ZugErgebnis;
import Spiel.iBediener;

public class SpeichernLadenMitKI {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws SpielerFarbeVorhandenException {
		for (int i = 0; i < 100; i++) {
			iBediener s = new Spiel("Karl", FarbEnum.ROT, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.BLAU, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.GELB, KiTypEnum.DEFENSIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.AGGRESIV);
			
			int durchgaenge = 0;
			
			while (true) {
				
				durchgaenge++;
				
				if (durchgaenge == 20) {
					iDatenzugriff dzg = new DatenzugriffSerialisiert();
					
					
					
					try {
						
						FarbEnum spielerAmZugFarbe = s.getSpielerAmZugFarbe();
						String[][] figurenVorLaden = s.getAlleFigurenPositionen();
						dzg.spielSpeichern((Spiel)s);
						
						s = dzg.spielLaden();
						String[][] figurenNachLaden = s.getAlleFigurenPositionen();
 						vergleichePositionen(figurenVorLaden, figurenNachLaden);
						assertEquals(spielerAmZugFarbe, s.getSpielerAmZugFarbe());
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
	
	public void printPosi(String[][] posis) {
		
		for (int i = 0; i < posis.length; ++i) {
			String s = "";
			for (int j = 0; j < posis[i].length; ++j) {
				s += posis[i][j] + " ";
			}
			System.out.println(s);
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
