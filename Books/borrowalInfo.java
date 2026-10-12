package Books;
import Books.Book;
import Users.User;

public class borrowalInfo {
    private static long borrowalId = 0;
    private Book book;
    private User user;
    public String borrowalDate;
    public String deadLine;
    public int noOfDays;

    public borrowalInfo(Book b, User u, String startDate, String endDate, int days){
        ++borrowalInfo.borrowalId;
        this.book = b;
        this.user = u;
        this.borrowalDate = startDate;
        this.deadLine = endDate;
        this.noOfDays = days;
    }

    public User getUserName(){
        return this.user;
    }

    public Book getBookName(){
        return this.book;
    }
}
