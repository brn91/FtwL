package prototyp1.fear_the_walking_lady.modell;

import java.util.LinkedList;

public class Stone {
	private String color;
	private Koordinate koordinate;
	
	//Konstruktor
	public Stone(String color, Koordinate koordinate){
		this.koordinate = koordinate;
		this.color = color;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public Koordinate getKoordinate(){
		return this.koordinate;
	}
	
	/**
	 * Setzt den Stein an die Zielkoordinate
	 *  
	 * @param zielKoord Die neue Koordinate
	 */
	public void ziehen(Koordinate zielKoord){
		this.koordinate = zielKoord;
	}
	
	public void schlagen(Stone stoneGegner){
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((koordinate == null) ? 0 : koordinate.hashCode());
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
		Stone other = (Stone) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (koordinate == null) {
			if (other.koordinate != null)
				return false;
		} else if (!koordinate.equals(other.koordinate))
			return false;
		return true;
	}	
	
}
