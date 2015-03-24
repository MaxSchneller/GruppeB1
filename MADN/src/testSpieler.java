import static org.junit.Assert.*;

import org.junit.Test;


public class testSpieler {
	
	protected Spieler s=new Spieler("John", FarbEnum.blau, null);
	
	@Test
	public void test() {
		assertNotNull(s.getName());
		assertTrue(s.getFarbe() instanceof FarbEnum);
		assertTrue(figurAufFeld() instanceof s);
	}
	

}
