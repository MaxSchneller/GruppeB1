/**
 * Die Klasse Spieler
 * @author Gruppe B1
 */
public class Spieler {
	private String name;
	private FarbEnum farbe;
	private Wuerfel w;
	private Spiel spiel;
	private Spielfigur[] figuren=new Spielfigur[4];
	
	/**
	 * Der Konstruktor der Klasse Spieler
	 * @param name Der Name der Spielers
	 * @param farbe Die Farbe der Spielers, aus dem Farbenum
	 * @param kiTyp Welche Art von KI diesen Spieler steuern soll (oder keine)
	 */
	public Spieler(Spiel spiel, String name, FarbEnum farbe, KiTypEnum kiTyp){
		this.setName(name);
		this.setFarbe(farbe);
		this.setSpiel(spiel);
		
		this.w = new Wuerfel();
		
		for (int i = 0; i < 4; ++i) {
			this.figuren[i] = new Spielfigur(this.getFarbe(),
					this.spiel.getSpielbrett().findeFeldDurchID("S" + (i+1) + " " + getFarbe()));
		}
	}
	
	private void setSpiel(Spiel spiel) {
		if(spiel==null){
			throw new RuntimeException("Spiel existiert nicht");
		}
		this.spiel=spiel;
		
	}

	public int wuerfeln(){
		return this.w.werfen();	
	}
	
	/**
	 * Der Konstruktor der Klasse Spieler. Erstellt einen Spieler ohne KI
	 * @param name Der Name der Spielers
	 * @param farbe Die Farbe der Spielers, aus dem Farbenum
	 */
	public Spieler(Spiel spiel, String name, FarbEnum farbe){
		this(spiel, name, farbe, KiTypEnum.KEINE_KI);
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

	public Spielfigur getFigurDurchID(int figurID) {
		if(figurID<=3 && figurID>=0){
			return figuren[figurID];
			}else{
				throw new RuntimeException("ID ungueltig!");
			}

	}
	
	public String getFeldvorEndfeld(){
		switch(farbe){
			case ROT: return "40";
			case BLAU: return "10";
			case GRUEN: return "20";
			case GELB: return "30";
		}
		return "0";
	}
	
	public String rausZiehFeld(){
		switch(farbe){
			case ROT: return "1";
			case BLAU: return "11";
			case GRUEN: return "21";
			case GELB: return "31";
		}
		return "0";
	}

}

 