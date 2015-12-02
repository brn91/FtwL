package prototyp1.fear_the_walking_lady.controller;

import prototyp1.fear_the_walking_lady.view.*;
import prototyp1.fear_the_walking_lady.modell.*;
import java.util.Random;

public class GameController {
	public static boolean activateCheats = false;
	TextInterface myInterface;
	boolean player1turn;
	Spieler spieler1;
	Spieler spieler2;
	
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
				case 1:		printSpielanleitung();
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
		long zeitBuf;
		long rundenZeit;
		
		//Ermitteln der rundenZeit
		rundenZeit = this.myInterface.createWindowAskTime();
		
		//Frage, welcher Spieler startet und setze die Anfangssteine
		this.player1turn = this.myInterface.createWindowAskWhoStarts();
		stelleSteineAnfangsposition();
		
		//Spieler wechseln sich ab solange das Spiel läuft
		do{
			//Zeit zum Anfang des Zuges ermitteln und rundenZeit dazurechnen
			zeitBuf = System.currentTimeMillis() + rundenZeit;
			//Tue bis die Zeit abgelaufen ist oder der Spielzug gültig ist
			do{	
				//Die Eingabe des Spielers ermitteln
				spielerEingabe = this.myInterface.createAndPrintGameMenueHvsH(
						this.spieler1, this.spieler2, this.player1turn, zeitBuf);
				spielerEingabe = spielerEingabe.toUpperCase();
				//Die Eingabe verarbeiten
				spielzugGueltig = ueberpruefeSpielzug(spielerEingabe);
				if(!spielzugGueltig){
					System.out.println("Ungültiger Spielzug!");
				}
				
			}while((!spielzugGueltig) && (System.currentTimeMillis() < zeitBuf));		
			//Wechsle den Spieler
			player1turn ^= true;
		}while(spielLaeuft(spielerEingabe));
		
	}
	
	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Computer
	 */
	public void runGameHvsC(){
		
	}
	
	public boolean ueberpruefeObSteinZuDame(){
		return false;
	}
	
	/**
	 * Überprüft den Spielzug anhand der Spielereingaben.
	 * 
	 * Falls eine Spielfigur bewegt werden soll, wird zuerst
	 * mit koordinatenEingabeCheck() getestet ob die Syntax der
	 * Eingabe korrekt ist.
	 * Danach wird in waehleSteinUndZiehe() versucht den entsprechenden
	 * Stein, falls vorhanden, zu ziehen.
	 * 
	 * 
	 * @param spielerEingabe Die vom Spieler getätigte Eingabe
	 * @return rundeGueltig Ob die Eingabe ausgeführt werden konnte
	 */
	public boolean ueberpruefeSpielzug(String spielerEingabe){
		boolean rundeGueltig = false;

		
		switch(spielerEingabe){
			case "ZEITÜBERSCHREITUNG":	
				System.out.println("Deine Zeit ist Abgelaufen!");
				System.out.println("Einer deiner Spielsteine wird entfernt!");
				entferneSteinZeitueberschreitung();
				break;
				
			case "SPIELANLEITUNG":		
				printSpielanleitung();
				break;
				
			case "ZUGWECHSEL":			
				if(GameController.activateCheats){
					rundeGueltig = true;
				}
				break;
				
			case "BEENDEN":				
				rundeGueltig = true;
				break;
										
			default:	
				if(koordinatenEingabeCheck(spielerEingabe) == true){
					if(GameController.activateCheats && spielerEingabe.length() == 3){
						cheatStoneToLady(new Koordinate((int)(spielerEingabe.charAt(1) - 48),
														spielerEingabe.charAt(0)));
					}else{
						rundeGueltig = waehleSteinUndZiehe(spielerEingabe);		
					}
				}
				break;
		}
		
		return rundeGueltig;
	}
	
	/*
	 * Überprüft die Sytax der vom Spieler eingegebenen Koordinaten
	 * 
	 * @param spielerEingabe Die vom Spieler eingegebenen Koordinaten
	 * @return eingabeLegal Wahrheitswert
	 */
	private boolean koordinatenEingabeCheck(String spielerEingabe){
		boolean eingabeLegal = true;
		
		//Länge der Eingabe überprüfen
		if(GameController.activateCheats){
			if(spielerEingabe.length() < 3 || spielerEingabe.length() > 4){
				eingabeLegal = false;
			}
			if(spielerEingabe.length() == 3){
				if(spielerEingabe.charAt(2) != '@'){
					eingabeLegal = false;
				}
			}
		}else{
			if(spielerEingabe.length() != 4){
				eingabeLegal = false;
			}
		}
	
		//Länge und die einzelnen Chars auf Gültigkeit überprüfen
		if(eingabeLegal){
			if(spielerEingabe.charAt(0) < 'A' || 
					spielerEingabe.charAt(0) > Koordinate.MAX_B_K){
				eingabeLegal = false;
			}else if(spielerEingabe.charAt(1) < '1' ||
					spielerEingabe.charAt(1) > (char)(Koordinate.MAX_Z_K + 47)){
				eingabeLegal = false;
			}
			if((GameController.activateCheats && (spielerEingabe.charAt(2) == '@')) == false){
				if(spielerEingabe.charAt(2) < 'A' ||
						spielerEingabe.charAt(2) > Koordinate.MAX_B_K){
					eingabeLegal = false;
				}else if(spielerEingabe.charAt(3) < '1' ||
						spielerEingabe.charAt(3) > (char)(Koordinate.MAX_Z_K + 47)){
					eingabeLegal = false;
				}
			}
		}
		
		return eingabeLegal;
	}
	
	/*
	 * Ersetzt einen Stein des Spielers mit einer Lady
	 * 
	 * @param koordinate Die Koordinate des Steins
	 */
	private void cheatStoneToLady(Koordinate koordinate){
		Stone indexChecker;
		Lady neueLady;
		int indexBuf;
		
		if(player1turn){
			neueLady = new Lady(spieler1.getFarbe(), koordinate);
			indexChecker = new Stone(spieler1.getFarbe(), koordinate);
			indexBuf = spieler1.getStones().indexOf(indexChecker);
			
			//Wenn Stein an der Position ist
			if(indexBuf != -1){	
				spieler1.getStones().remove(indexBuf);
				spieler1.getStones().add(neueLady);
				
			}
		}else{
			neueLady = new Lady(spieler2.getFarbe(), koordinate);
			indexChecker = new Stone(spieler2.getFarbe(), koordinate);
			indexBuf = spieler2.getStones().indexOf(indexChecker);
			
			//Wenn Stein an der Position ist
			if(indexBuf != -1){	
				spieler2.getStones().remove(indexBuf);
				spieler2.getStones().add(neueLady);
				
			}
		}
	}
	
	/*
	 * Wählt den in der Eingabe spezifizierten Stein aus, 
	 * falls vorhanden, und versucht ihn über die ziehe()
	 * Funktion des Steins zu bewegen.
	 * 
	 * @param spielerEingabe Die vom Spieler eingegeben Koordinaten
	 * @return rundeGueltig Wahr, wenn die Eingabe umgesetzt werden konnte
	 */
	private boolean waehleSteinUndZiehe(String spielerEingabe){
		boolean rundeGueltig = true;
		int indexBuf;
		Koordinate vonPos;
		Koordinate nachPos;
		Stone vonStein;
//		Stone nachStein;
//		Bewegungskoordinate zieheStein;
		
		vonPos = new Koordinate((int)(spielerEingabe.charAt(1) - 48), 
						 spielerEingabe.charAt(0));
		nachPos = new Koordinate((int)(spielerEingabe.charAt(3) - 48),
			  			 spielerEingabe.charAt(2));
		
		
		//zieheStein = new Bewegungskoordinate(vonPos, nachPos);							
		
		if(player1turn){
			vonStein = new Stone(spieler1.getFarbe(), vonPos);
			//nachStein = new Stone(spieler1.getFarbe(), nachPos);
			indexBuf = spieler1.getStones().indexOf(vonStein);
			
			if(indexBuf != -1){	
				rundeGueltig = spieler1.getStones().get(indexBuf).
						ziehen(nachPos, this.player1turn, spieler1, spieler2);
			}
		}else{
			vonStein = new Stone(spieler2.getFarbe(), vonPos);
			//nachStein = new Stone(spieler2.getFarbe(), nachPos);
			indexBuf = spieler2.getStones().indexOf(vonStein);
		
			if(indexBuf != -1){
				rundeGueltig = spieler2.getStones().get(indexBuf).
						ziehen(nachPos, this.player1turn, spieler1, spieler2);
			}
		}		
		return rundeGueltig;
	}
	
	public boolean spielLaeuft(String spielerEingabe){
		return true;
	}
	
	/**
	 * Falls der Spieler zu lange für eine Entscheidung braucht
	 * wird ein zufällig gewählter Stein von ihm entfernt.
	 * 
	 */
	private void entferneSteinZeitueberschreitung(){
		Random rand = new Random();
		int r;
		
		if(player1turn){
			r = rand.nextInt(spieler1.getStones().size() - 1); 
			spieler1.getStones().remove(r);
			spieler1.printStones();
		}else{
			r = rand.nextInt(spieler2.getStones().size() - 1); 
			spieler2.getStones().remove(r);
			spieler2.printStones();
		}
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
	
	public void printSpielanleitung(){
		
	}
}
