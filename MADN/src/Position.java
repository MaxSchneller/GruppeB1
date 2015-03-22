/**
 * Klasse fuer die Position
 * @author Gruppe B1
 */
public class Position {
	
	private Integer x;
	private Integer y;
	
	/**
	 * Konstruktor
	 * @param x Variable mit dem Namen x, vom Typ Integer
	 * @param y Variable mit dem Namen y, vom Typ Integer
	 */
	public Position(Integer x, Integer y){
		this.x = new Integer(x.intValue());
		this.y = new Integer(y.intValue());
	}
	
	/**
	 * Getter-Methode der Variable x
	 * @return gibt den x-Wert zurück
	 */
	public Integer getX(){
		return new Integer(x.intValue());
	}
	
	/**
	 * Getter-Methode der Variable y
	 * @return gibt den y-Wert zurück
	 */
	public Integer getY(){
		return new Integer(y.intValue());
	}
	
	/**
	 * Setter-Methode der Variable x
	 * @param x Variable mit dem Namen x, vom Typ Integer
	 */
	public void setX(Integer x){
        this.x = new Integer(x.intValue());
	}
	
	/**
	 * Setter-Methode der Variable y
	 * @param y Variable mit dem Namen y, vom Typ Integer
	 */
	public void setY(Integer y){
        this.y = new Integer(y.intValue());
	}
}
