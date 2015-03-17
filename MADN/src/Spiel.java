import java.util.ArrayList;


public class Spiel implements iBediener {


	private ArrayList<Spieler> spieler;
	private Spieler spielerAmZug;
	
	public Spiel() {
		this.spieler = new ArrayList<Spieler>();
	}

	
	@Override
	public void spielerHinzufügen(String name, FarbEnum farbe) throws SpielerFarbeVorhandenException {
		
		for (Spieler spieler : this.spieler) {
		
			if (spieler.getFarbe() == farbe)
				throw new SpielerFarbeVorhandenException(farbe);
		}
		
		spieler.add(new Spieler(name, farbe, null));
	}


	@Override
	public void spielerEntfernen(FarbEnum farbeDesSpielers) throws SpielerNichtGefundenException {
		
		int indexZuEntfernen = -1;
		for (int i = 0; i < this.spieler.size(); i++) {
			if (this.spieler.get(i).getFarbe() == farbeDesSpielers) {
				indexZuEntfernen = i;
				break;
			}
		}
		
		if (indexZuEntfernen == -1)
			throw new SpielerNichtGefundenException(farbeDesSpielers);
		else
			this.spieler.remove(indexZuEntfernen);
	}

}
