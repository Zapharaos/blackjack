package fr.mfreitag.blackjack;

public enum Value {
	
	CUT(1, "🛑"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), ACE(11, "A"), JACK(12, "J"), QUEEN(13, "Q"), KING(14, "K");
	
	private int value;
	private String text;
	
	private Value (int value, String text) {
		this.value = value;
		this.text = text;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getText()
	{
		return text;
	}
	
}
