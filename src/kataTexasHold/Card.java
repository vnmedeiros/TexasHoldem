package kataTexasHold;

public class Card implements Comparable<Card>{
	
	private char face;
	private char suit;
	
	public Card(char face, char suit){
		this.face = face;
		this.suit = suit;
	}

	public char getFace() {
		return face;
	}

	public char getSuit() {
		return suit;
	}
	
	public int minus(Card card){
		return compareTo(card);
	}
	
	@Override
	public int compareTo(Card o) {
		int a = replace(face);
		int b = replace(o.face);
		return a - b;
	}
	
	@Override
	public String toString(){
		return " " + face + suit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;		
		Card other = (Card) obj;
		if (face != other.face)
			return false;		
		return true;
	}
	
	private int replace(char face){
		switch (face) {
		case 65: //A
			return 14;
		case 75: //K
			return 13;
		case 81: //Q
			return 12;
		case 74: //J
			return 11;
		case 84: //T
			return 10;
		default:
			return face % 48;
		}
	}	
}
