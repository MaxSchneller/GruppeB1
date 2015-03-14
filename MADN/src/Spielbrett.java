/**
 * Das Spielbrett, auf dem die Spielfiguren stehen
 */
public class Spielbrett {

	/** Die regulären Spielfelder 1-40 */
	Spielfeld[] spielBrett;
	/** Die Startfelder der einzelnen Spieler (S1 - S4) */
	Spielfeld[][] start;
	/** Die Endfelder der einzelnen Spieler (E1 - E4) */
	Spielfeld[][] ziel;

	/**
	 * Erstellt ein neues Spielbrett, sowie die Spielfeld-Arrays
	 */
	public Spielbrett() {

		spielBrett = new Spielfeld[40];
		start = new Spielfeld[4][4];
		ziel = new Spielfeld[4][4];
	}

}
