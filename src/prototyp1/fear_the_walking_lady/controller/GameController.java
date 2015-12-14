package prototyp1.fear_the_walking_lady.controller;

import prototyp1.fear_the_walking_lady.view.*;
import prototyp1.fear_the_walking_lady.modell.*;

import java.util.Random;
import java.util.LinkedList;

public class GameController {
	public static boolean activateCheats = false;
	TextInterface myInterface;
	Spieler verlierer;
	boolean player1turn = true;
	Spieler spieler1;
	Spieler spieler2;

	/**
	 * Erzeugt einen GameController, der das Spiel verwaltet
	 */
	public GameController() {
		this.myInterface = new TextInterface();
	}

	// public boolean createInterface(){
	// myInterface = new TextInterface();
	// }

	/**
	 * Gibt das Startmenü aus und verarbeitet die Eingabe
	 */
	public void runStartMenue() {
		int eingabe;

		do {
			eingabe = this.myInterface.createAndPrintStartMenue();

			switch (eingabe) {
			case 1:
				myInterface.printSpielanleitung();
				break;
			case 2:
				runGameHvsH();
				break;
			case 3:
				runGameHvsC();
				break;
			case 4:
				System.out.println("Das Spiel wird beendet!");
				break;
			default:
				System.out.println("Ungültige Eingabe!");
				break;
			}
		} while (eingabe != 4);
	}

	// public void askTimeLimit(){
	//
	// }

	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Mensch
	 */
	public void runGameHvsH() {
		boolean spielzugGueltig = false;
		String spielerEingabe;
		long zeitBuf;
		long rundenZeit;
		int gewonnen;
		
		this.verlierer = null;

		// Ermitteln der rundenZeit
		rundenZeit = this.myInterface.createWindowAskTime();

		// Frage, welcher Spieler startet und setze die Anfangssteine
		this.player1turn = this.myInterface.createWindowAskWhoStarts();
		stelleSteineAnfangsposition();

		// Spieler wechseln sich ab solange das Spiel läuft
		do {
			// Zeit zum Anfang des Zuges ermitteln und rundenZeit dazurechnen
			zeitBuf = System.currentTimeMillis() + rundenZeit;
			// Tue bis die Zeit abgelaufen ist oder der Spielzug gültig ist
			do {
				// Die Eingabe des Spielers ermitteln
				spielerEingabe = this.myInterface.createAndPrintGameMenueHvsH(this.spieler1, this.spieler2,
						this.player1turn, zeitBuf);
				// Die Eingabe verarbeiten
				spielzugGueltig = ueberpruefeSpielzug(spielerEingabe);
				if (!spielzugGueltig) {
					System.out.println("Ungültiger Spielzug!");
				}
				//Zeit zurücksetzen nachdem Spielanleitung angeschaut wurde
				if(spielerEingabe.equals("SPIELANLEITUNG")){
					zeitBuf = System.currentTimeMillis() + rundenZeit;
				}

			} while ((!spielzugGueltig) && (System.currentTimeMillis() < zeitBuf));
			// Wechsle den Spieler
			this.player1turn ^= true;
		} while (spielLaeuft());
		
		//Werte das Spiel aus
		gewonnen = spieler1.hatGewonnen(this.verlierer);
		if(gewonnen == -1){
			System.out.println("Spieler1 hat verloren, Spieler2 hat gewonnen!");
		}else if(gewonnen == 1){
			System.out.println("Spieler2 hat verloren, Spieler1 hat gewonnen!");
		}else{
			System.out.println("Keiner der beiden Spieler hat gewonnen!");
		}

	}

	/**
	 * Startet und verwaltet ein Spiel Mensch gegen Computer
	 */
	public void runGameHvsC() {

	}

	/**
	 * Überprüft den Spielzug anhand der Spielereingaben.
	 * 
	 * Falls eine Spielfigur bewegt werden soll, wird zuerst mit
	 * koordinatenEingabeCheck() getestet ob die Syntax der Eingabe korrekt ist.
	 * Danach wird in waehleSteinUndZiehe() versucht den entsprechenden Stein,
	 * falls vorhanden, zu ziehen.
	 * 
	 * 
	 * @param spielerEingabe
	 *            Die vom Spieler getätigte Eingabe
	 * @return rundeGueltig Ob die Eingabe ausgeführt werden konnte
	 */
	public boolean ueberpruefeSpielzug(String spielerEingabe) {
		boolean rundeGueltig = false;
		Spieler aktuellerSpieler;
		Spieler aktuellerGegner;
		Bewegungskoordinate bewegung;
		
		if(player1turn){
			aktuellerSpieler = spieler1;
			aktuellerGegner = spieler2;
		}else{
			aktuellerSpieler = spieler2;
			aktuellerGegner = spieler1;
		}

		switch (spielerEingabe) {
		case "ZEITÜBERSCHREITUNG":
			System.out.println("Deine Zeit ist Abgelaufen!");
			System.out.println("Einer deiner Spielsteine wird entfernt!");
			entferneSteinZeitueberschreitung();
			break;

		case "SPIELANLEITUNG":
			myInterface.printSpielanleitung();
			break;

		case "ZUGWECHSEL":
			if (GameController.activateCheats) {
				rundeGueltig = true;
			}
			break;

		case "BEENDEN":
			rundeGueltig = true;
			if(player1turn){
				this.verlierer = spieler1;
			}else{
				this.verlierer = spieler2;
			}
			break;

		default:
			if (aktuellerSpieler.koordinatenEingabeCheck(spielerEingabe) == true) {
				if (GameController.activateCheats && spielerEingabe.length() == 3) {
					aktuellerSpieler.stoneToLady(new Koordinate(
							(int) (spielerEingabe.charAt(1) - 48), spielerEingabe.charAt(0)));
				} else {
					bewegung = new Bewegungskoordinate(
							new Koordinate(spielerEingabe.charAt(1) - 48, spielerEingabe.charAt(0)),
							new Koordinate(spielerEingabe.charAt(3) - 48, spielerEingabe.charAt(2)));
					rundeGueltig = aktuellerSpieler.waehleSteinUndZiehe(bewegung, aktuellerGegner);
				}
			}
			break;
		}

		return rundeGueltig;
	}

	/**
	 * Stellt fest ob das Spiel noch läuft
	 * 
	 * @return laeuft Wahr, wenn Spiel läuft
	 */
	public boolean spielLaeuft() {
		LinkedList<LinkedList<Koordinate>> alleGueltigenWege = 
				new LinkedList<LinkedList<Koordinate>>();
		boolean laeuft = true;
		
		//wahr wenn:
		//-prüfe als erstes, wenn spiel abgebrochen(spielLaeuft == false) hat
		// aktueller spieler verloren
		//-gegner keine steine mehr
		//-gegner kann nicht mehr ziehen
		//-unentschieden wenn beide nur noch lady haben
		
		//Wenn bereits ein Verlierer feststeht
		if(this.verlierer != null){
			laeuft = false;
		}else{
			if(this.spieler1 != null && this.spieler2 != null){
				//Wenn Spieler1 keine Steine mehr hat
				if(spieler1.getStones().size() == 0){
					this.verlierer = spieler1;
					laeuft = false;
				//Wenn Spieler2 keine Steine mehr hat
				}else if(spieler2.getStones().size() == 0){
					this.verlierer = spieler2;
					laeuft = false;
				//Wenn beide Spieler nur noch einen Stein haben...
				}else if(spieler1.getStones().size() == 1 && spieler2.getStones().size() == 1){
					//...und diese Steine Ladys sind
					if(spieler1.getStones().getFirst() instanceof Lady && 
							spieler2.getStones().getFirst() instanceof Lady){
						this.verlierer = null;
						laeuft = false;
					}
				}else if(player1turn){
					for(int i = 0; i < spieler1.getStones().size(); i++){
						alleGueltigenWege.addAll(spieler1.erzeugeAlleWege(spieler2));
					}
					//Wenn Spieler1 keine seiner Steine mehr bewegen kann
					if(alleGueltigenWege.size() == 0){
						this.verlierer = spieler1;
						laeuft = false;
					}else{
						for(int i = 0; i < spieler2.getStones().size(); i++){
							alleGueltigenWege.addAll(spieler2.erzeugeAlleWege(spieler1));
						}
						//Wenn Spieler2 keine seiner Steine mehr bewegen kann
						if(alleGueltigenWege.size() == 0){
							this.verlierer = spieler2;
							laeuft = false;
						}
					}
				}
			}
		}
		
		return laeuft;
	}
	
	/**
	 * Falls der Spieler zu lange für eine Entscheidung braucht wird ein
	 * zufällig gewählter Stein von ihm entfernt.
	 * 
	 */
	private void entferneSteinZeitueberschreitung() {
		Random rand = new Random();
		Spieler aktuellerSpieler;
		int r;
		
		if(this.player1turn){
			aktuellerSpieler = spieler1;
		}else{
			aktuellerSpieler = spieler2;
		}

		r = rand.nextInt(aktuellerSpieler.getStones().size() - 1);
		aktuellerSpieler.getStones().remove(r);
	}

	/**
	 * Stellt alle Steine an die Anfangsposition.
	 */
	public void stelleSteineAnfangsposition() {
		if (this.player1turn == true) {
			this.spieler1 = new Spieler(Spieler.spielerFarbe.SCHWARZ);
			this.spieler2 = new Spieler(Spieler.spielerFarbe.WEIß);
		} else {
			this.spieler1 = new Spieler(Spieler.spielerFarbe.WEIß);
			this.spieler2 = new Spieler(Spieler.spielerFarbe.SCHWARZ);
		}
	}
}
