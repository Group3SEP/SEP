import java.io.Serializable;

public class Reservation implements Serializable 
{

   private Item reservedItem;
   private String details;
   private int index;
   
   public Reservation(String detail, Item item, int index)
   {
      this.details = detail;
      reservedItem = item;
      item.setAvailability(false, index);
      this.index = index;
   }
   
   public String toString()
   {
      return this.reservedItem.getTitle() + " copy n." + (this.index+1); 
   }
   
   public Item getReservedItem()
   {
      return reservedItem;
   }
   
   public int getIndex()
   {
      return index;
   }
   
}
