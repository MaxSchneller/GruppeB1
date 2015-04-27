package Speichern_Laden;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import sun.awt.image.ByteArrayImageSource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
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
	public void spielSpeichern(Object spiel) throws IOException {
		if (spiel instanceof Spiel) {
			Document document = new Document(new Rectangle(650f, 650f), 0,0,0,0);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();

			try {
				PdfWriter pdfWriter = PdfWriter.getInstance(document,
						stream);

				document.open();
				
				// 92 px = 70.86614 pts
				// 1 px =

				Image image = Image.getInstance("Bilder/madn-neu.png");
				image.setAbsolutePosition(0, 0);

				document.add(image);

				image = Image.getInstance("Bilder/blau.png");
				image.setAbsolutePosition(this.xSpalten[0], this.ySpalten[10]);
				
				System.out.println(image.getScaledHeight());
				

				document.add(image);

				PdfContentByte directContent = pdfWriter.getDirectContent();

				directContent.saveState();
				directContent.beginText();
			
				BaseFont bf = BaseFont.createFont();
				//
				// directContent.setTextRenderingMode(
				// PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
				// directContent.setLineWidth(1.5f);
				// directContent.setRGBColorStroke(0xFF, 0x00, 0x00);
				// directContent.setRGBColorFill(0xFF, 0xFF, 0xFF);
				directContent.setFontAndSize(bf, 36);

				// directContent.showText("SOLD OUT");
				directContent.showTextAligned(Element.ALIGN_CENTER, "Hallo",
						300, 300, 0);
				directContent.endText();
				directContent.restoreState();

				document.close();
				
				
				PdfReader pdfReader = new PdfReader(stream.toByteArray());

				PdfStamper pdfStamper = new PdfStamper(pdfReader,
						new FileOutputStream("Dateien_Gespeichert/test.pdf"));

				HashMap<String, String> info = pdfReader.getInfo();

				info.put("Hallo", "Ohoho");

				pdfStamper.setMoreInfo(info);
				pdfStamper.close();

				 pdfReader = new PdfReader(
						"Dateien_Gespeichert/test.pdf");

				
				info = pdfReader.getInfo();
				
				System.out.println(info.get("Hallo"));

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				document.close();
			}
		}

	}

	@Override
	public Object spielLaden() throws ClassNotFoundException,
			FileNotFoundException, IOException, SpielerFarbeVorhandenException,
			SpielerNichtGefundenException {
		return null;
	}
	
	private int getXNormalesFeld(String feldID) {
		int feldInt = Integer.parseInt(feldID);
		
		// 1-5
		if (feldInt <= 5) {
			int basis = this.startX[0];
			
			basis += (40 + this.standardLueckeX) * (feldInt - 1);
			
			return basis;
		} else if  
		else if (feldInt >= 9 && feldInt <= 11) {
			int basis = this.feld9X;
			
			basis += (40 + this.standardLueckeX) * (feldInt - 11);
			return basis;
		} else if (feldInt >= 15 && feldInt <= 19) {
			int basis = this.feld11X;
			
			basis += (40 + this.standardLueckeX) * (feldInt - 15);
		} else if (feldInt >= 21 && feldInt <= 25)
	}
	

}
