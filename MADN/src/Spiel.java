import java.util.ArrayList;

/**
 * Die zentrale Managerklasse des Spiels
 */
public class Spiel implements iBediener {

	// Attribute

	/** Alle Spieler, die zur Zeit teilnehmen */
	private ArrayList<Spieler> teilnehmendeSpieler;
	/** Der Spieler, der gerade am Zug ist */
	private Spieler spielerAmZug;
	/** Die zuletzt geürfelte Zahl */
	private int zuleztGewuerfelt;
	/** Index des Spielers, der gerade am Zug ist */
	private int spielerAmZugIndex;
	/** Das Spielbrett */
	private Spielbrett spielbrett;

	// Getter und Setter

	/**
	 * Gibt das Spielbrett dieses Spiels zurück
	 * 
	 * @return
	 */
	public Spielbrett getSpielbrett() {
		return spielbrett;
	}

	/**
	 * Setzt die ArrayList der Spieler (nicht die Spieler selbst, darf nicht
	 * null sein)
	 * 
	 * @param spieler
	 */
	private void setSpieler(ArrayList<Spieler> spieler) {
		if (spieler == null) {
			throw new NullPointerException("spieler");
		}
		this.teilnehmendeSpieler = spieler;
	}

	/**
	 * Setzt den Spieler der jetzt am Zug ist (darf nicht null sein)
	 * 
	 * @param spielerAmZug
	 */
	private void setSpielerAmZug(Spieler spielerAmZug) {
		if (spielerAmZug == null) {
			throw new NullPointerException("spielerAmZug");
		}
		this.spielerAmZug = spielerAmZug;
	}

	/**
	 * Setzt die Zahl die zuletzt gewürfelt wurde (muss zwischen eins und sechs
	 * sein)
	 * 
	 * @param zuleztGewuerfelt
	 */
	private void setZuleztGewuerfelt(int zuleztGewuerfelt) {

		if (zuleztGewuerfelt < 1 || zuleztGewuerfelt > 6) {
			throw new IllegalArgumentException(
					"zuletztGewuerfelt liegt nicht zwischen 1 und 6");
		}
		this.zuleztGewuerfelt = zuleztGewuerfelt;
	}

	/**
	 * Setzt den Index, welcher in der ArrayList der Spieler auf denjenigen
	 * Spieler verweist, der jetzt am Zug ist (ist der Index nicht im erlaubten
	 * Bereich, so wird er auf 0 gesetzt)
	 * 
	 * @param spielerAmZugIndex
	 */
	private void setSpielerAmZugIndex(int spielerAmZugIndex) {
		if (spielerAmZugIndex < 0
				|| spielerAmZugIndex >= this.teilnehmendeSpieler.size()) {
			this.spielerAmZugIndex = 0;
		} else {
			this.spielerAmZugIndex = spielerAmZugIndex;
		}
	}

	/**
	 * Setzt das Spielbrett, auf dem das Spiel abläuft (darf nicht null sein)
	 * 
	 * @param spielbrett
	 */
	private void setSpielbrett(Spielbrett spielbrett) {
		if (spielbrett == null) {
			throw new NullPointerException("spielbrett");
		}
		this.spielbrett = spielbrett;
	}

	// Konstruktor

	/**
	 * Erstellt ein neues Spiel
	 * 
	 * @param spielerName
	 *            Name des ersten Spielers
	 * @param spielerFarbe
	 *            Farbe des ersten Spielers
	 * @param kiTyp
	 *            KITyp des ersten Spielers
	 */
	public Spiel(String spielerName, FarbEnum spielerFarbe, KiTypEnum kiTyp) {
		this.setSpieler(new ArrayList<Spieler>());
		Spieler ersterSpieler = new Spieler(this, spielerName, spielerFarbe, kiTyp);

		this.setSpielerAmZug(ersterSpieler);
		this.setSpielerAmZugIndex(0);
		this.teilnehmendeSpieler.add(ersterSpieler);
	}

	// iBediener overrides

	/**
	 * Überschreiben der Methode spielerHinzufügen, welche einen Spieler
	 * hinzufügt, der einen Namen, Farbe und einen Würfel besitzt
	 */
	@Override
	public void spielerHinzufuegen(String name, FarbEnum farbe, KiTypEnum kiTyp)
			throws SpielerFarbeVorhandenException {

		if (name == null || farbe == null || name.isEmpty())
			throw new IllegalArgumentException();

		for (Spieler spieler : this.teilnehmendeSpieler) {

			if (spieler.getFarbe() == farbe)
				throw new SpielerFarbeVorhandenException(farbe);
		}

		this.teilnehmendeSpieler.add(new Spieler(this, name, farbe, kiTyp));
	}

	/**
	 * Überschreiben der Methode spielerEntfernen, welche einen Spieler enfernt.
	 */
	@Override
	public void spielerEntfernen(FarbEnum farbeDesSpielers)
			throws SpielerNichtGefundenException {
		
		if (farbeDesSpielers == null) 
			throw new NullPointerException("farbeDesSpielers");

		int indexZuEntfernen = -1;
		for (int i = 0; i < this.teilnehmendeSpieler.size(); i++) {
			if (this.teilnehmendeSpieler.get(i).getFarbe() == farbeDesSpielers) {
				indexZuEntfernen = i;
				break;
			}
		}

		if (indexZuEntfernen == -1)
			throw new SpielerNichtGefundenException(farbeDesSpielers);
		else
			this.teilnehmendeSpieler.remove(indexZuEntfernen);
	}

	/**
	 * Überschreiben der Methode sWuerfeln, welche den Spieler, der am Zug ist,
	 * würfeln lässt und die gewürfelte Zahl zurück gibt
	 */
	@Override
	public int sWuerfeln() {

		if (this.spielerAmZug != null) {
			this.setZuleztGewuerfelt(this.spielerAmZug.wuerfeln());
			return this.zuleztGewuerfelt;
		} else
			throw new NullPointerException("this.spielerAmZug");

	}

	/**
	 * Überschreiben der Methode ziehen, welche versucht den gewünschten Zug
	 * auszuführen, sofern er mit den Spielregeln vereinbar ist
	 */
	@Override
	public ZugErgebnis ziehen(int figurID) {


		ZugErgebnis ergebnis = this.getSpielbrett().zug(this.zuleztGewuerfelt,
				this.spielerAmZug.getFigurDurchID(figurID));

		// Der Zug war gültig und es wurde keine 6 gewürfelt, also ist der
		// nächste Spieler dran
		if (ergebnis.isGueltig() && ergebnis.isZugBeendet()) {

			// Der Index muss immer neu geprüft werden, da Spieler hinzugekommen
			// sein könnten
			this.setSpielerAmZugIndex(this.spielerAmZugIndex + 1);

			if (this.spielerAmZugIndex >= this.teilnehmendeSpieler.size())
				this.setSpielerAmZugIndex(0);

			this.setSpielerAmZug(this.teilnehmendeSpieler
					.get(this.spielerAmZugIndex));

		}

		return ergebnis;
	}

	/**
	 * Überschrieben der Methode debugWuerfeln, welche einen Würfel Wurf
	 * simuliert, der die gewünschte Zahl ergibt
	 */
	@Override
	public int debugWuerfeln(int gewuenschteZahl) {
		return this.zuleztGewuerfelt = gewuenschteZahl;
	}

	/**
	 * Überschreiben der Methode debugSetzeFigur, welche die gewünschte Figur
	 * auf das gewünschte Feld setzt ohne dabei die Spielregeln zu beachten
	 */
	@Override
	public void debugSetzeFigur(FarbEnum spielerFarbe, int figurID,
			String zielFeldID) throws SpielerNichtGefundenException {

		Spieler spieler = findeSpieler(spielerFarbe);

		if (spieler != null) {
			Spielfigur figur = spieler.getFigurDurchID(figurID);

			if (figur != null && this.spielbrett != null) {
				this.spielbrett.debugSetPos(figur, zielFeldID);
			}
		}
	}

	// Private Methoden

	/**
	 * Versucht den gesuchten Spieler in der Spielerliste zu finden
	 * 
	 * @param spielerFarbe
	 *            Die Farbe des gesuchten Spielers
	 * @return Den gefundenen Spieler
	 * @throws SpielerNichtGefundenException
	 *             Es gibt keinen Spieler mit der gesuchten Farbe
	 */
	private Spieler findeSpieler(FarbEnum spielerFarbe)
			throws SpielerNichtGefundenException {

		for (Spieler spieler : this.teilnehmendeSpieler) {
			if (spieler.getFarbe() == spielerFarbe)
				return spieler;
		}

		throw new SpielerNichtGefundenException(spielerFarbe);
	}

}
