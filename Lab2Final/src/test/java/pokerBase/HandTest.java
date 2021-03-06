package pokerBase;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DeckException;
import exceptions.HandException;
import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class HandTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private Hand SetHand(ArrayList<Card> setCards, Hand h)
	{
		Object t = null;
		
		try {
			//	Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Hand");
			//	Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			//	Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[]{ArrayList.class});
			//	Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			//	invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);
			
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		h = (Hand)t;
		return h;
		
	}
	/**
	 * This test checks to see if a HandException is throw if the hand only has two cards.
	 * @throws Exception
	 */
	@Test(expected = HandException.class)
	public void TestEvalShortHand() throws Exception {
		
		Hand h = new Hand();
		
		ArrayList<Card> ShortHand = new ArrayList<Card>();
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));

		h = SetHand(ShortHand,h);
		
		//	This statement should throw a HandException
		h = Hand.EvaluateHand(h);	
	}	
			
	@Test
	public void TestFourOfAKindTrue() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}
	
	@Test
	public void TestSecondFourOfAKindTrue() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}
	
	@Test
	public void TestFourOfAKindFalse() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = false;

		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);	
		//if there is no four of a kind none of the below evaluate.
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),0);		
		//	FOAK has one kicker.  Was it a Club?
		//assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), 0);
		//	FOAK has one kicker.  Was it a King?		
		//assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), 0);
	}
	
	@Test
	public void TestFiveOfAKindTrue() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FiveOfAKind = new ArrayList<Card>();
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(FiveOfAKind,h);
		
		boolean bActualIsHandFiveOfAKind = Hand.isHandFiveOfAKind(h, hs);
		boolean bExpectedIsHandFiveOfAKind = true;
		
		//	Did this evaluate to Five of a Kind?
		assertEquals(bActualIsHandFiveOfAKind,bExpectedIsHandFiveOfAKind);		
		//	Was the five of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FiveOAK has zero kickers
	}
	@Test
	public void TestFiveOfAKindFalse() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FiveOfAKind = new ArrayList<Card>();
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(FiveOfAKind,h);
		
		boolean bActualIsHandFiveOfAKind = Hand.isHandFiveOfAKind(h, hs);
		boolean bExpectedIsHandFiveOfAKind = false;
		
		//	Did this evaluate to Five of a Kind?
		assertEquals(bActualIsHandFiveOfAKind,bExpectedIsHandFiveOfAKind);
		//No high card if hand does not work. 0 by default
		assertEquals(hs.getHiHand(),0);
		//	FOAK has zero kickers
	}
	
	@Test
	public void TestRoyalFlushTrue() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpecedIsHandRoyalFlush = true;
		
		//	Did this evaluate to royal flush?
		assertEquals(bActualIsHandRoyalFlush,bExpecedIsHandRoyalFlush);
		//Was the high card an ACE?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());
		
		//	FOAK has zero kickers
	}
	
	@Test
	public void TestRoyalFlushFalse() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpecedIsHandRoyalFlush = false;
		
		//	Did this evaluate to royal flush?
		assertEquals(bActualIsHandRoyalFlush,bExpecedIsHandRoyalFlush);
		//Was the high card an ACE?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());
		
		//	FOAK has zero kickers
	}
	
	@Test
	public void TestRoyalFlushFalseSuit() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpecedIsHandRoyalFlush = false;
		
		//	Did this evaluate to royal flush?
		assertEquals(bActualIsHandRoyalFlush,bExpecedIsHandRoyalFlush);
		//Royal flush does not have a high card (it is always Ace)
		
		//	FOAK has zero kickers
	}
	
	@Test
	public void TestFirstHandFullHouse() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		
		//	Did this evaluate to two Pair?
		assertTrue(bActualIsHandFullHouse);
		//Was the high card an Four?
				assertEquals(hs.getHiHand(),eRank.FOUR.getiRankNbr());
		//no kickers
	}
	
	@Test
	public void TestSecondHandFullHouse() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		
		//	Did this evaluate to two Pair?
		assertTrue(bActualIsHandFullHouse);
		//Was the high card an Four?
				assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());
		//no kickers
	}
	
	@Test
	public void TestFirstThreeOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		
		//	Did this evaluate to two Pair?
		assertTrue(bActualIsHandThreeOfAKind);
		//Was the high card an Four?
				assertEquals(hs.getHiHand(),eRank.FOUR.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.FIVE);
	}
	
	@Test
	public void TestMiddleThreeOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		
		//	Did this evaluate to two Pair?
		assertTrue(bActualIsHandThreeOfAKind);
		//Was the high card an Four?
				assertEquals(hs.getHiHand(),eRank.FOUR.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.FIVE);
	}
	
	@Test
	public void TestLastThreeOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		
		//	Did this evaluate to two Pair?
		assertTrue(bActualIsHandThreeOfAKind);
		//Was the high card an Four?
				assertEquals(hs.getHiHand(),eRank.FOUR.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.TWO);
	}
	@Test
	public void TestFirstTwoPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		
		//	Did this evaluate to two Pair?
		assertTrue(bActualIsHandTwoPair);
		//Was the high card an FIVE?
				assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.ACE);
	}
	@Test
	public void TestMiddleTwoPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		
		//	Did this evaluate to two pair?
		assertTrue(bActualIsHandTwoPair);
		//Was the high card an KING?
				assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.FIVE);
	}
	
	@Test
	public void TestLastTwoPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.KING,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		
		//	Did this evaluate to two pair?
		assertTrue(bActualIsHandTwoPair);
		//Was the high card an KING?
				assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.FOUR);
	}
	
	@Test
	public void TestFirstPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandPair = Hand.isHandPair(h, hs);
		
		//	Did this evaluate to pair?
		assertTrue(bActualIsHandPair);
		//Was the high card an FIVE?
				assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.QUEEN);
	}
	
	@Test
	public void TestSecondPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandPair = Hand.isHandPair(h, hs);
		
		//	Did this evaluate to pair?
		assertTrue(bActualIsHandPair);
		//Was the high card an FIVE?
				assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.FOUR);
	}
	
	@Test
	public void TestThirdPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandPair = Hand.isHandPair(h, hs);
		
		//	Did this evaluate to pair?
		assertTrue(bActualIsHandPair);
		//Was the high card an FIVE?
				assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.THREE);
	}
	@Test
	public void TestFourthPair() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.DIAMONDS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FOUR,0));		
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsHandPair = Hand.isHandPair(h, hs);
		
		//	Did this evaluate to pair?
		assertTrue(bActualIsHandPair);
		//Was the high card an FIVE?
				assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.TWO);
	}
	
	@Test
	public void TestHighCard() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> HighCard = new ArrayList<Card>();
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		HighCard.add(new Card(eSuit.CLUBS,eRank.KING,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(HighCard,h);
		
		boolean bActualIsHandHighCard = Hand.isHandHighCard(h, hs);
		
		//	Did this evaluate to highCard?
		assertTrue(bActualIsHandHighCard);
		//Was the high card an ACE?
				assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());
		//kickers
				assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(),eRank.FIVE);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
