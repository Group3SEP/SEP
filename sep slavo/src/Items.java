import java.util.ArrayList;
import java.io.Serializable;

public class Items implements Serializable 
{

   private ArrayList<Item> items;
   
   public Items()
   {
      items = new ArrayList<Item>();
   }
   
   public void addAnItem(Item item)
   {
      this.items.add(item);
   }
   
   public void getAnItem(int index)
   {
      this.items.get(index);
   }
   
   public void removeAnItem(int index)
   {
      this.items.remove(index);
   }
   
   public Item searchAnItem(String title)
   {
      Item book = new Book("no match found");
      
      for (int i=0; i<this.items.size(); i++)
      {
         if(items.get(i).getTitle().equalsIgnoreCase(title))
         {
            book = this.items.get(i);
         }
      }
      
      return book;
   }
}
