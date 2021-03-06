package Spiel;

import java.io.Serializable;

import Kuenstliche_Intelligenz.KI;
import Kuenstliche_Intelligenz.KI_Aggressiv;
import Kuenstliche_Intelligenz.KI_Defensiv;
import Kuenstliche_Intelligenz.KiTypEnum;

/**
 * Die Klasse Spieler
 * @author Gruppe B1
 */
public class Spieler implements Serializable {
	/** Der Name des Spielers */
	private String name;
	/** Die Farbe des Spielers */
	private FarbEnum farbe;
	/** Jeder Spieler hat seinen eigenen Wuerfel */
	private Wuerfel wuerfel;
	/** Das Spiel an dem dieser Spieler teilnimmt */
	private Spiel spiel;
	/** Die Spielfiguren dieses Spielers */
	private Spielfigur[] figuren=new Spielfigur[4];
	/** Die KI dieses Spielers, falls eine vorhanden */
	private KI spielerKI;
	
	/**
	 * Der Konstruktor der Klasse Spieler
	 * @param name Der Name der Spielers
	 * @param farbe Die Farbe der Spielers, aus dem Farbenum
	 * @param kiTyp Welche Art von KI diesen Spieler steuern soll (oder keine)
	 * @param spiel Das Objekt Spiel
	 */
	public Spieler(Spiel spiel, String name, FarbEnum farbe, KiTypEnum kiTyp){
		this.setSpiel(spiel);
		this.setName(name);
		this.setFarbe(farbe);
		this.wuerfel = new Wuerfel();
		
		for (int i = 0; i < 4; ++i) {
			String feldID = "S" + (i+1) + " " + this.getFarbe();
			Spielfeld feld = this.spiel.getSpielbrett().findeFeldDurchID(feldID);
			
			if (feld == null) {
				throw new NullPointerException();
			}
				
			this.figuren[i] = new Spielfigur(this.getFarbe(), feld, i, this);
		}
		
		if (kiTyp != null) {
			switch (kiTyp) {
			case AGGRESIV: {
				this.spielerKI = new KI_Aggressiv(this);
			}break;
			case DEFENSIV: {
				this.spielerKI = new KI_Defensiv(this);
			}break;

			default:
				break;
			}
		}
	}
	
	/**
	 * Der Konstruktor der Klasse Spieler. Erstellt einen Spieler ohne KI
	 * @param name Der Name der Spielers
	 * @param farbe Die Farbe der Spielers, aus dem Farbenum
	 * @param spiel das Objekt Spiel
	 */
	public Spieler(Spiel spiel, String name, FarbEnum farbe){
		this(spiel, name, farbe, null);
	}
	
	/**
	 * Setter fuer Spiel
	 * @param spiel
	 */
	private void setSpiel(Spiel spiel) {
		if (spiel != null) {
			this.spiel = spiel;
		} else {
			throw new NullPointerException("Spiel darf nicht null sein");
		}
	}

	/**
	 * Setter fuer Farbe
	 * @param farbe
	 */
	private void setFarbe(FarbEnum farbe) {
		if (farbe != null) {
			this.farbe = farbe;
		} else {
			throw new NullPointerException("farbe ist null");
		}
		
	}

	/**
	 * Setter fuer Name
	 * @param name
	 */
	private void setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("name ist null oder leer");
		}
	}

	/**
	 * Getter-Methode des Namens
	 * @return Gibt den Namen zurueck
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter-Methode der Farbe
	 * @return Gibt die Farbe zurueck
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}
	
	/**
	 * Laesst den Spieler seinen Wuerfel werfen und gibt das Ergbnis zurueck
	 * @return Die gewuerfelte Zahl
	 */
	public int wuerfeln(){
		return this.wuerfel.werfen();	
	}

	/** 
	 * Gibt die gewuenschte Spielfigur dieses Spielers zurueck
	 * @param figurID Die Nummer der gesuchten Figur (0-3)
	 * @return Die Spielfigur, falls diese gefunden wurde
	 */
	public Spielfigur getFigurDurchID(int figurID) {
		if(figurID<=3 && figurID>=0){
			return figuren[figurID];
			}else{
				throw new IndexOutOfBoundsException("ID ungueltig! (" + figurID + ")");
			}

	}
	
	/**
	 * Gibt die ID des Feldes zurueck, das sich unmittelbar vor den Endfeldern dieses Spielers befindet
	 * @return Das Feld vor den Endfeldern oder keins.
	 */
	public String getFeldvorEndfeld(){
		switch(farbe){
			case ROT: return "40";
			case BLAU: return "10";
			case GRUEN: return "20";
			case GELB: return "30";
		}
		return "0";
	}
	
	/**
	 * Gibt die ID des Feldes zurueck, auf welches dieser Spieler seine Spielfiguren
	 * aus den Startfeldern stellt. 
	 * @return Das Feld der jeweiligen Farbe, auf das die Figuren vom Startfeld springen. 
	 */
	public String getRausZiehFeld(){
		switch(farbe){
			case ROT: return "1";
			case BLAU: return "11";
			case GRUEN: return "21";
			case GELB: return "31";
		}
		return "0";
	}
	
	/**
	 * Prueft ob mindestens eine Spielfigur nicht mehr auf den Startfeldern sitzt
	 * @return true oder false
	 */
	public boolean hatFigurAufSpielfeld() {
		
		for (int i = 0; i < figuren.length; i++) {
			Spielfigur spielfigur = figuren[i];
			if (spielfigur.getSpielfeld().isStartfeld() == false) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Gibt an, ob dieser Spieler eine KI besitzt
	 * @return is der Spieler eine KI true oder false
	 */
	public boolean isSpielerKI() {
		return this.spielerKI == null ? false : true;
	}

	/**
	 * Laesst die KI dieses Spielers den Zug anhand der Parameter berechnen
	 * @param gegnerFiguren Die Figuren aller Gegner im Spiel
	 * @param zuleztGewuerfelt Die Zahl, die fuer diesen Zug gewuerfelt wurde
	 * @return Die ID der Figur dieses Spielers die bewegt werden soll
	 */
	public int kiBerechnen(Spielfigur[][] gegnerFiguren, int zuleztGewuerfelt) {
		return this.spielerKI.zugBerechnen(gegnerFiguren, zuleztGewuerfelt);
	}
	
	/**
	 * Welcher Typ von KI oder keiner?
	 * @return Wenn null, dann dann ist der Spieler keine KI. Wenn nicht dann welche KI? Gib diese zurueck
	 */
	public KiTypEnum getKiTyp(){
		if(this.spielerKI == null){
			return null;
		} else {
			return this.spielerKI.getKiTyp();
		}
	}
}

 