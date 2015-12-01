package prototyp1.fear_the_walking_lady.controller;

import prototyp1.fear_the_walking_lady.view.*;
import prototyp1.fear_the_walking_lady.modell.*;

public class GameController {
	public static boolean activateCheats = false;
	TextInterface myInterface;
	boolean player1turn;
	Spieler spieler1;
	Spieler spieler2;
	int zeit;
	
	/**
	 * Erzeugt einen GameController, der das Spiel verwaltet
	 */
	public GameController(){
		this.myInterface = new TextInterface();
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
			eingabe = this.myInterface.createAndPrintStartMenue();
			
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
		boolean aktuellerSpielzugGueltig = false;
		String spielerEingabe;
		
		this.zeit = this.myInterface.createWindowAskTime();
		this.player1turn = this.myInterface.createWindowAskWhoStarts();
		
		if(this.player1turn == true){
			this.spieler1 = new Spieler(Spieler.spielerFarbe.SCHWARZ);
			this.spieler2 = new Spieler(Spieler.spielerFarbe.WEIß);
		}else{
			this.spieler1 = new Spieler(Spieler.spielerFarbe.WEIß);
			this.spieler2 = new Spieler(Spieler.spielerFarbe.SCHWARZ);
		}
		
		//Reihenfolge muss noch beachtet werden
		this.myInterface.createAndPrintGameMenueHvsH(spieler1, spieler2, player1turn);
		this.myInterface.createAndPrintGameMenueHvsH(spieler1, spieler2, !player1turn);
		
//		do{
//			spielerEingabe = this.myInterface.createAndPrintGameMenueHvsH(
//					spieler1, spieler2, player1turn);
//			aktuellerSpielzugGueltig = ueberpruefeSpielzug(spielerEingabe);
//		}while(aktuellerSpielzugGueltig == false);
		

	}
	
	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Computer
	 */
	public void runGameHvsC(){
		
	}
	
	public boolean ueberpruefeObSteinZuDame(){
		return false;
	}
	
	public boolean ueberpruefeSpielzug(String spielerEingabe){
		
		
		return false;
	}
}
