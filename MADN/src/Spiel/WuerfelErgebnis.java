package Spiel;

import java.io.Serializable;


/**
 * Das Ergebnis eines Wuerfelversuches
 */
public class WuerfelErgebnis implements Serializable {

	public int getGewuerfelteZahl() {
		return gewuerfelteZahl;
	}

	public boolean isKannZugAusfuehren() {
		return kannZugAusfuehren;
	}

	public boolean isKannNochmalWuerfeln() {
		return kannNochmalWuerfeln;
	}

	/** Die Zahl, welche gewuerfelt wurde */
	private int gewuerfelteZahl;
	/** Kann mit der gewuerfelten Zahl ein Zug ausgefuehrt werden ? */
	private boolean kannZugAusfuehren;
	/** Darf der Spieler nochmal wuerfeln */
	private boolean kannNochmalWuerfeln;

	/**
	 * Erstellt ein neues Wuerfelergebnis
	 * @param gewuerfelteZahl Die Zahl die gewuerfelt wurde
	 * @param kannZugAusfuehren Kann mit der gewuerfelten Zahl ein Zug ausgefuehrt werden
	 * @param kannNochmalWuerfeln Darf der Spieler im Falle eines nicht moeglichen Zuges nochmal wuerfeln
	 */
	public WuerfelErgebnis(int gewuerfelteZahl, boolean kannZugAusfuehren,
							boolean kannNochmalWuerfeln) {
		setGewuerfelteZahl(gewuerfelteZahl);
		this.kannZugAusfuehren = kannZugAusfuehren;
		this.kannNochmalWuerfeln = kannNochmalWuerfeln;
	}
	
	private void setGewuerfelteZahl(int zahl) {
		if (zahl < 1 || zahl > 6) {
			throw new IllegalArgumentException("Gewuerfelte Zahl kann nicht von einem Wuerfel stammen");
		}
		
		this.gewuerfelteZahl = zahl;
	}
}
