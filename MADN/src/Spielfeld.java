/**
 * Ein Feld auf dem Brett, auf dem die Figuren stehen können
 */
public class Spielfeld {
	
	
	/** Bezeichner dieses Feldes */
	protected String ID;
	/** Die Spielfigur, die sich gerade auf dem Feld befindet, oder null wenn keine darauf ist */
	protected Spielfigur figurAufFeld;
	
	/**
	 * Erstellt ein neues Spielfeld
	 * @param ID Der Bezeichner des Feldes (1-40)
	 * @param figurAufFeld Die Figur die auf diesem Feld steht oder null falls keine darauf
	 */
	public Spielfeld(String ID, Spielfigur figurAufFeld) {
		
		this.ID = ID;
		this.figurAufFeld = figurAufFeld;
	}
	
}
