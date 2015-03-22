import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SpielerTest {

	protected Spieler s;
	protected Wuerfel w;
	
	
	@Before
	public void spielerErstellen() {
		s = new Spieler("Max", FarbEnum.gelb, w);
		w = new Wuerfel();
	
	}
	
	@Test
	public void testSpieler(){
		String name=s.getName();
		FarbEnum farbe=s.getFarbe();
		
		assertNotNull(name);
		assertTrue(farbe instanceof FarbEnum); 
		
	}
}
