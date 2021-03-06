package Speichern_Laden;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import sun.awt.FwDispatcher;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Spiel.SpielBean;

public class DatenzugriffXML implements iDatenzugriff {

	@Override
	public void spielSpeichern(Object spiel, String dateipfad) throws IOException, JAXBException {
		
		
		FileWriter fw = null;
		
		if (spiel instanceof SpielBean) {
			SpielBean s = (SpielBean)spiel;
		
			try{
				JAXBContext context = JAXBContext.newInstance( SpielBean.class );
				Marshaller m = context.createMarshaller();
				m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
				//m.marshal( s, System.out );
				fw=new FileWriter(dateipfad);
				m.marshal(s, fw);
			}
			finally {
				try{
					fw.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		
		}
				
	}

	@Override
	public Object spielLaden(String dateipfad) throws ClassNotFoundException,
			FileNotFoundException, IOException, SpielerFarbeVorhandenException,
			SpielerNichtGefundenException, JAXBException {

		JAXBContext context=JAXBContext.newInstance(SpielBean.class);
		Unmarshaller um = context.createUnmarshaller();
		SpielBean s = (SpielBean)um.unmarshal(new FileReader(dateipfad));
		s.setSpielerAmZug(s.getTeilnehmendeSpieler().get(s.getSpielerAmZugIndex()));
		
		//Alle SPieler ordnen
		
		return s;
	}


}
