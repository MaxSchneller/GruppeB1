package Fehler_Exceptions;

import Spiel.FarbEnum;

/**
 * Die Klasse SpielerNichtGefundenException
 * @author Gruppe B1
 */
public class SpielerNichtGefundenException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Diese Fehlermeldung tritt auf, wenn es keinen Spieler mit dieser Farbe gibt.
	 * @param farbe Die Farbe aus dem Farbenum
	 */
	public SpielerNichtGefundenException(FarbEnum farbe) {
		super("Der Spieler mit der Farbe \"" + farbe.toString() + "\" wurde nicht gefunden.");
	}
}
