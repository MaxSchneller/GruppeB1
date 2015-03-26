import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class iBedienerTest {

	protected  iBediener s;
	
	@Before
	public void vorher() {
		s = new Spiel("Heinz", FarbEnum.BLAU, KiTypEnum.KEINE_KI);
	}
	
	
	@Test
	public void test() throws SpielerFarbeVorhandenException {
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.KEINE_KI);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testGleicheFarbe() throws SpielerFarbeVorhandenException {
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.KEINE_KI);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testZuViele() throws SpielerFarbeVorhandenException{
		// Hier wird eine doppelte Spielerfarbe sowieso gefunden,
		// es bringt also nichts noch eine extra Zählervariable für Spiel
		// zu erstellen
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.GELB, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.ROT, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.BLAU, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.GELB, KiTypEnum.KEINE_KI);
	}
	
	@Test
	public void testSpielerEntfernen() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufuegen("Karl", FarbEnum.GELB, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.KEINE_KI);
		
		s.spielerEntfernen(FarbEnum.GRUEN);
		
	}
	
	@Test(expected=SpielerNichtGefundenException.class)
	public void testSpielerEntfernenNichtGefunden() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufuegen("Karl", FarbEnum.GELB, KiTypEnum.KEINE_KI);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN, KiTypEnum.KEINE_KI);
		
		s.spielerEntfernen(FarbEnum.BLAU);
	}
	
	@Test
	public void testWuerfeln(){
		int zahl = s.sWuerfeln();
		
		if (!(zahl > 0 && zahl < 7))
			fail();
	}
	
	@Test
	public void testZiehen() {
		
		int zahl = s.sWuerfeln();		
		ZugErgebnis e = s.ziehen(0);
		
		if (zahl != 6)
			assertFalse(e.isGueltig());
	}
	
	@Test
	public void testZiehenSechser() {
		s.debugWuerfeln(6);
		ZugErgebnis e = s.ziehen(0);
		
		assertTrue(e.isGueltig());
		assertFalse(e.isZugBeendet());
	}
	
	@Test
	public void testZiehenUngueltigerZug() throws SpielerNichtGefundenException {
		
		// Spieler 1 ist im Test immer blau...
		s.debugSetzeFigur(FarbEnum.BLAU, 0, "12");
		
		s.debugWuerfeln(4);
		
		ZugErgebnis e = s.ziehen(0);
		
		assertFalse(e.isGueltig());
	}

}
