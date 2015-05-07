package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.SelectionMode;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.iDatenzugriff;
import Spiel.Spiel;

public class FileChooserGUI {
	public static void main(String[] args) {
		//dateiOeffnen();
	}

	public static Spiel dateiOeffnen(madnGUI gui)
			throws ClassNotFoundException, IOException,
			SpielerFarbeVorhandenException, SpielerNichtGefundenException {
		JFileChooser chooser = new JFileChooser();

		// Erzeugung eines neuen Frames mit dem Titel "Dateiauswahl"
		// JFrame jf = new JFrame("Dateiauswahl");
		// Wir setzen die Breite auf 450 und die Höhe 300 pixel
		// jf.setSize(450,300);
		// Hole den ContentPane und füge diesem unseren JFileChooser hinzu
		// jf.getContentPane().add(chooser);
		// Wir lassen unseren Frame anzeigen
		// jf.setVisible(true);

		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("PDF",
				"pdf"));
		chooser.setFileFilter(new FileNameExtensionFilter("SER", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int showOpenDialog = chooser.showOpenDialog(null);
		if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
			String dateiName = chooser.getSelectedFile().getAbsolutePath();
			String lcDateiName = dateiName.toLowerCase();

			iDatenzugriff id = null;
			if (lcDateiName.endsWith(".pdf")) {
				 id = new DatenzugriffPDF();
			} else if (lcDateiName.endsWith(".ser")) {
				 id = new DatenzugriffSerialisiert();
			} else if (lcDateiName.endsWith(".csv")) {
				 id = new DatenzugriffCSV();
			} else {
				gui.zeigeFehler("Es wurde eine ungueltige Datei zum Laden ausgewaehlt");
				return null;
			}
			
			try {
				return (Spiel) id.spielLaden(dateiName);
			} catch (FileNotFoundException e) {
				gui.zeigeFehler("Die gewuenschte Datei kann nicht gefunden werden!");
			}
		}
		return null;
	}

	public static boolean dateiSpeicher(madnGUI gui, Spiel spiel) {
		JFileChooser chooser = new JFileChooser();
//		// Erzeugung eines neuen Frames mit dem Titel "Dateiauswahl"
//		JFrame jf = new JFrame("Dateiauswahl");
//		// Wir setzen die Breite auf 450 und die Höhe 300 pixel
//		jf.setSize(450, 300);
//		// Hole den ContentPane und füge diesem unseren JFileChooser hinzu
//		jf.getContentPane().add(chooser);
//		// Wir lassen unseren Frame anzeigen
//		jf.setVisible(true);

		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("PDF",
				"pdf"));
		chooser.setFileFilter(new FileNameExtensionFilter("SER", "ser"));
		chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int ergebnis = chooser.showSaveDialog(null);
		
		if (ergebnis == JFileChooser.APPROVE_OPTION) {
			String dateiName = chooser.getSelectedFile().getAbsolutePath();
			
			
			if (!dateiName.contains(".")) {
				FileNameExtensionFilter s = (FileNameExtensionFilter)chooser.getFileFilter();
				
				if (s.getExtensions()[0].equals("pdf")) {
					dateiName += ".pdf";
				} else if (s.getExtensions()[0].equals("ser")) {
					dateiName += ".ser";
				} else if (s.getExtensions()[0].equals("csv")) {
					dateiName += ".csv";
				} else {
				
					gui.zeigeFehler("Kann nicht speichern, da keine Endung angegeben");
					return false;
				}
			}
			
			String lcDateiName = dateiName.toLowerCase();
			iDatenzugriff id = null;
			
			if (lcDateiName.endsWith(".pdf")) {
				 id = new DatenzugriffPDF();
			} else if (lcDateiName.endsWith(".ser")) {
				 id = new DatenzugriffSerialisiert();
			} else if (lcDateiName.endsWith(".csv")) {
				 id = new DatenzugriffCSV();
			} else {
				gui.zeigeFehler("Der Dateiname hat keine gültige Endung");
				return false;
			}
			
			try {
				id.spielSpeichern(spiel, dateiName);
			} catch (IOException e) {
				gui.zeigeFehler("Speichern fehlgeschlagen: " + e.getMessage());
			}
			
		}
		
		return false;
	}
}
