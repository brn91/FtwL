package prototyp1.fear_the_walking_lady.view;

import prototyp1.fear_the_walking_lady.controller.GameController;
import prototyp1.fear_the_walking_lady.modell.*;
import java.util.Scanner;

public class TextInterface {
	StartMenue myStartMenue;
	GameMenue myGameMenue;
	Scanner in = new Scanner(System.in);

	/**
	 * Erzeugt ein TextInterface, welches für die Verwaltung der
	 * Bildschirmausgaben verantwortlich ist
	 */
	public TextInterface(){
		
	}
	
	/**
	 * Gibt das Startmenue mit Eingabeaufforderung auf dem
	 * Bildschirm aus.
	 * 
	 * @return menuePunkt Die getätigte Eingabe
	 */
	public int createAndPrintStartMenue(){
		int menuePunkt;
		
		this.myStartMenue = new StartMenue();
		menuePunkt = this.myStartMenue.zeichneDich();
		
		return menuePunkt;
	}

	/**
	 * Erzeugt das Menü, welches die Spieler nimmt,
	 * das Spielfeld entsprechend ausgibt 
	 * und die Spielereingabe zurückgibt.
	 * 
	 * @param spieler1 Spieler 1
	 * @param spieler2 Spieler 2
	 * @param player1turn Ist Spieler 1 gerade am Zug?
	 * @return koordinate Die Spielereingabe
	 */
	public String createAndPrintGameMenue(
			Spieler spieler1, Spieler spieler2, boolean player1turn, long rundenZeit){
		
		Field feld = new Field(spieler1.getStones(), spieler2.getStones());
		String koordinate = null;
		
		//Im GameMenü wird Spielfeld gezeichnet und Spieler nach Koordinaten gefragt
		if(player1turn){
			this.myGameMenue = new GameMenue(feld, spieler1, rundenZeit);
		}else{
			this.myGameMenue = new GameMenue(feld, spieler2, rundenZeit);
		}
		koordinate = this.myGameMenue.zeichneDich(player1turn);

			
		return koordinate;
	}

	/**
	 * Erzeugt ein Menü das abfragt, welcher Spieler beginnen darf.
	 * 
	 * @return spieler1beginnt true/false
	 */
	public boolean createWindowAskWhoStarts(){
		int spieler = 0;
		boolean spieler1beginnt = false;

		do{
			System.out.println("Wer nimmt die Farbe Schwarz und beginnt das Spiel?");
			System.out.println("1: Spieler1/Mensch");
			System.out.println("2: Spieler2/KI");
			
			try{
				spieler = Integer.parseInt(in.nextLine());
				
				if(spieler == 1){
					spieler1beginnt = true;
					System.out.println("Spieler 1 beginnt!");
				}else if(spieler == 2){
					System.out.println("Spieler 2 beginnt!");
				}else{
					System.out.println("Geben Sie 1 oder 2 ein!");
				}
			}
			catch(NumberFormatException ex){
				System.out.println("Geben Sie eine Zahl ein! \n");
			}
			
		}while(spieler != 1 && spieler != 2);
		
		return spieler1beginnt;
	}

	/**
	 * Anfängliche eingabe der Zeit, die jeder Spieler pro
	 * Runde zur Verfügung hat
	 * 
	 * @return zeit Rundenzeit
	 */
	public long createWindowAskTime(){
		long zeit = 0;
		
		//Frage nach der Zeit, solange sie <=0 ist
		do{
			System.out.println("Bitte Zeit pro Zug in \n"
					+ "ganzen Sekunden angeben:");
			
			try{
				zeit = Integer.parseInt(in.nextLine());
				
				if(zeit <= 0){
					System.out.println("Geben Sie eine Zahl > 0 ein!");
				}
			}
			catch(NumberFormatException ex){
				System.out.println("Geben Sie eine Zahl ein! \n");
			}
			
		}while(zeit <= 0);
		
		//Bei aktivierten Cheats beträgt die RundenZeit 5 Minuten
		if(GameController.activateCheats){
			zeit = 300;
		}
		
		return zeit * 1000;
	}
	
	/**
	 * Gibt die Spielanleitung auf der Konsole aus.
	 */
	public void printSpielanleitung() {
		Scanner in = new Scanner(System.in);
		
		System.out.println(
		  "# FtwL"

		+ "# Regeln"
		+ "# Das Regelwerk findet sich auch auf https://www.github.com/brn91/FtwL"
		+ "##Organisatorisches:"
		+ "  1.	Das Spiel ist 6 X 6 Felder groß."
		+ " 2.	Es gibt helle und dunkle Steine"
		+ "  3.	Die ersten beiden Reihen der Spielseiten werden mit Steinen besetzt, aber nur die dunklen Felder."
		+ "  4.	Jeder Spieler besitzt 5 normale Spielsteine und eine Lady."
		+ "  5.	Der Spieler der dran ist kann das Spiel sofort beenden, verliert dann aber."
		+ "  6.	Es gibt 2 Modi: Human vs. Human und Human vs. Computer"
		+ "    6.1.	Bei Human vs. Human wird das Spielfeld jede Runde für den jeweiligen Spieler gedreht." 
		+ "  7.	Die Eingabe Besteht aus Quelle zu Ziel (XYXY)"
		+ "    7.1.	X= A-F, Y= 1-6 (Hier Spielfeld einfügen zum Verständnis)"
		+ "    7.2.	Kleinbuchstaben werden Akzeptiert"
		
		+ "###Spielablauf:"
		+ "  1.	Es wird abwechselnd Gespielt"
		+ "  2.	Der Spieler mit den dunklen Steinen beginnt"
		+ "  3.	Es wird vorwärts diagonal gezogen"
		
		+ "  4.	Es besteht Schlagpflicht:"
		+ "    4.1.1.	Stehen 2 Steine diagonal gegenüber und das Feld dahinter ist frei, so muss 	geschlagen werden. "
		+ "             Kann man anschließend noch einen Stein schlagen, muss man dies auch machen."
		+ "    4.1.2.	Man muss auch rückwärts schlagen, sofern man das kann."
		+ "       4.2.	Für den Modus Human vs. Human gilt zusätzlich:"
		+ "         4.2.1.	Wird die Schlagpflicht missachtet, so wird der Stein, der hätte" 	
		+ "                schlagen müssen entfernt."
		+ "           4.2.2.	Der Computer weißt die Eingabe lediglich ab."
		+ "  5.	Man muss einen Zug durchführen und kann nicht passen."
		+ "    5.1.	Es gibt zusätzlich zur Zugpflicht ein Zeitlimit. Ist dies überschritten, so wird ein zufälliger Spielstein gelöscht."
		+ "  6.	Beim Erreichen der gegnerischen Grundlinie wird ein Spielstein zur Lady."
		+ "    6.1.	Die Lady kann ein oder mehrere Felder diagonal ziehen."
		+ "      6.2.	Die Lady zieht direkt hinter den geschlagenen Spielstein."
		+ "  7.	Gewonnen hat man, wenn der Gegner keine Spielsteine mehr besitzt oder er nicht mehr ziehen kann."
		+ "    7.1.	Wenn beide Spieler nur noch eine Lady besitzen, gibt es ein Unentschieden.");
		
		System.out.println("\nDrücken Sie einen Buchstaben um mit dem Spiel fortzusetzen");
		in.nextLine();
	}
}
