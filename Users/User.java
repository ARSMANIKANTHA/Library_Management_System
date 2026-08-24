package Users;

abstract class User{
    protected String firstName;
    protected String lastName;
    protected String passWord;
    protected int age;
    protected String Gender;
    //Automatic fields
    protected String userID;
    private static int userCount = 0;

    public User(String firstName,String lastName,int age,String Gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.Gender = Gender;
        this.userID = "USER0"+(++User.userCount);
        this.passWord = this.userID;
    }
    User(){}
}