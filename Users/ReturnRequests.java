package Users;
import Books.Book;
public class ReturnRequests extends Requests{
    String returnDate;
    public ReturnRequests(User reqUser, Book reqBook, String reqDate){
        super(reqUser,reqBook);
        returnDate = reqDate;
    }
}