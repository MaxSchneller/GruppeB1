

/**
 * Die Klasse Spielbrett
 * @author Gruppe B1
 *
 */
public class Spielbrett {
	
	Spielfeld feld = new Spielfeld("1");
	ListElement startElem = new ListElement (feld);
	
	static Spielfeld [][] startfelder = new Spielfeld [4][4];
	static Spielfeld [][] endfelder = new Spielfeld [4][4];

	
	public Spielbrett(){
		setStartfelderID();
		setEndfelderID();
		
		for (int i = 2; i <= 40; i++) {
			Spielfeld feld = new Spielfeld(String.format("%d", i));
			addLast(feld);
		}
	}

	/**
	 * Fügt Objekt an der nächsten freien Stelle dem Spielbrett hinzu
	 * @param feld Das einzufügende Spielfeld
	 */
	public void addLast(Spielfeld feld){
		ListElement newElem = new ListElement(feld);
		ListElement lastElem = getLastElem();
		lastElem.setNextElem(newElem);
	}
	
	/**
	 * Fügt ein Feld an einer gewünschten Stelle ein
	 * @param prevFeld
	 * @param feld
	 */

	public void insertAfter(Spielfeld prevFeld, Spielfeld feld){
		ListElement newElem, nextElem, pointerElem;
		pointerElem = startElem.getNextElem();
		while(pointerElem != null && !pointerElem.getSpielfeld().equals(prevFeld)){ 
            pointerElem = pointerElem.getNextElem(); 
        } 
        newElem = new ListElement(feld); 
        nextElem = pointerElem.getNextElem(); 
        pointerElem.setNextElem(newElem); 
        newElem.setNextElem(nextElem);
	}
	
	/**
	 * Läuft durch die Liste und sucht das übergebene Spielfeld
	 * @param feld
	 * @return true/ false Je nachdem ob das Feld gefunden wurde oder nicht
	 */
	
	public boolean find(Spielfeld feld){ 
        ListElement le = startElem; 
        while (le != null){ 
            if(le.getSpielfeld().equals(feld)) 
            return true; 
            le = le.nextElem; 
        } 
        return false; 
    } 

	
	/**
	 * Spielfeld aus der Liste löschen
	 * @param feld Das Objekt das gelöscht werden soll
	 * @return true/false
	 */
	
	public void delete(Spielfeld feld){ 
        ListElement le = startElem; 
        while (le.getNextElem() != null && !le.getSpielfeld().equals(feld)){ 
	           if(le.getNextElem().getSpielfeld().equals(feld)){ 
	               if(le.getNextElem().getNextElem()!=null) 
	                   le.setNextElem(le.getNextElem().getNextElem()); 
	               else{ 
	                   le.setNextElem(null); 
	                   break; 
	               } 
	           } 
	           le = le.getNextElem(); 
        } 
     } 
	
	public ListElement getFirstElem() { 
		return startElem; 
	} 
	
	/**
	 * Läuft von vorne durch die Liste und gibt das letzte Element aus
	 * @return
	 */
	
    public ListElement getLastElem() { 
        ListElement le = startElem; 
        while(le.getNextElem() != null){ 
            le = le.getNextElem(); 
        } 
	    return le; 
	} 
	
    /**
     * Läuft durch die Liste und gibt diese aus
     */
    
    public void writeList() { 
        ListElement le = startElem; 
        while(le != null){ 
            System.out.print(le.getSpielfeld() + " "); 
            le = le.getNextElem(); 
        } 
    }
    
    /**
     * setzt Spielfelder in das Array und weißt ID zu
     */
    
    public void setStartfelderID(){
    	String farbe = null; 
    	for (int i = 0; i < 4; i++) {
    		switch(i+1){
    		case 1: farbe = "rot";
    				break;
    		case 2: farbe = "blau";
    				break;
    		case 3: farbe = "grün";
    				break;
    		case 4: farbe = "gelb";
    				break;
    		}
    		for (int j = 0; j < 4; j++) {
    			startfelder[i][j] = new Spielfeld("S" + (j+1) + " " + farbe);
			}
		}
    }
    
    /**
     * setzt Spielfelder in das Array und weißt ID zu
     */
    
	private void setEndfelderID() {
		String farbe = null; 
    	for (int i = 0; i < 4; i++) {
    		switch(i+1){
    		case 1: farbe = "rot";
    				break;
    		case 2: farbe = "blau";
    				break;
    		case 3: farbe = "grün";
    				break;
    		case 4: farbe = "gelb";
    				break;
    		}
    		for (int j = 0; j < 4; j++) {
    			endfelder[i][j] = new Spielfeld("E" + (j+1) + " " + farbe);
			}
		}
	}
	
    /**
     * Zum testen
     * @param args
     */
	
    public static void main(String[] args) {
		Spielbrett brett = new Spielbrett();
		brett.writeList();
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