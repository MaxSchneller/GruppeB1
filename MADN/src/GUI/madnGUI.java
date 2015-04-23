package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
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

import java.awt.Image;

public class madnGUI {

	public static void main(String[] args) throws IOException {

		File imageFile = new File("bilder/madn.jpg");
		BufferedImage madn = ImageIO.read(imageFile);

		File imageFile2 = new File("bilder/wuerfel.png");
		BufferedImage wuerfel = ImageIO.read(imageFile2);

		ImageIcon bild = new ImageIcon(wuerfel);
		bild.setImage(bild.getImage().getScaledInstance(80, 80,
				Image.SCALE_DEFAULT));

		Color color = Color.BLACK;
		JFrame jf = new JFrame("Mensch-Ärgere-Dich-Nicht");
		
		
		
		
		JMenuItem jmi = new JMenuItem("Speichern");
		JMenuItem jmi1 = new JMenuItem("Laden");
		JMenuItem jmi2 = new JMenuItem("Beenden");
		JPanel jp_north = new JPanel();
		JPanel jp_south = new JPanel();
		JPanel jp_west = new JPanel();
		JPanel jp_east = new JPanel();
		JPanel jp_center = new JPanel();

		JButton buttonWuerfel = new JButton("Wuerfeln");
		
		JMenuBar jm = new JMenuBar();
		JMenu jMenu = new JMenu("Datei");
		JMenu jMenu2 = new JMenu("Hilfe");
		JTextArea console = new JTextArea(1, 60);
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
		
		jp_center.add(new JLabel(new ImageIcon(madn)));
		jp_south.add(console);
		jp_east.setPreferredSize(new Dimension(150,1));
		jp_east.setLayout(new BorderLayout());
		jp_east.add(buttonWuerfel, BorderLayout.CENTER);
		jp_west.setPreferredSize(new Dimension(150,1));
		
		jp_west.setLayout(new BorderLayout());
		
		

		jp_west.add(new JLabel(bild), BorderLayout.EAST);

		jm.add(jMenu);
		jm.add(jMenu2);
		jMenu.add(jmi);
		jMenu.add(jmi1);
		jMenu.add(jmi2);

		
		jf.setSize(1000, 1000);

	}

}
