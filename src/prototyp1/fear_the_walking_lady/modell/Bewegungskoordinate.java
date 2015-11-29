package prototyp1.fear_the_walking_lady.modell;

public class Bewegungskoordinate extends Koordinate{
	int neuZahl;
	char neuBuchstabe;
	boolean fertig;
	
	Bewegungskoordinate(int zahl, char buchstabe, int neuzahl, char neubuchstabe){
		super(zahl, buchstabe);
		this.neuZahl = neuzahl;
		this.neuBuchstabe = neubuchstabe;
	}
}
