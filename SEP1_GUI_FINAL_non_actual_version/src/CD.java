import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;


public class CD extends Item implements Serializable 
{

   private boolean availability;
   private Date borrowedFrom;
   private String title;
   private String author;
   private String year;
   private ArrayList<CD> itemsOfTheSameTitle;
   
   public CD(String title, String author, String year)
   {
      this.title = title;
      this.author = author;
      this.year = year;
      this.itemsOfTheSameTitle = new ArrayList<CD>();
      this.availability = true;
   }
   
   public void addTheItemOfTheSameType()
   {
      this.itemsOfTheSameTitle.add(new CD(this.title, this.author, this.year));
   }
   
   public void deleteTheItem(int index)
   {
      this.itemsOfTheSameTitle.remove(index);
   }
   
   public CD getTheItem(int index)
   {
      return this.itemsOfTheSameTitle.get(index);
   }
   
   @Override
   public void setAvailability(boolean availability,int index)
   {
      this.itemsOfTheSameTitle.get(index).availability=availability;
   }
   
   @Override
   public void setBorrowedFrom(int index)
   {
      this.itemsOfTheSameTitle.get(index).borrowedFrom = new Date();
   }
   
   @Override
   public boolean getAvailability(int index)
   {
      return this.itemsOfTheSameTitle.get(index).availability;
   }
   
   @Override
   public Date getBorrowedFrom(int index)
   {
      return this.itemsOfTheSameTitle.get(index).borrowedFrom;
   }
   
   @Override
   public String getTitle()
   {
      return this.title;
   }
   
   @Override
   public int getNumberOfCopies()
   {
      return this.itemsOfTheSameTitle.size();
   }
   
   @Override
   public int getIndexOfAvailableCopy()
   {
      int indexOfAvailableCopy = -1;   
         for (int i = this.itemsOfTheSameTitle.size()-1; i>=0; i--)
         {
            if (this.itemsOfTheSameTitle.get(i).availability == true)
               indexOfAvailableCopy = i;
         }
      
      return indexOfAvailableCopy;
   }
   
   @Override
   public String toString()
   {
       return "CD" + "\n" +"AUTHOR: " + this.author + "\n" + "TITLE: " + this.title + "\n" + "YEAR: " + this.year;
   }
}
