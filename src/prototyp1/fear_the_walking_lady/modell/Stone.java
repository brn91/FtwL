package prototyp1.fear_the_walking_lady.modell;

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
	
	public void ziehen(Koordinate Bewegungskoordinate){
		
	}
	
	public void schlagen(Stone stoneGegner){
		
	}
}
