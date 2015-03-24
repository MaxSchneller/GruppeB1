
public class SpielTest implements iBediener{

	public static void main(String[] args) throws SpielerFarbeVorhandenException {
		Spiel s1=new Spiel();
		
		s1.spielerHinzufuegen("Max", FarbEnum.blau);
		s1.spielerHinzufuegen("Christoph", FarbEnum.gruen);
		s1.spielerHinzufuegen("Markus", FarbEnum.gelb);
		s1.spielerHinzufuegen("Martin", FarbEnum.rot);
		
		

	}

	@Override
	public void spielerHinzufuegen(String name, FarbEnum farbe)
			throws SpielerFarbeVorhandenException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spielerEntfernen(FarbEnum farbeDesSpielers)
			throws SpielerNichtGefundenException {
		// TODO Auto-generated method stub
		
	}

}
