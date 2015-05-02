package GUI;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class FileChooserGUI {
	    
	    
	    public static void dateiOeffnen(){
	    	   JFileChooser chooser = new JFileChooser();
	    	 
		        // Erzeugung eines neuen Frames mit dem Titel "Dateiauswahl"
		        JFrame jf = new JFrame("Dateiauswahl");
		        // Wir setzen die Breite auf 450 und die Höhe 300 pixel
		        jf.setSize(450,300);
		        // Hole den ContentPane und füge diesem unseren JFileChooser hinzu      
		        jf.getContentPane().add(chooser);
		        // Wir lassen unseren Frame anzeigen
		        jf.setVisible(true);
		        
		        
		     
		       
		        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
	    	
	    }
	    public static void dateiSpeicher(){
	    	   JFileChooser chooser = new JFileChooser();
		        // Erzeugung eines neuen Frames mit dem Titel "Dateiauswahl"
		        JFrame jf = new JFrame("Dateiauswahl");
		        // Wir setzen die Breite auf 450 und die Höhe 300 pixel
		        jf.setSize(450,300);
		        // Hole den ContentPane und füge diesem unseren JFileChooser hinzu      
		        jf.getContentPane().add(chooser);
		        // Wir lassen unseren Frame anzeigen
		        jf.setVisible(true);
		        
		     
		       
		        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
	    }
	}

