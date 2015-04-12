/**
 * Die Basisklasse der beiden KIs
 */
public abstract class KI {
	
	/** Der Spieler, dem diese KI gehoert */
	protected Spieler spieler;
	/** Alle gegnerischen Figuren */
	protected Spielfigur[][] gegnerFiguren;
	/** Die eigenen Figuren der Ki */
	protected Spielfigur[] eigeneFiguren = new Spielfigur[4];
	
	/**
	 * Berechnet anhand der gegebenen Parameter den Zug, der am besten zur KI Persoenlichkeit passt
	 * @param gegnerFiguren Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 falls kein Zug moeglich
	 */
	public abstract int zugBerechnen(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl);
	
	/**
	 * Berechnet einen normalen Zug, fall alle anderen Zugmoeglichkeiten nicht gegeben sind
	 * @param gegnerFiguren Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 falls kein Zug moeglich
	 */
	protected abstract int normalerZug(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl);
	
	/**
	 * <b> Muss von den Konstruktoren der abgeleiteten Klassen benuetzt werden, 
	 * um den Spieler zu setzen. </b> <br>
	 * Setzt den Spieler dieser KI, und fuellt das Array eigeneFiguren
	 * @param spieler Der Spieler, dem diese KI gehoert
	 */
	protected final void setSpieler(Spieler spieler) {
		if (spieler == null) {
			throw new IllegalArgumentException("Kann keine KI ohne Spieler erstellen.");
		}
		
		for (int i = 0; i < 4; ++i) {
			this.eigeneFiguren[i] = spieler.getFigurDurchID(i);
		}
		this.spieler = spieler;
	}
	
	/**
	 * Berechnet, ob eine gegnerische Figur geschlagen werden kann
	 * @param gegnerFiguren Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 wenn kein Schlagen moeglich
	 */
	protected final int figurSchlagen(Spielfigur[][] gegnerFiguren, int gewuefelteZahl) {
		/*
		 * Vorgehensweise:
		 * Berechne das Zielfeld jeder eigenen Figur
		 * Schaue ob irgendeine Gegnerfigur dasselbe Feld belegt
		 * Wenn mehrere dies tun, nimm die Figur, die dem Gegner am meisten Schaden
		 * zufuegt (Die Figur des Gegners, die die geringste Distanz zum ihrem Endfeld hat
		 */
		return -1;
	}
	
	/**
	 * Berechnet, ob eine eigenen Figur ins Endfeld gebracht werden kann
	 * @param gegnerFiguren Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 wenn kein Beenden moeglich
	 */
	protected final int spielBeenden(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl) {
		/*
		 * Vorgehensweise
		 * Berechne, ob eine eigenene Figur ins ein Endfeld kommen kann,
		 * wenn nicht, schaue ob eigene Figuren im Endfeld bewegt werden koennen 
		 */
		return -1;
	}
	
	/**
	 * Berechnet, ob eine eigene Figr von den Startfeldern aufs Spielfeld gebracht werden kann
	 * @param gegnerFiguren Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 wenn kein Rausziehen moeglich
	 */
	protected final int figurRausziehen(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl) {
		/*
		 * Pruefe ob 6 gewuerfelt
		 * Schaue ob das Startspielfeld belegt ist,
		 * Nimm die naechste Figur auf das Startspielfeld
		 */
		return -1;
	}
	
}