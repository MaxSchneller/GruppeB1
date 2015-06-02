package Tests;

import java.io.IOException;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;
import Spiel.iBediener;

public class SpielTestSpeichern {
	
	public static void main (String [] args) throws ClassNotFoundException, SpielerNichtGefundenException, IOException, SpielerFarbeVorhandenException, KannNichtWuerfelnException{
		
		//Vorbedingungen
		iBediener s = new Spiel("MADN", FarbEnum.BLAU, null);
		s.spielerHinzufuegen("Maggus", FarbEnum.GELB, KiTypEnum.AGGRESIV);
		s.spielerHinzufuegen("Peter", FarbEnum.ROT, null);
		s.spielerHinzufuegen("Olga", FarbEnum.GRUEN, KiTypEnum.DEFENSIV);
		s.sWuerfeln();
		
		//Blauer Spieler
		s.debugSetzeFigur(FarbEnum.BLAU, 0, "40");
		s.debugSetzeFigur(FarbEnum.BLAU, 1, "15");
		
		//Gelber Spieler
		s.debugSetzeFigur(FarbEnum.GELB, 0, "1");
		s.debugSetzeFigur(FarbEnum.GELB, 1, "30");
		
		//Roter Spieler alle auff Startfelder
		
		//Gruener Spieler
		s.debugSetzeFigur(FarbEnum.GRUEN, 0, "13");
		s.debugSetzeFigur(FarbEnum.GRUEN, 1, "14");
		s.debugSetzeFigur(FarbEnum.GRUEN, 2, "E4");
		s.debugSetzeFigur(FarbEnum.GRUEN, 3, "E2");
		
		//Wo gespeichert
		iDatenzugriff id = new DatenzugriffSerialisiert();
		iDatenzugriff id1 = new DatenzugriffCSV();
		
		//Als .ser speichern
		id.spielSpeichern((Spiel)s, "Dateien_Gespeichert/test.ser");
		
		//Als .csv speichern
		id1.spielSpeichern((Spiel)s, "Dateien_Gespeichert/test.csv");
		
		}
}
