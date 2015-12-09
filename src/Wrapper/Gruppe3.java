package Wrapper;

public class Gruppe3 implements GruppeX {

	// KI3 gruppe 3=new Ki3();
	// KI2 gruppe 2= new KI2();

	/**
	 * Signalisiert dem jeweiligen Programm, das es den ersten Zug macht.
	 */
	public void youAreFirst() {
		// KI3 collor Schwarz
	}

	/**
	 * Signalisiert dem jeweiligen Programm, dass es den zweiten Zug macht
	 */
	public void youAreSecond() {
		// KI2 collor Weiß
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
		return false;
		//return isGameRunning();

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
		return 0;
	//	return gruppe2.gewonnen;

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
		return false;

	//	return ueberpuefeZug(hisMove);
	}

	/**
	 * Erfragt vom jeweiligen Programm seinen Zug
	 * 
	 * @return String, den Zug des Programms
	 */
	public String getMyMove() {
		return null;

	}

	/**
	 * liefert true, wenn ein gegnerischer Zug (aus der Sicht des jeweiligen
	 * Programms) möglich ist, ansonsten false
	 * 
	 * @return true, falls der Zug des Gegners erlaubt ist.
	 * @return false, falls der Zug des Gegners nicht erlaubt ist
	 */
	public boolean canYouMove() {
		return false;

	}

	/**
	 * liefert true, wenn ein eigener Zug (aus der Sicht des jeweiligen
	 * Programms) möglich ist, ansonsten false
	 * 
	 * @return true, falls der Zug erlaubt ist.
	 * @return false, falls der Zug nicht erlaubt ist.
	 */
	public boolean canIMove() {
		return false;

	}

	/**
	 * druckt mit System.out.println()-Kommandos das Spielbrett im aktuellen
	 * Zustand auf der Konsole aus
	 */
	public void printBoard() {
		
	}

}
