package Users;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import CustomExceptions.InvalidEmailException;
import CustomExceptions.DuplicateEmailException;

import java.util.regex.Matcher;;

public class LoginService {
    Database db;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    public LoginService(Database db) {
        this.db = db;
    }
    Scanner sc = new Scanner(System.in);

    //Login : Takes user choice
    public void Login() {
        int userSelection = -1;
        System.out.println("\n=== Hello User, Welcome to Library Management System...! ====");
        while (true) {
            System.out.println("=".repeat(10));
            System.out.println("1.Login\n2.Register User\n(Choose Your Option)");
            System.out.println("=".repeat(10));
            try {
                userSelection = sc.nextInt();
                if (userSelection == 1 || userSelection == 2 ) {
                    break;
                }
                System.out.println("Choose Correct Value: ");
            } 
            catch (InputMismatchException e) {
                System.out.println("ERROR: Input Mismatch..!");
                sc.next();
            }
        }
        if (userSelection == 1) {
            signin();
        } else{
            if(registerUser()){
                signin();
            }else{
                System.out.println("Signin Failed..!");
            }
        }
    }

    // Actual Login: Takes your UserName, Password => Check against the DB => AND LOGS YOU IN
    private  void signin(){
        while(true){
                //take input
                System.out.println(YELLOW+ "Enter Your User Name:" +RESET);
                String userID = sc.next();
                System.out.println("Enter Your Password: ");
                String password = sc.next();
                //vaidate details
                if(validateUser(userID, password)){
                    User curUser = db.usersDB().get(userID);
                    //if valid: continue doing all actions
                    curUser.listActions();
                    break;
                }else{
                    //else repeat the process
                    String temp = "Invalid Credentials!! Try Again!!";
                    System.out.println("-".repeat(temp.length()));
                    System.out.println(temp);
                    System.out.println("-".repeat(temp.length()));
                }
            }
    }

    // Register New User : Takes your Details => Creates an account with your details    
    private boolean registerUser() {
        System.out.println("-".repeat(25));
        System.out.println("USER REGISTRATION");
        System.out.println("-".repeat(25));
        sc.nextLine();
        System.out.println("Enter FirstName: ");
        String firstName = sc.nextLine();
        System.out.println("Enter LastName: ");
        String lastName = sc.nextLine();
        int age = 0;
        while(true){
            try{
                System.out.println("Enter Age: ");
                age = sc.nextInt();
                if(age >=1 && age <= 100){
                    break;
                }
                throw new InputMismatchException("Please Enter Age Between the Range (0 - 100)...!");
            }
            catch(InputMismatchException e){
                System.out.println("Invalid Input..!");
                sc.nextLine();
            }
        }
        sc.nextLine();
        System.out.println("Enter Gender: ");
        String Gender = sc.nextLine();
        String email = null;
        while(true){
            try{
                System.out.println("Enter Your Email: ");
                email = sc.nextLine();
                if(emailExists(email)){
                    throw new DuplicateEmailException("< DUPLICATE USER >");
                }
                if(validateEmail(email)){
                    break;
                }
            }
            catch(InputMismatchException e){
                System.out.println(RED+"-".repeat(email.length()));
                System.out.println("<INVALID EMAIL>: "+e.getMessage());
                System.out.println("-".repeat(email.length())+RESET);
            }
            catch(DuplicateEmailException e){
                System.out.println(RED+"-".repeat(email.length())+RESET);
                System.out.println(e.getMessage()+" User Already Exists");
                System.out.println(RED+"-".repeat(email.length())+RESET);
            }
        }
        
        //User Creation with the provided details...
        User tempUser = new Member(firstName, lastName, age, Gender, email);
        db.addNewUser(tempUser);
        System.out.println(YELLOW+"==== Registration is Successful..!! ===="+RESET);
        System.out.printf(
            "USER NAME : %s\nTemporary Password (Change temporary password...): %s\n",
            tempUser.email, tempUser.passWord);
            return true;
    }
    
    //Check User Exists already
    private boolean emailExists(String email){
        if(db.usersDB().containsKey(email)){
            return true;
        }
        return false;
    }

    //Validating the USER
    private boolean validateUser(String email, String password) {
        if(emailExists(email)){
            if(db.usersDB().get(email).passWord.equals(password)){
                return true;
            }
        }
        return false;
    }
    //Validating the EMAIL
    boolean validateEmail(String inputEmail) throws InputMismatchException{
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputEmail);
        if(matcher.matches()){
            return true;
        }
        throw new InvalidEmailException("Invalid EMAIL");
    }
}
