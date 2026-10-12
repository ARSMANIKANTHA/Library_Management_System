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
    private boolean isActive;

    public borrowalInfo(Book b, User u, String startDate, String endDate, int days){
        ++borrowalInfo.borrowalId;
        this.book = b;
        this.user = u;
        this.borrowalDate = startDate;
        this.deadLine = endDate;
        this.noOfDays = days;
        this.isActive = true;
    }

    public boolean getBorrowStatus(){
        return this.isActive;
    }

    public void setBorrowStatus(boolean curStatus){
        this.isActive = curStatus;
        return;
    }

    public User getUserName(){
        return this.user;
    }

    public Book getBookName(){
        return this.book;
    }
}
