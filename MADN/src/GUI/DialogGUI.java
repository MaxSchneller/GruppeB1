package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;

public class DialogGUI {

	private String name;
	private FarbEnum farbe;
	private KiTypEnum kiTyp;
	private madnGUI maingui;

	private Box box;
	private JLabel jl1;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;

	private Box box1;
	private JLabel jl2;
	private JLabel spielerName;
	private JTextField spielerNameText;
	
	private JLabel spielerFarbe;
	private JRadioButton rotAuswahl;
	private JRadioButton blauAuswahl;
	private JRadioButton gruenAuswahl;
	private JRadioButton gelbAuswahl;
	private ButtonGroup farbAuswahl;
	
	private JLabel spielerComputer;
	private ButtonGroup kIAuswahl;
	private JRadioButton realerSpieler;
	private JRadioButton agressiverComputer;
	private JRadioButton devensiverComputer;
	
	private JButton oKButton;
	
	


	
	public static void main(String[] args) throws IOException{
		new DialogGUI();
	}

	public DialogGUI() throws IOException {

		//fensterFuerAnzahl();
		
		fensterFuerSpielerAnlegen();

	}

	/**
	 * Dialogfenster um Auszuwaehlen, wie viele Spieler am Spiel Teilnehmen
	 */
	public void fensterFuerAnzahl() {
		JDialog jd = new JDialog();
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
		jp1.add(b1);
		b2 = new JButton("Zwei Spieler");
		jp1.add(b2);
		b3 = new JButton("Drei Spieler");
		jp1.add(b3);
		b4 = new JButton("Vier Spieler");
		jp1.add(b4);
		box.add(jp1);

		jd.setContentPane(box);
		jd.pack();
		jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jd.setVisible(true);
	}

	/**
	 * Das Dialogfenster, mit dem man einen Spieler anlegen kann.
	 * @throws IOException 
	 */
	public void fensterFuerSpielerAnlegen() throws IOException {
		
		
		File imageFile5 = new File("bilder/BLAU_Figur.png");
		BufferedImage blau = ImageIO.read(imageFile5);
		
		File imageFile6 = new File("bilder/ROT_Figur.png");
		BufferedImage rot = ImageIO.read(imageFile6);
		
		File imageFile7 = new File("bilder/GELB_Figur.png");
		BufferedImage gelb = ImageIO.read(imageFile7);
		
		File imageFile8 = new File("bilder/GRUEN_Figur.png");
		BufferedImage gruen = ImageIO.read(imageFile8);
		
		
		ImageIcon blau1=new ImageIcon(blau);
		ImageIcon gruen1=new ImageIcon(gruen);
		ImageIcon rot1=new ImageIcon(rot);
		ImageIcon gelb1=new ImageIcon(gelb);
		
		JDialog jd2 = new JDialog();
		box1 = new Box(BoxLayout.Y_AXIS);
		jd2.setTitle("Spieler erstellen");

		JPanel jp2 = new JPanel ();
		jl2 = new JLabel("Bitte legen Sie Ihren Spieler an:");
		jp2.add(jl2);
		box1.add(jp2);

		box1.add(new JPanel());
		
		JPanel jp3 = new JPanel();
		spielerName = new JLabel ("Spielername: "); jp3.add(spielerName);
		spielerNameText = new JTextField (15); jp3.add(spielerNameText);
		box1.add(jp3);
		
		JPanel jp4 = new JPanel();
		JPanel farbe = new JPanel();
		spielerFarbe = new JLabel ("Spielerfarbe: "); jp3.add(spielerFarbe);
		farbAuswahl = new ButtonGroup ();
		rotAuswahl = new JRadioButton("ROT" , rot1 , false);
		blauAuswahl = new JRadioButton("BLAU" , blau1, false);
		gruenAuswahl = new JRadioButton("GRUEN" , gruen1 , false);
		gelbAuswahl = new JRadioButton("GELB", gelb1 , false);
		farbe.add(rotAuswahl);
		farbe.add(blauAuswahl);
		farbe.add(gruenAuswahl);
		farbe.add(gelbAuswahl);
		jp4.add(farbe);
		box1.add(jp4);
		
		box1.add(new JPanel());
		
		JPanel jp5 = new JPanel();
		JPanel ki = new JPanel();
		spielerComputer = new JLabel ("Spielerart: "); jp3.add(spielerComputer);
		kIAuswahl = new ButtonGroup ();
		realerSpieler = new JRadioButton("Realer Spieler", true);
		agressiverComputer = new JRadioButton("aggressiver Computergegner" , false);
		devensiverComputer = new JRadioButton("devensiver Comutergegner" , false);
		ki.add(realerSpieler);
		ki.add(agressiverComputer);
		ki.add(devensiverComputer);
		jp5.add(ki);
		box1.add(jp5);
		
		box1.add(new JPanel());
		
		oKButton = new JButton("Spieler anlegen");
		box1.add(oKButton);
		
		jd2.setContentPane(box1);
		jd2.pack();
		jd2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jd2.setVisible(true);
		

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
