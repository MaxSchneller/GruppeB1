/**
 * 
 * Das Spielfeld, auf dem die Spielfiguren stehen
 *
 */
public class Spielbrett {
	
	Spielfeld [] spielBrett;
	Spielfeld [][] start;
	Spielfeld [][] ziel;
	
	public Spielbrett(){
		spielBrett = new Spielfeld [40];
		start = new Spielfeld [4][4];
		ziel = new Spielfeld [4][4];
	}
	
	
}
