/**
 * Klasse Spielfeld
 * @author Gruppe B1
 *
 */
public class Spielfeld {
	private Spielbrett brett;
	private String ID;
	
	/**
	 * Konstruktor Spielfeld
	 * @param ID Die ID des Spielfelds 
	 * @param brett Das Spielbrett
	 */
	public Spielfeld(String ID, Spielbrett brett){
		setID(ID);
		setSpielbrett(brett);
	}
	
	/**
	 * Eine Spielfigur auf Feld setzen
	 * @param figur ist eine Spielfigur
	 */
	public void figurAufFeld(Spielfigur figur){
		
	}
	
	/**
	 * Setter-Methode fuer Spielbrett
	 * @param brett Das Spielbrett
	 */
	private void setSpielbrett(Spielbrett brett){
		if(brett == null){
			throw new RuntimeException("Spielbrett darf nicht leer sein");
		} else{
		this.brett = brett;
		}
	}
	
	/**
	 * Setter-methode fuer ID
	 * @param ID Die ID des Spielfelds 
	 */
	private void setID(String ID){
		if(ID == null || ID.isEmpty()){
			throw new RuntimeException("ID darf nicht leer sein");
		} else if(ID.length() <= 2){
			int id = Integer.parseInt(ID);
			if(id > 40 || id < 1){
				throw new RuntimeException("ID muss zwischen 1 und 40 liegen");
			}
			
			this.ID = ID;
		} else {
			if(!ID.contains(" ")){
				throw new RuntimeException("ID nicht gültig");
			}
			String[] teile = ID.split(" ");
			
			if (teile.length != 2) {
				throw new RuntimeException("ID hat zu viele Leerzeichen!");
			}
			
			String idTeil = teile[0];
			String farbTeil = teile[1];
			
			if (idTeil.length() != 2 ||
					!(idTeil.startsWith("S") || 
					idTeil.startsWith("E"))) {
				throw new RuntimeException("ID ist ungültig");
			}
			
			int startEndfeldID = Integer.parseInt(idTeil.substring(1));
			if (startEndfeldID < 1 || startEndfeldID > 4) {
				throw new RuntimeException("ID ist ungültig");
			}
			
			if (!(farbTeil.equalsIgnoreCase("BLAU") ||
					farbTeil.equalsIgnoreCase("ROT") ||
					farbTeil.equalsIgnoreCase("GRUEN") ||
					farbTeil.equalsIgnoreCase("GELB"))) {
				throw new RuntimeException("ID ist ungültig");
			}
			
			// Hier ist die ID gültig
			this.ID = ID;
		}
	}
	
	/**
	 * Getter-Methode fuer ID
	 * @return Gibt ID zurueck
	 */
	public String getID(){
		return ID;
	}
	
	public String toString(){
		return ID;
	}
}
	
