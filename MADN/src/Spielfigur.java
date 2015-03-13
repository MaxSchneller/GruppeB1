
public class Spielfigur {
	
	private FarbEnum farbe;
	private Spielfeld spielfeld;
	
	public Spielfigur(FarbEnum farbe, Spielfeld aktuellePosition){
		
		this.farbe = farbe;
		
//		if (spielfeld == null)
//			throw new UngueltigesSpielfeldException();
		
		
		this.spielfeld = spielfeld;
		
	}
	
}
