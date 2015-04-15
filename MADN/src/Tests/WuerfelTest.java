package Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Spiel.Wuerfel;


public class WuerfelTest {
	
	protected Wuerfel w;
		
	@Before
	public void vorWuerfeln() {
		System.out.println("Vor dem Würfeln");
		w = new Wuerfel();
	
	}

	@Test
	public void testWuerfel() {
		for (int i = 0 ; i <= 10 ; i++){
			int gewuerfelteZahl = w.werfen();
			assertTrue(gewuerfelteZahl > 0 && gewuerfelteZahl < 7);
		}
	}


	
	
}
 