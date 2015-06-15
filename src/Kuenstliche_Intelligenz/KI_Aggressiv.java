package Kuenstliche_Intelligenz;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import Spiel.Spieler;
import Spiel.Spielfigur;

import com.sun.security.auth.NTDomainPrincipal;

/**
 * Die aggressive KI, die immer zuerst eine Gegnerfigur schlagen will
 */
@XmlRootElement
public class KI_Aggressiv extends KI implements Serializable {
	/**
	 * Erstellt eine neue aggressive KI
	 * 
	 * @param spieler
	 *            Der Spieler, dem diese KI gehoert
	 */
	public KI_Aggressiv(Spieler spieler) {
		this.setSpieler(spieler);
	}
	
	public KI_Aggressiv() {
		// TODO Auto-generated constructor stub
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
					// Startspielfeld raeumen
					figurID = this.raeumeStartSpielfeld(gegnerFiguren,
							gewuerfelteZahl);
					if (figurID == -1) {
						// Dann normal ziehen
						figurID = this.normalerZug(gegnerFiguren,
								gewuerfelteZahl);
					}
				}
			}
		}

		return figurID;
	}

	@Override
	protected final int normalerZug(Spielfigur[][] gegnerFiguren,
			int gewuerfelteZahl) {

		for (int i = 0; i < this.eigeneFiguren.length; ++i) {

			Spielfigur eigeneSpielfigur = this.eigeneFiguren[i];
			String feldID = eigeneSpielfigur.getSpielfeld().getID();
			int feldInt = 0;

			try {
				feldInt = Integer.parseInt(feldID);
			} catch (NumberFormatException e) {
				continue;
			}

			int zielFeldInt = feldInt + gewuerfelteZahl;
			
			int feldVorEndfeldInt = Integer.parseInt(this.spieler.getFeldvorEndfeld());
			
			// Wenn die Figur hinter dem Endfeld steht und das Zielfeld vor dem Endfeld liegt nichts tun
			if (zielFeldInt > feldVorEndfeldInt && feldInt <= feldVorEndfeldInt) {
				continue;
			}

			if (zielFeldInt > 40) {
				zielFeldInt -= 40;
			}

			String zielFeldID = String.format("%d", zielFeldInt);

			Spielfigur figurAufZielfeld = this.getFigurAufFeld(gegnerFiguren,
					zielFeldID);

			if (figurAufZielfeld != null
					&& (eigeneSpielfigur.getFarbe() == figurAufZielfeld
							.getFarbe())) {
				continue;
			}

			return eigeneSpielfigur.getId();
		}

		return -1;
	}

	@Override
	public KiTypEnum getKiTyp() {
		return KiTypEnum.AGGRESIV;
	}
}
