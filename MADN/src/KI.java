
public abstract class KI {
	
	boolean zugBeendet;
	
	protected Spielfiguren[][] figuren;
	private int KiSchlagen(Spielfigur[][] figuren, int gewuerfelteZahl){
		this.figuren = figuren;
		
		figuren[0][0].getSpielfeld()
	}
		
	
	
	
	private Spielfigur KiRaus(int wuerfel){
		
	}
	
	private ZugErgebnis KiZiehen(){
		
	}
	
	private ZugErgebnis KiBeenden(){
		
	}
	
	public abstract Spielfigur berechneZug();
}