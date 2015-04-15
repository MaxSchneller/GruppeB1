package Spiel;
/**
 * Die Klasse Spielfigur
 * @author Gruppe B1
 *
 */
public class Spielfigur {
	
	private FarbEnum farbe;
	private Spielfeld feld;
	private int id;
	private Spieler spieler;
	
	
	/**
	 * Der Konstruktor der Klasse Spielfigur
	 * @param farbe Jede Spielfigur besitzt eine Farbe
	 * @param position Jede Spielfigur besitzt eine Position auf dem Spielbrett
	 */
	public Spielfigur(FarbEnum farbe, Spielfeld feld, int id, Spieler spieler){
		setFarbe(farbe);
		setSpielfeld(feld);
		setID(id);
		setSpieler(spieler);
		
		feld.setFigurAufFeld(this);
	}
	
	public int getID() {
		return id;
	}

	private void setID(int id) {
		if(id < 0 || id > 3){
			throw new RuntimeException("Ungueltige ID");
		}
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getFarbe() + " " + this.getID();
	}

	/**
	 * Setter-Methode der Farbe
	 * @param farbe Jede Spielfigur besitzt eine Farbe
	 */
	private void setFarbe(FarbEnum farbe){
		this.farbe = farbe;
	}
	
	/**
	 * Getter-Methode der Farbe
	 * @return Gibt Farbe zurück
	 */
	public FarbEnum getFarbe(){
         return farbe;
	}
	
	public void setSpielfeld(Spielfeld feld){
		if(feld != null){
			this.feld = feld;
		}
	}

	public Spielfeld getSpielfeld() {
		
		return feld;
	}
	
	public Spieler getSpieler(){
		return spieler;
	}
	
	private void setSpieler(Spieler spieler){
		if(spieler == null){
			throw new RuntimeException("Spieler darf nicht null sein");
		}
		this.spieler = spieler;
	}
}