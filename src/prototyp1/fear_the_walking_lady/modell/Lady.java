package prototyp1.fear_the_walking_lady.modell;

public class Lady extends Stone{
	
	public Lady(String color, Koordinate koordinate){
		super(color, koordinate);
	}
	
	/**
	 * Setzt die Lady an die Zielkoordinate
	 *  
	 * @param zielKoord Die neue Koordinate
	 */
	public void ziehen(Koordinate zielKoord){
		this.koordinate = zielKoord;
	}
	
}
