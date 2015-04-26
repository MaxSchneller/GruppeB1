package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import jdk.internal.dynalink.support.DefaultPrelinkFilter;

import org.junit.Before;
import org.junit.Test;

import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;

public class DatenzugriffPDFTest {
	
	protected Spiel spiel;

	@Before
	public void before() {
		this.spiel = new Spiel("Heinz", FarbEnum.BLAU, null);
	}
	@Test
	public void test() throws IOException {
		iDatenzugriff d = new	DatenzugriffPDF();
		
		d.spielSpeichern(spiel);
	}

}
