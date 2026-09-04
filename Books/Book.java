package Books;
import java.util.*;
public class Book {
    private String bookName;
    private String ISBN;
    private String authorName;
    private String publishedDate;
    private String Genre;
    private int noOfCopies;

    //Borrowal Information for specific book:
    public ArrayList<borrowedUser> borrowalList = new ArrayList<>();

    public Book(){}
    public Book(String bookName,String ISBN, String autorName, String publishedDate,String Genre, int noOfCopies){
        this.bookName = bookName;
        this.ISBN = ISBN;
        this.authorName = autorName;
        this.Genre = Genre;
        this.noOfCopies = noOfCopies;
        this.publishedDate = publishedDate;
    }
    //getter method to get individual prop of an object
    
    public String getBookName(){
        return bookName;
    }
    public String getISBN(){
        return ISBN;
    }
    public String getAuthorName(){
        return authorName;
    }
    public String getPublishedDate(){
        return publishedDate;
    }
    public String getGenre(){
        return Genre;
    }
    public int getNoOfCopies(){
        return noOfCopies;
    }
    public void setNoOfCopies(int n){
       noOfCopies = n;
    }

    
}
