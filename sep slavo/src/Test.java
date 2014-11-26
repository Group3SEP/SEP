import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Test implements Serializable 
{

   public static void main(String[] args) throws IOException, ClassNotFoundException
   {
      // TODO Auto-generated method stub
      
      // test of item class and subclasses
      
//      Item book1 = new Book("tale of a man");
//      
//      book1.addTheItemOfTheSameType();
//      
//      book1.setBorrowedFrom(0);
//      
//      System.out.println(book1.getTitle() + " " + book1.getBorrowedFrom(0));
//      
//      book1.addTheItemOfTheSameType();
//      
//      book1.setBorrowedFrom(1);
//      
//      System.out.println(book1.getTitle() + " " + book1.getBorrowedFrom(1));
//      
//      
//      Item article1 = new Article("how to do a sep");
//      
//      article1.addTheItemOfTheSameType();
//      
//      article1.setBorrowedFrom(0);
//      
//      System.out.println(article1.getTitle() + " " + article1.getBorrowedFrom(0));
//      
//      article1.addTheItemOfTheSameType();
//      
//      article1.setBorrowedFrom(1);
//      
//      System.out.println(article1.getTitle() + " " + article1.getBorrowedFrom(1));
//      
//      //test of items class
//      
//      //search test
//      
//      Items database = new Items();
//      
//      database.addAnItem(article1);
//      database.addAnItem(book1);
//      
//      System.out.println(database.searchAnItem("HOW to do a SEP").getBorrowedFrom(0));
//      
////      //reservation and borrowing test
////      
////      Borrowing borr1 = new Borrowing("first test blah blah", book1, 0);
////      System.out.println(borr1.toString());
////      
////      Reservation res1 = new Reservation("hahaha", article1, 0);
////      System.out.println(res1.toString());
////      
////      Reservation res2 = new Reservation("hahaha", article1, 1);
////      System.out.println(res2.toString());
////      //test of methods related to copies of an item
////      
////      System.out.println(book1.getNumberOfCopies());
////      
////      System.out.println(book1.getIndexOfAvailableCopy());
////      
////      System.out.println(article1.getIndexOfAvailableCopy());
//      
//      //first customer test
//      Customer slavo = new Customer("Slavo", "123", "student");
//      
//      slavo.newReservation("prva skuska", book1);
//      slavo.newReservation("prva skuska", book1);
//      slavo.newReservation("prva skuska", book1);
//      
//      slavo.newBorrowing("bla bla bla", article1);
//      slavo.newBorrowing("bla bla bla", article1);
//      slavo.newBorrowing("bla bla bla", article1);
//      
//      System.out.println(slavo.reservationsToString());
//      System.out.println(slavo.borrowingsToString());
//      
//      slavo.deleteBorrowing(1);
//      
//      System.out.println("after deleting \n");
//      System.out.println(slavo.borrowingsToString());
//      
//      //customers test
//      Customer alin = new Customer("Alin", "21234", "student");
//      Customer stanley = new Customer("Stanley", "2454235", "student");
//      Customer iustin = new Customer("Iustin", "6735654", "student");
//      Customer marek = new Customer("Marek", "1324", "student");
//      Customer ole = new Customer("Ole", "846365", "lecturer");
//      
//      Customers customers = new Customers();
//      customers.registerCustomer(ole);
//      customers.registerCustomer(marek);
//      customers.registerCustomer(iustin);
//      customers.registerCustomer(stanley);
//      customers.registerCustomer(alin);
//      customers.registerCustomer(slavo);
//      
//      System.out.println(customers.searchByName("stanley").getName());
//      
//      //struggling with files
//      
//      try
//      {
//         FileOutputStream fos = new FileOutputStream("customers database");
//         ObjectOutputStream oos = new ObjectOutputStream(fos);
//         
//         oos.writeObject(customers);
//         
//         oos.close();
//      }
//      
//      catch (FileNotFoundException e)
//      {
//         System.out.println("no file");
//      }
//      
//      try
//      {
//         FileOutputStream fos1 = new FileOutputStream("items database");
//         ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
//         
//         oos1.writeObject(database);
//         
//         oos1.close();
//      }
//      
//      catch (FileNotFoundException e)
//      {
//         System.out.println("no file");
//      }
      
      
      //working with database
      try {
         FileInputStream is = new FileInputStream("customers database");
         ObjectInputStream in = new ObjectInputStream(is);
         
         Object cus1 = in.readObject();
         Customers cus2 = (Customers)cus1;
         
         System.out.println(cus2.searchByName("stanley").getName());
         System.out.println(cus2.searchByName("slavo").reservationsToString());
         
         in.close();
      } catch (FileNotFoundException e) {
         System.out.println("No file");
      }
      
      try {
         FileInputStream is = new FileInputStream("items database");
         ObjectInputStream in = new ObjectInputStream(is);
         
         Object items1 = in.readObject();
         Items items2 = (Items)items1;
         
         System.out.println(items2.searchAnItem("tale OF a Man").getTitle());
         
         in.close();
      } catch (FileNotFoundException e) {
         System.out.println("No file");
      }
      
   }

}
