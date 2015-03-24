import java.util.ArrayList;


/**
 * Die zentrale Managerklasse des Spiels
 */
public class Spiel implements iBediener {

	// Attribute
    
	/** Alle Spieler, die zur Zeit teilnehmen */
	private ArrayList<Spieler> spieler;
	/** Der Spieler, der gerade am Zug ist */
	private Spieler spielerAmZug;
	/** Die zuletzt geürfelte Zahl */
	private int zuleztGewuerfelt;
	/** Index des Spielers, der gerade am Zug ist */
	private int spielerAmZugIndex;
	/** Das Spielbrett */
	private Spielbrett spielbrett;
	
	// Konstruktor

	/**
	 * Erstellt ein neues Spiel
	 * @param spielerName Name des ersten Spielers
	 * @param spielerFarbe Farbe des ersten Spielers
	 * @param kiTyp KITyp des ersten Spielers
	 */
	public Spiel(String spielerName, FarbEnum spielerFarbe, KITyp kiTyp) {
		this.spieler = new ArrayList<Spieler>();
		
		Spieler ersterSpieler = new Spieler(spielerName, spielerFarbe, kiTyp);
		
		this.spielerAmZug = ersterSpieler;
		this.spielerAmZugIndex = 0;
		this.spieler.add(ersterSpieler);
	}

	// iBediener overrides
	
	/**
	 * Überschreiben der Methode spielerHinzufügen, welche einen Spieler hinzufügt, der einen Namen, Farbe und einen Würfel besitzt
	 */
	@Override
	public void spielerHinzufuegen(String name, FarbEnum farbe)
			throws SpielerFarbeVorhandenException {

		for (Spieler spieler : this.spieler) {

			if (spieler.getFarbe() == farbe)
				throw new SpielerFarbeVorhandenException(farbe);
		}

		spieler.add(new Spieler(name, farbe, new Wuerfel()));
	}

	/**
	 * Überschreiben der Methode spielerEntfernen, welche einen Spieler enfernt.
	 */
	@Override
	public void spielerEntfernen(FarbEnum farbeDesSpielers)
			throws SpielerNichtGefundenException {

		int indexZuEntfernen = -1;
		for (int i = 0; i < this.spieler.size(); i++) {
			if (this.spieler.get(i).getFarbe() == farbeDesSpielers) {
				indexZuEntfernen = i;
				break;
			}
		}

		if (indexZuEntfernen == -1)
			throw new SpielerNichtGefundenException(farbeDesSpielers);
		else
			this.spieler.remove(indexZuEntfernen);
	}

	/**
	 * Überschreiben der Methode sWuerfeln, welche den Spieler, der am Zug ist, würfeln lässt und 
	 * die gewürfelte Zahl zurück gibt
	 */
	@Override
	public int sWuerfeln() {

		if (spielerAmZug != null) {
			return this.zuleztGewuerfelt = spielerAmZug.wuerfeln();
		} else
			throw new NullPointerException("this.spielerAmZug");

	}
	
	/**
	 * Überschreiben der Methode ziehen, welche versucht den gewünschten Zug auszuführen, 
	 * sofern er mit den Spielregeln vereinbar ist
	 */
	@Override
	public ZugErgebnis ziehen(String figurID, String zielFeldID) {

		if (figurID == null || zielFeldID == null) {
			return new ZugErgebnis(false, false, null, false, null, null,
					"figurID oder zielFeldID sind null!");
		}

		ZugErgebnis ergebnis = this.spielbrett.zug(this.zuleztGewuerfelt,
				this.spielerAmZug.getFigurDurchID(figurID), zielFeldID);

		// Der Zug war gültig und es wurde keine 6 gewürfelt, also ist der nächste Spieler dran
		if (ergebnis.getGueltig() && ergebnis.getZugBeendet()) {
			
			// Der Index muss immer neu geprüft werden, da Spieler hinzugekommen sein könnten
			++this.spielerAmZugIndex;
			
			if (this.spielerAmZugIndex >= this.spieler.size())
				this.spielerAmZugIndex = 0;

			this.spielerAmZug = this.spieler.get(this.spielerAmZugIndex);

		}
		
		return ergebnis;
	}

	/**
	 * Überschrieben der Methode debugWuerfeln, welche einen Würfel Wurf simuliert, der die gewünschte Zahl
	 * ergibt
	 */
	@Override
	public int debugWuerfeln(int gewuenschteZahl) {
		return this.zuleztGewuerfelt = gewuenschteZahl;
	}

	/**
	 * Überschreiben der Methode debugSetzeFigur, welche die gewünschte Figur auf das gewünschte Feld setzt
	 * ohne dabei die Spielregeln zu beachten
	 */
	@Override
	public void debugSetzeFigur(FarbEnum spielerFarbe, String figurID,
			String zielFeldID) throws SpielerNichtGefundenException {
		
		Spieler spieler = FindeSpieler(spielerFarbe);
		
		if (spieler != null)
		{
			Spielfigur figur = spieler.getFigurDurchID(figurID);
			
			if (figur != null && this.spielbrett != null)
			{
				this.spielbrett.debugSetPos(figur, zielFeldID);
			}
		}
	}
	
	// Private Methoden

	/**
	 * Versucht den gesuchten Spieler in der Spielerliste zu finden
	 * @param spielerFarbe Die Farbe des gesuchten Spielers 
	 * @return Den gefundenen Spieler
	 * @throws SpielerNichtGefundenException Es gibt keinen Spieler mit der gesuchten Farbe
	 */
	private Spieler FindeSpieler(FarbEnum spielerFarbe) throws SpielerNichtGefundenException {
		
		for (Spieler spieler : this.spieler) {
			if (spieler.getFarbe() == spielerFarbe)
				return spieler;
		}
		
		throw new SpielerNichtGefundenException(spielerFarbe);
	}

}
