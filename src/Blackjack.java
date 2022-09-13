
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
		
		Scanner sc= new Scanner(System.in); 
		for(Player player : players)
		{
			System.out.println("Player " + player.getId() + " please choose a number of hands");
			int nb_hands = sc.nextInt();
			player.addHand(nb_hands);
		}
		
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
				
				for(Hand hand : new ArrayList<Hand>(player.getHands()))
				{
					if(hand.getStatus() != Hand.Status.WAIT)
						continue;
					play = true;
					System.out.println("\nPlayer " + player.getId() + ", Hand " + hand.getId() + " => Bet 💰 " + hand.getBet() + " ; Value " + hand.getValue() + " : " + hand.toString());
					 
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
						player.splitHand(hand.getId());
						break;
					}
					
					System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " now has a value of " + hand.getValue() + " : " + hand.toString());
				}
			}
		}
		
		System.out.println( "\nDealer has " + getDealer().getValue() + " : " + getDealer().toString());
		
		while(getDealer().getValue() < 16)
		{
			getDealer().addCard(deck.draw(true));
			System.out.println( "Dealer now has " + getDealer().getValue() + " : " + getDealer().toString());
		}
		
		System.out.println("Calculating the results...");
		
		for(Player player : players)
		{
			for(Hand hand : player.getHands())
			{
				
				System.out.println("\nPlayer " + player.getId() + ", Hand " + hand.getId() + " => Bet 💰 " + hand.getBet() + " ; Value " + hand.getValue() + " : " + hand.toString());
				System.out.println( "\nvs Dealer who has " + getDealer().getValue() + " : " + getDealer().toString());
				
				if(hand.getStatus() == Hand.Status.STOP)
				{
					if(hand.getValue() > getDealer().getValue() || getDealer().getValue() > 21)
						hand.Won();
					else if (hand.getValue() == getDealer().getValue())
						hand.Draw();
					else
						hand.Lost();
				}
				
				if(hand.getStatus() == Hand.Status.LOST)
				{
					System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => LOST");
				}
				else if(hand.getStatus() == Hand.Status.DRAW)
				{
					System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => DRAW");
				}
				else
				{
					System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => WON");
				}
			}
		}
		
		System.out.println("\nEND OF ROUND");
		
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
		
		// TODO : bet per hand + new round + blackjack + fix index issue when drawcard + draw no deck
	}
	
}
