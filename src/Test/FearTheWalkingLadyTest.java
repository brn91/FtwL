package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import prototyp1.fear_the_walking_lady.modell.Koordinate;
import prototyp1.fear_the_walking_lady.modell.Lady;
import prototyp1.fear_the_walking_lady.modell.Stone;
import prototyp1.fear_the_walking_lady.modell.Spieler;
import prototyp1.fear_the_walking_lady.modell.Spieler.spielerFarbe;

public class FearTheWalkingLadyTest {
	// Hier ein Spielfeld erstellen, welches dann in den einzelnen Testcases nur
	// veränderd wird.
	/**
	 * Erzeuge einen weißen Stein und schaut, ob er richtig erzeugt wurde.
	 */
	@Test
	public void testErzeugeSteinWeiss() {
		Stone a = new Stone("Weiß", new Koordinate(1, 'A'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
	}
	/**
	 * Erzeuge einen schwarzen Stein und schaut, ob er richtig erzeugt wurde.
	 */
	@Test
	public void testErzeugeSteinSchwarz() {
		Stone a = new Stone("Schwarz", new Koordinate(2, 'F'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
	}
	/**
	 * Erzeuge eine schwarze Lady und schaut, ob diese Korrekt inizialisiert wird.
	 */
	@Test
	public void testLadySchwarz() {
		Stone a = new Lady("Schwarz", new Koordinate(2, 'F'));
		assertEquals("Schwarz", a.getColor());
		assertEquals(2, a.getKoordinate().getZahl());
		assertEquals('F', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}
	/**
	 * Erzeuge eine weiße Lady und schaut, ob diese Korrekt inizialisiert wird.
	 */
	@Test
	public void testLadyWeiss() {
		Stone a = new Lady("Weiß", new Koordinate(1, 'A'));
		assertEquals("Weiß", a.getColor());
		assertEquals(1, a.getKoordinate().getZahl());
		assertEquals('A', a.getKoordinate().getBuchstabe());
		assertEquals(true, a instanceof Lady);
	}
	/**
	 * Erzeugt einen weißen Stein und überprüft ob dieser zur Lady werden darf.
	 */
	@Test
	public void testDarfSteinZuLadyWeiss() {
		Spieler a = new Spieler(spielerFarbe.WEIß,"A1");
		assertEquals(true,a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}
	/**
	 * Erzeugt einen schwarzen Stein und überprüft ob dieser zur Lady werden darf.
	 */
	@Test
	public void testDarfSteinZuLadySchwarz() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ,"F6");
		
		assertEquals(true,a.ueberpruefeObSteinZuDame(a.getStones().getFirst()));
	}
	
	/**
	 * Erzeugt einen weißen Stein und macht diesen zur Lady.
	 */
	@Test
	public void testWirdSteinZuLadyWeiss() {
		Spieler a = new Spieler(spielerFarbe.WEIß,"A1");
		a.stoneToLady(new Koordinate(1,'A'));
		assertEquals(true,(a.getStones().getFirst()) instanceof Lady);
	}
	/**
	 * Erzeugt einen schwarzen Stein und und macht diesen zur Lady.
	 */
	@Test
	public void testWirdSteinZuLadySchwarz() {
		Spieler a = new Spieler(spielerFarbe.SCHWARZ,"F6");
		a.stoneToLady(new Koordinate(6,'F'));
		assertEquals(true,(a.getStones().getFirst()) instanceof Lady);
	}
	

}
