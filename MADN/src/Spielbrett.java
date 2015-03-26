import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Die Klasse Spielbrett
 * 
 * @author Gruppe B1
 *
 */
public class Spielbrett {

	private  Spielfeld[] regulaereFelder = new Spielfeld[40];

	private  Spielfeld[][] startfelder = new Spielfeld[4][4];
	private  Spielfeld[][] endfelder = new Spielfeld[4][4];

	/**
	 * Konstruktor des Spielbretts
	 */
	public Spielbrett() {
		setStartfelderID();
		setEndfelderID();

		for (int i = 1; i <= 40; i++) {
			regulaereFelder[i - 1] = new Spielfeld(String.format("%d", i), this);
		}
	}

	/**
	 * setzt Spielfelder in das Array und weißt ID zu
	 */

	private void setStartfelderID() {
		FarbEnum farbe = null;
		for (int i = 0; i < 4; i++) {
			switch (i + 1) {
			case 1:
				farbe = FarbEnum.ROT;
				break;
			case 2:
				farbe = FarbEnum.BLAU;
				break;
			case 3:
				farbe = FarbEnum.GRUEN;
				break;
			case 4:
				farbe = FarbEnum.GELB;
				break;
			}
			for (int j = 0; j < 4; j++) {
				startfelder[i][j] = new Spielfeld("S" + (j + 1) + " " + farbe,
						this);
			}
		}
	}

	/**
	 * setzt Spielfelder in das Array und weisst ID zu
	 */

	private void setEndfelderID() {
		FarbEnum farbe = null;
		for (int i = 0; i < 4; i++) {
			switch (i + 1) {
			case 1:
				farbe = FarbEnum.ROT;
				break;
			case 2:
				farbe = FarbEnum.BLAU;
				break;
			case 3:
				farbe = FarbEnum.GRUEN;
				break;
			case 4:
				farbe = FarbEnum.GELB;
				break;
			}
			for (int j = 0; j < 4; j++) {
				endfelder[i][j] = new Spielfeld("E" + (j + 1) + " " + farbe,
						this);
			}
		}
	}

	/**
	 * Getter-Methode fuer Startfelder
	 * 
	 * @return startfeld
	 */
	public  Spielfeld[][] getStartfelder() {
		return this.startfelder;
	}

	/**
	 * Setter-Methode fuer Startfelder
	 * 
	 * @param startfelder
	 *            Das Startfeld
	 */
	public  void setStartfelder(Spielfeld[][] startfelder) {
		this.startfelder = startfelder;
	}

	/**
	 * Getter-Methode fuer Endfeld
	 * 
	 * @return endfeld
	 */
	public  Spielfeld[][] getEndfelder() {
		return this.endfelder;
	}

	/**
	 * Versucht, den gewuenschten Zug auszufuehren und gibt das Ergebnis zurueck 
	 * @param gewuerfelteZahl Die Zahl die der Spieler gewuerfelt hat
	 * @param figur Die Figur, die Bewegt werden soll
	 * @return Das Ergebnis des Ziehversuches
	 */
	public ZugErgebnis zug(int gewuerfelteZahl, Spielfigur figur) {
		
		Spielfeld feldDerFigur = figur.getSpielfeld();
		
		if(gewuerfelteZahl == 6 && feldDerFigur.isStartfeld()){
			
			return figurRausziehen(figur);
		} else if (gewuerfelteZahl > 3 && feldDerFigur.isEndfeld()) {
			
			// Kann nicht sein
			return new ZugErgebnis(false, false, null, false, null, null, "Ungueltiger Zug!");
		} else if (!feldDerFigur.isEndfeld() && !feldDerFigur.isStartfeld()) {
			
			// Figur steht auf einem ganz normalen Feld
			int nummerDesFeldes = Integer.parseInt(feldDerFigur.getID());
			nummerDesFeldes += gewuerfelteZahl;
			
			int feldVorEndfeldNummer = Integer.parseInt(figur.getSpieler().getFeldvorEndfeld());
			
			if (nummerDesFeldes > feldVorEndfeldNummer) {
				// Hier koentte er noch in die Endfelder ziehen wollen
				
				return figurInEndfeldBringen(figur, gewuerfelteZahl);
				
			} else {
				// Der normalste Zug der Welt
				Spielfeld zielFeld = findeFeldDurchID(String.format("%d", nummerDesFeldes));
				return ganzNormalerZug(feldDerFigur, zielFeld);
			}
		} else {
			// Ist in Endfeld und will weiter
			return figurImEndFeldBewegen(figur, gewuerfelteZahl);
		}
	}


	private ZugErgebnis figurImEndFeldBewegen(Spielfigur figur,
			int gewuerfelteZahl) {
		
		Spielfeld feldDerFigur = figur.getSpielfeld();
		
		if (feldDerFigur.isEndfeld()) {
			String id = feldDerFigur.getID();
			
			String[] teile = id.split(" ");
			
			if (teile[0].length() == 2) {
				int feldNummer = Integer.parseInt(teile[0].substring(1));
				
				int zielFeldNummer = feldNummer + gewuerfelteZahl;
				
				if (zielFeldNummer > 4) {
					return new ZugErgebnis(false, false, null, false, null, null, "Kann nicht im Endfeld vorruecken");
				} else {
					Spielfeld[] spielerEndfelder = endfelder[figur.getFarbe().ordinal()];
					
					
					for (int i = feldNummer + 1; i < zielFeldNummer; ++i) {
						if (spielerEndfelder[i].getFigurAufFeld() != null) {
							return new ZugErgebnis(false, false, null, false, null, null, "Eine Figur steht im Weg");
						}
					}
					
					// Alle Felder frei
					int zielFeldIndex = zielFeldNummer - 1;
					
					figurBewegen(figur, spielerEndfelder[zielFeldIndex]);
					
					Spielfigur[] neueFig = new Spielfigur[1];
					
					boolean allesVoll = true;
					for (int i = 0; i < spielerEndfelder.length; ++i) {
						if (spielerEndfelder[i].getFigurAufFeld() == null) {
							allesVoll = false;
						}
					}
					
					return new ZugErgebnis(true, true, neueFig, 
							allesVoll, figur.getSpieler().getName(), figur.getFarbe(), 
							"Figur wurde bewegt");
					
				}
			}
		}
		return new ZugErgebnis(false, false, null, false, null, null, "Etwas lief schief ;)"); 
	}

	private ZugErgebnis figurInEndfeldBringen(Spielfigur figur,
			int gewuerfelteZahl) {
		
		int feldNummer = Integer.parseInt(figur.getSpielfeld().getID());
		int zielFeldNummer = feldNummer + gewuerfelteZahl;
		
		int rausZiehNummer = Integer.parseInt(figur.getSpieler().getRausZiehFeld());
		
		int endFeldNummer = zielFeldNummer - rausZiehNummer;
		int endFeldIndex = endFeldNummer - 1;
		
		if (endFeldIndex < 0 || endFeldIndex > 3) {
			throw new IndexOutOfBoundsException();
		}
		
		Spielfeld[] spielerEndfelder = this.endfelder[figur.getFarbe().ordinal()];
		
		for (int i = 0; i <= endFeldIndex; i++) {
			if (spielerEndfelder[i].getFigurAufFeld() != null) {
				return new ZugErgebnis(false, false, null, false, null, null, "Eine Figur steht im Weg");
			}
		}
		
		// Hier sind alle Endfelder bis zum Zielfeld leer
		figurBewegen(figur, spielerEndfelder[endFeldIndex]);
		Spielfigur[] neueFig = new Spielfigur[1];
		neueFig[0] = figur;
		
		boolean allesVoll = true;
		// Pruefen,ob ein Feld noch leer
		for (int i = 0; i < spielerEndfelder.length; ++i) {
			if (spielerEndfelder[i].getFigurAufFeld() == null) {
				allesVoll = false;
			}
		}
		return new ZugErgebnis(true, true, neueFig, 
				allesVoll, figur.getSpieler().getName(), figur.getFarbe(), 
				"Figur wurde ins Endfeld bewegt");
		
		
	}

	private ZugErgebnis ganzNormalerZug(Spielfeld feldDerFigur,
			Spielfeld zielFeld) {
		
		Spielfigur gegnerFigur = zielFeld.getFigurAufFeld();
		Spielfigur eigeneFigur = feldDerFigur.getFigurAufFeld();
		
		if (gegnerFigur != null && 
				(eigeneFigur.getFarbe() != gegnerFigur.getFarbe())) {
			// Figur schlagen
			
			figurSchlagen(eigeneFigur, gegnerFigur);
			
			Spielfigur[] neueFig = new Spielfigur[2];
			neueFig[0] = eigeneFigur;
			neueFig[1] = gegnerFigur;
			
			return new ZugErgebnis(true, true, neueFig, false, null, null, "Figur geschlagen!");
		} else if (gegnerFigur != null &&
				(eigeneFigur.getFarbe() == gegnerFigur.getFarbe())) {
			
			// Darf keine eigenen Figuren schlagen
			return new ZugErgebnis(false, false, null, false, null, null, "Eigene Figur steht dort bereits");
		} else {
			
			// Zielfeld ist leer
			figurBewegen(eigeneFigur, zielFeld);
			
			Spielfigur[] neueFig = new Spielfigur[1];
			neueFig[0] = eigeneFigur;
			
			return new ZugErgebnis(true, true, neueFig, false, null, null, "Figur wurde bewegt");
		}
	}

	private ZugErgebnis figurRausziehen(Spielfigur figur) {
		
		Spielfeld feldDerFigur = figur.getSpielfeld();
		Spielfeld rausZiehFeld = findeFeldDurchID(figur.getSpieler().getRausZiehFeld());
		
		Spielfigur figurAufRausZiehFeld = rausZiehFeld.getFigurAufFeld();
		if(figurAufRausZiehFeld != null && figurAufRausZiehFeld.getFarbe() != figur.getFarbe()){
			// Das Rausziehfeld ist belegt...
			
			figurSchlagen(figur, figurAufRausZiehFeld);
			
			// Das Ergbnis zurueckgeben
			Spielfigur [] geaenderteFiguren = new Spielfigur [2];
			geaenderteFiguren [0] = figur;
			geaenderteFiguren [1] = figurAufRausZiehFeld;
			return new ZugErgebnis(true, false, geaenderteFiguren, false, null, null, "Zug gueltig");
		} else if (figurAufRausZiehFeld != null && 
					(figurAufRausZiehFeld.getFarbe() == figur.getFarbe())){
			// Eine seiner eigenen Figuren steht noch auf dem Feld
			return new ZugErgebnis(false, false, null, false, null, null, "Eine Figur der selben Farbe steht bereits auf dem Rausziehfeld");
			
		} else {
			// Das Rausziehfeld ist frei
			
			// Figur rausziehen
			rausZiehFeld.setFigurAufFeld(figur);
			feldDerFigur.setFigurAufFeld(null);
			figur.setSpielfeld(rausZiehFeld);
			
			// Ergbnis zurrueckgeben
			Spielfigur [] geaenderteFiguren = new Spielfigur [1];
			geaenderteFiguren[0] = figur;
			return new ZugErgebnis(true, false, geaenderteFiguren, false, null, null, "Spielfigur ist raus");
		}
	}
	
	private void figurSchlagen(Spielfigur figur, Spielfigur gegnerFigur) {
		
		String idGegnerStart = "S" + (gegnerFigur.getID() + 1) + " " + gegnerFigur.getFarbe();
		Spielfeld startFeldDesGegners = findeFeldDurchID(idGegnerStart);
		
		// Figur auf Gegnerfeld
		Spielfeld ziel = gegnerFigur.getSpielfeld();
		figur.setSpielfeld(ziel);
		ziel.setFigurAufFeld(figur);
		
		// Gegner zuruecksetzen
		startFeldDesGegners.setFigurAufFeld(gegnerFigur);
		gegnerFigur.setSpielfeld(startFeldDesGegners);
		
	}
	
	private void figurBewegen(Spielfigur figur, Spielfeld zielFeld) {
		
		figur.getSpielfeld().setFigurAufFeld(null);
		figur.setSpielfeld(zielFeld);
		zielFeld.setFigurAufFeld(figur);
	}

	/**
	 * Setzt die gewünschte Figur auf das gewünschte Feld, falls das Zielfeld
	 * leer ist. Führt keine weiteren Regelprüfungen durch.
	 * 
	 * @param figur
	 *            Die Figur, die versetzt werden soll
	 * @param zielFeldID
	 *            Die ID des Feldes auf das die Figur gesetzt werden soll
	 * @return True falls erfolgreich, sonst false
	 */
	public boolean debugSetPos(Spielfigur figur, String zielFeldID) {
		Spielfeld zielFeld = this.findeFeld(zielFeldID);

		if (zielFeld != null && figur != null) {

			if (zielFeld.getFigurAufFeld() == null) {
				figur.getSpielfeld().setFigurAufFeld(null);
				zielFeld.setFigurAufFeld(figur);
				figur.setSpielfeld(zielFeld);

				return true;
			}
		}

		return false;
	}

	/**
	 * Findet das gesuchte Feld in den Spielfeldarrays
	 * 
	 * @param ID
	 *            ID des gesuchten Feldes
	 * @return Das gefundene Feld oder null falls es nicht gefunden wurde
	 */
	private Spielfeld findeFeld(String ID) {
		for (int i = 0; i < regulaereFelder.length; i++) {
			if (regulaereFelder[i].getID().equals(ID))
				return regulaereFelder[i];
		}

		for (int i = 0; i < startfelder.length; i++) {
			for (int j = 0; j < startfelder[i].length; j++) {
				if (startfelder[i][j].getID().equals(ID))
					return startfelder[i][j];
			}
		}
		for (int i = 0; i < endfelder.length; i++) {
			for (int j = 0; j < endfelder[i].length; j++) {
				if (endfelder[i][j].getID().equals(ID))
					return endfelder[i][j];
			}
		}

		return null;
	}
	

	/**
	 * Zum testen
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		Spielbrett s = new Spielbrett();

		for (int i = 0; i < s.regulaereFelder.length; i++) {
			System.out.print(s.regulaereFelder[i].toString() + " ");
		}

		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(s.startfelder[i][j] + " | ");
			}
		}
		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(s.endfelder[i][j] + " | ");
			}
		}
	}

	/**
	 * Durchsucht die Spielfelder nach der gwuenschten ID
	 * @param feldID Die ID des gesuchten Feldes
	 * @return Das Feld, falls es gefunden wurde, sonst null
	 */
	public Spielfeld findeFeldDurchID(String feldID) {
		if (Spielfeld.isFeldIDgueltig(feldID)) {
			for (int i = 0; i < regulaereFelder.length; i++) {
				if (regulaereFelder[i].getID().equals(feldID)) {
					return regulaereFelder[i];
				}
			}
			for (int i = 0; i < startfelder.length; i++) {
				for (int j = 0; j < startfelder[i].length; j++) {
					if (startfelder[i][j].getID().equals(feldID)) {
						return startfelder[i][j];
					}
				}
			}
			for (int i = 0; i < endfelder.length; i++) {
				for (int j = 0; j < endfelder.length; j++) {
					if (endfelder[i][j].getID().equals(feldID)) {
						return endfelder[i][j];
					}
				}
			}
		} else {
			throw new RuntimeException("Feld nicht gefunden!");
		}
		return null;
	}
}
