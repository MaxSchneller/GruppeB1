package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;

public class DialogGUI {

	private String name;
	private FarbEnum farbe;
	private KiTypEnum kiTyp;
	private madnGUI maingui;

	private JFrame jf;
	private JLabel jl1 = new JLabel(
			"Bitte wählen Sie die Anzahl der teilnehmenden Spieler:");
	private JButton b1 = new JButton("Ein Spieler");
	private JButton b2 = new JButton("Zwei Spieler");
	private JButton b3 = new JButton("Drei Spieler");
	private JButton b4 = new JButton("Vier Spieler");

	
	public static void main (String [] args){
		new DialogGUI ();
	}
	
	public DialogGUI(/*madnGUI maingui*/) {
//		this.setMaingui(maingui);
//		EventHandler eh = maingui.getEventHandler();

//		if (maingui != null) {
			jf = new JFrame("Anzahl der Spieler");
			JPanel jp = new JPanel();
			jp.setLayout(new GridLayout(3, 2));
			jp.add(jl1);
			jp.add(new JPanel());
			jp.add(b1);
//			b1.addActionListener(eh);
			jp.add(b2);
//			b2.addActionListener(eh);
			jp.add(b3);
//			b3.addActionListener(eh);
			jp.add(b4);
//			b4.addActionListener(eh);
			jf.setContentPane(jp);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.pack();
			jf.setVisible(true);
//		}

	}

	
	public void setMaingui(madnGUI maingui) {
		if (maingui == null) {
			throw new RuntimeException("Es existiert noch keine Hauptgui");
		} else {
			this.maingui = maingui;
		}
	}

	public String getName() {
		return name;
	}

	public FarbEnum getFarbe() {
		return farbe;
	}

	public KiTypEnum getKiTyp() {
		return kiTyp;
	}
}
