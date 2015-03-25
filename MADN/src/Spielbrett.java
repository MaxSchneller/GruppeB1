

/**
 * Die Klasse Spielbrett
 * @author Gruppe B1
 *
 */
public class Spielbrett {
	
	private static Spielfeld[] regulaereFelder = new Spielfeld[40];
	
	private static Spielfeld [][] startfelder = new Spielfeld [4][4];
	private static Spielfeld [][] endfelder = new Spielfeld [4][4];

	/**
	 * Konstruktor des Spielbretts 
	 */
	public Spielbrett(){
		setStartfelderID();
		setEndfelderID();
		
		for (int i = 1; i <= 40; i++) {
			regulaereFelder[i-1] = new Spielfeld(String.format("%d", i), this);
		}
	}
    
    /**
     * setzt Spielfelder in das Array und weißt ID zu
     */
    
    public void setStartfelderID(){
    	FarbEnum farbe = null; 
    	for (int i = 0; i < 4; i++) {
    		switch(i+1){
    		case 1: farbe = FarbEnum.ROT;
    				break;
    		case 2: farbe = FarbEnum.BLAU;
    				break;
    		case 3: farbe = FarbEnum.GRUEN;
    				break;
    		case 4: farbe = FarbEnum.GELB;
    				break;
    		}
    		for (int j = 0; j < 4; j++) {
    			startfelder[i][j] = new Spielfeld("S" + (j+1) + " " + farbe, this);
			}
		}
    }
    
    /**
     * setzt Spielfelder in das Array und weisst ID zu
     */
    
	private void setEndfelderID() {
		FarbEnum farbe = null; 
    	for (int i = 0; i < 4; i++) {
    		switch(i+1){
    		case 1: farbe = FarbEnum.ROT;
    				break;
    		case 2: farbe = FarbEnum.BLAU;
    				break;
    		case 3: farbe = FarbEnum.GRUEN;
    				break;
    		case 4: farbe = FarbEnum.GELB;
    				break;
    		}
    		for (int j = 0; j < 4; j++) {
    			endfelder[i][j] = new Spielfeld("E" + (j+1) + " " + farbe, this);
			}
		}
	}
	
	/**
	 * Getter-Methode fuer Spielfeld
	 * @return feld
	 */
    public Spielfeld getFeld() {
		return feld;
	}

    /**
     * Setter-Methode fuer Feld
     * @param feld ist Spielfeld
     */
	public void setFeld(Spielfeld feld) {
		this.feld = feld;
	}

	/**
	 * Getter-Methode fuer Spartfelder
	 * @return startfeld
	 */
	public static Spielfeld[][] getStartfelder() {
		return startfelder;
	}

	/**
	 * Setter-Methode fuer Startfelder
	 * @param startfelder Das Startfeld
	 */
	public static void setStartfelder(Spielfeld[][] startfelder) {
		Spielbrett.startfelder = startfelder;
	}

	/**
	 * Getter-Methode fuer Endfeld
	 * @return endfeld
	 */
	public static Spielfeld[][] getEndfelder() {
		return endfelder;
	}

	/**
	 * Setter-Methode fuer Endfeld
	 * @param endfelder Das Endfeld
	 */
	public static void setEndfelder(Spielfeld[][] endfelder) {
		Spielbrett.endfelder = endfelder;
	}
	
	public Object zug(int gewuerfelteZahl, Spielfigur figur){
		return null; 
	}
	
	/**
	 * Setzt die gewünschte Figur auf das gewünschte Feld, falls das Zielfeld leer ist.
	 * Führt keine weiteren Regelprüfungen durch.
	 * @param figur Die Figur, die versetzt werden soll
	 * @param zielFeldID Die ID des Feldes auf das die Figur gesetzt werden soll
	 * @return True falls erfolgreich, sonst false
	 */
	public boolean debugSetPos(Spielfigur figur, String zielFeldID){
		Spielfeld zielFeld = this.findeFeld(zielFeldID);
		
		if (zielFeld != null && figur != null){
			
			if (zielFeld.getSpielfigur() == null) {
				figur.getSpielfeld().setSpielfigur(null);
				zielFeld.setSpielfigur(figur);
				figur.setSpielfeld(zielFeld);
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Findet das gesuchte Feld in den Spielfeldarrays
	 * @param ID ID des gesuchten Feldes
	 * @return Das gefundene Feld oder null falls es nicht gefunden wurde
	 */
	private Spielfeld findeFeld(String ID){
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
     * @param args
     */
	
    public static void main(String[] args) {
    	
    	Spielbrett s = new Spielbrett();
    	
    	for (int i = 0; i < regulaereFelder.length; i++) {
			System.out.print(regulaereFelder[i].toString() + " ");
		}
    	
    	System.out.println();
		for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			System.out.print(startfelder[i][j] + " | ");
			}
		}
		System.out.println();
		for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			System.out.print(endfelder[i][j] + " | ");
			}
		}
	}
}
