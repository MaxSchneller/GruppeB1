package Spiel;

import java.io.Serializable;

/**
 * Klasse Spielfeld
 * 
 * @author Gruppe B1
 *
 */
public class Spielfeld implements Serializable {
	private Spielbrett brett;
	private String ID;
	private Spielfigur figurAufFeld;

	/**
	 * Konstruktor Spielfeld
	 * 
	 * @param ID
	 *            Die ID des Spielfelds
	 * @param brett
	 *            Das Spielbrett
	 */
	public Spielfeld(String ID, Spielbrett brett) {
		setID(ID);
		setSpielbrett(brett);
	}

	public static boolean isFeldIDgueltig(String ID) {
		if (ID == null || ID.isEmpty()) {
			throw new RuntimeException("ID darf nicht leer sein");
		} else if (ID.length() <= 2) {
			int id = Integer.parseInt(ID);
			if (id > 40 || id < 1) {
				throw new RuntimeException("ID muss zwischen 1 und 40 liegen");
			}
			return true;
		} else {
			if (!ID.contains(" ")) {
				throw new RuntimeException("ID nicht gültig");
			}
			String[] teile = ID.split(" ");

			if (teile.length != 2) {
				throw new RuntimeException("ID hat zu viele Leerzeichen!");
			}

			String idTeil = teile[0];
			String farbTeil = teile[1];

			if (idTeil.length() != 2
					|| !(idTeil.startsWith("S") || idTeil.startsWith("E"))) {
				throw new RuntimeException("ID ist ungültig");
			}

			int startEndfeldID = Integer.parseInt(idTeil.substring(1));
			if (startEndfeldID < 1 || startEndfeldID > 4) {
				throw new RuntimeException("ID ist ungültig");
			}

			if (!(farbTeil.equalsIgnoreCase("BLAU")
					|| farbTeil.equalsIgnoreCase("ROT")
					|| farbTeil.equalsIgnoreCase("GRUEN") || farbTeil
						.equalsIgnoreCase("GELB"))) {
				throw new RuntimeException("ID ist ungültig");
			}
			return true;
		}
	}

	/**
	 * Eine Spielfigur auf Feld setzen
	 * 
	 * @param figur
	 *            ist eine Spielfigur
	 */
	public void figurAufFeld(Spielfigur figur) {

	}

	/**
	 * Setter-Methode fuer Spielbrett
	 * 
	 * @param brett
	 *            Das Spielbrett
	 */
	private void setSpielbrett(Spielbrett brett) {
		if (brett == null) {
			throw new RuntimeException("Spielbrett darf nicht leer sein");
		} else {
			this.brett = brett;
		}
	}

	/**
	 * Setter-methode fuer ID
	 * 
	 * @param ID
	 *            Die ID des Spielfelds
	 */
	private void setID(String ID) {
		if (Spielfeld.isFeldIDgueltig(ID)) {
			this.ID = ID;
		} else {
			throw new RuntimeException("ungueltige ID");
		}
	}

	/**
	 * Getter-Methode fuer ID
	 * 
	 * @return Gibt ID zurueck
	 */
	public String getID() {
		return ID;
	}

	@Override
	public String toString() {
		return ID;
	}

	public Spielfigur getFigurAufFeld() {

		return this.figurAufFeld;
	}

	public void setFigurAufFeld(Spielfigur figur) {
		this.figurAufFeld = figur;
	}

	/**
	 * Konvertiert den Farbteil der ID (falls vorhanden) in eine Farbe aus
	 * FarbEnum
	 * 
	 * @return Die Farbe dieses Feldes oder null falls es keine Farbe hat
	 */
	public FarbEnum getFarbe() {
		String id = this.getID();

		if (Spielfeld.isFeldIDgueltig(id)) {
			if (id.length() > 2) {

				String[] teile = id.split(" ");

				return FarbEnum.vonString(teile[1]);
			}
		}

		return null;
	}

	/**
	 * Ermittelt anhand der ID, ob dieses Feld ein Startfeld ist
	 * 
	 * @return True falls dieses Feld ein Startfeld ist, sonst false
	 */
	public boolean isStartfeld() {
		String id = this.getID();

		if (Spielfeld.isFeldIDgueltig(id)) {
			if (id.length() > 2) {
				String[] teile = id.split(" ");

				return teile[0].startsWith("S");

			}
		}

		return false;
	}

	/**
	 * Ermittelt anhand der ID, ob dieses Feld ein Endfeld ist
	 * 
	 * @return True falls dieses Feld ein Endfeld ist, sonst false
	 */
	public boolean isEndfeld() {
		
		String id = this.getID();
		
		if (Spielfeld.isFeldIDgueltig(id)) {
			
			if (id.length() > 2) {
				
				String[] teile = id.split(" ");
				return teile[0].startsWith("E");
			}
		}
		return false;
	}
	
	/**
	 * Falls dieses Feld ein End- oder Startfeld ist, wird die Nummer des Feldes
	 * zurueckgegeben sonst -1
	 * @return
	 */
	public int getEndStartFeldNummer() {
		if (this.isEndfeld() || this.isStartfeld()) {
			// S1 ROT
			
			String[] teile = this.getID().split(" ");
			
			if (teile.length == 2) {
				String nummer = teile[0];
				
				return Integer.parseInt(nummer.substring(1));
			}
		}
		
		return -1;
	}
}
