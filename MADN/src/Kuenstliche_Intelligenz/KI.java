package Kuenstliche_Intelligenz;
import java.io.Serializable;
import java.util.ArrayList;

import Spiel.Spieler;
import Spiel.Spielfeld;
import Spiel.Spielfigur;

/**
 * Die Basisklasse der beiden KIs
 */
public abstract class KI implements Serializable{

	/** Der Spieler, dem diese KI gehoert */
	protected Spieler spieler;
	/** Alle gegnerischen Figuren */
	protected Spielfigur[][] gegnerFiguren;
	/** Die eigenen Figuren der Ki */
	protected Spielfigur[] eigeneFiguren = new Spielfigur[4];

	/**
	 * Berechnet anhand der gegebenen Parameter den Zug, der am besten zur KI
	 * Persoenlichkeit passt
	 * 
	 * @param gegnerFiguren
	 *            Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl
	 *            Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 falls kein Zug moeglich
	 */
	public abstract int zugBerechnen(Spielfigur[][] gegnerFiguren,
			int gewuerfelteZahl);

	/**
	 * Berechnet einen normalen Zug, fall alle anderen Zugmoeglichkeiten nicht
	 * gegeben sind
	 * 
	 * @param gegnerFiguren
	 *            Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl
	 *            Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 falls kein Zug moeglich
	 */
	protected abstract int normalerZug(Spielfigur[][] gegnerFiguren,
			int gewuerfelteZahl);

	/**
	 * <b> Muss von den Konstruktoren der abgeleiteten Klassen benuetzt werden,
	 * um den Spieler zu setzen. </b> <br>
	 * Setzt den Spieler dieser KI, und fuellt das Array eigeneFiguren
	 * 
	 * @param spieler
	 *            Der Spieler, dem diese KI gehoert
	 */
	protected final void setSpieler(Spieler spieler) {
		if (spieler == null) {
			throw new IllegalArgumentException(
					"Kann keine KI ohne Spieler erstellen.");
		}

		for (int i = 0; i < 4; ++i) {
			this.eigeneFiguren[i] = spieler.getFigurDurchID(i);
		}
		this.spieler = spieler;
	}

	/**
	 * Berechnet, ob eine gegnerische Figur geschlagen werden kann
	 * 
	 * @param gegnerFiguren
	 *            Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl
	 *            Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 wenn kein Schlagen
	 *         moeglich
	 */
	protected final int figurSchlagen(Spielfigur[][] gegnerFiguren,
			int gewuefelteZahl) {
		/*
		 * Vorgehensweise: Berechne das Zielfeld jeder eigenen Figur Schaue ob
		 * irgendeine Gegnerfigur dasselbe Feld belegt Wenn mehrere dies tun,
		 * nimm die Figur, die dem Gegner am meisten Schaden zufuegt (Die Figur
		 * des Gegners, die die geringste Distanz zum ihrem Endfeld hat
		 */

		ArrayList<Spielfigur> figurenDieGegnerSchlagenKoennen = new ArrayList<Spielfigur>();
		String endFeldID = this.spieler.getFeldvorEndfeld();
		for (Spielfigur spielfigur : this.eigeneFiguren) {

			for (int i = 0; i < gegnerFiguren.length; ++i) {
				for (int j = 0; j < gegnerFiguren[i].length; ++j) {
					int distanz = this.berechneDistanzZwischenFiguren(
							spielfigur, gegnerFiguren[i][j]);
					int distanzZuEndfeld = this.berechneDistanzZuFeld(
							spielfigur, endFeldID);

					if (distanz == gewuefelteZahl
							&& distanzZuEndfeld >= distanz) {
						figurenDieGegnerSchlagenKoennen.add(spielfigur);
					}
				}
			}
		}

		// Alle Distanzen fertig
		int gegnerDistanzZuEndfeld = 40;
		Spielfigur finaleFigur = null;

		for (Spielfigur spielfigur : figurenDieGegnerSchlagenKoennen) {
			int feld = Integer.parseInt(spielfigur.getSpielfeld().getID());
			int zielFeld = feld + gewuefelteZahl;

			if (zielFeld > 40) {
				zielFeld = zielFeld - 40;
			}

			String zielFeldID = String.format("%d", zielFeld);

			Spielfigur figurAufFeld = this.getFigurAufFeld(gegnerFiguren,
					zielFeldID);

			String gegnerEndFeldID = figurAufFeld.getSpieler()
					.getFeldvorEndfeld();

			int distanz = this.berechneDistanzZuFeld(figurAufFeld,
					gegnerEndFeldID);

			if (distanz <= gegnerDistanzZuEndfeld) {
				gegnerDistanzZuEndfeld = distanz;
				finaleFigur = spielfigur;
			}

		}

		return finaleFigur == null ? -1 : finaleFigur.getID();
	}

	/**
	 * Berechnet, ob eine eigenen Figur ins Endfeld gebracht werden kann
	 * 
	 * @param gegnerFiguren
	 *            Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl
	 *            Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 wenn kein Beenden moeglich
	 */
	protected final int spielBeenden(Spielfigur[][] gegnerFiguren,
			int gewuerfelteZahl) {
		/*
		 * Vorgehensweise Berechne, ob eine eigenene Figur ins ein Endfeld
		 * kommen kann, wenn nicht, schaue ob eigene Figuren im Endfeld bewegt
		 * werden koennen
		 */

		String endFeldID = this.spieler.getFeldvorEndfeld();

		for (Spielfigur spielfigur : this.eigeneFiguren) {

			int distanz = this.berechneDistanzZuFeld(spielfigur, endFeldID);

			if (distanz == -1) {
				continue;
			}

			if (distanz < gewuerfelteZahl) {
				// Echt-kleiner noetig, da + 1 um wirklich im Endfeld zu sein

				int feldInt = Integer.parseInt(spielfigur.getSpielfeld()
						.getID());
				int zielInt = feldInt + distanz;
				int endFeldInt = Integer.parseInt(endFeldID);

				int endFeldNummer = zielInt - endFeldInt + 1;

				boolean kannZiehen = true;
				for (Spielfigur spielfigur2 : this.eigeneFiguren) {
					if (spielfigur2.getSpielfeld().isEndfeld()) {
						if (spielfigur2.getSpielfeld().getEndStartFeldNummer() <= endFeldNummer) {
							kannZiehen = false;
							break;
						}
					}
				}
				if (kannZiehen) {
					return spielfigur.getID();
				}
			}
		}
		// Es keine keine Figur ins Endfeld gebracht werden,
		if (gewuerfelteZahl < 4) {

			// Vielleicht koennen Figuren in den Feldern bewegt werden
			for (Spielfigur spielfigur : this.eigeneFiguren) {
				if (spielfigur.getSpielfeld().isEndfeld()) {
					int feldNummer = spielfigur.getSpielfeld()
							.getEndStartFeldNummer();

					int zielFeldNummer = feldNummer + gewuerfelteZahl;

					if (zielFeldNummer > 4) {
						// Kann allein durch Zahl nicht ziehen
						continue;
					} else {
						// Pruefen ob Figuren dazwischen
						boolean kannZiehen = true;
						for (Spielfigur spielfigur2 : this.eigeneFiguren) {
							int andereFeldNummer = spielfigur2.getSpielfeld()
									.getEndStartFeldNummer();

							if (andereFeldNummer <= zielFeldNummer
									&& andereFeldNummer > feldNummer
									&& spielfigur2.getSpielfeld().isEndfeld()) {
								kannZiehen = false;
								break;
							}
						}

						if (kannZiehen) {
							return spielfigur.getID();
						}
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Berechnet, ob eine eigene Figr von den Startfeldern aufs Spielfeld
	 * gebracht werden kann
	 * 
	 * @param gegnerFiguren
	 *            Alle Figuren aller Gegner, die am Spiel teilnehmen
	 * @param gewuerfelteZahl
	 *            Die gewuerfelte Zahl fuer diesen Zug
	 * @return Die ID der zu bewegenden Figur oder -1 wenn kein Rausziehen
	 *         moeglich
	 */
	protected final int figurRausziehen(Spielfigur[][] gegnerFiguren,
			int gewuerfelteZahl) {
		/*
		 * Pruefe ob 6 gewuerfelt Schaue ob das Startspielfeld belegt ist, Nimm
		 * die naechste Figur auf das Startspielfeld
		 */

		if (gewuerfelteZahl == 6) {

			String startFeldID = this.spieler.getRausZiehFeld();
			Spielfigur figurAufFeld = this.getFigurAufFeld(gegnerFiguren,
					startFeldID);

			if (figurAufFeld != null) {
				// Eine Figur steht auf dem Startspielfeld
				if (figurAufFeld.getFarbe() == this.spieler.getFarbe()) {
					// Eine eigene Figur steht auf dem Feld also kann nicht
					// rausgezogen werden
					return -1;
				}
			}
			// Es steht hoechstens ein Gegner auf dem Startspielfeld

			for (int i = 0; i < 4; ++i) {
				// Pruefen ob und welche der eigenen Figuren noch in den
				// Startfeldern stehen
				Spielfeld feldDerFigur = this.eigeneFiguren[i].getSpielfeld();

				if (feldDerFigur.isStartfeld()) {
					// Diese Figur steht noch im Startfeld also diese rausziehen
					return this.eigeneFiguren[i].getID();
				}
			}

		}
		return -1;
	}

	/**
	 * Gibt die Figur, die auf dem angebenen Feld steht zurueck, falls vorhanden
	 * 
	 * @param figuren
	 *            Alle Gegnerfiguren (eigene Figuren werden auch geprueft
	 * @param feldID
	 *            Die ID des Feldes, welches zu pruefen ist
	 * @return Die Spielfigur, die auf dem Feld steht oder null falls keine
	 *         darauf steht
	 */
	protected final Spielfigur getFigurAufFeld(Spielfigur[][] figuren,
			String feldID) {
		for (int i = 0; i < figuren.length; ++i) {
			for (int j = 0; j < figuren[i].length; ++j) {
				if (figuren[i][j].getSpielfeld().getID().equals(feldID)) {
					return figuren[i][j];
				}
			}
		}

		for (int i = 0; i < 4; ++i) {
			if (this.eigeneFiguren[i].getSpielfeld().getID().equals(feldID)) {
				return this.eigeneFiguren[i];
			}
		}

		return null;
	}

	/**
	 * Berechnet die Anzahl an Feldern, die zwischen zwei Figuren liegen. Dabei
	 * werden Figuren in End- und Startfeldern ignoriert
	 * 
	 * @param figur
	 *            Erste Figur
	 * @param andereFigur
	 *            Zweite Figur
	 * @return Die Anzahl an Feldern zwischen den Figuren oder -1 falls
	 *         unerreichbar
	 */
	protected int berechneDistanzZwischenFiguren(Spielfigur figur,
			Spielfigur andereFigur) {

		Spielfeld feld = figur.getSpielfeld();
		Spielfeld anderesFeld = andereFigur.getSpielfeld();

		if (feld.isStartfeld() || feld.isEndfeld() || anderesFeld.isStartfeld()
				|| anderesFeld.isEndfeld()) {
			// Start und Endfelder werden ignoriert
			return -1;
		}

		int feldVorEndfeld = Integer.parseInt(figur.getSpieler()
				.getFeldvorEndfeld());
		int anderesFeldInt = Integer.parseInt(anderesFeld.getID());
		int eigenesFeldInt = Integer.parseInt(feld.getID());

		if (anderesFeldInt > feldVorEndfeld) {
			if (anderesFeldInt < eigenesFeldInt) {
				return -1;
			}
		}
		
		return anderesFeldInt - eigenesFeldInt;
	}

	/**
	 * Berechnet die Distanz einer Figur zu einem Spielfeld Kann nur Distanzen
	 * zu normalen Feldern von Figuren, die aktiv am Spiel teilnehemen berechnen
	 * 
	 * @param figur
	 *            Die Figur von der aus gemessen werden soll (Darf nicht in
	 *            Start- oder Endfeld stehen)
	 * @param feldID
	 *            Die ID des Feldes bis zu dem gemssen werden soll (darf kein
	 *            Start- oder Endfeld sein)
	 * @return Die Distanz oder -1 falls Distanz negativ oder ungueltige
	 *         paramter
	 */
	protected int berechneDistanzZuFeld(Spielfigur figur, String feldID) {
		int feldInt = 0;
		try {
			feldInt = Integer.parseInt(feldID);

			int eigenesFeldInt = Integer.parseInt(figur.getSpielfeld().getID());

			int distanz = feldInt - eigenesFeldInt;

			if (feldInt < eigenesFeldInt) {
				distanz = 40 - eigenesFeldInt + feldInt;
			}

			distanz = Math.max(-1, distanz);
			return distanz;
		} catch (Exception e) {
			return -1;
		}
	}
	
	public KiTypEnum getKiTyp(){
		return null;
	}

}