public class KiAggresiv extends Ki {

	public KiAggresiv(){
		int counter=1;
		
		
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

	@Overwrite
	public void KiSchlagen() {

	}

	@Overwrite
	public void KiRaus() {

	}

	@Overwrite
	public void KiBeenden() {

	}

	@Overwrite
	public void KiZiehen() {

	}

}
