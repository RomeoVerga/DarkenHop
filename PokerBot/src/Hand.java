import java.util.BitSet;
import java.util.Random;

public class Hand {
	private static final int NO_CARDS = 52;
	private static final Random random = new Random();
	private BitSet bitSet;

	/****************************************Creates Hand with no Cards**************************************/
	public Hand() {
		bitSet = new BitSet(NO_CARDS);
	}

	/****************************************Creates Hand from Card Array************************************/
	public Hand(Card[] cards) {
		bitSet = new BitSet(NO_CARDS);
		for (Card card : cards) {
			bitSet.set(card.ordinal);
		}
	}

	/************************************Resets hand with new hand by ordinals*******************************/
	public void reset(int[] cards) {
		//I'm not sure why there is not a reset method by Card[] like the Hand Constructor
		bitSet = new BitSet(NO_CARDS);
		for (int i = 0; i < cards.length; i++) {
			bitSet.set(cards[i]);
		}
	}

	/****************************************Add Card to Hand by Ordinal*************************************/
	public void addCard(int card) { 
		bitSet.set(card); 
	}

	/*********************************PLUS METHODS ADDS 1 HAND TO ANOTHER************************************/
	public Hand plus(Hand hand){
		//I don't know why you would ever add the cards of a hand to another hand
		this.bitSet.or(hand.bitSet);
		return this;
	}

	public Hand plus(Card[] cards) {
		for (Card card : cards) {
			this.bitSet.set(card.ordinal);
		}
		return this;
	}
	
	public Hand plus(Card card) {
		this.bitSet.set(card.ordinal);
		return this;
	}

	public Hand plus(Pair p) {
		Card[] cards = p.getCards();
		this.plus(cards);
		return this;
	}
	
	/*****************************MINUS METHODS SUBTRACTS 1 HAND FROM ANOTHER**********************************/
	public Hand minus(Hand hand) {
		this.bitSet.andNot(hand.bitSet);
		return this;
	}

	public Hand minus(Card[] cards) {
		for (Card card : cards) {
			this.bitSet.clear(card.ordinal);
		}
		return this;
	}
	
	public Hand minus(Card card) {
		this.bitSet.clear(card.ordinal);
		return this;
	}
	
	public Hand minus(Pair p) {
		Card[] cards = p.getCards();
		this.minus(cards);
		return this;
	}

	/*****************************************INTERSECTS METHODS*********************************************/
	public boolean intersects(Hand hand) {
		//Returns true if the specified BitSet has any bits set to true that are also set to true in this BitSet.
		return bitSet.intersects(hand.bitSet);
	}
	
	public boolean intersects(Pair p) {
		Card[] cards = p.getCards();
		if(this.contains(cards[0])) return true;
		if(this.contains(cards[1])) return true;
		return false;
	}
	
	/*****************************************Transforms bitset into card[]****************************/
	public Card[] toCards() {
		int noCards = noCards();
		Card[] result = new Card[noCards];

		int j = -1;
		for (int i = 0; i < result.length; i++) {
			j = bitSet.nextSetBit(j+1);
			result[i] = Card.values()[j];
		}

		return result;
	}

	/****************************************Number of Cards**************************************/
	public int noCards() {
		return bitSet.cardinality();//returns the number of bits set to true
	}

	/**************************************Sets random bit from true to false****************************/
	public Card removeRandom() {
		/* Difference between do-while loops and while loops is that do while will execute at least once!
		*  and will check the condition after as opposed to before the loop.
		*  NOTE: Why would you ever remove a random card from a hand???!
		*/
		int cardNo;
		do {
			cardNo = random.nextInt(52);
		} while (!this.bitSet.get(cardNo));		

		this.bitSet.clear(cardNo);
		return Card.values()[cardNo];
	}
	
	/***************************************Takes a Hand in String form and makes hand Object***************/
	public static Hand parse(String s) {
		Hand result = new Hand();
		int noCards = s.length()/2;
		for (int i = 0; i < noCards; i++) {
			result.plus(Card.parse( s.substring(2*i, 2*i+2)));
		}
		return result;
	}

	/****************************************Contains Card***********************************/
	private boolean contains(Card card) {
		return this.bitSet.get(card.ordinal);
	}
	
	/****************************************Clear Hand**************************************/
	public Hand clear() {
		this.bitSet.clear();
		return this;
	}
	
	/****************************************toString**************************************/
	public String toString() {
		return Card.toString(this.toCards());
	}
}