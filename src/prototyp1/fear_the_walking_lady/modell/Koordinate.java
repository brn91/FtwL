package prototyp1.fear_the_walking_lady.modell;

public class Koordinate {
	public static final char MAX_B_K = 'F';
	public static final int MAX_Z_K = 6;
	
	private int zahl;
	private char buchstabe;
	
	Koordinate(int zahl, char buchstabe){
		this.zahl = zahl;
		this.buchstabe = buchstabe;
	}
	
	public int getZahl(){
		return this.zahl;
	}
	
	public void setZahl(int zahl){
		this.zahl = zahl;
	}
	
	public char getBuchstabe(){
		return this.buchstabe;
	}
	
	public void setBuchstabe(char buchstabe){
		this.buchstabe = buchstabe;
	}
}
