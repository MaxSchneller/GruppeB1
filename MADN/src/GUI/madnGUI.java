package GUI;
	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
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



	
		 public static void main(String [] args) throws IOException{
			 
		
		File imageFile = new File("madn.jpg");
		BufferedImage madn = ImageIO.read(imageFile);
		
		File imageFile2 = new File("wuerfel.png");
		BufferedImage wuerfel = ImageIO.read(imageFile2);
		
		Color color=Color.BLACK;
				
		JFrame jf=new JFrame("Mensch-Ärgere-Dich-Nicht");
		
		JMenuItem jmi=new JMenuItem("Speichern");
		JMenuItem jmi1=new JMenuItem("Laden");
		JMenuItem jmi2=new JMenuItem("Beenden");
		JPanel jp_north=new JPanel();
		JPanel jp_south=new JPanel();
		JPanel jp_west=new JPanel();
		JPanel jp_east=new JPanel();
		JPanel jp_center=new JPanel();
		
		JButton buttonWuerfel=new JButton("Wuerfeln");
		JMenuBar jm=new JMenuBar();
		JMenu jMenu=new JMenu("Datei");
		JMenu jMenu2=new JMenu("Hilfe");
		JTextArea console=new JTextArea(1,60);
		console.setEditable(false);
		jp_west.setBackground(color);
		jp_east.setBackground(color);
		jp_south.setBackground(color);
		jp_center.setBackground(color);
		jp_north.setBackground(color);
		jf.setLayout(new BorderLayout());
		jf.add(jp_north, BorderLayout.NORTH);
		jf.add(jp_south, BorderLayout.SOUTH);
		jf.add(jp_east, BorderLayout.EAST);
		jf.add(jp_west, BorderLayout.WEST);
		jf.add(jp_center, BorderLayout.CENTER);
		jp_east.add(buttonWuerfel);
		jp_center.add(new JLabel(new ImageIcon(madn)));
		jf.setJMenuBar(jm);
		jm.add(jMenu);
		jm.add(jMenu2);
		jMenu.add(jmi);
		jMenu.add(jmi1);
		jMenu.add(jmi2);
		jp_south.add(console);
		
		
		ImageIcon bild=new ImageIcon(wuerfel);
		bild.setImage(bild.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		
		
		
		jp_west.add(new JLabel(bild));
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		jf.setSize(xSize,ySize);
		
		 }
		

		
	}


