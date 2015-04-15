package Tests;

import java.io.IOException;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;

public class SpielTestSpeichern {
	public static void main (String [] args) throws ClassNotFoundException, SpielerNichtGefundenException, IOException, SpielerFarbeVorhandenException{
		iDatenzugriff id = new DatenzugriffSerialisiert();
		iDatenzugriff id1 = new DatenzugriffCSV();
		
		Spiel s = new Spiel("MADN", FarbEnum.BLAU, null);
		s.spielerHinzufuegen("Mongo", FarbEnum.GELB, KiTypEnum.AGGRESIV);
		s.sWuerfeln();
		
		s.debugSetzeFigur(FarbEnum.BLAU, 0, "40");
		id.spielSpeichern(s);
		Spiel s1 = id.spielLaden();
		String[][] ausgabe = s1.getAlleFigurenPositionen();
		
		for(int i = 0; i < ausgabe.length; i++){
			String str = "";
			for(int j = 0; j < ausgabe[i].length; j++){
				str += ausgabe[i][j] + " ";
			}
			System.out.println(str);
		}
		
		id1.spielSpeichern(s);
		Spiel var = id1.spielLaden();
		
		String[][] ausgabe1 = var.getAlleFigurenPositionen();
		
		for(int i = 0; i < ausgabe1.length; i++){
			String str = "";
			for(int j = 0; j < ausgabe1[i].length; j++){
				str += ausgabe1[i][j] + " ";
			}
			System.out.println(str);
		}
	}
}
