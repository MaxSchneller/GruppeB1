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
import java.awt.Toolkit;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Image;

public class madnGUI {

	public static void main(String[] args) throws IOException {

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

		ImageIcon bild = new ImageIcon(wuerfel);
		bild.setImage(bild.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT));

		Color color = Color.BLACK;
		JFrame jf = new JFrame("Mensch-Ärgere-Dich-Nicht");
		
		jf.setResizable(false);
		
		ImageIcon platz1 = new ImageIcon(platz);
		
		
		ImageIcon blau1=new ImageIcon(blau);
		ImageIcon gruen1=new ImageIcon(gruen);
		ImageIcon rot1=new ImageIcon(rot);
		ImageIcon gelb1=new ImageIcon(gelb);
		
		JMenuItem jmi = new JMenuItem("Speichern");
		JMenuItem jmi1 = new JMenuItem("Laden");
		JMenuItem jmi2 = new JMenuItem("Beenden");
		JPanel jp_north = new JPanel();
		JPanel jp_south = new JPanel();
		JPanel jp_west = new JPanel();
		JPanel jp_east = new JPanel();
		JPanel jp_center = new JPanel();
		
		JLabel brett = new JLabel(new ImageIcon(madn));

		JButton buttonWuerfel = new JButton("Wuerfeln");
		
		JMenuBar jm = new JMenuBar();
		JMenu jMenu = new JMenu("Datei");
		JMenu jMenu2 = new JMenu("Hilfe");
		JTextArea console = new JTextArea(5, 60);
		console.setEditable(false);
		
		jf.setLayout(new BorderLayout());
		jf.add(jp_north, BorderLayout.NORTH);
		jf.add(jp_south, BorderLayout.SOUTH);
		jf.add(jp_east, BorderLayout.EAST);
		jf.add(jp_west, BorderLayout.WEST);
		jf.add(jp_center, BorderLayout.CENTER);
		jf.setJMenuBar(jm);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		//jp_center.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jp_west.setBackground(color);
		jp_east.setBackground(color);
		jp_south.setBackground(color);
		jp_center.setBackground(color);
		jp_north.setBackground(color);
		
		ImageIcon farbe= platz1;
		
		
		JPanel jpPlatz = new JPanel();
		jpPlatz.setPreferredSize(new Dimension(40,40));
		jpPlatz.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
		JLabel jl1 = new JLabel(rot1); 		//Position S2 Rot;
		JLabel jl2 = new JLabel(rot1);	 	//Position S3 Rot;
		JLabel jl3 = new JLabel(rot1); 		//Position S1 Rot;
		JLabel jl4 = new JLabel(rot1);		//Position S4 Rot;
		
		JLabel jl5 = new JLabel(blau1);		//Position S2 Blau;
		JLabel jl6 = new JLabel(blau1);		//Position S3 Blau;
		JLabel jl7 = new JLabel(blau1);		//Position S1 Blau;
		JLabel jl8 = new JLabel(blau1);		//Position S4 Blau;
		
		JLabel jl9 = new JLabel(gruen1);	//Position S2 Gruen;
		JLabel jl10 = new JLabel(gruen1);	//Position S3 Gruen;
		JLabel jl11 = new JLabel(gruen1);	//Position S1 Gruen;
		JLabel jl12 = new JLabel(gruen1);	//Position S4 Gruen;
		
		JLabel jl13 = new JLabel(gelb1);	//Position S2 Gelb;
		JLabel jl14 = new JLabel(gelb1);	//Position S3 Gelb;
		JLabel jl15 = new JLabel(gelb1);	//Position S1 Gelb;
		JLabel jl16 = new JLabel(gelb1);	//Position S4 Gelb;
		
		JLabel jl17 = new JLabel(farbe);	//Position E1 Rot;
		JLabel jl18 = new JLabel(farbe);	//Position E2 Rot;
		JLabel jl19 = new JLabel(farbe);	//Position E3 Rot;
		JLabel jl20 = new JLabel(farbe);	//Position E4 Rot;
		
		JLabel jl21 = new JLabel(farbe);	//Position E4 Gruen;
		JLabel jl22 = new JLabel(farbe);	//Position E3 Gruen;
		JLabel jl23= new JLabel(farbe);		//Position E2 Gruen;
		JLabel jl24 = new JLabel(farbe);	//Position E1 Gruen;
		
		JLabel jl25 = new JLabel(farbe);	//Position E1 Blau;
		JLabel jl26 = new JLabel(farbe);	//Position E2 Blau;
		JLabel jl27 = new JLabel(farbe);	//Position E3 Blau;
		JLabel jl28 = new JLabel(farbe);	//Position E4 Blau;
		
		JLabel jl29 = new JLabel(farbe);	//Position E4 Gelb;
		JLabel jl30 = new JLabel(farbe);	//Position E3 Gelb;
		JLabel jl31 = new JLabel(farbe);	//Position E2 Gelb;
		JLabel jl32 = new JLabel(farbe);	//Position E1 Gelb;
		
		JLabel jl33 = new JLabel(farbe);    //Noramles Feld 1
		JLabel jl34 = new JLabel(farbe);	//Noramles Feld 2
		JLabel jl35 = new JLabel(farbe);	//.
		JLabel jl36 = new JLabel(farbe);	//.
		JLabel jl37= new JLabel(farbe);		//.
		JLabel jl38 = new JLabel(farbe);	//.
		JLabel jl39= new JLabel(farbe);		//.
		JLabel jl40 = new JLabel(farbe);	//.
		JLabel jl41 = new JLabel(farbe);	//.
		JLabel jl42 = new JLabel(farbe);	//.
		JLabel jl43 = new JLabel(farbe);	//.
		JLabel jl44 = new JLabel(farbe);	//.
		JLabel jl45 = new JLabel(farbe);	//.
		JLabel jl46 = new JLabel(farbe);	//.
		JLabel jl47 = new JLabel(farbe);	//.
		JLabel jl48 = new JLabel(farbe);	//.
		JLabel jl49 = new JLabel(farbe);	//.
		JLabel jl50 = new JLabel(farbe);	//.
		JLabel jl51 = new JLabel(farbe);	//.
		JLabel jl52 = new JLabel(farbe);	//.
		JLabel jl53 = new JLabel(farbe);	//.
		JLabel jl54 = new JLabel(farbe);	//.
		JLabel jl55= new JLabel(farbe);		//.
		JLabel jl56 = new JLabel(farbe);	//.
		JLabel jl57 = new JLabel(farbe);	//.
		JLabel jl58 = new JLabel(farbe);	//.
		JLabel jl59 = new JLabel(farbe);	//.
		JLabel jl60 = new JLabel(farbe);	//.
		JLabel jl61 = new JLabel(farbe);	//.
		JLabel jl62 = new JLabel(farbe);	//.
		JLabel jl63 = new JLabel(farbe);	//.
		JLabel jl64 = new JLabel(farbe);	//.
		JLabel jl65 = new JLabel(farbe);	//.
		JLabel jl66 = new JLabel(farbe);	//.
		JLabel jl67 = new JLabel(farbe);	//.
		JLabel jl68 = new JLabel(farbe);	//.
		JLabel jl69 = new JLabel(farbe);	//.
		JLabel jl70 = new JLabel(farbe);	//.
		JLabel jl71 = new JLabel(farbe);	//.
		JLabel jl72 = new JLabel(farbe);	//Noramales Feld 40
	
		
		jp_center.add(brett);
		
	
		
		/**
		 * StartFeldRot Position
		 */
		brett.add(jl1);
		jl1.setBounds(71, 71, 40, 40); //Position S2;
		brett.add(jl2);
		jl2.setBounds(71, 116, 40, 40); // Position S3
		brett.add(jl3);
		jl3.setBounds(116, 71, 40, 40);// Position S1
		brett.add(jl4);
		jl4.setBounds(116, 116, 40, 40);// Position S4
		
		/**
		 * StartFeldBlau Position
		 */
		
		brett.add(jl5);
		jl5.setBounds(490, 71, 40, 40); //Position S2;
		brett.add(jl6);
		jl6.setBounds(490, 116, 40, 40); // Position S3
		brett.add(jl7);
		jl7.setBounds(535, 71, 40, 40);// Position S1
		brett.add(jl8);
		jl8.setBounds(535, 116, 40, 40);// Position S4
		/**
		 * StartFeldGruen Position
		 */
		brett.add(jl9);
		jl9.setBounds(490, 492, 40, 40); //Position S2;
		brett.add(jl10);
		jl10.setBounds(490, 537, 40, 40); // Position S3
		brett.add(jl11);
		jl11.setBounds(535, 492, 40, 40);// Position S1
		brett.add(jl12);
		jl12.setBounds(535, 537, 40, 40);// Position S4
		
		/**
		 * StartFeldGelb Position
		 */
		brett.add(jl13);
		jl13.setBounds(71, 493, 40, 40); //Position S2;
		brett.add(jl14);
		jl14.setBounds(71, 538, 40, 40); // Position S3
		brett.add(jl15);
		jl15.setBounds(116, 493, 40, 40);// Position S1
		brett.add(jl16);
		jl16.setBounds(116, 538, 40, 40);// Position S4
		
		
		/**
		 * EndFeldRot Position
		 */
		brett.add(jl17);
		jl17.setBounds(116, 304, 40, 40); //Position E1;
		brett.add(jl18);
		jl18.setBounds(162, 304, 40, 40); // Position E2:
		brett.add(jl19);
		jl19.setBounds(208, 304, 40, 40);// Position E3;
		brett.add(jl20);
		jl20.setBounds(253, 304, 40, 40);// Position E4;
		
		/**
		 * EndFeldGruen Position
		 */
		brett.add(jl21);
		jl21.setBounds(353, 304, 40, 40); //Position E4;
		brett.add(jl22);
		jl22.setBounds(398, 304, 40, 40); // Position E3:
		brett.add(jl23);
		jl23.setBounds(444, 304, 40, 40);// Position E2;
		brett.add(jl24);
		jl24.setBounds(490, 304, 40, 40);// Position E1;
		
		

		/**
		 * EndFeldBlau Position
		 */
		brett.add(jl25);
		jl25.setBounds(304, 116, 40, 40); //Position E1;
		brett.add(jl26);
		jl26.setBounds(304, 162, 40, 40); // Position E2:
		brett.add(jl27);
		jl27.setBounds(304, 208, 40, 40);// Position E3;
		brett.add(jl28);
		jl28.setBounds(304, 253, 40, 40);// Position E4;
		

		/**
		 * EndFeldGelb Position
		 */
		brett.add(jl29);
		jl29.setBounds(304, 356, 40, 40); //Position E4;
		brett.add(jl30);
		jl30.setBounds(304, 401, 40, 40); // Position E3:
		brett.add(jl31);
		jl31.setBounds(304, 447, 40, 40);// Position E2;
		brett.add(jl32);
		jl32.setBounds(304, 492, 40, 40);// Position E1;
		
		
		/**
		 * Normale Felder start bei 1
		 */
		
		brett.add(jl33);
		jl33.setBounds(71, 253, 40, 40); //Position 1;
		brett.add(jl34);
		jl34.setBounds(116, 253, 40, 40); //Position 2;
		brett.add(jl35);
		jl35.setBounds(162, 253, 40, 40); //Position 3;
		brett.add(jl36);
		jl36.setBounds(207, 253, 40, 40); //Position 4;
		brett.add(jl37);
		jl37.setBounds(253, 253, 40, 40); //Position 5;
		brett.add(jl38);
		jl38.setBounds(253, 207, 40, 40); //Position 6;
		brett.add(jl39);
		jl39.setBounds(253, 162, 40, 40); //Position 7;
		brett.add(jl40);
		jl40.setBounds(253, 116, 40, 40); //Position 8;
		brett.add(jl41);
		jl41.setBounds(253, 71, 40, 40); //Position 9;
		brett.add(jl42);
		jl42.setBounds(304, 71, 40, 40); //Position 10;
		brett.add(jl43);
		jl43.setBounds(353, 71, 40, 40); //Position 11;
		brett.add(jl44);
		jl44.setBounds(353, 116, 40, 40); //Position 12;
		brett.add(jl45);
		jl45.setBounds(353, 162, 40, 40); //Position 13;
		brett.add(jl46);
		jl46.setBounds(353, 207, 40, 40); //Position 14;
		brett.add(jl47);
		jl47.setBounds(353, 253, 40, 40); //Position 15;
		brett.add(jl48);
		jl48.setBounds(399, 253, 40, 40); //Position 16;
		brett.add(jl49);
		jl49.setBounds(444, 253, 40, 40); //Position 17;
		brett.add(jl50);
		jl50.setBounds(490, 253, 40, 40); //Position 18;
		brett.add(jl51);
		jl51.setBounds(535, 253, 40, 40); //Position 19;
		brett.add(jl52);
		jl52.setBounds(535, 304, 40, 40); //Position 20;
		brett.add(jl53);
		jl53.setBounds(535, 355, 40, 40); //Position 21;
		brett.add(jl54);
		jl54.setBounds(490, 355, 40, 40); //Position 22;
		brett.add(jl55);
		jl55.setBounds(444, 355, 40, 40); //Position 23;
		brett.add(jl56);
		jl56.setBounds(399, 355, 40, 40); //Position 24;
		brett.add(jl57);
		jl57.setBounds(353, 355, 40, 40); //Position 25;
		brett.add(jl58);
		jl58.setBounds(353, 401, 40, 40); //Position 26;
		brett.add(jl59);
		jl59.setBounds(353, 446, 40, 40); //Position 27;
		brett.add(jl60);
		jl60.setBounds(353, 492, 40, 40); //Position 28;
		brett.add(jl61);
		jl61.setBounds(353, 537, 40, 40); //Position 29;
		brett.add(jl62);
		jl62.setBounds(304, 537, 40, 40); //Position 30;
		brett.add(jl63);
		jl63.setBounds(253, 538, 40, 40); //Position 31;
		brett.add(jl64);
		jl64.setBounds(253, 493, 40, 40); //Position 32;
		brett.add(jl65);
		jl65.setBounds(253, 447, 40, 40); //Position 33;
		brett.add(jl66);
		jl66.setBounds(253, 402, 40, 40); //Position 34;
		brett.add(jl67);
		jl67.setBounds(253, 356, 40, 40); //Position 35;
		brett.add(jl68);
		jl68.setBounds(207, 356, 40, 40); //Position 36;
		brett.add(jl69);
		jl69.setBounds(162, 356, 40, 40); //Position 37;
		brett.add(jl70);
		jl70.setBounds(116, 356, 40, 40); //Position 38;
		brett.add(jl71);
		jl71.setBounds(71, 356, 40, 40); //Position 39;
		brett.add(jl72);
		jl72.setBounds(71, 304, 40, 40); //Position 40;
		
		
		//jp1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		

	
		
		
		
		
		
		jp_south.add(console);
		jp_east.setPreferredSize(new Dimension(150,1));
		jp_east.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
	
		gbc.gridx = 0;  // x-Position im gedachten Gitter
		gbc.gridy = 0;  // y-Position im gedachten Gitter
		gbc.gridheight = 2;  // zwei Gitter-Felder hoch
		gbc.fill=GridBagConstraints.HORIZONTAL;
	


		gbc.gridx = 1;  // x-Position im gedachten Gitter
		gbc.gridy = 1;  // y-Position im gedachten Gitter
		gbc.gridheight = 2;  // zwei Gitter-Felder hoch
		
		
		
		
		jp_east.add(buttonWuerfel);
		jp_west.setPreferredSize(new Dimension(150,1));
		
		jp_west.setLayout(new BorderLayout());
		buttonWuerfel.setPreferredSize(new Dimension(150,150));
		

		jp_west.add(new JLabel(bild), BorderLayout.EAST);

		jm.add(jMenu);
		jm.add(jMenu2);
		jMenu.add(jmi);
		jMenu.add(jmi1);
		jMenu.add(jmi2);

		
		jf.setSize(1000, 800);
	}
		
		
//		public JLabel[] Label(ImageIcon farbe){
//			JLabel[] labels =  new JLabel[72];
//					 
//					for(int i = 0;  i < labels.length; i++ ) {
//					  labels [i] = new JLabel ();
//		}
//					return labels;
		

	
	
}
