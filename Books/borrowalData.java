package Books;

import Users.User;

public class borrowalData {
    User user;
    String borrowalDate;
    String deadLine;
    String noOfDays;
    // String returnDate;

    public borrowalData(User user, String borrowDate,String noOfDays, String deadLine) {
        this.user = user;
        this.borrowalDate = borrowDate;
        this.deadLine = deadLine;
        this.noOfDays = noOfDays;
        // this.returnDate = returnDate;
    }

    public borrowalData(){}
}
