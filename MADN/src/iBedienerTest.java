import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class iBedienerTest {

	protected  iBediener s;
	
	@Before
	public void vorher() {
		s = new Spiel();
	}
	
	
	@Test
	public void test() throws SpielerFarbeVorhandenException {
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testGleicheFarbe() throws SpielerFarbeVorhandenException {
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testZuViele() throws SpielerFarbeVorhandenException{
		// Hier wird eine doppelte Spielerfarbe sowieso gefunden,
		// es bringt also nichts noch eine extra Zählervariable für Spiel
		// zu erstellen
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN);
		s.spielerHinzufuegen("Heinz", FarbEnum.GELB);
		s.spielerHinzufuegen("Heinz", FarbEnum.ROT);
		s.spielerHinzufuegen("Heinz", FarbEnum.BLAU);
		s.spielerHinzufuegen("Heinz", FarbEnum.GELB);
	}
	
	@Test
	public void testSpielerEntfernen() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufuegen("Karl", FarbEnum.GELB);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN);
		
		s.spielerEntfernen(FarbEnum.GRUEN);
		
	}
	
	@Test(expected=SpielerNichtGefundenException.class)
	public void testSpielerEntfernenNichtGefunden() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufuegen("Karl", FarbEnum.GELB);
		s.spielerHinzufuegen("Heinz", FarbEnum.GRUEN);
		
		s.spielerEntfernen(FarbEnum.BLAU);
	}

}
