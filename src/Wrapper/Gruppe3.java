package Wrapper;

import prototyp1.fear_the_walking_lady.controller.GameController;
import prototyp1.fear_the_walking_lady.modell.*;

public class Gruppe3 implements GruppeX {
	private GameController spiel;
	private boolean wirSindSpieler1;

	/**
	 * Konstruktor
	 */
	public Gruppe3(){
		spiel = new GameController();
	}

	/**
	 * Signalisiert dem jeweiligen Programm, das es den ersten Zug macht.
	 */
	public void youAreFirst() {
		this.spiel.setPlayer1turn(true);
		this.wirSindSpieler1 = true;
		this.spiel.stelleSteineAnfangspositionCvsC();
	}

	/**
	 * Signalisiert dem jeweiligen Programm, dass es den zweiten Zug macht
	 */
	public void youAreSecond() {
		this.spiel.setPlayer1turn(false);
		this.wirSindSpieler1 = false;
		this.spiel.stelleSteineAnfangspositionCvsC();
	}

	/**
	 * Fragt das jeweilige Programm, ob das Spiel seiner Meinung nach überhaupt
	 * noch läuft (Rückgabe true) oder ob einer der beiden Spieler gewonnen bzw.
	 * kein weiterer Zug mehr möglich ist (Rückgabe false)
	 * 
	 * @return true, wenn das Spiel noch läuft
	 * @return false, wenn einer gewonnen hat oder kein Spielzug mehr möglich
	 *         ist
	 */
	public boolean isRunning() {
		return this.spiel.spielLaeuft();
	}

	/**
	 * Unter der Voraussetzung, dass das Spiel für dieses Programm beendet ist,
	 * fragt diese Methode das jeweilige Programm, ob der Computer gewonnen
	 * (Rückgabewert 1) oder verloren hat (Rückgabewert -1)
	 * 
	 * @return 1, falls Programm gewonnen hat
	 * @return 0, falls ein Unentschieden ist
	 * @return -1, falls Programm verloren hat
	 */
	public int whoWon() {
		int rueckgabe = 0;
		
		//Wenn das Spiel nicht mehr läuft, checke ob unsere Ki gewonnen hat
		if(!isRunning()){
			if(this.wirSindSpieler1){
				rueckgabe = this.spiel.getSpieler1().hatGewonnen(this.spiel.getVerlierer());
			}else{
				rueckgabe = this.spiel.getSpieler2().hatGewonnen(this.spiel.getVerlierer());
			}
		}
		
		return rueckgabe;
	}

	/**
	 * Übergibt dem jeweiligen Programm den gegnerischen Zug. Der Rückgabewert
	 * ist true, wenn der Zug gültig ist, false ansonsten
	 * 
	 * @param hisMove
	 * @return true, falls der Zug des Gegners gültig ist
	 * @return false, falls der Zug des Gegbers ungültig ist.
	 */
	public boolean takeYourMove(String hisMove) {
		if(this.wirSindSpieler1){
			this.spiel.setPlayer1turn(false);
		}else{
			this.spiel.setPlayer1turn(true);
		}
		
		return this.spiel.ueberpruefeSpielzug(hisMove);
	}

	/**
	 * Erfragt vom jeweiligen Programm seinen Zug
	 * 
	 * @return String, den Zug des Programms
	 */
	public String getMyMove() {
		String rueckgabe;
		
		if(this.wirSindSpieler1){
			this.spiel.setPlayer1turn(true);
			rueckgabe = ((Ki)this.spiel.getSpieler1()).kiZug(this.spiel.getSpieler2());
		}else{
			this.spiel.setPlayer1turn(false);
			rueckgabe = ((Ki)this.spiel.getSpieler2()).kiZug(this.spiel.getSpieler1());
		}
			
		return rueckgabe;
	}

	/**
	 * liefert true, wenn ein gegnerischer Zug (aus der Sicht des jeweiligen
	 * Programms) möglich ist, ansonsten false
	 * 
	 * @return true, falls der Zug des Gegners erlaubt ist.
	 * @return false, falls der Zug des Gegners nicht erlaubt ist
	 */
	public boolean canYouMove() {
		boolean canMove;
		
		if(wirSindSpieler1){
			canMove = this.spiel.getSpieler2().canMove(this.spiel.getSpieler1());
		}else{
			canMove = this.spiel.getSpieler1().canMove(this.spiel.getSpieler2());
		}
		
		return canMove;
	}

	/**
	 * liefert true, wenn ein eigener Zug (aus der Sicht des jeweiligen
	 * Programms) möglich ist, ansonsten false
	 * 
	 * @return true, falls der Zug erlaubt ist.
	 * @return false, falls der Zug nicht erlaubt ist.
	 */
	public boolean canIMove() {
		boolean canMove;
		
		if(wirSindSpieler1){
			canMove = this.spiel.getSpieler1().canMove(this.spiel.getSpieler2());
		}else{
			canMove = this.spiel.getSpieler2().canMove(this.spiel.getSpieler1());
		}
		
		return canMove;
	}

	/**
	 * druckt mit System.out.println()-Kommandos das Spielbrett im aktuellen
	 * Zustand auf der Konsole aus
	 */
	public void printBoard() {
		Field feld = new Field(
				this.spiel.getSpieler1().getStones(), this.spiel.getSpieler2().getStones());
		
		System.out.println(feld.toString());
	}

}
