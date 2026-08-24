package Users;

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
        userCount++;
    }
    User(){}
    abstract void listActions();
}