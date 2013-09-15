package kataTexasHold;

import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Comparable<Hand> {

	public enum HandHank {
		HighCard, OnePair, TwoPair, ThreeKind, Straight, Flush, FullHouse, FourKind, StraightFlush, RoyalStraightFlush
	}

	private ArrayList<Card> cards;
	private int cCard[];
	private int round;

	private HandHank rank;
	private int iHCPrimary;
	private int iHCSecondary;
	private int kicker;

	public Hand(String hand) {
		hand = hand.trim();		
		String[] vectoCards = hand.split("\\s");		
		cards = new ArrayList<Card>();
		for (String c : vectoCards) {
			Card card = new Card(c.charAt(0), c.charAt(1));
			cards.add(card);
		}
		round = cards.size();
		
		if (round != 7)
			return;
		
		cCard = new int[7];
		Collections.sort(cards);
		countCards();
		generateRanks();
	}

	private void generateRanks() {
		if (isStraightFlush()){
			this.rank = HandHank.StraightFlush;
			if(cards.get(iHCPrimary).getFace() == 'A' )
				this.rank = HandHank.RoyalStraightFlush;
		}
		else if (isFourKind())
			this.rank = HandHank.FourKind;
		else if (isFullHouse())
			this.rank = HandHank.FullHouse;
		else if (isFlush())
			this.rank = HandHank.Flush;
		else if (isStraight())
			this.rank = HandHank.Straight;
		else if (isThreeKind())
			this.rank = HandHank.ThreeKind;
		else if (isTwoPair())
			this.rank = HandHank.TwoPair;
		else if (isOnePair())
			this.rank = HandHank.OnePair;
		else
			this.rank = HandHank.HighCard;

	}

	private void countCards() {
		cCard[0] = 1;
		int p = 0;
		for (int i = 0; i < 6; i++) {
			if (cards.get(i).equals(cards.get(i + 1)))
				cCard[p]++;
			else {
				p++;
				cCard[p]++;
			}
		}
	}

	@Override
	public int compareTo(Hand o) {
		if (rank == null && o.rank == null)
			return 0;
		else if (rank != null && o.rank == null)
			return 1;
		else if (rank == null && o.rank != null)
			return -1;
		else if (rank.ordinal() > o.rank.ordinal())
			return 1;
		else if (rank.ordinal() < o.rank.ordinal())
			return -1;

		switch (rank) {
			case TwoPair:
				if (cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary)) == 0) {
					if (cards.get(iHCSecondary).minus(cards.get(o.iHCSecondary)) == 0)
						return cards.get(kicker).minus(cards.get(o.kicker));
					else
						return cards.get(iHCSecondary).minus(cards.get(o.iHCSecondary));
				} else
					return cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary));
				
	
			case Straight:
			case Flush:
			case FourKind:
			case StraightFlush:
				return cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary));
	
			case OnePair:
			case ThreeKind:
				if (cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary)) == 0)
					return cards.get(kicker).minus(cards.get(o.kicker));
				else 
					return cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary));
				
	
			case FullHouse:
				if (cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary)) == 0)
					return cards.get(iHCSecondary).minus(cards.get(o.iHCSecondary));
				else 
					return cards.get(iHCPrimary).minus(cards.get(o.iHCPrimary));
			default:
				return cards.get(kicker).minus(cards.get(o.kicker));				
		}
	}

	private boolean isOnePair() {
		for (int i = 0; i < 7 && cCard[i] != 0; i++) {
			if (cCard[i] == 2) {
				// kicker:
				kicker = (iHCPrimary == 0) ? 2 : 0;
				for (int j = 0; j < 7; j++) {
					if (j < iHCPrimary || j >= iHCPrimary + 2) {
						if (cards.get(kicker).compareTo(cards.get(j)) < 0)
							kicker = j;
					}
				}
				return true;
			}
			iHCPrimary += cCard[i];
		}
		iHCPrimary = 0;
		return false;

	}

	private boolean isTwoPair() {
		int i = 0;

		for (; i < 7 && cCard[i] != 0; i++) {
			if (cCard[i] == 2) {
				i++;
				break;
			}
			iHCPrimary += cCard[i];
		}

		for (; i < 7 && cCard[i] != 0; i++) {
			if (cCard[i] == 2) {
				// kicker:
				kicker = (iHCPrimary == 0) ? 2 : 0;
				for (int j = 0; j < 7; j++) {
					if (j < iHCPrimary || j >= iHCPrimary + 2
							&& j < iHCSecondary || j >= iHCSecondary + 2) {
						if (cards.get(kicker).compareTo(cards.get(j)) < 0)
							kicker = j;
					}
				}
				// high card:
				if (cards.get(iHCPrimary).compareTo(cards.get(iHCSecondary)) < 0) {
					int aux = iHCPrimary;
					iHCPrimary = iHCSecondary;
					iHCSecondary = aux;
				}
				return true;
			}
			iHCSecondary += cCard[i];
		}
		iHCPrimary = 0;
		iHCSecondary = 0;
		return false;
	}

	private boolean isThreeKind() {
		for (int i = 0; i < 7 && cCard[i] != 0; i++) {			
			if (cCard[i] == 3) {
				// kicker:				
				kicker = (iHCPrimary == 0) ? 3 : 0;
				for (int j = 0; j < 7; j++) {
					if (j < iHCPrimary || j >= iHCPrimary + 3) {
						if (cards.get(kicker).compareTo(cards.get(j)) < 0)
							kicker = j;
					}
				}
				return true;
			}
			iHCPrimary += cCard[i];
		}
		iHCPrimary = 0;
		return false;
	}

	private boolean isStraight() {
		for (int i = 6; i > 3; i--) {
			int count = 1;
			int j = i-1;
			while (count < 4 && j >= 0) {				
				if (cards.get(i).minus(cards.get(j)) == count)
					count++;
				j--;
			}
			if (count == 4) {
				iHCPrimary = i;
				return true;
			}
		}
		//A-2-3-4-5
		if(cards.get(0).getFace() == '2' && cards.get(6).getFace() == 'A' ){
			int a = 1;
			for(int i = 1; i<6; i++) {				
				if(cards.get(i).minus(cards.get(0)) == a) {
					a++;
				}				
				if(a==4){
					iHCPrimary = i;
					return true;
				}
			}			
		}
		return false;
	}

	private boolean isFlush() {
		for (int i = 6; i > 3; i--) {
			int count = 0;
			for (int j = 6; j >= 0 && count < 3; j--) {
				if (cards.get(i).getSuit() != cards.get(j).getSuit())
					count++;
			}
			if (count < 3) {
				iHCPrimary = i;				
				return true;
			}
		}
		return false;
	}

	private boolean isFullHouse() {
		boolean pair = isOnePair();
		iHCSecondary = iHCPrimary;
		iHCPrimary = 0;
		return pair && isThreeKind();
	}

	private boolean isFourKind() {
		for (int i = 0; i < 7 && cCard[i] != 0; i++) {
			if (cCard[i] == 4)
				return true;
			iHCPrimary += cCard[i];
		}
		iHCPrimary = 0;
		return false;
	}

	private boolean isStraightFlush() {
		return isStraight() && isFlush();
	}
	
	@Override
	public String toString(){
		String hand = "";
		for(Card c : this.cards){
			hand += " " + c;
		}
		return hand;
	}

	public HandHank getRank() {
		return rank;
	}
}
