import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;


public class Book extends Item implements Serializable 
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private boolean availability;
   private Date borrowedFrom;
   private String title;
   private String author;
   private String isbn;
   private int year;
   private ArrayList<Book> itemsOfTheSameTitle;
   private String instance;
   
   public Book(String title, String author, int year, String isbn)
   {
      this.title = title;
      this.author = author;
      this.year = year;
      this.isbn = isbn;
      this.itemsOfTheSameTitle = new ArrayList<Book>();
      this.availability = true;
      this.instance = "Book";
   }
   
   public void addTheItemOfTheSameType()
   {
      this.itemsOfTheSameTitle.add(new Book(this.title, this.author, this.year, this.isbn));
   }
   
   public void deleteTheItem(int index)
   {
      this.itemsOfTheSameTitle.remove(index);
   }
   
   public Book getTheItem(int index)
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
      // TODO Auto-generated method stub
      return "Book" + "\n" +"TITLE: " + this.title + "\n" + "AUTHOR: " + this.author + "\n" + "ISBN: " + this.isbn + "\n" + "YEAR: "  + this.year;
   }
   
   @Override
   public String getInstance()
   {
      // TODO Auto-generated method stub
      return instance;
   }
   
   @Override
   public int getYear()
   {
      // TODO Auto-generated method stub
      return this.year;
   }
}
