package Users;

import Books.Book;

abstract class User{
    protected String firstName;
    protected String lastName;
    protected String passWord;
    protected int age;
    protected String Gender;
    protected String email;
    //Automatic fields
    private static int userCount = 0;

    Database db = new Database();
    public User(String firstName,String lastName,int age,String Gender,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.Gender = Gender;
        this.email = email;
        this.passWord = firstName;
        User.userCount++;
    }
    User(){}


    //common methods for both Librarian or Member
    abstract void listActions();

     //Printing all the Books
    void printAllBooks(){
        System.out.println("\n ========:- BOOKS LIST -:=======");
        for(Book b : db.booksDB().values()){
            System.out.println("\n----------------------");
            System.out.printf("Book Name: %s \nISBN: %s\nAuthor Name: %s\nPublished Date: %s\nGenre: %s\n No of Copies: %d",b.getBookName(),b.getISBN(),b.getAuthorName(),b.getPublishedDate(),b.getGenre(),b.getNoOfCopies());
            System.out.println("\n----------------------");
        }
        System.out.println("\n ========:- END OF LIST -:=======");
        
    }

    //updateSelfDetails
    void updateMyDetails(){
        User tempUser;
        for(User user : db.usersDB().values()){
            if(user.email.equals(this.email)){
                System.out.println("Modifying My own details");
            }
        }

    }
}