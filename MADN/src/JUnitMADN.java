import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class JUnitMADN {
	
	protected Wuerfel w;
	protected Spieler s;
		
	@Before
	public void vorWuerfeln() {
		System.out.println("Vor dem Würfeln");
		w = new Wuerfel();
		s = new Spieler("Max", FarbEnum.gelb,w);
	
	}

	@Test
	public void testWuerfel() {
		for (int i = 0 ; i <= 10 ; i++){
			int gewuerfelteZahl = w.werfen();
			assertTrue(gewuerfelteZahl > 0 && gewuerfelteZahl < 7);
		}
	}
	@Test
	public void testSpieler(){
		String name=s.getName();
		FarbEnum farbe=s.getFarbe();
		
		assertNotNull(name);
		assertTrue(farbe instanceof FarbEnum); 
		
	}

	
	
}
 