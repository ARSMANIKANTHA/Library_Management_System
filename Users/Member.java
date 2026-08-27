package Users;

import java.util.Scanner;

class Member extends User{

    String[] availableActions = {"List All Books","Request A book","Return A book","Pay Fine","Update self Details","Delete My account"};
    public Member(String firstName,String lastName,int age,String Gender,String email){
        super(firstName,lastName,age, Gender,email);
    }
    
    Member(){}

    Scanner sc = new Scanner(System.in);
    //list actions
    @Override
    void listActions() {
       System.out.println("Hi, Hello Member...Welcome to Library...!");
       System.out.println("What do you want to do today??");
       System.out.println("===> Available Actions <===");
       for(int i=0;i<availableActions.length;i++){
        System.out.printf("%d: %s\n",i+1,availableActions[i]);
       }
       System.out.println("===> End of the List <===");
       int input = sc.nextInt();
       switch(input){
            case 1:printAllBooks();
            break;
            case 2: borrowBook();
            break;
            case 3: returnBook();
            break;
            case 4:payFine();
            break;
            case 100: switchLogin();
            default:
                System.out.println("Feature is not available");
       }
    }


    void borrowBook(){
        printAllBooks();
        System.out.println("Choose any book from above available books.");
        System.out.println("Enter Book ISBN: ");
        sc.nextLine();
        String borrowISBN = sc.nextLine();
        if(db.bookExistence(borrowISBN)){
            System.out.println("Book is Available..!");
            System.out.println("Enter no of Borrowal Days (Value should be less than 10): ");//need to add constraints
            String  days = sc.nextLine(); //Need to add int variable for days
            Requests newRequest = new Requests(this,db.booksDB().get(borrowISBN),"today",days);
            db.requestsDB().offer(newRequest);
            System.out.println("******Requests---Queue******"+ db.bookRequests);
            System.out.println("Your Request has been added to the queue...!");
            return;
        }
        System.out.println("Book is not available at the moment!!");
        return;
    }

    void returnBook(){
        System.out.println("Returned a book");
    }

    void payFine(){
        System.out.println("Fine pay completed");
    }

}
