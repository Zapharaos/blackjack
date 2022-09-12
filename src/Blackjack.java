
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner; // import the Scanner class

public class Blackjack {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hand dealer = new Hand();
	private Deck deck;
	
	public Blackjack () {}
	
	public Blackjack( int nb_decks, int nb_players)
	{
		System.out.println("Game is starting with " + nb_players + " player(s) and " + nb_decks + " deck(s) !\n");
		for(int i=0; i < nb_players; i++)
			players.add(new Player(i+1, 100));
	}
	
	public void start()
	{
		setup();
		round();		
	}
	
	public void setup()
	{
		deck = new Deck();
		deck.shuffle();
		
		for(Player player : players)
			player.addHand();
		
		for(int i=0; i<2; i++)
		{
			for(Player player : players)
				for(Hand hand : player.getHands())
					hand.addCard(deck.draw(false));
			dealer.addCard(deck.draw(false));
		}
		
		display(true);
	}
	
	public void round()
	{
		boolean play = true, first = true;
		Scanner sc = new Scanner(System.in); 
		ArrayList<Hand> hands = new ArrayList<Hand>();
		
		while(play || first)
		{
			if(first)
				first = false;
			play = false;
			
			for(Player player : players)
			{
				hands = player.getHands();
				
				for(Hand hand : hands)
				{
					if(hand.getStatus() != Hand.Status.WAIT)
						continue;
					play = true;
					System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => Bet 💰 " + hand.getBet() + " ; Value " + hand.getValue() + " : " + hand.toString());
					 
					System.out.println("What would you like to do ?\n");
					String choice = sc.nextLine();
					
					Card card = new Card();
					
					switch(choice)
					{
					case "DOUBLE":
						card = deck.draw(false);
						hand.addCard(card);
					case "STAY":
						hand.setStatus(Hand.Status.STOP);
						break;
					case "HIT":
						card = deck.draw(false);
						hand.addCard(card);
						break;
					case "SPLIT":
						// TO DO
						break;
					}
					
					System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " now has a value of " + hand.getValue() + " : " + hand.toString());
				}
			}
		}
		
		sc.close();
		if(deck.check())
			deck.shuffle();
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
		
		System.out.println("\n");
	}
	
	public Hand getDealer()
	{
		return dealer;
	}
	
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);  
		System.out.print("Enter the number of players : ");
		//int nbp = sc.nextInt();
		System.out.print("Enter the number of decks : ");
		//int nbd = sc.nextInt();
		
		int nbp = 1, nbd = 1;
		
		Blackjack bj = new Blackjack(nbd, nbp);
		bj.start();
		
		// TODO : bet per hand + split + multiple hands + multiple players + new round + blackjack + fix index issue when drawcard
		
		/*
		 * if(hand.getValue() > getDealer().getValue())
						hand.Won();
					else if (hand.getValue() == getDealer().getValue())
						hand.Draw();
					else
						hand.Lost();
		 */
	}
	
}
