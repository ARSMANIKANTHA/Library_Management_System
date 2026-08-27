package Users;
import java.util.Scanner;
import Books.Book;
import Books.borrowedBook;
import Books.borrowedUser;
public class Librarian extends User{

    String[] availableActions = {"Add User","Remove User","Add Book",
    "Remove A book","List all users","List all Books","Check Borrow Requests"};
    //Librarian Constructors...
    public Librarian(){}

    Librarian(String firstName,String lastName,int age, String Gender,String email){
        super(firstName,lastName,age,Gender,email);
    }

    //Scanner class to scan the input
    Scanner sc = new Scanner(System.in);
    @Override
    void listActions() {
       System.out.println("Hi, Hello Admin...Welcome to Library...!");
       System.out.println("What do you want to do today??");
       System.out.println("===> Available Actions <===");
       for(int i=0;i<availableActions.length;i++){
        System.out.printf("%d: %s\n",i+1,availableActions[i]);
       }
       System.out.println("===> End of the List <===");
       System.out.println("Enter your Action");
       int input = sc.nextInt();
       switch(input){
        case 1: addUser();
        break;
        case 2: removeUser();
        break;
        case 3:addBook();
        break;
        case 4: removeBook();
        break;
        case 5: printAllUsers();
        break;
        case 6: printAllBooks();
        break;
        case 7: checkRequests();
        break;
        case 100: switchLogin();
        break;
        default:
            System.out.println("END OF CODE");
       }
       listActions();
    }

     // User Functionalities
     //Add User
    public void addUser(){
        System.out.println("==== ADDING NEW USER ====");
        System.out.println("**** Enter New User Details ****");
        System.out.println("Enter FirstName: ");
        sc.nextLine();
        String firstName = sc.nextLine();
        System.out.println("Enter LastName: ");
        String lastName = sc.nextLine();
        System.out.println("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Gender: ");
        String Gender = sc.nextLine();
        System.out.println("Enter Your email: ");
        String email = sc.nextLine();
        System.out.println("Checking the list of Users");
        if(!db.userExistence(email)){
            db.addNewUser(new Member(firstName,lastName,age,Gender,email));
            System.out.println("User Added Successfully...");
            return;
        }
        System.out.println("User already exists..!");
        return;
    }

    //Remove User
    public void removeUser(){
        System.out.println("Enter User ID");
        sc.nextLine();
        System.out.println("Enter User's Email address whom you want to remove: ");
        String email = sc.nextLine();
        if(db.userExistence(email)){
            db.removeUser(email);
            return;
        }
        System.out.println("User Doesn't exist");
        return;
    }

     //Book Functionalities....

    
    //To check book presence
    boolean isAvailable(String ISBN){
        if(db.bookExistence(ISBN)){
            if(db.getBook(ISBN).getNoOfCopies() < 1){
                System.out.println("All Copies are borrowed");
                return false;
            }
            return true;
        }
        return false;
    }

    void addBook(){
        System.out.println("=====> Adding a Book to the Library <=====");
        getBookDetails();
    }
    
    //getBookDetails
    void getBookDetails(){
        System.out.println("Enter Book Name:");
        sc.nextLine();
        String bookName = sc.nextLine();
        System.out.println("Enter Book ISBN");
        // sc.next();
        String ISBN = sc.nextLine();
        System.out.println("Enter Author Name");
        String authorName = sc.nextLine();
        System.out.println("Enter Book's Published Date: ");
        String publishedDate = sc.nextLine();
        System.out.println("Enter Book Genre: ");
        String genre = sc.nextLine();
        System.out.println("Enter Number of Copies: ");
        int noOfCopies = sc.nextInt();
        if(db.bookExistence(ISBN)){
            System.out.println("Book Already Exists...!");
            return;
        }
        db.addBook(new Book(bookName,ISBN,authorName,publishedDate,genre,noOfCopies));
        return;
    }

    //REMOVING A BOOK
    void removeBook(){
        System.out.println("Enter ISBN of the Book that you want to remove..!\nISBN: ");
        String bookISBN = sc.next();
        if(db.bookExistence(bookISBN)){
            System.out.println("How many copies of the book you want to remove..");
            int noOfCopies = sc.nextInt();
            int curCount = db.getBook(bookISBN).getNoOfCopies();
            if(curCount >= noOfCopies ){
                int newValue = db.getBook(bookISBN).getNoOfCopies() - noOfCopies;
                db.getBook(bookISBN).setNoOfCopies(newValue);
                System.out.println(noOfCopies+" has been removed Successfully...");
            }else{
                System.out.printf("==== ERROR: %d of books are not available ====",noOfCopies);
            }
        }
    }

    //Printing all the users
    public void printAllUsers(){
        System.out.println("\n==========:- USERS LIST -:==========");
        for(User a : db.usersDB().values()){
            System.out.println("\n----------------------");
            System.out.printf("Full Name: %s %s \nAge: %d\nGender: %s\nUser ID: %s\nPassword: %s",a.firstName,a.lastName,a.age,a.Gender,a.email,a.passWord);
            System.out.println("\n----------------------");
        }
        System.out.println("\n==========:- END OF LIST -:==========");
    }

    // To check the requests (If there are requests, and for each request check book availability and then assign the book)
    void checkRequests(){
        System.out.println(db.requestsDB());
        if(!db.requestsDB().isEmpty()){
            for(Requests req : db.requestsDB()){
                String ISBN = req.requestedBook.getISBN();
                if(isAvailable(ISBN)){
                    //Book is allocated once it is available...
                    System.out.println("USER \""+ req.requestedUser.firstName + "\"has requested for the Book \"" + req.requestedBook.getBookName() +"\" and it is Available..!");
                    db.booksDB().get(ISBN).setNoOfCopies(db.booksDB().get(ISBN).getNoOfCopies()-1);
                    approveBorrowal(req.requestedBook, req.requestedUser,req.requestDate,req.noOfDays);
                }else{
                    rejectBorrowal();
                }
            }
        }else{
            System.out.println("Db is empty");
        }
    }

    //When approving the book, applying changes to the Database and adding requests to the queue...
    void approveBorrowal(Book book,User user,String startDate,String noOfDays){
        System.out.println("===Allocating Book to the User===");
        System.out.println("===Updating the Database with the latest Borrowals");
        System.out.println("===Updating the User DB with latest Borrowals===");
        borrowedUser bd = new borrowedUser(user,"startdate",noOfDays,startDate+noOfDays);
        borrowedBook bb = new borrowedBook(book, startDate, noOfDays, noOfDays);
        ((Member)user).borrowedBooks.add(bb);
        book.borrowalList.add(bd);
    }


     //Upcoming Features Features....to be added once remaining dev completed
    void addFine(){
        System.out.println("Fined user");
    }
    
    void approveReturn(){
        System.out.println("Approved return");
    }
 
    void rejectBorrowal(){
        System.out.println("Book is not available!! Check after few Days...");
    }
    // updateBookDetails()
    // updateUserInfo()
    // "Add Fine","Accept Fine","Withdraw Fine"
}
