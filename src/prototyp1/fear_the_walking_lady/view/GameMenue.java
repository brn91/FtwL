package prototyp1.fear_the_walking_lady.view;

import prototyp1.fear_the_walking_lady.controller.GameController;
import prototyp1.fear_the_walking_lady.modell.*;
import java.util.Scanner;

public class GameMenue{
	private Field myField;
	private Spieler spieler;
	private long rundenZeit;
	
	/**
	 * Erzeugt ein Spielmenü in dem das Spielfeld
	 * gezeichnet und Spielereingabe entgegengenommen werden.
	 * 
	 * @param feld Das Spielfeld, welchen ausgegeben wird
	 * @param spieler Der Spieler, der an der Reihe ist
	 */
	public GameMenue(Field feld, Spieler spieler, long rundenZeit){
		this.myField = feld;
		this.spieler = spieler;
		this.rundenZeit = rundenZeit;
	}
	
	/**
	 * Zeichne das Spielfeld und 
	 * empfange die Eingabe des Spielers
	 * 
	 * @param player1turn Ist Spieler 1 am Zug?
	 * @return rueckgabe Die Spielereingabe
	 */
	public String zeichneDich(boolean player1turn){
		Scanner in = new Scanner(System.in);
		String rueckgabe = null;
		int menueEingabe = -1;
		
		//Ausgabe, welcher Spieler an der reihe ist
		if(player1turn){
			System.out.println("Spieler 1 ist dran\n");
		}else{
			System.out.println("Spieler 2 ist dran\n");
		}
		
		if(this.spieler.getFarbe().equals("Weiß")){
			//Gebe von oben ausgehend für jede Zeile des Feldes...
			for(int i = 0; i < Koordinate.MAX_Z_K + 1; i++){
				//...jede Spalte auf dem Bildschirm aus
				for(int j = 0; j < Koordinate.MAX_B_K - 'A' + 2 ; j++){
					System.out.print(this.myField.getFeld()[i][j]);
				}
				System.out.println();
			}
		}else{
			//Gebe von unten ausgehend für jede Zeile des Feldes...
			for(int i = Koordinate.MAX_Z_K; i >= 0; i--){
				//...jede Spalte auf dem Bildschirm aus
				for(int j = 0; j < Koordinate.MAX_B_K - 'A' + 2 ; j++){
					System.out.print(this.myField.getFeld()[i][j]);
				}
				System.out.println();
			}
		}
		
		//Ausgabe der Menüpunkte
		System.out.println("\nGebe bitte eine Zahl ein: ");
		System.out.println("0: Spielanleitung lesen");
		if(GameController.activateCheats){
			System.out.println("1: Ziehe beliebige Spielfigur (auf beliebiges freies Feld)");
			System.out.println("2: Wechsle den Spieler");
			System.out.println("3: Wandle Stein in Dame um");
		}else{
			System.out.println("1: Ziehe Spielfigur");
			System.out.println("2: Beenden (Du Verlierst!)");
		}
		
		do{
			try{
				menueEingabe = Integer.parseInt(in.nextLine());
				
				switch(menueEingabe){
				case 0:		//Spielanleitung aufrufen
							break;
				case 1: 	System.out.println("Gebe die Koordinaten ein:");
							System.out.println("(B1A2 steht zB für: Ziehe B1 auf B2)");
							rueckgabe = in.nextLine();
							break;
				case 2: 	if(GameController.activateCheats){
								rueckgabe = "Zugwechsel";
							}else{
								rueckgabe = "Beenden";
							}								
							break;
				case 3:		if(GameController.activateCheats){
								System.out.println("Gebe die Koordinaten ein:");
								System.out.println("(B1 steht zB für: Wandle B1 um)");
								rueckgabe = in.nextLine();
							}else{
								System.out.println("Geben Sie eine gültige Zahl ein!");
							}
				default:    System.out.println("Geben Sie eine gültige Zahl ein!");
							break;				
				}
			}
			catch(NumberFormatException ex){
				System.out.println("Geben Sie eine Zahl ein!\n");
			}			
		}while(rueckgabe == null);
		
		//Falls die RundenZeit überschritten wurde
		if(System.currentTimeMillis() > rundenZeit){
			rueckgabe = "Zeitüberschreitung";
		}

		return rueckgabe;
	}	
}
