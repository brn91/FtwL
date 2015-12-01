package prototyp1.fear_the_walking_lady.view;

import prototyp1.fear_the_walking_lady.modell.*;
import java.util.Scanner;

public class GameMenue {
	private Field myField;
	private Spieler player1sturn;
	
	public GameMenue(Field feld, Spieler player1sturn){
		this.myField = feld;
		this.player1sturn = player1sturn;
	}
	
	public Bewegungskoordinate zeichneDich(boolean player1turn){
		Scanner in = new Scanner(System.in);
		
		//Spielername als Membervariable
		if(player1turn){
			System.out.println("Spieler 1 ist dran");
		}else{
			System.out.println("Spieler 2 ist dran");
		}
		
		//Gebe für jede Zeile des Feldes...
		for(int i = 0; i < Koordinate.MAX_Z_K + 1; i++){
			//...jede Spalte auf dem Bildschirm aus
			for(int j = 0; j < Koordinate.MAX_B_K - 'A' + 2 ; j++){
				System.out.print(this.myField.getFeld()[i][j]);
			}
			System.out.println();
		}
		
		return null;
	}
	
}
