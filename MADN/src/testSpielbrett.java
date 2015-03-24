import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class testSpielbrett {
	
	protected Spielbrett sb=new Spielbrett();
	
	@Test
	public void testStart() {
		assertNotNull(Spielbrett.getStartfelder());
	}
	
	@Test
	public void testEndNull(){
		assertNotNull(Spielbrett.getEndfelder());
	}
	

}
