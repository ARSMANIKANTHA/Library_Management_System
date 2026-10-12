package Users;
import java.util.InputMismatchException;
import java.util.Scanner;
import Books.Book;
import CustomExceptions.InvalidFieldException;
import CustomExceptions.ObjectNotFoundException;

public class Member extends User {
    String[] availableActions = { "List All Books", "Request A book", "Return A book", "List My Borrowals","New Notifications" };

    public Member(String firstName, String lastName, int age, String Gender, String email) {
        super(firstName, lastName, age, Gender, email);
    }

    public Member() {
    }

    Scanner sc = new Scanner(System.in);


    // list actions
    @Override
    void listActions() {
        System.out.println("Hi, Hello Member...Welcome to Library...!");
        System.out.println("What do you want to do today??");
        System.out.println("===> Available Actions <===");
        for (int i = 0; i < availableActions.length; i++) {
            System.out.printf("%d: %s\n", i + 1, availableActions[i]);
        }
        System.out.println("===> End of the List <===");
        int input = sc.nextInt();
        switch (input) {
            case 1:
                printAllBooks();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                listMyBorrowals();
                break;
            case 5:
                listNotifications();
                break;
            case 100:
                switchLogin();
            default:
                System.out.println("Feature is not available");
        }
    }

    void borrowBook() {
        printAllBooks();
        System.out.println("Choose any book from above available books.");
        String borrowISBN = null;
        Book availableBook;
        while (true) {
            try {
                System.out.println("Enter Book ISBN: ");
                borrowISBN = sc.nextLine();
                if (db.bookExistence(borrowISBN)) {
                    availableBook = db.getBook(borrowISBN);
                    break;
                }
                throw new ObjectNotFoundException("Book with ISBN " + borrowISBN + " is Not Found");
            } catch (ObjectNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        if (availableBook.getNoOfCopies() == 0) {
            System.out.println("Book is not avaialble at the moment..!");
            return;
        }
        System.out.println("Book is Available..!");
        int days = 0;
        while (true) {
            try {
                System.out.println("Enter no of Borrowal Days : ");// need to add constraints
                days = sc.nextInt();
                if (days > 0) {
                    break;
                }
                if (days <= 0) {
                    throw new InvalidFieldException(days + " is not a valid Input. Enter a Valid Number!");
                }
                throw new InputMismatchException();
            } catch (InvalidFieldException f) {
                System.out.println(f.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid Number of Days!");
                sc.nextLine();
            }
        }
        borrowRequests newRequest = new borrowRequests(this, availableBook, "today", days);
        db.requestsDB().offer(newRequest);
        System.out.println("Your Request has been added to the queue...!");
        return;
    }

    // List my borrowals
    void listMyBorrowals() {
        //how to list all my borrowals
       db.getBorrowals(this);
    }

    // Return the Book
    void returnBook() {
        sc.nextLine();
        System.out.println("=== BOOK RETUNRN ===");
        String returnISBN = null;
        while(true){
            try{
                System.out.println("Enter ISBN of the Book that You want to return: ");
                returnISBN = sc.nextLine();
                if(!db.bookExistence(returnISBN)){
                    throw new InputMismatchException("Please check the entered ISBN value...");
                }else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        //ReturnRequests(User reqUser, Book reqBook, String reqDate)
        ReturnRequests newReturnRequest = new ReturnRequests(this, db.booksDB().get(returnISBN), "today");
        db.returnsDB().offer(newReturnRequest);
        System.out.println("Return request has been successfully added to the queue");
    }
}
