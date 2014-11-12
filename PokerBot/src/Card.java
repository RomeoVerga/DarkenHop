public class Card {
	public static final int count = 52;
	private static Card[] values = new Card[count];
	public final Rank rank;
	public final Suit suit;
	int ordinal; //index of card in value array
	final String toString;
	final String name;

	private Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.ordinal = ordinal(rank, suit);
		this.name = rank.name() + "Of" + suit.name(); //name() from enum class returns the literal String of varaible
		this.toString = rank.toString() + suit.toString();
	}

	static {
		for (Rank rank : Rank.values()) { //rank will become every value of the array from Rank.values()
			for (Suit suit : Suit.values()) {  //Suit emum contain values() not in Java API
				Card c = new Card(rank, suit);
				int ordinal = c.ordinal; 
				values[ordinal] = c;
			}
		}
	}

	private static int ordinal(Rank rank, Suit suit) {
		return rank.ordinal() + suit.ordinal() * 13;
	}

	public int ordinal() {
		return ordinal;
	}

	public String toString() {	/* As */
		return toString;
	}

	public String name() { /* AceOfSpades */
		return name;
	}

	public static Card get(Rank rank, Suit suit) {
		return Card.values()[ordinal(rank, suit)];
	}

	public static Card get(int ord) {
		return values[ord];
	}

	public static Card[] values() {
		return values;
	} 

	public static String toString(Card[] hand) {
		if(hand == null) return "NULL";
		if(hand.length == 0)return "NULL";
		StringBuffer b = new StringBuffer();
		for (Card card : hand) {
			b.append(card.toString);
		}
		return b.toString();
	}

	public static Card parse(String s) {
		Rank rank = Rank.parse(s.substring(0, 1));
		Suit suit = Suit.parse(s.substring(1, 2));

		return Card.get(rank, suit);
	}

	public static Card[] parseArray(String s) {
		int noCards = s.length()/2;
		Card[] result = new Card[noCards];
		for (int i = 0; i < noCards; i++) {
			result[i] = Card.parse( s.substring(2*i, 2*i+2));
		}
		return result;
	}
}