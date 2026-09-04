package Users;
import Books.Book;
public class borrowRequests extends Requests{
    public String requestDate;
    public int noOfDays;
    public borrowRequests(User reqUser, Book reqBook, String reqDate, int noOfDays){
        super(reqUser,reqBook);
        requestDate = reqDate;
        this.noOfDays = noOfDays;
    }
}