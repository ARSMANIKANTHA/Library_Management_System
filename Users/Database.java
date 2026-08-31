package Users;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import Books.Book;

class Database {
    
    private static final HashMap<String,Book> booksData = new HashMap<>();
    private static final HashMap<String,User> usersData = new HashMap<>(Map.of("admin",new Librarian("admin","admin",28,"Male","admin")));
    public static final Queue<borrowRequests> bookRequests = new ArrayDeque<>();
    public static final Queue<ReturnRequests> returnRequests = new ArrayDeque<>();
    static{
        usersData.put("Mani",new Member("Mani","Mani",23,"Male","Mani"));
        booksData.put("ISBN1", new Book("Book1","ISBN1", "Author-1", "Date1","Genre1", 2));
        booksData.put("ISBN2", new Book("Book2","ISBN2", "Author-1", "Date2","Genre2", 3));
    }
    Database(){
    }

    // Temporary data for testing...

    //User Info
    public HashMap<String,User> usersDB(){
        return usersData;
    }

    public Queue<borrowRequests> requestsDB(){
        return bookRequests;
    }
    
    public Queue<ReturnRequests> returnsDB(){
        return returnRequests;
    }


    public HashMap<String,Book> booksDB(){
        return booksData;
    }

    public boolean userExistence(String email){
        if(usersData.containsKey(email)){
            return true;
        }
        return false;
    }

    //Check for Book existence
    public boolean bookExistence(String ISBN){
        for(Book b: booksData.values()){
            System.out.println(b.getISBN()+"-->"+ISBN);
            if(b.getISBN().equals(ISBN) ){
                return true;
            }
        }
        return false;
    }

    // get Book using ISBN:
    public Book getBook(String ISBN){
        return booksData.get(ISBN);
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
    Scanner sc = new Scanner(System.in);

    void getBookProperty(Book book){
        String bookProperties [] = new String[]{"BookName","Book Author","Book ISBN","Book Published Date","Book Genre","No of copies Left"};
        System.out.println("Tell me! What do you want to know about the book: ");
        for(int i=0;i<bookProperties.length;i++){
            System.out.printf("%d. %s\n",i+1,bookProperties[i]);
        }
        System.out.println("Enter Your Choice");
        int option = sc.nextInt();
        switch(option){
            case 1: book.getBookName();
            break;
            case 2: book.getAuthorName();
            break;
            case 3: book.getISBN();
            break;
            case 4: book.getPublishedDate();
            break;
            case 5: book.getGenre();
            break;
            case 6: book.getNoOfCopies();
            break;
            default:
                System.out.println("Choose correct option...");
        }
    }
    public static void listAvailableBooks(){
        System.out.println("====Books List====");

        for(String ISBN : booksData.keySet()){
            Book book = booksData.get(ISBN);
            System.out.printf("Book Name (ISBN: %s):%s \n Book Author: %s\nPublished Date: %s\nBook Genre: %s",book.getISBN(),book.getBookName(),book.getAuthorName(),book.getPublishedDate(),book.getGenre());
        }
    }
}
