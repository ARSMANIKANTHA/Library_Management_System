package Users;
import Books.Book;
public class Requests {
    User requestedUser;
    Book requestedBook;
    String requestDate;
    String noOfDays;
    public Requests(User reqUser, Book reqBook, String reqDate, String noOfDays){
        requestedUser = reqUser;
        requestedBook = reqBook;
        requestDate = reqDate;
        this.noOfDays = noOfDays;
    }
}
