package kataTexasHold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Hand> hands;
	private int indexWinner;
	
	public Game(){
		this.hands = new ArrayList<Hand>();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.in();
		game.out();
	}
	
	public void in(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
		 
		String str;
		try {
			str = in.readLine();
			while(str.length() != 0){
				Hand hand = new Hand(str); 
				hands.add(hand);
				if(hands.get(indexWinner).compareTo(hand) < 0){
					indexWinner = hands.size() -1;
				}
				str = in.readLine();
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public void out(){
		for(int i =0 ; i< hands.size(); i++) {
			System.out.print(hands.get(i));
			if(hands.get(i).getRank()!=null)
				System.out.print(" " + hands.get(i).getRank());
			if(i==indexWinner)
				System.out.print(" (winner) ");
			System.out.println();
		}		
	}
}
