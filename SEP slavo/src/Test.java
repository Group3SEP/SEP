
public class Test
{

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      
      // test of item class and subclasses
      
      Item book1 = new Book("tale of a man");
      
      book1.addTheItemOfTheSameType();
      
      book1.setBorrowedFrom(0);
      
      System.out.println(book1.getTitle() + " " + book1.getBorrowedFrom(0));
      
      book1.addTheItemOfTheSameType();
      
      book1.setBorrowedFrom(1);
      
      System.out.println(book1.getTitle() + " " + book1.getBorrowedFrom(1));
      
      
      Item article1 = new Article("how to do a sep");
      
      article1.addTheItemOfTheSameType();
      
      article1.setBorrowedFrom(0);
      
      System.out.println(article1.getTitle() + " " + article1.getBorrowedFrom(0));
      
      article1.addTheItemOfTheSameType();
      
      article1.setBorrowedFrom(1);
      
      System.out.println(article1.getTitle() + " " + article1.getBorrowedFrom(1));
      
      //test of items class
      
      //search test
      
      Items database = new Items();
      
      database.addAnItem(article1);
      database.addAnItem(book1);
      
      System.out.println(database.searchAnItem("HOW to do a SEP").getBorrowedFrom(0));
      
//      //reservation and borrowing test
//      
//      Borrowing borr1 = new Borrowing("first test blah blah", book1, 0);
//      System.out.println(borr1.toString());
//      
//      Reservation res1 = new Reservation("hahaha", article1, 0);
//      System.out.println(res1.toString());
//      
//      Reservation res2 = new Reservation("hahaha", article1, 1);
//      System.out.println(res2.toString());
//      //test of methods related to copies of an item
//      
//      System.out.println(book1.getNumberOfCopies());
//      
//      System.out.println(book1.getIndexOfAvailableCopy());
//      
//      System.out.println(article1.getIndexOfAvailableCopy());
      
      //first customer test
      Customer slavo = new Customer("Slavo", "123", "student");
      
      slavo.newReservation("prva skuska", book1);
      slavo.newReservation("prva skuska", book1);
      slavo.newReservation("prva skuska", book1);
      
      slavo.newBorrowing("bla bla bla", article1);
      slavo.newBorrowing("bla bla bla", article1);
      slavo.newBorrowing("bla bla bla", article1);
      
      System.out.println(slavo.reservationsToString());
      System.out.println(slavo.borrowingsToString());
   }

}
