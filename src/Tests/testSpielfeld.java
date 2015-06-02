package Tests;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Spiel.FarbEnum;
import Spiel.SpielBean;
import Spiel.Spielbrett;
import Spiel.Spielfeld;


public class testSpielfeld {
	
	protected static Spielbrett s;
	protected static SpielBean sp = new SpielBean("YOLO", FarbEnum.BLAU, null);
	
	@BeforeClass
	public static void ganzVorher() {
		s = new Spielbrett(sp);
	}

	@Test(expected=RuntimeException.class)
	public void test1() {
		new Spielfeld("Blals", s);
	}
	
	@Test(expected=RuntimeException.class)
	public void test2() {
		Spielfeld f = new Spielfeld("C3", s);
	}

	@Test(expected=RuntimeException.class)
	public void test3() {
		Spielfeld f = new Spielfeld("S1 ORANGE", s);
	}

	@Test(expected=RuntimeException.class)
	public void test4() {
		Spielfeld f = new Spielfeld("    ", s);
	}
	
	@Test(expected=NumberFormatException.class)
	public void test5() {
		Spielfeld f = new Spielfeld("SA ROT", s);
	}
	
	@Test
	public void test6() {
		Spielfeld f = new Spielfeld("1", s);
	}
	
	@Test
	public void test7() {
		Spielfeld f = new Spielfeld("23", s);
	}
	
	@Test(expected=RuntimeException.class)
	public void test8() {
		Spielfeld f = new Spielfeld("41", s);
	}

}
