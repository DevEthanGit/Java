package lab04;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for generic Library.
 *
 */
public class LibraryGenericTest {

  public static void main(String[] args) {

    // test a library that uses names (String) to id patrons
    Library<String> lib1 = new Library<String>();
    lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib1.add(9780374292798L, "Thomas L. Friedman", "dhe World is Flat");
    lib1.add(9780374292797L, "Thomas L. Friedman", "adhe World is Flat");
    lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib1.add(9780446580342L, "David Baldacci", "Simple Genius");
    //System.out.println(lib1.getInventoryList());

    //lib1.addAll("Mushroom_Publishing.txt");
    //System.out.println(lib1.getOrderedByAuthor());

    String patron1 = "Jane Doe";

    if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBook<String>> booksCheckedOut1 = lib1.lookup(patron1);
    if (booksCheckedOut1 == null
        || booksCheckedOut1.size() != 2
        || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut1.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut1.get(0).getHolder().equals(patron1)
        || !booksCheckedOut1.get(0).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut1.get(1).getHolder().equals(patron1)
        || !booksCheckedOut1.get(1).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib1.checkin(patron1))
      System.err.println("TEST FAILED: checkin(holder)");

    // test a library that uses phone numbers (PhoneNumber) to id patrons
    Library<PhoneNumber> lib2 = new Library<PhoneNumber>();
    lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

    PhoneNumber patron2 = new PhoneNumber("305.555.1234");

    if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBook<PhoneNumber>> booksCheckedOut2 = lib2.lookup((patron2));
    if (booksCheckedOut2 == null
        || booksCheckedOut2.size() != 2
        || !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut2.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut2.get(0).getHolder().equals(patron2)
        || !booksCheckedOut2.get(0).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut2.get(1).getHolder().equals(patron2)
        || !booksCheckedOut2.get(1).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib2.checkin(patron2))
      System.err.println("TEST FAILED: checkin(holder)");
    
    System.out.println("Testing done.");
    
    // FILL IN for tests
    lib1.checkout(9780374292799L,patron1,9,20,2022);
    lib1.checkout(9780374292798L,patron1,9,21,2022);



    // FOR LAB: write tests for getInventoryList
//    ArrayList<LibraryBook<PhoneNumber>> inventory = lib2.getInventoryList();
//    for(int i = 0; i < inventory.size(); i++){
//      System.out.print(inventory.get(i).getIsbn() + " ");
//    }
    // test a medium library: you will use this for homework
    Library<String> lib3 = new Library<String>();
    lib3.addAll("Mushroom_Publishing.txt");
    String name = "John Doe";
    int day = 20;
    ArrayList<LibraryBook<String>> dueDateList = lib3.getOverdueList(9,25,2022);
    for(int i = 0; i < dueDateList.size();i++){
      lib3.checkout(lib3.getInventoryList().get(i).getIsbn(),name,9,day,2022);
    }

//    for(int i = 0; i < dueDateList.size(); i++){
//      System.out.print(dueDateList.get(i).getDueDate() + " ");
//    }
  }
}
