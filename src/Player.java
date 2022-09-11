
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
		hands.add(new Hand());
	}
	
	public ArrayList<Hand> getHands()
	{
		return hands;
	}
}
