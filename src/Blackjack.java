
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
			players.add(new Player());
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
	
	public void debugDealerHand()
	{
		System.out.println( "\n Dealer has : " + getDealer().toString());
	}
	
	public static void main(String[] args) {
		
		Blackjack bj = new Blackjack(1, 1);
		bj.start();
		bj.debugPlayerHands();
		bj.debugDealerHand();
	}
	
}
