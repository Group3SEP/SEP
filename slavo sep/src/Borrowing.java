import java.util.ArrayList;


public class Borrowing
{
   private ArrayList<Item> reservedItems;
   private String name;
   
   public Borrowing(String name, Item item)
   {
      this.name = name;
      reservedItems = new ArrayList<Item>();
      reservedItems.add(item);
   }
   
   public void addOneMoreItem(Item item)
   {
      reservedItems.add(item);
   }
   
   public void removeReservedItem(int index)
   {
      reservedItems.remove(index);
   }
   
   
}
