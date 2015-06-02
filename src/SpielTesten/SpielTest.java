package SpielTesten;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Spiel.FarbEnum;
import Spiel.SpielBean;
import Spiel.WuerfelErgebnis;
import Spiel.ZugErgebnis;
import Spiel.iBediener;

/**
 * Klasse zum Testen eines realen Spiels
 * 
 * @author Gruppe B1
 *
 */
public class SpielTest {

	public static void main(String[] args)
			throws SpielerFarbeVorhandenException, IOException,
			SpielerNichtGefundenException, KannNichtWuerfelnException {
		iBediener s1 = new SpielBean("Martin", FarbEnum.ROT, KiTypEnum.AGGRESIV);
		s1.spielerHinzufuegen("Heinz", FarbEnum.BLAU, KiTypEnum.DEFENSIV
				);

		// vorbedingungenAendern(s1);

		// vorbedingungenAendern(s1);

		ZugErgebnis ergebnis = null;
		String weiterSpielen = "Ja";
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));

		do {

			System.out.println("--> Spieler : " + s1.getSpielerAmZugFarbe()
					+ " ist am Zug");
			
			int figID = 0;
			
			if (!s1.isSpielerAmZugKI()) {
				System.out
						.println("Enter  druecken zum Wuerfeln (Zahl eingeben, um Wuerfel zu manipulieren)");
				

				String read = reader.readLine();

				int cheatWuerfelIndikator = -1;

				try {
					cheatWuerfelIndikator = Integer.parseInt(read);
				} catch (NumberFormatException e) {
					System.out
							.println("!!! Keine gueltige Wuerfelzahl erhalten...Spieler wuerfelt selbst...");
				}

				int zahl = 0;
				WuerfelErgebnis wuerfelErgebnis = null;

				if (cheatWuerfelIndikator != -1) {
					cheatWuerfelIndikator = Math.min(6, cheatWuerfelIndikator);
					cheatWuerfelIndikator = Math.max(1, cheatWuerfelIndikator);
					wuerfelErgebnis = s1.debugWuerfeln(cheatWuerfelIndikator);
				} else {
					wuerfelErgebnis = s1.sWuerfeln();
				}

				System.out.println("--> Es wurde die Zahl "
						+ wuerfelErgebnis.getGewuerfelteZahl() + " gewuerfelt");

				if (wuerfelErgebnis.isKannZugAusfuehren() == false) {
					System.out.println("--> Kein zug moeglich");

					if (wuerfelErgebnis.isKannNochmalWuerfeln()) {
						System.out.println("!!! Bitte nochmal wuerfeln!");
					} else {
						System.out
								.println("--> Der naechste Spieler ist an der Reihe");
					}
				} else {
					System.out.println("!!! Welche Figur soll bewegt werden?");

					

					while (true) {
						try {
							figID = Integer.parseInt(reader.readLine());

		
							if (figID > 3 || figID < 0) {
								throw new NumberFormatException();
							}

							break;
						} catch (NumberFormatException e) {
							System.out
									.println("!!! Bitte gueltige Zahl zwischen 0 und 3 eingeben");
						}
					}
					
					ergebnis = s1.ziehen(figID);
					
					System.out.println("--> ZugErgebnis:");
					System.out.println(ergebnis);

					if (ergebnis.isSpielGewonnen()) {
						break;
					}
				}

				

				
			} else {
				
				System.out.println("KI ist am Zug, KI wuerfelt ...");
				
				WuerfelErgebnis zahl = s1.sWuerfeln();
				
				System.out.println("KI hat " + zahl.getGewuerfelteZahl() + " gewuerfelt");
				
				if (zahl.isKannZugAusfuehren() == false) {
					System.out.println("Zug kann nicht ausgefuehrt werden");
					
					while (zahl.isKannNochmalWuerfeln()) {
						System.out.println("KI wuerfelt nochmal...");
						zahl = s1.sWuerfeln();
						
						System.out.println("KI hat " + zahl.getGewuerfelteZahl() + " geuwerfelt");
						
						if (zahl.isKannZugAusfuehren() == false) {
							System.out.println("Zug kann nicht ausgefuehrt werden");
						}
					}
					
					if (zahl.isKannZugAusfuehren() == false) {
						System.out.println("KI kann nicht ziehen...naechster Spieler");
					} else {
						ergebnis = s1.ziehen(0);
						
						System.out.println("KI hat gezogen: ");
						System.out.println(ergebnis);
						
					}
				} else {
					ergebnis = s1.ziehen(0);
					
					System.out.println("KI hat gezogen: ");
					System.out.println(ergebnis);
				}
				
				
			}
			//System.out.println("?? Weiter spielen? (ja/nein)");

			//weiterSpielen = reader.readLine();
			//weiterSpielen = weiterSpielen.toLowerCase();

		} while (!weiterSpielen.equals("nein")); // true);
	}

	/**
	 * Kann benutzt werden, um bestimmte Szenarien herbeizufuehren
	 * 
	 * @param s
	 *            Das bereits erstellte Spiel
	 * @throws SpielerNichtGefundenException
	 */
	private static void vorbedingungenAendern(iBediener s)
			throws SpielerNichtGefundenException {
		s.debugSetzeFigur(FarbEnum.ROT, 0, "S1 ROT");
		s.debugSetzeFigur(FarbEnum.ROT, 1, "S2 ROT");
		s.debugSetzeFigur(FarbEnum.ROT, 2, "1");
		s.debugSetzeFigur(FarbEnum.ROT, 3, "S4 ROT");

		s.debugSetzeFigur(FarbEnum.BLAU, 0, "35");

	}
}
