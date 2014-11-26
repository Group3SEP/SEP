
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
      
      Borrowing borr1 = new Borrowing("first test blah blah", book1, 0);
      System.out.println(borr1.toString());
      
      Reservation res1 = new Reservation("hahaha", article1, 1);
      System.out.println(res1.toString());
      
   }

}
