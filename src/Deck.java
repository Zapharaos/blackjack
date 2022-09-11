

import java.util.ArrayList; // import the ArrayList class
import java.util.Collections;
import java.util.Random;

public class Deck {

	private static int cards = 52;
	private static float min_treshold = (float) 0.25;
	private static float max_treshold = (float) 0.75;
	
	private int nb_cards = cards;
	private int nb_decks = 1;
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	public Deck() {}
	
	public Deck(int nb_decks) {
		this.nb_decks = nb_decks;
		setup();		
	}
	
	public void setup() {
		nb_cards = cards * nb_decks;
		
		for(int i = 0; i < nb_decks; i++)
		{
			for(Color color : Color.values())
			{
				if(color == Color.CUT) continue; // cutcard not used in the default deck
				for(Value value : Value.values())
				{
					if(value == Value.CUT) continue; // cutcard not used in the default deck
					deck.add(new Card(color, value));
				}	
			}
		}	
	}
	
	public void shuffle() {
		setup();
		Collections.shuffle(deck);
		Random random = new Random();
		int min = (int) Math.round(nb_cards*min_treshold);
		int max = (int) Math.round(nb_cards*max_treshold);
		int cutcardindex = random.nextInt(max + 1 - min) + min;
		deck.add(cutcardindex, new Card(Color.CUT, Value.CUT));
		nb_cards++;
	}
	
	public Card draw(boolean cutcard) {
		Random random = new Random();
		int min = 0;
		int max = nb_cards - 2;
		int cardindex = random.nextInt(max + 1 - min) + min;
		Card card = deck.get(cardindex);
		while(card.getColor() == Color.CUT && card.getValue() == Value.CUT)
		{
			cardindex = random.nextInt(max + 1 - min) + min;
			card = deck.get(cardindex);
		}
		deck.remove(cardindex);
		return card;
	}
	
	public void debug()
	{
		System.out.println("Using For Loop\n ");
	      for (int i = 0; i < deck.size();i++) 
	      { 		      
	          System.out.println(deck.get(i).toString()); 		
	      }   
	}
	
}
