import java.io.Serializable;

public class Borrowing implements Serializable 
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private Item borrowedItem;
   private String details;
   private int index;
   private String returnDate;
   
   public Borrowing(String details, Item item, int index, String returnDate)
   {
      this.details = details;
      borrowedItem = item;
      item.setBorrowedFrom(index);
      item.setAvailability(false, index);
      this.index = index;
      this.returnDate = returnDate;
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
   
   public String returnDate()
   {
      return this.returnDate + " - " + borrowedItem.getTitle();
   }
   

}
