package Speichern_Laden;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Spiel.SpielBean;

/**
 * Die Klasse DatenzugriffSerialisiert, welches das Interface iDatenzugriff und
 * Serializable implementiert.
 * 
 * @author Gruppe B1
 *
 */

public class DatenzugriffSerialisiert implements iDatenzugriff {

	/**
	 * Ueberschreibt die Methode des Interface iDatenzugriff, um den Sielstand
	 * als Serialisierte Datei speichert.
	 */
	@Override
	public void spielSpeichern(Object spiel, String dateipfad) {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(dateipfad));
			oos.writeObject(spiel);
		} catch (FileNotFoundException e) {
			System.err.println("Konnte " + dateipfad + " nicht erzeugen.");
		} catch (IOException e) {
			System.err.println("Fehler bei der Ein/Ausgabe: " + e);
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
				System.err.println("Fehler beim schließen.");
			}
		}
	}

	/**
	 * Ueberschreibt die Methode spielLaden des Interface iDatenzugriff, um die
	 * serialisierte Datei des Sielstands zu laden.
	 */
	@Override
	public Object spielLaden(String dateipfad) throws ClassNotFoundException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(dateipfad));
			Object o = ois.readObject();
			if (o instanceof SpielBean) {
				return o;
			} else {
				throw new RuntimeException("Fehler!");
			}
		} catch (FileNotFoundException e) {
			System.err.println("Konnte " + dateipfad + " nicht laden.");
		} catch (IOException e) {
			System.err.println("Fehler bei der Ein/Ausgabe: " + e);
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
				System.err.println("Fehler beim schließen.");
			}
		}
		return null;
	}

}
