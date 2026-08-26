package Users;
import java.util.Scanner;
import Books.Book;
public class Librarian extends User{

    String[] availableActions = {"Add User","Remove User","Update User Info","Add Book",
    "Remove A book","Update Book Info","List all users","List all Books","Approve Borrow",
    "Reject Borrow","Add Fine","Accept Fine","Withdraw Fine"};
    
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
        case 4:
            addBook();
            break;
        case 5: removeBook();
        break;
        case 7: printAllUsers();
        break;
        case 8: printAllBooks();
        break;
        default:
            System.out.println("This is the default code...");
       }
       listActions();
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

    // User Functionalities
    public void addUser(){
        System.out.println("==== ADDING NEW USER ====");
        System.out.println("**** Enter New User Details ****");
        System.out.println("Enter FirstName: ");
        String firstName = sc.next();
        System.out.println("Enter LastName: ");
        String lastName = sc.next();
        System.out.println("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Gender: ");
        String Gender = sc.next();
        System.out.println("Enter Your Gmail: ");
        String email = sc.next();
        System.out.println("Enter Your email Address: ");
        System.out.println("Checking the list of Users");
        if(!db.userExistence(email)){
            db.addNewUser(new Member(firstName,lastName,age,Gender,email));
            System.out.println("User Added Successfully...");
            return;
        }
        System.out.println("User already exists..!");
        return;
    }

    //Remove a user
    public void removeUser(){
        System.out.println("Enter User ID");
        sc.nextLine();
        System.out.println("Enter User's Email address whom you want to remove: ");
        String email = sc.next();
        if(db.userExistence(email)){
            db.removeUser(email);
            return;
        }
        System.out.println("User Doesn't exist");
        return;
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
        System.out.println("=====> Adding a Book to the Library <=====");
        getBookDetails();
    }
    
    //getBookDetails
    void getBookDetails(){
        System.out.println("Enter Book Name:");
        String bookName = sc.next();
        System.out.println("Enter Book ISBN");
        // sc.next();
        String ISBN = sc.next();
        System.out.println("Enter Author Name");
        String authorName = sc.next();
        System.out.println("Enter Book's Published Date: ");
        String publishedDate = sc.next();
        System.out.println("Enter Book Genre: ");
        String genre = sc.next();
        System.out.println("Enter Number of Copies: ");
        int noOfCopies = sc.nextInt();
        if(db.bookExistence(ISBN)){
            System.out.println("Book Already Exists...!");
            return;
        }
        db.addBook(new Book(bookName,ISBN,authorName,publishedDate,genre,noOfCopies));
        return;
    }

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
}
