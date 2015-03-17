
public class SpielerNichtGefundenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpielerNichtGefundenException(FarbEnum farbe) {
		super("Der Spieler mit der Farbe \"" + farbe.toString() + "\" wurde nicht gefunden.");
	}
}
