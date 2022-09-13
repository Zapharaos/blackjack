
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
			hand.toValue();
			Hand split = new Hand(hands.size()+1);
			split.setBet(hand.getBet());
			split.addCard(hand.getHand().get(1));
			
			if(hand.getHand().get(0).getValue() == Value.ACE)
			{
				hand.setStatus(Hand.Status.STOP);
				split.setStatus(Hand.Status.STOP);
			}
		
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
	
	public void addPoints(double points)
	{
		this.points += points;
	}
	
	public void resetHand()
	{
		hands.removeAll(hands);
		hands = new ArrayList<Hand>();
		System.out.println(hands.size());
	}
	
	public void Bj(int id)
	{
		hands.get(id-1).Bj();
		this.points += 2.5*hands.get(id-1).getBet();
		System.out.println("Player " + this.id + ", Hand " + id + " won " + hands.get(id-1).getBet()*2.5 + "💰 and has a total of " + this.points + "💰");
	}
	
	public void Won(int id)
	{
		this.points += 2*hands.get(id-1).getBet();
		System.out.println("Player " + this.id + ", Hand " + id + " won " + hands.get(id-1).getBet()*2 + "💰 and has a total of " + this.points + "💰");
	}
	
	public void Draw(int id)
	{
		System.out.println("Player " + this.id + ", Hand " + id + " draw " + hands.get(id-1).getBet() + " and has a total of " + this.points + "💰");
	}
	
	public void Lost(int id)
	{
		System.out.println("Player " + this.id + ", Hand " + id + " lost " + hands.get(id-1).getBet() + "💰 and has a total of " + this.points + "💰");
	}
}
