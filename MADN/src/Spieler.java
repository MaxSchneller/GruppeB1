/**
 * Ein Spielteilnehemer, der entwerder von einem Menschen oder einer KI 
 * gesteuert wird
 */
public class Spieler {
	
	/** Der Wuerfel des Spielers, jeder besitzt seinen eigenen */
	private Würfel wuerfel;
	
	/** Name des Spielers */
	private String name;
	
	/** Die vier Spielfiguren dieses Spielers */
	private Spielfigur[] spielfiguren;
	
	/** Die KI des Spielers, falls dieser von ihr Gesteuert werden soll (sonst null) */
	private KI spielerKI;
	
	/**
	 * Instanziert die Klasse Spieler mit den angegeben parametern. 
	 * @param name Der Name des Spielers, darf nicht null sein
	 * @param farbe Die gewünschte Farbe des Spielers
	 * @param istKI Soll der Spieler von einer KI gesteuert werden
	 */
	public Spieler(String name, FarbEnum farbe, boolean istKI) {
		
		this.wuerfel = new Würfel();
		this.name = name;
		this.spielfiguren = new Spielfigur[4];
		
		// Vorläufige Implementierung
		// wird vielleicht problematisch bei Serialisierung
		for (int i = 0; i < 4; ++i) {
			
			this.spielfiguren[i] = new Spielfigur(farbe);
			
		}
	}
	
}
