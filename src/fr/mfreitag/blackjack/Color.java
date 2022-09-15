package fr.mfreitag.blackjack;

public enum Color {

	CUT(-1, "🛑"), CLUB(1, "♣️"), DIAMOND(2, "♦️"), HEART(3, "♥️"), SPADE(4, "♠️");
	
	private int color;
	private String text;
	
	private Color (int color, String text) {
		this.color = color;
		this.text = text;
	}
	
	public int getColor() {
		return color;
	}
	
	public String getText() {
		return text;
	}
	
}
