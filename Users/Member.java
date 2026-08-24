package Users;

class Member extends User{

    public Member(String firstName,String lastName,int age,String Gender){
        super(firstName,lastName,age, Gender);
    }
    
    Member(){}

    void borrowBook(){
        Database.listAvailableBooks();
    }
    void returnBook(){
        System.out.println("Returned a book");
    }

    void payFine(){
        System.out.println("Fine pay completed");
    }

}
