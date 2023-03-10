package Verarbeitung;

public class Vokabeltrainer {

	public static void main(String[] args) {
		
		//GUI gui = new GUI();
		Datenbank database = new Datenbank("abc");
		
		database.erstelleVerbindung();
		database.erstelleDatenbank();
		
		database.erstelleLernSet("Stapel1");

	}

}
