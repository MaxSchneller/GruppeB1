
/**
 * Das Resultat eines Zugversuches
 */
public class ZugErgebnis {

	/** War der Zug gueltig? */
	private boolean gueltig;
	/** Ist der naechste Spieler an der Reihe */
	private boolean zugBeendet;
	/** Alle Figuren, die durch den Zug veraendert wurden */
	private String[][] geaenderteFiguren = null;
	/** Wurde das Spiel gewonnen? */
	private boolean spielGewonnen;
	/** Der Name des Gewinners, falls das Spiel gewonnen wurde */
	private String gewinnerName;
	/** Die Farbe des Gewinners, falls das Spiel gewonnen wurde */
	private FarbEnum gewinnerFarbe;
	/** Details zum Zug */
	private String nachricht;

	/**
	 * Erstellt ein neues Zugergebnis, welches das Resultat eines Zugversuches darstellt
	 * @param gueltig War der Zug gueltig
	 * @param zugBeendet Ist der naechste Spieler an der Reihe
	 * @param geanderteFiguren Alle Figuren, die durch den Zug veraendert wurden <b>(kann null sein)</b>
	 * @param spielGewonnen Wurde das Spiel gewonnen ?
	 * @param gewinnerName Der Name des Gewinners <b>(kann null sein)</b>
	 * @param gewinnerFarbe Die Farbe des Gewinners <b>(kann null sein)</b>
	 * @param nachricht Details zum Zug
	 */
	public ZugErgebnis(boolean gueltig, boolean zugBeendet,
			Spielfigur[] geanderteFiguren, boolean spielGewonnen,
			String gewinnerName, FarbEnum gewinnerFarbe, String nachricht) {
		this.gueltig = gueltig;
		this.zugBeendet = zugBeendet;
		setGeaennderteFiguren(geanderteFiguren);
		this.spielGewonnen = spielGewonnen;
		this.gewinnerName = gewinnerName;
		this.gewinnerFarbe = gewinnerFarbe;
		this.nachricht = nachricht == null ? "" : nachricht;

		// Bei einem ungueltige Zug ist sofort der nöchste Spieler dran
		/*
		 * if(zugBeendet && !gueltig){ throw new
		 * RuntimeException("Zug ungueltig!"); }
		 */
		// Kann vorkommen, da Zuege nicht mehr ausgefuehrt werden muessen
		/*if (gueltig && geanderteFiguren == null) {
			throw new RuntimeException("Zug ungueltig!");
		}*/
		if (spielGewonnen) {
			if (gewinnerName == null || gewinnerFarbe == null) {
				throw new RuntimeException("Zug ungueltig!");
			}
		}
	}

	private void setGeaennderteFiguren(Spielfigur[] geanderteFiguren) {
		if (geanderteFiguren == null) {
			// Nichts tun
			return;
		}
		
		this.geaenderteFiguren = new String[geanderteFiguren.length][3];
		for (int i = 0; i < geanderteFiguren.length; i++) {
			this.geaenderteFiguren[i][0] = geanderteFiguren[i].getFarbe()
					.toString();
			this.geaenderteFiguren[i][1] = String.format("%d",
					geanderteFiguren[i].getID());
			this.geaenderteFiguren[i][2] = geanderteFiguren[i].getSpielfeld()
					.getID();
		}
	}

	public boolean isGueltig() {
		return gueltig;
	}

	public boolean isZugBeendet() {
		return zugBeendet;
	}

	/**
	 * Format: <br>
	 * Index 0: Farbe der Figur <br>
	 * Index 1: ID der Figur	<br>
	 * Index 2: Id des Feldes auf dem die Figur steht<br>
	 * @return Alle durch den Zug geaenderten Figuren <b>(kann null sein!)</b>
	 */
	public String[][] getGeaenderteFiguren() {
		return geaenderteFiguren;
	}

	public boolean isSpielGewonnen() {
		return spielGewonnen;
	}

	/**
	 * @return Der Name des Gewinners <b>(nur != null falls Spiel gewonnen)</b>
	 */
	public String getGewinnerName() {
		return gewinnerName;
	}

	/**
	 * @return Die Farbe des Gewinners <b>(nur != null falls Spiel gewonnen)</b>
	 */
	public FarbEnum getGewinnerFarbe() {
		return gewinnerFarbe;
	}

	public String getNachricht() {
		return nachricht;
	}

	@Override
	public String toString() {
		String nachrichtenString = "";

		nachrichtenString += "Gueltig: " + (isGueltig() ? "Ja" : "Nein") + "\n";
		nachrichtenString += "Beendet: " + (isZugBeendet() ? "Ja" : "Nein")
				+ "\n";
		nachrichtenString += getNachricht() + "\n";

		if (isGueltig()) {
			if (this.geaenderteFiguren != null) {
				for (int i = 0; i < this.geaenderteFiguren.length; i++) {
					nachrichtenString += "Figur "
							+ this.geaenderteFiguren[i][0] + " "
							+ this.geaenderteFiguren[i][1]
							+ " ist auf Feld: "
							+ this.geaenderteFiguren[i][2] + "\n";
				}
			}

			if (isSpielGewonnen()) {
				nachrichtenString += "Spiel wurde gewonnen! \n";
				nachrichtenString += "Gewinner Name: " + getGewinnerName()
						+ "\n";
				nachrichtenString += "Gewinner Farbe: " + getGewinnerFarbe()
						+ "\n";
			}
		}

		return nachrichtenString;
	}

}
