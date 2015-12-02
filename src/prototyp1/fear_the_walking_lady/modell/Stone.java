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
	
	/**
	 * Versucht diesen Stein auf eine Zielkoordinate
	 * zu setzen.
	 *  
	 * @param zielKoord Die neue Koordinate
	 * @param player1turn Ist Spieler1 dran?
	 * @param spieler1 Spieler1
	 * @param spieler2 Spieler2
	 * @return konnteZiehen Ob der Zug funktioniert hat
	 */
	public boolean ziehen(Koordinate zielKoord, boolean player1turn, 
			Spieler spieler1, Spieler spieler2){
		Stone bufStone1;
		Stone bufStone2;
		boolean konnteZiehen = true;

		//Teste ob Zielkoordinate frei ist
		//Teste ob Ziel obere oder untere Zeile ist
		if(this.koordinate.getZahl() + 1 == zielKoord.getZahl() ||
				this.koordinate.getZahl() - 1 == zielKoord.getZahl()){
			//Teste ob Ziel rechte oder linke Spalte ist
			if(this.koordinate.getBuchstabe() + 1 == zielKoord.getBuchstabe() ||
					this.koordinate.getBuchstabe() - 1 == zielKoord.getBuchstabe()){
				bufStone1 = new Stone(spieler1.getFarbe(), zielKoord);
				bufStone2 = new Stone(spieler2.getFarbe(), zielKoord);
				
				//Teste ob die Koordinate bereits belegt ist
				if(spieler2.getStones().contains(bufStone2) || 
						spieler1.getStones().contains(bufStone1)){
					konnteZiehen = false;
				}else{
					//Ziehe Stein
					this.koordinate = zielKoord;			
				}
			}else{
				konnteZiehen = false;
			}
		}else{
			konnteZiehen = false;
		}
		
		return konnteZiehen;
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
