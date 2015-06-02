package Fehler_Exceptions;

/**
 * Der Nutzer hat versucht zu wuerfeln obwohl noch ein Zug ausgefuehrt werden muss 
 */
public class KannNichtWuerfelnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Kann nicht wuerfeln, da zuerst Zug ausgefuehrt werden muss";
	}
	
}
