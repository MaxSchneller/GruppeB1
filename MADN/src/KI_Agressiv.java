public class KI_Agressiv extends KI {

	
	public KI_Agressiv(){
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

	}

}
