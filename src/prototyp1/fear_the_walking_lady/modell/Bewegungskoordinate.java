package prototyp1.fear_the_walking_lady.modell;

public class Bewegungskoordinate{
	private Koordinate start;
	private Koordinate ziel;
	
	/**
	 * Erzeugt eine Bewegungskoordinate, welche Start und Ziel einer
	 * Steinbewegung beinhaltet
	 * 
	 * @param start Startposition des Steines
	 * @param ziel Zielposition des Steines
	 */
	public Bewegungskoordinate(Koordinate start, Koordinate ziel){
		this.start = start;
		this.ziel = ziel;
	}
	
	/**
	 * Gibt die Startkoordinate zurück
	 * 
	 * @return start
	 */
	public Koordinate getStart(){
		return this.start;
	}
	
	/**
	 * Gibt die Zielkoordinate zurück
	 * 
	 * @return ziel
	 */
	public Koordinate getZiel(){
		return this.ziel;
	}
	
	@Override
	public String toString(){
		String rueckgabe = start.toString() + ziel.toString();
		
		return rueckgabe;
	}
}
