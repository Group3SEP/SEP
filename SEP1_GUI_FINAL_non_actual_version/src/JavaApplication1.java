
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaApplication1 {
      
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("deprecation")
   public static void main(String[] args) {
         
        List<Date> myList = new ArrayList<Date>();
   myList.add(new Date(2014, 05, 20));
   myList.add(new Date(2014, 02, 20));
   myList.add(new Date(2014, 05, 20));
         
        Collections.sort(myList, new Comparator<Date>(){
 
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });
         
        for(int i = 0; i < myList.size(); i++){
            System.out.println(myList.get(i).toString());
        }
 
    }
     
}