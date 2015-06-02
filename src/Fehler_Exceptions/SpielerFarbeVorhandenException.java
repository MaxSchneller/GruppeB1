package Fehler_Exceptions;

import Spiel.FarbEnum;

/**
 * Die Klasse SpielerFarbeVorhandenException
 * @author Gruppe B1
 */
public class SpielerFarbeVorhandenException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Hat ein Spieler schon eine Farbe gewählt, kann ein anderer diese nicht nutzen. Dann wird diese Fehlermeldung auftreten.
	 * @param farbe Die Farbe aus dem Farbenum
	 */
	public SpielerFarbeVorhandenException(FarbEnum farbe){
		super("Die Farbe \"" + farbe.toString() +"\" ist bereits vorhanden.");
	}

}
