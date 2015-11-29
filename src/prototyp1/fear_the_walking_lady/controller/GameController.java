package prototyp1.fear_the_walking_lady.controller;

import prototyp1.fear_the_walking_lady.view.*;
import prototyp1.fear_the_walking_lady.modell.*;

public class GameController {
	TextInterface myInterface;
	boolean player1turn;
	Spieler spieler1;
	Spieler spieler2;
	int zeit;
	
	/**
	 * Erzeugt einen GameController, der das Spiel verwaltet
	 */
	public GameController(){
		myInterface = new TextInterface();
	}
	
//	public boolean createInterface(){
//		myInterface = new TextInterface();	
//	}
	
	/**
	 * Gibt das Startmenü aus und verarbeitet die Eingabe
	 */
	public void runStartMenue(){
		int eingabe;		
		
		do{
			eingabe = myInterface.createAndPrintStartMenue();
			
			switch(eingabe){
				case 1:		//Spielregeln (In einer Funktion + Internetlink?)
							break;
				case 2: 	runGameHvsH();
							break;
				case 3: 	runGameHvsC();
							break;
				case 4: 	System.out.println("Das Spiel wird beendet!");
							break;
				default:	System.out.println("Ungültige Eingabe!");
							break;
			}
		}while(eingabe != 4);
	}
	
	public void askTimeLimit(){
		
	}
	
	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Mensch
	 */
	public void runGameHvsH(){
		zeit = myInterface.createWindowAskTime();
		myInterface.createWindowAskWhoStarts();
		
	}
	
	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Computer
	 */
	public void runGameHvsC(){
		
	}
	
	public boolean ueberpruefeObSteinZuDame(){
		return false;
	}
	
	public boolean ueberpruefeSpielzeug(){
		return false;
	}
}
