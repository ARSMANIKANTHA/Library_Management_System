package Books;
public class borrowedBook extends borrowalInfo {
    public Book book;
    public borrowedBook(Book book, String borrowDate,String noOfDays, String deadLine) {
        this.book = book;
        super(borrowDate,deadLine,noOfDays);
    }
}
