import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;


public class Article extends Item implements Serializable 
{

   private boolean availability;
   private Date borrowedFrom;
   private String title;
   private String author;
   private String month;
   private String year;
   private ArrayList<Article> itemsOfTheSameTitle;
   
   public Article(String title, String author, String month, String year)
   {
      this.title = title;
      this.author = author;
      this.year = year;
      this.month = month;
      this.itemsOfTheSameTitle = new ArrayList<Article>();
      this.availability = true;
   }
   
   public void addTheItemOfTheSameType()
   {
      this.itemsOfTheSameTitle.add(new Article(this.title, this.author, this.month, this.year));
   }
   
   public void deleteTheItem(int index)
   {
      this.itemsOfTheSameTitle.remove(index);
   }
   
   public Article getTheItem(int index)
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
       return "Article" + "\n" +"TITLE: " + this.title + "\n" + "AUTHOR: " + this.author + "\n" + "MONTH: " + this.month + "\n" + "YEAR: "  + this.year;
   }
}
