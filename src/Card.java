

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
   
   public int toValue()
   {
	   int result = value.ordinal();
	   if(result > 10 && result <= 13)
		   result = 10;
;	   return result;
   }
   
   public String toString()
   {
	   return value.getText() + color.getText();
   }
	 
}
