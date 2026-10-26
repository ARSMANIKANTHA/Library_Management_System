package CustomExceptions;

import java.io.File;
import java.io.FileReader;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message){
        super(message);
        FileReader abc =null;
        File file = new File("");   
    }
}