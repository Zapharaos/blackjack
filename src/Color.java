

public enum Color {

	CUT(-1), CLUB(1), DIAMOND(2), HEART(3), SPADE(4);
	
	private int color;
	
	private Color (int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
}
