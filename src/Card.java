

public class Card {
	
	private Color color;
	private Value value;
	
	public Card() {}
	
	public Card (Color color, Value value)
	{
		this.color = color;
		this.value = value;
	}
	
	public Color getColor() {
        return color;
   }

   public void setColor(Color color) {
        this.color = color;
   }
   
   public Value getValue() {
       return value;
  }

   public void setValue(Value value) {
        this.value = value;
   }
   
   public String toString()
   {
	   return color + " " + value;
   }
	 
}
