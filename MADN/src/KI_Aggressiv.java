/**
 * Die aggressive KI, die immer zuerst eine Gegnerfigur schlagen will
 */
public class KI_Aggressiv extends KI {
	/**
	 * Erstellt eine neue aggressive KI
	 * @param spieler Der Spieler, dem diese KI gehoert
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
	protected final int normalerZug(Spielfigur[][] gegnerFiguren, int gewuerfelteZahl) {
		/*
		 * Da aggressiv:
		 * Schaue, ob eine eigene Figur noch nicht in Reichweite einer Gegnerfigur ist, 
		 * d.h. Distanz > 6
		 * Sonst Schaue, welche Gegnerfigur am naechsten an ihrem Endfeld dran ist,
		 * versuche diese einzuholen
		 */
		return -1;
	}

	
	/*public KI_Aggressiv(){
		int counter=1;
		Spieler k = new Spieler(spiel, name, farbe, KiTypEnum);
		
		String name="CPU" + counter;
		counter++;
		
	}
	
	public ZugErgebnis schlagen(Spielfeld feldDerFigur, Spielfeld zielFeld) {
		
		boolean schlagen=false;
		boolean raus=false;
		boolean beenden=false;
		boolean ziehen=false;

		Spielfigur gegnerFigur = zielFeld.getFigurAufFeld();
		Spielfigur eigeneFigur = feldDerFigur.getFigurAufFeld();

		if (gegnerFigur != null
				&& (eigeneFigur.getFarbe() != gegnerFigur.getFarbe())) {
			KiSchlagen();
			schlagen=true;
			
		}else if(schlagen==false){
			KiRaus();
			raus=true;
		}else if(raus==false && schlagen==false){
			KiBeenden();
			beenden=true;
		}else if(beenden==false && raus==false && schlagen==false){
			KiZiehen();
			ziehen=false;
			
		}
	}

	@Override
	public void KiSchlagen() {

	}

	@Override
	public void KiRaus() {

	}

	@Override
	public void KiBeenden() {

	}

	@Override
	public void KiZiehen() {

	}*/

}
