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

	@Override
	public void spielSpeichern(Object spiel) throws IOException {
		if (spiel instanceof Spiel) {
			Document document = new Document(new Rectangle(650f, 650f));
			ByteArrayOutputStream stream = new ByteArrayOutputStream();

			try {
				PdfWriter pdfWriter = PdfWriter.getInstance(document,
						stream);

				document.open();
				
				

				Image image = Image.getInstance("Bilder/madn.jpg");
				image.setAbsolutePosition(0, 0);

				document.add(image);

				image = Image.getInstance("Bilder/blau.png");

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

}
