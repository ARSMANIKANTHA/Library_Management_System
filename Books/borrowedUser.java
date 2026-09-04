package Books;

import Users.User;

public class borrowedUser extends borrowalInfo{
    public User user;

    public borrowedUser(User user, String borrowDate,int noOfDays, String deadLine) {
        this.user = user;
        super(borrowDate,noOfDays,deadLine);
    }
}
