package lab04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 * 
 */
public class Library<Type>{

  protected ArrayList<LibraryBook<Type>> library;

  public Library() {
    library = new ArrayList<LibraryBook<Type>>();
  }

  /**
   * Add the specified book to the library, assume no duplicates.
   * 
   * @param isbn --
   *          ISBN of the book to be added
   * @param author --
   *          author of the book to be added
   * @param title --
   *          title of the book to be added
   */
  public void add(long isbn, String author, String title) {
    library.add(new LibraryBook(isbn, author, title));
  }
  

  /**
   * Add the list of library books to the library, assume no duplicates.
   * 
   * @param list --
   *          list of library books to be added
   */
  public void addAll(ArrayList<LibraryBook<Type>> list) {
    library.addAll(list);
  }

  /**
   * Add books specified by the input file. One book per line with ISBN, author,
   * and title separated by tabs.
   * 
   * If file does not exist or format is violated, do nothing.
   * 
   * @param filename
   */
  public void addAll(String filename) {
    ArrayList<LibraryBook<Type>> toBeAdded = new ArrayList<LibraryBook<Type>>();

    try {
      Scanner fileIn = new Scanner(new File(filename));
      int lineNum = 1;

      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();

        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter("\\t");

        if (!lineIn.hasNextLong())
          throw new ParseException("ISBN", lineNum);
        long isbn = lineIn.nextLong();

        if (!lineIn.hasNext())
          throw new ParseException("Author", lineNum);
        String author = lineIn.next();

        if (!lineIn.hasNext())
          throw new ParseException("Title", lineNum);
        String title = lineIn.next();

        toBeAdded.add(new LibraryBook(isbn, author, title));

        lineNum++;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage() + " Nothing added to the library.");
      return;
    } catch (ParseException e) {
      System.err.println(e.getLocalizedMessage()
          + " formatted incorrectly at line " + e.getErrorOffset()
          + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

  /**
   * Returns the holder of the library book with the specified ISBN.
   * If no book with the specified ISBN is in the library, returns null.
   * 
   * @param isbn --
   *          ISBN of the book to be looked up
   */
  public String lookup(long isbn) {
    // *FILL IN -- do not return null unless appropriate
	for (int i = 0; i < library.size(); i++){
		if (library.get(i).getIsbn() == isbn){
			return (String) library.get(i).getHolder();
		}
	}
    return null;
  }

  /**
   * Returns the list of library books checked out to the specified holder.
   * 
   * If the specified holder has no books checked out, returns an empty list.
   *
   * @param holder --
   *          holder whose checked out books are returned
   * @return
   */
  public ArrayList<LibraryBook<Type>> lookup(Type holder) {
    // *FILL IN -- do not return null
	  ArrayList<LibraryBook<Type>>  CheckedOutList = new ArrayList<>();
	  if (library.size() == 0)
		  return CheckedOutList;
	  for (int i = 0; i < library.size(); i++){
		  Type BookHolder = library.get(i).getHolder();
		  if (holder.equals(BookHolder)){
			  CheckedOutList.add(library.get(i));
		  }
	  }
	  
     // ** according to javadoc, should never return null **
	 // if (CheckedOutList.size() == 0)
	 //	  return null;
    
	 return CheckedOutList;
  }

  /**
   * Sets the holder and due date of the library book with the specified ISBN.
   * If no book with the specified ISBN is in the library, returns false.
   * If the book with the specified ISBN is already checked out, returns false.
   * Otherwise, returns true.
   * 
   * @param isbn --
   *          ISBN of the library book to be checked out
   * @param holder --
   *          new holder of the library book
   * @param month --
   *          month of the new due date of the library book
   * @param day --
   *          day of the new due date of the library book
   * @param year --
   *          year of the new due date of the library book
   */
  public boolean checkout(long isbn, Type holder, int month, int day, int year) {
    // *FILL IN -- do not return false unless appropriate
	for (int i = 0; i < library.size(); i++){
		if (library.get(i).getIsbn() == isbn){
			if (library.get(i).getHolder() != null){
				return false;
			}else{
				GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
				 
				library.get(i).checkout((Type) holder, dueDate);
				return true;
			}
		}
	}
    return false;
  }

  /**
   * Unsets the holder and due date of the library book.
   * If no book with the specified ISBN is in the library, returns false.
   * If the book with the specified ISBN is already checked in, returns false.
   * Otherwise, returns true.
   * 
   * @param isbn --
   *          ISBN of the library book to be checked in
   */
  public boolean checkin(long isbn) {
    // *FILL IN -- do not return false unless appropriate
	  for (int i = 0; i < library.size(); i++){
		  if (library.get(i).getIsbn() == isbn){
			  if (library.get(i).getHolder() == null){
				  return false;
			  }else{
				  library.get(i).checkin();
				  return true;
			  }
		  }
	  }
    return false;
  }


    /**
     * Returns the list of library books, sorted by ISBN (smallest ISBN first).
     */

    public ArrayList<LibraryBook<Type>> getInventoryList() {
        ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
        libraryCopy.addAll(library);
        IsbnComparator comparator = new IsbnComparator();
        sort(libraryCopy, comparator);
        return libraryCopy;
    }

    /**
     * Performs a SELECTION SORT on the input ArrayList.
     *    1. Find the smallest item in the list.
     *    2. Swap the smallest item with the first item in the list.
     *    3. Now let the list be the remaining unsorted portion
     *       (second item to Nth item) and repeat steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list,
                                        Comparator<ListType> c) {
        for (int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < list.size(); j++)
                if (c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among library books using the ISBN.
     */
    protected class IsbnComparator implements Comparator<LibraryBook<Type>> {
        @Override
        public int compare(LibraryBook<Type> o1, LibraryBook<Type> o2){
            return (int) (o1.getIsbn()-o2.getIsbn());
        }
        // FILL IN - write the compare method
    }

    /**
     * Returns the list of library books, sorted by author
     */

  public ArrayList<LibraryBook<Type>> getOrderedByAuthor() {
      AuthorComparator comparator1 = new AuthorComparator();
      ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
      libraryCopy.addAll(library);
      sort(libraryCopy, comparator1);
      return libraryCopy;
  }


    /**
     * Returns the list of library books whose due date is older than the input
     * date. The list is sorted by date (oldest first).
     *
     * If no library books are overdue, returns an empty list.
     */

  public ArrayList<LibraryBook<Type>> getOverdueList(int month, int day,
      int year) {
      DueDateComparator comparator2 = new DueDateComparator();
      ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
      for(int i = 0; i < libraryCopy.size(); i++){
          System.out.print(libraryCopy.get(i).getDueDate());
      }
      libraryCopy.addAll(library);

      sort(libraryCopy, comparator2);
      //System.out.println(libraryCopy);
      return libraryCopy;
    // FILL IN -- do not return null
  }


    /**
     * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
     */

  protected class AuthorComparator implements Comparator<LibraryBook<Type>> {
        @Override
        public int compare(LibraryBook<Type> o1, LibraryBook<Type> o2){
            if(o1.getAuthor().compareTo(o2.getAuthor()) == 0){
                if(!(o1.getTitle().compareTo(o2.getTitle())==0)){
                    return (int) (o2.getAuthor().compareTo(o1.getAuthor()));
                }
            }
            return (int) (o1.getAuthor().compareTo(o2.getAuthor()));
        }

    // FILL IN - write the compare method
  }

    /**
     * Comparator that defines an ordering among library books using the due date.
     */

  protected class DueDateComparator implements Comparator<LibraryBook<Type>> {
        @Override
        public int compare(LibraryBook<Type> o1, LibraryBook<Type> o2){
            if(o1.getDueDate() == null || o2.getDueDate() == null){
                return 0;
            }
            return (int) (o1.getDueDate().compareTo(o2.getDueDate()));
        }
        // FILL IN - write the compare method

    // FILL IN - write the compare method

  }








    /**
   * Unsets the holder and due date for all library books checked out by the
   * specified holder.
   * If no books with the specified holder are in the library, returns false;
   * Otherwise, returns true.
   * 
   * @param holder --
   *          holder of the library books to be checked in
   */
  public boolean checkin(Type holder) {
	    // *FILL IN -- do not return false unless appropriate	  
	int counter = 0;
	for (int i = 0; i < library.size(); i++){
		if (holder.equals(library.get(i).getHolder())){
			counter++;
			library.get(i).checkin();
		}
	}
	
	if (counter > 0)
		return true;
	
    return false;
  }
}
