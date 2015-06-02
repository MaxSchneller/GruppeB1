package Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;
import Spiel.SpielBean;
import Spiel.ZugErgebnis;

public class KI_AggressivTest {

	private SpielBean spiel;

	@Before
	public void setUp() throws Exception {
		this.spiel = new SpielBean("KI_Aggro", FarbEnum.BLAU, KiTypEnum.AGGRESIV);
	}

	@Test
	public void test() throws KannNichtWuerfelnException {

		spiel.debugWuerfeln(6);

		ZugErgebnis ergebnis = spiel.ziehen(0);

		assertTrue(ergebnis.isGueltig());

		String[][] gf = ergebnis.getGeaenderteFiguren();

		assertEquals("11", gf[0][2]);
	}

	@Test
	public void test2() throws KannNichtWuerfelnException {
		spiel.debugWuerfeln(6);

		spiel.ziehen(0);

		spiel.debugWuerfeln(6);

		ZugErgebnis ergebnis = spiel.ziehen(0);

		assertTrue(ergebnis.isGueltig());

		assertEquals("17", ergebnis.getGeaenderteFiguren()[0][2]);
	}

	@Test
	public void test3() throws SpielerNichtGefundenException, KannNichtWuerfelnException {

		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "11");

		spiel.debugWuerfeln(6);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("0", ergebnis.getGeaenderteFiguren()[0][1]);
	}

	@Test
	public void test4() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "17");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "11");

		spiel.debugWuerfeln(6);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("0", ergebnis.getGeaenderteFiguren()[0][1]);
	}
	
	@Test
	public void test5() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "17");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "11");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 2, "23");

		spiel.debugWuerfeln(6);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("2", ergebnis.getGeaenderteFiguren()[0][1]);
	}
	@Test
	public void test6() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "17");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "14");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 2, "23");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 3, "12");

		spiel.debugWuerfeln(4);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("2", ergebnis.getGeaenderteFiguren()[0][1]);
	}
	
	@Test
	public void test7() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "E4 BLAU");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "E3 BLAU");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 2, "E1 BLAU");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 3, "10");

		spiel.debugWuerfeln(1);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("2", ergebnis.getGeaenderteFiguren()[0][1]);
		
		spiel.debugWuerfeln(1);
		
		ergebnis = spiel.ziehen(2);
		
		assertTrue(ergebnis.isGueltig());

		assertEquals("3", ergebnis.getGeaenderteFiguren()[0][1]);
	}
	
	@Test
	public void test8() throws SpielerNichtGefundenException, KannNichtWuerfelnException {
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "E4 BLAU");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "E3 BLAU");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 2, "E1 BLAU");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 3, "8");

		spiel.debugWuerfeln(2);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("3", ergebnis.getGeaenderteFiguren()[0][1]);
		
		
	}
	
	@Test
	public void test9() throws SpielerNichtGefundenException, SpielerFarbeVorhandenException, KannNichtWuerfelnException {
		
		spiel.spielerHinzufuegen("Karl", FarbEnum.GELB, null);
		
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "17");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "14");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 2, "23");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 3, "12");
		
		spiel.debugSetzeFigur(FarbEnum.GELB, 0, "13");

		spiel.debugWuerfeln(1);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("3", ergebnis.getGeaenderteFiguren()[0][1]);
		
		
	}
	
	@Test
	public void test10() throws SpielerNichtGefundenException, SpielerFarbeVorhandenException, KannNichtWuerfelnException {
		
		spiel.spielerHinzufuegen("Karl", FarbEnum.GELB, null);
		
		spiel.debugSetzeFigur(FarbEnum.BLAU, 0, "17");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 1, "14");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 2, "23");
		spiel.debugSetzeFigur(FarbEnum.BLAU, 3, "13");
		
		spiel.debugSetzeFigur(FarbEnum.GELB, 0, "16");
		spiel.debugSetzeFigur(FarbEnum.GELB, 1, "25");

		spiel.debugWuerfeln(1);

		ZugErgebnis ergebnis = spiel.ziehen(3);

		assertTrue(ergebnis.isGueltig());

		assertEquals("3", ergebnis.getGeaenderteFiguren()[0][1]);	
	}

}
