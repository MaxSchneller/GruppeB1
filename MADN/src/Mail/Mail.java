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


public class Mail extends Thread{
	private Properties p;
	
	private class MailAuthenticetor extends Authenticator {
		private String user , password;
		public MailAuthenticetor(){
			this.user=""+p.get("mail.smtp.user");
			this.password=""+p.get("mail.smtp.password");
		}
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user , password);
		}
	}
	
	public Mail (String an , String betreff , String text , String anhangpfad1 , String anhangname1){
		if ((an==null)||(an.length()==0)){
			return;
		} else {
			p = new Properties();
			p.put("mail.smtp.host","maildap.reutlingen-university.de");
			p.put("mail.smtp.user",""); //geb deine buntzername an
			p.put("mail.smtp.password",""); //geb dein Passwort an
			p.put("mail.smtp.socketFactory.port","465");
			p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.auth","true");
			p.put("mail.smtp.port","465");
			p.put("von",""); // deine email
			p.put("an",an);
			p.put("betreff",betreff);
			p.put("text",text);
			
			if (anhangpfad1 == null){
				p.put("anhangpfad1","");
			} else {
				p.put("anhangpfad1",anhangpfad1);
			}
			if (anhangname1 == null){
				p.put("anhangname1", "");
			} else {
				p.put("anhangname1", anhangname1);
			}
			this.start();
		}
		}
		
		@Override
		public void run(){
			try{
				System.out.println("Start Mailing an " + p.getProperty("an"));
				MailAuthenticetor auth = new MailAuthenticetor();
				Session session = Session.getDefaultInstance(p,auth);
				Message msg = new MimeMessage (session);
				msg.setFrom(new InternetAddress(p.getProperty("von")));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(p.getProperty("an") , false));
				msg.setSubject(p.getProperty("betreff"));
				
				//1.Teil ist die Nachricht
				MimeBodyPart bodyNachricht = new MimeBodyPart();
				bodyNachricht.setText(p.getProperty("text"));
				Multipart body = new MimeMultipart();
				body.addBodyPart(bodyNachricht);
				
				//2.Teil sind die Anhaenge
				/*	if ((!p.getProperty("anhangpfad1").equals("")) && (!p.getProperty("anhangname1").equals(""))){
						MimeBodyPart bodyAnhang = new MimeBodyPart();
						DataSource source =  new FileDataSource(p.getProperty("anhangpfad1"));
						bodyAnhang.setDataHandler(new DataHandler(source));
						bodyAnhang.setFileName(p.getProperty("anhangname1"));
						body.addBodyPart(bodyAnhang);
				}*/
				
				msg.setContent(body);
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("Mailing an " +p.getProperty("an") + " erfolgreich beendet.");
			}
			catch (Exception e){
				System.out.println("mailing an " + p.getProperty("an")+ " FEHLGESCHLAGEN");
				e.printStackTrace();
			}
			
			
			
		}
		
		
	}
	


