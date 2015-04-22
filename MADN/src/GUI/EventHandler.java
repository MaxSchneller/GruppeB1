package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

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
	private MADNGui gui;
	/** Das Spiel */
	private iBediener spiel;
	/** Nummer des Spielers dessen Daten abgefragt werden sollen */
	private int neuerSpielerNummer;
	/** Anzahl der zu erstellenden Spieler */
	private int spielerAnzahl = -1;

	private void setGui(MADNGui gui) {
		if (gui == null) {
			throw new IllegalArgumentException(
					"EventHandler kann nicht ohne GUI arbeiten (gui ist null)");
		}
		this.gui = gui;
	}

	public EventHandler(MADNGui gui) {
		this.setGui(gui);

		this.gui.frageGewuenschteSpielerAnzahl();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		this.verarbeiteNeuerSpieler(source);
		this.verarbeiteWuerfeln(source);
		this.verarbeiteFiguren(source);
		this.verarbeiteSpielerAnzahl(source);

	}

	private void verarbeiteWuerfeln(Object source) {
		if (source == this.gui.getButtonWuerfeln()) {
			// "Wuerfeln" wurde gedrueckt

			if (this.spiel == null) {
				this.logFehler("Kann jetzt nicht wuerfeln, es gibt noch kein Spiel!");
				return;
			} else {
				WuerfelErgebnis ergebnis = this.spiel.sWuerfeln();

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
							this.lassKIWuerfeln();
						}
					}
				}

			}
		}
	}

	private void lassKIWuerfeln() {
		// TODO Auto-generated method stub

	}

	private void verarbeiteNeuerSpieler(Object source) {
		if (source == this.gui.getButtonWeiter()) {
			// True wenn der "Weiter" Button bei der Spielererstellung gedrueckt
			// wurde
			JTextField nameArea = this.gui.getNameArea();
			JComboBox farbeComboBox = this.gui.getFarbeCombo();
			JComboBox kiComboBox = this.gui.getKICombo();

			String name = nameArea.getText();
			FarbEnum farbe = FarbEnum.vonInt(farbeComboBox.getSelectedIndex());
			KiTypEnum kiTyp = KiTypEnum.vonInt(kiComboBox.getSelectedIndex());

			if (this.spiel == null) {
				this.spiel = new Spiel(name, farbe, kiTyp);
			} else {
				try {

					this.spiel.spielerHinzufuegen(name, farbe, kiTyp);
					++this.neuerSpielerNummer;
				} catch (SpielerFarbeVorhandenException e) {
					this.logFehler(e.getMessage());
				}
			}

			if (this.neuerSpielerNummer < this.spielerAnzahl) {
				this.gui.frageSpielerDaten(this.neuerSpielerNummer);
			}
		}
	}

	private void verarbeiteFiguren(Object source) {
		if (this.spiel != null) {
			// Macht nur Sinn mit Spiel...

			boolean ziehen = false;
			int figurID = -1;
			
			if (source == this.gui.getButtonFigur1()) {
				figurID = 1;
				ziehen = true;
			}
			if (source == this.gui.getButtonFigur2()) {
				figurID = 2;
				ziehen = true;
			}
			if (source == this.gui.getButtonFigur3()) {
				figurID = 3;
				ziehen = true;
			}
			if (source == this.gui.getButtonFigur4()) {
				figurID = 4;
				ziehen = true;
			}
			
			if (ziehen) {
				ZugErgebnis ergebnis = this.spiel.ziehen(figurID);
				
				if (ergebnis.isGueltig()) {
					this.logInfo("Zug ist gueltig.");
					
					for (String[] figur : ergebnis.getGeaenderteFiguren()) {
						this.gui.setzeSpielfigur(figur[0], 
												Integer.parseInt(figur[1]), // Try noetig, da garantiert
												figur[2]);
					}
				} else {
					this.logFehler("Ungueltiger Zug!");
				}
				
				if (ergebnis.isSpielGewonnen()) {
					this.gui.spielGewonnen(ergebnis.getGewinnerName(), ergebnis.getGewinnerFarbe());
					return;
				}
				
				if (ergebnis.isZugBeendet()) {
					this.logInfo("Naechster Spieler ist dran.");
					this.gui.setzteSpielerAmZug(this.spiel.getSpielerAmZugFarbe().name());
					
					if (this.spiel.isSpielerAmZugKI()) {
						this.lassKIWuerfeln();
					}
				}
			}
		}
	}

	private void verarbeiteSpielerAnzahl(Object source) {
		// Wenn schon ein Spiel existiert, nichts machen
		if (this.spiel != null) {
			return;
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
			this.gui.frageSpielerDaten(this.neuerSpielerNummer);
		}
	}

	private void logInfo(String s) {
		this.gui.setzeStatusNachricht(s);
	}

	private void logWarnung(String s) {
		this.gui.zeigeWarnung(s);
	}

	private void logFehler(String s) {
		this.gui.ziegeFehler(s);
	}

	private void logKI(String s) {
		this.logInfo(s);
	}

}
