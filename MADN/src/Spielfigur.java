/**
 * Die Klasse Spielfigur
 * @author Gruppe B1
 *
 */
public class Spielfigur {
	
	private FarbEnum farbe;
	private Position position;
	private Spielfeld feld;
	
	/**
	 * Der Konstruktor der Klasse Spielfigur
	 * @param farbe Jede Spielfigur besitzt eine Farbe
	 * @param position Jede Spielfigur besitzt eine Position auf dem Spielbrett
	 */
	public Spielfigur(FarbEnum farbe, Spielfeld feld){
		setFarbe(farbe);
		setSpielfeld(feld);
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
	
	private void setSpielfeld(Spielfeld feld){
		if(feld != null){
			this.feld = feld;
		}
	}
}
