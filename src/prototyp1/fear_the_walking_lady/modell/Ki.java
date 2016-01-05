package prototyp1.fear_the_walking_lady.modell;

import java.util.LinkedList;
import java.util.Random;

public class Ki extends Spieler{

	public Ki(spielerFarbe color){
		super(color);
	}
	
	/**
	 * Methode mit der die KI entscheidet, welchen Zug sie machen wird.
	 * 
	 * @param mensch Der Gegner der KI
	 * 
	 * @return zugGueltig Wahr, wenn Zug gueltig ist
	 */
	public String kiZug(Spieler gegner){
		LinkedList<LinkedList<Koordinate>> alleLegalenWege = new LinkedList<LinkedList<Koordinate>>();
		LinkedList<LinkedList<Koordinate>> schlagliste = new LinkedList<LinkedList<Koordinate>>();
		LinkedList<Koordinate> zugWeg = null;
		Bewegungskoordinate bewegung = null;
		Random rand = new Random();
		int r;
		
		alleLegalenWege.addAll(erzeugeAlleWege(gegner));
		schlagliste.addAll(erzeugeSchlagpflichtWege(gegner));
		
		//Suche nach dem Stein, der die meisten Gegner schlägt
		if(schlagliste.size() > 0){
			zugWeg = schlagliste.get(0);
			for(int i=1; i < schlagliste.size(); i++){
				//Wenn der aktuelle Weg länger als der vorherige ist
				if(schlagliste.get(i).size() > zugWeg.size()){
					zugWeg = schlagliste.get(i);
				}
			}
		//Sonst wähle einen zufälligen Zug
		}else if(alleLegalenWege.size() > 1){
			r = rand.nextInt(alleLegalenWege.size() - 1);
			zugWeg = alleLegalenWege.get(r);
		}else if(alleLegalenWege.size() == 1){
			zugWeg = alleLegalenWege.get(0);
		}
		
		//Mache den Zug
		if(zugWeg != null){
			bewegung = new Bewegungskoordinate(zugWeg.getFirst(), zugWeg.getLast());
			waehleSteinUndZiehe(bewegung, gegner);
		}
		
		return bewegung.toString();
	}
}
