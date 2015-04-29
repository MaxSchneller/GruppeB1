package GUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ein Dialogfenster zur Erfragung der Spieleranzahl
 */
public class SpielerAnzahlGUI {

	private Box box;
	private JLabel jl1;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private madnGUI GUI;
	private JDialog jd;
	private EventHandler eH;
	
	/**
	 * Erstellt ein neues Dialogfenster fuer die Spieleranzahl
	 * @param eH Der EventHandler, der die events verarbeitet
	 */
	public SpielerAnzahlGUI(EventHandler eH) {
		this.setEventHandler(eH);
		
		fensterFuerAnzahl();
	}
	

	/**
	 * Prueft den Eventhandler auf null und setzt in
	 * @param eH Der EventHandler
	 */
	private void setEventHandler(EventHandler eH) {
		if (eH == null) {
			throw new RuntimeException("EventHandler darf nicht null sein");
		}
		
		this.eH = eH;
	}

	/**
	 * Verbirgt dieses Dialogfenster
	 */
	public void schliessen() {
		
		jd.dispose();
	}
	
	/**
	 * Erstellt alle Komponenten fuer das Dialogfenster und fuegt diese zusammen.
	 * Registriert den EventHandler als ActionListener wo noetig
	 */
	public void fensterFuerAnzahl() {
		this.jd = new JDialog();
		box = new Box(BoxLayout.Y_AXIS);
		jd.setTitle("Spieleranzahl");

		JPanel jp = new JPanel();
		jl1 = new JLabel(
				"Bitte wählen Sie die Anzahl der teilnehmenden Spieler:");
		jp.add(jl1);
		box.add(jp);

		box.add(new JPanel());

		JPanel jp1 = new JPanel();
		b1 = new JButton("Ein Spieler");
		b1.addActionListener(this.eH);
		jp1.add(b1);
		
		b2 = new JButton("Zwei Spieler");
		b2.addActionListener(this.eH);
		jp1.add(b2);
		b3 = new JButton("Drei Spieler");
		b3.addActionListener(this.eH);
		jp1.add(b3);
		b4 = new JButton("Vier Spieler");
		b4.addActionListener(this.eH);
		jp1.add(b4);
		box.add(jp1);

		jd.setContentPane(box);
		jd.pack();
		jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jd.setVisible(true);
		jd.setLocationRelativeTo(null);
	}
	
	/**
	 * @return Gibt den Button fuer "1" Spieler zurrueck
	 */
	public Object getButtonSpielerZahl1() {
		return this.b1;
	}

	/**
	 * @return Gibt den Button fuer "2" Spieler zurrueck
	 */
	public Object getButtonSpielerZahl2() {
		return this.b2;
	}

	/**
	 * @return Gibt den Button fuer "4" Spieler zurrueck
	 */
	public Object getButtonSpielerZahl3() {
		return this.b3;
	}

	/**
	 * @return Gibt den Button fuer "4" Spieler zurrueck
	 */
	public Object getButtonSpielerZahl4() {
		return this.b4;
	}
	
}
