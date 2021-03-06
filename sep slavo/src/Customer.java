import java.util.ArrayList;
import java.io.Serializable;

public class Customer implements Serializable 
{

   private String name;
   private String cpr;
   private String type;
   private ArrayList<Borrowing> borrowings;
   private ArrayList<Reservation> reservations;
   
   public Customer(String name, String cpr, String type)
   {
      this.name = name;
      this.cpr = cpr;
      this.type = type;
      this.borrowings = new ArrayList<Borrowing>();
      this.reservations = new ArrayList<Reservation>();
   }
   
   public String toString()
   {
      return this.name + " " + this.cpr + " " + this.type;
   }
   
   public void newBorrowing(String detail, Item item)
   {
      int index = item.getIndexOfAvailableCopy();
      if (index != -1)
      this.borrowings.add(new Borrowing(detail,item,index));
   }
   
   public void newReservation(String detail, Item item)
   {
      int index = item.getIndexOfAvailableCopy();
      if (index != -1)
      this.reservations.add(new Reservation(detail,item,index));
   }
   
   public String reservationsToString()
   {
      String message = "";
      for (int i=0; i<this.reservations.size(); i++)
      {
         message += this.reservations.get(i).toString();
         message += "\n";
      }
      return message;
   }
   
   public String borrowingsToString()
   {
      String message = "";
      for (int i=0; i<this.borrowings.size(); i++)
      {
         message += "res num " + (i+1) + " ";
         message += this.borrowings.get(i).toString();
         message += "\n";
      }
      return message;
   }
   
   public void deleteReservation(int numberOfCopy)
   {
      this.reservations.remove(numberOfCopy-1);
   }
   
   public void deleteBorrowing(int numberOfCopy)
   {
      this.borrowings.remove(numberOfCopy-1);
   }
   
   public String getName()
   {
      return name;
   }
}
