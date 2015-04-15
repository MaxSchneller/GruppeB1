package Speichern_Laden;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Spiel.Spiel;

public class DatenzugriffSerialisiert implements Serializable, iDatenzugriff {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void spielSpeichern(Spiel spiel){
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(new FileOutputStream("spiel.ser"));
			oos.writeObject(spiel);
		} catch (FileNotFoundException e){
			System.err.println("Konnte spiel.ser nicht erzeugen.");
		} catch (IOException e){
			System.err.println("Fehler bei der Ein/Ausgabe: " + e);
		} finally{
			try{
				oos.close();
			} catch (Exception e){
				System.err.println("Fehler beim schließen.");
			}
		}
	}

	@Override
	public Spiel spielLaden() throws ClassNotFoundException {
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream("spiel.ser"));
			Object o = ois.readObject();
			if(o instanceof Spiel){
				Spiel spiel = (Spiel)o;
				return spiel;
			} else {
				throw new RuntimeException("Fehler!");
			}
		} catch (FileNotFoundException e){
			System.err.println("Konnte spiel.ser nicht laden.");
		} catch (IOException e){
			System.err.println("Fehler bei der Ein/Ausgabe: " + e);
		} finally{
			try{
				ois.close();
			} catch (Exception e){
				System.err.println("Fehler beim schließen.");
			}
		}
		return null;
	}
	
}
