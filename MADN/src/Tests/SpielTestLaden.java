package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.Spiel;
import Spiel.iBediener;


public class SpielTestLaden {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		
		// Wo gespeichert
		iDatenzugriff id = new DatenzugriffSerialisiert();
		iDatenzugriff id1 = new DatenzugriffCSV();
		
		// Den Speicherstand (Selialisiert) laden
		iBediener s1 = id.spielLaden();

		String[] ausgabe = s1.getSpieler();
		System.out.println("Serialisiert:\n===================");
		for (int i = 0; i < ausgabe.length; i++) {
			//System.out.println(ausgabe[i]);
			
			String[] teile = ausgabe[i].split(" ; ");
			
			String s = "";
			
			s += "Name : " + teile[0] + ", ";
			s += "Farbe : " + teile[1] + ", ";
			s += "KI Typ : " + teile[2] + "\n";
			s += "Figuren :\n";
			for (int j = 3; j < teile.length; ++j) {
				s += "\t" + "Figur " + (j - 3) + ": auf Feld: " + teile[j] + "\n";
			}
			
			System.out.println(s);
		}

		// Den Speicherstand (CVS) laden
		iBediener var = id1.spielLaden();

		String[] ausgabe1 = var.getSpieler();
		System.out.println("CSV:\n====================");
		for (int i = 0; i < ausgabe1.length; i++) {
			//System.out.println(ausgabe[i]);
			
			String[] teile = ausgabe1[i].split(" ; ");
			
			String s = "";
			
			s += "Name : " + teile[0] + ", ";
			s += "Farbe : " + teile[1] + ", ";
			s += "KI Typ : " + teile[2] + "\n";
			s += "Figuren :\n";
			for (int j = 3; j < teile.length; ++j) {
				s += "\t" + "Figur " + (j - 3) + ": auf Feld: " + teile[j] + "\n";
			}
			
			System.out.println(s);
		}
	}
}
