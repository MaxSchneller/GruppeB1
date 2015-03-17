import java.util.ArrayList;


public class Spielbrett {

	private static ArrayList <Spielfeld> regulaer = new ArrayList <Spielfeld>(); 
	private static ArrayList <Spielfeld> start = new ArrayList <Spielfeld>();
	private static ArrayList <Spielfeld> ziel = new ArrayList <Spielfeld>();
	
	public Spielbrett(ArrayList<Spielfeld> regulaer, ArrayList <Spielfeld> start,ArrayList <Spielfeld> ziel){
		setSpielfeldRegulaer(regulaer);
		setSpielfeldStart(start);
		setSpielfeldZiel(ziel);
	}

	public void setSpielfeldRegulaer(ArrayList<Spielfeld> regulaer){
		for(int i = 0; i < 40; i++){
			regulaer.add(new Spielfeld(i +1));
		}
	}
	
	public void setSpielfeldStart(ArrayList <Spielfeld> start){
		for(int i = 0; i < 4; i++){
			start.add(new Spielfeld("S" + i));
		}
	}
	
	public void setSpielfeldZiel(ArrayList <Spielfeld> ziel){
		
	}
	
//	public static void main(String[] args) {
//		Spielbrett brett1 = new Spielbrett(regulaer);
//		System.out.println(regulaer.toString());
//	}
		
}
