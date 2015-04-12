import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Klasse zum Testen eines realen Spiels
 * 
 * @author Gruppe B1
 *
 */
public class SpielTest {

	public static void main(String[] args)
			throws SpielerFarbeVorhandenException, IOException,
			SpielerNichtGefundenException {
		iBediener s1 = new Spiel("Martin", FarbEnum.ROT, null);
		s1.spielerHinzufuegen("Heinz", FarbEnum.BLAU, null);
		
		//vorbedingungenAendern(s1);

		// vorbedingungenAendern(s1);

		ZugErgebnis ergebnis = null;
		String weiterSpielen = "Ja";

		do {

			System.out.println("--> Spieler : " + s1.getSpielerAmZugFarbe()
					+ " ist am Zug");
			System.out
					.println("Enter  druecken zum Wuerfeln (Zahl eingeben, um Wuerfel zu manipulieren)");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));

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
					System.out.println("--> Der naechste Spieler ist an der Reihe");
				}
			} else {
				System.out.println("!!! Welche Figur soll bewegt werden?");

				int figID = 0;

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
			System.out.println("?? Weiter spielen? (ja/nein)");

			weiterSpielen = reader.readLine();
			weiterSpielen = weiterSpielen.toLowerCase();

		} while (!weiterSpielen.equals("nein"));
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
