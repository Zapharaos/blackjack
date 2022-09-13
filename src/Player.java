
import java.util.ArrayList; // import the ArrayList class

public class Player {

	private int id;
	private double points;
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	
	public Player() {}
	
	public Player(int id, double points)
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
	
	public double getPoints()
	{
		return points;
	}
	
	public void Won(int id)
	{
		this.points += hands.get(id-1).getBet();
		System.out.println("Player " + this.id + ", Hand " + id + " won " + hands.get(id-1).getBet() + "💰 and has a total of " + this.points + "💰");
	}
	
	public void Draw(int id)
	{
		System.out.println("Player " + this.id + ", Hand " + id + " draw " + hands.get(id-1).getBet() + " and has a total of " + this.points + "💰");
	}
	
	public void Lost(int id)
	{
		this.points -= hands.get(id-1).getBet();
		System.out.println("Player " + this.id + ", Hand " + id + " lost " + hands.get(id-1).getBet() + "💰 and has a total of " + this.points + "💰");
	}
}
