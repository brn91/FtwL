package prototyp1.fear_the_walking_lady.modell;

public class Bewegungskoordinate extends Koordinate{
	private int neuZahl;
	private char neuBuchstabe;
	private boolean fertig;
	
	public Bewegungskoordinate(int zahl, char buchstabe, int neuzahl, char neubuchstabe){
		super(zahl, buchstabe);
		this.neuZahl = neuzahl;
		this.neuBuchstabe = neubuchstabe;
	}
	
	public int getNeuZahl(){
		return this.neuZahl;
	}
	
	public char getNeuBuchstabe(){
		return this.neuBuchstabe;
	}
}
