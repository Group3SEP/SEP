import java.util.ArrayList;
import java.io.Serializable;

public class Items implements Serializable 
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private ArrayList<Item> items;
   
   public Items()
   {
      items = new ArrayList<Item>();
   }
   
   public void addAnItem(Item item)
   {
      this.items.add(item);
   }
   
   public Item getAnItem(int index)
   {
      return this.items.get(index);
   }
   
   public void removeAnItem(int index)
   {
      this.items.remove(index);
   }
   
   public int getSize()
   {
      return items.size();
   }
   
   public Item searchAnItem(String title)
   {
      Item book = new Book("no match found","",0,"");
      
      for (int i=0; i<this.items.size(); i++)
      {
         if(items.get(i).getTitle().equalsIgnoreCase(title))
         {
            book = this.items.get(i);
         }
      }
      
      return book;
   }
   
   public int searchAnItemAndGetIndex(String title)            
   {
      int index = -1;
      
      for (int i=0; i<this.items.size(); i++)
      {
         if(items.get(i).getTitle().equalsIgnoreCase(title))
         {
            index = i;
         }
      }

      return index;
   
   }
}
