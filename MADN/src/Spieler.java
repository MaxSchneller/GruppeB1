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
	 * @param kiTyp Welche Art von KI diesen Spieler steuern soll (oder keine)
	 */
	public Spieler(String name, FarbEnum farbe, KITyp kiTyp){
		this.setName(name);
		this.setFarbe(farbe);
		
		this.w = new Wuerfel();
		
		for (int i = 0; i < 4; ++i) {
			this.figuren[i] = new Spielfigur(this.getFarbe(),
					this.getSpiel().getSpielbrett().findeDurchID("S" + (i+1) + " " + getFarbe()));
		}
	}
	
	/**
	 * Der Konstruktor der Klasse Spieler. Erstellt einen Spieler ohne KI
	 * @param name Der Name der Spielers
	 * @param farbe Die Farbe der Spielers, aus dem Farbenum
	 */
	public Spieler(String name, FarbEnum farbe){
		this(name, farbe, KITyp.KEINE_KI);
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

 