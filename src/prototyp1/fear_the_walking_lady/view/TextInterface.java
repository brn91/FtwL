package prototyp1.fear_the_walking_lady.view;

import java.util.Scanner;
import prototyp1.fear_the_walking_lady.modell.*;

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
		
		myStartMenue = new StartMenue();
		menuePunkt = myStartMenue.zeichneDich();
		
		return menuePunkt;
	}

	public Bewegungskoordinate createAndPrintGameMenueHvsH(Spieler spieler){
		return null;
	}
	
	public Bewegungskoordinate createAndPrintGameMenueHvsC(Spieler spieler){
		return null;
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
			System.out.println("1: Spieler1");
			System.out.println("2: Spieler2");
			
			try{
				spieler = Integer.parseInt(in.nextLine());
				
				if(spieler == 1){
					spieler1beginnt = true;
				}else if(spieler != 2){
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
	public int createWindowAskTime(){
		int zeit = 0;
		
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
		
		return zeit;
	}
}
