/**
 * Über diese Schnittstelle wird das Spiel bedient
 */
public interface iBediener {

	/**
	 * Einen neuen Spieler zum Spiel hinzufügen
	 * @param name Der Name des Spielers (darf mehrfach vorkommen)
	 * @param farbe Die Farbe des Spielers (muss einzigartig im Spiel sein)
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
	public int sWuerfeln();

	/**
	 * Lässt den Spieler, der am Zug ist, die gewünschte Figur auf das gewünschte Feld ziehen, falls dies möglich ist
	 * @param figurID Die ID der Figur, welche versetzt werden soll
	 * @param zielFeldID Die ID des Feldes auf das die Figur gesetzt werden soll
	 * @return Das Ergebnis dieses Zuges
	 */
	public ZugErgebnis ziehen(int figurID);
	
	/**
	 * Verhält sich wie ein gezinkter Würfel (setzt Spiel.zuletztGewuerfelt)
	 * @param gewuenschteZahl Die Zahl die gewuerfelt werden soll
	 * @return Die gewuerfelte Zahl
	 */
	public int debugWuerfeln(int gewuenschteZahl);

	/**
	 * Setzt die gewünschte Figur ohne Regelprüfung auf das gewünschte Feld
	 * @param spielerFarbe Farbe des Spielers dessen Figur versetzt werden soll
	 * @param figurID Die ID der Figur, welche versetzt werden soll
	 * @param zielFeldID Die ID des Zielfeldes
	 * @throws SpielerNichtGefundenException Es gibt keinen Spieler mit der farbe "spielerFarbe"
	 */
	public void debugSetzeFigur(FarbEnum spielerFarbe, int figurID, String zielFeldID) throws SpielerNichtGefundenException;
	
	/**
	 * Gibt die Farbe des Spielers, der gerade am Zug ist zurueck
	 */
	public FarbEnum getSpielerAmZugFarbe();

}
