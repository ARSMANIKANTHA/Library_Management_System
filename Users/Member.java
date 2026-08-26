package Users;

import java.util.Scanner;

class Member extends User{

    String[] availableActions = {"Update self Details","Delete My account","List All Books","Request A book","Return A book","Pay Fine"};
    public Member(String firstName,String lastName,int age,String Gender,String email){
        super(firstName,lastName,age, Gender,email);
    }
    
    Member(){}

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
    }

    void borrowBook(){
        Database.listAvailableBooks();
        Scanner sc = new Scanner(System.in);
        String ISBN = sc.next();
        sc.close();
    }
    void returnBook(){
        System.out.println("Returned a book");
    }

    void payFine(){
        System.out.println("Fine pay completed");
    }

}
