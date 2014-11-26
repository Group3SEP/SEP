
public class Borrowing
{
   private Item reservedItem;
   private String name;
   private int index;
   
   public Borrowing(String name, Item item, int index)
   {
      this.name = name;
      reservedItem = item;
      item.setBorrowedFrom(index);
      item.setAvailability(false, index);
      this.index = index;
   }
   
   public String toString()
   {
      return name + " " + this.reservedItem.getBorrowedFrom(index);
   }
}
