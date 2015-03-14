/**
 * Ein Startfeld mit einer bestimmten Farbe
 */
public class FarbigesSpielfeld extends Spielfeld {

	/** Die Farbe dieses Feldes */
	private FarbEnum farbe;
	
	
	/**
	 * Erstellt ein neues Startfeld
	 * @param ID Der Bezeichner des Feldes (S1 - S4, E1 - E4)
	 * @param figurAufFeld Die Figur, die auf dem Feld steht oder null
	 * @param farbe Die Farbe des Spielfeldes
	 */
	public FarbigesSpielfeld(String ID, Spielfigur figurAufFeld, FarbEnum farbe) {
		
		super(ID, figurAufFeld);
		
		this.farbe = farbe;
	}
}
