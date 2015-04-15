package Kuenstliche_Intelligenz;

import Spiel.FarbEnum;

public enum KiTypEnum {
 AGGRESIV,DEFENSIV;
 
 public static KiTypEnum vonString(String s) {
		if (s.equals("AGGRESIV"))
			return AGGRESIV;
		else if (s.equals("DEFENSIV"))
			return DEFENSIV;
		
		return null;
	}
}


