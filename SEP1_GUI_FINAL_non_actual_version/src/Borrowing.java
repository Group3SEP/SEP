import java.io.Serializable;

public class Borrowing implements Serializable 
{

   private Item borrowedItem;
   private String details;
   private int index;
   
   public Borrowing(String details, Item item, int index)
   {
      this.details = details;
      borrowedItem = item;
      item.setBorrowedFrom(index);
      item.setAvailability(false, index);
      this.index = index;
   }
   
   public String toString()
   {
      return "Borrowing " + details + " from " + this.borrowedItem.getBorrowedFrom(index) + " " + this.borrowedItem.getTitle() + " copy n." + (this.index+1);
   }
   
   public Item getBorrowedItem()
   {
      return borrowedItem;
   }
   public int getIndex()
   {
      return index;
   }
}
