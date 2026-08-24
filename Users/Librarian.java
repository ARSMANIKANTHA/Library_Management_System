package Users;
import java.util.Scanner;
import Books.Book;
public class Librarian extends Member{

    Database db = new Database();

    //Librarian Constructors...
    public Librarian(){}

    Librarian(String firstName,String lastName,int age, String Gender){
        super(firstName,lastName,age,Gender);
    }

    public void printAllUsers(){
        System.out.println("\n==========:- USERS LIST -:==========");
        for(User a : db.usersDB().values()){
            System.out.println("\n----------------------");
            System.out.printf("Full Name: %s %s \nAge: %d\nGender: %s\nUser Type: %s\nUser ID: %s\nPassword: %s",a.firstName,a.lastName,a.age,a.Gender,resolveUserType(a),a.userID,a.passWord);
            System.out.println("\n----------------------");
        }
        System.out.println("\n==========:- END OF LIST -:==========");
    }

    public String resolveUserType(User a){
        return a.getClass().toString();
    }

    // User Functionalities
    public void addUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter FirstName: ");
        String firstName = sc.nextLine();
        System.out.println("Enter LastName: ");
        String lastName = sc.nextLine();
        System.out.println("Enter Age: ");
        int age = sc.nextInt();
        System.out.println("Enter Gender: ");
        sc.next();
        String Gender = sc.nextLine();
        db.addUser(new Member(firstName,lastName,age,Gender));
    }

    //Remove a user
    public void removeUser(String userID){
        db.removeUser(userID);
    }


    void approveBorrowal(){
        System.out.println("Borrowal approved");
    }

    void approveReturn(){
        System.out.println("Approved return");
    }

    void addFine(){
        System.out.println("Fined user");
    }
    
    //Need to update the functionality

    //Book Functionalities....
    void addBook(){
        Book newBook = getBookDetails();
        db.booksData.put(newBook.getISBN(),newBook);
    }

    //getBookDetails
    Book getBookDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Book Name:");
        String bookName = sc.next();
        System.out.println("Enter Book ISBN");
        String ISBN = sc.next();
        System.out.println("Enter Author Name");
        String authorName = sc.next();
        String publishedDate = sc.next();
        String genre = sc.next();
        sc.next();
        int noOfCopies = sc.nextInt();
        return new Book(bookName,ISBN,authorName,publishedDate,genre,noOfCopies);
    }

    void removeBook(){
        Scanner sc = new Scanner(System.in);
        String bookISBN = sc.next();
        int noOfCopies = sc.nextInt();
        int curCount = db.booksData.get(bookISBN).getNoOfCopies();
        if(curCount >= noOfCopies ){
            int newValue = db.booksData.get(bookISBN).getNoOfCopies() - noOfCopies;
            db.booksData.get(bookISBN).setNoOfCopies(newValue);
            System.out.println(noOfCopies+" has been removed Successfully...");
        }else{
            System.out.printf("==== ERROR: %d of books are not available ====",noOfCopies);
        }
    }
}
