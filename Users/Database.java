package Users;
import java.util.HashMap;
import Books.Book;

class Database {
    
    public static final HashMap<String,Book> booksData = new HashMap<>();
    private static final HashMap<String,User> usersData = new HashMap<>();
    Database(){}
    Database(User admin){
        usersData.put(admin.userID,admin);
    }
    HashMap<String,User> usersDB(){
        return usersData;
    }
    void addUser(User newUser){
        usersData.put(newUser.userID,newUser);
    }
    void removeUser(String userID){
        usersData.remove(userID);
    }
    
    void addBook(Book book){
        booksData.put(book.getISBN(), book);
    }

    public static void listAvailableBooks(){
        System.out.println("====Books List====");

        for(String ISBN : booksData.keySet()){
            Book book = booksData.get(ISBN);
            System.out.printf("Book Name (ISBN: %s):%s \n Book Author: %s\nPublished Date: %s\nBook Genre: %s",book.getISBN(),book.getBookName(),book.getAuthorName(),book.getPublishedDate(),book.getGenre());
        }
    }
}
