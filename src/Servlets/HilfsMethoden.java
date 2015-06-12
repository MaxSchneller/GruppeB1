package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Spiel.FarbEnum;

/**
 * Stellt oefter genutzte Funktionen bereit
 */
public class HilfsMethoden {
	
	/**
	 * Sucht den ServletContext nach "status" ab und fuegt am Anfang die gewuenschte Nachricht hinzu
	 * @param request Der erhaltene HttpRequest des Servlets
	 * @param nachricht Die Nachricht, die eingefuegt werden soll
	 */
	public static void fuegeStatusHinzu(HttpServletRequest request, String nachricht) {
		ServletContext ctx = request.getServletContext();
		String status = (String)ctx.getAttribute("status");
		
		if (status == null) {
			status = "";
		}
		
		String neuerStatus = nachricht + "\n" + status;
		
		ctx.setAttribute("status", neuerStatus);
		
	}
	/**
	 * Gibt die passende Spalte fuer dieses Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	public static int getSpalteFeld(String feldID) {
		try {
			int i = Integer.parseInt(feldID);
			return HilfsMethoden.getSpalteNormalesFeld(i);
		} catch (NumberFormatException e) {
			return HilfsMethoden.getSpalteSonderfeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Reihe fuer dieses Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	public static int getReiheFeld(String feldID) {
		try {
			int i = Integer.parseInt(feldID);
			return HilfsMethoden.getReiheNormalesFeld(i);
		} catch (NumberFormatException e) {
			return HilfsMethoden.getReiheSonderfeld(feldID);
		}
	}
	
	/**
	 * Konvertiert die gegebenen Figuren im Format Farbe, ID, FeldID in
	 * das Format Farbe, ID, Spalte, Zeile
	 * @param figuren Die zu konvertierenden Figuren aus spiel.getAlleFigurenPositionen
	 * @return Die Konvertierten Positionen
	 */
	public static String[][] konvertiereFigurenInZeileUndSpalte(String[][] figuren) {
		
		String[][]felder = new String [figuren.length][4];
		
		for(int i = 0; i < figuren.length; i ++){
			felder[i][0] = figuren[i][0];
			felder[i][1] = figuren[i][1];
			felder[i][2] = String.format("%d",HilfsMethoden.getSpalteFeld(figuren[i][2]));
			felder[i][3] = String.format("%d",HilfsMethoden.getReiheFeld(figuren[i][2]));
		}
		
		return felder;
	}
	
	public static void zeigeFehlerJSP(String nachricht, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.getSession().setAttribute("fehlerArg", nachricht);
		response.sendRedirect("fehler.jsp");
	}
	/**
	 * Gibt die passende Spalte fuer ein normales Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private static int getSpalteNormalesFeld(int feldInt) {
		switch (feldInt) {
		case 1:
		case 40:
		case 39: 
			return 0;
		case 2:
		case 38:
			return 1;
		case 3:
		case 37: 
			return 2;
		case 4:
		case 36: 
			return 3;
		case 10:
		case 30: 
			return 5;
		case 16:
		case 24: 
			return 7;
		case 17:
		case 23:
			return 8;
		case 18:
		case 22:
			return 9;
		case 19:
		case 20:
		case 21: 
			return 10;
		default: {
			if (HilfsMethoden.inIntervall(5, 9, feldInt) ||
					HilfsMethoden.inIntervall(31, 35, feldInt)) {
				return 4;
			} else if (HilfsMethoden.inIntervall(11, 15, feldInt) ||
					HilfsMethoden.inIntervall(25, 29, feldInt)) {
				return 6;
			} else {
				return -1;
			}
		}
		}
	}
	
	/**
	 * Gibt die passende Reihe fuer ein normales Feld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private static int getReiheNormalesFeld(int feldInt) {
		
		switch (feldInt) {
		case 9:
		case 10:
		case 11:
			return 10;
		case 8:
		case 12:
			return 9;
		case 7:
		case 13:
			return 8;
		case 6:
		case 14:
			return 7;
		case 40:
		case 20:
			return 5;
		case 34:
		case 26:
			return 3;
		case 33:
		case 27:
			return 2;
		case 32:
		case 28:
			return 1;
		case 31:
		case 30:
		case 29:
			return 0;
		default: {
			if (HilfsMethoden.inIntervall(1, 5, feldInt)||
					HilfsMethoden.inIntervall(15, 19, feldInt)) {
				return 6;
			} else if (HilfsMethoden.inIntervall(35, 39, feldInt) ||
					HilfsMethoden.inIntervall(21, 25, feldInt)) {
				return 4;
			} else {
				return -1;
			}
		}
		}
	}
	
	/**
	 * Gibt die passende Spalte fuer ein Start- oder Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private static int getSpalteSonderfeld(String feldID) {
		if (feldID.contains("S")) {
			return HilfsMethoden.getSpalteStartFeld(feldID);
		} else {
			return HilfsMethoden.getSpalteEndFeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Spalte fuer ein Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private static int getSpalteEndFeld(String feldID) {
		String[] teile = feldID.split(" ");
		
		if (teile.length == 2) {
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case BLAU:
			case GELB: {
				return 5;
			}
			case ROT: {
				return nummer;
			}
			case GRUEN: {
				return 10 - nummer;
			}
			}
		}
		
		return -1;
	}

	/**
	 * Gibt die passende Spalte fuer ein Startfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Spaltennummer oder -1 falls nicht gefunden
	 */
	private static int getSpalteStartFeld(String feldID) {
		
		String[] teile = feldID.split(" ");
		if (teile.length == 2) {
			
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case ROT:
			case GELB: {
				if (nummer == 1 || nummer == 4) {
					return 1;
				} else {
					return 0;
				}
			}
			case BLAU:
			case GRUEN: {
				if (nummer == 1  || nummer == 4) {
					return 10;
				} else {
					return 9;
				}
			}
			}
		}
		return -1;
	}

	/**
	 * Gibt die passende Reihe fuer ein Start- oder Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private static int getReiheSonderfeld(String feldID) {
		if (feldID.contains("S")) {
			return HilfsMethoden.getReiheStartFeld(feldID);
		} else {
			return HilfsMethoden.getReiheEndfeld(feldID);
		}
	}
	
	/**
	 * Gibt die passende Reihe fuer ein Endfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private static int getReiheEndfeld(String feldID) {
		String[] teile = feldID.split(" ");
		
		if (teile.length == 2) {
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case ROT:
			case GRUEN: {
				return 5;
			}
			case BLAU: {
				return 10 - nummer;
			}
			case GELB:
				return nummer;
			}
		}
		
		return -1;
	}

	/**
	 * Gibt die passende Reihe fuer ein Startfeld zurueck
	 * @param feldID Die ID des Feldes
	 * @return Die Reihennummer oder -1 falls nicht gefunden
	 */
	private static int getReiheStartFeld(String feldID) {
		String[] teile = feldID.split(" ");
		
		if (teile.length == 2) {
			FarbEnum farbe = FarbEnum.vonString(teile[1]);
			int nummer = Integer.parseInt(teile[0].substring(1));
			
			switch (farbe) {
			case ROT:
			case BLAU: {
				if (nummer < 3) {
					return 10;
				} else {
						return 9;
				}
				}
			case GELB:
			case GRUEN: {
				if (nummer < 3) {
					return 1;
				} else {
					return 0;
				}
			}
			}
		}
		
		return -1;
	}

	/**
	 * Prueft ob a im gewuenschten Intervall ist
	 * @param min Minimum
	 * @param max Maximum
	 * @param a Der Wert
	 * @return True falls innerhalb, sonst false
	 */
	public static boolean inIntervall(int min, int max, int a) {
		if (a >= min && a <= max) {
			return true;
		}
		return false;
	}

}
