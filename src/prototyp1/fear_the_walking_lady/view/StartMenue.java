package prototyp1.fear_the_walking_lady.view;

import java.util.Scanner;

public class StartMenue {
	
	public StartMenue(){
		
	}
	
	public int zeichneDich(){
		int eingabe = 0;
		Scanner in = new Scanner(System.in);

		
		//Frage nach einer Eingabe bis Sie gültig ist
		do{
			System.out.println("\n\nStartMenü");
			System.out.println("1: Spielanleitung lesen");
			System.out.println("2: Mensch gegen Mensch");
			System.out.println("3: Mensch gegen Computer");
			System.out.println("4: Beenden");
			
			try{

				eingabe = Integer.parseInt(in.nextLine());
				
				if(eingabe < 1 || eingabe > 4){
					System.out.println("Geben Sie eine Zahl zwischen 1 und 4 ein!\n");
				}
			}
			catch(NumberFormatException ex){
				System.out.println("Geben Sie eine Zahl zwischen 1 und 4 ein!\n");
			}
	
		}while(eingabe < 1 || eingabe > 4);
		
		return eingabe;
	}
}
