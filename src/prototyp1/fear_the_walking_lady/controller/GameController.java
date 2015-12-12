package prototyp1.fear_the_walking_lady.controller;

import prototyp1.fear_the_walking_lady.view.*;
import prototyp1.fear_the_walking_lady.modell.*;

import java.util.LinkedList;
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
		} while (spielLaeuft(spielerEingabe));

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
			break;

		default:
			if (koordinatenEingabeCheck(spielerEingabe) == true) {
				if (GameController.activateCheats && spielerEingabe.length() == 3) {
					stoneToLady(new Koordinate((int) (spielerEingabe.charAt(1) - 48), spielerEingabe.charAt(0)));
				} else {
					rundeGueltig = waehleSteinUndZiehe(spielerEingabe);
				}
			}
			break;
		}

		return rundeGueltig;
	}

	/**
	 * Überprüft die Sytax der vom Spieler eingegebenen Koordinaten
	 * 
	 * @param spielerEingabe
	 *            Die vom Spieler eingegebenen Koordinaten
	 * 
	 * @return eingabeLegal Wahrheitswert
	 */
	private boolean koordinatenEingabeCheck(String spielerEingabe) {
		boolean eingabeLegal = true;

		// Länge der Eingabe überprüfen
		if (GameController.activateCheats) {
			if (spielerEingabe.length() < 3 || spielerEingabe.length() > 4) {
				eingabeLegal = false;
			}
			if (spielerEingabe.length() == 3) {
				if (spielerEingabe.charAt(2) != '@') {
					eingabeLegal = false;
				}
			}
		} else {
			if (spielerEingabe.length() != 4) {
				eingabeLegal = false;
			}
		}

		// Länge und die einzelnen Chars auf Gültigkeit überprüfen
		if (eingabeLegal) {
			if (spielerEingabe.charAt(0) < 'A' || spielerEingabe.charAt(0) > Koordinate.MAX_B_K) {
				eingabeLegal = false;
			} else if (spielerEingabe.charAt(1) < '1' || spielerEingabe.charAt(1) > (char) (Koordinate.MAX_Z_K + 48)) {
				eingabeLegal = false;
			}
			if ((GameController.activateCheats && (spielerEingabe.charAt(2) == '@')) == false) {
				if (spielerEingabe.charAt(2) < 'A' || spielerEingabe.charAt(2) > Koordinate.MAX_B_K) {
					eingabeLegal = false;
				} else
					if (spielerEingabe.charAt(3) < '1' || spielerEingabe.charAt(3) > (char) (Koordinate.MAX_Z_K + 48)) {
					eingabeLegal = false;
				}
			}
		}

		return eingabeLegal;
	}

	/**
	 * Ersetzt einen Stein des Spielers mit einer Lady
	 * 
	 * @param koordinate
	 *            Die Koordinate des Steins
	 */
	private void stoneToLady(Koordinate koordinate) {
		Spieler aktuellerSpieler;
		Stone indexChecker;
		Lady neueLady;
		int indexBuf;

		if (this.player1turn) {
			aktuellerSpieler = this.spieler1;
		} else {
			aktuellerSpieler = this.spieler2;
		}
		
		neueLady = new Lady(aktuellerSpieler.getFarbe(), koordinate);
		indexChecker = new Stone(aktuellerSpieler.getFarbe(), koordinate);
		indexBuf = aktuellerSpieler.getStones().indexOf(indexChecker);

		// Wenn Stein an der Position ist
		if (indexBuf != -1) {
			aktuellerSpieler.getStones().remove(indexBuf);
			aktuellerSpieler.getStones().add(neueLady);

		}
	}

	/**
	 * Erzeugt eine Liste aller legalen Wege, die ein Spielstein nehmen kann
	 * Jede dieser legalen Wege ist eine Liste der Koordinaten, die auf dem
	 * entsprechenden Weg liegen.
	 * 
	 * @param spielerEingabe
	 *            Eingabe des aktuellen Spielers
	 * 
	 * @return alleLegalenWege Liste aller legalen Wege
	 */
	private LinkedList<LinkedList<Koordinate>> erzeugeGueltigeWege(String spielerEingabe) {
		LinkedList<LinkedList<Koordinate>> alleLegalenWege = new LinkedList<LinkedList<Koordinate>>();
		LinkedList<Koordinate> legalerWeg = new LinkedList<Koordinate>();
		Spieler gegner;
		Spieler eigene;
		Stone bufGegnerStone;
		Stone bufEigeneStone;
		Koordinate vonPos;
		Koordinate aktKoord;
		int richtung;
		int bufIndex;

		vonPos = new Koordinate((int) (spielerEingabe.charAt(1) - 48), spielerEingabe.charAt(0));

		if (this.player1turn) {
			gegner = this.spieler2;
			eigene = this.spieler1;
		} else {
			gegner = this.spieler1;
			eigene = this.spieler2;
		}

		// Die Farbe des Spielers entscheidet darüber ob die Steine nach
		// unten(höhere Zahlen) oder nach oben(niedrigere Zahlen) bewegt werden
		// dürfen
		if (eigene.getFarbe().equals("Schwarz")) {
			richtung = 1;
		} else {
			richtung = -1;
		}

		// Teste das Feld vorne links von der Quellkoordinate! aus gesehen
		aktKoord = new Koordinate(vonPos.getZahl() + richtung, 
				(char) (vonPos.getBuchstabe() + richtung));
		bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
		legalerWeg = new LinkedList<Koordinate>();
		legalerWeg.add(vonPos);
		// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
		if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
			// Wenn das zu testende Feld (aktKoord) frei ist
			if (!gegner.getStones().contains(bufGegnerStone) && 
					!eigene.getStones().contains(bufEigeneStone)) {
				bufIndex = eigene.getStones().indexOf(new Stone(eigene.getFarbe(), vonPos));			
				//Wenn der zu ziehende Stein eine Lady ist
				if(eigene.getStones().get(bufIndex) instanceof Lady){
					do{
						legalerWeg.add(aktKoord);
						alleLegalenWege.add(legalerWeg);
						legalerWeg = copy(legalerWeg);
						aktKoord = new Koordinate(aktKoord.getZahl() + richtung,
								(char) (aktKoord.getBuchstabe() + richtung));
						bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
						bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
					//Solange die nächste Koordinate im Feld ist und keine Steine enthält
					}while(koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString()) &&
							!gegner.getStones().contains(bufGegnerStone) &&
							!eigene.getStones().contains(bufEigeneStone));
				//Wenn der zu ziehende Stein KEINE Lady ist
				}else{
					legalerWeg.add(aktKoord);
					alleLegalenWege.add(legalerWeg);
				}
			}
			// Wenn ein Gegner auf dem aktuell zu testenden Feld (aktKoord) ist
			if (gegner.getStones().contains(bufGegnerStone)) {
				legalerWeg.add(aktKoord);
				aktKoord = new Koordinate(aktKoord.getZahl() + richtung,
						(char) (aktKoord.getBuchstabe() + richtung));
				// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
				if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
					alleLegalenWege = pruefeObGegnerSchlagbar(
							gegner, eigene, aktKoord, legalerWeg, alleLegalenWege);
				}
			}
		}
		
		// Teste das Feld vorne rechts von der Quellkoordinate! aus gesehen
		aktKoord = new Koordinate(vonPos.getZahl() + richtung, 
				(char) (vonPos.getBuchstabe() - richtung));
		bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
		legalerWeg = new LinkedList<Koordinate>();
		legalerWeg.add(vonPos);
		// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
		if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
			// Wenn das zu testende Feld (aktKoord) frei ist
			if (!gegner.getStones().contains(bufGegnerStone) && 
					!eigene.getStones().contains(bufEigeneStone)) {
				bufIndex = eigene.getStones().indexOf(new Stone(eigene.getFarbe(), vonPos));			
				//Wenn der zu ziehende Stein eine Lady ist
				if(eigene.getStones().get(bufIndex) instanceof Lady){
					do{
						legalerWeg.add(aktKoord);
						alleLegalenWege.add(legalerWeg);
						legalerWeg = copy(legalerWeg);
						aktKoord = new Koordinate(aktKoord.getZahl() + richtung,
								(char) (aktKoord.getBuchstabe() - richtung));
						bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
						bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
					//Solange die nächste Koordinate im Feld ist und keine Steine enthält
					}while(koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString()) &&
							!gegner.getStones().contains(bufGegnerStone) &&
							!eigene.getStones().contains(bufEigeneStone));
				//Wenn der zu ziehende Stein KEINE Lady ist
				}else{
					legalerWeg.add(aktKoord);
					alleLegalenWege.add(legalerWeg);
				}
			}
			// Wenn ein Gegner auf dem aktuell zu testenden Feld (aktKoord) ist
			if (gegner.getStones().contains(bufGegnerStone)) {
				legalerWeg.add(aktKoord);
				aktKoord = new Koordinate(aktKoord.getZahl() + richtung,
						(char) (aktKoord.getBuchstabe() - richtung));
				// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
				if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
					alleLegalenWege = pruefeObGegnerSchlagbar(
							gegner, eigene, aktKoord, legalerWeg, alleLegalenWege);
				}
			}
		}
		
		// Teste das Feld hinten links von der Quellkoordinate! aus gesehen
		aktKoord = new Koordinate(vonPos.getZahl() - richtung, 
				(char) (vonPos.getBuchstabe() + richtung));
		bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
		legalerWeg = new LinkedList<Koordinate>();
		legalerWeg.add(vonPos);
		// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
		if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
			// Wenn das zu testende Feld (aktKoord) frei ist
			if (!gegner.getStones().contains(bufGegnerStone) && 
					!eigene.getStones().contains(bufEigeneStone)) {
				bufIndex = eigene.getStones().indexOf(new Stone(eigene.getFarbe(), vonPos));			
				//Wenn der zu ziehende Stein eine Lady ist
				if(eigene.getStones().get(bufIndex) instanceof Lady){
					do{
						legalerWeg.add(aktKoord);
						alleLegalenWege.add(legalerWeg);
						legalerWeg = copy(legalerWeg);
						aktKoord = new Koordinate(aktKoord.getZahl() - richtung,
								(char) (aktKoord.getBuchstabe() + richtung));
						bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
						bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
					//Solange die nächste Koordinate im Feld ist und keine Steine enthält
					}while(koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString()) &&
							!gegner.getStones().contains(bufGegnerStone) &&
							!eigene.getStones().contains(bufEigeneStone));
				}
			}
			// Wenn ein Gegner auf dem aktuell zu testenden Feld (aktKoord) ist
			if (gegner.getStones().contains(bufGegnerStone)) {
				legalerWeg.add(aktKoord);
				aktKoord = new Koordinate(aktKoord.getZahl() - richtung,
						(char) (aktKoord.getBuchstabe() + richtung));
				// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
				if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
					alleLegalenWege = pruefeObGegnerSchlagbar(
							gegner, eigene, aktKoord, legalerWeg, alleLegalenWege);
				}
			}
		}
		
		// Teste das Feld hinten rechts von der Quellkoordinate! aus gesehen
		aktKoord = new Koordinate(vonPos.getZahl() - richtung, 
				(char) (vonPos.getBuchstabe() - richtung));
		bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
		legalerWeg = new LinkedList<Koordinate>();
		legalerWeg.add(vonPos);
		// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
		if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
			// Wenn das zu testende Feld (aktKoord) frei ist
			if (!gegner.getStones().contains(bufGegnerStone) && 
					!eigene.getStones().contains(bufEigeneStone)) {
				bufIndex = eigene.getStones().indexOf(new Stone(eigene.getFarbe(), vonPos));			
				//Wenn der zu ziehende Stein eine Lady ist
				if(eigene.getStones().get(bufIndex) instanceof Lady){
					do{
						legalerWeg.add(aktKoord);
						alleLegalenWege.add(legalerWeg);
						legalerWeg = copy(legalerWeg);
						aktKoord = new Koordinate(aktKoord.getZahl() - richtung,
								(char) (aktKoord.getBuchstabe() - richtung));
						bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
						bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
					//Solange die nächste Koordinate im Feld ist und keine Steine enthält
					}while(koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString()) &&
							!gegner.getStones().contains(bufGegnerStone) &&
							!eigene.getStones().contains(bufEigeneStone));
				}
			}
			// Wenn ein Gegner auf dem aktuell zu testenden Feld (aktKoord) ist
			if (gegner.getStones().contains(bufGegnerStone)) {
				legalerWeg.add(aktKoord);
				aktKoord = new Koordinate(aktKoord.getZahl() - richtung,
						(char) (aktKoord.getBuchstabe() - richtung));
				// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
				if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
					alleLegalenWege = pruefeObGegnerSchlagbar(
							gegner, eigene, aktKoord, legalerWeg, alleLegalenWege);
				}
			}
		}

		return alleLegalenWege;
	}

	/**
	 * Stellt fest, ob von der aktuellen Position eines Steines ein Gegner
	 * geschlagen werden kann. Wenn das der Fall ist, werden die Koordinaten in
	 * die Liste der legalenWege aufgenommen und Rekursiv nach weiteren
	 * schlagbaren Gegnern gesucht.
	 * 
	 * @param gegner
	 *            Der Gegner
	 * 
	 * @param eigene
	 *            Der aktuelle Spieler
	 * 
	 * @param aktKoord
	 *            Die Koordinate hinter dem gegnerischen Stein
	 * 
	 * @param legalerWeg
	 *            Der aktuelle legaleWeg, der aufgebaut wird
	 * 
	 * @param alleLegalenWege
	 *            Liste aller legalen Wege
	 * 
	 * @return alleLegalenWege Liste aller legalen Wege
	 */
	private LinkedList<LinkedList<Koordinate>> pruefeObGegnerSchlagbar(Spieler gegner, Spieler eigene,
			Koordinate aktKoord, LinkedList<Koordinate> legalerWeg,
			LinkedList<LinkedList<Koordinate>> alleLegalenWege) {
		Stone bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		Stone bufEigeneStone = new Stone(eigene.getFarbe(), aktKoord);
		Koordinate neuKoord;
		LinkedList<Koordinate> legalerWegZweig;

		// Wenn kein Stein auf der aktuellen Koordinate ist, ist Gegner
		// schlagbar
		if (!(eigene.getStones().contains(bufEigeneStone) || gegner.getStones().contains(bufGegnerStone))) {
			if (koordinatenEingabeCheck(aktKoord.toString() + aktKoord.toString())) {
				legalerWeg.add(aktKoord);
				alleLegalenWege.add(legalerWeg);
			}

			// Prüfe nun, ob von der aktuellen Position aus weitere Gegner
			// schlagbar sind
			neuKoord = new Koordinate(aktKoord.getZahl() - 1, (char) (aktKoord.getBuchstabe() - 1));
			// Falls man nicht von links oben kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() - 2, (char) (aktKoord.getBuchstabe() - 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, eigene, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}

			neuKoord = new Koordinate(aktKoord.getZahl() + 1, (char) (aktKoord.getBuchstabe() + 1));
			// Falls man nicht von rechts unten kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() + 2, (char) (aktKoord.getBuchstabe() + 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, eigene, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}

			neuKoord = new Koordinate(aktKoord.getZahl() - 1, (char) (aktKoord.getBuchstabe() + 1));
			// Falls man nicht von rechts oben kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() - 2, (char) (aktKoord.getBuchstabe() + 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, eigene, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}

			neuKoord = new Koordinate(aktKoord.getZahl() + 1, (char) (aktKoord.getBuchstabe() - 1));
			// Falls man nicht von links unten kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() + 2, (char) (aktKoord.getBuchstabe() - 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, eigene, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}
		}

		return alleLegalenWege;
	}

	/**
	 * Erzeugt eine tiefe Kopie einer Liste aus Koordinaten.
	 * 
	 * @param koordinatenListe
	 *            Die Liste, die kopiert wird
	 * @return kopierteListe Die Kopie
	 */
	private LinkedList<Koordinate> copy(LinkedList<Koordinate> koordinatenListe) {
		LinkedList<Koordinate> kopierteListe = new LinkedList<Koordinate>();

		for (Koordinate buf : koordinatenListe) {
			kopierteListe.addLast(buf);
		}

		return kopierteListe;
	}

	/**
	 * Wählt den in der Eingabe spezifizierten Stein aus, erstellt eine Liste
	 * aller möglichen Züge des Steins. Der Stein wird bewegt (falls möglich)
	 * und die Gegner auf seiner Bahn werden gelöscht.
	 * 
	 * @param spielerEingabe
	 *            Die vom Spieler eingegeben Koordinaten
	 * @return rundeGueltig Wahr, wenn die Eingabe umgesetzt werden konnte
	 */
	private boolean waehleSteinUndZiehe(String spielerEingabe) {
		LinkedList<LinkedList<Koordinate>> alleLegalenWege;
		LinkedList<LinkedList<Koordinate>> alleSchlagpflichtWege;
		LinkedList<Koordinate> zugWeg = null;
		Spieler aktuellerSpieler;
		Spieler aktuellerGegner;
		boolean rundeGueltig = true;
		boolean schlagpflichtBeachtet = false;
		int indexBuf;
		Koordinate vonPos;
		Koordinate nachPos;
		Stone bufStein;

		if (this.player1turn) {
			aktuellerSpieler = this.spieler1;
			aktuellerGegner = this.spieler2;
		} else {
			aktuellerSpieler = this.spieler2;
			aktuellerGegner = this.spieler1;
		}
		
		vonPos = new Koordinate((int) (spielerEingabe.charAt(1) - 48), spielerEingabe.charAt(0));
		nachPos = new Koordinate((int) (spielerEingabe.charAt(3) - 48), spielerEingabe.charAt(2));
		bufStein = new Stone(aktuellerSpieler.getFarbe(), vonPos);
		indexBuf = aktuellerSpieler.getStones().indexOf(bufStein);
		
		//Wenn der zu Ziehende Stein existiert
		if(indexBuf != -1){
			bufStein = aktuellerSpieler.getStones().get(indexBuf);
			
			alleLegalenWege = erzeugeGueltigeWege(spielerEingabe);
			alleSchlagpflichtWege = erzeugeSchlagpflichtWege();
			
			// Wenn Schlagpflichten bestehen
			if(alleSchlagpflichtWege.size() > 0){
				// Gehe die Liste alleSchalgpflichtWege durch...
				for (LinkedList<Koordinate> aktuellerWeg : alleSchlagpflichtWege) {
					// ...und suche nach einer Liste, welche die Koordinaten der Eingabe beinhaltet
					if (aktuellerWeg.getFirst().equals(vonPos) && aktuellerWeg.getLast().equals(nachPos)) {
						schlagpflichtBeachtet = true;
					}
				}
			}else{
				schlagpflichtBeachtet = true;
			}
			
			// Gehe die Liste alleLegalenWege durch...
			for (LinkedList<Koordinate> aktuellerWeg : alleLegalenWege) {
				// ...und suche nach einer Liste, welche die Koordinaten der Eingabe enthält
				if (aktuellerWeg.getFirst().equals(vonPos) && aktuellerWeg.getLast().equals(nachPos)) {
					zugWeg = aktuellerWeg;
				}
			}

			// Wenn kein gültiger Weg existiert
			if (zugWeg == null) {
				rundeGueltig = false;
			} else {
				// Gehe den gesamten Weg ab
				for (int i = 1; i < zugWeg.size(); i ++) {
					bufStein = new Stone(aktuellerGegner.getFarbe(), zugWeg.get(i));
					
					if(ueberpruefeObSteinZuDame(new Stone(aktuellerSpieler.getFarbe(), zugWeg.get(i)))){
						stoneToLady(zugWeg.get(i));
					}
					
					// Wenn die aktuelle Koordinate einen Gegner beinhaltet, entferne ihn
					if(aktuellerGegner.getStones().remove(bufStein)){
						System.out.println("Der Gegner auf " + bufStein.getKoordinate().getBuchstabe()
								+ bufStein.getKoordinate().getZahl() + " wurde geschlagen!");
					}
				}
				
				// Wenn die Schlagpflicht nicht beachtet wurde
				if(!schlagpflichtBeachtet){
					bufStein = new Stone(aktuellerSpieler.getFarbe(), 
							alleSchlagpflichtWege.getFirst().getFirst());
					// Wenn der gezogene Stein in der Schlagpflicht war, lösche ihn...
					if(aktuellerSpieler.getStones().get(indexBuf).equals(bufStein)){
						aktuellerSpieler.getStones().remove(bufStein);
						System.out.println("Der Stein auf " + bufStein.getKoordinate().toString() 
								+ " wurde aufgrund nicht eingehaltener Schlagpflicht entfernt!");
					// ...sonst ziehe den Stein und lösche den ersten schlagpflichtigen Stein
					}else{
						aktuellerSpieler.getStones().remove(bufStein);
						System.out.println("Der Stein auf " + bufStein.getKoordinate().toString() 
								+ " wurde aufgrund nicht eingehaltener Schlagpflicht entfernt!");
						// Ziehe den eigenen Stein an die Zielposition
						aktuellerSpieler.getStones().get(indexBuf-1).ziehen(nachPos);
					}
				}else{
					// Ziehe den eigenen Stein an die Zielposition
					aktuellerSpieler.getStones().get(indexBuf).ziehen(nachPos);
				}
			}
		}

		return rundeGueltig;
	}
	
	/**
	 * Erzeugt alle Spielzugmöglichkeiten, bei der mindestens 1 Stein 
	 * des Gegners geschlagen wird. Diese Methode dient als Basis für
	 * das Überprüfen der Einhaltung der Schlagpflicht.
	 * 
	 * @return alleSchlagpflichtWege Gültige Wege für den Schlagpflichtabgleich
	 */
	private LinkedList<LinkedList<Koordinate>> erzeugeSchlagpflichtWege(){
		Spieler aktuellerSpieler;
		Spieler aktuellerGegner;
		Stone gegnerStein;
		boolean gueltigerWeg;
		LinkedList<LinkedList<Koordinate>> alleSchlagpflichtWegeBuf = 
				new LinkedList<LinkedList<Koordinate>>();
		LinkedList<LinkedList<Koordinate>> alleSchlagpflichtWege = 
				new LinkedList<LinkedList<Koordinate>>();
		LinkedList<Koordinate> aktuellerWeg;
		
		if(this.player1turn){
			aktuellerSpieler = spieler1;
			aktuellerGegner = spieler2;
		}else{
			aktuellerSpieler = spieler2;
			aktuellerGegner = spieler1;
		}
		
		//Erzeugt für jeden Stein des aktuellen Spielers alle gültigen Wege
		for(int i = 0; i < aktuellerSpieler.getStones().size(); i++){
			alleSchlagpflichtWegeBuf.addAll(erzeugeGueltigeWege(
					aktuellerSpieler.getStones().get(i).getKoordinate().toString()));
		}
		
		// Für jeden Weg...
		for(int i = 0; i < alleSchlagpflichtWegeBuf.size(); i++){
			gueltigerWeg = false;
			aktuellerWeg = alleSchlagpflichtWegeBuf.get(i);
			
			// ...gehe den gesamten Weg ab und...
			for (int j = 1; j < aktuellerWeg.size(); j ++) {
				gegnerStein = new Stone(aktuellerGegner.getFarbe(), aktuellerWeg.get(j));
				
				// ...wenn die aktuelle Koordinate einen Gegner beinhaltet...
				if(aktuellerGegner.getStones().contains(gegnerStein)){
					// ...markiere den Weg als gültig
					gueltigerWeg = true;
				}					
			}
			
			// Wenn der aktuelle Weg gültig ist, füge ihn zur Liste hinzu
			if(gueltigerWeg){
				alleSchlagpflichtWege.add(aktuellerWeg);
			}
		}
			
		return alleSchlagpflichtWege;
	}
	
	
	
	/**
	 * Überprüft ob ein Stein auf einem Feld ist, auf dem er zur Lady wird
	 * 
	 * @param stein Der zu testende Stein
	 * @return rueckgabe Wahr, wenn Stein zur Lady wird
	 */
	public boolean ueberpruefeObSteinZuDame(Stone stein) {
		boolean rueckgabe = false;
		
		if(stein.getColor() == "Weiß"){
			// Wenn der weiße Spieler das Ende des Felds erreicht hat, mache ihn zur Lady
			if(stein.getKoordinate().getZahl() == 1){
				rueckgabe = true;
			}
		}else{
			// Wenn der schwarze Spieler das Ende des Felds erreicht hat, mache ihn zur Lady
			if(stein.getKoordinate().getZahl() == Koordinate.MAX_Z_K){
				rueckgabe = true;
			}
		}
		
		return rueckgabe;
	}

	public boolean spielLaeuft(String spielerEingabe) {
		return true;
	}

	/**
	 * Falls der Spieler zu lange für eine Entscheidung braucht wird ein
	 * zufällig gewählter Stein von ihm entfernt.
	 * 
	 */
	private void entferneSteinZeitueberschreitung() {
		Random rand = new Random();
		int r;

		if (this.player1turn) {
			r = rand.nextInt(this.spieler1.getStones().size() - 1);
			this.spieler1.getStones().remove(r);
			this.spieler1.printStones();
		} else {
			r = rand.nextInt(this.spieler2.getStones().size() - 1);
			this.spieler2.getStones().remove(r);
			this.spieler2.printStones();
		}
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
