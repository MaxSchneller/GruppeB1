package Speichern_Laden;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;
import Spiel.Spiel;

/**
 * Die Klasse DatenzugriffCSV, welches das Interface iDatenzugriff und Serializable implementiert.
 * @author Gruppe B1
 *
 */
public class DatenzugriffCSV implements iDatenzugriff {

	/**
	 * Ueberschreibt die Methode des Interface iDatenzugriff, um den Sielstand als CSV Datei zu speichert.
	 */
	@Override
	public void spielSpeichern(Object spiel, String dateipfad) throws IOException {
	
		String dateiPfad = dateipfad;
		
		if (spiel instanceof Spiel) {
			
			BufferedWriter bw = null;
			Spiel s = (Spiel)spiel;
		
			try {
				bw = new BufferedWriter(new FileWriter(dateiPfad));
				for (String spieler : s.getSpieler()) {
					bw.write(spieler);
					bw.newLine();
				}
				bw.write(s.getSpielerAmZugFarbe().name());
				bw.newLine();
				
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
		}
		
	}

	/**
	 * Ueberschreibt die Methode spielLaden des Interface iDatenzugriff, um die CSV Datei des Sielstands zu laden.
	 */
	@Override
	public Object spielLaden(String dateipfad) throws ClassNotFoundException, IOException, SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		
		String dateiPfad = dateipfad;
		
		BufferedReader br = new BufferedReader(new FileReader(dateiPfad));
		boolean wurdeSpielErstellt = false;
		Spiel var = null;
		
		while(br.ready()){
			String zeile = br.readLine();
			String[] teile = zeile.split(";");
			
			if(teile.length > 1){
				String name = teile[0].trim();
				FarbEnum farbe = FarbEnum.vonString(teile[1].trim());
				KiTypEnum typ = KiTypEnum.vonString(teile[2].trim());
				if(wurdeSpielErstellt == false){
					var = new Spiel(name, farbe, typ);
					wurdeSpielErstellt = true;
				} else {
					var.spielerHinzufuegen(name, farbe, typ);
				}
				
				for(int i = 0; i < 4; i++){
					var.debugSetzeFigur(farbe, i, teile[3+i].trim());
				}
			} else {
				FarbEnum spielerAmZugFarbe = FarbEnum.vonString(teile[0].trim());
				var.setSpielerAmZug(spielerAmZugFarbe);
			}
		}
		
		br.close();
		return var;
	}

}
