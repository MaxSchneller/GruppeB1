
public class ListElement {
	
    Spielfeld feld; 

    ListElement nextElem; 

    public ListElement(Spielfeld feld) { 
        this.feld = feld; 
        nextElem = null; 
    } 

    public void setNextElem(ListElement nextElem) { 
        this.nextElem = nextElem; 
    } 

    public ListElement getNextElem() { 
        return nextElem; 
    } 

    public Spielfeld getSpielfeld() { 
        return feld; 
    } 
}
