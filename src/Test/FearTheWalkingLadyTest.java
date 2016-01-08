package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import prototyp1.fear_the_walking_lady.controller.GameController;
import prototyp1.fear_the_walking_lady.modell.Ki;
import prototyp1.fear_the_walking_lady.modell.Koordinate;
import prototyp1.fear_the_walking_lady.modell.Lady;
import prototyp1.fear_the_walking_lady.modell.Stone;
import prototyp1.fear_the_walking_lady.modell.Spieler;
import prototyp1.fear_the_walking_lady.modell.Spieler.spielerFarbe;

public class FearTheWalkingLadyTest {
	// Hier ein Spielfeld erstellen, welches dann in den einzelnen Testcases nur
	// veränderd wird.

	GameController theGame = new GameController();

	/**
	 * Erzeuge einen weißen Stein und schaut, ob er richtig erzeugt wurde.
	 */
	@Test
	public void testErzeugeSteinWeiss1() {
		Stone a = new Stone("Weiß", new Koordinate(2, 'A'));
		assertEquals("Weiß", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss2() {
		Stone a = new Stone("Weiß", new Koordinate(1, 'B'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('B', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss3() {
		Stone a = new Stone("Weiß", new Koordinate(3, 'B'));
		assertEquals("Weiß", a.getColor());
		assertEquals(3, a.getKoordinate().getZahl());
		assertEquals('B', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss4() {
		Stone a = new Stone("Weiß", new Koordinate(1, 'F'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss5() {
		Stone a = new Stone("Weiß", new Koordinate(6, 'E'));
		assertEquals("Weiß", a.getColor());
		assertEquals(6, a.getKoordinate().getZahl());
		assertEquals('E', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss6() {
		Stone a = new Stone("Weiß", new Koordinate(4, 'C'));
		assertEquals("Weiß", a.getColor());
		assertEquals(4, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss7() {
		Stone a = new Stone("Weiß", new Koordinate(6, 'A'));
		assertEquals("Weiß", a.getColor());
		assertEquals(6, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss8() {
		Stone a = new Stone("Weiß", new Koordinate(1, 'D'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('D', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss9() {
		Stone a = new Stone("Weiß", new Koordinate(6, 'C'));
		assertEquals("Weiß", a.getColor());
		assertEquals(6, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinWeiss10() {
		Stone a = new Stone("Weiß", new Koordinate(3, 'F'));
		assertEquals("Weiß", a.getColor());
		assertEquals(3, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
	}

	/**
	 * Erzeuge einen schwarzen Stein und schaut, ob er richtig erzeugt wurde.
	 */
	@Test
	public void testErzeugeSteinSchwarz1() {
		Stone a = new Stone("Schwarz", new Koordinate(1, 'F'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz2() {
		Stone a = new Stone("Schwarz", new Koordinate(1, 'A'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz3() {
		Stone a = new Stone("Schwarz", new Koordinate(5, 'B'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(5, a.getKoordinate().getZahl());
		assertEquals('B', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz4() {
		Stone a = new Stone("Schwarz", new Koordinate(1, 'B'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('B', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz5() {
		Stone a = new Stone("Schwarz", new Koordinate(2, 'C'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz6() {
		Stone a = new Stone("Schwarz", new Koordinate(6, 'C'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(6, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz7() {
		Stone a = new Stone("Schwarz", new Koordinate(3, 'D'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(3, a.getKoordinate().getZahl());
		assertEquals('D', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz8() {
		Stone a = new Stone("Schwarz", new Koordinate(1, 'D'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('D', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz9() {
		Stone a = new Stone("Schwarz", new Koordinate(2, 'E'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('E', a.getKoordinate().getBuchstabe());
	}

	@Test
	public void testErzeugeSteinSchwarz10() {
		Stone a = new Stone("Schwarz", new Koordinate(3, 'F'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(3, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
	}

	/**
	 * Erzeuge eine schwarze Lady und schaut, ob diese Korrekt inizialisiert
	 * wird.
	 */
	@Test
	public void testLadySchwarz1() {
		Stone a = new Lady("Schwarz", new Koordinate(2, 'F'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadySchwarz2() {
		Stone a = new Lady("Schwarz", new Koordinate(2, 'C'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadySchwarz3() {
		Stone a = new Lady("Schwarz", new Koordinate(2, 'E'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('E', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadySchwarz4() {
		Stone a = new Lady("Schwarz", new Koordinate(3, 'B'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(3, a.getKoordinate().getZahl());
		assertEquals('B', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadySchwarz5() {
		Stone a = new Lady("Schwarz", new Koordinate(6, 'C'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(6, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	/**
	 * Erzeuge eine weiße Lady und schaut, ob diese Korrekt inizialisiert wird.
	 */
	@Test
	public void testLadyWeiss1() {
		Stone a = new Lady("Weiß", new Koordinate(1, 'A'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadyWeiss2() {
		Stone a = new Lady("Weiß", new Koordinate(5, 'B'));
		assertEquals("Weiß", a.getColor());
		assertEquals(5, a.getKoordinate().getZahl());
		assertEquals('B', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadyWeiss3() {
		Stone a = new Lady("Weiß", new Koordinate(3, 'D'));
		assertEquals("Weiß", a.getColor());
		assertEquals(3, a.getKoordinate().getZahl());
		assertEquals('D', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadyWeiss4() {
		Stone a = new Lady("Weiß", new Koordinate(4, 'E'));
		assertEquals("Weiß", a.getColor());
		assertEquals(4, a.getKoordinate().getZahl());
		assertEquals('E', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	@Test
	public void testLadyWeiss5() {
		Stone a = new Lady("Weiß", new Koordinate(2, 'C'));
		assertEquals("Weiß", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('C', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}

	/**
	 * Erzeugt einen weißen Stein und überprüft ob dieser zur Lady werden darf.
	 */
	@Test
	public void testDarfSteinZuLadyWeiss1() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "A1");
		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	public void testDarfSteinZuLadyWeiss2() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "B1");
		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	public void testDarfSteinZuLadyWeiss3() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "D1");
		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	public void testDarfSteinZuLadyWeiss4() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "F1");
		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	/**
	 * Erzeugt einen schwarzen Stein und überprüft ob dieser zur Lady werden
	 * darf.
	 */
	@Test
	public void testDarfSteinZuLadySchwarz1() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "E6");

		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfSteinZuLadySchwarz2() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "A6");

		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfSteinZuLadySchwarz3() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "C6");

		assertEquals(true, a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	/**
	 * Erzeugt einen Weißen Stein der nicht zur Lady werden darf.
	 */
	@Test
	public void testDarfNichtSteinZuLadyWeiss1() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "F6");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadyWeiss2() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "A2");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadyWeiss3() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "C2");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadyWeiss4() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "B3");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadyWeiss5() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "F3");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	/**
	 * Erzeugt einen Schwarzen Stein der nicht zur Lady werden darf.
	 */
	@Test
	public void testDarfNichtSteinZuLadySchwarz1() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "A2");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadySchwarz2() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "B5");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadySchwarz3() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "D1");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadySchwarz4() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "E4");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	@Test
	public void testDarfNichtSteinZuLadySchwarz5() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "F5");

		assertFalse(a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}

	/**
	 * Erzeugt einen weißen Stein und macht diesen zur Lady.
	 */
	@Test
	public void testWirdSteinZuLadyWeiss1() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "B1");
		a.stoneToLady(new Koordinate(1, 'B'));
		assertEquals(true, (a.getStones().getFirst()) instanceof Lady);
	}

	@Test
	public void testWirdSteinZuLadyWeiss2() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "D1");
		a.stoneToLady(new Koordinate(1, 'D'));
		assertEquals(true, (a.getStones().getFirst()) instanceof Lady);
	}

	@Test
	public void testWirdSteinZuLadyWeiss3() {
		Spieler a = new Spieler(spielerFarbe.WEIß, "F1");
		a.stoneToLady(new Koordinate(1, 'F'));
		assertEquals(true, (a.getStones().getFirst()) instanceof Lady);
	}

	/**
	 * Erzeugt einen schwarzen Stein und und macht diesen zur Lady.
	 */
	@Test
	public void testWirdSteinZuLadySchwarz1() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "E6");
		a.stoneToLady(new Koordinate(6, 'E'));
		assertEquals(true, (a.getStones().getFirst()) instanceof Lady);
	}

	@Test
	public void testWirdSteinZuLadySchwarz2() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "C6");
		a.stoneToLady(new Koordinate(6, 'C'));
		assertEquals(true, (a.getStones().getFirst()) instanceof Lady);
	}

	@Test
	public void testWirdSteinZuLadySchwarz3() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ, "A6");
		a.stoneToLady(new Koordinate(6, 'A'));
		assertEquals(true, (a.getStones().getFirst()) instanceof Lady);
	}

	/**
	 * Erzeugt Spieler und KI auf dem Spielfeld und schaut ob ein Zug korrekt
	 * durchgeführt wird.
	 */
	@Test
	public void testSpielerMachtKorrektenZug1() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("A2B3"));
	}

	@Test
	public void testSpielerMachtKorrektenZug2() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("A2B3"));
	}

	@Test
	public void testSpielerMachtKorrektenZug3() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "B1"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("B1A2"));
	}

	@Test
	public void testSpielerMachtKorrektenZug4() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "C2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("C2D3"));
	}

	@Test
	public void testSpielerMachtKorrektenZug5() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "C4"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("C4D5"));
	}

	@Test
	public void testSpielerMachtKorrektenZug6() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "E4"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("E4F5"));
	}

	@Test
	public void testSpielerMachtKorrektenZug7() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "F1"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("F1E2"));
	}

	@Test
	public void testSpielerMachtKorrektenZug8() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "F3"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("F3E4"));
	}

	@Test
	public void testSpielerMachtKorrektenZug9() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "D3"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("D3C4"));
	}

	@Test
	public void testSpielerMachtKorrektenZug10() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A4"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertTrue(theGame.ueberpruefeSpielzug("A4B5"));
	}

	@Test
	public void testSpielerMachtKorrektenZug11() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "A6"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("A6B5"));
	}

	@Test
	public void testSpielerMachtKorrektenZug12() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "C6"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("C6D5"));
	}

	@Test
	public void testSpielerMachtKorrektenZug13() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B5"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("B5C4"));
	}

	@Test
	public void testSpielerMachtKorrektenZug14() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "E2"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("E2F1"));
	}

	@Test
	public void testSpielerMachtKorrektenZug15() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "F3"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("F3E2"));
	}

	@Test
	public void testSpielerMachtKorrektenZug16() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "E6"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("E6D5"));
	}

	@Test
	public void testSpielerMachtKorrektenZug17() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "A4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertTrue(theGame.ueberpruefeSpielzug("A4B3"));
	}

	/**
	 * Gleiches Schema, nur diesmal macht die Ki den Zug
	 */
	@Test
	public void testKIMachtKorrektenZug1() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "A4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		assertEquals("A2B3", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug2() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "A4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "F1"));
		assertEquals("F1E2", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug3() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "A4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "F5"));
		assertEquals("F5E6", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug4() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "A4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "F3"));
		assertEquals("F3E4", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug5() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B3"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "C4"));
		assertEquals("C4A2", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug6() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "C4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "D5"));
		assertEquals("D5B3", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug7() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "C4C2"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "D5"));
		assertEquals("D5D1", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug8() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "C4C2E2"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "D5"));
		assertEquals("D5F3", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug9() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "D3"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "E2"));
		assertEquals("E2C4", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug10() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B5"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "F1"));
		theGame.getSpieler2().stoneToLady(new Koordinate(1, 'F'));
		assertEquals("F1A6", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug11() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B3"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "D1"));
		theGame.getSpieler2().stoneToLady(new Koordinate(1, 'D'));

		assertEquals("D1A4", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug12() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B3"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "E6"));
		theGame.getSpieler2().stoneToLady(new Koordinate(6, 'E'));
		assertEquals("E6A2", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug13() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "C4"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "D5"));
		theGame.getSpieler2().stoneToLady(new Koordinate(5, 'D'));
		assertEquals("D5B3", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	@Test
	public void testKIMachtKorrektenZug14() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "C4C2"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "E6"));
		theGame.getSpieler2().stoneToLady(new Koordinate(6, 'E'));
		assertEquals("E6D1", ((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1()));
	}

	/**
	 * Testet ob das Spiel vorbei ist.
	 * 
	 */
	@Test
	public void testSpielVorbei1() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B5C6"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "F1"));
		theGame.getSpieler1().stoneToLady(new Koordinate(6, 'C'));
		theGame.getSpieler2().stoneToLady(new Koordinate(1, 'F'));

		((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1());
		assertFalse(theGame.spielLaeuft());
	}

	@Test
	public void testSpielVorbei2() {
		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "D3C6"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "F1"));
		theGame.getSpieler1().stoneToLady(new Koordinate(6, 'C'));
		theGame.getSpieler2().stoneToLady(new Koordinate(1, 'F'));

		((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1());
		assertFalse(theGame.spielLaeuft());
	}

	@Test
	public void testSpielVorbei3() {

		theGame.setSpieler1(new Spieler(spielerFarbe.WEIß, "B3C6"));
		theGame.setSpieler2(new Ki(spielerFarbe.SCHWARZ, "A2"));
		theGame.getSpieler1().stoneToLady(new Koordinate(6, 'C'));
		theGame.getSpieler2().stoneToLady(new Koordinate(2, 'A'));

		((Ki) theGame.getSpieler2()).kiZug(theGame.getSpieler1());
		assertFalse(theGame.spielLaeuft());
	}

	/**
	 * Erzeugt Spieler und KI auf dem Spielfeld und schaut ob ein Zug korrekt
	 * durchgeführt wird.
	 */
	@Test
	public void testSpielerMachtFalschenZug1() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("A1B3"));
	}
	@Test
	public void testSpielerMachtFalschenZug2() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("A2B1"));
	}
	@Test
	public void testSpielerMachtFalschenZug3() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("A2A1"));
	}
	@Test
	public void testSpielerMachtFalschenZug4() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("A2@3"));
	}
	@Test
	public void testSpielerMachtFalschenZug5() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "B1"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("B1C0"));
	}
	@Test
	public void testSpielerMachtFalschenZug6() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "E6"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("E6F7"));
	}
	@Test
	public void testSpielerMachtFalschenZug7() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "A2"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("aaaa"));
	}
	@Test
	public void testSpielerMachtFalschenZug8() {
		theGame.setSpieler1(new Spieler(spielerFarbe.SCHWARZ, "D1"));
		theGame.setSpieler2(new Ki(spielerFarbe.WEIß, "A6"));
		assertFalse(theGame.ueberpruefeSpielzug("D1F3"));
	}
}
