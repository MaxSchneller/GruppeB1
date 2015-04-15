package Spiel;
import java.util.ArrayList;

import com.sun.security.auth.NTDomainPrincipal;

/**
 * Die aggressive KI, die immer zuerst eine Gegnerfigur schlagen will
 */
public class KI_Aggressiv extends KI {
	/**
	 * Erstellt eine neue aggressive KI
	 * 
	 * @param spieler
	 *            Der Spieler, dem diese KI gehoert
	 */
	public KI_Aggressiv(Spieler spieler) {
		this.setSpieler(spieler);
	}

	@Override
	public int zugBerechnen(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl) {

		int figurID = -1;
		// Zuerst schlagen
		figurID = this.figurSchlagen(gegnerFiguren, gewuerfelteZahl);

		if (figurID == -1) {
			// Dann rausziehen
			figurID = this.figurRausziehen(gegnerFiguren, gewuerfelteZahl);

			if (figurID == -1) {
				// Dann in Endfeld bringen
				figurID = this.spielBeenden(gegnerFiguren, gewuerfelteZahl);

				if (figurID == -1) {
					// Dann normal ziehen
					figurID = this.normalerZug(gegnerFiguren, gewuerfelteZahl);
				}
			}
		}

		return figurID;
	}

	@Override
	protected final int normalerZug(Spielfigur[][] gegnerFiguren,
			int gewuerfelteZahl) {
		/*
		 * Da aggressiv: Schaue, ob eine eigene Figur noch nicht in Reichweite
		 * einer Gegnerfigur ist, d.h. Distanz > 6 Sonst Schaue, welche
		 * Gegnerfigur am naechsten an ihrem Endfeld dran ist, versuche diese
		 * einzuholen
		 */
		int groessteDistanz = -1;
		ArrayList<Spielfigur> figurenMitGroessterDistanz = new ArrayList<Spielfigur>();

		for (int i = 0; i < 4; ++i) {
			// Alle eigenen Figuren durchgehen

			Spielfigur eigeneFigur = this.eigeneFiguren[i];

			for (int j = 0; j < gegnerFiguren.length; ++j) {
				for (int k = 0; k < gegnerFiguren[j].length; ++k) {
					// Distanz zu allen Gegnern berechnen
					int distanz = this.berechneDistanzZwischenFiguren(
							eigeneFigur, gegnerFiguren[j][k]);

					if (distanz > groessteDistanz) {
						// Eine noch groessere Distanz wurde gefunden
						groessteDistanz = distanz;
						// Die Figuren mit der alten Distanz sind obsolet
						figurenMitGroessterDistanz.clear();

						figurenMitGroessterDistanz.add(eigeneFigur);

					} else if (distanz == groessteDistanz) {
						// Die Distanz ist gleich gross, also fuer spaeter
						// merken
						figurenMitGroessterDistanz.add(eigeneFigur);

					}
				}
			}
		}

		// Hier wurden alle Distanzen zu allen Figuren ermittelt

		if (groessteDistanz == -1) {
			// Keine andere Figur kann mehr geschlagen werden
			// Also schauen dass das eigene Startspielfeld frei von eigenen
			// Figuren ist,
			// wenn ja die Figur, die am naechsten zum eigenen Endfeld ist
			// bewegen

			Spielfigur figurAufStartspielfeld = this.getFigurAufFeld(
					gegnerFiguren, this.spieler.getRausZiehFeld());

			if (figurAufStartspielfeld != null
					&& (figurAufStartspielfeld.getFarbe() == this.spieler
							.getFarbe())) {
				// Feld falls moeglich freimachen

				Spielfigur figurAufZielfeld = null;
				int zielFeldInt = -1;

				do {
					int figurAufStartInt = Integer
							.parseInt(figurAufStartspielfeld.getSpielfeld()
									.getID());
					zielFeldInt = figurAufStartInt + gewuerfelteZahl;
					if (zielFeldInt > 40) {
						zielFeldInt = zielFeldInt - 40;
					}

					figurAufZielfeld = this.getFigurAufFeld(gegnerFiguren,
							String.format("%d", zielFeldInt));

					if (figurAufZielfeld != null
							&& (figurAufStartspielfeld.getFarbe() == figurAufZielfeld
									.getFarbe())) {
						figurAufStartspielfeld = figurAufZielfeld;
					}
				} while (figurAufZielfeld != null
						&& (figurAufStartspielfeld.getFarbe() == figurAufZielfeld
								.getFarbe()));

				// Hier ist ein Zielfeld frei, und figurAufStartFeld ist die zu
				// bewegende Figur
				return figurAufStartspielfeld.getID();
			}

		} else {
			// Schauen, welche Gegnerfigur am naechsten an ihrem Endfeld ist
			Spielfigur figurMeisterSchaden = null;
			int distanzGegnerEndfeld = 0;

			for (Spielfigur fig : figurenMitGroessterDistanz) {
				int figFeldInt = Integer.parseInt(fig.getSpielfeld().getID());
				int zielFeldInt = figFeldInt + gewuerfelteZahl;

				if (zielFeldInt > 40) {
					zielFeldInt = zielFeldInt - 40;
				}

				Spielfigur figurAufZiel = this.getFigurAufFeld(gegnerFiguren,
						String.format("%d", zielFeldInt));

				if (figurAufZiel != null) {
					String endFeldID = figurAufZiel.getSpieler()
							.getFeldvorEndfeld();
					int distanz = this.berechneDistanzZuFeld(fig, endFeldID);

					if (distanz < distanzGegnerEndfeld) {
						distanzGegnerEndfeld = distanz;
						figurMeisterSchaden = fig;
					}
				}
			}

			if (figurMeisterSchaden != null) {
				return figurMeisterSchaden.getID();
			}
		}

		// Hier sind Startspielfelder frei und keine Figuren zu schlagen, also
		// die, die
		// am Endfeld dran ist, nehmen

		// Figur zum Endfeld bringen
		int eigeneDistanzZuEndfeld = 40;
		Spielfigur figur = null;
		String endFeldID = this.spieler.getFeldvorEndfeld();

		for (Spielfigur spielfigur : eigeneFiguren) {

			int distanz = this.berechneDistanzZuFeld(spielfigur, endFeldID);
			
			if (distanz == -1) {
				continue;
			}

			if (distanz <= eigeneDistanzZuEndfeld) {
				
				boolean kannZiehen = true;
				
				int feldInt = Integer.parseInt(spielfigur.getSpielfeld().getID());
				int zielFeldInt = feldInt + distanz;
				if (zielFeldInt > 40) {
					zielFeldInt -= 40;
				}
				
				String zielFeldID = String.format("%d", zielFeldInt);
				
				for (Spielfigur spielfigur2 : this.eigeneFiguren) {
					if (spielfigur2.getSpielfeld().getID().equals(zielFeldID)) {
						// Kann keine eigenen Figuren Schlagen
						kannZiehen = false;
						break;
					}
				}
				
				if (kannZiehen) {
					eigeneDistanzZuEndfeld = distanz;
					figur = spielfigur;
				}
			}
		}

		return figur.getID();
	}

}
