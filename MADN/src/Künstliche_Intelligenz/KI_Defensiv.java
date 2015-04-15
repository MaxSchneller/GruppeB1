package Künstliche_Intelligenz;

import Spiel.Spieler;
import Spiel.Spielfigur;

/**
 * Die defensive KI, die versucht das Spiel moeglichst schnell zu beenden
 */
public class KI_Defensiv extends KI {
	
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
					// Dann normal ziehen
					figID = this.normalerZug(gegnerFiguren, gewuerfelteZahl);
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
		return -1;
	}

	
}
