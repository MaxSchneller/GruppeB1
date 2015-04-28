package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 * Ein Dialogfenster zur Eingabe von Spielerdaten
 */
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
	private File imageFile5;
	private BufferedImage blau;
	private File imageFile6;
	private BufferedImage rot;
	private File imageFile7;
	private BufferedImage gelb;
	private File imageFile8;
	private BufferedImage gruen;
	private File imageFile9;
	private BufferedImage keine;
	private ImageIcon blau1;
	private ImageIcon gruen1;
	private ImageIcon rot1;
	private ImageIcon gelb1;
	private ImageIcon keine1;
	
	private JButton oKButton;
	private madnGUI GUI;
	
	


	/**
	 * Erstellt ein neues Dialogfenster zur Abfrage von Spielerdaten 
	 * @param GUI Die main GUI
	 * @throws IOException Bilder konnten nicht geladen werden
	 */
	public DialogGUI(madnGUI GUI) throws IOException {
		this.GUI=GUI;
		fensterFuerSpielerAnlegen();
	}
	

	/**
	 * Das Dialogfenster, mit dem man einen Spieler anlegen kann.
	 * @throws IOException Bilder konnten nicht geladen werden
	 */
	public void fensterFuerSpielerAnlegen() throws IOException {
		
		
		JDialog jd2 = new JDialog();
		box1 = new Box(BoxLayout.Y_AXIS);
		jd2.setTitle("Spieler erstellen");

		//Fuer was ist das Fester?
		JPanel jp2 = new JPanel ();
		jl2 = new JLabel("Bitte legen Sie Ihren Spieler an:");
		jp2.add(jl2);
		box1.add(jp2);

		//Abstand
		box1.add(new JPanel());
		
		//Namen eingeben
		JPanel jp3 = new JPanel();
		spielerName = new JLabel ("Spielername: "); jp3.add(spielerName);
		spielerNameText = new JTextField (15); jp3.add(spielerNameText);
		box1.add(jp3);
		
		//Farbe Waehlen
		JPanel jp4 = new JPanel();
		JPanel farbe = new JPanel();
		imageFile5 = new File("bilder/BLAU_Figur.png");
		blau = ImageIO.read(imageFile5);
		imageFile6 = new File("bilder/ROT_Figur.png");
		rot = ImageIO.read(imageFile6);
		imageFile7 = new File("bilder/GELB_Figur.png");
		gelb = ImageIO.read(imageFile7);
		imageFile8 = new File("bilder/GRUEN_Figur.png");
		gruen = ImageIO.read(imageFile8);
		imageFile9 = new File("bilder/keine_Figur.png");
		keine = ImageIO.read(imageFile8);
		blau1=new ImageIcon(blau);
		gruen1=new ImageIcon(gruen);
		rot1=new ImageIcon(rot);
		gelb1=new ImageIcon(gelb);
		keine1=new ImageIcon(keine);
		farbAuswahl = new ButtonGroup ();
		rotAuswahl = new JRadioButton("ROT" , rot1 , false);
		blauAuswahl = new JRadioButton("BLAU" , blau1, false);
		gruenAuswahl = new JRadioButton("GRÜN" , gruen1 , false);
		gelbAuswahl = new JRadioButton("GELB", gelb1 , false);
		farbe.add(rotAuswahl);
		farbe.add(blauAuswahl);
		farbe.add(gruenAuswahl);
		farbe.add(gelbAuswahl);
		jp4.add(farbe);
		box1.add(jp4);
		
		//Abstand
		box1.add(new JPanel());
		
		//KI auswaehlen
		JPanel jp5 = new JPanel();
		kIAuswahl = new ButtonGroup ();
		realerSpieler = new JRadioButton("Realer Spieler", true);
		agressiverComputer = new JRadioButton("aggressiver Computergegner" , false);
		devensiverComputer = new JRadioButton("devensiver Comutergegner" , false);
		kIAuswahl.add(realerSpieler);
		kIAuswahl.add(agressiverComputer);
		kIAuswahl.add(devensiverComputer);
		jp5.add(realerSpieler);
		jp5.add(agressiverComputer);
		jp5.add(devensiverComputer);
		box1.add(jp5);
		
		//Abstand
		box1.add(new JPanel());
		
		//OK Button fuer naechsten Spieler
		oKButton = new JButton("Spieler anlegen");
		oKButton.addActionListener(this.GUI.getEventHandler());
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

	/**
	 * @return Gibt den gewuenschten Name des Spielers zurrueck
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Gibt die geuwenschte Farbe des Spielers zurrueck
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * @return Gibt den KiTyp des zu erstellenden Spielers zurrueck
	 */
	public KiTypEnum getKiTyp() {
		return kiTyp;
	}


	/**
	 * @return Gibt den Button "Spieler anlegen" zurrueck
	 */
	public Object getButtonWeiter() {
		return this.oKButton;
	}
}
