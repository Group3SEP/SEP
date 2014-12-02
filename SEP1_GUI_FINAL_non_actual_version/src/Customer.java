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
      return this.type + "\n" + "Name: " + this.name + "\n" + "CPR: " + this.cpr;
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
         message += this.borrowings.get(i).toString();
         message += "\n";
      }
      return message;
   }
   
   public void deleteReservation(Item item)
   {
      for (int i = 0; i<this.reservations.size(); i++)
      {
         if (this.reservations.get(i).getReservedItem().getTitle().equals(item.getTitle()))
         {
            this.reservations.get(i).getReservedItem().setAvailability(true, this.reservations.get(i).getIndex());
            this.reservations.remove(i);
         }
      }
   }
   
   public void deleteBorrowing(Item item)
   {
      for (int i = 0; i<this.borrowings.size(); i++)
      {
         if (this.borrowings.get(i).getBorrowedItem().getTitle().equals(item.getTitle()))
         {
            this.borrowings.get(i).getBorrowedItem().setAvailability(true, this.borrowings.get(i).getIndex());
            this.borrowings.remove(i);
         }
      }
   }
   
   public String getName()
   {
      return name;
   }
   
  public String getCpr()
{
   return cpr;
}
  
  public int getNumberOfBorrowings()
  {
     return this.borrowings.size();
  }

  public int getNumberOfReservations()
  {
     return this.reservations.size();
  }
}
