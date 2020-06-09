package demo.demo.error.exception;

public class TimeCoincidence extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -1532661746166427743L;
    
    public TimeCoincidence(){super();}
    public TimeCoincidence(String message){super(message);}
}