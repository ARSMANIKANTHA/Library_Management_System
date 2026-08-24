package Users;
import java.util.Scanner;
import Books.Book;
public class Librarian extends User{

    String[] availableActions = {"Add User","Remove User","Update User Info","Approve Borrow","Reject Borrow","Add Fine","Accept Fine","Withdraw Fine","Add Book","Remove A book","Update Book Info","List all users"};
    
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
        printAllUsers();
        break;
        case 2: 
        System.out.println("Enter User ID");
        // removeUser(email);
        break;
        default:
            System.out.println("This is the default code...");
       }
    }

    public void printAllUsers(){
        System.out.println("\n==========:- USERS LIST -:==========");
        for(User a : db.usersDB().values()){
            System.out.println("\n----------------------");
            System.out.printf("Full Name: %s %s \nAge: %d\nGender: %s\nUser Type: %s\nUser ID: %s\nPassword: %s",a.firstName,a.lastName,a.age,a.Gender,a.email,a.passWord);
            System.out.println("\n----------------------");
        }
        System.out.println("\n==========:- END OF LIST -:==========");
    }

    // User Functionalities
    public void addUser(){
        sc = new Scanner(System.in);
        System.out.println("==== ADDING NEW USER ====");
        System.out.println("**** Enter New User Details ****");
        System.out.println("Enter FirstName: ");
        String firstName = sc.next();
        System.out.println("Enter LastName: ");
        String lastName = sc.next();
        System.out.println("Enter Age: ");
        sc.next();
        int age = sc.nextInt();
        System.out.println("Enter Gender: ");
        String Gender = sc.next();
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

    // //Book Functionalities....
    // void addBook(){
    //     Book newBook = getBookDetails();
    //     db.booksData.put(newBook.getISBN(),newBook);
    // }

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

    // void removeBook(){
    //     Scanner sc = new Scanner(System.in);
    //     String bookISBN = sc.next();
    //     int noOfCopies = sc.nextInt();
    //     int curCount = db.booksData.get(bookISBN).getNoOfCopies();
    //     if(curCount >= noOfCopies ){
    //         int newValue = db.booksData.get(bookISBN).getNoOfCopies() - noOfCopies;
    //         db.booksData.get(bookISBN).setNoOfCopies(newValue);
    //         System.out.println(noOfCopies+" has been removed Successfully...");
    //     }else{
    //         System.out.printf("==== ERROR: %d of books are not available ====",noOfCopies);
    //     }
    // }
}
