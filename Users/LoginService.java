package Users;

import java.util.Scanner;

public class LoginService {
    Database db;
    public LoginService(Database db){
        this.db = db;
    }
    Scanner sc = new Scanner(System.in);
    public void Login() {
        System.out.println("Login : Enter 1 (press Enter) \nRegister : Enter 2 (press Enter)");
        int userSelection = sc.nextInt();
        if (userSelection == 1) {
            System.out.println("Enter Your User Name:");
            String userID = sc.next();
            System.out.println("Enter Your Password");
            String password = sc.next();
            validateUser(userID, password);
        } else if(userSelection == 2) {
            registerUser();
        }else{
            System.out.println("<ERROR: Invalid Input Try again...>");
            Login();
            return;
        }
    }

    private void validateUser(String email, String password) {
        User tempUser = db.usersDB().get(email);
        if (tempUser == null) {
            System.out.println("====ERROR:User Doesn't exist====");
            return;
        }
        if (email.equals(tempUser.email) && password.equals(tempUser.passWord)) {
            System.out.println("Validation Successful");
            tempUser.listActions();
            return;
        }
        System.out.println("Invalid Credentials");
        Login();
        return;
    }

    void registerUser() {
        System.out.println("====USER REGISTRATION====");
        System.out.println("Enter FirstName: ");
        sc.next();
        String firstName = sc.nextLine();
        System.out.println("Enter LastName: ");
        String lastName = sc.nextLine();
        System.out.println("Enter Age: ");
        int age = sc.nextInt();
        System.out.println("Enter Gender: ");
        sc.next();
        String Gender = sc.nextLine();
        System.out.println("Enter Your Email: ");
        String email = sc.next();
        User tempUser = new Member(firstName, lastName, age, Gender,email);
        db.addNewUser(tempUser);
        System.out.printf(
                "Registration is Successful..!!\n USER NAME : %s\n Temporary Password: %s\n(Change temporary password...)",
                tempUser.email, tempUser.passWord);
        Login();

    }
}
