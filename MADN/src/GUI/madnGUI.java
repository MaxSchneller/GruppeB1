package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import Spiel.FarbEnum;

import java.awt.Image;

public class madnGUI {

	/** Der EventHandler der alle events dieses GUIs verarbeitet */
	EventHandler eH;
	/** Das Dialogfenster mit dem Spielerdate abgefragt werden */
	private DialogGUI diaGui;
	/** Das Dialogfenster mit dem die Spieleranzahl abgefragt wird */
	private SpielerAnzahlGUI spielerAnzahlGui;
	private JLabel[][] startfelder = new JLabel[4][4];
	private JLabel[][] endfelder = new JLabel[4][4];
	private JLabel[] normaleFelder = new JLabel[40];
	private JButton[][] spielfiguren = new JButton[4][4];

	public static void main(String[] args) throws IOException {
		// madnGUI GUI = new madnGUI();
		// GUI.erstelleGUI();

		try {
			madnGUI gui = new madnGUI();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Etwas lief beim Laden der Bilder schief" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt ein neues Hauptfenster
	 * @throws IOException Das Laden der Bolder lief schief
	 */
	public madnGUI() throws IOException {
		this.eH = new EventHandler(this);

		this.erstelleGUI();
		this.spielerAnzahlGui = new SpielerAnzahlGUI(this.getEventHandler());
	}

	/** 
	 * Erstellt alle Komponenten der Haupgui und fuegt diese zusammen
	 * @throws IOException Die Bilder konnten nicht geladen werden
	 */
	public void erstelleGUI() throws IOException {
		File imageFile = new File("bilder/madn.jpg");
		BufferedImage madn = ImageIO.read(imageFile);

		File imageFile3 = new File("bilder/gruen.png");
		BufferedImage gruen = ImageIO.read(imageFile3);

		File imageFile2 = new File("bilder/wuerfel.png");
		BufferedImage wuerfel = ImageIO.read(imageFile2);

		File imageFile4 = new File("bilder/platz.png");
		BufferedImage platz = ImageIO.read(imageFile4);

		File imageFile5 = new File("bilder/blau.png");
		BufferedImage blau = ImageIO.read(imageFile5);

		File imageFile6 = new File("bilder/rot.png");
		BufferedImage rot = ImageIO.read(imageFile6);

		File imageFile7 = new File("bilder/gelb.png");
		BufferedImage gelb = ImageIO.read(imageFile7);

		File imageFile8 = new File("bilder/wuerfeln.png");
		BufferedImage wuerfeln = ImageIO.read(imageFile8);

		ImageIcon bild = new ImageIcon(wuerfel);
		bild.setImage(bild.getImage().getScaledInstance(80, 80,
				Image.SCALE_DEFAULT));

		Color color = Color.BLACK;
		JFrame jf = new JFrame("Mensch-Ärgere-Dich-Nicht");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		ImageIcon platz1 = new ImageIcon(platz);

		ImageIcon wuerfeln1 = new ImageIcon(wuerfeln);
		wuerfeln1.setImage(wuerfeln1.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));

		ImageIcon blau1 = new ImageIcon(blau);
		ImageIcon gruen1 = new ImageIcon(gruen);
		ImageIcon rot1 = new ImageIcon(rot);
		ImageIcon gelb1 = new ImageIcon(gelb);

		JPanel jp_north = new JPanel();
		JPanel jp_south = new JPanel();
		JPanel jp_west = new JPanel();
		JPanel jp_east = new JPanel();
		JPanel jp_center = new JPanel();

		JLabel brett = new JLabel(new ImageIcon(madn));

		JButton buttonWuerfel = new JButton(wuerfeln1);
		
		JButton s1_gruen = new JButton(gruen1);
		s1_gruen.setContentAreaFilled(false);
		s1_gruen.setBorder(null);
		JButton s2_gruen = new JButton(gruen1);
		JButton s3_gruen = new JButton(gruen1);
		JButton s4_gruen = new JButton(gruen1);
		
		

		/**
		 * Menu
		 */
		JMenuBar jm = new JMenuBar();
		JMenu jMenu = new JMenu("Datei");
		JMenu jMenu2 = new JMenu("Hilfe");
		JMenuItem jmi = new JMenuItem("Speichern");
		JMenuItem jmi1 = new JMenuItem("Laden");
		JMenuItem jmi2 = new JMenuItem("Beenden");

		/**
		 * Spiel Log
		 */
		JTextArea console = new JTextArea(5, 1000);
		console.setEditable(false);

		jf.setLayout(new BorderLayout());
		jf.add(jp_north, BorderLayout.NORTH);
		jf.add(jp_south, BorderLayout.SOUTH);
		jf.add(jp_east, BorderLayout.EAST);
		jf.add(jp_west, BorderLayout.WEST);
		jf.add(jp_center, BorderLayout.CENTER);
		jf.setJMenuBar(jm);

		// jp_center.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jp_west.setBackground(color);
		jp_east.setBackground(color);
		jp_south.setBackground(color);
		jp_center.setBackground(color);
		jp_north.setBackground(color);

		ImageIcon farbe = platz1;
		JPanel jpPlatz = new JPanel();
		jpPlatz.setPreferredSize(new Dimension(40, 40));
		jpPlatz.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		for(int i = 0; i < startfelder.length; i++){
			for(int j = 0; j < startfelder[i].length; j++){
				startfelder[i][j] = new JLabel();
				startfelder[i][j].setLayout(new BorderLayout());
			}
		}
		
		for(int i = 0; i < endfelder.length; i++){
			for(int j = 0; j < endfelder[i].length; j++){
				endfelder[i][j] = new JLabel();
				endfelder[i][j].setLayout(new BorderLayout());
			}
		}
		
		for(int i = 0; i < normaleFelder.length; i++){
			normaleFelder[i] = new JLabel();
			normaleFelder[i].setLayout(new BorderLayout());
		}
		
		for(int i = 0; i < spielfiguren.length; i++){
			for(int j = 0; j < spielfiguren[i].length; j++){
				if(i == 0){
					spielfiguren[i][j] = new JButton(rot1);
				}
				if(i == 1){
					spielfiguren[i][j] = new JButton(blau1);
				}
				if(i == 2){
					spielfiguren[i][j] = new JButton(gruen1);
				}
				if(i == 3){
					spielfiguren[i][j] = new JButton(gelb1);
				}
				spielfiguren[i][j].setBorder(null);
				spielfiguren[i][j].setContentAreaFilled(false);
			}
		}
		
		jp_center.add(brett);

		/**
		 * StartFeldRot Position
		 */
		brett.add(startfelder[0][1]);
		startfelder[0][1].setBounds(71, 71, 40, 40); // Position S2;
		brett.add(startfelder[0][2]);
		startfelder[0][2].setBounds(71, 116, 40, 40); // Position S3
		brett.add(startfelder[0][0]);
		startfelder[0][0].setBounds(116, 71, 40, 40);// Position S1
		brett.add(startfelder[0][3]);
		startfelder[0][3].setBounds(116, 116, 40, 40);// Position S4

		/**
		 * StartFeldBlau Position
		 */

		brett.add(startfelder[1][1]);
		startfelder[1][1].setBounds(490, 71, 40, 40); // Position S2;
		brett.add(startfelder[1][2]);
		startfelder[1][2].setBounds(490, 116, 40, 40); // Position S3
		brett.add(startfelder[0][0]);
		startfelder[0][0].setBounds(535, 71, 40, 40);// Position S1
		brett.add(startfelder[1][3]);
		startfelder[1][3].setBounds(535, 116, 40, 40);// Position S4
		/**
		 * StartFeldGruen Position
		 */
		brett.add(startfelder[2][1]);
		startfelder[2][1].setBounds(490, 492, 40, 40); // Position S2;
		brett.add(startfelder[2][2]);
		startfelder[2][2].setBounds(490, 537, 40, 40); // Position S3
		brett.add(startfelder[2][0]);
		startfelder[2][0].setBounds(535, 492, 40, 40);// Position S1
		brett.add(startfelder[2][3]);
		startfelder[2][3].setBounds(535, 537, 40, 40);// Position S4

		/**
		 * StartFeldGelb Position
		 */
		brett.add(startfelder[3][1]);
		startfelder[3][1].setBounds(71, 493, 40, 40); // Position S2;
		brett.add(startfelder[3][2]);
		startfelder[3][2].setBounds(71, 538, 40, 40); // Position S3
		brett.add(startfelder[3][0]);
		startfelder[3][0].setBounds(116, 493, 40, 40);// Position S1
		brett.add(startfelder[3][3]);
		startfelder[3][3].setBounds(116, 538, 40, 40);// Position S4

		/**
		 * EndFeldRot Position
		 */
		brett.add(endfelder[0][0]);
		endfelder[0][0].setBounds(116, 304, 40, 40); // Position E1;
		brett.add(endfelder[0][1]);
		endfelder[0][1].setBounds(162, 304, 40, 40); // Position E2:
		brett.add(endfelder[0][2]);
		endfelder[0][2].setBounds(208, 304, 40, 40);// Position E3;
		brett.add(endfelder[0][3]);
		endfelder[0][3].setBounds(253, 304, 40, 40);// Position E4;

		/**
		 * EndFeldGruen Position
		 */
		brett.add(endfelder[1][3]);
		endfelder[1][3].setBounds(353, 304, 40, 40); // Position E4;
		brett.add(endfelder[1][2]);
		endfelder[1][2].setBounds(398, 304, 40, 40); // Position E3:
		brett.add(endfelder[1][1]);
		endfelder[1][1].setBounds(444, 304, 40, 40);// Position E2;
		brett.add(endfelder[1][0]);
		endfelder[1][0].setBounds(490, 304, 40, 40);// Position E1;

		/**
		 * EndFeldBlau Position
		 */
		brett.add(endfelder[2][0]);
		endfelder[2][0].setBounds(304, 116, 40, 40); // Position E1;
		brett.add(endfelder[2][1]);
		endfelder[2][1].setBounds(304, 162, 40, 40); // Position E2:
		brett.add(endfelder[2][2]);
		endfelder[2][2].setBounds(304, 208, 40, 40);// Position E3;
		brett.add(endfelder[2][3]);
		endfelder[2][3].setBounds(304, 253, 40, 40);// Position E4;

		/**
		 * EndFeldGelb Position
		 */
		brett.add(endfelder[3][3]);
		endfelder[3][3].setBounds(304, 356, 40, 40); // Position E4;
		brett.add(endfelder[3][2]);
		endfelder[3][2].setBounds(304, 401, 40, 40); // Position E3:
		brett.add(endfelder[3][1]);
		endfelder[3][1].setBounds(304, 447, 40, 40);// Position E2;
		brett.add(endfelder[3][0]);
		endfelder[3][0].setBounds(304, 492, 40, 40);// Position E1;

		/**
		 * Normale Felder start bei 1
		 */

		brett.add(normaleFelder[0]);
		normaleFelder[0].setBounds(71, 253, 40, 40); // Position 1;
		brett.add(normaleFelder[1]);
		normaleFelder[1].setBounds(116, 253, 40, 40); // Position 2;
		brett.add(normaleFelder[2]);
		normaleFelder[2].setBounds(162, 253, 40, 40); // Position 3;
		brett.add(normaleFelder[3]);
		normaleFelder[3].setBounds(207, 253, 40, 40); // Position 4;
		brett.add(normaleFelder[4]);
		normaleFelder[4].setBounds(253, 253, 40, 40); // Position 5;
		brett.add(normaleFelder[5]);
		normaleFelder[5].setBounds(253, 207, 40, 40); // Position 6;
		brett.add(normaleFelder[6]);
		normaleFelder[6].setBounds(253, 162, 40, 40); // Position 7;
		brett.add(normaleFelder[7]);
		normaleFelder[7].setBounds(253, 116, 40, 40); // Position 8;
		brett.add(normaleFelder[8]);
		normaleFelder[8].setBounds(253, 71, 40, 40); // Position 9;
		brett.add(normaleFelder[9]);
		normaleFelder[9].setBounds(304, 71, 40, 40); // Position 10;
		brett.add(normaleFelder[10]);
		normaleFelder[10].setBounds(353, 71, 40, 40); // Position 11;
		brett.add(normaleFelder[11]);
		normaleFelder[11].setBounds(353, 116, 40, 40); // Position 12;
		brett.add(normaleFelder[12]);
		normaleFelder[12].setBounds(353, 162, 40, 40); // Position 13;
		brett.add(normaleFelder[13]);
		normaleFelder[13].setBounds(353, 207, 40, 40); // Position 14;
		brett.add(normaleFelder[14]);
		normaleFelder[14].setBounds(353, 253, 40, 40); // Position 15;
		brett.add(normaleFelder[15]);
		normaleFelder[15].setBounds(399, 253, 40, 40); // Position 16;
		brett.add(normaleFelder[16]);
		normaleFelder[16].setBounds(444, 253, 40, 40); // Position 17;
		brett.add(normaleFelder[17]);
		normaleFelder[17].setBounds(490, 253, 40, 40); // Position 18;
		brett.add(normaleFelder[18]);
		normaleFelder[18].setBounds(535, 253, 40, 40); // Position 19;
		brett.add(normaleFelder[19]);
		normaleFelder[19].setBounds(535, 304, 40, 40); // Position 20;
		brett.add(normaleFelder[20]);
		normaleFelder[20].setBounds(535, 355, 40, 40); // Position 21;
		brett.add(normaleFelder[21]);
		normaleFelder[21].setBounds(490, 355, 40, 40); // Position 22;
		brett.add(normaleFelder[22]);
		normaleFelder[22].setBounds(444, 355, 40, 40); // Position 23;
		brett.add(normaleFelder[23]);
		normaleFelder[23].setBounds(399, 355, 40, 40); // Position 24;
		brett.add(normaleFelder[24]);
		normaleFelder[24].setBounds(353, 355, 40, 40); // Position 25;
		brett.add(normaleFelder[25]);
		normaleFelder[25].setBounds(353, 401, 40, 40); // Position 26;
		brett.add(normaleFelder[26]);
		normaleFelder[26].setBounds(353, 446, 40, 40); // Position 27;
		brett.add(normaleFelder[27]);
		normaleFelder[27].setBounds(353, 492, 40, 40); // Position 28;
		brett.add(normaleFelder[28]);
		normaleFelder[28].setBounds(353, 537, 40, 40); // Position 29;
		brett.add(normaleFelder[29]);
		normaleFelder[29].setBounds(304, 537, 40, 40); // Position 30;
		brett.add(normaleFelder[30]);
		normaleFelder[30].setBounds(253, 538, 40, 40); // Position 31;
		brett.add(normaleFelder[31]);
		normaleFelder[31].setBounds(253, 493, 40, 40); // Position 32;
		brett.add(normaleFelder[32]);
		normaleFelder[32].setBounds(253, 447, 40, 40); // Position 33;
		brett.add(normaleFelder[33]);
		normaleFelder[33].setBounds(253, 402, 40, 40); // Position 34;
		brett.add(normaleFelder[34]);
		normaleFelder[34].setBounds(253, 356, 40, 40); // Position 35;
		brett.add(normaleFelder[35]);
		normaleFelder[35].setBounds(207, 356, 40, 40); // Position 36;
		brett.add(normaleFelder[36]);
		normaleFelder[36].setBounds(162, 356, 40, 40); // Position 37;
		brett.add(normaleFelder[37]);
		normaleFelder[37].setBounds(116, 356, 40, 40); // Position 38;
		brett.add(normaleFelder[38]);
		normaleFelder[38].setBounds(71, 356, 40, 40); // Position 39;
		brett.add(normaleFelder[39]);
		normaleFelder[39].setBounds(71, 304, 40, 40); // Position 40;
		
		setzeSpielfigur("ROT", 0, "E1 ROT");
		
		jp_south.add(console);

		/**
		 * East Steuerung
		 */

		JLabel jlSpieler = new JLabel("Spieler");
		jlSpieler.setForeground(Color.WHITE);

		JLabel jlFarbe = new JLabel(gruen1);
		JLabel jlSpielerEnde = new JLabel("ist am Zug");

		jlSpielerEnde.setForeground(Color.WHITE);

		JLabel jlWuerfeln = new JLabel("Würfeln:");
		jlWuerfeln.setForeground(Color.WHITE);

		JPanel jpZug = new JPanel();
		JPanel jpWuerfel = new JPanel();
		JPanel jpWuerfelTxt = new JPanel();
		JPanel jbButtonWuerfel = new JPanel();

		jp_east.setLayout(new BorderLayout());

		jpZug.add(jlSpieler);
		jpZug.add(jlFarbe);
		jpZug.add(jlSpielerEnde);
		jpZug.setBackground(color);
		jp_east.add(jpWuerfelTxt, BorderLayout.CENTER);
		jp_east.add(jpZug, BorderLayout.NORTH);
		jp_east.add(jpWuerfel, BorderLayout.SOUTH);
		jpWuerfel.setLayout(new BorderLayout());
		jpWuerfelTxt.add(jlWuerfeln);

		jpWuerfelTxt.setBackground(color);

		jpWuerfel.setPreferredSize(new Dimension(155, 350));
		jpWuerfel.setLayout(new BorderLayout());
		jpWuerfel.add(jbButtonWuerfel, BorderLayout.NORTH);
		jbButtonWuerfel.add(buttonWuerfel);
		jbButtonWuerfel.setBackground(color);

		jpZug.setPreferredSize(new Dimension(155, 250));

		jpWuerfel.setBackground(color);
		buttonWuerfel.setContentAreaFilled(false);
		buttonWuerfel.setBorder(null);
		// buttonWuerfel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		// jpWuerfel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		/**
		 * West Bereich
		 */
		jp_west.setPreferredSize(new Dimension(150, 1));

		jp_west.setLayout(new BorderLayout());

		jp_west.add(new JLabel(bild), BorderLayout.EAST);

		jm.add(jMenu);
		jm.add(jMenu2);
		jMenu.add(jmi);
		jMenu.add(jmi1);
		jMenu.add(jmi2);

		jf.setSize(1000, 800);

	}

	/**
	 * 
	 * @return Gibt den EventHandler dieses GUIs zurrueck
	 */
	public EventHandler getEventHandler() {
		return eH;

	}

	/**
	 * Wird von EventHandler aufgerufen, wenn das Spiel gewonnen wurde
	 * @param gewinnerName Der Name des Gewinners
	 * @param gewinnerFarbe Die Farbe des Gewinners
	 */
	public void spielGewonnen(String gewinnerName, FarbEnum gewinnerFarbe) {
		// TODO Auto-generated method stub

	}

	/**
	 * Wird vom EventHandler aufgerufen und setzt die angegebene Figur auf das angegebene Feld
	 * @param farbe Die Farbe der zu versetzenden Figur
	 * @param figurID Die ID der Figur
	 * @param feldID Das Feld auf dem sich die Figur jetzt befindet
	 */
	public void setzeSpielfigur(String farbe, int figurID, String feldID) {
		int farbeInt = FarbEnum.vonString(farbe).ordinal();
		JLabel jl = null;
		JButton jb = spielfiguren[farbeInt][figurID];
		try{
			int feldIDint = Integer.parseInt(feldID);
			jl = normaleFelder[feldIDint];
		} catch(Exception e){
			String[] teile = feldID.split(" ");
			int feldFarbeInt = FarbEnum.vonString(teile[1]).ordinal();
			int intFeldNr = Integer.parseInt(teile[0].substring(1)) - 1;
			if(teile[0].contains("S")){
				jl = startfelder[feldFarbeInt][intFeldNr];
			} else {
				jl = endfelder[feldFarbeInt][intFeldNr];
			}
		}
		if(jl != null){
			jl.add(jb);
		}
	}

	/**
	 * 
	 * @return Gibt den Button "Spieler Anlegen" aus dem DialogGUI zurueck
	 */
	public Object getButtonWeiter() {
		return this.diaGui.getButtonWeiter();
	}
	
	/**
	 * Ruft ein neues DialogGUI auf um die Spielerdaten abzufragen
	 * @param neuerSpielerNummer Die Nummer des Spielers dessen Daten abgefragt werden sollen
	 */
	public void frageSpielerDaten(int neuerSpielerNummer) {
		try {
			this.diaGui = new DialogGUI(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					null,
					"Konnte Spielerdatendialog nicht erstellen: "
							+ e.getMessage());
		}
	}

	/**
	 * @return Gibt den Button der ersten Spelfigur zurrueck
	 */
	public Object getButtonFigur1(FarbEnum farbe) {
		int farbeInt = farbe.ordinal();
		return spielfiguren[farbeInt][0];
	}

	/**
	 * @return Gibt den Button der zweiten Spelfigur zurrueck
	 */
	public Object getButtonFigur2(FarbEnum farbe) {
		int farbeInt = farbe.ordinal();
		return spielfiguren[farbeInt][1];
	}

	/**
	 * @return Gibt den Button der dritten Spelfigur zurrueck
	 */
	public Object getButtonFigur3(FarbEnum farbe) {
		int farbeInt = farbe.ordinal();
		return spielfiguren[farbeInt][2];
	}

	/**
	 * @return Gibt den Button der vierten Spelfigur zurrueck
	 */
	public Object getButtonFigur4(FarbEnum farbe) {
		int farbeInt = farbe.ordinal();
		return spielfiguren[farbeInt][3];
	}

	/**
	 * @return Spieleranzahl 1 Button
	 */
	public Object getButtonSpielerZahl1() {
		return this.spielerAnzahlGui.getButtonSpielerZahl1();
	}

	/**
	 * @return Spieleranzahl 2 Button
	 */
	public Object getButtonSpielerZahl2() {
		return this.spielerAnzahlGui.getButtonSpielerZahl2();
	}

	/**
	 * @return Spieleranzahl 3 Button
	 */
	public Object getButtonSpielerZahl3() {
		return this.spielerAnzahlGui.getButtonSpielerZahl3();
	}

	/**
	 * @return Spieleranzahl 4 Button
	 */
	public Object getButtonSpielerZahl4() {
		return this.spielerAnzahlGui.getButtonSpielerZahl4();
	}

	/**
	 * Gibt eine Statusnachricht aus
	 * @param s Die Nachricht
	 */
	public void setzeStatusNachricht(String s) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gibt eine Warnung aus 
	 * @param s Die Warnung
	 */
	public void zeigeWarnung(String s) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gibt einen Fehler aus
	 * @param s Die Fehlernachricht
	 */
	public void ziegeFehler(String s) {
		// TODO Auto-generated method stub

	}

	/**
	 * Setzt den Wuerfel so, dass er die gewuerfelte Zah anzeigt
	 * @param gewuerfelteZahl Die gewuerfelte Zahl
	 */
	public void zeigeWuerfel(int gewuerfelteZahl) {
		// TODO Auto-generated method stub

	}

	/**
	 * Setzt den Spieler der am Zug ist
	 * @param name Der Name des Spielers
	 */
	public void setzeSpielerAmZug(String name) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Gibt den Button fuer das Wuerfeln zurrueck
	 */
	public Object getButtonWuerfeln() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  Ruft ein neues Dialogfenster zur Abfrage der Spieleranzahl
	 */
	public void frageGewuenschteSpielerAnzahl() {
		this.spielerAnzahlGui = new SpielerAnzahlGUI(this.getEventHandler());
	}

	/**
	 * Schliesst den Dialog zur Abfrage der Spieleranzahl
	 */
	public void schliesseGewuenschteSpielerAnzahl() {
		this.spielerAnzahlGui.schliessen();
	}

}
