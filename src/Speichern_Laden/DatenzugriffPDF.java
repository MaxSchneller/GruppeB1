package Speichern_Laden;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;
import Spiel.Spiel;

/**
 * Ermoeglicht das Speichern von Spielen via PDF
 */
public class DatenzugriffPDF implements iDatenzugriff {
	
	
	/** Luecke zwischen zwei Feldern X */
	private int standardLueckeX = 50;
	/** Luecke zwischen zwei Feldern Y*/
	private int standardLueckeY = 50;
	/** Alle Spalten des Bretts */
	private int[] xSpalten = new int[11];
	/** Alle Zeilen des Bretts */
	private int[] ySpalten = new int[11];
	/** Die Pixeldaten fuer die Namen der Spieler */
	private int[][] namen = 
	{
			{ 50, 475 },
			{ 500, 475},
			{ 500, 175},
			{ 50, 175}
	};
	
	/**
	 * Erstellt ein neues Objekt zum Speichern von PDF Dateien
	 */
	public DatenzugriffPDF() {
		
		for (int i = 0; i < 11; ++i) {
			if (i == 0) {
				this.xSpalten[i] = 75 - 20;
				this.ySpalten[i] = 75 - 20;
			} else {
				this.xSpalten[i] = this.xSpalten[i-1] + this.standardLueckeX;
				this.ySpalten[i] = this.xSpalten[i- 1] + this.standardLueckeY;
			}
		}
		
		
	}

	@Override
	public void spielSpeichern(Object spiel, String dateipfad) throws IOException {
		if (spiel instanceof Spiel) {
			Spiel zuSpeicherndesSpiel = (Spiel) spiel;
			Document document = new Document(new Rectangle(650f, 650f), 0,0,0,0);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();

			try {
				PdfWriter pdfWriter = PdfWriter.getInstance(document,
						stream);

				document.open();
				
			
				Image image = Image.getInstance("Bilder/madn-neu.png");
				image.setAbsolutePosition(0, 0);

				document.add(image);
				
				this.speichereFiguren(zuSpeicherndesSpiel, document);
				this.speichereNamen(zuSpeicherndesSpiel, pdfWriter);

				document.close();
				
				
				PdfReader pdfReader = new PdfReader(stream.toByteArray());

				PdfStamper pdfStamper = new PdfStamper(pdfReader,
						new FileOutputStream(dateipfad));

				HashMap<String, String> info = pdfReader.getInfo();

				this.speichereMeta(info, zuSpeicherndesSpiel);

				pdfStamper.setMoreInfo(info);
				pdfStamper.close();
			} catch (DocumentException e) {
				e.printStackTrace();
			} finally {
				document.close();
			}
		}

	}

	

	@Override
	public Object spielLaden(String dateipfad) throws ClassNotFoundException,
			FileNotFoundException, IOException, SpielerFarbeVorhandenException,
			SpielerNichtGefundenException {
		
		 PdfReader pdfReader = null;
		 try {
			 pdfReader = new PdfReader(dateipfad);
			 HashMap<String, String> info = pdfReader.getInfo();
			 
			 int spielerZahl = Integer.parseInt(info.get("spielerAnzahl"));
			 
			 Spiel s = null;
			 for (int i = 0; i < spielerZahl; ++i) {
				 String spieler = info.get("Spieler" + i);
				 String[] teile = spieler.split(" ; ");
				 
				 String name = teile[0];
				 FarbEnum farbe = FarbEnum.vonString(teile[1]);
				 KiTypEnum kiTyp = KiTypEnum.vonString(teile[2]);
				 
				 if (s == null) {
					 s = new Spiel(name, farbe, kiTyp);
				 } else {
					 s.spielerHinzufuegen(name, farbe, kiTyp);
				 }
				 
				 for (int j = 3; j < 7; ++j) {
					 s.debugSetzeFigur(farbe, j - 3, teile[j]);
				 }
			 }
			 
			 if (s != null) {
				 s.setSpielerAmZug(FarbEnum.vonString(info.get("amZug")));
			 }
			 
			 return s;
			 
		 } catch (IOException e) {
			 e.printStackTrace();
		 } finally {
			 if (pdfReader != null) {
				 pdfReader.close();
			 }
		 }
		return null;
	}
	
	/**
	 * Speichert den Spielstand als Metadaten, um das Laden zu erleichtern
	 * @param info Die info HashMap des documents
	 * @param spiel Das zu speichernde Spiel
	 */
	private void speichereMeta(HashMap<String, String> info, Spiel spiel) {
		String[] spieler = spiel.getSpieler();
		
		info.put("spielerAnzahl", String.format("%d", spieler.length));
		
		for (int i = 0; i < spieler.length; ++i) {
			info.put("Spieler" + i, spieler[i]);
		}
		
		info.put("amZug", spiel.getSpielerAmZugFarbe().name());
		info.put("gewuerfelt", String.format("%d", spiel.getZuleztGewuerfelt()));
	}

	/**
	 * Speichert die Namen aller Spieler auf dem Spielbrett image
	 * @param zuSpeicherndesSpiel Das zu speichernde Spiel
	 * @param writer Der PdfWriter mit dem das Document erstellt wurde
	 * @throws DocumentException Etwas lief schief
	 * @throws IOException Etwas lief schief
	 */
	private void speichereNamen(Spiel zuSpeicherndesSpiel, PdfWriter writer) throws DocumentException, IOException {
		String[] spieler = zuSpeicherndesSpiel.getSpieler();
		
		for (String s : spieler) {
			String[] teile = s.split(" ; "); 
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int ordinal = farbe.ordinal();
			PdfContentByte directContent = writer.getDirectContent();
			directContent.saveState();
			directContent.beginText();
		
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, true);
			directContent.setFontAndSize(bf, 27);
			directContent.showTextAligned(Element.ALIGN_LEFT, teile[0],
					this.namen[ordinal][0], this.namen[ordinal][1], 0);
			directContent.endText();
			directContent.restoreState();
		}
	}

	/**
	 * Speichert alle Figuren auf dem Spielbrett
	 * @param zuSpeicherndesSpiel Das zu speichernde Spiel
	 * @param doc Das Document in dem gespeichert werden soll
	 * @throws MalformedURLException Etwas lief schief
	 * @throws IOException Etwas lief schief
	 * @throws DocumentException Etwas lief schief
	 */
	private void speichereFiguren(Spiel zuSpeicherndesSpiel, Document doc) throws MalformedURLException, IOException, DocumentException {
		String[] spieler = zuSpeicherndesSpiel.getSpieler();
		
		for (String s : spieler) {
			
			String[] teile = s.split(" ; ");
			
			for (int i = 3; i < 7; ++i) {
				int spalte = this.getSpalteFeld(teile[i]);
				int reihe = this.getReiheFeld(teile[i]);
				
				String farbe = teile[1].toLowerCase();
				
				Image img = Image.getInstance("Bilder/" + farbe + ".png");
				img.setAbsolutePosition(this.xSpalten[spalte], this.ySpalten[reihe]);
				
				doc.add(img);
			}
		}
	}
	
	/**
	 * Gibt die passende Spalte fuer dieses Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private int getSpalteFeld(String feldID) {
		try {
			int i = Integer.parseInt(feldID);
			return this.getSpalteNormalesFeld(i);
		} catch (NumberFormatException e) {
			return this.getSpalteSonderfeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Reihe fuer dieses Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private int getReiheFeld(String feldID) {
		try {
			int i = Integer.parseInt(feldID);
			return this.getReiheNormalesFeld(i);
		} catch (NumberFormatException e) {
			return this.getReiheSonderfeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Spalte fuer ein normales Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private int getSpalteNormalesFeld(int feldInt) {
		switch (feldInt) {
		case 1:
		case 40:
		case 39: 
			return 0;
		case 2:
		case 38:
			return 1;
		case 3:
		case 37: 
			return 2;
		case 4:
		case 36: 
			return 3;
		case 10:
		case 30: 
			return 5;
		case 16:
		case 24: 
			return 7;
		case 17:
		case 23:
			return 8;
		case 18:
		case 22:
			return 9;
		case 19:
		case 20:
		case 21: 
			return 10;
		default: {
			if (this.inIntervall(5, 9, feldInt) ||
					this.inIntervall(31, 35, feldInt)) {
				return 4;
			} else if (this.inIntervall(11, 15, feldInt) ||
					this.inIntervall(25, 29, feldInt)) {
				return 6;
			} else {
				throw new RuntimeException("Kann keine Spalte zuweisen!");
			}
		}
		}
	}
	
	/**
	 * Gibt die passende Reihe fuer ein normales Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private int getReiheNormalesFeld(int feldInt) {
		
		switch (feldInt) {
		case 9:
		case 10:
		case 11:
			return 10;
		case 8:
		case 12:
			return 9;
		case 7:
		case 13:
			return 8;
		case 6:
		case 14:
			return 7;
		case 40:
		case 20:
			return 5;
		case 34:
		case 26:
			return 3;
		case 33:
		case 27:
			return 2;
		case 32:
		case 28:
			return 1;
		case 31:
		case 30:
		case 29:
			return 0;
		default: {
			if (this.inIntervall(1, 5, feldInt)||
					this.inIntervall(15, 19, feldInt)) {
				return 6;
			} else if (this.inIntervall(35, 39, feldInt) ||
					this.inIntervall(21, 25, feldInt)) {
				return 4;
			} else {
				throw new RuntimeException("Kann keine Reihe zuweisen!");
			}
		}
		}
	}
	
	/**
	 * Gibt die passende Spalte fuer ein Start- oder Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private int getSpalteSonderfeld(String feldID) {
		if (feldID.contains("S")) {
			return this.getSpalteStartFeld(feldID);
		} else {
			return this.getSpalteEndFeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Spalte fuer ein Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private int getSpalteEndFeld(String feldID) {
		String[] teile = feldID.split(" ");
		
		if (teile.length == 2) {
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case BLAU:
			case GELB: {
				return 5;
			}
			case ROT: {
				return nummer;
			}
			case GRUEN: {
				return 10 - nummer;
			}
			}
		}
		
		return -1;
	}

	/**
	 * Gibt die passende Spalte fuer ein Startfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private int getSpalteStartFeld(String feldID) {
		
		String[] teile = feldID.split(" ");
		if (teile.length == 2) {
			
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case ROT:
			case GELB: {
				if (nummer == 1 || nummer == 4) {
					return 1;
				} else {
					return 0;
				}
			}
			case BLAU:
			case GRUEN: {
				if (nummer == 1  || nummer == 4) {
					return 10;
				} else {
					return 9;
				}
			}
			}
		}
		return -1;
	}

	/**
	 * Gibt die passende Reihe fuer ein Start- oder Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private int getReiheSonderfeld(String feldID) {
		if (feldID.contains("S")) {
			return this.getReiheStartFeld(feldID);
		} else {
			return this.getReiheEndfeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Reihe fuer ein Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private int getReiheEndfeld(String feldID) {
		String[] teile = feldID.split(" ");
		
		if (teile.length == 2) {
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case ROT:
			case GRUEN: {
				return 5;
			}
			case BLAU: {
				return 10 - nummer;
			}
			case GELB:
				return nummer;
			}
		}
		
		return -1;
	}

	/**
	 * Gibt die passende Reihe fuer ein Startfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private int getReiheStartFeld(String feldID) {
		String[] teile = feldID.split(" ");
		
		if (teile.length == 2) {
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case ROT:
			case BLAU: {
				if (nummer < 3) {
					return 10;
				} else {
						return 9;
				}
				}
			case GELB:
			case GRUEN: {
				if (nummer < 3) {
					return 1;
				} else {
					return 0;
				}
			}
			}
		}
		
		return -1;
	}

	/**
	 * Prueft ob a im gewuenschten Intervall ist
	 * @param min Minimum
	 * @param max Maximum
	 * @param a Der Wert
	 * @return True falls innerhalb, sonst false
	 */
	private boolean inIntervall(int min, int max, int a) {
		if (a >= min && a <= max) {
			return true;
		}
		return false;
	}

}
