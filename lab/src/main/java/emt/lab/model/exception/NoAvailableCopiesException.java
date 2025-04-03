package emt.lab.model.exception;

public class NoAvailableCopiesException extends RuntimeException{
    public NoAvailableCopiesException(long id){
        super("Book with id: " + id + " is not available for rent");
    }
}
