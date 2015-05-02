package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import Kuenstliche_Intelligenz.KiTypEnum;
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
	private JEditorPane console;
	private HTMLFormatierer formatierer;
	private Container jp_west;
	private ImageIcon w5;
	private ImageIcon w4;
	private ImageIcon w1;
	private ImageIcon w2;
	private ImageIcon w3;
	private ImageIcon w6;
	private JLabel wuerfel;
	private JLabel jlFarbe;
	private ImageIcon blau1;
	private ImageIcon gruen1;
	private ImageIcon rot1;
	private ImageIcon gelb1;
	private JButton buttonWuerfel;
	private JMenuItem jmi;
	private JMenuItem jmi1;
	private JMenuItem jmi2;
	private JMenuItem jmi3;
	private JButton jbja;
	private JButton jbnein;
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
	 * 
	 * @throws IOException
	 *             Das Laden der Bolder lief schief
	 */
	public madnGUI() throws IOException {
		this.eH = new EventHandler(this);

		this.erstelleGUI();
		this.spielerAnzahlGui = new SpielerAnzahlGUI(this.getEventHandler());
	}

	/**
	 * Erstellt alle Komponenten der Haupgui und fuegt diese zusammen
	 * 
	 * @throws IOException
	 *             Die Bilder konnten nicht geladen werden
	 */
	public void erstelleGUI() throws IOException {
		File imageFile = new File("bilder/madn.jpg");
		BufferedImage madn = ImageIO.read(imageFile);

		File imageFile3 = new File("bilder/gruen.png");
		BufferedImage gruen = ImageIO.read(imageFile3);

		File imageFileWuerfel_1 = new File("bilder/wuerfel_1.png");
		BufferedImage wuerfel_1 = ImageIO.read(imageFileWuerfel_1);

		File imageFileWuerfel_2 = new File("bilder/wuerfel_2.png");
		BufferedImage wuerfel_2 = ImageIO.read(imageFileWuerfel_2);

		File imageFileWuerfel_3 = new File("bilder/wuerfel_3.png");
		BufferedImage wuerfel_3 = ImageIO.read(imageFileWuerfel_3);

		File imageFileWuerfel_4 = new File("bilder/wuerfel_4.png");
		BufferedImage wuerfel_4 = ImageIO.read(imageFileWuerfel_4);

		File imageFileWuerfel_5 = new File("bilder/wuerfel_5.png");
		BufferedImage wuerfel_5 = ImageIO.read(imageFileWuerfel_5);

		File imageFileWuerfel_6 = new File("bilder/wuerfel_6.png");
		BufferedImage wuerfel_6 = ImageIO.read(imageFileWuerfel_6);

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

		w1 = new ImageIcon(wuerfel_1);
		w1.setImage(w1.getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		w2 = new ImageIcon(wuerfel_2);
		w2.setImage(w2.getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		w3 = new ImageIcon(wuerfel_3);
		w3.setImage(w3.getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		w4 = new ImageIcon(wuerfel_4);
		w4.setImage(w4.getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		w5 = new ImageIcon(wuerfel_5);
		w5.setImage(w5.getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		w6 = new ImageIcon(wuerfel_6);
		w6.setImage(w6.getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		Color color = Color.BLACK;
		JFrame jf = new JFrame("Mensch-Ärgere-Dich-Nicht");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setSize(1000, 800);
		jf.setLocationRelativeTo(null);

		ImageIcon platz1 = new ImageIcon(platz);

		ImageIcon wuerfeln1 = new ImageIcon(wuerfeln);
		wuerfeln1.setImage(wuerfeln1.getImage().getScaledInstance(100, 100,
				Image.SCALE_DEFAULT));

		blau1 = new ImageIcon(blau);
		gruen1 = new ImageIcon(gruen);
		rot1 = new ImageIcon(rot);
		gelb1 = new ImageIcon(gelb);

		JPanel jp_north = new JPanel();
		JPanel jp_south = new JPanel();
		JPanel jp_west = new JPanel();
		JPanel jp_east = new JPanel();
		JPanel jp_center = new JPanel();

		JLabel brett = new JLabel(new ImageIcon(madn));

		buttonWuerfel = new JButton(wuerfeln1);
		buttonWuerfel.addActionListener(this.getEventHandler());

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
		jmi3=new JMenuItem("Neues Spiel");
		jmi = new JMenuItem("Speichern");
		jmi1 = new JMenuItem("Laden");
		jmi2 = new JMenuItem("Beenden");

		/**
		 * Spiel Log
		 */

		//jp_south.setPreferredSize(new Dimension(650, 80));

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

		for (int i = 0; i < startfelder.length; i++) {
			for (int j = 0; j < startfelder[i].length; j++) {
				startfelder[i][j] = new JLabel();
				startfelder[i][j].setLayout(new BorderLayout());
				//startfelder[i][j].setBorder(BorderFactory.createDashedBorder(Color.RED, 2, 1, 1, false));
			}
		}
		
		
		for (int i = 0; i < endfelder.length; i++) {
			for (int j = 0; j < endfelder[i].length; j++) {
				endfelder[i][j] = new JLabel();
				endfelder[i][j].setLayout(new BorderLayout());
				//endfelder[i][j].setBorder(BorderFactory.createDashedBorder(Color.RED, 2, 1, 1, false));
			}
		}

		for (int i = 0; i < normaleFelder.length; i++) {
			normaleFelder[i] = new JLabel();
			normaleFelder[i].setLayout(new BorderLayout());
		}

		for (int i = 0; i < spielfiguren.length; i++) {
			for (int j = 0; j < spielfiguren[i].length; j++) {
				if (i == 0) {
					spielfiguren[i][j] = new JButton(rot1);
				}
				if (i == 1) {
					spielfiguren[i][j] = new JButton(blau1);
				}
				if (i == 2) {
					spielfiguren[i][j] = new JButton(gruen1);
				}
				if (i == 3) {
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
		brett.add(startfelder[1][0]);
		startfelder[1][0].setBounds(535, 71, 40, 40);// Position S1
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

		JScrollPane scrollPane = new JScrollPane();
		console = new JEditorPane("text/html", "");
		scrollPane.setPreferredSize(new Dimension(650, 80));
		scrollPane.setViewportView(console);
		jp_south.add(scrollPane);
		formatierer = new HTMLFormatierer(true);
		

		/**
		 * East Steuerung
		 */

		JLabel jlSpieler = new JLabel("Spieler");
		jlSpieler.setForeground(Color.WHITE);

		jlFarbe = new JLabel(gruen1);
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

		wuerfel = new JLabel();
		wuerfel.setIcon(w1);
		jp_west.add(wuerfel, BorderLayout.EAST);

		jm.add(jMenu);
		jm.add(jMenu2);
		jMenu.add(jmi3);
		jMenu.add(jmi);
		jMenu.add(jmi1);
		jMenu.add(jmi2);
		
		spielGewonnen("PIFF", FarbEnum.BLAU);
		setzeSpielfigur("ROT", 0, "40");
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
	 * 
	 * @param gewinnerName
	 *            Der Name des Gewinners
	 * @param gewinnerFarbe
	 *            Die Farbe des Gewinners
	 */
	public void spielGewonnen(String gewinnerName, FarbEnum gewinnerFarbe) {
	
		JLabel jl= new JLabel("Neues Spiel starten?");
		jbja = new JButton("Ja");
		jbnein = new JButton("Nein");
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		JPanel jpg = new JPanel();
		JPanel jp2 = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		jp.setAlignmentX(Component.CENTER_ALIGNMENT);
		jd.add(jp);
		
		
		JLabel turk = new JLabel("Spieler: " + gewinnerName);
		
		jp.add(jpg);
		jpg.add(turk);
		if (gewinnerFarbe == FarbEnum.ROT) {
			JLabel jl2 = new JLabel(rot1);
			jpg.add(jl2);
		}
		if (gewinnerFarbe == FarbEnum.BLAU) {
			JLabel jl2 = new JLabel(blau1);
			jpg.add(jl2);
		}
		if (gewinnerFarbe == FarbEnum.GRUEN) {
			JLabel jl2 = new JLabel(gruen1);
			jpg.add(jl2);
		}
		if (gewinnerFarbe == FarbEnum.GELB) {
			JLabel jl2 = new JLabel(gelb1);
			jpg.add(jl2);
		}
		JLabel jl3 = new JLabel("hat gewonnen! HIPP HIPP HURRA!!!");
		jpg.add(jl3);
		
		JPanel jp1 = new JPanel();
		jp2.add(jl);
		jp.add(jp2);
		jp1.add(jbja);
		jp1.add(jbnein);
		jp.add(jp1);
		jd.pack();
		jd.setVisible(true);
		jd.setTitle("Gewinner: " + gewinnerName);
		
		
		jd.setLocationRelativeTo(null);
	}

	/**
	 * Wird vom EventHandler aufgerufen und setzt die angegebene Figur auf das
	 * angegebene Feld
	 * 
	 * @param farbe
	 *            Die Farbe der zu versetzenden Figur
	 * @param figurID
	 *            Die ID der Figur
	 * @param feldID
	 *            Das Feld auf dem sich die Figur jetzt befindet
	 */
	public void setzeSpielfigur(String farbe, int figurID, String feldID) {
		int farbeInt = FarbEnum.vonString(farbe).ordinal();
		JLabel jl = null;
		JButton jb = spielfiguren[farbeInt][figurID];
		try {
			int feldIDint = Integer.parseInt(feldID) - 1;
			jl = normaleFelder[feldIDint];
		} catch (NumberFormatException e) {
			String[] teile = feldID.split(" ");
			int feldFarbeInt = FarbEnum.vonString(teile[1]).ordinal();
			int intFeldNr = Integer.parseInt(teile[0].substring(1)) - 1;
			if (teile[0].contains("S")) {
				jl = startfelder[feldFarbeInt][intFeldNr];
			} else {
				jl = endfelder[feldFarbeInt][intFeldNr];
			}
		}
		if (jl != null) {
			jl.add(jb);
		}
	}

	/**
	 * 
	 * @return Gibt den Button "Spieler Anlegen" aus dem DialogGUI zurueck
	 */
	public Object getButtonWeiter() {
		if (this.diaGui != null) {
			return this.diaGui.getButtonWeiter();
		}
		return null;
	}

	/**
	 * Ruft ein neues DialogGUI auf um die Spielerdaten abzufragen
	 * 
	 * @param neuerSpielerNummer
	 *            Die Nummer des Spielers dessen Daten abgefragt werden sollen
	 */
	public void frageSpielerDaten(int neuerSpielerNummer) {
		try {
			this.diaGui = new DialogGUI(this, neuerSpielerNummer);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					null,
					"Konnte Spielerdatendialog nicht erstellen: "
							+ e.getMessage());
		}
	}
	public Object getSpeichern(){
		return this.jmi;
	}
	
	public Object getLaden(){
		return this.jmi1;
	}
	
	public Object getBeenden(){
		return this.jmi2;
	}
	public Object getNeuesSpiel(){
		return this.jmi3;
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
	 * 
	 * @param s
	 *            Die Nachricht
	 */
	public void setzeStatusNachricht(String s) {
		formatierer.schreibeInfo(s);
		console.setText(formatierer.konstruiereHTML());
	}
	
	public JButton getSpielneustartenJa(){
		return this.jbja;
	}
	public JButton getSpielneustartenNein(){
		return this.jbnein;
	}

	/**
	 * Gibt eine Warnung aus
	 * 
	 * @param s
	 *            Die Warnung
	 */
	public void zeigeWarnung(String s) {
		formatierer.schreibeWarnung(s);
		console.setText(formatierer.konstruiereHTML());
	}

	/**
	 * Gibt einen Fehler aus
	 * 
	 * @param s
	 *            Die Fehlernachricht
	 */
	public void zeigeFehler(String s) {
		formatierer.schreibeFehler(s);
		console.setText(formatierer.konstruiereHTML());
	}

	/**
	 * Setzt den Wuerfel so, dass er die gewuerfelte Zah anzeigt
	 * 
	 * @param gewuerfelteZahl
	 *            Die gewuerfelte Zahl
	 */
	public void zeigeWuerfel(int gewuerfelteZahl) {
		switch (gewuerfelteZahl) {
		case 1: {
			wuerfel.setIcon(w1);
			break;
		}
		case 2: {
			wuerfel.setIcon(w2);
			break;
		}
		case 3: {
			wuerfel.setIcon(w3);
			break;
		}
		case 4: {
			wuerfel.setIcon(w4);
			break;
		}
		case 5: {
			wuerfel.setIcon(w5);
			break;
		}
		case 6: {
			wuerfel.setIcon(w6);
			break;
		}
		default: {
			zeigeFehler("Die gewürfelte Zahl ist nicht zwischen 1-6!");
		}
		}

	}

	/**
	 * Setzt den Spieler der am Zug ist
	 * 
	 * @param name
	 *            Die Farbe des Spielers
	 */
	public void setzeSpielerAmZug(String farbe) {
		if (farbe.equals("ROT")) {
			jlFarbe.setIcon(rot1);
		}
		if (farbe.equals("GRUEN")) {
			jlFarbe.setIcon(gruen1);
		}
		if (farbe.equals("BLAU")) {
			jlFarbe.setIcon(blau1);
		}
		if (farbe.equals("GELB")) {
			jlFarbe.setIcon(gelb1);
		}
	}

	/**
	 * @return Gibt den Button fuer das Wuerfeln zurrueck
	 */
	public Object getButtonWuerfeln() {
		return this.buttonWuerfel;
	}

	/**
	 * Ruft ein neues Dialogfenster zur Abfrage der Spieleranzahl
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

	public String getNeuerSpielerName() {
		if (this.diaGui != null) {
			return this.diaGui.getName();
		} else {
			this.zeigeFehler("Versuche Name eines neuen Spielers zu getten ohne dass DialogGui existiert");
			return "";
		}
	}

	public FarbEnum getNeuerSpielerFarbe() {
		if (this.diaGui != null) {
			return this.diaGui.getFarbe();
		} else {
			this.zeigeFehler("Versuche Farbe eines neuen Spielers zu getten ohne dass DialogGui existiert");
			return FarbEnum.ROT;
		}
	}

	public KiTypEnum getNeuerSpielerKiTyp() {
		if (this.diaGui != null) {
			return this.diaGui.getKiTyp();
		} else {
			this.zeigeFehler("Versuche KIType eines neuen Spielers zu getten ohne dass DialogGui existiert");
			return null;
		}
	}

	public Object getNaechsterZugButton() {
		return this.buttonWuerfel;
	}

	public void schliesseSpielerDaten() {
		if (this.diaGui != null) {
			diaGui.schliessen();
		}
	}
}
