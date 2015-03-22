import java.util.ArrayList;

/**
 * Die Klasse Spielbrett
 * @author Gruppe B1
 *
 */
public class Spielbrett {

	private static ArrayList <Spielfeld> regulaer = new ArrayList <Spielfeld>(); 
	private static ArrayList <Spielfeld> start = new ArrayList <Spielfeld>();
	private static ArrayList <Spielfeld> ziel = new ArrayList <Spielfeld>();
	
	/**
	 * Der konstruktor der klasse Spielbrett
	 * @param regulaer Dies ist eine ArrayList mit dem Namen regulaer, in der alle 40 Spielfelder vorhanden sind.
	 * @param start Dies ist eine ArrayList mit dem Namen start, welche die vier Startfelder der jeweiligen Farben verwaltet.
	 * @param ziel Dies ist eine ArrayList mit dem Namen ziel, welche die vier Endfelder der jeweiligen Farben verwaltet.
	 */
	public Spielbrett(ArrayList<Spielfeld> regulaer, ArrayList <Spielfeld> start,ArrayList <Spielfeld> ziel){
		setSpielfeldRegulaer(regulaer);
		setSpielfeldStart(start);
		setSpielfeldZiel(ziel);
	}

	/**
	 * Setter-Methode fuer das Setzen auf Spielfeld 
	 * @param regulaer Dies ist eine ArrayList mit dem Namen regulaer, in der alle 40 Spielfelder vorhanden sind.
	 */
	public void setSpielfeldRegulaer(ArrayList<Spielfeld> regulaer){
		for(int i = 0; i < 40; i++){
			regulaer.add(new Spielfeld(i +1));
		}
	}
	
	/**
	 * Setter-Methode fuer das Setzen auf Startfelder
	 * @param start Dies ist eine ArrayList mit dem Namen start, welche die vier Startfelder der jeweiligen Farben verwaltet.
	 */
	public void setSpielfeldStart(ArrayList <Spielfeld> start){
		for(int i = 0; i < 4; i++){
			start.add(new Spielfeld("S" + i));
		}
	}
	
	/**
	 * Setter-Methode fuer das Setzen auf Endfelder
	 * @param ziel Dies ist eine ArrayList mit dem Namen ziel, welche die vier Endfelder der jeweiligen Farben verwaltet.
	 */
	public void setSpielfeldZiel(ArrayList <Spielfeld> ziel){
		
	}
	
//	public static void main(String[] args) {
//		Spielbrett brett1 = new Spielbrett(regulaer);
//		System.out.println(regulaer.toString());
//	}
		
}
