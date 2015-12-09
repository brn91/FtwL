package Wrapper;

public class Gruppe3 {
	/**
	 * Signalisiert dem jeweiligen Programm, das es den ersten Zug macht.
	 */
	void youAreFirst() {

	}

	/**
	 * Signalisiert dem jeweiligen Programm, dass es den zweiten Zug macht
	 */
	void youAreSecond() {

	}

	/**
	 * Fragt das jeweilige Programm, ob das Spiel seiner Meinung nach überhaupt
	 * noch läuft (Rückgabe true) oder ob einer der beiden Spieler gewonnen bzw.
	 * kein weiterer Zug mehr möglich ist (Rückgabe false)
	 * 
	 * @return
	 */
	boolean isRunning() {
		return false;

	}

	/**
	 * Unter der Voraussetzung, dass das Spiel für dieses Programm beendet ist,
	 * fragt diese Methode das jeweilige Programm, ob der Computer gewonnen
	 * (Rückgabewert 1) oder verloren hat (Rückgabewert -1)
	 * 
	 * @return
	 */
	int whoWon() {
		return 0;

	}

	/**
	 * Übergibt dem jeweiligen Programm den gegnerischen Zug. Der Rückgabewert
	 * ist true, wenn der Zug gültig ist, false ansonsten
	 * 
	 * @param zug
	 * @return
	 */
	boolean takeYourMove(String zug) {
		return false;
	}

	/**
	 * Erfragt vom jeweiligen Programm seinen Zug
	 * 
	 * @return
	 */
	String getMyMove() {
		return null;

	}

	/**
	 * liefert true, wenn ein gegnerischer Zug (aus der Sicht des jeweiligen
	 * Programms) möglich ist, ansonsten false
	 * 
	 * @return
	 */
	boolean canYouMove() {
		return false;

	}

	/**
	 * liefert true, wenn ein eigener Zug (aus der Sicht des jeweiligen
	 * Programms) möglich ist, ansonsten false
	 * 
	 * @return
	 */
	boolean canIMove() {
		return false;

	}

	/**
	 * druckt mit System.out.println()-Kommandos das Spielbrett im aktuellen
	 * Zustand auf der Konsole aus
	 */
	void printBoard() {

	}
}
