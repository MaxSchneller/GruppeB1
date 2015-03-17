
public interface iBediener {

	/**
	 * Einen neuen Spieler zum Spiel hinzufügen
	 * @param name Der Name des Spielers (darf mehrfach vorkommen)
	 * @param farbe Die Farbe des Spielers (muss einzigartig im Spiel sein)
	 * @throws SpielerFarbeVorhandenException Die Farbe ist bereits vergeben
	 */
	public void spielerHinzufügen(String name, FarbEnum farbe) throws SpielerFarbeVorhandenException;

	/**
	 * Entfernt den Spieler mit der angegebenen Farbe aus dem Spiel
	 * @param farbeDesSpielers Die Farbe des Spielers, der entfern werden soll
	 * @throws SpielerNichtGefundenException  Der Spieler wurde nicht gefunden
	 */
	public void spielerEntfernen(FarbEnum farbeDesSpielers) throws SpielerNichtGefundenException ;

}
