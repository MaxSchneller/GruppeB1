package Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Spiel.FarbEnum;
import Spiel.SpielBean;
import Spiel.Spieler;
import Spiel.Spielfeld;
import Spiel.Spielfigur;


public class testSpieler {
	protected SpielBean spiel;
	
	@Before
	public void vorher() {
		this.spiel = new SpielBean("Heinz", FarbEnum.BLAU,null);
	}
	
	@Test
	public void test() {
		assertNotNull(this.spiel);
		
		Spieler s = new Spieler(this.spiel, "Arnold", FarbEnum.GRUEN, null);
		
		assertNotNull(s);
		
		Spielfigur sf = s.getFigurDurchID(0);
		
		assertNotNull(sf);
		
		Spielfeld sFeld = sf.getSpielfeld();
		
		assertNotNull(sFeld);
		
		assertTrue(sFeld.getID().equals("S1 GRUEN"));
		
		assertEquals(s.getFarbe(), FarbEnum.GRUEN);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void test1() {
		Spieler s = new Spieler(this.spiel, "Kurt", FarbEnum.ROT, null);
		
		s.getFigurDurchID(5);
	}
	
	@Test
	public void test2() {
		Spieler s = new Spieler(this.spiel, "Heinz", FarbEnum.GELB);
		
		assertEquals(s.getFeldvorEndfeld(), "30");
		assertEquals(s.getRausZiehFeld(), "31");
		
		s = new Spieler(this.spiel, "Heinz", FarbEnum.ROT);
		assertEquals(s.getFeldvorEndfeld(), "40");
		assertEquals(s.getRausZiehFeld(), "1");
		
		s = new Spieler(this.spiel, "Heinz", FarbEnum.GRUEN);
		assertEquals(s.getFeldvorEndfeld(), "20");
		assertEquals(s.getRausZiehFeld(), "21");
		
		s = new Spieler(this.spiel, "Heinz", FarbEnum.BLAU);
		assertEquals(s.getFeldvorEndfeld(), "10");
		assertEquals(s.getRausZiehFeld(), "11");
	}
}
