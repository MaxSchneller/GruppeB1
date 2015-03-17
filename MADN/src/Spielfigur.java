
public class Spielfigur {
	
	private FarbEnum farbe;
	private Position position;
	
	public Spielfigur(FarbEnum farbe, Position position){
		setPosition(position);
		setFarbe(farbe);
	}
	
	private void setFarbe(FarbEnum farbe){
		this.farbe = farbe;
	}
	
	 public FarbEnum getFarbe(){
         return farbe;
	 }
	
	public void setPosition(Position position){
        this.position = new Position(position.getX(), position.getY());
	}
	
	public Position getPosition(){
        return new Position(this.position.getX(), this.position.getY());
}
}
