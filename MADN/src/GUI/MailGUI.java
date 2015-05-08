package GUI;


import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MailGUI {

	private JDialog jd;
	private Box box;

	private JLabel jl2;

	private JPanel jp3;
	private JTextField eMailAdresse;
	private JTextArea jTA;

	private JButton sendeButton;
	private JPanel jp4;
	private JTextField jTF2;
	private JLabel jl;
	private JButton dateiButton;
	private JFileChooser welcheDateiSenden;

	public static void main(String[] args) throws IOException {
		new MailGUI();
	}

	public MailGUI() throws IOException {
		try {
			fensterFuerMailVersenden();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Etwas lief schief" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void fensterFuerMailVersenden() {

		jd = new JDialog();
		box = new Box(BoxLayout.Y_AXIS);
		jd.setTitle("Spielstand per Mail versenden");

		// Fuer was ist das Fester?
		JPanel jp2 = new JPanel();
		jl2 = new JLabel(
				"Versendnen Sie Ihren Spielstand per Mail an wen Sie wollen.");
		jp2.add(jl2);
		box.add(jp2);

		// Abstand
		box.add(new JPanel());

		// Email Text eingeben
		jp3 = new JPanel();
		eMailAdresse = new JTextField("EMail Adresse des Empfängers: ", 20);
		jp3.add(eMailAdresse);
		box.add(jp3);

		// Abstand
		box.add(new JPanel());

		// Email Text
		jp4 = new JPanel();
		String emailText = "Hallo, hier ist mein Spielstand meines Mensch Ärger dich nicht Spiels. Liebe Grüße ";
		// + DialogGUI.getName();
		jTA = new JTextArea(emailText, 5, 30);
		jp4.add(jTA);
		box.add(jp4);

		// Abstand
		box.add(new JPanel());

		// Datei auswaehlen
		JPanel jp5 = new JPanel();
		jl = new JLabel("Anhangsdatei: ");
		jTF2 = new JTextField("DIE DATEI", 20);
		dateiButton = new JButton("...");
		jp5.add(jl);
		jp5.add(jTF2);
		jp5.add(dateiButton);
		box.add(jp5);

		// Abstand
		box.add(new JPanel());

		// Sendebutton
		sendeButton = new JButton("Senden");
		// sendeButton.addActionListener(this.maingui.getEventHandler());
		box.add(sendeButton);

		// Abstand
		box.add(new JPanel());

		jd.setContentPane(box);
		jd.pack();
		jd.setLocationRelativeTo(null);
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.setResizable(false);
		jd.setVisible(true);
	}
	
	public String dateiFileChooser (){
		
		welcheDateiSenden = new JFileChooser("./Dateien_Gespeichert");
		welcheDateiSenden.setDialogType(JFileChooser.OPEN_DIALOG);
		welcheDateiSenden.setFileFilter(new FileNameExtensionFilter("PDF","pdf"));
		welcheDateiSenden.setFileFilter(new FileNameExtensionFilter("SER", "ser"));
		welcheDateiSenden.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int ergebnis = welcheDateiSenden.showOpenDialog(null);
		
		if (ergebnis == JFileChooser.APPROVE_OPTION){
			return welcheDateiSenden.getSelectedFile().getAbsolutePath();
		}
		else {
			return null;
		}
	}

	public JTextField getAnhangDatei() {
		return jTF2;
	}

	public JTextField geteMailAdresse() {
		return eMailAdresse;
	}

	public JTextArea getEmailtext() {
		return jTA;
	}

	public JButton getSendeButton() {
		return sendeButton;
	}

	public JButton getDateiButton() {
		return dateiButton;
	}
	
}
