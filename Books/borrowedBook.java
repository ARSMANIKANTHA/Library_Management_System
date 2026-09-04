package Books;
public class borrowedBook extends borrowalInfo {
    public Book book;
    public borrowedBook(Book book, String borrowDate,int noOfDays, String deadLine) {
        this.book = book;
        super(borrowDate,noOfDays,deadLine);
    }
}