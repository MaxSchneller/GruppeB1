package GUI;

import java.awt.Dialog;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MailGUI {

	private JDialog jd;
	private Box box;
	private Dialog jd2;

	private JLabel jl2;

	private JPanel jp3;
	private JTextField eMailAdresse;
	private JTextArea jTA;

	private ButtonGroup anhang;
	private JRadioButton welcheDatei;
	private JRadioButton welcheDatei2;

	private JButton sendeButton;
	private JPanel jp4;

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

		//Email Text
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
		anhang = new ButtonGroup();
		welcheDatei = new JRadioButton("Serialisierte Datei", true);
		welcheDatei2 = new JRadioButton("PDF Datei", false);

		anhang.add(welcheDatei);
		anhang.add(welcheDatei2);
		jp5.add(welcheDatei);
		jp5.add(welcheDatei2);
		box.add(jp5);

		// Abstand
		box.add(new JPanel());

		// Sendebutton
		sendeButton = new JButton("Senden");
		// sendeButton.addActionListener(this.maingui.getEventHandler());
		box.add(sendeButton);

		jd.setContentPane(box);
		jd.pack();
		jd.setLocationRelativeTo(null);
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.setVisible(true);
	}

}
