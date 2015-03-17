
public class Position {
	private Integer x;
	private Integer y;
	
	public Position(Integer x, Integer y){
		this.x = new Integer(x.intValue());
		this.y = new Integer(y.intValue());
	}
	
	public Integer getX(){
		return new Integer(x.intValue());
	}
	
	public Integer getY(){
		return new Integer(y.intValue());
	}
	
	public void setX(Integer x){
        this.x = new Integer(x.intValue());
	}
	
	public void setY(Integer y){
        this.y = new Integer(y.intValue());
	}
}
