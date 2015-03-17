
public class SpielerFarbeVorhandenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SpielerFarbeVorhandenException(FarbEnum farbe){
		super("Die Farbe \"" + farbe.toString() +"\" ist bereits vorhanden.");
	}

}
