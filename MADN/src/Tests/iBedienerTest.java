package Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Spiel.FarbEnum;
import Spiel.Spiel;
import Spiel.ZugErgebnis;
import Spiel.iBediener;


public class iBedienerTest {

	protected  iBediener s;
	
	@Before
	public void vorher() {
		s = new Spiel("Heinz", FarbEnum.BLAU, null);
	}
	
	
	@Test
	public void test() throws SpielerFarbeVorhandenException {
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, null);
		
		String[][] strings = s.getAlleFigurenPositionen();
		
		for (int i = 0; i < strings.length; ++i) {
			for (int j = 0; j < strings[i].length; ++j) {
				System.out.print(strings[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testGleicheFarbe() throws SpielerFarbeVorhandenException {
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, null);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testZuViele() throws SpielerFarbeVorhandenException{
		// Hier wird eine doppelte Spielerfarbe sowieso gefunden,
		// es bringt also nichts noch eine extra Zählervariable für Spiel
		// zu erstellen
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.GELB, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.ROT, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.BLAU, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.GELB, null);
	}
	
	@Test
	public void testSpielerEntfernen() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufuegen("Karl", FarbEnum.GELB, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, null);
		
		s.spielerEntfernen(FarbEnum.GRUEN);
		
	}
	
	@Test(expected=SpielerNichtGefundenException.class)
	public void testSpielerEntfernenNichtGefunden() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufuegen("Karl", FarbEnum.GELB, null);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, null);
		
		s.spielerEntfernen(FarbEnum.ROT);
	}
	
	@Test
	public void testWuerfeln() throws KannNichtWuerfelnException{
		int zahl = s.sWuerfeln().getGewuerfelteZahl();
		
		if (!(zahl > 0 && zahl < 7))
			fail();
	}
	
	@Test
	public void testZiehen() throws KannNichtWuerfelnException {
		
		int zahl = s.sWuerfeln().getGewuerfelteZahl();		
		ZugErgebnis e = s.ziehen(0);
		
		if (zahl != 6)
			assertFalse(e.isGueltig());
	}
	
	@Test
	public void testZiehenSechser() throws KannNichtWuerfelnException {
		s.debugWuerfeln(6);
		ZugErgebnis e = s.ziehen(0);
		
		assertTrue(e.isGueltig());
		assertFalse(e.isZugBeendet());
	}
	
	@Test
	public void testZiehenUngueltigerZug() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		
		// Spieler 1 ist im Test immer blau...
		s.debugSetzeFigur(FarbEnum.BLAU, 0, "12");
		
		s.debugWuerfeln(4);
		
		ZugErgebnis e = s.ziehen(0);
		
		assertTrue(e.isGueltig());
	}
	
	@Test
	public void testZugInEndfeld() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		s.debugSetzeFigur(FarbEnum.BLAU, 0, "10");
		
		s.debugWuerfeln(2);
		
		ZugErgebnis ergebnis =  s.ziehen(0);
		
		System.out.println(ergebnis);
		
		assertTrue(ergebnis.isGueltig());
		
		s.debugSetzeFigur(FarbEnum.BLAU, 1, "10");
		s.debugWuerfeln(3);
		
		ergebnis = s.ziehen(1);
		
		System.out.println(ergebnis);
		
		assertFalse(ergebnis.isGueltig());
	}
	
	@Test
	public void testStartspielfeldBelegt() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		
		s.debugSetzeFigur(FarbEnum.BLAU, 0, "11");
		
		s.debugWuerfeln(6);
		
		ZugErgebnis ergebnis = s.ziehen(1);
		
		System.out.println(ergebnis);
		assertFalse(ergebnis.isGueltig());
	}

}
