import java.util.ArrayList;


public class Spielbrett {

	private static ArrayList <Spielfeld> regulaer = new ArrayList <Spielfeld>(); 
	Spielfeld feld;
	
	public Spielbrett(ArrayList<Spielfeld> regulaer){
		setSpielfeldRegulaer(regulaer);
	}

	public void setSpielfeldRegulaer(ArrayList<Spielfeld> regulaer){
		for(int i = 0; i < 40; i++){
			regulaer.add(new Spielfeld(i +1));
		}
	}
	
	public static void main(String[] args) {
		Spielbrett brett1 = new Spielbrett(regulaer);
		System.out.println(regulaer.toString());
	}
	
//	Spielfeld [] spielBrett;
//	Spielfeld [][] start;
//	Spielfeld [][] ziel;
//	
//	public Spielbrett(){
//		spielBrett = new Spielfeld [40];
//		start = new Spielfeld [4][4];
//		ziel = new Spielfeld [4][4];
//	}
//	
//	
}
