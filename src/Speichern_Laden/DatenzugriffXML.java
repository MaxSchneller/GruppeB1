package Speichern_Laden;

import java.io.FileNotFoundException;
import java.io.IOException;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;

public class DatenzugriffXML implements iDatenzugriff {

	@Override
	public void spielSpeichern(Object spiel, String dateipfad)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object spielLaden(String dateipfad) throws ClassNotFoundException,
			FileNotFoundException, IOException, SpielerFarbeVorhandenException,
			SpielerNichtGefundenException {
		// TODO Auto-generated method stub
		return null;
	}

}
