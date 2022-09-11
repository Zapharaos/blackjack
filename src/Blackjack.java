
import java.util.ArrayList; // import the ArrayList class

public class Blackjack {
	
	private static int max_players = 8;
	private int nb_players = 1;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hand dealer = new Hand();
	private Deck deck;
	
	public Blackjack () {}
	
	public Blackjack( int nb_decks, int nb_players) {
		this.nb_players = nb_players;
		for(int i=0; i < nb_players; i++)
			players.add(new Player(i, 100));
	}
	
	public void start()
	{
		deck = new Deck();
		deck.shuffle();
		
		for(Player player : players)
			player.addHand();
		
		for(int i=0; i<2; i++)
			deal();	
	}

	public void deal()
	{
		for(Player player : players)
			for(Hand hand : player.getHands())
				hand.addCard(deck.draw(false));
		dealer.addCard(deck.draw(false));
	}
	
	public void display(boolean first)
	{
		ArrayList<Hand> hands = new ArrayList<Hand>();
		for(Player player : players)
		{
			hands = player.getHands();
			for(Hand hand : hands)
				System.out.println("Player " + player.getId() + " has " + hand.getValue() + " : " + hand.toString());
		}
		
		if(first)
		{
			Card dealer = getDealer().getFirstCard();
			System.out.println( "Dealer has " + dealer.toValue() + " : " + dealer.toString());
		}
		else
		{
			System.out.println( "Dealer has " + getDealer().getValue() + " : " + getDealer().toString());
		}
	}
	
	public Hand getDealer()
	{
		return dealer;
	}
	
	public void debugPlayerHands()
	{
		for(Player player : players)
		{
			System.out.println( "\n Player has : " + player.getHands().get(0).toString());
		}
	}
	
	public void debugDealerHand(boolean all)
	{
		if(all)
			System.out.println( "\n Dealer has : " + getDealer().toString());
		else
			System.out.println( "\n Dealer has : " + getDealer().getFirstCard().toString());
	}
	
	public static void main(String[] args) {
		
		Blackjack bj = new Blackjack(1, 1);
		bj.start();
		bj.display(true);
		bj.display(false);
	}
	
}
