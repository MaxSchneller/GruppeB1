package Tests;

import static org.junit.Assert.*;
import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Spiel.FarbEnum;
import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.SpielBean;
import Spiel.WuerfelErgebnis;
import Spiel.ZugErgebnis;
import Spiel.iBediener;

import org.junit.Before;
import org.junit.Test;


public class KiTestRuns {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws SpielerFarbeVorhandenException, KannNichtWuerfelnException {
		for (int i = 0; i < 100; i++) {
			iBediener s = new SpielBean("Karl", FarbEnum.ROT, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.BLAU, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.GELB, KiTypEnum.AGGRESIV);
			s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.AGGRESIV);
			
			while (true) {
				
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

}
