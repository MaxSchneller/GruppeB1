
public class Spieler {
	private String name;
	private FarbEnum farbe;
	private Wuerfel w;
	
	public Spieler(String name, FarbEnum farbe, Wuerfel w){
		setName(name);
		this.setFarbe(farbe);
		getWuerfel();
		
	}
	
	public Wuerfel getWuerfel(){
		return w;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public FarbEnum getFarbe() {
		return farbe;
	}

	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

}

 