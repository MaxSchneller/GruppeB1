package Speichern_Laden;

import java.io.FileNotFoundException;
import java.io.IOException;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Spiel.Spiel;

public interface iDatenzugriff {

	public void spielSpeichern (Spiel spiel) throws IOException;
	public Spiel spielLaden() throws ClassNotFoundException, FileNotFoundException, IOException, SpielerFarbeVorhandenException, SpielerNichtGefundenException;
}
