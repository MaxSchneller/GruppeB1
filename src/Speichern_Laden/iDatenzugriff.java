package Speichern_Laden;

import java.io.FileNotFoundException;
import java.io.IOException;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Spiel.Spiel;

/**
 * Ueber dieses Interface wird der Zugriff auf die Daten, die zu speichern oder laden sind bedient.
 * @author B1
 */
public interface iDatenzugriff {
	
	/**
	 * Einen Spielstand speichern
	 * @param spiel Ein Spielstand der gespeichert werden soll (Lauft alles im Spiel ab)
	 * @param dateipfad Gewuenschter Speicherpfad
	 * @throws IOException Fehlermeldung bei falscher Ein- und Ausgabe
	 */
	public void spielSpeichern (Object spiel, String dateipfad) throws IOException;
	
	/**
	 * @param dateipfad Pfad zur Datei, die geladen werden soll
	 * @return Gibt den gespeicherten Spielstand zurück, falls vorhanden.
	 * @throws ClassNotFoundException Fehlermeldung falls es keine Datei gibt, zum lesen
	 * @throws FileNotFoundException Fehlermeldung beim laden des Spielstands
	 * @throws IOException Fehlermeldung bei falscher Ein- und Ausgabe
	 * @throws SpielerFarbeVorhandenException Hat ein Spieler schon eine Farbe gewählt, kann ein anderer diese nicht nutzen.
	 * @throws SpielerNichtGefundenException Fehlermeldung, wenn es keinen Spieler mit dieser Farbe gibt.
	 */
	public Object spielLaden(String dateipfad) throws ClassNotFoundException, FileNotFoundException, IOException, SpielerFarbeVorhandenException, SpielerNichtGefundenException;
}
