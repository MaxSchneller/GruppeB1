package Spiel;
import java.io.IOException;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.PrimitiveIterator.OfDouble;
import java.util.concurrent.CountDownLatch;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.iDatenzugriff;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

/**
 * Die zentrale Managerklasse des Spiels
 */
public class SpielBean implements iBediener, Serializable {

	// Attribute

	public ArrayList<Spieler> getTeilnehmendeSpieler() {
		return teilnehmendeSpieler;
	}

	public void setTeilnehmendeSpieler(ArrayList<Spieler> teilnehmendeSpieler) {
		this.teilnehmendeSpieler = teilnehmendeSpieler;
	}

	public boolean isKannWuerfeln() {
		return kannWuerfeln;
	}

	public void setKannWuerfeln(boolean kannWuerfeln) {
		this.kannWuerfeln = kannWuerfeln;
	}

	public boolean isKanZiehen() {
		return kanZiehen;
	}

	public void setKanZiehen(boolean kanZiehen) {
		this.kanZiehen = kanZiehen;
	}

	public int getWuerfelVersuche() {
		return wuerfelVersuche;
	}

	public void setWuerfelVersuche(int wuerfelVersuche) {
		this.wuerfelVersuche = wuerfelVersuche;
	}

	public Spieler getSpielerAmZug() {
		return spielerAmZug;
	}

	/** Alle Spieler, die zur Zeit teilnehmen */
	private ArrayList<Spieler> teilnehmendeSpieler = new ArrayList<Spieler>();
	/** Der Spieler, der gerade am Zug ist */
	private Spieler spielerAmZug = null;
	/** Die zuletzt geürfelte Zahl */
	private int zuleztGewuerfelt = 0;
	/** Index des Spielers, der gerade am Zug ist */
	private int spielerAmZugIndex = 0;
	/** Das Spielbrett */
	private Spielbrett spielbrett = new Spielbrett(this);
	/** Kann zur Zeit gewuerfelt werden? */
	private boolean kannWuerfeln = true;
	/** Gibt an ob der Spieler am Zug ziehen darf */
	private boolean kanZiehen = false;
	/** Anzahl der Versuche eine 6 zu wuerfeln */
	private int wuerfelVersuche = 0;

	// Getter und Setter

	/**
	 * Gibt das Spielbrett dieses Spiels zurück
	 * 
	 * @return Spielbrett
	 */
	public Spielbrett getSpielbrett() {
		return spielbrett;
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
	 * Setzt die Zahl die zuletzt gewuerfelt wurde (muss zwischen eins und sechs
	 * sein)
	 * 
	 * @param zuleztGewuerfelt
	 * @throws KannNichtWuerfelnException 
	 */
	private void setZuleztGewuerfelt(int zuleztGewuerfelt) throws KannNichtWuerfelnException {

		if (zuleztGewuerfelt < 1 || zuleztGewuerfelt > 6) {
			throw new IllegalArgumentException(
					"zuletztGewuerfelt liegt nicht zwischen 1 und 6");
		} else if (!this.kannWuerfeln) {
			throw new KannNichtWuerfelnException();
		}
		this.zuleztGewuerfelt = zuleztGewuerfelt;
		this.kanZiehen = true;
	}

	/**
	 * Setzt den Index, welcher in der ArrayList der Spieler auf denjenigen
	 * Spieler verweist, der jetzt am Zug ist (ist der Index nicht im erlaubten
	 * Bereich, so wird er auf 0 gesetzt)
	 * 
	 * @param spielerAmZugIndex Index des Spielers am Zug
	 */
	public void setSpielerAmZugIndex(int spielerAmZugIndex) {
		if (spielerAmZugIndex < 0
				|| spielerAmZugIndex >= this.teilnehmendeSpieler.size()) {
			this.spielerAmZugIndex = 0;
		} else {
			this.spielerAmZugIndex = spielerAmZugIndex;
		}
	}
	
	/**
	 * Getter fuer getSpielerAmZugIndex
	 * @return den Index welcher Spieler am zug ist
	 */
	public int getSpielerAmZugIndex(){
		return spielerAmZugIndex;
	}
	
	

	/**
	 * Setzt das Spielbrett, auf dem das Spiel abläuft (darf nicht null sein)
	 * 
	 * @param spielbrett Objekt
	 */
	public void setSpielbrett(Spielbrett spielbrett) {
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
	public SpielBean(String spielerName, FarbEnum spielerFarbe, KiTypEnum kiTyp) {

		Spieler ersterSpieler = new Spieler(this, spielerName, spielerFarbe,
				kiTyp);

		this.setSpielerAmZug(ersterSpieler);
		this.setSpielerAmZugIndex(0);
		this.teilnehmendeSpieler.add(ersterSpieler);
	}
	
	/**
	 * Default Konstruktor fuer die Bean
	 */
	public SpielBean() {
		// Macht nix
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
	 * @throws KannNichtWuerfelnException 
	 */
	@Override
	public WuerfelErgebnis sWuerfeln() throws KannNichtWuerfelnException {

		if (this.spielerAmZug != null) {
			this.setZuleztGewuerfelt(this.spielerAmZug.wuerfeln());

			return konstruiereWuerfelErgebnis();
		} else {
			throw new NullPointerException("this.spielerAmZug");
		}

	}

	/**
	 * Der naechste Spieler ist am Zug.
	 */
	private void naechsterSpielerIstDran() {
		this.kannWuerfeln = true;
		this.wuerfelVersuche = 0;
		this.setSpielerAmZugIndex(this.spielerAmZugIndex + 1);

		if (this.spielerAmZugIndex >= this.teilnehmendeSpieler.size())
			this.setSpielerAmZugIndex(0);

		this.setSpielerAmZug(this.teilnehmendeSpieler
				.get(this.spielerAmZugIndex));
	}

	/**
	 * Überschreiben der Methode ziehen, welche versucht den gewünschten Zug
	 * auszuführen, sofern er mit den Spielregeln vereinbar ist
	 */
	@Override
	public ZugErgebnis ziehen(int figurID) {

		ZugErgebnis ergebnis;
		if (this.kanZiehen == false) {
			return new ZugErgebnis(false, false, null, false, null, null,
					"Kann nicht ziehen, muss erst wuerfel");
		}
		
		if (this.spielerAmZug.hatFigurAufSpielfeld()
				|| (this.zuleztGewuerfelt == 6)) {
			// Spieler hat schon Figuren draussen oder eine 6 gewuerfelt
			if (!this.isSpielerAmZugKI()) {
				// Spieler ist keine KI
				ergebnis = this.getSpielbrett().zug(this.zuleztGewuerfelt,
						this.spielerAmZug.getFigurDurchID(figurID), true);
			} else {
				// Spieler ist KI
				
				ArrayList<Spieler> gegner = new ArrayList<Spieler>();
				
				for (Spieler spieler : this.teilnehmendeSpieler) {
					if (spieler.getFarbe() != this.spielerAmZug.getFarbe()) {
						gegner.add(spieler);
					}
				}
				Spielfigur[][] gegnerFiguren = new Spielfigur[gegner.size()][4];
				
				for (int i = 0; i < gegner.size(); ++i) {
					Spieler spieler = gegner.get(i);
					for (int j = 0; j < 4; ++j) {
						gegnerFiguren[i][j] = spieler.getFigurDurchID(j);
					}
				}
				
				int figID = this.spielerAmZug.kiBerechnen(gegnerFiguren, this.zuleztGewuerfelt);
				
				if (figID == -1) {
					iDatenzugriff s = new DatenzugriffPDF();
					try {
						s.spielSpeichern(this, "./KIMuell.pdf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					@SuppressWarnings("unused")
					int foo = this.spielerAmZug.kiBerechnen(gegnerFiguren, this.zuleztGewuerfelt);
					
					throw new RuntimeException("KI hat ungueltigen Zug berechnet, Spielstand wurde unter"
							+ " 'KI Muell.pdf' gespeichert.");
				}
				Spielfigur figur = this.spielerAmZug.getFigurDurchID(figID);
				ergebnis = this.spielbrett.zug(this.zuleztGewuerfelt, figur, true);
				
				if (!ergebnis.isGueltig()){
				
					
					iDatenzugriff s = new DatenzugriffPDF();
					try {
						s.spielSpeichern(this, "./KIMuell.pdf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					@SuppressWarnings("unused")
					int foo = this.spielerAmZug.kiBerechnen(gegnerFiguren, this.zuleztGewuerfelt);
					
					throw new RuntimeException("KI hat ungueltigen Zug berechnet, Spielstand wurde unter"
							+ " 'KI Muell.pdf' gespeichert.");
				}
			}
			// Der Zug war gültig und es wurde keine 6 gewürfelt, also ist der
			// nächste Spieler dran
			if (ergebnis.isZugBeendet()) {

				naechsterSpielerIstDran();
				this.kannWuerfeln = true;
				this.kanZiehen = false;
			} else {
				if (!ergebnis.isGueltig()) {
					this.kannWuerfeln = false;
					this.kanZiehen = true;
				} else {
					this.kannWuerfeln = true;
					this.kanZiehen = false;
				}
			}

		
			return ergebnis;
		} else {
			return new ZugErgebnis(false, false, null, false, null, null, "Kann nicht ziehen, muss erst wuerfeln");
		}
	}

	/**
	 * Überschrieben der Methode debugWuerfeln, welche einen Würfel Wurf
	 * simuliert, der die gewünschte Zahl ergibt
	 * @throws KannNichtWuerfelnException 
	 */
	@Override
	public WuerfelErgebnis debugWuerfeln(int gewuenschteZahl) throws KannNichtWuerfelnException {
		setZuleztGewuerfelt(gewuenschteZahl);

		return konstruiereWuerfelErgebnis();
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

	@Override
	public FarbEnum getSpielerAmZugFarbe() {
		return (this.spielerAmZug != null) ? this.spielerAmZug.getFarbe()
				: null;
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

	private boolean kannZiehen() {

		for (int i = 0; i < 4; ++i) {
			Spielfigur figur = this.spielerAmZug.getFigurDurchID(i);

			ZugErgebnis ergebnis = this.spielbrett.zug(zuleztGewuerfelt, figur,
					false);

			if (ergebnis.isGueltig()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Ueberprueft ob die Wuerfelzahl, was mit ihr gemacht werden kann (ziehen, nochmal wuerfeln, ...)
	 * @return Gibt das Ergebnis zurueck.
	 */
	private WuerfelErgebnis konstruiereWuerfelErgebnis() {
		if (!kannZiehen()) {
			if (!this.spielerAmZug.hatFigurAufSpielfeld()) {
				++this.wuerfelVersuche;
				if (this.wuerfelVersuche < 3) {
					return new WuerfelErgebnis(this.zuleztGewuerfelt, false,
							true);
				} else {
					naechsterSpielerIstDran();
					return new WuerfelErgebnis(this.zuleztGewuerfelt, false,
							false);
				}
			} else {
				this.naechsterSpielerIstDran();
				this.wuerfelVersuche = 0;
				return new WuerfelErgebnis(this.zuleztGewuerfelt, false, false);
			}
		} else {
			this.kannWuerfeln = false;
			return new WuerfelErgebnis(this.zuleztGewuerfelt, true, false);
		}
	}

	@Override
	public String[][] getAlleFigurenPositionen() {
		String[][] positionenStrings = new String[this.teilnehmendeSpieler
				.size() * 4][3];

		for (int i = 0; i < this.teilnehmendeSpieler.size(); ++i) {
			for (int j = 0; j < 4; ++j) {
				int figurenIndex = i * 4 + j;
				Spielfigur figur = this.teilnehmendeSpieler.get(i)
						.getFigurDurchID(j);
				positionenStrings[figurenIndex][0] = figur.getFarbe()
						.toString();
				positionenStrings[figurenIndex][1] = String.format("%d",
						figur.getID());
				positionenStrings[figurenIndex][2] = figur.getSpielfeld()
						.getID();
			}
		}
		return positionenStrings;
	}

	@Override
	public boolean isSpielerAmZugKI() {
		return this.spielerAmZug.isSpielerKI();
	}


	@Override
	public String[] getSpieler() {
		String [] spieler = new String[this.teilnehmendeSpieler.size()];
		for(int i = 0; i < teilnehmendeSpieler.size(); i++){
			spieler[i] = teilnehmendeSpieler.get(i).getName() + " ; " + teilnehmendeSpieler.get(i).getFarbe().name() + " ; ";
				spieler[i] += teilnehmendeSpieler.get(i).getKiTyp() == null? " ; ": teilnehmendeSpieler.get(i).getKiTyp() + " ; "; 
						for(int j = 0; j < 4; j++){
							spieler[i] += teilnehmendeSpieler.get(i).getFigurDurchID(j).getSpielfeld().getID() + " ; ";
						}
		}
		return spieler;
	}

	/**
	 * Welcher Spieler ist am Zug? setten!
	 * @param spielerAmZugFarbe Gibt die Farbe des Spielers, der am zug ist zurueck.
	 */
	public void setSpielerAmZug(FarbEnum spielerAmZugFarbe) {
		if (spielerAmZugFarbe != null) {
			for (int i = 0; i < this.teilnehmendeSpieler.size(); ++i) {
				Spieler s = this.teilnehmendeSpieler.get(i);
				
				if (s.getFarbe() == spielerAmZugFarbe) {
					this.kannWuerfeln = true;
					this.wuerfelVersuche = 0;
					this.setSpielerAmZugIndex(i);
					this.spielerAmZug = s;
					return;
					
				}
			}
		}
	}

	@Override
	public boolean isFarbeVergeben(FarbEnum farbe) {
		for (Spieler s : this.teilnehmendeSpieler) {
			if (s.getFarbe() == farbe) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @return Gibt die zulezt gewuerfelte Zahl zurueck
	 */
	public int getZuleztGewuerfelt() {
		return this.zuleztGewuerfelt;
	}

	@Override
	public String getSpielerName(FarbEnum farbe)
			throws SpielerNichtGefundenException {
		for (Spieler s : this.teilnehmendeSpieler) {
			if (s.getFarbe() == farbe) {
				return s.getName();
			}
		}
		
		throw new SpielerNichtGefundenException(farbe);
	}

}
