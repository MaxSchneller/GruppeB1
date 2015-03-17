import java.util.*;

public class Spielfeld {
	
	private int ID;
	private String IDstart;
	private Spielfigur figur;
	private int counter = 0;
	
	public Spielfeld(int ID){
		this.ID = ID;
	}
	
	public Spielfeld(String IDstart){
		this.IDstart = IDstart;
	}
	
//	public void setID(int ID){
//		counter++;
//		this.ID = counter;
//	}
	
	
//	private ArrayList <Spielfeld> regulaer = new ArrayList <Spielfeld>(); 
//	private static String [][] start = new String [4][4];
//	private static String [][] ziel = new String [4][4];
//	
//	public Spielfeld(int regulaerID, Spielfigur figur){
//		setSpielFelderID(spielFelder);
//		setStartID(start);
//		setZielID(ziel);
//	}
//	
//	private static void setSpielFelderID(Spielfeld [] spielFelder){
//		int counter = 1;
//		
//		for(int i = 0; i < 40; i++){
//			spielFelder[i] = new Spielfeld();
//			counter++;
//		}
//	}
//	
//	private static void setStartID(String [][] start){
//		for(int i = 0; i < 4; i++){
//			for(int j = 0; j < 4; j++){
//				
//			}
//		}
//	}
//	
//	private static void setZielID(String [][] ziel){
//		
//	}
	
//	public static void main(String[] args) {
//		setSpielFelderID(spielFelder);
//		for(int i = 0; i<40; i++){
//			System.out.print(spielFelder[i]+ " ");
//		}
//	}
}
