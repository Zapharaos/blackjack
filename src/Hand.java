import java.util.ArrayList;

public class Hand {

	enum Status { WAIT, STOP, HIT, DOUBLE, SPLIT, BUST, WON, LOST };
	
	private Status status;
	private int value;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public void addCard(Card card)
	{
		hand.add(card);
	}
	
	public String toString()
	{
		String str = "";
		for(Card card : hand)
		{
			str += " " + card.toString() + " ; ";
		}
		return str;
	}
}
