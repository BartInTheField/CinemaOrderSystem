package exceptions;

public class TicketsNotForTheSameScreeningException extends Exception {
    public TicketsNotForTheSameScreeningException(String message) {
        super(message);
    }
}
