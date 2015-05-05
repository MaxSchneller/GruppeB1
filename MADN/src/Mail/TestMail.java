package Mail;

public class TestMail {

	public static void main(String[] args) {

		
		String m = "Hallo das ist ein test!!!";
		new Mail ("Max.Schneller@student.reutlingen-university.de" , "test-betreff" , m , null , null); 
		System.out.println("Ende");
	}
}
