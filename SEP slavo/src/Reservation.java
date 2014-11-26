
public class Reservation
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
      return "reservation " + details + " availability " + this.reservedItem.getAvailability(index) + " " + this.reservedItem.getTitle() + " copy n." + (this.index+1); 
   }
   
   
}
