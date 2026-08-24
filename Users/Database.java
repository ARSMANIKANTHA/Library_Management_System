package Users;
import java.util.HashMap;
import java.util.Map;

import Books.Book;

class Database {
    
    private static final HashMap<String,Book> booksData = new HashMap<>();
    private static final HashMap<String,User> usersData = new HashMap<>(Map.of("admin@lms.com",new Librarian("Administrator","Administrator",28,"Male","admin@lms.com")));
    Database(){}

    //User Info
    public HashMap<String,User> usersDB(){
        return usersData;
    }

    public boolean userExistence(String email){
        for(User i : usersData.values()){
            if(i.email == email){
                return true;
            }
        }
        return false;
    }
    public void addNewUser(User newUser){
        usersData.put(newUser.email, newUser);
    }

    void removeUser(String email){
        usersData.remove(email);
        System.out.println("User has been deleted successfully....!");
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
