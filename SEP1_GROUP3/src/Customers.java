import java.util.ArrayList;
import java.io.Serializable;

public class Customers implements Serializable 
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private ArrayList<Customer> customers;

   public Customers()
   {
      customers = new ArrayList<Customer>();
   }

   public void registerCustomer(Customer customer)
   {
      customers.add(customer);
   }

   public void unregisterCustomer(Customer customer)
   {
      int index = -1;
      for (int i = 0; i < customers.size(); i++)
      {
         if (customer == customers.get(i))
            index = i;
      }
      customers.remove(index);
   }

   public Customer searchByName(String name)
   {
      Customer searchedCustomer = null;
      for (int i = 0; i<customers.size(); i++)
      {
         if (customers.get(i).getName().equalsIgnoreCase(name))
            searchedCustomer=customers.get(i);
      }
      return searchedCustomer;
   }

   public Customer searchByCPR(String cpr)
   {
      Customer searchedCustomer = new Customer("cx451df5vc","","");
      for (int i = 0; i<customers.size(); i++)
      {
         if (customers.get(i).getCpr().equalsIgnoreCase(cpr))
            searchedCustomer=customers.get(i);
      }
      return searchedCustomer;
   }

   public Customer returnBorrowing(String cpr)
   {
      Customer searchedCustomer = new Customer("cx451df5vc","","");
      for (int i = 0; i<customers.size(); i++)
      {
         if (customers.get(i).getCpr().equalsIgnoreCase(cpr))
            searchedCustomer=customers.get(i);
      }
      return searchedCustomer;
   }
   
   @SuppressWarnings("rawtypes")
   public ArrayList<ArrayList> returnDate()
   {
      ArrayList<ArrayList> message = new ArrayList<>();
      for (int i=0; i<this.customers.size(); i++)
      {
         message.add(this.customers.get(i).getReturnDate());
      }
      return message;
   }
   
}
