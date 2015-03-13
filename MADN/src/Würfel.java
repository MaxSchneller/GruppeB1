
public class Würfel {

	public int werfen() {
		int zufall = (int) (6 * Math.random()) + 1;
		System.out.println("Es wurde " + zufall + " gewuerfelt!");
		return zufall;
		}
}
