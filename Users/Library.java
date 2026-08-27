package Users;

public class Library{
   private Database db = new Database();
   public LoginService login = new LoginService(db);
   // public Transactions trans = new Transactions(db);
}