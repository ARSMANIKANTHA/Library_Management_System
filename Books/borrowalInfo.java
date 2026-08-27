package Books;

public class borrowalInfo {
    
    public String borrowalDate;
    public String deadLine;
    public String noOfDays;
    // String returnDate;

    public borrowalInfo(String borrowDate,String noOfDays, String deadLine) {
        this.borrowalDate = borrowDate;
        this.deadLine = deadLine;
        this.noOfDays = noOfDays;
    }
}
