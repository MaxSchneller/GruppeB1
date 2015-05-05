package Mail;

public class TestMail {

	public static void main(String[] args) {

		
		String m = "Hallo das ist ein test!!!";
		new Mail ("max.schneller@gmx.net" , "test-betreff" , m , null , null); 
		System.out.println("Ende");
	}
}
