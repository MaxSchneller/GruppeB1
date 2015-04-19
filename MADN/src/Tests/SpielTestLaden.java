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


public class SpielTestLaden {

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		
		// Wo gespeichert
		iDatenzugriff id = new DatenzugriffSerialisiert();
		iDatenzugriff id1 = new DatenzugriffCSV();
		
		// Den Speicherstand (Selialisiert) laden
		Spiel s1 = id.spielLaden();

		String[][] ausgabe = s1.getAlleFigurenPositionen();
		System.out.println("Selialisiert:");
		for (int i = 0; i < ausgabe.length; i++) {
			String str = "";
			for (int j = 0; j < ausgabe[i].length; j++) {
				str += ausgabe[i][j] + " ";
			}
			System.out.println(str);
		}

		// Den Speicherstand (CVS) laden
		Spiel var = id1.spielLaden();

		String[][] ausgabe1 = var.getAlleFigurenPositionen();
		System.out.println("CSV:");
		for (int p = 0; p < ausgabe1.length; p++) {
			String str = "";
			for (int q = 0; q < ausgabe1[p].length; q++) {
				str += ausgabe1[p][q] + " ";
			}
			System.out.println(str);
		}
	}
}
