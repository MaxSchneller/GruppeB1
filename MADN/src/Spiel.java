import java.util.ArrayList;

/**
 * Die Klasse Spiel
 * @author Gruppe B1
 */
public class Spiel implements iBediener {


	private ArrayList<Spieler> spieler;
	private Spieler spielerAmZug;
	
	/**
	 * Konstruktor des Spiels
	 */
	public Spiel() {
		this.spieler = new ArrayList<Spieler>();
	}

	
	/**
	 * Überschreiben der Methode spielerHinzufügen, welche einen Spieler hinzufügt, der einen Namen, Farbe und einen Würfel besitzt
	 */
	@Override
	public void spielerHinzufuegen(String name, FarbEnum farbe) throws SpielerFarbeVorhandenException {
		
		for (Spieler spieler : this.spieler) {
		
			if (spieler.getFarbe() == farbe)
				throw new SpielerFarbeVorhandenException(farbe);
		}
		
		spieler.add(new Spieler(name, farbe, null));
	}


	/**
	 * Überschreiben der Methode spielerEntfernen, welche einen Spieler enfernt.
	 */
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
