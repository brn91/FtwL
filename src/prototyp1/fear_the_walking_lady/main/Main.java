package prototyp1.fear_the_walking_lady.main;

import prototyp1.fear_the_walking_lady.controller.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		GameController gc = new GameController();
		Scanner in = new Scanner(System.in);
		int eingabe;

		
		System.out.println("1: Test- /Cheatmodus");
		System.out.println("2: Normales Spiel");
		
		try{
			eingabe = Integer.parseInt(in.nextLine());
			
			if(eingabe == 1){
				GameController.activateCheats = true;
			}
			
			gc.runStartMenue();			
		}
		catch(NumberFormatException ex){
			System.out.println("Geben Sie eine Zahl ein! \n");
		}
		
		in.close();
	}
}
