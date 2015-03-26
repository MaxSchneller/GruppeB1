/**
 * Farbenum, der Farben Rot, Blau, Gruen, Gelb 
 * @author Gruppe B1
 */
public enum FarbEnum {
	ROT, BLAU, GRUEN, GELB;
	
	/**
	 * Konvertiert den angegeben String in eine Farbe aus FarbEnum, falls möglich
	 * @param s Der String, welcher konvertiert werden soll (_muss_ genau so geschrieben sein, wie die Enumeration)
	 * @return Die passende Farbe aus FarbEnum oder null falls keine passende Farbe gefunden
	 */
	public static FarbEnum vonString(String s) {
		if (s.equals("ROT"))
			return ROT;
		else if (s.equals("BLAU"))
			return BLAU;
		else if (s.equals("GRUEN"))
			return GRUEN;
		else if (s.equals("GELB"))
			return GELB;
		else
			return null;
	}
}
