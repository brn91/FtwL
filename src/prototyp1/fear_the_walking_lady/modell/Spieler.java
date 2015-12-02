package prototyp1.fear_the_walking_lady.modell;

import java.util.LinkedList;

public class Spieler {
	private LinkedList<Stone> playerStones;
	private String farbe;
	
	public enum spielerFarbe{
		WEIß,
		SCHWARZ
	}
	
	/**
	 * Erstellt die Spielersteine an den Startkoordinaten 
	 * und weist ihm eine Farbe zu.
	 * 
	 * @param color Farbe des Spielers
	 */
	public Spieler(spielerFarbe color){
		char linkesteBkoordinate;
		
		this.playerStones = new LinkedList<Stone>();
		
		if(color == spielerFarbe.WEIß){
			this.farbe = "Weiß";
			
			//Für die beiden unteren Zahlenreihen...
			for(int i = Koordinate.MAX_Z_K; i >= Koordinate.MAX_Z_K - 1; i--){
				//...berechne das linkeste schwarze Feld der aktuellen Zahlenreihe
				linkesteBkoordinate = (char)('A' + i%2);
				//Setze vom linkesten Feld ausgehend alle 2 Felder einen Stein
				for(char c = linkesteBkoordinate; c <= Koordinate.MAX_B_K; c += 2){
					
					//Wenn aktuelle Koordinate untere linke Ecke, setze Lady, sonst Stein
					if(i == Koordinate.MAX_Z_K && c == linkesteBkoordinate){
						this.playerStones.add(new Lady(farbe, new Koordinate(i, c)));
					}else{
						this.playerStones.add(new Stone(farbe, new Koordinate(i, c)));
					}		
				}
			}
		}else{
			this.farbe = "Schwarz";
			
			//Für die beiden oberen Zahlenreihen...
			for(int i = 1; i <= 2; i++){
				//...berechne das linkeste schwarze Feld der aktuellen Zahlenreihe
				linkesteBkoordinate = (char)('A' + i%2);
				//Setze vom linkesten Feld ausgehend alle 2 Felder einen Stein
				for(char c = linkesteBkoordinate; c <= Koordinate.MAX_B_K; c += 2){
					
					//Wenn aktuelle Koordinate obere rechte Ecke, dann Lady, sonst Stein
					if(i == 1 && (c == Koordinate.MAX_B_K || c == Koordinate.MAX_B_K-1)){
						this.playerStones.add(new Lady(farbe, new Koordinate(i, c)));
					}else{
						this.playerStones.add(new Stone(farbe, new Koordinate(i, c)));
					}		
				}
			}
		}
	}
	
	/**
	 * Gibt die Steine des Spieler zurück
	 * 
	 * @return playerStones Liste der Steine
	 */
	public LinkedList<Stone> getStones(){
		return this.playerStones;
	}
	
	/**
	 * Gibt die Farbe des Spielers zurück
	 * 
	 * @return farbe Farbe
	 */
	public String getFarbe(){
		return this.farbe;
	}
	
	//(DEBUG)
	//Gebe die Koordinaten aller Steine des Spielers auf dem Bildschirm aus
	public void printStones(){
		for(Stone buf : this.playerStones){
			System.out.print(buf.getKoordinate().getBuchstabe() + "" + buf.getKoordinate().getZahl());
			
			if(buf instanceof Lady){
				System.out.println(" Lady");
			}else{
				System.out.println();
			}
		}
	}
}
