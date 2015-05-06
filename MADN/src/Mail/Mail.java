package Mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import GUI.madnGUI;

public class Mail extends Thread {
	private Properties p;
	private madnGUI gui;

	private class MailAuthenticetor extends Authenticator {
		private String user, password;

		public MailAuthenticetor() {
			this.user = "" + p.get("mail.smtp.user");
			this.password = "" + p.get("mail.smtp.password");
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}
	}

	public Mail(madnGUI gui, String an, String betreff, String text, String anhangPfad1,
			String anhangName1, String anhangPfad2, String anhangName2) {
		this.setGUI(gui);
		if ((an == null) || (an.length() == 0))
			return;
		p = new Properties();
		p.put("mail.smtp.host", "mail.gmx.net");
		p.put("mail.smtp.user", "madnb1@gmx.de"); // geb deine buntzername an
		p.put("mail.smtp.password", "gruppeb1"); // geb dein Passwort an
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "465");
		p.put("von", "madnb1@gmx.de"); // deine
																		// email
		p.put("an", an);
		p.put("betreff", betreff);
		p.put("text", text);

		if (anhangPfad1 == null) {
			p.put("anhangPfad1", "");
		} else {
			p.put("anhangPfad1", anhangPfad1);
		}
		if (anhangName1 == null) {
			p.put("anhangName1", "");
		} else {
			p.put("anhangName1", anhangName1);
		}
		if (anhangPfad2 == null) {
			p.put("anhangPfad2", "");
		} else {
			p.put("anhangPfad2", anhangPfad1);
		}
		if (anhangName2 == null) {
			p.put("anhangName2", "");
		} else {
			p.put("anhangName2", anhangName2);
		}
		this.start();
	}

	private void setGUI(madnGUI gui) {
		if (gui == null) {
			throw new NullPointerException("Mail : gui ist null");
		}
		this.gui = gui;
	}

	@Override
	public void run() {
		try {
			gui.setzeStatusNachricht("Start Mailing an " + p.getProperty("an"));
			MailAuthenticetor auth = new MailAuthenticetor();
			Session session = Session.getDefaultInstance(p, auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(p.getProperty("von")));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(p.getProperty("an"), false));
			msg.setSubject(p.getProperty("betreff"));

			// 1.Teil ist die Nachricht
			MimeBodyPart bodyNachricht = new MimeBodyPart();
			bodyNachricht.setText(p.getProperty("text"));
			Multipart body = new MimeMultipart();
			body.addBodyPart(bodyNachricht);

			// 2.Teil sind die Anhaenge
			for (int i = 1; i <= 2; i++) {
				if ((!p.getProperty("anhangPfad" + i).equals(""))
						&& (!p.getProperty("anhangName" + i).equals(""))) {
					MimeBodyPart bodyAnhang = new MimeBodyPart();
					DataSource source = new FileDataSource(
							p.getProperty("anhangPfad" + i));
					bodyAnhang.setDataHandler(new DataHandler(source));
					bodyAnhang.setFileName(p.getProperty("anhangName" + i));
					body.addBodyPart(bodyAnhang);
				}
			}

			msg.setContent(body);
			msg.setSentDate(new Date());
			Transport.send(msg);
			this.gui.setzeStatusNachricht("Mailing an " + p.getProperty("an")
					+ " erfolgreich beendet.");
		} catch (Exception e) {
			this.gui.zeigeFehler("mailing an " + p.getProperty("an")
					+ " FEHLGESCHLAGEN");
			e.printStackTrace();
		}

	}

}
