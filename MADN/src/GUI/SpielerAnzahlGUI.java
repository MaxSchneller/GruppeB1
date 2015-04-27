package GUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public SpielerAnzahlGUI(madnGUI gui) {
		this.setGUI(gui);
		
		fensterFuerAnzahl();
	}
	

	private void setGUI(EventHandler eH) {
		if (eH == null) {
			throw new RuntimeException("Gui darf nicht null sein");
		}
		
		this.eH = eH;
	}

	public void schliessen() {
		
		jd.setVisible(false);
	}

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
	}
	
	public Object getButtonSpielerZahl1() {
		return this.b1;
	}

	public Object getButtonSpielerZahl2() {
		return this.b2;
	}

	public Object getButtonSpielerZahl3() {
		return this.b3;
	}

	public Object getButtonSpielerZahl4() {
		return this.b4;
	}
	
}
