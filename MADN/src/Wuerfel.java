/**
 * Die Wuerfelklasse
 * @author Gruppe B1
 *
 */
class Wuerfel {
	/** 
	 * @return Der Returnwert ist die gewuerfelte Zahl, zwieschen 1 und 6.
	 */
	public int werfen (){
		int zahl = (int) (6 * Math.random()) + 1;
		System.out.println("Sie haben mit dem Wuerfel die Zahl " + zahl + " geworfen.");
		return zahl;
	}
}
