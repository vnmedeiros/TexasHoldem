package teste;


import junit.framework.TestCase;
import kataTexasHold.Hand;
import kataTexasHold.Hand.HandHank;

import org.junit.Test;

public class HandTest extends TestCase {

	@Test
	public void testGetRank() {
		Hand hand;
		
		hand = new Hand("9c Ah 3s Kd 8d 5c 6d");
		assertEquals(HandHank.HighCard, hand.getRank());
		
		hand = new Hand("9c Ah 4s Kd 9d 3c 6d");
		assertEquals(HandHank.OnePair, hand.getRank());
		
		hand = new Hand("9c Ah Ks Kd 9d 3c 6d");
		assertEquals(HandHank.TwoPair, hand.getRank());
		
		hand = new Hand("9c Ah Ks Kd Kd 3c 6d");
		assertEquals(HandHank.ThreeKind, hand.getRank());
		
		hand = new Hand("2c 4h As 3d 9d 5c Td");
		assertEquals(HandHank.Straight, hand.getRank());
		
		hand = new Hand("9s As 4s 4d 9s 3s 6d");
		assertEquals(HandHank.Flush, hand.getRank());

		hand = new Hand("9c Ah Ks Kd 9d Kc 6d");
		assertEquals(HandHank.FullHouse, hand.getRank());
		
		hand = new Hand("9c Kh Ks Kd 9d Kc 6d");
		assertEquals(HandHank.FourKind, hand.getRank());
		
		hand = new Hand("9c Tc Jc Kc Qc 3c Qd");
		assertEquals(HandHank.StraightFlush, hand.getRank());
		
		hand = new Hand("Tc Ac Kc Kd Qc 3c Jc");
		assertEquals(HandHank.RoyalStraightFlush, hand.getRank());		
	}

}
