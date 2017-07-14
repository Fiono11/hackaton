package server.persistence;

/**
 * Created by Cyrille on 13/07/17.
 */
public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }
}
