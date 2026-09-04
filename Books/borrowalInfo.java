package Books;

public class borrowalInfo {
    
    public String borrowalDate;
    public String deadLine;
    public int noOfDays;
    // String returnDate;

    public borrowalInfo(String borrowDate,int noOfDays, String deadLine) {
        this.borrowalDate = borrowDate;
        this.deadLine = deadLine;
        this.noOfDays = noOfDays;
    }
}
