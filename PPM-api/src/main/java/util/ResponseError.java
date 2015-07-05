package util;

/**
 * Created by Wojciech on 2015-04-17.
 */
public class ResponseError {

    private String message;

    public ResponseError(String message, String args) {
        this.message = String.format(message, args);
    }

    public ResponseError(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
