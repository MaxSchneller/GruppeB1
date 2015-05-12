package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;
import Spiel.iBediener;

public class SpielVorEndeErstellen {

	@Test
	public void test() throws SpielerFarbeVorhandenException, SpielerNichtGefundenException, IOException {
		iBediener b = new Spiel("Spieler1", FarbEnum.ROT, null);
			b.spielerHinzufuegen("Spieler2", FarbEnum.BLAU, null);
			b.spielerHinzufuegen("Spieler3", FarbEnum.GELB, null);
			b.spielerHinzufuegen("Spieler4", FarbEnum.GRUEN, null);
		
			b.debugSetzeFigur(FarbEnum.ROT, 0, "E4 ROT");
			b.debugSetzeFigur(FarbEnum.ROT, 1, "E3 ROT");
			b.debugSetzeFigur(FarbEnum.ROT, 2, "E2 ROT");
			b.debugSetzeFigur(FarbEnum.ROT, 3, "35");
			
			b.debugSetzeFigur(FarbEnum.BLAU, 0, "E4 BLAU");
			b.debugSetzeFigur(FarbEnum.BLAU, 1, "E3 BLAU");
			b.debugSetzeFigur(FarbEnum.BLAU, 2, "E2 BLAU");
			b.debugSetzeFigur(FarbEnum.BLAU, 3, "5");
			
			b.debugSetzeFigur(FarbEnum.GELB, 0, "E4 GELB");
			b.debugSetzeFigur(FarbEnum.GELB, 1, "E3 GELB");
			b.debugSetzeFigur(FarbEnum.GELB, 2, "E2 GELB");
			b.debugSetzeFigur(FarbEnum.GELB, 3, "25");
			
			b.debugSetzeFigur(FarbEnum.GRUEN, 0, "E4 GRUEN");
			b.debugSetzeFigur(FarbEnum.GRUEN, 1, "E3 GRUEN");
			b.debugSetzeFigur(FarbEnum.GRUEN, 2, "E2 GRUEN");
			b.debugSetzeFigur(FarbEnum.GRUEN, 3, "15");
			
			iDatenzugriff d = new DatenzugriffPDF();
			
			d.spielSpeichern(b, "Dateien_Gespeichert/spielKurzVorEnde.pdf");
	
	}

}
