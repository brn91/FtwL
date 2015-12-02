package prototyp1.fear_the_walking_lady.modell;

public class Bewegungskoordinate{
	private Koordinate start;
	private Koordinate ziel;
	
	public Bewegungskoordinate(Koordinate start, Koordinate ziel){
		this.start = start;
		this.ziel = ziel;
	}
	
	public Koordinate getStartKoord(){
		return this.start;
	}
	
	public Koordinate getZielKoord(){
		return this.ziel;
	}
}
