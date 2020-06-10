package demo.demo.error.exception;

public class AlreadyHasOpinion extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 8802517162958799150L;
    public AlreadyHasOpinion(){super();}
    public AlreadyHasOpinion(String message){super(message);}
}