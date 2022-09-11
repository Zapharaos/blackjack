import java.util.ArrayList;

public class Hand {

	enum Status { WAIT, STAY, HIT, DOUBLE, SPLIT, BUST, DRAW, WON, LOST };
	
	private Status status;
	private int value;
	private boolean ace = false;
	private boolean first = true;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public void addCard(Card card)
	{
		hand.add(card);
		if(card.getValue() == Value.ACE)
		{
			if(ace)
			{
				value += 1;
				return;
			}
			else
			{
				ace = true;
			}
		}
		value += card.toValue();
		
		if(value > 21 && ace)
		{
			ace = false;
			value -= 10;
		}
		
	}
	
	public Card getFirstCard()
	{
		return hand.get(0);
	}
	
	public void toValue()
	{
		value = 0;
		for(Card card : hand)
			value += card.toValue();
	}
	
	public int getValue()
	{
		return value;
	}
	
	public String toString()
	{
		String str = "";
		for(Card card : hand)
			str += " " + card.toString();
		str += " ; ";
		return str;
	}
}
