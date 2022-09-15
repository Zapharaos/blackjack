package fr.mfreitag.blackjack;

import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner; // import the Scanner class

public class Blackjack {

    private ArrayList<Player> players = new ArrayList<Player>();
    private Hand dealer = new Hand();
    private Deck deck;

    public Blackjack() {
    }

    public Blackjack(int nb_decks, int nb_players) {
        System.out.println("Game is starting with " + nb_players + " player(s) and " + nb_decks + " deck(s) !\n");
        for (int i = 0; i < nb_players; i++)
            players.add(new Player(i + 1, 100));
    }

    public void start() {
        deck = new Deck();
        deck.shuffle();

        while (true) {
            setup();
            round();
        }
    }

    public void setup() {
        Scanner sc = new Scanner(System.in);
        for (Player player : players) {
            // number of hands per player
            System.out.println("Player " + player.getId() + " please choose a number of hands : ");
            int nb_hands = sc.nextInt();
            player.addHand(nb_hands);

            for (Hand hand : new ArrayList<Hand>(player.getHands())) {
                // points bet per hand
                if (player.getPoints() == 0)
                    player.getHands().remove(hand.getId());
                System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => Please choose an amount to bet. You currently have " + player.getPoints() + "💰.");
                double points = sc.nextDouble();
                player.addPoints(points * -1);
                hand.setBet(points);
            }
        }

        // init the dealer's hand and player's hands
        for (Player player : players)
            for (Hand hand : player.getHands())
                hand.addCard(deck.draw(false));
        dealer.addCard(deck.draw(false));

        for (Player player : players)
            for (Hand hand : player.getHands()) {
                hand.addCard(deck.draw(false));

                if (hand.getValue() == 21 && this.dealer.getValue() != 21) {
                    player.Bj(hand.getId());
                    System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => Amazing Blackjack !");
                }
            }
        dealer.addCard(deck.draw(false));

        System.out.println("\nDealer has " + dealer.getFirstCard().toValue() + " : " + dealer.getFirstCard().toString());

        // check if dealer might have a blackjack
        if (dealer.getFirstCard().getValue() == Value.ACE) {
            for (Player player : players)
                for (Hand hand : player.getHands()) {
                    System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " => Value " + hand.getValue() + " : " + hand.toString());

                    // player have enough money to buy the insurance
                    if (player.getPoints() < hand.getBet() * 0.5) {
                        System.out.println("You do not have enough points to take an insurance. The game will proceed normally.");
                        continue;
                    }

                    System.out.println("You have " + player.getPoints() + "💰 left. Would you like to take an insurance for " + hand.getBet() * 0.5 + "💰 ?");
                    String insurance = sc.nextLine();

                    if (insurance == "YES") {
                        player.addPoints(hand.getBet() * -0.5);
                        if (dealer.getValue() == 21)
                            hand.setStatus(Hand.Status.DRAW);
                    }
                }
        }
    }

    public void round() {
        boolean play = true, first = true;
        Scanner sc = new Scanner(System.in);

        while (play || first) {
            if (first)
                first = false;
            play = false;

            // player decides what to do for each one of his hands
            for (Player player : players) {

                for (Hand hand : new ArrayList<Hand>(player.getHands())) {
                    if (hand.getStatus() != Hand.Status.WAIT)
                        continue;
                    play = true;
                    System.out.println("\nPlayer " + player.getId() + ", Hand " + hand.getId() + " => Bet 💰 " + hand.getBet() + " ; Value " + hand.getValue() + " : " + hand.toString());

                    System.out.println("What would you like to do ?\n");
                    String choice = sc.nextLine();

                    Card card = new Card();

                    switch (choice) {
                        case "HIT":
                            card = deck.draw(false);
                            hand.addCard(card);
                            break;
                        case "SPLIT":
                            player.splitHand(hand.getId());
                            player.addPoints(hand.getBet() * -1);
                            hand.addCard(deck.draw(false));
                            System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " now has a value of " + hand.getValue() + " : " + hand.toString());

                            ArrayList<Hand> hands = player.getHands();
                            hands.get(hands.size() - 1).addCard(deck.draw(false));
                            System.out.println("Player " + player.getId() + ", Hand " + hands.get(hands.size() - 1).getId() + " now has a value of " + hands.get(hands.size() - 1).getValue() + " : " + hands.get(hands.size() - 1).toString());
                            continue;
                        case "DOUBLE":
                            card = deck.draw(false);
                            hand.addCard(card);
                        case "STAY":
                        default:
                            hand.setStatus(Hand.Status.STOP);
                            break;
                    }

                    System.out.println("Player " + player.getId() + ", Hand " + hand.getId() + " now has a value of " + hand.getValue() + " : " + hand.toString());
                }
            }
        }

        System.out.println("\nDealer has " + this.dealer.getValue() + " : " + this.dealer.toString());

        // dealer hits until the value reachs at least 15
        while (this.dealer.getValue() < 16) {
            this.dealer.addCard(deck.draw(true));
            System.out.println("Dealer now has " + this.dealer.getValue() + " : " + this.dealer.toString());
        }

        System.out.println("Calculating the results...");

        for (Player player : players) {
            for (Hand hand : player.getHands()) {

                System.out.println("\nPlayer " + player.getId() + ", Hand " + hand.getId() + " => Value " + hand.getValue() + " : " + hand.toString());

                // choose winner
                if (hand.getStatus() == Hand.Status.STOP) {
                    if (hand.getValue() > this.dealer.getValue() || this.dealer.getValue() > 21)
                        hand.Won();
                    else if (hand.getValue() == this.dealer.getValue())
                        hand.Draw();
                    else
                        hand.Lost();
                }

                // results
                if (hand.getStatus() == Hand.Status.LOST)
                    player.Lost(hand.getId());
                else if (hand.getStatus() == Hand.Status.DRAW)
                    player.Draw(hand.getId());
                else if (hand.getStatus() == Hand.Status.WON)
                    player.Won(hand.getId());
            }
        }

        System.out.println("\nEND OF ROUND");

        // reset player's hands and dealer's hand
        for (Player player : players)
            player.resetHand();
        this.dealer = new Hand();

        // shuffle if the cutcard was hit
        if (deck.check())
            deck.shuffle();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of players : ");
        int nbp = sc.nextInt();
        System.out.print("Enter the number of decks : ");
        int nbd = sc.nextInt();

        Blackjack bj = new Blackjack(nbd, nbp);
        bj.start();
        sc.close();

        // TODO :
        // BONUS : stats + sidebets

        // DONE :
    }

}
