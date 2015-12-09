package prototyp1.fear_the_walking_lady.modell;

public class Koordinate {
	public static final char MAX_B_K = 'F';
	public static final int MAX_Z_K = 6;
	
	private int zahl;
	private char buchstabe;
	
	public Koordinate(int zahl, char buchstabe){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buchstabe;
		result = prime * result + zahl;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Koordinate other = (Koordinate) obj;
		if (buchstabe != other.buchstabe)
			return false;
		if (zahl != other.zahl)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String rueckgabe = String.valueOf(buchstabe);
		rueckgabe += String.valueOf(zahl);
		
		return rueckgabe;
	}
	
}
