
public class ZugErgebnis {
	
	private boolean gueltig;
	private boolean zugBeendet;
	private Spielfigur[] geaenderteFiguren=new Spielfigur[2];
	private boolean spielGewonnen;
	private String gewinnerName;
	private FarbEnum gewinnerFarbe;
	private String nachricht;
	
	public ZugErgebnis(boolean gueltig, boolean zugBeendet, Spielfigur[] geanderteFiguren, boolean spielGewonnen, String gewinnerName,FarbEnum gewinnerFarbe, String nachricht ){
		this.gueltig=gueltig;
		this.zugBeendet=zugBeendet;
		this.geaenderteFiguren=geanderteFiguren;
		this.spielGewonnen=spielGewonnen;
		this.gewinnerName=gewinnerName;
		this.gewinnerFarbe=gewinnerFarbe;
		this.nachricht=nachricht;
		
		if(zugBeendet && !gueltig){
			throw new RuntimeException("Zug ungueltig!");
			}
		if(gueltig && geanderteFiguren == null){
			throw new RuntimeException("Zug ungueltig!");
		}
		if(spielGewonnen){
			if(gewinnerName == null || gewinnerFarbe == null){
				throw new RuntimeException("Zug ungueltig!");
			}
		}
			
		}
	
	
	
	
	
	
	
	
	public boolean isGueltig() {
		return gueltig;
	}
	public boolean isZugBeendet() {
		return zugBeendet;
	}
	public Spielfigur[] getGeaenderteFiguren() {
		return geaenderteFiguren;
	}
	public boolean isSpielGewonnen() {
		return spielGewonnen;
	}
	public String getGewinnerName() {
		return gewinnerName;
	}
	public FarbEnum getGewinnerFarbe() {
		return gewinnerFarbe;
	}
	public String getNachricht() {
		return nachricht;
	}
	
	
	
}
