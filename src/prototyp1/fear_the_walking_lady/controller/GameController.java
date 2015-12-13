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
				printSpielanleitung();
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
				spielerEingabe = spielerEingabe.toUpperCase();
				// Die Eingabe verarbeiten
				spielzugGueltig = ueberpruefeSpielzug(spielerEingabe);
				if (!spielzugGueltig) {
					System.out.println("Ungültiger Spielzug!");
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
			printSpielanleitung();
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
		if(!this.verlierer.equals("")){
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

	/**
	 * Gibt die Spielanleitung auf der Konsole aus.
	 */
	public void printSpielanleitung() {
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
	}
}
