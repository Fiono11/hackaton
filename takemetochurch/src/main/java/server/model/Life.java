package server.model;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Life implements Data {

    private long id;

    private String message;

    public Life() {

    }

    public Life(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
