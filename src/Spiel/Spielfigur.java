package Spiel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Die Klasse Spielfigur
 * @author Gruppe B1
 *
 */
@XmlType(propOrder={"id", "spieler", "farbe", "eindeutigeID", "spielfeld"})
public class Spielfigur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FarbEnum farbe;
	@XmlIDREF
	@XmlElement(name="spielfeld")
	private Spielfeld spielfeld;
	private int id;
	@XmlIDREF
	@XmlElement(name="spieler")
	private Spieler spieler;
	
	@XmlID
	private String eindeutigeID;
	
	
	/**
	 * Der Konstruktor der Klasse Spielfigur
	 * @param farbe Jede Spielfigur besitzt eine Farbe
	 * @param feld Das Feld auf dem die Figur steht
	 * @param id Die ID diese Figur
	 * @param spieler Der Spieler dem diese Figur gehoert
	 */
	public Spielfigur(FarbEnum farbe, Spielfeld feld, int id, Spieler spieler){
		setFarbe(farbe);
		setSpielfeld(feld);
		setId(id);
		setSpieler(spieler);
		
		feld.setFigurAufFeld(this);
		
		this.eindeutigeID = "" + farbe + " " + id;
	}
	
	public Spielfigur() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter der ID
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter der ID soll eine zweistellige Zahl sien
	 * @param id 
	 */
	public void setId(int id) {
		if(id < 0 || id > 3){
			throw new RuntimeException("Ungueltige ID");
		}
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getFarbe() + " " + this.getId();
	}

	/**
	 * Setter-Methode der Farbe
	 * @param farbe Jede Spielfigur besitzt eine Farbe
	 */
	public void setFarbe(FarbEnum farbe){
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
	 * @param feld Das Feld auf dem die Figur nun steht
	 */
	public void setSpielfeld(Spielfeld feld){
		if(feld != null){
			this.spielfeld = feld;
		}
	}

	/**
	 * Getter des Spielfelds
	 * @return feld
	 */
	@XmlTransient
	public Spielfeld getSpielfeld() {
		
		return spielfeld;
	}
	
	/**
	 * Getter des Spielers
	 * @return spieler
	 */
	@XmlTransient
	public Spieler getSpieler(){
		return spieler;
	}
	
	/**
	 * Setter des Spielers, darf nicht null sein
	 * @param spieler
	 */
	public void setSpieler(Spieler spieler){
		if(spieler == null){
			throw new RuntimeException("Spieler darf nicht null sein");
		}
		this.spieler = spieler;
	}
}