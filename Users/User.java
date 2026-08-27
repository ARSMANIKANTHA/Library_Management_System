package Users;

import Books.Book;

public abstract class User{
    protected String firstName;
    protected String lastName;
    protected String passWord;
    protected int age;
    protected String Gender;
    protected String email;
    //Automatic fields
    private static int userCount = 0;

    //session Switch (This is for just testing purpose...)
    void switchLogin(){
        LoginService login = new LoginService(db);
        login.Login();
    }

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
        //table Formatting
        String bookName = "Book Name";
        String authorName = "Author Name";
        String genre = "Genre";
        String noOfCopies = "No of Copies";
        int maxLenBook = bookName.length();
        int maxLenAuthor = authorName.length();
        int maxLenGenre = genre.length();
        for(Book b : db.booksDB().values()){
            maxLenBook = Math.max(b.getBookName().length(),maxLenBook);
            maxLenAuthor = Math.max(b.getAuthorName().length(),maxLenAuthor);
            maxLenGenre = Math.max(b.getGenre().length(),maxLenGenre);
        }
        int otherExtraCharLen = 11;
        System.out.println("-".repeat(maxLenBook+maxLenAuthor+maxLenGenre+noOfCopies.length()+otherExtraCharLen));
        System.out.println("| Book Name"+" ".repeat(maxLenBook-bookName.length())+" | "+"Author Name"+" ".repeat(maxLenAuthor-authorName.length())+" | "+"Genre"+" ".repeat(maxLenGenre-genre.length())+" | "+"No of Copies");
        System.out.println("-".repeat(maxLenBook+maxLenAuthor+maxLenGenre+noOfCopies.length()+otherExtraCharLen));
        //table Logic
        for(Book b : db.booksDB().values()){
            System.out.println("| "+b.getBookName()+" ".repeat(maxLenBook-b.getBookName().length())
            +" | "+b.getAuthorName()+" ".repeat(maxLenAuthor-b.getAuthorName().length())
            +" | " +b.getGenre()+" ".repeat(maxLenGenre-b.getGenre().length())
            +" | " +b.getNoOfCopies()+" |");
            System.out.println("-".repeat(maxLenBook+maxLenAuthor+maxLenGenre+noOfCopies.length()+otherExtraCharLen));
        }
        
    }

}