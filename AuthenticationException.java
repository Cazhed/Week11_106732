package week11.zachary.id.ac.umn;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Anda telah mencapai jumlah batas login");
    }

    public AuthenticationException(String msg) {
        super(msg);
    }
}
