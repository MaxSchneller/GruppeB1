package Spiel;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;

/**
 * Über diese Schnittstelle wird das Spiel bedient
 */
public interface iBediener {

	/**
	 * Einen neuen Spieler zum Spiel hinzufügen
	 * @param name Der Name des Spielers (darf mehrfach vorkommen)
	 * @param farbe Die Farbe des Spielers (muss einzigartig im Spiel sein)
	 * @param kiTyp Ist die KI Aggressiv, Defensiv oder keine
	 * @throws SpielerFarbeVorhandenException Die Farbe ist bereits vergeben
	 */
	public void spielerHinzufuegen(String name, FarbEnum farbe, KiTypEnum kiTyp) throws SpielerFarbeVorhandenException;

	/**
	 * Entfernt den Spieler mit der angegebenen Farbe aus dem Spiel
	 * @param farbeDesSpielers Die Farbe des Spielers, der entfernt werden soll
	 * @throws SpielerNichtGefundenException  Der Spieler wurde nicht gefunden
	 */
	public void spielerEntfernen(FarbEnum farbeDesSpielers) throws SpielerNichtGefundenException ;

	/**
	 * Lässt den Spieler, der gerade an der Reihe ist würfeln
	 * @return Die gewürfelte Zahl
	 */
	public WuerfelErgebnis sWuerfeln();

	/**
	 * Lässt den Spieler, der am Zug ist, die gewünschte Figur auf das gewünschte Feld ziehen, falls dies möglich ist
	 * @param figurID Die ID der Figur, welche versetzt werden soll
	 * @return Das Ergebnis dieses Zuges
	 */
	public ZugErgebnis ziehen(int figurID);
	
	/**
	 * Verhält sich wie ein gezinkter Würfel (setzt Spiel.zuletztGewuerfelt)
	 * @param gewuenschteZahl Die Zahl die gewuerfelt werden soll
	 * @return Die gewuerfelte Zahl
	 */
	public WuerfelErgebnis debugWuerfeln(int gewuenschteZahl);

	/**
	 * Setzt die gewünschte Figur ohne Regelprüfung auf das gewünschte Feld
	 * @param spielerFarbe Farbe des Spielers dessen Figur versetzt werden soll
	 * @param figurID Die ID der Figur, welche versetzt werden soll
	 * @param zielFeldID Die ID des Zielfeldes
	 * @throws SpielerNichtGefundenException Es gibt keinen Spieler mit der farbe "spielerFarbe"
	 */
	public void debugSetzeFigur(FarbEnum spielerFarbe, int figurID, String zielFeldID) throws SpielerNichtGefundenException;
	
	/**
	 * Gibt die Farbe des Spielers, der gerade am Zug ist zurueck7
	 * @return Gibt den Spieler zuruech, der am Zug ist
	 */
	public FarbEnum getSpielerAmZugFarbe();
	
	/**
	 * Format: <br>
	 * Index 0: Farbe der Figur <br>
	 * Index 1: ID der Figur <br>
	 * Index 2: ID des Feldes auf dem sie steht
	 * @return Positionen aller Figuren aller Spieler, die zur Zeit am Spiel teilnehmen
	 */
	public String[][] getAlleFigurenPositionen();
	
	/**
	 * Gibt an, ob der Spieler, der gerade am Zug ist, von einer KI gesteuert wird
	 * @return True falls eine KI den Spieler steuert, sonst false
	 */
	public boolean isSpielerAmZugKI();
	
	/**
	 * Alle Eigenschaften, die ein teilnehmender Spieler besitzt werden in einem Array gespeichert (Name, Farbe, ob es eine KI ist, und die Positionen der Spielfiguren)
	 * @return Gibt alle Eigenschaften, die ein Spieler besitzt zurueck
	 */
	public String[] getSpieler();
	
	/**
	 * Gibt an, ob eine Farbe bereits vergeben ist, oder noch genommen werden kann
	 * @param farbe Die zu pruefende Farbe
	 * @return True falls die Farbe vergeben ist, sonst false
	 */
	public boolean isFarbeVergeben(FarbEnum farbe);

}
