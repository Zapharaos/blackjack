
import java.util.ArrayList; // import the ArrayList class

public class Player {

	private int id;
	private int points;
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	
	public Player() {}
	
	public Player(int id, int points)
	{
		this.id = id;
		this.points = points;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void addHand()
	{
		hands.add(new Hand(hands.size() + 1));
	}
	
	public void addHand(int nb_hands)
	{
		for(int i=0; i<nb_hands; i++)
			addHand();
	}
	
	public void splitHand(int id)
	{
		Hand hand = hands.get(id-1);
		if(hand.getHand().size() == 2 && hand.getHand().get(0).toValue() == hand.getHand().get(1).toValue())
		{
			Hand split = new Hand(hands.size()+1);
			split.addCard(hand.getHand().get(1));
			hands.add(split);;
			hand.split();
		}
	}
	
	public ArrayList<Hand> getHands()
	{
		return hands;
	}
}
