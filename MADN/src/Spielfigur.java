/**
 * Die Klasse Spielfigur
 * @author Gruppe B1
 *
 */
public class Spielfigur {
	
	private FarbEnum farbe;
	private Position position;
	
	/**
	 * Der Konstruktor der Klasse Spielfigur
	 * @param farbe Jede Spielfigur besitzt eine Farbe
	 * @param position Jede Spielfigur besitzt eine Position auf dem Spielbrett
	 */
	public Spielfigur(FarbEnum farbe, Position position){
		setPosition(position);
		setFarbe(farbe);
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
	
	/**
	 * Setter-Methode der Position
	 * @param position Jeder Spielfigur besitzt eine Position auf dem Spielbrett
	 */
	public void setPosition(Position position){
        this.position = new Position(position.getX(), position.getY());
	}
	
	/**
	 * Getter-Methode der Position
	 * @return Gibt Position zurück
	 */
	public Position getPosition(){
        return new Position(this.position.getX(), this.position.getY());
}
}
