/**
 * Die Klasse Spieler
 * @author Gruppe B1
 */
public class Spieler {
	private String name;
	private FarbEnum farbe;
	private Wuerfel w;
	
	/**
	 * Der Konstruktor der Klasse Spieler
	 * @param name Der Name der Spielers
	 * @param farbe Die Farbe der Spielers, aus dem Farbenum
	 * @param w Der eigene Wuerfel des Spielers
	 */
	public Spieler(String name, FarbEnum farbe, Wuerfel w){
		setName(name);
		this.setFarbe(farbe);
		getWuerfel();
		
	}
	
	/**
	 * Getter-Methode der Wuerfels
	 * @return Gibt die gewuerfelte Zahl zurück
	 */
	public Wuerfel getWuerfel(){
		return w;
	}
	
	/**
	 * Getter-Methode des Namens
	 * @return Gibt den Namen zurück
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter-Methode der Farbe
	 * @return Gibt die Farbe zurück
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}
	
	/**
	 * Setter-Methode des Namens
	 * @param name Der Name des Spielers
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Setter-Methode der Farbe
	 * @param farbe Die Farbe der Spielers
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

}

 