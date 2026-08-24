package Users;

public class Session {
    User loggedInUser;

    public void startSession(User curUser){
        if(curUser instanceof Librarian){
            Librarian admin = (Librarian) curUser;
            // admin.listFunctions();
        }else{
            Member member = (Member) curUser;
            // member.listFunctions();
        }
    }
}
