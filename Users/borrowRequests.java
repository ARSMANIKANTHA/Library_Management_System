package Users;
import Books.Book;
public class borrowRequests extends Requests{
    public String requestDate;
    public String noOfDays;
    public borrowRequests(User reqUser, Book reqBook, String reqDate, String noOfDays){
        super(reqUser,reqBook);
        requestDate = reqDate;
        this.noOfDays = noOfDays;
    }
}