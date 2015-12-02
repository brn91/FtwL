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
	
//	public void askTimeLimit(){
//		
//	}
	
	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Mensch
	 */
	public void runGameHvsH(){
		boolean spielzugGueltig = false;
		String spielerEingabe;
		long rundenZeit;
		
		//Ermitteln der rundenZeit
		this.zeit = this.myInterface.createWindowAskTime();
		
		//Frage, welcher Spieler startet und setze die Anfangssteine
		this.player1turn = this.myInterface.createWindowAskWhoStarts();
		stelleSteineAnfangsposition();
		
		//Spieler wechseln sich ab solange das Spiel läuft
		do{
			//Zeit zum Anfang des Zuges ermitteln und rundenZeit dazurechnen
			rundenZeit = System.currentTimeMillis() + this.zeit;
			//Tue bis die Zeit abgelaufen ist oder der Spielzug gültig ist
			do{	
				//Die Eingabe des Spielers ermitteln
				spielerEingabe = this.myInterface.createAndPrintGameMenueHvsH(
						this.spieler1, this.spieler2, this.player1turn, rundenZeit);
				//Die Eingabe verarbeiten
				spielzugGueltig = ueberpruefeSpielzug(spielerEingabe);	
			}while(!spielzugGueltig && (System.currentTimeMillis() < rundenZeit));
			//Wechsle den Spieler
			player1turn ^= true;
		}while(spielLaeuft());
		
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
	
	public boolean spielLaeuft(){
		return true;
	}
	
	/**
	 * Stellt alle Steine an die Anfangsposition.
	 */
	public void stelleSteineAnfangsposition(){
		if(this.player1turn == true){
			this.spieler1 = new Spieler(Spieler.spielerFarbe.SCHWARZ);
			this.spieler2 = new Spieler(Spieler.spielerFarbe.WEIß);
		}else{
			this.spieler1 = new Spieler(Spieler.spielerFarbe.WEIß);
			this.spieler2 = new Spieler(Spieler.spielerFarbe.SCHWARZ);
		}
	}
}
