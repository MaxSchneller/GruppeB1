package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;
import Spiel.Spiel;
import Spiel.WuerfelErgebnis;
import Spiel.ZugErgebnis;
import Spiel.iBediener;

/**
 * Verarbeitet alle Events, welche von der GUI kommen
 */
public class EventHandler implements ActionListener {

	/** Die GUI mit der gearbeitet wird */
	private madnGUI gui;
	/** Das Spiel */
	private iBediener spiel;
	/** Nummer des Spielers dessen Daten abgefragt werden sollen */
	private int neuerSpielerNummer;
	/** Anzahl der zu erstellenden Spieler */
	private int spielerAnzahl = -1;

	private void setGui(madnGUI gui) {
		if (gui == null) {
			throw new IllegalArgumentException(
					"EventHandler kann nicht ohne GUI arbeiten (gui ist null)");
		}
		this.gui = gui;
	}

	/**
	 * Erstellt einen neuen EventHandler fuer die Verbindung zwischen GUI und Spiel
	 * @param gui Die GUI, die diesen EventHandler erstellen will
	 * @exception IllegalArgumentException gui war null
	 */
	public EventHandler(madnGUI gui) throws IllegalArgumentException {
		this.setGui(gui);

		//this.gui.frageGewuenschteSpielerAnzahl();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
	
		this.verarbeiteNaechsterZug(source);
		this.verarbeiteNeuerSpieler(source);
		this.verarbeiteWuerfeln(source);
		this.verarbeiteFiguren(source);
		this.verarbeiteSpielerAnzahl(source);
		
		if (source == this.gui.getButtonKI()) {
			this.verarbeiteKIZug(source);
		}
		
		if (source  == this.gui.getNeuesSpiel()) {
			this.neuerSpielerNummer = 0;
			this.gui.frageGewuenschteSpielerAnzahl();
		}
	
	}

	private void verarbeiteKIZug(Object source) {
		lassKIWuerfeln();
	}

	/**
	 * Prueft ob der "Naechster Zug" Button gedrueckt wurde 
	 * und laesst falls vorhanden die KI wuerfeln und ziehen
	 * @param source Die Eventquellr
	 */
	private void verarbeiteNaechsterZug(Object source) {
		if (source == this.gui.getNaechsterZugButton()) {
			if (this.spiel.isSpielerAmZugKI()) {
				this.gui.getButtonKI().setVisible(true);
				JButton b = (JButton)this.gui.getButtonWuerfeln();
				b.setEnabled(false);
				} else {
				this.gui.getButtonKI().setVisible(false);
				this.gui.setzeStatusNachricht("Naechster Spieler kann wuerfeln");
				JButton b = (JButton)this.gui.getButtonWuerfeln();
				b.setEnabled(true);
			}
		}
	}

	/**
	 * Gibt an ob eine Farbe bereits vergeben ist
	 * @param farbe Die Farbe die geprueft werden soll
	 * @return True falls die Farbe vergeben ist, sonst false
	 */
	public boolean isFarbeVergeben(FarbEnum farbe) {
		if(spiel != null){
			return this.spiel.isFarbeVergeben(farbe);
		}
		return false;
	}

	/**
	 * Prueft ob das ActionEvent von dem "Wuerfeln" Button ausgeloest wurde
	 * @param source Die Eventquelle des ActionEvents
	 */
	private void verarbeiteWuerfeln(Object source) {
		if (source == this.gui.getButtonWuerfeln()) {
			// "Wuerfeln" wurde gedrueckt

			if (this.spiel == null) {
				this.logFehler("Kann jetzt nicht wuerfeln, es gibt noch kein Spiel!");
				return;
			} else {
				
				WuerfelErgebnis ergebnis = null;
				
				try {
					ergebnis = this.spiel.sWuerfeln();
				} catch (KannNichtWuerfelnException e) {
					this.gui.zeigeFehler(e.getMessage());
					return;
				}

				this.gui.setzeStatusNachricht("Es wurde " + ergebnis.getGewuerfelteZahl() + " gewuerfelt");
				this.gui.zeigeWuerfel(ergebnis.getGewuerfelteZahl());

				if (ergebnis.isKannZugAusfuehren() == false) {
					this.logWarnung("Es kann kein Zug ausgefuehrt werden!");

					if (ergebnis.isKannNochmalWuerfeln()) {
						this.logWarnung("Bitte nochmal wuerfeln.");
					} else {
						this.logWarnung("Naechster Spieler ist dran.");
						this.gui.setzeSpielerAmZug(this.spiel
								.getSpielerAmZugFarbe().name());

						// Hier ist im Spiel schon der naechste Spieler gesetzt
						if (this.spiel.isSpielerAmZugKI()) {
							this.neachsterSpielerAnDerReihe();
						}
					}
				}

			}
		}
	}

	private void neachsterSpielerAnDerReihe() {
		JButton wuerfel = (JButton)this.gui.getButtonWuerfeln();
		JButton kiZug = this.gui.getButtonKI();
		
		wuerfel.setEnabled(!this.spiel.isSpielerAmZugKI());
		kiZug.setVisible(this.spiel.isSpielerAmZugKI());
		this.gui.setzeSpielerAmZug(this.spiel.getSpielerAmZugFarbe().name());
	}

	/**
	 * Geht einen kompletten KI Zug durch
	 */
	private void lassKIWuerfeln() {
		if (!this.spiel.isSpielerAmZugKI()) {
			this.logFehler("lassKIWuerfeln wurde aufgerufen, aber es ist keine KI am Zug.");
		}
		
		this.logKI("KI wuerfelt...");
		
		WuerfelErgebnis ergebnis;
		try {
			ergebnis = this.spiel.sWuerfeln();
		} catch (KannNichtWuerfelnException e1) {
			this.gui.zeigeFehler(e1.getMessage());
			return;
		}
		
		this.logKI("KI hat " + ergebnis.getGewuerfelteZahl() + " gewuerfelt");
		this.gui.zeigeWuerfel(ergebnis.getGewuerfelteZahl());
		
		while (!ergebnis.isKannZugAusfuehren()) {
			this.logKI("Kein Zug moeglich");
			
			if (ergebnis.isKannNochmalWuerfeln()) {
				this.logKI("KI wuerfelt nochmal...");
				try {
					ergebnis = this.spiel.sWuerfeln();
				} catch (KannNichtWuerfelnException e1) {
					this.gui.zeigeFehler(e1.getMessage());
				}
				this.logKI("KI hat " + ergebnis.getGewuerfelteZahl() + " gewuerfelt");
			} else {
				this.logKI("Kann nicht nochmal wuerfeln, naechster ist dran.");
				this.neachsterSpielerAnDerReihe();
				return;
			}
		}
		
		this.logKI("KI zieht...");
		ZugErgebnis e = this.spiel.ziehen(0);
		
		if (e.isGueltig()) {
			for (String[] figuren : e.getGeaenderteFiguren()) {
				this.gui.setzeSpielfigur(figuren[0], Integer.parseInt(figuren[1]), figuren[2]);
				this.gui.setzeStatusNachricht("Figur: " + Integer.parseInt(figuren[1]) +
						 " " + figuren[0] + " sitzt auf Feld " + figuren[2]);

			}
			
			if (e.isSpielGewonnen()) {
				this.gui.spielGewonnen(e.getGewinnerName(), e.getGewinnerFarbe());
			}
		} else {
			this.logFehler("KI hat ungueltigen Zug errechnet...och noeeee");
		}
		
		if (e.isZugBeendet()) {
			this.neachsterSpielerAnDerReihe();
		}
	}

	/**
	 * Prueft ob das ActionEvent von dem "Weiter" Button bei der Spielerstellung ausgeloest wurde
	 * @param source Die Eventquelle des ActionEvents
	 */
	private void verarbeiteNeuerSpieler(Object source) {
		if (source == this.gui.getButtonWeiter()) {
			// True wenn der "Weiter" Button bei der Spielererstellung gedrueckt
			// wurde
		
			String name = this.gui.getNeuerSpielerName();
			FarbEnum farbe = this.gui.getNeuerSpielerFarbe();
			KiTypEnum kiTyp = this.gui.getNeuerSpielerKiTyp();

			if (this.spiel == null) {
				this.spiel = new Spiel(name, farbe, kiTyp);
				++this.neuerSpielerNummer;
			} else {
				try {

					this.spiel.spielerHinzufuegen(name, farbe, kiTyp);
					++this.neuerSpielerNummer;
				} catch (SpielerFarbeVorhandenException e) {
					this.logFehler(e.getMessage());
				}
			}

			if (this.neuerSpielerNummer < this.spielerAnzahl) {
				this.gui.schliesseSpielerDaten();
				this.gui.frageSpielerDaten(this.neuerSpielerNummer);
			} else {
				this.gui.schliesseSpielerDaten();
				this.gui.setzeSpielerAmZug(this.spiel.getSpielerAmZugFarbe().name());
				
				for (String[] figur : this.spiel.getAlleFigurenPositionen()) {
					this.gui.setzeSpielfigur(figur[0], Integer.parseInt(figur[1]), figur[2]);
				}
				
				if (this.spiel.isSpielerAmZugKI()) {
					this.gui.getButtonKI().setVisible(true);
					JButton b = (JButton)this.gui.getButtonWuerfeln();
					b.setEnabled(false);
				} else {
					JButton b = (JButton)this.gui.getButtonWuerfeln();
					b.setEnabled(true);
				}
			}
		}
	}

	/**
	 * Prueft ob das ActionEvent von den Figurenbuttons ausgeloest wurde
	 * @param source Die Eventquelle des ActionEvents
	 */
	private void verarbeiteFiguren(Object source) {
		if (this.spiel != null) {
			// Macht nur Sinn mit Spiel...

		
			int figurID = -1;
			
			if (source == this.gui.getButtonFigur1(this.spiel.getSpielerAmZugFarbe())) {
				figurID = 0;
			}
			if (source == this.gui.getButtonFigur2(this.spiel.getSpielerAmZugFarbe())) {
				figurID = 1;
			}
			if (source == this.gui.getButtonFigur3(this.spiel.getSpielerAmZugFarbe())) {
				figurID = 2;
			}
			if (source == this.gui.getButtonFigur4(this.spiel.getSpielerAmZugFarbe())) {
				figurID = 3;
			}
			
			if (figurID != -1) {
				if (this.spiel.isSpielerAmZugKI()) {
					this.gui.zeigeFehler("KI ist am Zug, bitte \"KI Zug\" Button benutzen!");
					return;
				}
				ZugErgebnis ergebnis = this.spiel.ziehen(figurID);
				
				if (ergebnis.isGueltig()) {
					this.logInfo("Zug ist gueltig.");
					
					for (String[] figur : ergebnis.getGeaenderteFiguren()) {
						this.gui.setzeStatusNachricht("Figur: " + Integer.parseInt(figur[1]) +
														 " " + figur[0] + " sitzt auf Feld " + figur[2]);
						this.gui.setzeSpielfigur(figur[0], 
												Integer.parseInt(figur[1]), // Try nicht noetig, da garantiert
												figur[2]);
					}
				} else {
					this.logFehler("Ungueltiger Zug!: " + ergebnis.getNachricht());
				}
				
				if (ergebnis.isSpielGewonnen()) {
					this.gui.setzeStatusNachricht("Spiel wurde von " + ergebnis.getGewinnerName()+ "(" + 
													ergebnis.getGewinnerFarbe() + ")" + " gewonnen");
					this.gui.spielGewonnen(ergebnis.getGewinnerName(), ergebnis.getGewinnerFarbe());
					return;
				}
				
				if (ergebnis.isZugBeendet()) {
					this.logInfo("Naechster Spieler ist dran.");
					this.neachsterSpielerAnDerReihe();
				}
			}
		}
	}

	/**
	 * Prueft ob das ActionEvent von Spieleranzahlbuttons ganz am Anfang einer Partie ausgeloest wurde
	 * @param source Die Eventquelle des ActionEvents
	 */
	private void verarbeiteSpielerAnzahl(Object source) {
		// Wenn schon ein Spiel existiert, nichts machen
		if (this.spiel != null && this.neuerSpielerNummer == 0) {
			this.spiel = null;
		}
		// Wurde einer der Anzahlbuttons gedrueckt =?
		boolean einerWurdeGedrueckt = false;

		if (source == this.gui.getButtonSpielerZahl1()) {
			this.spielerAnzahl = 1;
			einerWurdeGedrueckt = true;
		}

		if (source == this.gui.getButtonSpielerZahl2()) {
			this.spielerAnzahl = 2;
			einerWurdeGedrueckt = true;
		}

		if (source == this.gui.getButtonSpielerZahl3()) {
			this.spielerAnzahl = 3;
			einerWurdeGedrueckt = true;
		}

		if (source == this.gui.getButtonSpielerZahl4()) {
			this.spielerAnzahl = 4;
			einerWurdeGedrueckt = true;
		}

		if (einerWurdeGedrueckt) {
			this.gui.schliesseGewuenschteSpielerAnzahl();
			this.gui.frageSpielerDaten(this.neuerSpielerNummer);
		}
	}

	/**
	 * Laesst die GUI die Nachricht als normale Info praesentieren
	 * @param s Die Nachricht, die geschrieben werden soll
	 */
	private void logInfo(String s) {
		this.gui.setzeStatusNachricht(s);
	}

	/**
	 * Laesst die GUI die Nachricht als Warnung praesentieren
	 * @param s Die Nachricht, die geschrieben werden soll
	 */
	private void logWarnung(String s) {
		this.gui.zeigeWarnung(s);
	}

	/**
	 * Laesst die GUI die Nachricht als Fehler praesentieren
	 * @param s Die Nachricht, die geschrieben werden soll
	 */
	private void logFehler(String s) {
		this.gui.zeigeFehler(s);
	}

	/**
	 * Loggt KI Nachrichten
	 * @param s Die Nachricht, die geschrieben werden soll
	 */
	private void logKI(String s) {
		this.logInfo(s);
	}

}
