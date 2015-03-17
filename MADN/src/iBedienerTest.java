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
		s.spielerHinzufügen("Heinz", FarbEnum.grün);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testGleicheFarbe() throws SpielerFarbeVorhandenException {
		s.spielerHinzufügen("Heinz", FarbEnum.grün);
		s.spielerHinzufügen("Heinz", FarbEnum.grün);
	}
	
	@Test(expected=SpielerFarbeVorhandenException.class)
	public void testZuViele() throws SpielerFarbeVorhandenException{
		// Hier wird eine doppelte Spielerfarbe sowieso gefunden,
		// es bringt also nichts noch eine extra Zählervariable für Spiel
		// zu erstellen
		s.spielerHinzufügen("Heinz", FarbEnum.grün);
		s.spielerHinzufügen("Heinz", FarbEnum.gelb);
		s.spielerHinzufügen("Heinz", FarbEnum.rot);
		s.spielerHinzufügen("Heinz", FarbEnum.blau);
		s.spielerHinzufügen("Heinz", FarbEnum.gelb);
	}
	
	@Test
	public void testSpielerEntfernen() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufügen("Karl", FarbEnum.gelb);
		s.spielerHinzufügen("Heinz", FarbEnum.grün);
		
		s.spielerEntfernen(FarbEnum.grün);
		
	}
	
	@Test(expected=SpielerNichtGefundenException.class)
	public void testSpielerEntfernenNichtGefunden() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		s.spielerHinzufügen("Karl", FarbEnum.gelb);
		s.spielerHinzufügen("Heinz", FarbEnum.grün);
		
		s.spielerEntfernen(FarbEnum.blau);
	}

}
