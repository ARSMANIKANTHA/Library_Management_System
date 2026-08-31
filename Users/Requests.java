package Users;
import Books.Book;
public class Requests {
    User requestedUser;
    Book requestedBook;
    public Requests(User reqUser, Book reqBook){
        requestedUser = reqUser;
        requestedBook = reqBook;
    }
}
