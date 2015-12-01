package prototyp1.fear_the_walking_lady.modell;

import java.util.LinkedList;

public class Field {
	private String[][] feld;
	
	public Field(LinkedList<Stone> spieler1Stones, LinkedList<Stone> spieler2Stones){
		//Initialisieren des leeren Felds mit Seitenbeschriftung
		int zeilen = Koordinate.MAX_Z_K + 1;
		int spalten = Koordinate.MAX_B_K - 'A' + 2;
		
		this.feld = new String[zeilen][spalten];
		
		//Nummernbeschriftung
		for(int i = 0; i < zeilen - 1; i++){
			this.feld[i][0] = String.valueOf(i + 1);
		}
		
		//Buchstabenbeschriftung
		this.feld[zeilen-1][0] = "  ";
		for(int i = 1; i < spalten; i++){
			this.feld[zeilen-1][i] = (char)('A' + i - 1) + " ";
		}
		
		//Standardfeld mit schwarzen Kacheln füllen
		//Für jede Zeile...
		for(int i = 0; i < zeilen-1; i++){
			//...jede Spalte mit Leerzeichen oder Kacheln füllen
			for(int j = 1; j < spalten; j++){
				
				//Wenn entweder aktuelleZeile ungerade ist oder 
				//aktuelleSpalte ungerade ist, füge Leerzeichen ein
				if((i%2 ^ j%2) == 1){
					this.feld[i][j] = "  ";
				}else{
					this.feld[i][j] = " \u25A0";
				}
			}
		}
		
		//Füge die Spielsteine in das Spielfeld ein
		insertPlayerStones(spieler1Stones);
		insertPlayerStones(spieler2Stones);
			
	}
	
	/**
	 * Rückgabe des aktuellen Spielfelds.
	 * 
	 * @return feld Das Spielfeld
	 */
	public String[][] getFeld(){
		return this.feld;
	}
	
	private void insertPlayerStones(LinkedList<Stone> playerStones){
		int zahlBuf;
		int buchstabenBuf;
		
		for(Stone buf : playerStones){
			zahlBuf = buf.getKoordinate().getZahl() - 1;
			buchstabenBuf = buf.getKoordinate().getBuchstabe() - 'A' + 1;
			
			if(buf.getColor().equals("Weiß")){
				if(buf instanceof Lady){
					this.feld[zahlBuf][buchstabenBuf] = " \u263A";
				}else{
					this.feld[zahlBuf][buchstabenBuf] = " \u25CB";
				}
			}else{
				if(buf instanceof Lady){
					this.feld[zahlBuf][buchstabenBuf] = " \u263B";
				}else{
					this.feld[zahlBuf][buchstabenBuf] = " \u25CF";
				}
			}			
		}
	}
}
