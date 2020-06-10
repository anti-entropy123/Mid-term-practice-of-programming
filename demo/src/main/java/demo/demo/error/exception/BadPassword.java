package demo.demo.error.exception;

public class BadPassword extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 2038372473262908608L;
    public BadPassword(){
        super();
    }
    public BadPassword(String message){
        super(message);
    }
}