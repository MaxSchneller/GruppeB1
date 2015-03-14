/**
 * Eine einzelne Spielfigur eines Spielers
 */
public class Spielfigur {
	
	/** Die Farbe dieser Spielfigur */
	private FarbEnum farbe;
	/** Das Spielfeld, auf dem diese Figur sich gerade befindet, darf nicht null sein */
	private Spielfeld spielfeld;
	
	/**
	 * Erstellt eine nue Spielfigur
	 * @param farbe Die Frabe dieser Spielfigur
	 * @param aktuellePosition Die Position auf dem Spielbrett (darf nicht null sein)
	 */
	public Spielfigur(FarbEnum farbe, Spielfeld aktuellePosition){
		
		this.farbe = farbe;
		
		this.spielfeld = spielfeld;
	}
	
}
