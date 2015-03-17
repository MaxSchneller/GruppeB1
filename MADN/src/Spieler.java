
public class Spieler {
	private String name;
	private FarbEnum farbe;
	
	public Spieler(String name, FarbEnum farbe){
		setName(name);
		this.farbe = farbe;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
