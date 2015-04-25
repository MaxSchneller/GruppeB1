package Kuenstliche_Intelligenz;

import java.io.Serializable;

import Spiel.Spieler;
import Spiel.Spielfeld;
import Spiel.Spielfigur;

/**
 * Die defensive KI, die versucht das Spiel moeglichst schnell zu beenden
 */
public class KI_Defensiv extends KI implements Serializable{
	
	/**
	 * Erstellt eine neue defensive KI
	 * @param spieler Der Spieler, dem diese KI gehoert
	 */
	public KI_Defensiv(Spieler spieler) {
		this.setSpieler(spieler);
	}

	@Override
	public int zugBerechnen(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl) {
		// Zuerst beenden 
		int figID = this.spielBeenden(gegnerFiguren, gewuerfelteZahl);
		
		if (figID == -1) {
			// Dann rausziehen
			figID = this.figurRausziehen(gegnerFiguren, gewuerfelteZahl);
			
			if (figID == -1) {
				// Dann schlagen 
				this.figurSchlagen(gegnerFiguren, gewuerfelteZahl);
				
				if (figID == -1) {
					// Startspielfeld raeumen
					figID = this.raeumeStartSpielfeld(gegnerFiguren,
							gewuerfelteZahl);
					if (figID == -1) {
						// Dann normal ziehen
						figID = this.normalerZug(gegnerFiguren,
								gewuerfelteZahl);
					}
				}
			}
		}
		
		return figID;
	}

	@Override
	protected final int normalerZug(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl) {
		
		/*
		 * Vorgehensweise:
		 * Schaue welche meiner Figuren am naechsten am Endfeld ist und bewege diese weiter darauf zu
		 * Dabei immer pruefen, ob der Zug moeglich ist (Figur im Weg etc)
		 */
		
		int kleinsteDistanz = 40;
		Spielfigur zuZiehendeFigur = null;
		String endFeldID = this.spieler.getFeldvorEndfeld();
		for (Spielfigur figur : this.eigeneFiguren) {
			Spielfeld feldDerFigur = figur.getSpielfeld();
			
			if (feldDerFigur.isEndfeld() || feldDerFigur.isStartfeld()) {
				continue;
			}
			
			int distanz = this.berechneDistanzZuFeld(figur, endFeldID);
			
			if (distanz < kleinsteDistanz) {
				
				int feldInt = Integer.parseInt(feldDerFigur.getID());
				int zielFeldInt = feldInt + gewuerfelteZahl;
				
				if (zielFeldInt > 40) {
					zielFeldInt -= 40;
				}
				
				String zielFeldID = String.format("%d", zielFeldInt);
				
				if (this.kannAufFeldZiehen(figur, gegnerFiguren, zielFeldID)) {
					kleinsteDistanz = distanz;
					zuZiehendeFigur = figur;
				}
				
			}
		}
		return zuZiehendeFigur == null ? -1 : zuZiehendeFigur.getID();
	}
	
	@Override
	public KiTypEnum getKiTyp(){
		return KiTypEnum.DEFENSIV;
	}
	
}
