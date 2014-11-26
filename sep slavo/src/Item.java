import java.util.Date;


public abstract class Item
{
   public abstract void setAvailability(boolean availability ,int index);
   
   public abstract void setBorrowedFrom(int index);
   
   public abstract boolean getAvailability(int index);
   
   public abstract Date getBorrowedFrom(int index);
   
   public abstract String getTitle();
   
   public abstract void addTheItemOfTheSameType();
   
   public abstract void deleteTheItem(int index);
     
   public abstract Item getTheItem(int index);
}
