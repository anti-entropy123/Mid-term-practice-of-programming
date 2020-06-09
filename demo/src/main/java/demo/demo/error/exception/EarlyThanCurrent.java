package demo.demo.error.exception;

public class EarlyThanCurrent extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 6489520461921523423L;
    public EarlyThanCurrent(){super();}
    public EarlyThanCurrent(String message){super(message);}
}