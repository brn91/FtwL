package prototyp1.fear_the_walking_lady.modell;

import java.util.LinkedList;

import prototyp1.fear_the_walking_lady.controller.GameController;

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
	
	/**
	 * Überprüft die Sytax der vom Spieler eingegebenen Koordinaten
	 * 
	 * @param spielerEingabe
	 *            Die vom Spieler eingegebenen Koordinaten
	 * 
	 * @return eingabeLegal Wahrheitswert
	 */
	public boolean koordinatenEingabeCheck(String spielerEingabe) {
		boolean eingabeLegal = true;

		// Länge der Eingabe überprüfen
		if (GameController.activateCheats) {
			if (spielerEingabe.length() < 3 || spielerEingabe.length() > 4) {
				eingabeLegal = false;
			}
			if (spielerEingabe.length() == 3) {
				if (spielerEingabe.charAt(2) != '@') {
					eingabeLegal = false;
				}
			}
		} else {
			if (spielerEingabe.length() != 4) {
				eingabeLegal = false;
			}
		}

		// Länge und die einzelnen Chars auf Gültigkeit überprüfen
		if (eingabeLegal) {
			if (spielerEingabe.charAt(0) < 'A' || spielerEingabe.charAt(0) > Koordinate.MAX_B_K) {
				eingabeLegal = false;
			} else if (spielerEingabe.charAt(1) < '1' || spielerEingabe.charAt(1) > (char) (Koordinate.MAX_Z_K + 48)) {
				eingabeLegal = false;
			}
			if ((GameController.activateCheats && (spielerEingabe.charAt(2) == '@')) == false) {
				if (spielerEingabe.charAt(2) < 'A' || spielerEingabe.charAt(2) > Koordinate.MAX_B_K) {
					eingabeLegal = false;
				} else
					if (spielerEingabe.charAt(3) < '1' || spielerEingabe.charAt(3) > (char) (Koordinate.MAX_Z_K + 48)) {
					eingabeLegal = false;
				}
			}
		}

		return eingabeLegal;
	}
	
	/**
	 * Überprüft ob ein Stein auf einem Feld ist, auf dem er zur Lady wird
	 * 
	 * @param stein Der zu testende Stein
	 * @return rueckgabe Wahr, wenn Stein zur Lady wird
	 */
	public boolean ueberpruefeObSteinZuDame(Stone stein) {
		boolean rueckgabe = false;
		
		if(stein.getColor() == "Weiß"){
			// Wenn der weiße Spieler das Ende des Felds erreicht hat, mache ihn zur Lady
			if(stein.getKoordinate().getZahl() == 1){
				rueckgabe = true;
			}
		}else{
			// Wenn der schwarze Spieler das Ende des Felds erreicht hat, mache ihn zur Lady
			if(stein.getKoordinate().getZahl() == Koordinate.MAX_Z_K){
				rueckgabe = true;
			}
		}
		
		return rueckgabe;
	}
	
	/**
	 * Ersetzt einen Stein des Spielers mit einer Lady
	 * 
	 * @param koordinate
	 *            Die Koordinate des Steins
	 */
	public void stoneToLady(Koordinate koordinate) {
		Stone indexChecker;
		Lady neueLady;
		int indexBuf;
		
		neueLady = new Lady(this.getFarbe(), koordinate);
		indexChecker = new Stone(this.getFarbe(), koordinate);
		indexBuf = this.getStones().indexOf(indexChecker);

		// Wenn Stein an der Position ist
		if (indexBuf != -1) {
			this.getStones().remove(indexBuf);
			this.getStones().add(neueLady);

		}
	}

	/**
	 * Wählt den in der Eingabe spezifizierten Stein aus, erstellt eine Liste
	 * aller möglichen Züge des Steins. Der Stein wird bewegt (falls möglich)
	 * und die Gegner auf seiner Bahn werden gelöscht.
	 * 
	 * @param bewegung
	 *            Welcher Stein wohin bewegt werden soll
	 * @param gegner Der Gegner des Spielers
	 * @return rundeGueltig Wahr, wenn die Eingabe umgesetzt werden konnte
	 */
	public boolean waehleSteinUndZiehe(Bewegungskoordinate bewegung, Spieler gegner) {
		LinkedList<LinkedList<Koordinate>> alleLegalenWege;
		LinkedList<LinkedList<Koordinate>> alleSchlagpflichtWege;
		LinkedList<Koordinate> zugWeg = null;	
		boolean rundeGueltig = true;
		boolean schlagpflichtBeachtet = false;
		int indexBuf;
		Stone bufStein;
		
		bufStein = new Stone(this.getFarbe(), bewegung.getStart());
		indexBuf = this.getStones().indexOf(bufStein);
		
		//Wenn der zu Ziehende Stein existiert
		if(indexBuf != -1){
			bufStein = this.getStones().get(indexBuf);
			
			alleLegalenWege = erzeugeGueltigeWegeStein(bewegung, gegner);
			alleSchlagpflichtWege = erzeugeSchlagpflichtWege(gegner);
			
			// Wenn Schlagpflichten bestehen
			if(alleSchlagpflichtWege.size() > 0){
				// Gehe die Liste alleSchalgpflichtWege durch...
				for (LinkedList<Koordinate> aktuellerWeg : alleSchlagpflichtWege) {
					// ...und suche nach einer Liste, welche die Koordinaten der Eingabe beinhaltet
					if (aktuellerWeg.getFirst().equals(bewegung.getStart()) && 
							aktuellerWeg.getLast().equals(bewegung.getZiel())) {
						schlagpflichtBeachtet = true;
					}
				}
			}else{
				schlagpflichtBeachtet = true;
			}
			
			// Gehe die Liste alleLegalenWege durch...
			for (LinkedList<Koordinate> aktuellerWeg : alleLegalenWege) {
				// ...und suche nach einer Liste, welche die Koordinaten der Eingabe enthält
				if (aktuellerWeg.getFirst().equals(bewegung.getStart()) && 
						aktuellerWeg.getLast().equals(bewegung.getZiel())) {
					zugWeg = aktuellerWeg;
				}
			}

			// Wenn kein gültiger Weg existiert
			if (zugWeg == null) {
				rundeGueltig = false;
			} else {
				// Gehe den gesamten Weg ab
				for (int i = 1; i < zugWeg.size(); i ++) {
					bufStein = new Stone(gegner.getFarbe(), zugWeg.get(i));
					
					if(ueberpruefeObSteinZuDame(new Stone(this.getFarbe(), zugWeg.get(i)))){
						stoneToLady(zugWeg.get(i));
					}
					
					// Wenn die aktuelle Koordinate einen Gegner beinhaltet, entferne ihn
					if(gegner.getStones().remove(bufStein)){
						System.out.println("Der Gegner auf " + bufStein.getKoordinate().getBuchstabe()
								+ bufStein.getKoordinate().getZahl() + " wurde geschlagen!");
					}
				}
				
				// Wenn die Schlagpflicht nicht beachtet wurde
				if(!schlagpflichtBeachtet){
					bufStein = new Stone(this.getFarbe(), 
							alleSchlagpflichtWege.getFirst().getFirst());
					// Wenn der gezogene Stein in der Schlagpflicht war, lösche ihn...
					if(this.getStones().get(indexBuf).equals(bufStein)){
						this.getStones().remove(bufStein);
						System.out.println("Der Stein auf " + bufStein.getKoordinate().toString() 
								+ " wurde aufgrund nicht eingehaltener Schlagpflicht entfernt!");
					// ...sonst ziehe den Stein und lösche den ersten schlagpflichtigen Stein
					}else{
						this.getStones().remove(bufStein);
						System.out.println("Der Stein auf " + bufStein.getKoordinate().toString() 
								+ " wurde aufgrund nicht eingehaltener Schlagpflicht entfernt!");
						// Ziehe den eigenen Stein an die Zielposition
						this.getStones().get(indexBuf-1).ziehen(bewegung.getZiel());
					}
				}else{
					// Ziehe den eigenen Stein an die Zielposition
					this.getStones().get(indexBuf).ziehen(bewegung.getZiel());
				}
			}
		}else{
			rundeGueltig = false;
		}

		return rundeGueltig;
	}
	
	/**
	 * Erzeugt eine Liste aller legalen Wege, die ein Spielstein nehmen kann
	 * Jede dieser legalen Wege ist eine Liste der Koordinaten, die auf dem
	 * entsprechenden Weg liegen.
	 * 
	 * @param bewegung
	 *            Welcher Stein wohin bewegt werden soll
	 * 
	 * @return alleLegalenWege Liste aller legalen Wege
	 */
	private LinkedList<LinkedList<Koordinate>> erzeugeGueltigeWegeStein(
			Bewegungskoordinate bewegung, Spieler gegner) {
		LinkedList<LinkedList<Koordinate>> alleLegalenWege = new LinkedList<LinkedList<Koordinate>>();
		int richtung;

		// Die Farbe des Spielers entscheidet darüber ob die Steine nach
		// unten(höhere Zahlen) oder nach oben(niedrigere Zahlen) bewegt werden
		// dürfen
		if (this.getFarbe().equals("Schwarz")) {
			richtung = 1;
		} else {
			richtung = -1;
		}
		
		// Teste das Feld vorne links von der Quellkoordinate! aus gesehen
		alleLegalenWege.addAll(erzeugeGueltigenTeilweg(bewegung.getStart(), richtung, richtung, gegner));
		// Teste das Feld vorne rechts von der Quellkoordinate! aus gesehen
		alleLegalenWege.addAll(erzeugeGueltigenTeilweg(bewegung.getStart(), richtung, -richtung, gegner));
		// Teste das Feld hinten links von der Quellkoordinate! aus gesehen
		alleLegalenWege.addAll(erzeugeGueltigenTeilweg(bewegung.getStart(), -richtung, richtung, gegner));
		// Teste das Feld hinten rechts von der Quellkoordinate! aus gesehen
		alleLegalenWege.addAll(erzeugeGueltigenTeilweg(bewegung.getStart(), -richtung, -richtung, gegner));

		return alleLegalenWege;
	}

	/**
	 * Subfunktion von erzeugeGueltigeWege(). Erzeugt einen Teilweg, 
	 * welcher an die Funktion zurückgegeben wird.
	 * 
	 * @param vonPos Die Koordinate des Spielersteins
	 * @param richtungZahl In welche Zahlenrichtung getestet wird
	 * @param richtungBuch In welche Buchstabenrichtung getestet wird
	 * @param gegner Der gegnerische Spieler
	 * @return alleLegalenWege Alle Wege in eine bestimmte Richtung
	 */
	private LinkedList<LinkedList<Koordinate>> erzeugeGueltigenTeilweg(
			Koordinate vonPos, int richtungZahl, int richtungBuch, Spieler gegner){
		
		LinkedList<LinkedList<Koordinate>> alleLegalenWege = new LinkedList<LinkedList<Koordinate>>();
		LinkedList<Koordinate> legalerWeg = new LinkedList<Koordinate>();
		Koordinate aktKoord = new Koordinate(vonPos.getZahl() + richtungZahl, 
				(char) (vonPos.getBuchstabe() + richtungBuch));
		Stone bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		Stone bufEigeneStone = new Stone(this.getFarbe(), aktKoord);
		int bufIndex;
		
		legalerWeg = new LinkedList<Koordinate>();
		legalerWeg.add(vonPos);
		// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
		if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
			// Wenn das zu testende Feld (aktKoord) frei ist
			if (!gegner.getStones().contains(bufGegnerStone) && 
					!this.getStones().contains(bufEigeneStone)) {
				bufIndex = this.getStones().indexOf(new Stone(this.getFarbe(), vonPos));			
				//Wenn der zu ziehende Stein eine Lady ist
				if(this.getStones().get(bufIndex) instanceof Lady){
					do{
						legalerWeg.add(aktKoord);
						alleLegalenWege.add(legalerWeg);
						legalerWeg = copy(legalerWeg);
						aktKoord = new Koordinate(aktKoord.getZahl() + richtungZahl,
								(char) (aktKoord.getBuchstabe() + richtungBuch));
						bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
						bufEigeneStone = new Stone(this.getFarbe(), aktKoord);
					//Solange die nächste Koordinate im Feld ist und keine Steine enthält
					}while(koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString()) &&
							!gegner.getStones().contains(bufGegnerStone) &&
							!this.getStones().contains(bufEigeneStone));
				//Wenn der zu ziehende Stein KEINE Lady ist...
				//...und er ein Schwarzer Stein in richtiger Richtung ist
				}else if (this.getFarbe().equals("Schwarz") && (richtungZahl == 1)) {
					legalerWeg.add(aktKoord);
					alleLegalenWege.add(legalerWeg);
				//...und er ein weißer Stein in richtiger Richtung ist
				}else if (this.getFarbe().equals("Weiß") && (richtungZahl == -1)) {
					legalerWeg.add(aktKoord);
					alleLegalenWege.add(legalerWeg);
				}
			}
			// Wenn ein Gegner auf dem aktuell zu testenden Feld (aktKoord) ist
			if (gegner.getStones().contains(bufGegnerStone)) {
				legalerWeg.add(aktKoord);
				aktKoord = new Koordinate(aktKoord.getZahl() + richtungZahl,
						(char) (aktKoord.getBuchstabe() + richtungBuch));
				// Wenn das zu testende Feld (aktKoord) im Spielfeld liegt
				if (koordinatenEingabeCheck(vonPos.toString() + aktKoord.toString())) {
					alleLegalenWege = pruefeObGegnerSchlagbar(
							gegner, aktKoord, legalerWeg, alleLegalenWege);
				}
			}
		}
		return alleLegalenWege;	
	}
	
	/**
	 * Stellt fest, ob von der aktuellen Position eines Steines ein Gegner
	 * geschlagen werden kann. Wenn das der Fall ist, werden die Koordinaten in
	 * die Liste der legalenWege aufgenommen und Rekursiv nach weiteren
	 * schlagbaren Gegnern gesucht.
	 * 
	 * @param gegner
	 *            Der Gegner
	 * 
	 * @param eigene
	 *            Der aktuelle Spieler
	 * 
	 * @param aktKoord
	 *            Die Koordinate hinter dem gegnerischen Stein
	 * 
	 * @param legalerWeg
	 *            Der aktuelle legaleWeg, der aufgebaut wird
	 * 
	 * @param alleLegalenWege
	 *            Liste aller legalen Wege
	 * 
	 * @return alleLegalenWege Liste aller legalen Wege
	 */
	private LinkedList<LinkedList<Koordinate>> pruefeObGegnerSchlagbar(Spieler gegner,
			Koordinate aktKoord, LinkedList<Koordinate> legalerWeg,
			LinkedList<LinkedList<Koordinate>> alleLegalenWege) {
		Stone bufGegnerStone = new Stone(gegner.getFarbe(), aktKoord);
		Stone bufEigeneStone = new Stone(this.getFarbe(), aktKoord);
		Koordinate neuKoord;
		LinkedList<Koordinate> legalerWegZweig;

		// Wenn kein Stein auf der aktuellen Koordinate ist, ist Gegner
		// schlagbar
		if (!(this.getStones().contains(bufEigeneStone) || gegner.getStones().contains(bufGegnerStone))) {
			if (koordinatenEingabeCheck(aktKoord.toString() + aktKoord.toString())) {
				legalerWeg.add(aktKoord);
				alleLegalenWege.add(legalerWeg);
			}

			// Prüfe nun, ob von der aktuellen Position aus weitere Gegner
			// schlagbar sind
			neuKoord = new Koordinate(aktKoord.getZahl() - 1, (char) (aktKoord.getBuchstabe() - 1));
			// Falls man nicht von links oben kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() - 2, (char) (aktKoord.getBuchstabe() - 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}

			neuKoord = new Koordinate(aktKoord.getZahl() + 1, (char) (aktKoord.getBuchstabe() + 1));
			// Falls man nicht von rechts unten kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() + 2, (char) (aktKoord.getBuchstabe() + 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}

			neuKoord = new Koordinate(aktKoord.getZahl() - 1, (char) (aktKoord.getBuchstabe() + 1));
			// Falls man nicht von rechts oben kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() - 2, (char) (aktKoord.getBuchstabe() + 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}

			neuKoord = new Koordinate(aktKoord.getZahl() + 1, (char) (aktKoord.getBuchstabe() - 1));
			// Falls man nicht von links unten kam...
			if (!legalerWeg.contains(neuKoord)) {
				bufGegnerStone = new Stone(gegner.getFarbe(), neuKoord);
				// ...und dort ein Gegner ist
				if (gegner.getStones().contains(bufGegnerStone)) {
					// Kopiere die Liste 'legalerWeg' und benutze Rekursion
					legalerWegZweig = copy(legalerWeg);
					legalerWegZweig.add(neuKoord);
					neuKoord = new Koordinate(aktKoord.getZahl() + 2, (char) (aktKoord.getBuchstabe() - 2));
					alleLegalenWege = pruefeObGegnerSchlagbar(gegner, neuKoord, legalerWegZweig,
							alleLegalenWege);
				}
			}
		}

		return alleLegalenWege;
	}

	/**
	 * Erzeugt alle Spielzugmöglichkeiten, bei der mindestens 1 Stein 
	 * des Gegners geschlagen wird. Diese Methode dient als Basis für
	 * das Überprüfen der Einhaltung der Schlagpflicht.
	 * 
	 * @return alleSchlagpflichtWege Gültige Wege für den Schlagpflichtabgleich
	 */
	private LinkedList<LinkedList<Koordinate>> erzeugeSchlagpflichtWege(Spieler gegner){
		LinkedList<LinkedList<Koordinate>> alleWege = new LinkedList<LinkedList<Koordinate>>();
		LinkedList<LinkedList<Koordinate>> alleSchlagpflichtWege = 
				new LinkedList<LinkedList<Koordinate>>();
		LinkedList<Koordinate> aktuellerWeg;
		Stone gegnerStein;
		boolean gueltigerWeg;
		
		//Erzeugt für jeden Stein des aktuellen Spielers alle gültigen Wege
		alleWege = erzeugeAlleWege(gegner);
		
		// Für jeden Weg...
		for(int i = 0; i < alleWege.size(); i++){
			gueltigerWeg = false;
			aktuellerWeg = alleWege.get(i);
			
			// ...gehe den gesamten Weg ab und...
			for (int j = 1; j < aktuellerWeg.size(); j ++) {
				gegnerStein = new Stone(gegner.getFarbe(), aktuellerWeg.get(j));
				
				// ...wenn die aktuelle Koordinate einen Gegner beinhaltet...
				if(gegner.getStones().contains(gegnerStein)){
					// ...markiere den Weg als gültig
					gueltigerWeg = true;
				}					
			}
			
			// Wenn der aktuelle Weg gültig ist, füge ihn zur Liste hinzu
			if(gueltigerWeg){
				alleSchlagpflichtWege.add(aktuellerWeg);
			}
		}
			
		return alleSchlagpflichtWege;
	}

	/**
	 * Erzeugt für jeden Stein des aktuellen Spielers alle gültigen Wege
	 * 
	 * @param gegner Der gegner dieses Spielers
	 * @return alleWege Alle legalen Züge des Spielers
	 */
	public LinkedList<LinkedList<Koordinate>> erzeugeAlleWege(Spieler gegner){
		LinkedList<LinkedList<Koordinate>> alleWege = new LinkedList<LinkedList<Koordinate>>();
		Bewegungskoordinate bewegung;
		
		for(int i = 0; i < this.getStones().size(); i++){
			bewegung = new Bewegungskoordinate(this.getStones().get(i).getKoordinate(),
					this.getStones().get(i).getKoordinate());
			alleWege.addAll(erzeugeGueltigeWegeStein(bewegung, gegner));
		}
		
		return alleWege;
	}
	
	/**
	 * Erzeugt eine tiefe Kopie einer Liste aus Koordinaten.
	 * 
	 * @param koordinatenListe
	 *            Die Liste, die kopiert wird
	 * @return kopierteListe Die Kopie
	 */
	private LinkedList<Koordinate> copy(LinkedList<Koordinate> koordinatenListe) {
		LinkedList<Koordinate> kopierteListe = new LinkedList<Koordinate>();

		for (Koordinate buf : koordinatenListe) {
			kopierteListe.addLast(buf);
		}

		return kopierteListe;
	}

	/**
	 * Stellt fest, ob dieser Spieler gewonnen oder verloren hat
	 * 
	 * @param verlierer Der Verlierer
	 * @return gewinner Der Gewinner
	 */
	public int hatGewonnen(Spieler verlierer){
		int gewinner = 0;
		
		// Wenn es einen Verlierer gibt
		if(verlierer != null){
			if(verlierer.equals(this)){
				gewinner = -1;
			}else{
				gewinner = 1;
			}
		}
		
		return gewinner;
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
