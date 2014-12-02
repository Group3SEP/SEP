import java.util.Date;
import java.io.Serializable;


public abstract class Item implements Serializable 
{


   public abstract void setAvailability(boolean availability ,int index);
   
   public abstract void setBorrowedFrom(int index);
   
   public abstract boolean getAvailability(int index);
   
   public abstract Date getBorrowedFrom(int index);
   
   public abstract String getTitle();
   
   public abstract void addTheItemOfTheSameType();
   
   public abstract void deleteTheItem(int index);
     
   public abstract Item getTheItem(int index);
   
   public abstract int getNumberOfCopies();
   
   public abstract int getIndexOfAvailableCopy();
   
   public abstract String toString();
}
