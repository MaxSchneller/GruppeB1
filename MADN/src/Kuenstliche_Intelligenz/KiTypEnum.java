package Kuenstliche_Intelligenz;

public enum KiTypEnum {
	AGGRESIV, DEFENSIV;

	/**
	 * Konvertiert den angegebenen String in einen KiTyp.
	 * 
	 * @param s
	 *            Der zu konvertierende String (muss exakt uebereinstimmen)
	 * @return Wenn String nicht exakt mit "AGGRESIV" oder "DEFENSIV"
	 *         uebereinstimmt null
	 */
	public static KiTypEnum vonString(String s) {
		if (s.equals("AGGRESIV"))
			return AGGRESIV;
		else if (s.equals("DEFENSIV"))
			return DEFENSIV;

		return null;
	}

	/**
	 * Konvertiert den gegebenen int Wert in einen KiTyp
	 * @param i Der zu konvertierende Wert
	 * @return AGRESSIV fuer 0, DEFENSIV fuer 1, null fuer alles andere
	 */
	public static KiTypEnum vonInt(int i) {
		if (i == 0) {
			return AGGRESIV;
		} else if (i == 1) {
			return DEFENSIV;
		} else {
			return null;
		}
	}
}
