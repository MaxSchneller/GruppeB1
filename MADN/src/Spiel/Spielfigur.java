package Spiel;

import java.io.Serializable;

/**
 * Die Klasse Spielfigur
 * @author Gruppe B1
 *
 */
public class Spielfigur implements Serializable {
	
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
	
	/**
	 * Getter der ID
	 * @return ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * Setter der ID soll eine zweistellige Zahl sien
	 * @param id 
	 */
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
	
	/**
	 * Setter des Spielfelds darf nicht null sein
	 * @param feld
	 */
	public void setSpielfeld(Spielfeld feld){
		if(feld != null){
			this.feld = feld;
		}
	}

	/**
	 * Getter des Spielfelds
	 * @return feld
	 */
	public Spielfeld getSpielfeld() {
		
		return feld;
	}
	
	/**
	 * Getter des Spielers
	 * @return spieler
	 */
	public Spieler getSpieler(){
		return spieler;
	}
	
	/**
	 * Setter des Spielers, darf nicht null sein
	 * @param spieler
	 */
	private void setSpieler(Spieler spieler){
		if(spieler == null){
			throw new RuntimeException("Spieler darf nicht null sein");
		}
		this.spieler = spieler;
	}
}