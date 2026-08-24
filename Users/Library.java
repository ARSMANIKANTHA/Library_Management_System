package Users;

public class Library{
   private Librarian admin = new Librarian("Administrator","Administrator",28,"Male");
   private Database db = new Database(admin);
   public LoginService login = new LoginService();
}