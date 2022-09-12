import java.util.ArrayList;

public class Hand {

	enum Status { WAIT, STOP, DRAW, WON, LOST };
	
	private Status status = Status.WAIT;
	private int value;
	private boolean ace = false;
	private boolean first = true;
	private int id;
	private float bet = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hand() {}
	
	public Hand(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public float getBet()
	{
		return bet;
	}
	
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
		
		if(value > 21)
		{
			System.out.println("BUSTED");
			Lost();
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
	
	public Status getStatus()
	{
		return status;
	}
	
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public void Won()
	{
		status = Status.WON;
	}
	
	public void Lost()
	{
		status = Status.LOST;
	}
	
	public void Draw()
	{
		status = Status.DRAW;
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
