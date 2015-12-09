package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import prototyp1.fear_the_walking_lady.modell.Koordinate;
import prototyp1.fear_the_walking_lady.modell.Stone;

public class FearTheWalkingLadyTest {
	// Hier ein Spielfeld erstellen, welches dann in den einzelnen Testcases nur
	// veränderd wird.
	/**
	 * Erzeuge ein Paar Steine und schaue, ob er richtig erzeugt wurde.
	 */
	@Test
	public void testErzeugeStein() {
		Stone a = new Stone("Weiß", new Koordinate(1, 'A'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
	}

	/**
	 * Erzeuge einen Stein und verändere die Position mehrmals und schaue, ob
	 * diese richtig verändert wird.
	 */
	@Test
	public void testPositionenVerändern() {
		fail("Not yet implemented");
	}

	/**
	 * Erzeuge ein paar Ladys und schaue, ob diese Korrekt inizialisiert wird.
	 */
	@Test
	public void testLady() {
		fail("Not yet implemented");
	}

	/**
	 * Erzeuge Steine und mache diese zur Lady und schau, ob dies korrekt
	 * geschieht.
	 */
	@Test
	public void testSteinZuLady() {
		fail("Not yet implemented");
	}

	/**
	 * Erzeuge einen Stein an der gegnerischen Seite und rüfe die Überprüfung
	 * zur Ladyerzeugung auf. Schaue ob eine Lady entstanden ist. Anschließend
	 * sorge dafür das die überprüfung geschiet, aber ein Stein nicht zur Lady
	 * wird und überprüfe dies auch.
	 */
	@Test
	public void testSteinZuLadyFeld() {
		fail("Not yet implemented");
	}

	/**
	 * Schaue nach, ob das Zeitlimit funktioniert.
	 */
	@Test
	public void testZeitlimit() {
		fail("Not yet implemented");
	}

	/**
	 * Spielfeld aufbauen und einen gültigen Zug starten. Dies überprüfen.
	 */
	@Test
	public void testGueltigerZug() {
		fail("Not yet implemented");
	}

	/**
	 * Spielfeld aufbauen und einen ungültigen Zug starten. Dies überprüfen.
	 */
	@Test
	public void testUngueltigerZug() {
		fail("Not yet implemented");
	}

	/**
	 * Schlage einen Stein und schaue ob dieser entfernt wird.
	 */
	@Test
	public void testSteinGeschlagen() {
		fail("Not yet implemented");
	}

	/**
	 * Schaue, ob das spiel noch läuft.
	 */
	@Test
	public void testGameRunning() {
		fail("Not yet implemented");
	}

	/**
	 * Schaue ob das spiel vorbei ist.
	 */
	@Test
	public void testGameNotRunning() {
		fail("Not yet implemented");
	}

	/**
	 * Baue Spielsituationen in denen der Computer gewinnt und überprüfe dies.
	 */
	@Test
	public void testComputerGewinnt() {
		fail("Not yet implemented");
	}

	/**
	 * Baue Spielsituationen in denen der Computer verliert und überprüfe dies.
	 */
	@Test
	public void testComputerVerliert() {
		fail("Not yet implemented");
	}

	/**
	 * Schaue nach ob bei der ersten Spielfeldinizialisierung alle Spielsteine
	 * korrekt stehen.
	 */
	@Test
	public void testSteineAnKorrektPlatziert() {
		fail("Not yet implemented");
	}

}
